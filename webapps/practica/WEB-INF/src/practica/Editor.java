package practica;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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

    private final DateFormat FORMATO = new SimpleDateFormat("yyyy-MM-dd");

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
        PrintWriter out = resp.getWriter();
        HttpSession session = req.getSession(false);
        

        if(session != null){

            String nombreUsuario = (String) session.getAttribute("nombreUsuario");

            String id = req.getParameter("id");
            DB db = new DB ();
            
            if (id != null) {
                Optional<Entrada> entrada = Optional.empty();
                entrada = db.buscarEntrada(Integer.parseInt(id));

                out.println(PlantillasHTML.paginaActualizarEntrada(entrada.get(), "Blog - Editor", nombreUsuario));
            }
            else {
                String titulo = req.getParameter("titulo");
                String texto = req.getParameter("texto");
                String fecha = req.getParameter("fecha");
                String idef = req.getParameter("identificador");

                Date date = new Date();

                try {
                    date = FORMATO.parse(fecha);
                } catch (ParseException e1) {
                    e1.printStackTrace();
                }

                Entrada e = Entrada.builder().id(Integer.parseInt(idef)).titulo(titulo).texto(texto).fecha(date).build();

                db.actualizarEntrada(e);

                resp.sendRedirect(req.getContextPath() + "/panel");

            }
        }
        else {
            resp.sendRedirect(req.getContextPath() + "/iniciosesion");
        }
    }
}
