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
public class Editar extends HttpServlet {
/*
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }
*/
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //String nombreUsuarioPagina = "Sin registrar";
        //PrintWriter out = resp.getWriter();
        HttpSession session = req.getSession(false);

        if(session != null){
            String id = (req.getParameter("id"));
            
            if (id != null) {
                
                DB db = new DB ();

                Optional<Entrada> e = db.buscarEntrada(Integer.parseInt(id));
                
                if (e.isPresent()) {
                    Entrada entrada = e.get();
                    
                    entrada.setTitulo(extraerCaracteres(entrada.getTitulo()));
                    entrada.setTexto(extraerCaracteres(entrada.getTexto()));
                }
            }
            else {
                resp.sendRedirect(req.getContextPath() + "/panelcontrol");
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
