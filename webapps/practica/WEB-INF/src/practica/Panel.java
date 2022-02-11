package practica;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
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
        List<String> usuarios = null;

        if (session != null) {
            DB db = new DB ();
            nombreUsuarioPagina = (String) session.getAttribute("nombreUsuario");

            if (nombreUsuarioPagina.equals("admin"))
                usuarios = db.getUsuarios();

            String psw = req.getParameter("password");
            String psw2 = req.getParameter("password2");

            if (psw != null && psw2 != null && !psw.equals("") && !psw2.equals("")){

                if (psw.equals(psw2)){
                    //Correcto
                    //Actualizar usuario
                    //Mensaje de correcto
                    if (stringValido(psw)) {
                        Usuario u = Usuario.builder()
                            .usuario( (String) session.getAttribute("nombreUsuario") )
                            .password(psw)
                            .build();

                        if (db.cambiarPassword(u))
                            info = Optional.of("Cambio de contrase&ntilde;a correcto");
                        else
                            info = Optional.of("Error en el servidor");
                    }
                    
                }
                else {
                    //Erroneo
                    //Mensaje de error
                    info = Optional.of("Error: Las contrase&ntilde;as no coinciden");
                }

            }

            out.println(PlantillasHTML.paginaPanelControl("Blog - Panel de control", nombreUsuarioPagina, db.getEntradas(), usuarios, info));

        }
        else
            resp.sendRedirect(req.getContextPath() + "/iniciosesion");


        
    }

    private boolean stringValido(String s) {
        return !(s.contains("'") || s.contains("\"") || s.contains("<") || s.contains(">") || s.contains("&"));
    }
}
