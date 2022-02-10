package practica;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.stringtemplate.v4.ST;

/**
 * @author @Alex13070
 * 
 * Servlet dedicado al manejo del panel de control del blog
 */
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

            ST template = PlantillasHTML.plantillaBasePaginaWeb();

            StringBuffer buffer = new StringBuffer();


            buffer.append(PlantillasHTML.formCambiarPassword());
            buffer.append(PlantillasHTML.listaEntradas(db.getEntradas()));

            template.add("cuerpo", buffer.toString());
            template.add("nombreUsuario", nombreUsuarioPagina);

            String psw = req.getParameter("nombreUsuario");
            String psw2 = req.getParameter("password");

            if (!psw.equals("") || !psw2.equals("")){

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

            out.println(buffer.toString());

        }
        else
            resp.sendRedirect(req.getContextPath() + "/iniciosesion");


        
    }
}
