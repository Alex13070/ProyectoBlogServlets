package practica;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class CrearEntrada extends HttpServlet {

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

            if (titulo != null && texto != null && fecha != null) {

                try {
                    entrada = Entrada.builder().titulo(titulo).texto(texto).fecha(format.parse(fecha)).build();
                    DB db = new DB ();
                    db.crearEntrada(entrada);

                } catch (ParseException e) {
                    System.out.println("Error en la fecha");
                }

                resp.sendRedirect(req.getContextPath() + "/panel");
            }    

            out.println(PlantillasHTML.paginaCrearEntrada("Blog - Crear entrada", nombreUsuario));
        }
        else 
            resp.sendRedirect(req.getContextPath() + "/iniciosesion");
        
    }

    

}
