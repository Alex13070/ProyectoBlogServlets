package practica;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


/**
 * @author @Alex13070
 * 
 * Servlet para actualizar las entradas.
 */
public class GurardarCambios extends HttpServlet {

    /**
     * Formato para hacer el parseo de las fechas
     */
    private final SimpleDateFormat FORMATTER = new SimpleDateFormat("yyyy-MM-dd");

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        
        HttpSession session = req.getSession(false);

        if (session != null) {
            DB db = new DB ();
            Integer id = Integer.parseInt(req.getParameter("id"));
            String titulo = req.getParameter("titulo");
            String texto = req.getParameter("texto");
            String f = req.getParameter("fecha");
            Date fecha = new Date();
            try {
                fecha = FORMATTER.parse(f);
            } catch (ParseException e) {
                System.err.println(e);
            }

            Entrada entrada = Entrada.builder().id(id).titulo(titulo).texto(texto).fecha(fecha).build();

            db.actualizarEntrada(entrada);

            resp.sendRedirect(req.getContextPath() + "/panel");
        }
        else
            resp.sendRedirect(req.getContextPath() + "/iniciosesion");
    }
    
}
