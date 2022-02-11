package practica;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.Optional;

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
     * @param alerta Mensaje de alerta del formulario
     * @return Plantilla renderizada a introducir
     */
    public static String formInicioSesion(Optional<String> alerta){

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
                    $alerta$
                    <button class='w3-btn w3-round-large w3-pink w3-margin-top w3-margin-bottom' style='margin-left: 30%; width: 40%'>Iniciar sesion</button>
                            
                </form>
                <form class='w3-container' style='width: 60%; margin: 0 auto' method='post' action='registro'>
                    <button class='w3-btn w3-round-large w3-pink w3-margin-top w3-margin-bottom' style='margin-left: 30%; width: 40%'>Registro</button>
                </form>
                
            </div>
        </div>
        
        """, '$','$');

        template.add("alerta", alerta.orElse("") + "<br>");

        return template.render().toString();
    }

    /**
     * Plantilla formulario de registro 
     * @param alerta Mensaje de alerta del formulario
     * @return Plantilla renderizada a introducir
     */
    public static String formRegistro(Optional<String> alerta){

        ST template = new ST("""
        <div class='w3-card-4 w3-margin-top w3-margin-bottom w3-white w3-animate-top'>

            <header class='w3-container w3-pink'>
                <h1>Registro de usuarios</h1>
            </header>
            <div class='w3-container w3-margin-top'>
                <form class='w3-container' style='width: 60%; margin: 0 auto' method='post' action='registro'>

                    <label class='w3-margin-top w3-margin-bottom'>Nombre de usuario</label>
                    <input class='w3-input w3-margin-top w3-margin-bottom' type='text' placeholder='Nombre de usuario' name='nombreUsuario'>                            
                    <label class='w3-margin-top w3-margin-bottom'>Contrase&ntilde;a</label>
                    <input class='w3-input w3-margin-top w3-margin-bottom' type='password' placeholder='Contrase&ntilde;a' name='password'>
                    <label class='w3-margin-top w3-margin-bottom'>Repetir contrase&ntilde;a</label>
                    <input class='w3-input w3-margin-top w3-margin-bottom' type='password' placeholder='Contrase&ntilde;a' name='password2'>
                    $alerta$
                    <button class='w3-btn w3-round-large w3-pink w3-margin-top w3-margin-bottom' style='margin-left: 30%; width: 40%'>Registrar</button>
                            
                </form>                
            </div>
        </div>
        
        """, '$','$');

        template.add("alerta", alerta.orElse("") + "<br>");

        return template.render().toString();
    }

    /**
     * Plantilla para el formulario de cambio de password 
     * @param alerta Mensaje de alerta del formulario
     * @return Plantilla renderizada a introducir
     */
    public static String formCambiarPassword(Optional<String> info) {

        ST template = new ST ("""
        <div class='w3-card-4 w3-margin-top w3-margin-bottom w3-white w3-animate-top'>

            <header class='w3-container w3-pink'>
                <h1>Cambiar de contrase&ntilde;a</h1>
            </header>

            <div class='w3-container w3-margin-top'>
                <form class='w3-container' style='width: 60%; margin: 0 auto' method='post' action='panel'>
                            
                    <label class='w3-margin-top w3-margin-bottom'>Contrase&ntilde;a</label>
                    <input class='w3-input w3-margin-top w3-margin-bottom' type='password' placeholder='Contrase&ntilde;a' name='password'>
                    <label class='w3-margin-top w3-margin-bottom'>Repetir contrase&ntilde;a</label>
                    <input class='w3-input w3-margin-top w3-margin-bottom' type='password' placeholder='Repetir contrase&ntilde;a' name='password2'>
                    $alerta$
                    <button class='w3-btn w3-round-large w3-pink w3-margin-top w3-margin-bottom' style='margin-left: 30%; width: 40%'>Cambiar contrase&ntilde;a</button>
                    
                            
                </form>
                <form class='w3-container' style='width: 60%; margin: 0 auto' method='get' action='cerrar'>
                    <button class='w3-btn w3-round-large w3-pink w3-margin-top w3-margin-bottom' style='margin-left: 30%; width: 40%'>Cerrar sesi&oacute;n</button>
                </form>
                
            </div>
        </div>""", '$', '$');

        template.add("alerta", info.orElse("") + "<br>");

        return template.render().toString();
        
    }

    /**
     * Plantilla base de la pagina web
     * @return Plantilla lista para ser tratada
     */
    public static String plantillaBasePaginaWeb() {
        return ("""
        <!DOCTYPE html>
        <html>

            <head>
                <meta charset='utf-8'>
                <meta http-equiv='X-UA-Compatible' content='IE=edge'>
                <title>$titulo$</title>
                <meta name='viewport' content='width=device-width, initial-scale=1'>
                <link rel='stylesheet' href='https://www.w3schools.com/w3css/4/w3.css'>
                <link rel='stylesheet' href='https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css'>
                <link rel='stylesheet' href='https://fonts.googleapis.com/css?family=Roboto'>
                <link rel='icon' href='imagenes/logo.png'>
                <link href='https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css' rel='stylesheet'>
                <script src='https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js'></script>
                <script>
                    function w3_open() {
                        document.getElementById('main').style.marginLeft = '15%';
                        document.getElementById('mySidebar').style.width = '15%';
                        document.getElementById('mySidebar').style.display = 'block';
                        document.getElementById('openNav').style.display = 'none';
                        document.getElementById('cuerpo').style.marginLeft = '15%';
                    }
                    function w3_close() {
                        document.getElementById('main').style.marginLeft = '0%';
                        document.getElementById('mySidebar').style.display = 'none';
                        document.getElementById('openNav').style.display = 'inline-block';
                        document.getElementById('cuerpo').style.marginLeft = '0%';
                    }
                </script>

                <style>
                    html {
                        position: static;
                        background: url(imagenes/fondoPagina.jpg);
                        background-size: cover;
                        background-repeat: no-repeat, repeat;
                        background-attachment: fixed;
                        color: #000000;
                    }

                    .footer {
                        position: fixed;
                        left: 0;
                        bottom: 0;
                        width: 100%;
                        color: white;
                        text-align: center;
                    }
                </style>
            </head>

            <body>

                <div class='w3-sidebar w3-bar-block w3-card w3-animate-left' style='display:none' id='mySidebar'>

                    <button class='w3-bar-item w3-button w3-large ' onclick='w3_close()'>
                        Cerrar &times;
                    </button>


                    <form class='d-flex w3-margin-left w3-margin-right w3-padding-32' action='hola' method='get'>
                        <input class='form-control me-2' type='text' placeholder='Search'>
                        <button class='btn btn-primary'><i class='fa fa-search'></i></button>
                    </form>

                    <form action='iniciosesion' method='post'>
                        <button class='w3-bar-item w3-button '><p class='w3-padding-8'>$nombreUsuario$</p></button>
                    </form>

                    <form action='crearentrada' method='get'>
                        <button class='w3-bar-item w3-button'><p class='w3-padding-8'>Crear entrada</p></button>
                    </form>

                </div>

                <div id='main'>

                    <div class='w3-indigo'>
                        
                        <nav class='navbar navbar-expand-sm navbar-dark w3-indigo'>
                            
                            <div class='w3-margin-left'>
                                <button id='openNav' class='w3-button w3-indigo w3-xlarge' onclick='w3_open()'>&#9776; </button>
                            </div>

                            <div class='container-fluid'>

                                <form action='blog' method='get'>
                                    <button class='w3-bar-item w3-button '>
                                        <img src='imagenes/logo2.png' style='height: 50px; width: 150px;'>
                                    </button>
                                </form>

                                <button class='navbar-toggler' data-bs-toggle='collapse' data-bs-target='#mynavbar'>
                                    <span class='navbar-toggler-icon'></span>
                                </button>

                                <div class='collapse navbar-collapse' id='mynavbar'>
                                    <ul class='navbar-nav me-auto'>
                                        <li class='nav-item w3-margin-left w3-margin-right'>
                                            <form action='crearentrada' method='post'>
                                                <button class='w3-bar-item w3-button'>
                                                    <p class='w3-padding-8'>Crear entrada</p>
                                                </button>
                                            </form>
                                        </li>
                                    </ul>
                                    <div class='d-flex'>
                                        <ul class='navbar-nav me-auto'>
                                            <li class='nav-item w3-margin-left w3-margin-right'>
                                                <form action='iniciosesion' method='get'>
                                                    <button class='w3-bar-item w3-button'>
                                                        <p class='w3-padding-8'>$nombreUsuario$</p>
                                                    </button>
                                                </form>
                                            </li>
                                        </ul>
                                    </div>

                                    <form class='d-flex' method='get' action='buscarentrada'>
                                        <input class='form-control me-2' type='text' placeholder='Search'>
                                        <button class='btn btn-primary'><i class='fa fa-search'></i></button>
                                    </form>
                                </div>
                            </div>
                        </nav>

                    </div>

                </div>

                

                <div id='cuerpo' style>
                    
                    <div class='w3-col w3-container' style='width:10%'></div>
                    <div class='w3-col w3-container' style='width:70%;'>

                        $cuerpo$
                        
                    </div>
                    <div class='w3-col w3-container' style='width:20%'></div>        
                </div>    

                <div class='footer w3-indigo ' >
                    <ul class='navbar-nav me-auto'>
                        <li class='nav-item  w3-padding-4 '>
                            <p class='w3-margin-top' style='float:right' >Practica de Programacion de Servicios y procesos&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</p>
                        </li>
                    </ul>
                </div>
            </body>
        </html>
        
        """);
        
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
                <h1>$titulo$</h1>
            </header>
            <div class='w3-container w3-margin-top'>
                <p>
                    $texto$
                </p>
            </div>
            <footer class='w3-container w3-white'>
                <h5 class='w3-right'>$fecha$</h5>
            </footer>
        </div>
        """, '$','$');

        template.add("titulo", e.getTitulo());
        template.add("texto", e.getTexto());
        template.add("fecha", convertirFechas(e.getFecha()));

        return template.render().toString();
        
    }

    /**
     * Plantilla de la pagina principal tratada.
     * @param entradas Lista de entradas a tratar
     * @param nombreUsuario Nombre de usuario del usuario loggeado
     * @param string Titulo de la pagina web
     * @return Plantilla lista para ser introducida
     */
    public static String paginaPrincipal(List<Entrada> entradas, String nombreUsuario, String titulo) {

        String pagina = plantillaBasePaginaWeb();

        ST template = new ST(pagina, '$', '$');

        template.add("cuerpo", recogerEntradas(entradas));
        template.add("nombreUsuario", nombreUsuario);
        template.add("titulo", titulo);

        return template.render().toString();
        
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
            <a href='http://127.0.0.1:8080/practica/actualizar?id=$id$'>Editar</a>
            &nbsp;
            <a href='http://127.0.0.1:8080/practica/borrar?id=$id$'>Borrar</a>
            &nbsp; &nbsp;&nbsp;
            $titulo$
        </li>
        """, '$', '$'
        );

        template.add("id", entrada.getId());
        template.add("titulo", entrada.getTitulo());


        return template.render().toString();
    }

    /**
     * Pagina de inicio de sesion.
     * @param titulo Titulo de la pagina web
     * @param nombreUsuario Nombre de usuario
     * @param valor Valor del mensaje de la alerta
     * @return Pagina de inicio de sesion
     */
    public static String paginaInicioSesion(String titulo, String nombreUsuario, Optional<String> valor) {

        String pagina = plantillaBasePaginaWeb();

        ST template = new ST (pagina, '$', '$');

        template.add("titulo", titulo);

        template.add("nombreUsuario", nombreUsuario);

        template.add("cuerpo", formInicioSesion(valor));

        return template.render().toString();
        
    }

    /**
     * Entradas de la pagina principal
     * @param entradas Lista de entradas de la base de datos
     * @return Lista en html
     */
    public static String entradasPanelControl(List<Entrada> entradas) {

        StringBuffer buffer = new StringBuffer();

        buffer.append("""
        
        <div class='w3-card-4 w3-margin-top w3-margin-bottom w3-white w3-animate-top'>

            <header class='w3-container w3-pink'>
                <h1>Entradas</h1>
            </header>

            <div class='w3-container w3-margin-top'>
                <ul class='w3-ul w3-hoverable w3-margin-bottom'>
        """);

        for (Entrada entrada : entradas) {
            ST template = new ST (entradaPanel(), '$', '$');

            template.add("idEntrada", entrada.getId().toString());
            template.add("tituloEntrada", entrada.getTitulo());

            buffer.append(template.render().toString());

        }

        buffer.append("</ul></div></div>");

        return buffer.toString();
        
    }

    /**
     * Entrada para los paneles
     * @return Entrada en html
     */
    public static String entradaPanel() {

        return ("""
        <li>
            <a href='http://127.0.0.1:8080/practica/borrar?id=$idEntrada$'>Borrar</a>
            &nbsp;
            <a href='http://127.0.0.1:8080/practica/editor?id=$idEntrada$'>Editar</a>
            
            &nbsp; &nbsp;&nbsp;
            $tituloEntrada$
        </li>
        
        """);
        
    }

    public static String usuarioPanel() {

        return ("""
        <li>
            <a href='http://127.0.0.1:8080/practica/borrarusuario?nombreUsuario=$nombreUsuario$'>Borrar</a>
            &nbsp; &nbsp;&nbsp;
            $nombreUsuario$
        </li>
        
        """);
        
    }

    /**
     * Pagina del panel de control
     * @param titulo Titulo de la pagina pagina 
     * @param nombreUsuario nombre de usuario de la pagina web
     * @param entradas Lista de entradas sacadas de la base de datos
     * @param info info de vuelta de la base de datos
     * @return HTML de la pagina panel de control
     */
    public static String paginaPanelControl(String titulo, String nombreUsuario, List<Entrada> entradas, List<String> usuarios, Optional<String> info) {

        ST template = new ST (plantillaBasePaginaWeb(), '$', '$');

        StringBuffer buffer = new StringBuffer();
        
        buffer.append(formCambiarPassword(info));
        buffer.append(entradasPanelControl(entradas));

        if (usuarios != null) {
            buffer.append(usuariosPanelControl(usuarios));
        }


        template.add("cuerpo", buffer.toString());
        template.add("titulo", titulo);
        template.add("nombreUsuario", nombreUsuario);

        return template.render().toString();
    }

    private static String usuariosPanelControl(List<String> usuarios) {

        StringBuffer buffer = new StringBuffer();

        buffer.append("""
        
        <div class='w3-card-4 w3-margin-top w3-margin-bottom w3-white w3-animate-top'>

            <header class='w3-container w3-pink'>
                <h1>Usuarios</h1>
            </header>

            <div class='w3-container w3-margin-top'>
                <ul class='w3-ul w3-hoverable w3-margin-bottom'>
        """);

        usuarios.removeIf(u -> u.equals("admin"));

        for (String s : usuarios) {
            ST template = new ST (usuarioPanel(), '$', '$');

            template.add("nombreUsuario", s);

            buffer.append(template.render().toString());

        }

        buffer.append("</ul></div></div>");

        return buffer.toString();
    }

    public static String paginaRegistro(String titulo, String nombreUsuario, Optional<String> mensaje) {
        ST template = new ST (plantillaBasePaginaWeb(), '$', '$');

        template.add("cuerpo", formRegistro(mensaje));
        template.add("titulo", titulo);
        template.add("nombreUsuario", nombreUsuario);

        return template.render().toString();
    }
}


