package practica;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @author @Alex13070
 * 
 * Servlet dedicado a la pagina principal del blog
 */
public class Blog extends HttpServlet {

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

        List<Entrada> entradas = db.getEntradas();

        entradas.removeIf(e -> e.getFecha().getTime() > new Date ().getTime());

        out.println(PlantillasHTML.paginaPrincipal(entradas, nombreUsuarioPagina, "Blog - Pagina principal"));
    }
    
}
