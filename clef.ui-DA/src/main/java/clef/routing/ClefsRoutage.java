package clef.routing;

import clef.datasource.MapperManager;
import clef.datasource.Utils;
import clef.domain.Clef;
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
@WebServlet(name = "ClefsRoutage",
        urlPatterns = {"/clefs.html"})
public class ClefsRoutage extends Routage {

    private TransactionManager transactionManager;
    public static final String JSP_ATTRIBUT_FILTRE = "filtre";

    @Override
    public void init() throws ServletException {

        this.transactionManager
                = Utils.getTransactionManager(this.getServletContext());

        actions.put(ActionPage.FILTRER.name(), new Action.Forward("/WEB-INF/clefs/liste.jsp") {
            @Override
            public void execute(HttpServletRequest request,
                    HttpServletResponse response)
                    throws ServletException, IOException {

                try {
                    List<Clef> liste = new ArrayList<>();
                    final String filtre = request.getParameter(JSP_ATTRIBUT_FILTRE);
                    final String filtreFinal = (filtre == null) ? ".*" : filtre;
                    liste = (List<Clef>) transactionManager.executeTransaction(new TransactionManager.Operation<MapperManager>() {
                        @Override
                        public Object execute(final MapperManager mm)
                                throws PersistenceException {
                            return mm.getClefMapper()
                                    .retrieve(filtreFinal);
                        }
                    });
                    request.setAttribute("liste", liste);
                    super.execute(request, response);
                } catch (SQLException | PersistenceException ex) {
                    Logger.getLogger(ClefsRoutage.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

        actionNull = actions.get(ActionPage.FILTRER.name());

        actions.put(ActionPage.CREER.name(),
                new Action.Forward(ClefUtils.PAGE_JSP_DETAIL) {
            @Override
            public void execute(
                    final HttpServletRequest request,
                    final HttpServletResponse response)
                    throws ServletException, IOException {

                try {
                    List<Clef> liste = new ArrayList<>();
                    final String filtreFinal = ".*";
                    liste = (List<Clef>) transactionManager.executeTransaction(new TransactionManager.Operation<MapperManager>() {
                        @Override
                        public Object execute(final MapperManager mm)
                                throws PersistenceException {
                            return mm.getClefMapper()
                                    .retrieve(".*");
                        }
                    });

                    request.setAttribute("liste", liste);
                    request.getSession().setAttribute(
                            ClefUtils.JSP_ATTRIBUT_ETAT_PAGE,
                            EtatPage.CREATION);
                    super.execute(request, response);
                } catch (SQLException | PersistenceException ex) {
                    Logger.getLogger(ClefsRoutage.class.getName()).log(Level.SEVERE, null, ex);
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
                    final Clef entite = ClefUtils.lireFormulaire(request);

                    Clef nouvelleEntite
                            = (Clef) transactionManager.executeTransaction(
                                    new TransactionManager.Operation<MapperManager>() {
                                @Override
                                public Object execute(final MapperManager mm)
                                        throws PersistenceException {
                                    return mm.getClefMapper().create(entite);
                                }
                            });

                    response.sendRedirect(String.format(
                            ClefUtils.TEMPLATE_URL_DETAIL,
                            request.getContextPath(),
                            nouvelleEntite.getIdentifiant().getUUID()));
                } catch (SQLException | PersistenceException ex) {
                    Logger.getLogger(ClefsRoutage.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }
}
