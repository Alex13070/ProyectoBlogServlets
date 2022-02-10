package practica;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Registro extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        
        DB db = new DB ();
        
        String nombreUsuario = req.getParameter("nombreUsuario");
        String password = req.getParameter("password");

        Usuario usuario = Usuario.builder().usuario(nombreUsuario).password(password).build();

        if (usuario.getUsuario() != null && usuario.getPassword() != null) {
            if (!db.existeUsuario(nombreUsuario)) {
                db.crearUsuario(usuario);
                //Mensaje de registro correcto
            }
            else {
                //Registro incorrecto, no se pudo introducir el usuario
            }      
        }
        else {
            //no hacer nada
        }
    }
    
}
