package practica;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Clase dedicada a instalar la base de datos SQLite.
 */
public class Instalador extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        DB db = new DB ();
        db.crearTablas();
        
        resp.setCharacterEncoding("UTF-8");
        resp.sendRedirect(req.getContextPath() + "/blog");
        

    }
}
