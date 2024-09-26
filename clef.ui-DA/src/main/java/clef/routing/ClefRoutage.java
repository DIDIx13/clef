package clef.routing;

import clef.datasource.MapperManager;
import clef.datasource.Utils;
import clef.domain.Clef;
import core.datasource.PersistenceException;
import core.datasource.TransactionManager;
import core.datasource.TransactionManager.Operation;
import core.domain.Identifiant;
import core.domain.IdentifiantBase;
import core.web.RequestUtils;
import core.web.routing.Action;
import core.web.routing.Routage;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author dominique huguenin (dominique.huguenin at rpn.ch)
 */
@WebServlet(name = "ClefRoutage",
        urlPatterns = {"/clefs/*"})
public class ClefRoutage extends Routage {

    private final Pattern idPatternRegex;
    private TransactionManager transactionManager;

    public ClefRoutage() {
        idPatternRegex = Pattern.compile(ClefUtils.ID_PATTERN_REGEX);
    }

    @Override
    public void init() throws ServletException {
        this.transactionManager
                = Utils.getTransactionManager(this.getServletContext());

        actions.put(ActionPage.VISUALISER.name(),
                new Action.Forward("/WEB-INF/clefs/detail.jsp") {
            @Override
            public void execute(HttpServletRequest request,
                    HttpServletResponse response)
                    throws ServletException, IOException {

                try {
                    String uuid = RequestUtils.extractId(request,
                            idPatternRegex);
                    final Identifiant id = IdentifiantBase.builder()
                            .uuid(uuid)
                            .build();

                    Clef detail = (Clef) transactionManager.executeTransaction(
                            new Operation<MapperManager>() {
                        @Override
                        public Object execute(final MapperManager mm)
                                throws PersistenceException {
                            return mm.getClefMapper()
                                    .retrieve(id);
                        }
                    });

                    if (detail != null) {
                        request.getSession().setAttribute(
                                ClefUtils.JSP_ATTRIBUT_ETAT_PAGE,
                                EtatPage.VISUALISATION);
                        request.getSession().setAttribute("detail", detail);
                        super.execute(request, response);
                    } else {
                        response.sendError(HttpServletResponse.SC_NOT_FOUND);
                    }
                } catch (SQLException | PersistenceException ex) {
                    Logger.getLogger(ClefRoutage.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

        actions.put(ActionPage.MODIFIER.name(),
                new Action.Forward(
                        ClefUtils.PAGE_JSP_DETAIL
                ) {
            @Override
            public void execute(
                    final HttpServletRequest request,
                    final HttpServletResponse response)
                    throws ServletException, IOException {

                try {
                    String uuid = RequestUtils.extractId(request,
                            idPatternRegex);
                    final Identifiant id = IdentifiantBase.builder()
                            .uuid(uuid)
                            .build();

                    Clef detail = (Clef) transactionManager.executeTransaction(
                            new Operation<MapperManager>() {
                        @Override
                        public Object execute(final MapperManager mm)
                                throws PersistenceException {
                            return mm.getClefMapper()
                                    .retrieve(id);
                        }
                    });

                    if (detail != null) {
                        request.getSession().setAttribute(
                                ClefUtils.JSP_ATTRIBUT_ETAT_PAGE,
                                EtatPage.MODIFICATION);
                        List<Clef> liste = new ArrayList<>();
                        final String filtreFinal = ".*";
                        liste = (List<Clef>) transactionManager.executeTransaction(new TransactionManager.Operation<MapperManager>() {
                            @Override
                            public Object execute(final MapperManager mm)
                                    throws PersistenceException {
                                return mm.getClefMapper()
                                        .retrieve(filtreFinal);
                            }
                        });
                        //request.setAttribute("liste", liste); #TODO Envoyer la liste des status pour la modification du status d'une clef
                        request.getSession().setAttribute("detail", detail);
                        super.execute(request, response);
                    } else {
                        response.sendError(
                                HttpServletResponse.SC_NOT_FOUND);
                    }

                } catch (SQLException | PersistenceException ex) {
                    Logger.getLogger(ClefRoutage.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        });

        actions.put(ActionPage.VALIDER_MODIFICATION.name(),
                new Action() {
            @Override
            public void execute(
                    final HttpServletRequest request,
                    final HttpServletResponse response)
                    throws ServletException, IOException {

                try {
                    final Clef entite = ClefUtils.lireFormulaire(request);
                    transactionManager.executeTransaction(
                            new TransactionManager.Operation<MapperManager>() {
                        @Override
                        public Object execute(final MapperManager mm)
                                throws PersistenceException {
                            mm.getClefMapper().update(entite);
                            return null;
                        }
                    });

                    response.sendRedirect(String.format(
                            ClefUtils.TEMPLATE_URL_DETAIL,
                            request.getContextPath(),
                            entite.getIdentifiant().getUUID()));

                } catch (SQLException | PersistenceException ex) {
                    Logger.getLogger(ClefRoutage.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        });

        actions.put(ActionPage.QUITTER.name(),
                new Action.Redirect(this.getServletContext().getContextPath()
                        + "/clefs.html"));

        actionNull = actions.get(ActionPage.VISUALISER.name());

    }
}
