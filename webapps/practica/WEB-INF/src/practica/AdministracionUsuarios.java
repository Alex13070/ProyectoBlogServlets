package practica;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @author @Alex13070
 * 
 * Servlet dedicado a la administracion de usuarios 
 */
public class AdministracionUsuarios extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        PrintWriter out = resp.getWriter();

        if(session != null) {
            
            String nombreUsuario = (String) session.getAttribute("nombreUsuario");
            
            if ( nombreUsuario != null && nombreUsuario.equals("admin") ) {

                List<Usuario> usuarios;
                DB db = new DB ();
                
                usuarios = db.getUsuarios();

                usuarios.remove(Usuario.builder().usuario("admin").build());
                
                out.println(PlantillasHTML.paginaManejoUsuario(usuarios));
            }
        }
    }
}
