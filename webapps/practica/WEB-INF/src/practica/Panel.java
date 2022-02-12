package practica;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Optional;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


/**
 * @author @Alex13070
 * 
 * Servlet dedicado al manejo del panel de control del blog
 */
public class Panel extends HttpServlet {
    

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String nombreUsuarioPagina = "Sin registrar";
        PrintWriter out = resp.getWriter();
        //String mostrar = "";
        HttpSession session = req.getSession(false);
        Optional<String> info = Optional.empty();

        if (session != null) {
            DB db = new DB ();

            nombreUsuarioPagina = (String) session.getAttribute("nombreUsuario");

            out.println(PlantillasHTML.paginaPanelControl("Blog - Panel de control", nombreUsuarioPagina, db.getEntradas(), info));
        }
        else
            resp.sendRedirect(req.getContextPath() + "/iniciosesion");
    }

    
}
