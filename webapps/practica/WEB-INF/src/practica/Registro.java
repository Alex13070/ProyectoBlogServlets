package practica;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Optional;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Registro extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        
        DB db = new DB ();
        PrintWriter out = resp.getWriter ();
        Optional<String> mensaje = Optional.empty ();
        
        String nombreUsuario = req.getParameter("nombreUsuario");
        String password = req.getParameter("password");
        String password2 = req.getParameter("password2");

        if (nombreUsuario != null && password != null && password2 != null) {
            if ( password.equals(password2)) {

                if (!db.existeUsuario(nombreUsuario)){

                    if (stringValido (nombreUsuario) && stringValido (password)) {
                        Usuario usuario = Usuario.builder().usuario(nombreUsuario).password(password).build();
                        db.crearUsuario(usuario);
                        resp.sendRedirect(req.getContextPath() + "/iniciosesion");
                    }
                    else
                        mensaje = Optional.of("Usuario o contrase&ntilde;a contienen caracteres inadecuados");
                }
                else
                    mensaje = Optional.of("El nombre de usuario introducido ya existe");
            }
            else 
                mensaje = Optional.of("Las contrase&ntilde;as no coinciden");
        }

        out.println(PlantillasHTML.paginaRegistro("Blog - Registro",  "Sin identificar", mensaje));
    }

    private boolean stringValido(String s) {
        return !(s.contains("'") || s.contains("\"") || s.contains("<") || s.contains(">") || s.contains("&"));
    }
    
}
