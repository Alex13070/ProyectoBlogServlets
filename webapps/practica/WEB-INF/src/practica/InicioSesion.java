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
 * Servlet dedicado a iniciar sesion
 */
public class InicioSesion extends HttpServlet{
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String nombreUsuarioPagina = "Sin registrar";
        PrintWriter out = resp.getWriter();
        Optional<String> respuesta = Optional.empty();
        HttpSession session = req.getSession(false);

        if(session != null){
            nombreUsuarioPagina = (String) session.getAttribute("nombreUsuario");
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
                    respuesta = Optional.of("Inicio de sesion fallido");
                }                
            }
        }
        else {
            //Sesion ya iniciada
            resp.sendRedirect(req.getContextPath() + "/panel");
        }
    

        out.println(PlantillasHTML.paginaInicioSesion("Blog - Inicio de sesi&oacute;n", nombreUsuarioPagina, respuesta));
    }
}
