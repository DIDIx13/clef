package clef.routing;
import clef.datasource.MapperManager;
import clef.datasource.Utils;
import clef.domain.Personne;
import core.datasource.PersistenceException;
import core.datasource.TransactionManager;
import core.web.routing.Action;
import core.web.routing.Routage;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author dominique huguenin (dominique.huguenin at rpn.ch)
 */
@WebServlet(name = "PersonnesRoutage",
        urlPatterns = {"/personnes.html"})
public class PersonnesRoutage extends Routage {

        private TransactionManager transactionManager;
    private static final Logger LOG
            = Logger.getLogger(PersonnesRoutage.class.getName());

    @Override
    public void init() throws ServletException {
        this.transactionManager
                = Utils.getTransactionManager(this.getServletContext());
        
        actions.put(ActionPage.FILTRER.name(), new Action.Forward("/WEB-INF/personnes/liste.jsp") {
            @Override
            public void execute(HttpServletRequest request,
                    HttpServletResponse response)
                    throws ServletException, IOException {
                
                try {
                    List<Personne> liste = new ArrayList<>();
                    final String filtre = request.getParameter(PersonneUtils.JSP_ATTRIBUT_FILTRE);
                    final String filtreFinal = (filtre == null) ? ".*" : filtre;
                    liste = (List<Personne>) transactionManager.executeTransaction(new TransactionManager.Operation<MapperManager>() {
                        @Override
                        public Object execute(final MapperManager mm)
                                throws PersistenceException {
                            return mm.getPersonneMapper()
                                    .retrieve(filtreFinal);
                        }
                    });
                    request.setAttribute("liste", liste);
                    super.execute(request, response);
                } catch (SQLException | PersistenceException ex) {
                    Logger.getLogger(PersonnesRoutage.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        
        actions.put(ActionPage.CREER.name(),
                new Action.Forward(PersonneUtils.PAGE_JSP_DETAIL) {
            @Override
            public void execute(
                    final HttpServletRequest request,
                    final HttpServletResponse response)
                    throws ServletException, IOException {

                try {
                    List<Personne> liste = new ArrayList<>();
                    final String filtreFinal = ".*";
                    liste = (List<Personne>) transactionManager.executeTransaction(new TransactionManager.Operation<MapperManager>() {
                        @Override
                        public Object execute(final MapperManager mm)
                                throws PersistenceException {
                            return mm.getPersonneMapper()
                                    .retrieve(".*");
                        }
                    });

                    request.setAttribute("liste", liste);
                    request.getSession().setAttribute(
                            PersonneUtils.JSP_ATTRIBUT_ETAT_PAGE,
                            EtatPage.CREATION);
                    super.execute(request, response);
                } catch (SQLException | PersistenceException ex) {
                    Logger.getLogger(PersonnesRoutage.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

        actions.put(ActionPage.VALIDER_CREATION.name(),
                new Action() {
            @Override
            public void execute(
                    final HttpServletRequest request,
                    final HttpServletResponse response)
                    throws ServletException, IOException {

                try {
                    final Personne entite = PersonneUtils.lireFormulaire(request);

                    Personne nouvelleEntite
                            = (Personne) transactionManager.executeTransaction(
                                    new TransactionManager.Operation<MapperManager>() {
                                @Override
                                public Object execute(final MapperManager mm)
                                        throws PersistenceException {
                                    return mm.getPersonneMapper().create(entite);
                                }
                            });

                    response.sendRedirect(String.format(
                            PersonneUtils.TEMPLATE_URL_DETAIL,
                            request.getContextPath(),
                            nouvelleEntite.getIdentifiant().getUUID()));
                } catch (SQLException | PersistenceException ex) {
                    Logger.getLogger(PersonnesRoutage.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

        actionNull = actions.get(ActionPage.FILTRER.name());

    }
}
