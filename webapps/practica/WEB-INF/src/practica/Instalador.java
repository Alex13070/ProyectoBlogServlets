package practica;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author @Alex13070
 * Clase dedicada a instalar la base de datos SQLite.
 */
public class Instalador extends HttpServlet {
    
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        
        PrintWriter out = resp.getWriter();

        String pulsado = req.getParameter("pulsado");

        if (pulsado != null){
            DB db = new DB ();
            db.crearTablas();
            resp.setCharacterEncoding("UTF-8");
            resp.sendRedirect(req.getContextPath() + "/blog");
        }
        else {
            out.println(PlantillasHTML.paginaInstalador());
        }

        
        
        

    }
}
