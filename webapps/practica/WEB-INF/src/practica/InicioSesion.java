package practica;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.stringtemplate.v4.ST;

public class InicioSesion extends HttpServlet{
    
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

        if(session != null){
            session.getAttribute("nombreUsuario");
        }
    
        if (session == null) {
            String nombreUsuario = req.getParameter("nombreUsuario");
            String password = req.getParameter("password");

            if (nombreUsuario != null && nombreUsuario != null){
                
                Usuario usuario = new Usuario(nombreUsuario, password);
                DB db = new DB ();
                if (db.comprobarUsuario(usuario)){

                    //Inicio de sesion correcto
                    session = req.getSession(true);
                    session.setAttribute("nombreUsuario", nombreUsuario);
                    session.setMaxInactiveInterval(120);
                    resp.sendRedirect(req.getContextPath() + "/panel");
                }
                else {
                    //Inicio de sesion correcto
                }                
            }
            else {
                //Acceso sin inicio de sesion
            }
        }
        else {
            //Sesion ya iniciada
            resp.sendRedirect(req.getContextPath() + "/panel");
        }

        ST template = PlantillasHTML.plantillaBasePaginaWeb();

        template.add("usuario", nombreUsuarioPagina);
        template.add("cuerpo", PlantillasHTML.formInicioSesion().render().toString());
        


        out.println(template.render().toString());
    }
}
