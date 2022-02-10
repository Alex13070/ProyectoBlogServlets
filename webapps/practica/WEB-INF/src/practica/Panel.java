package practica;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Optional;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


/**
 * @author @Alex13070
 * 
 * Servlet dedicado al manejo del panel de control del blog
 */

 @WebServlet()
public class Panel extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String nombreUsuarioPagina = "Sin registrar";
        PrintWriter out = resp.getWriter();
        //String mostrar = "";
        HttpSession session = req.getSession(false);

        if (session != null) {
            DB db = new DB ();
            nombreUsuarioPagina = (String) session.getAttribute("nombreUsuario");

            String psw = req.getParameter("nombreUsuario");
            String psw2 = req.getParameter("password");

            if (psw != null && psw2 != null && !psw.equals("") && !psw2.equals("")){

                if (psw.equals(psw2)){
                    //Correcto
                    //Actualizar usuario
                    //Mensaje de correcto
                    Usuario u = Usuario.builder()
                        .usuario( (String) session.getAttribute("nombreUsuario") )
                        .password(psw)
                        .build();

                    db.cambiarPassword(u);
                }
                else {
                    //Erroneo
                    //Mensaje de error
                }

            }          

            out.println(PlantillasHTML.paginaPanelControl("Blog - Panel de control", nombreUsuarioPagina, db.getEntradas(), Optional.empty()));

        }
        else
            resp.sendRedirect(req.getContextPath() + "/iniciosesion");


        
    }
}
