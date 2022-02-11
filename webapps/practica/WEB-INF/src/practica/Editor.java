package practica;
import java.io.IOException;
import java.util.Optional;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


/**
 * @author @Alex13070
 * 
 * Servlet dedicado a editar entradas en el servidor
 */
public class Editor extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        HttpSession session = req.getSession (false);

        if (session == null) {
            resp.sendRedirect(req.getContextPath() + "/iniciosesion");
        }
        else {
            resp.sendRedirect(req.getContextPath() + "/panel");
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //String nombreUsuarioPagina = "Sin registrar";
        //PrintWriter out = resp.getWriter();
        HttpSession session = req.getSession(false);
        Optional<Entrada> entrada = Optional.empty();

        if(session != null){
            String id = (req.getParameter("id"));
            DB db = new DB ();
            
            if (id != null) {
                
                if (entrada.isPresent()) {
                    Entrada e = entrada.get();
                    
                    e.setTitulo(extraerCaracteres(e.getTitulo()));
                    e.setTexto(extraerCaracteres(e.getTexto()));

                    db.actualizarEntrada(e);
                }
            }
            else {
                
            }

            //nombreUsuarioPagina = (String) session.getAttribute("nombreUsuario");
        }
        else {
            //Sesion ya iniciada
            resp.sendRedirect(req.getContextPath() + "/iniciosesion");
        }
    }

    /**
     * Quita los caracteres especiales de los string
     * @param str string al que hay que quitarle los parametros especiales
     * @return String sin los parametros especiales
     */
    private String extraerCaracteres(String str) {
        
        str.replaceAll("&", "&amp;");
        str.replaceAll("<", "&gt;");
        str.replaceAll(">", "&lt;");
        str.replaceAll("'", "&#039;");
        str.replaceAll("\"", "&#034;");

        return str;
    }
}
