package clef.routing;

import clef.datasource.MapperManager;
import clef.datasource.Utils;
import clef.domain.Personne;
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
@WebServlet(name = "PersonneRoutage",
        urlPatterns = {"/personnes/*"})
public class PersonneRoutage extends Routage {

    private final Pattern idPatternRegex;
    private TransactionManager transactionManager;

    public PersonneRoutage() {
        idPatternRegex = Pattern.compile(PersonneUtils.ID_PATTERN_REGEX);
    }

    @Override
    public void init() throws ServletException {
        this.transactionManager
                = Utils.getTransactionManager(this.getServletContext());

        actions.put(ActionPage.VISUALISER.name(),
                new Action.Forward("/WEB-INF/personnes/detail.jsp") {
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

                    Personne detail = getDetail(id);

                    if (detail != null) {
                        request.getSession().setAttribute(
                                PersonneUtils.JSP_ATTRIBUT_ETAT_PAGE,
                                EtatPage.VISUALISATION);

                        request.setAttribute("detail", detail);
                        super.execute(request, response);
                    } else {
                        response.sendError(HttpServletResponse.SC_NOT_FOUND);
                    }
                } catch (SQLException | PersistenceException ex) {
                    Logger.getLogger(PersonneRoutage.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

        actions.put(ActionPage.MODIFIER.name(),
                new Action.Forward(
                        PersonneUtils.PAGE_JSP_DETAIL
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

                    Personne detail = getDetail(id);

                    if (detail != null) {
                        request.getSession().setAttribute(
                                PersonneUtils.JSP_ATTRIBUT_ETAT_PAGE,
                                EtatPage.MODIFICATION);
                        request.setAttribute("detail", detail);
                        super.execute(request, response);
                    } else {
                        response.sendError(
                                HttpServletResponse.SC_NOT_FOUND);
                    }

                } catch (SQLException | PersistenceException ex) {
                    Logger.getLogger(PersonneRoutage.class.getName()).log(Level.SEVERE, null, ex);
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
                    final Personne entite = PersonneUtils.lireFormulaire(request);
                    transactionManager.executeTransaction(
                            new TransactionManager.Operation<MapperManager>() {
                        @Override
                        public Object execute(final MapperManager mm)
                                throws PersistenceException {
                            mm.getPersonneMapper().update(entite);
                            return null;
                        }
                    });

                    response.sendRedirect(String.format(
                            PersonneUtils.TEMPLATE_URL_DETAIL,
                            request.getContextPath(),
                            entite.getIdentifiant().getUUID()));

                } catch (SQLException | PersistenceException ex) {
                    Logger.getLogger(PersonneRoutage.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        });

        actions.put(ActionPage.SUPPRIMER.name(),
                new Action.Forward(
                        PersonneUtils.PAGE_JSP_DETAIL
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
                    Personne detail = getDetail(id);

                    if (detail != null) {
                        request.getSession().setAttribute(
                                PersonneUtils.JSP_ATTRIBUT_ETAT_PAGE,
                                EtatPage.SUPPRESSION);
                        request.setAttribute(PersonneUtils.JSP_ATTRIBUT_DETAIL,
                                detail);

                        super.execute(request, response);

                    } else {
                        response.sendError(
                                HttpServletResponse.SC_NOT_FOUND);
                    }
                } catch (SQLException | PersistenceException ex) {
                    Logger.getLogger(PersonneRoutage.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

        actions.put(ActionPage.VALIDER_SUPPRESSION.name(),
                new Action.Redirect(
                        String.format(PersonneUtils.TEMPLATE_URL_LISTE,
                                this.getServletContext().getContextPath())
                ) {
            @Override
            public void execute(
                    final HttpServletRequest request,
                    final HttpServletResponse response)
                    throws ServletException, IOException {

                try {
                    final Personne entite = PersonneUtils.lireFormulaire(request);

                    transactionManager.executeTransaction(
                            new TransactionManager.Operation<MapperManager>() {
                        @Override
                        public Object execute(final MapperManager mm)
                                throws PersistenceException {
                            mm.getPersonneMapper().delete(entite);
                            return null;
                        }
                    });
                    super.execute(request, response);

                } catch (SQLException | PersistenceException ex) {
                    Logger.getLogger(PersonneRoutage.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

        actions.put(ActionPage.QUITTER.name(),
                new Action.Redirect(this.getServletContext().getContextPath()
                        + "/personnes.html"));

        actionNull = actions.get(ActionPage.VISUALISER.name());

    }

    private Personne getDetail(final Identifiant id) throws PersistenceException, SQLException {
        return (Personne) transactionManager.executeTransaction(
                new Operation<MapperManager>() {
            @Override
            public Object execute(final MapperManager mm)
                    throws PersistenceException {

                return mm.getPersonneMapper()
                        .retrieve(id);
            }
        });
    }
}
