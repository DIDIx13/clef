package core.web;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author dominique huguenin (dominique.huguenin@rpn.ch)
 */
public final class RequestUtils {

    private RequestUtils() {
    }

    public static final String HEADER_ACCEPT = "Accept";
    public static final String HEADER_LOCATION = "Location";

    public static final String MEDIA_TYPE_APPLICATION_JSON = "application/json";
    public static final String MEDIA_TYPE_APPLICATION_XML = "application/xml";
    public static final String MEDIA_TYPE_TEXT_PLAIN = "text/plain";
    public static final String ENCODAGE_UTF8 = "UTF-8";

    public static final String VERSION_PARAM = "version";

    public static final String ACTION_PARAMETRE = "action";
    public static final String ACTION_PATTERN_REGEX
            = "([^\\]]*)(?:\\[([^=]*)=([^\\]]*)\\])?";
    public static final int ACTION_PATTERN_GROUPE_KEY = 2;
    public static final int ACTION_PATTERN_GROUPE_VALUE = 3;

    private static final Logger LOG
            = Logger.getLogger(RequestUtils.class.getName());

    public static Long extractVersion(final HttpServletRequest req) {
        return RequestUtils.extractLongParametre(req, VERSION_PARAM);
    }

    public static String extractAcceptHeader(final HttpServletRequest req) {
        return extractParametre(req, HEADER_ACCEPT);
    }

    private static String extractParametre(
            final HttpServletRequest req,
            final String param) {
        String value = req.getParameter(param);
        if (value == null) {
            value = "";
        }
        return value;
    }

    public static String extractId(
            final HttpServletRequest req,
            final Pattern idRegex) {
        String id = null;

        Matcher matcher = idRegex.matcher(req.getRequestURL());
        if (matcher.matches()) {
            id = matcher.group(1);

        }
        return id;
    }

    public static String extractActionParameter(
            final HttpServletRequest request) {
        String actionStr = request.getParameter(ACTION_PARAMETRE);
        Pattern actionPattern = Pattern.compile(ACTION_PATTERN_REGEX);
        if (actionStr != null) {
            Matcher matcher = actionPattern.matcher(actionStr);
            if (matcher.matches()) {
                actionStr = matcher.group(1);
            }
        }
        return actionStr;
    }

    /**
     *
     * @param request une requète
     * @return les attributs de l'action
     */
    public static Map<String, String> extractActionAttribut(
            final HttpServletRequest request) {
        Map<String, String> map = new HashMap<>();
        Pattern actionPattern = Pattern.compile(ACTION_PATTERN_REGEX);
        String actionStr = request.getParameter(ACTION_PARAMETRE);
        String key;
        String value;
        if (actionStr != null) {
            Matcher matcher = actionPattern.matcher(actionStr);
            if (matcher.matches()) {
                key = matcher.group(ACTION_PATTERN_GROUPE_KEY);
                value = matcher.group(ACTION_PATTERN_GROUPE_VALUE);
                map.put(key, value);
            }
        }
        return map;
    }

    public static Long extractLongParametre(
            final HttpServletRequest request,
            final String param) {
        String valeurStr = request.getParameter(param);
        Long valeur = null;
        try {
            if (!(valeurStr == null || "".equals(valeurStr))) {
                valeur = Long.valueOf(valeurStr);
            }
        } catch (NumberFormatException ex) {
            LOG.log(Level.WARNING,
                    String.format("%s, %s", ex.getMessage(), valeurStr));
        }
        return valeur;
    }

    public static Integer extractIntegerParametre(
            final HttpServletRequest request,
            final String param) {
        String valeurStr = request.getParameter(param);
        Integer valeur = null;
        try {
            if (!(valeurStr == null || "".equals(valeurStr))) {
                valeur = Integer.valueOf(valeurStr);
            }

        } catch (NumberFormatException ex) {
            LOG.log(Level.WARNING,
                    String.format("%s, %s", ex.getMessage(), valeurStr));
        }
        return valeur;
    }

    public static Double extractDoubleParametre(
            final HttpServletRequest request,
            final String param) {
        Double valeur = null;
        String valeurStr = request.getParameter(param);
        try {
            if (!(valeurStr == null || "".equals(valeurStr))) {
                valeur = Double.valueOf(valeurStr);
            }
        } catch (NumberFormatException ex) {
            LOG.log(Level.WARNING,
                    String.format("%s, %s", ex.getMessage(), valeurStr));
        }

        return valeur;
    }

}
