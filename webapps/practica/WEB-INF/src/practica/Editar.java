package practica;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Optional;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.stringtemplate.v4.ST;

public class Editar extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String nombreUsuarioPagina = "Sin registrar";
        PrintWriter out = resp.getWriter();
        HttpSession session = req.getSession(false);

        if(session != null){
            Integer id = (Integer) session.getAttribute("id");
            DB db = new DB ();

            Optional<Entrada> e = db.buscarEntrada(id);
            
            if (e.isPresent()) {
                Entrada entrada = e.get();
                
                entrada.setTitulo(extraerCaracteres(entrada.getTitulo()));
                entrada.setTexto(extraerCaracteres(entrada.getTexto()));
            }
            
            

            nombreUsuarioPagina = (String) session.getAttribute("nombreUsuario");
        }
        else {
            //Sesion ya iniciada
            resp.sendRedirect(req.getContextPath() + "/iniciosesion");
        }
    
        if (session == null) {
            String nombreUsuario = req.getParameter("nombreUsuario");
            String password = req.getParameter("password");

            if (nombreUsuario != null && nombreUsuario != null){
                
                Usuario usuario = new Usuario(nombreUsuario, password);
                DB db = new DB ();
                if (db.comprobarUsuario(usuario)){
                    session = req.getSession(true);
                    session.setAttribute("nombreUsuario", nombreUsuario);
                    session.setMaxInactiveInterval(120);
                    resp.sendRedirect(req.getContextPath() + "/panel");
                    //mostrar = "correcto";
                }
                else {
                    //mostrar = "no aceptado";
                }
            }
            else {
                //mostrar = "primera vez";
            }
        }
        else {
            //mostrar = "habia sesion";
        }

        ST template = PlantillasHTML.plantillaBasePaginaWeb();

        template.add("usuario", nombreUsuarioPagina);
        template.add("cuerpo", PlantillasHTML.formInicioSesion().render().toString());

        out.println(template.render().toString());
    }

    private String extraerCaracteres(String str) {
        str.replaceAll("<", "&gt;");
        str.replaceAll(">", "&lt;");
        str.replaceAll("&", "&amp;");
        str.replaceAll("'", "&#039;");
        str.replaceAll("\"", "&#034;");

        return str;
    }
}
