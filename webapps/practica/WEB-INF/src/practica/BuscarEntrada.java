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
 * Servlet dedicado a la busqueda de entradas por nombre
 */
public class BuscarEntrada extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String nombreUsuarioPagina = "Sin registrar";
        PrintWriter out = resp.getWriter();
        //String mostrar = "";
        HttpSession session = req.getSession(false);

        if(session != null){
            nombreUsuarioPagina = (String) session.getAttribute("nombreUsuario");
        }


        DB db = new DB ();

        String nombre = req.getParameter("nombre");

        if (nombre != null ) {
            List<Entrada> entradas = db.getEntradas(nombre);

            out.println(PlantillasHTML.paginaPrincipal(entradas, nombreUsuarioPagina, "Blog - Buscar", session != null));
        }
        else {
            resp.sendRedirect(req.getContextPath() + "/blog");
        }


        
    }
    
}
