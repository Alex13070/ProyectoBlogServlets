package practica;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.stringtemplate.v4.ST;

public class PaginaPrincipal extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String nombreUsuarioPagina = "Sin registrar";
        PrintWriter out = resp.getWriter();
        //String mostrar = "";
        HttpSession session = req.getSession(false);

        if(session != null){
            session.getAttribute("nombreUsuario");
        }
        DB db = new DB ();
        ST template = PlantillasHTML.paginaPrincipal(db.getEntradas(), nombreUsuarioPagina);

        out.println(template.render().toString());
    }
    
}
