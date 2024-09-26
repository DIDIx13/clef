package clef.routing;

import core.domain.Identifiant;
import core.domain.IdentifiantBase;
import core.web.RequestUtils;
import javax.servlet.http.HttpServletRequest;
import clef.domain.Lieu;
import clef.domain.LieuBase;

/**
 *
 * @author dominique huguenin (dominique.huguenin at rpn.ch)
 */
public class LieuUtils {

    public static final String ELEMENT
            = "lieux";
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
    public static final String JSP_ATTRIBUT_NOM = "nom";
    public static final String JSP_LIEU_PARENT_UUID = "lieuParentUUID";

    public static Lieu lireFormulaire(final HttpServletRequest request) {
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

        String nom = request.getParameter(JSP_ATTRIBUT_NOM);
        String lieuParentUUID = request.getParameter(JSP_LIEU_PARENT_UUID);
        if ("".equals(lieuParentUUID)) {
            lieuParentUUID = null;
        }

        Identifiant identifiantLieuParent = null;
        Lieu lieuParent = null;
        if (lieuParentUUID != null) {
            identifiantLieuParent = IdentifiantBase.builder()
                    .uuid(lieuParentUUID)
                    .version(1L)
                    .build();

            lieuParent = LieuBase.builder()
                    .identifiant(identifiantLieuParent)
                    .build();
        }

        return LieuBase.builder()
                .identifiant(identifiant)
                .nom(nom)
                .lieuParent(lieuParent)
                .build();
    }
}
