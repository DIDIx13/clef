package core.web.routing;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author dominique huguenin (dominique.huguenin at rpn.ch)
 */
public abstract class Routage extends HttpServlet {

    public static final String ACTION_PARAMETRE = "action";
    protected Map<String, Action> actions;
    protected Action actionNull;

    public Routage() {
        actions = new HashMap<>();

    }

    protected void processRequest(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {
        String actionStr = extractActionParameter(request);
        Action action = actions.get(actionStr);
        if (action == null) {
            action = this.actionNull;
        }
        action.execute(request, response);
    }

    public String extractActionParameter(final HttpServletRequest request) {
        String actionStr = request.getParameter(ACTION_PARAMETRE);
        return actionStr;
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }
}
