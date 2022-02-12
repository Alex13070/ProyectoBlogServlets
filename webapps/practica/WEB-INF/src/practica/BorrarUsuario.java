package practica;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @author @Alex13070
 * 
 * Servlet a borrar usuarios de la base de datos.
 */
public class BorrarUsuario extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        HttpSession session = req.getSession(false);

        if (session != null) {

            DB db = new DB ();
            String nombreUsuario =  req.getParameter("nombreUsuario");
            
            if (nombreUsuario != null && !nombreUsuario.equals("admin")) {
                db.borrarUsuario(nombreUsuario);
            }

            resp.sendRedirect(req.getContextPath() + "/panel");
        }
        else
            resp.sendRedirect(req.getContextPath() + "/blog");

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.sendRedirect(req.getContextPath() + "/blog");
    }
}
