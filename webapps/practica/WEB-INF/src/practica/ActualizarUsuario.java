package practica;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @author @Alex13070
 * 
 * Servlet dedicado a la actualizacion de usuarios.
 */
public class ActualizarUsuario extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.sendRedirect(req.getContextPath() + "/blog");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //String mostrar = "";
        HttpSession session = req.getSession(false);


        if (session != null) {
            DB db = new DB ();

            String nombreUsuario = req.getParameter("nombreUsuario");
            String psw = req.getParameter("password");
            String psw2 = req.getParameter("password2");

            if (nombreUsuario != null && psw != null && psw2 != null && !psw.equals("") && !psw2.equals("")){

                if (psw.equals(psw2)){
                    
                    if (stringValido(psw)) {
                        Usuario u = Usuario.builder()
                            .usuario( (String) nombreUsuario )
                            .password(psw)
                            .build();

                        db.cambiarPassword(u);
                        
                    }
                    
                }
            }

            resp.sendRedirect(req.getContextPath() + "/panel");

        }
        else
            resp.sendRedirect(req.getContextPath() + "/blog");

    }

    /**
     * Funcion para validar que no entran caracteres no deseados.
     * @param s Cadena de texto a analizar
     * @return {@code true} Cadena valida
     *         {@code false} Cadena no valida
     */
    private boolean stringValido(String s) {
        return !(s.contains("'") || s.contains("\"") || s.contains("<") || s.contains(">") || s.contains("&"));
    }
    
}
