package clef.routing;

import core.domain.Identifiant;
import core.domain.IdentifiantBase;
import core.web.RequestUtils;
import javax.servlet.http.HttpServletRequest;
import clef.domain.Clef;
import clef.domain.ClefBase;
import clef.domain.ClefStatus;

/**
 *
 * @author dominique huguenin (dominique.huguenin at rpn.ch)
 */
public class ClefUtils {

    public static final String ELEMENT
            = "clefs";
    public static final String ID_PATTERN_REGEX = ".*/" + ELEMENT + "/([\\w-]*).html";

    public static final String PAGE_JSP_LISTE = "/WEB-INF/" + ELEMENT + "/liste.jsp";
    public static final String PAGE_JSP_DETAIL = "/WEB-INF/" + ELEMENT + "/detail.jsp";
    public static final String TEMPLATE_URL_LISTE = "%s/" + ELEMENT + ".html";
    public static final String TEMPLATE_URL_DETAIL = "%s/" + ELEMENT + "/%s.html";

    public static final String JSP_ATTRIBUT_ETAT_PAGE = "etatPage";
    public static final String JSP_ATTRIBUT_LISTE = "liste";
    public static final String JSP_ATTRIBUT_FILTRE = "filtre";
    public static final String JSP_ATTRIBUT_DETAIL = "detail";

    public static final String JSP_ATTRIBUT_UUID = "uuid";
    public static final String JSP_ATTRIBUT_VERSION = "version";
    public static final String JSP_ATTRIBUT_NUMEROSERIE = "numeroserie";
    public static final String JSP_ATTRIBUT_STATUS = "status";

    public static Clef lireFormulaire(final HttpServletRequest request) {
        String uuid = request.getParameter(JSP_ATTRIBUT_UUID);
        if ("".equals(uuid)) {
            uuid = null;
        }
        Long version = RequestUtils.extractLongParametre(
                request, JSP_ATTRIBUT_VERSION);

        Identifiant identifiant = null;
        if (uuid != null) {
            identifiant = IdentifiantBase.builder()
                    .uuid(uuid)
                    .version(version)
                    .build();
        }

        String numeroserie = request.getParameter(JSP_ATTRIBUT_NUMEROSERIE);
        ClefStatus status = ClefStatus.valueOf(request.getParameter(JSP_ATTRIBUT_STATUS));

        return ClefBase.builder()
                .identifiant(identifiant)
                .numeroserie(numeroserie)
                .status(status)
                .build();
    }

}
