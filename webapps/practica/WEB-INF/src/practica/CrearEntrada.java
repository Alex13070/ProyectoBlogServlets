package practica;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @author @Alex13070
 * 
 * Servlet para crear entradas en la base de datos.
 */
public class CrearEntrada extends HttpServlet {

    /**
     * Formato de fecha definido,
     */
    private final DateFormat format = new SimpleDateFormat ("yyyy-MM-dd");

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        if (session != null) 
            resp.sendRedirect(req.getContextPath() + "/panel");
        else
            resp.sendRedirect(req.getContextPath() + "/iniciosesion");
    }
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        
        HttpSession session = req.getSession(false);
        Entrada entrada;
        PrintWriter out = resp.getWriter();

        if (session != null) {
            
            String nombreUsuario = (String) session.getAttribute("nombreUsuario");

            String titulo = req.getParameter("titulo");
            String texto = req.getParameter("texto");
            String fecha = req.getParameter("fecha");
            Date date;

            if (titulo != null && texto != null && fecha != null) {

                try {
                    date = format.parse(fecha);
                } catch (ParseException e) {
                    System.out.println("Error en la fecha");
                    date = new Date ();
                }

                entrada = Entrada.builder().titulo(titulo).texto(texto).fecha(date).build();
                DB db = new DB ();
                db.crearEntrada(entrada);

                resp.sendRedirect(req.getContextPath() + "/panel");
            }    

            out.println(PlantillasHTML.paginaCrearEntrada("Blog - Crear entrada", nombreUsuario));
        }
        else 
            resp.sendRedirect(req.getContextPath() + "/iniciosesion");
        
    }

    

}
