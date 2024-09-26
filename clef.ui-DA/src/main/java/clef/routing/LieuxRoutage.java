package clef.routing;

import clef.datasource.MapperManager;
import clef.datasource.Utils;
import clef.domain.Lieu;
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
@WebServlet(name = "LieuxRoutage",
        urlPatterns = {"/lieux.html"})
public class LieuxRoutage extends Routage {

    private TransactionManager transactionManager;
    public static final String JSP_ATTRIBUT_FILTRE = "filtre";

    @Override
    public void init() throws ServletException {

        this.transactionManager
                = Utils.getTransactionManager(this.getServletContext());

        actions.put(ActionPage.FILTRER.name(), new Action.Forward("/WEB-INF/lieux/liste.jsp") {
            @Override
            public void execute(HttpServletRequest request,
                    HttpServletResponse response)
                    throws ServletException, IOException {

                try {
                    List<Lieu> liste = new ArrayList<>();
                    final String filtre = request.getParameter(JSP_ATTRIBUT_FILTRE);
                    final String filtreFinal = (filtre == null) ? ".*" : filtre;
                    liste = (List<Lieu>) transactionManager.executeTransaction(new TransactionManager.Operation<MapperManager>() {
                        @Override
                        public Object execute(final MapperManager mm)
                                throws PersistenceException {
                            return mm.getLieuMapper()
                                    .retrieve(filtreFinal);
                        }
                    });
                    request.setAttribute("liste", liste);
                    super.execute(request, response);
                } catch (SQLException | PersistenceException ex) {
                    Logger.getLogger(LieuxRoutage.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

        actionNull = actions.get(ActionPage.FILTRER.name());

        actions.put(ActionPage.CREER.name(),
                new Action.Forward(LieuUtils.PAGE_JSP_DETAIL) {
            @Override
            public void execute(
                    final HttpServletRequest request,
                    final HttpServletResponse response)
                    throws ServletException, IOException {

                try {
                    List<Lieu> liste = new ArrayList<>();
                    final String filtreFinal = ".*";
                    liste = (List<Lieu>) transactionManager.executeTransaction(new TransactionManager.Operation<MapperManager>() {
                        @Override
                        public Object execute(final MapperManager mm)
                                throws PersistenceException {
                            return mm.getLieuMapper()
                                    .retrieve(".*");
                        }
                    });

                    request.setAttribute("liste", liste);
                    request.getSession().setAttribute(
                            LieuUtils.JSP_ATTRIBUT_ETAT_PAGE,
                            EtatPage.CREATION);
                    super.execute(request, response);
                } catch (SQLException | PersistenceException ex) {
                    Logger.getLogger(LieuxRoutage.class.getName()).log(Level.SEVERE, null, ex);
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
                    final Lieu entite = LieuUtils.lireFormulaire(request);

                    Lieu nouvelleEntite
                            = (Lieu) transactionManager.executeTransaction(
                                    new TransactionManager.Operation<MapperManager>() {
                                @Override
                                public Object execute(final MapperManager mm)
                                        throws PersistenceException {
                                    return mm.getLieuMapper().create(entite);
                                }
                            });

                    response.sendRedirect(String.format(
                            LieuUtils.TEMPLATE_URL_DETAIL,
                            request.getContextPath(),
                            nouvelleEntite.getIdentifiant().getUUID()));
                } catch (SQLException | PersistenceException ex) {
                    Logger.getLogger(LieuxRoutage.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

    }
}
