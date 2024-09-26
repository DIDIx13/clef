package clef.routing;

import clef.datasource.LieuMapper;
import clef.datasource.MapperManager;
import clef.datasource.Utils;
import clef.domain.Lieu;
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
@WebServlet(name = "LieuRoutage",
        urlPatterns = {"/lieux/*"})
public class LieuRoutage extends Routage {

    private final Pattern idPatternRegex;
    private TransactionManager transactionManager;

    public LieuRoutage() {
        idPatternRegex = Pattern.compile(LieuUtils.ID_PATTERN_REGEX);
    }

    @Override
    public void init() throws ServletException {
        this.transactionManager
                = Utils.getTransactionManager(this.getServletContext());

        actions.put(ActionPage.VISUALISER.name(),
                new Action.Forward("/WEB-INF/lieux/detail.jsp") {
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

                    Lieu detail = getDetail(id);

                    if (detail != null) {
                        request.getSession().setAttribute(
                                LieuUtils.JSP_ATTRIBUT_ETAT_PAGE,
                                EtatPage.VISUALISATION);
                        request.setAttribute("detail", detail);
                        super.execute(request, response);
                    } else {
                        response.sendError(HttpServletResponse.SC_NOT_FOUND);
                    }
                } catch (SQLException | PersistenceException ex) {
                    Logger.getLogger(LieuRoutage.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

        actions.put(ActionPage.MODIFIER.name(),
                new Action.Forward(
                        LieuUtils.PAGE_JSP_DETAIL
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

                    Lieu detail = getDetail(id);

                    if (detail != null) {
                        request.getSession().setAttribute(
                                LieuUtils.JSP_ATTRIBUT_ETAT_PAGE,
                                EtatPage.MODIFICATION);

                        List<Lieu> liste = new ArrayList<>();
                        final String filtreFinal = ".*";
                        liste = (List<Lieu>) transactionManager.executeTransaction(new TransactionManager.Operation<MapperManager>() {
                            @Override
                            public Object execute(final MapperManager mm)
                                    throws PersistenceException {
                                return mm.getLieuMapper()
                                        .retrieve(filtreFinal);
                            }
                        });
                        request.setAttribute("liste", liste);
                        request.setAttribute("detail", detail);
                        super.execute(request, response);
                    } else {
                        response.sendError(
                                HttpServletResponse.SC_NOT_FOUND);
                    }

                } catch (SQLException | PersistenceException ex) {
                    Logger.getLogger(LieuRoutage.class.getName()).log(Level.SEVERE, null, ex);
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
                    final Lieu entite = LieuUtils.lireFormulaire(request);
                    transactionManager.executeTransaction(
                            new TransactionManager.Operation<MapperManager>() {
                        @Override
                        public Object execute(final MapperManager mm)
                                throws PersistenceException {
                            mm.getLieuMapper().update(entite);
                            return null;
                        }
                    });

                    response.sendRedirect(String.format(
                            LieuUtils.TEMPLATE_URL_DETAIL,
                            request.getContextPath(),
                            entite.getIdentifiant().getUUID()));

                } catch (SQLException | PersistenceException ex) {
                    Logger.getLogger(LieuRoutage.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        });

        actions.put(ActionPage.SUPPRIMER.name(),
                new Action.Forward(
                        LieuUtils.PAGE_JSP_DETAIL
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
                    Lieu detail = getDetail(id);

                    if (detail != null) {
                        request.getSession().setAttribute(
                                LieuUtils.JSP_ATTRIBUT_ETAT_PAGE,
                                EtatPage.SUPPRESSION);
                        request.setAttribute(LieuUtils.JSP_ATTRIBUT_DETAIL,
                                detail);

                        super.execute(request, response);

                    } else {
                        response.sendError(
                                HttpServletResponse.SC_NOT_FOUND);
                    }
                } catch (SQLException | PersistenceException ex) {
                    Logger.getLogger(LieuRoutage.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        });

        actions.put(ActionPage.VALIDER_SUPPRESSION.name(),
                new Action.Redirect(
                        String.format(LieuUtils.TEMPLATE_URL_LISTE,
                                this.getServletContext().getContextPath())
                ) {
            @Override
            public void execute(
                    final HttpServletRequest request,
                    final HttpServletResponse response)
                    throws ServletException, IOException {

                try {
                    final Lieu entite = LieuUtils.lireFormulaire(request);

                    transactionManager.executeTransaction(
                            new TransactionManager.Operation<MapperManager>() {
                        @Override
                        public Object execute(final MapperManager mm)
                                throws PersistenceException {
                            mm.getLieuMapper().delete(entite);
                            return null;
                        }
                    });
                    super.execute(request, response);

                } catch (SQLException | PersistenceException ex) {
                    Logger.getLogger(LieuRoutage.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

        actions.put(ActionPage.QUITTER.name(),
                new Action.Redirect(this.getServletContext().getContextPath()
                        + "/lieux.html"));

        actionNull = actions.get(ActionPage.VISUALISER.name());

    }

    private Lieu getDetail(final Identifiant id) throws PersistenceException, SQLException {
        Lieu detail = (Lieu) transactionManager.executeTransaction(
                new Operation<MapperManager>() {
            @Override
            public Object execute(final MapperManager mm)
                    throws PersistenceException {

                Lieu l = mm.getLieuMapper()
                        .retrieve(id);
                if (l.getLieuParent() != null) {
                    Lieu parent = mm.getLieuMapper().retrieve(l.getLieuParent().getIdentifiant());
                    l.setLieuParent(parent);
                }

                return l;
            }
        });
        return detail;
    }
}
