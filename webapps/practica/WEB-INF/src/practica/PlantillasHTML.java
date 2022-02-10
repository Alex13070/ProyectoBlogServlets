package practica;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

import org.stringtemplate.v4.ST;

/**
 * @author @Alex13070
 * 
 * Clase en la que estan almacenadas las plantillas de las paginas web
 */
public class PlantillasHTML {

    /**
     * Formato de la fecha
     */
    private static final DateTimeFormatter FORMATO = DateTimeFormatter.ofPattern("dd-MM-yyyy");

    /**
     * Plantilla para el formulario de inicio de sesion
     * @return Plantilla lista para ser introducida
     */
    public static ST formInicioSesion(){

        ST template = new ST("""
        
        <div class='w3-card-4 w3-margin-top w3-margin-bottom w3-white w3-animate-top'>

            <header class='w3-container w3-pink'>
                <h1>Inicio de sesi&oacute;n</h1>
            </header>
            <div class='w3-container w3-margin-top'>
                <form class='w3-container' style='width: 60%; margin: 0 auto' method='post' action='iniciosesion'>

                    <label class='w3-margin-top w3-margin-bottom'>Nombre de usuario</label>
                    <input class='w3-input w3-margin-top w3-margin-bottom' type='text' placeholder='Nombre de usuario' name='nombreUsuario'>                            
                    <label class='w3-margin-top w3-margin-bottom'>Contrase&ntilde;a</label>
                    <input class='w3-input w3-margin-top w3-margin-bottom' type='password' placeholder='Contrase&ntilde;a' name='password'>
                    <button class='w3-btn w3-round-large w3-pink w3-margin-top w3-margin-bottom' style='margin-left: 30%; width: 40%'>Iniciar sesion</button>
                            
                </form>
            </div>
        </div>
        
        """, '{','}');

        return template;
    }

    /**
     * Plantilla para el formulario de cambio de password
     * @return Plantilla lista para ser introducida
     */
    public static ST formCambiarPassword() {

        ST template = new ST("""
        <div class='w3-card-4 w3-margin-top w3-margin-bottom w3-white w3-animate-top'>

            <header class='w3-container w3-pink'>
                <h1>Cambiar de contrase&ntilde;a</h1>
            </header>

            <div class='w3-container w3-margin-top'>
                <form class='w3-container' style='width: 60%; margin: 0 auto' method='post' action='cambiarpassword'>
                            
                    <label class='w3-margin-top w3-margin-bottom'>Contrase&ntilde;a</label>
                    <input class='w3-input w3-margin-top w3-margin-bottom' type='password' placeholder='Contrase&ntilde;a' name='password'>
                    <label class='w3-margin-top w3-margin-bottom'>Repetir contrase&ntilde;a</label>
                    <input class='w3-input w3-margin-top w3-margin-bottom' type='password' placeholder='Repetir contrase&ntilde;a' name='password2'>
                    <button class='w3-btn w3-round-large w3-pink w3-margin-top w3-margin-bottom' style='margin-left: 30%; width: 40%'>Cambiar contrase&ntilde;a</button>
                            
                            
                </form>
                
            </div>
        </div>
        """, '{','}');

        return template;
        
    }

    /**
     * Plantilla base de la pagina web
     * @return Plantilla lista para ser tratada
     */
    public static ST plantillaBasePaginaWeb() {

        ST template = new ST("<html><body>{cuerpo}</body></html>", '{','}');
        return template;
        
    }

    /**
     * Plantilla para crear entradas
     * @param e Entrada a introducir en la plantilla
     * @return Plantilla lista para ser introducida
     */
    public static String entradaCompleta(Entrada e) {
        ST template = new ST (
        """
        <div class='w3-card-4 w3-margin-top w3-margin-bottom w3-white w3-animate-top'>
            <header class='w3-container w3-pink'>
                <h1>{titulo}</h1>
            </header>
            <div class='w3-container w3-margin-top'>
                <p>
                    {texto}
                </p>
            </div>
            <footer class='w3-container w3-white'>
                <h5 class='w3-right'>{fecha}</h5>
            </footer>
        </div>
        """, '{','}');

        template.add("titulo", e.getTitulo());
        template.add("texto", e.getTexto());
        template.add("fecha", convertirFechas(e.getFecha()));

        return template.render().toString();
        
    }

    /**
     * Plantilla de la pagina principal tratada.
     * @param entradas Lista de entradas a tratar
     * @param nombreUsuario Nombre de usuario del usuario loggeado
     * @return Plantilla lista para ser introducida
     */
    public static ST paginaPrincipal(List<Entrada> entradas, String nombreUsuario) {

        ST template = plantillaBasePaginaWeb();
        template.add("cuerpo", recogerEntradas(entradas));
        template.add("nombreUsuario", nombreUsuario);

        return template;
        
    }

    /**
     * Conversor de Date a LocalDate formateado
     * @param fecha fecha por defecto
     * @return Fecha formateada
     */
    private static String convertirFechas(Date fecha) {
        return LocalDate.ofInstant(fecha.toInstant(), ZoneId.systemDefault()).format(FORMATO);
    }

    /**
     * Conversor de coleccion de entradas a un string con todas las entradas
     * @param entradas Coleccion de entradas sacadas de la base de datos
     * @return Codigo a introducir en la pagina web
     */
    private static String recogerEntradas(List<Entrada> entradas) {
        StringBuffer buffer = new StringBuffer();

        entradas.sort((o1,o2) -> o1.getFecha().compareTo(o2.getFecha()));

        for (Entrada entrada : entradas) {
            buffer.append(entradaCompleta(entrada));
        }

        return buffer.toString();
    }

    /**
     * Devuelve la lista de entradas que se encesitan para el panel de control
     * @param entradas Lista de entradas sacadas de la base de datos
     * @return String en el que esta la pagina web que escribiremos
     */
    public static String listaEntradas(List<Entrada> entradas) {
        StringBuffer buffer = new StringBuffer();

        buffer.append("""
        <div class='w3-card-4 w3-margin-top w3-margin-bottom w3-white w3-animate-top'>

            <header class='w3-container w3-pink'>
                <h1>Entradas</h1>
            </header>
            <div class='w3-container w3-margin-top'>
                <ul class='w3-ul w3-hoverable w3-margin-bottom'>
        """);

        for (Entrada e : entradas){
            buffer.append(updateDelete(e));
        }

        buffer.append("</ul></div></div>");

        return buffer.toString();
    }

    /**
     * Escribe cada entrada pasandola a html.
     * @param entrada Entrada a convertir a html
     * @return Entrada convertida a String html
     */
    private static String updateDelete(Entrada entrada) {
        ST template = new ST(
        """
        <li>
            <a href='http://127.0.0.1:8080/practica/actualizar?id={id}'>Editar</a>
            &nbsp;
            <a href='http://127.0.0.1:8080/practica/borrar?id={id}'>Borrar</a>
            &nbsp; &nbsp;&nbsp;
            {titulo}
        </li>
        """, '{', '}'
        );

        template.add("id", entrada.getId());
        template.add("titulo", entrada.getTitulo());


        return template.render().toString();
    }

}


