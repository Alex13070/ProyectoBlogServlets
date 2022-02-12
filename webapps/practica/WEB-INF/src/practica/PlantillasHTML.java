package practica;

import java.text.SimpleDateFormat;
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
    private static final SimpleDateFormat FORMATODATE = new SimpleDateFormat("yyyy-MM-dd");

    /**
     * Aqui se almacena la pagina
     * @return
     */
    private final static String PLANTILLA_BASE =
    """
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


                    <form class='d-flex w3-margin-left w3-margin-right w3-padding-32' action='buscarentrada' method='get'>
                        <input class='form-control me-2' type='text' placeholder='Search' name='nombre'>
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
                                            <form action='crearentrada' method='get'>
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
                                        <input class='form-control me-2' type='text' placeholder='Search' name='nombre'>
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
        
        """;
    public static String ENTRADA_PANEL =
        """
            <li>
                <a href='http://127.0.0.1:8080/practica/borrar?id=$idEntrada$'>Borrar</a>
                &nbsp;
                <a href='http://127.0.0.1:8080/practica/editor?id=$idEntrada$'>Editar</a>
                
                &nbsp; &nbsp;&nbsp;
                $tituloEntrada$
            </li>
            
        """;

    ////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////

    //Pagina inicial

    ////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////

    /**
     * Pagina principal del blog --> Servlet blog / buscarentrada
     * @param entradas Lista de entradas a mostrar 
     * @param nombreUsuario Nombre de usuario de la pagina web 
     * @param titulo Titulo de la pagina web 
     * @param sesion Indica si hay sesion o no
     * @return String de la pagina principal
     */
    public static String paginaPrincipal(List<Entrada> entradas, String nombreUsuario, String titulo, boolean sesion) {

        String pagina = PLANTILLA_BASE;

        ST template = new ST(pagina, '$', '$');

        template.add("cuerpo", recogerEntradas(entradas, sesion));
        template.add("nombreUsuario", nombreUsuario);
        template.add("titulo", titulo);

        return template.render().toString();
        
    }

    /**
     * Emplea la funcion que parsea entradas y ordena por fecha
     * @param entradas Lista de entradas a parsear
     * @param sesion Indica si hay sesion o no
     * @return Devuelve las entradas parseada
     */
    private static String recogerEntradas(List<Entrada> entradas, boolean sesion) {
        StringBuffer buffer = new StringBuffer();

        entradas.sort((o1,o2) -> o1.getFecha().compareTo(o2.getFecha()));

        for (Entrada entrada : entradas) {
            buffer.append(entradaCompleta(entrada, sesion));
        }

        return buffer.toString();
    }

    /**
     * Entrada lista para introducir
     * @param e Entrada a mostrar
     * @param sesion Dice si hay sesion
     * @return String con la entrada convertida a html
     */
    public static String entradaCompleta(Entrada e, boolean sesion) {
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
                $editor$
                <h5 class='w3-right'>$fecha$</h5>
            </footer>
        </div>
        """, '$','$');

        if (sesion){
            ST st = new ST ( """
            <p class='w3-left'>
                <a href='http://127.0.0.1:8080/practica/borrar?id=$idEntrada$'>Borrar</a>
                &nbsp;
                <a href='http://127.0.0.1:8080/practica/editor?id=$idEntrada$'>Editar</a>
            </p>
            """, '$','$');

            st.add("idEntrada", e.getId());
            template.add("editor", st.render().toString());
        }
        else {
            template.add("editor", "");
        }

        template.add("titulo", extraerCaracteres(e.getTitulo()));
        template.add("texto", extraerCaracteres(e.getTexto()));
        template.add("fecha", FORMATODATE.format(e.getFecha()));

        return template.render().toString();
        
    }

    ////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////

    //Pagina de inicio de sesion

    ////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////

    /**
     * Pagina de inicio de sesion formateada
     * @param titulo titulo de la pagina web
     * @param nombreUsuario Nombre de usuario de la pagina web
     * @param valor Alerta a mostrar
     * @return String con la pagina de inicio de sesion formateada
     */
    public static String paginaInicioSesion(String titulo, String nombreUsuario, Optional<String> valor) {

        String pagina = PLANTILLA_BASE;

        ST template = new ST (pagina, '$', '$');

        template.add("titulo", titulo);

        template.add("nombreUsuario", nombreUsuario);

        template.add("cuerpo", formInicioSesion(valor));

        return template.render().toString();
        
    }



    /**
     * Formulario de inicio de sesion tratado con una alerta
     * @param alerta alerta a mostrar
     * @return formulario tratado
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
            </div>
        </div>
        
        """, '$','$');

        template.add("alerta", alerta.orElse("") + "<br>");

        return template.render().toString();
    }


    ////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////

    //Pagina registro

    ////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////

    /**
     * Pagina de inicio de sesion formateada
     * @param titulo titulo de la pagina web
     * @param nombreUsuario Nombre de usuario de la pagina web
     * @param mensaje Alerta a mostrar
     * @return String con la pagina de inicio de sesion formateada
     */
    public static String paginaRegistro(String titulo, String nombreUsuario, Optional<String> mensaje) {
        ST template = new ST (PLANTILLA_BASE, '$', '$');

        template.add("cuerpo", formRegistro(mensaje));
        template.add("titulo", titulo);
        template.add("nombreUsuario", nombreUsuario);

        return template.render().toString();
    }

    /**
     * Formulario de registro tratado con una alerta
     * @param alerta alerta a mostrar
     * @return formulario tratado
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

        template.add("alerta", alerta.orElse("") + "");

        return template.render().toString();
    }

    ////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////

    //Pagina panel de control

    ////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////

    /**
     * Pagina de panel de control formateada
     * @param titulo titulo de la pagina web
     * @param nombreUsuario Nombre de usuario de la pagina web
     * @param entradas Entradas a mostrar
     * @param info Mensajes de informacion 
     * @return String con la pagina en html
     */
    public static String paginaPanelControl(String titulo, String nombreUsuario, List<Entrada> entradas, Optional<String> info) {

        ST template = new ST (PLANTILLA_BASE, '$', '$');

        StringBuffer buffer = new StringBuffer();
        
        buffer.append(formCambiarPassword(info, nombreUsuario));
        buffer.append(entradasPanelControl(entradas));

        template.add("cuerpo", buffer.toString());
        template.add("titulo", titulo);
        template.add("nombreUsuario", nombreUsuario);

        return template.render().toString();
    }

    /**
     * Formulario de cambio de password tratado con una alerta
     * @param alerta alerta a mostrar
     * @return formulario tratado
     */
    public static String formCambiarPassword(Optional<String> info, String nombreUsuario) {

        ST template = new ST ("""
        <div class='w3-card-4 w3-margin-top w3-margin-bottom w3-white w3-animate-top'>

            <header class='w3-container w3-pink'>
                <h1>Cambiar de contrase&ntilde;a</h1>
            </header>

            <div class='w3-container w3-margin-top'>
                <form class='w3-container' style='width: 60%; margin: 0 auto' method='post' action='actualizarusuario'>
                    <input type='hidden' name='nombreUsuario' value='$nombreUsuario$' />
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
                $admin$
                
            </div>
        </div>""", '$', '$');

        template.add("alerta", info.orElse("") + "<br>");

        if (nombreUsuario.equals("admin")) {
            template.add("admin", """
            <form class='w3-container' style='width: 60%; margin: 0 auto' method='post' action='administracionusuarios'>
                <button class='w3-btn w3-round-large w3-pink w3-margin-top w3-margin-bottom' style='margin-left: 30%; width: 40%'>Administracion de usuarios</button>
            </form>
            """);
        }
        else
            template.add("admin", "");

        template.add("nombreUsuario", nombreUsuario);

        return template.render().toString();
        
    }

    /**
     * Tratamiento de entradas para mostar en el panel de control
     * @param entradas Entradas a tratar
     * @return Entradas tratadas
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
            ST template = new ST (ENTRADA_PANEL, '$', '$');

            template.add("idEntrada", entrada.getId().toString());
            template.add("tituloEntrada", entrada.getTitulo());

            buffer.append(template.render().toString());

        }

        buffer.append("</ul></div></div>");

        return buffer.toString();
        
    }
    
    /*
    private static String listaEntradas(List<Entrada> entradas) {
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
    

    private static String updateDelete(Entrada entrada) {
        ST template = new ST(
        """
        <li>
            <a href='http://127.0.0.1:8080/practica/editor?id=$id$'>Editar</a>
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
    

    public static String usuarioPanel() {

        return ("""
        <li>
            <a href='http://127.0.0.1:8080/practica/borrarusuario?nombreUsuario=$nombreUsuario$'>Borrar</a>
            &nbsp; &nbsp;&nbsp;
            $nombreUsuario$
        </li>
        
        """);
        
    }
    */
    
    ////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////

    //Pagina crear/actualizar entrada

    ////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////

    /**
     * Pagina crear entrada
     * @param entrada parametro de entrada
     * @return html formateado
     */
    private static String crearEntrada(Entrada entrada) {

        ST template = new ST ("""
        <div class='w3-card-4 w3-margin-top w3-margin-bottom w3-white w3-animate-top'>

            <header class='w3-container w3-pink'>
                <h1>Editor de entradas</h1>
            </header>

            <div class='w3-container w3-margin-top'>
                <form class='w3-container' style='width: 60%; margin: 0 auto' method='get' action='$accion$'>
                    
                    <input type='hidden' value='$id$' name='identificador'>
                    <label class='w3-margin-top w3-margin-bottom'>T&iacute;tulo</label>
                    <input class='w3-input w3-margin-top w3-margin-bottom' placeholder='T&iacute;tulo' name='titulo' value='$titulo$'>
                    <label class='w3-margin-top w3-margin-bottom'>Texto</label>
                    <textarea class='w3-input w3-margin-top w3-margin-bottom' placeholder='Cuerpo del blog' name='texto'>$texto$</textarea>
                    <label class='w3-margin-top w3-margin-bottom'>Fecha</label>
                    <input class='w3-input w3-margin-top w3-margin-bottom' type='date' placeholder='T&iacute;tulo' name='fecha' value='$fecha$'>
                    <button class='w3-btn w3-round-large w3-pink w3-margin-top w3-margin-bottom' style='margin-left: 30%; width: 40%'>$textoboton$</button>
                                
                </form>
            </div>
        </div>
        """, '$','$');

        if (entrada == null) {
            template.add("id", "");
            template.add("titulo", "");
            template.add("fecha", "");
            template.add("texto", "");
            template.add("accion", "crearentrada");
            template.add("textoboton", "Crear entrada");
        }
        else {
            template.add("id", entrada.getId());
            template.add("titulo", extraerCaracteres(entrada.getTitulo()));
            template.add("texto", extraerCaracteres(entrada.getTexto()));
            template.add("accion", "editor");
            template.add("textoboton", "Actualizar entrada");
            template.add("fecha", (FORMATODATE.format(entrada.getFecha())));
        }


        return template.render().toString();
    }


    /**
     * Crear pagina de entrada
     * @param titulo Titulo de la pagina web 
     * @param nombreUsuario Nombre de usuario de la pagina web
     * @return Pagina de crear entrada formateada
     */
    public static String paginaCrearEntrada(String titulo, String nombreUsuario) {

        ST template = new ST (PLANTILLA_BASE, '$', '$');  

        template.add("cuerpo", crearEntrada(null));
        template.add("titulo", titulo);
        template.add("nombreUsuario", nombreUsuario);

        return template.render().toString();
    }

    /**
     * Pagina a actualizar
     * @param e entrada a actualizar
     * @param titulo titulo de la pagina web
     * @param nombreUsuario nombre de usuario del usuario en cuestion
     * @return pagina formateada
     */
    public static String paginaActualizarEntrada(Entrada e, String titulo, String nombreUsuario) {

        ST template = new ST (PLANTILLA_BASE, '$', '$');  

        template.add("cuerpo", crearEntrada(e));
        template.add("titulo", titulo);
        template.add("nombreUsuario", nombreUsuario);

        return template.render().toString();
    }

    /**
     * Funcion para extraer caracteres no deseados de una cadena 
     * @param str cadena a tratar
     * @return cadena tratada
     */
    private static String extraerCaracteres(String str) {
        
        str = str.replaceAll("&", "&amp;");
        str = str.replaceAll(">", "&gt;");
        str = str.replaceAll("<", "&lt;");
        str = str.replaceAll("'", "&#039;");
        str = str.replaceAll("\"", "&#034;");

        return str;
    }

    ////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////

    //Pagina de manejo de usuarios

    ////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////

    /**
     * Pagina de manejo de usuarios
     * @param usuarios Usuarios a tratar
     * @return pagina de manejo de usuarios formateada
     */
    public static String paginaManejoUsuario(List<Usuario> usuarios) {
        ST template = new ST (PLANTILLA_BASE, '$', '$');

        template.add("titulo", "Blog - Manejo de usuarios");
        template.add("nombreUsuario", "admin");
        template.add("cuerpo",  usuariosPaginaManejo (usuarios));




        return template.render().toString();
    }

    /**
     * Tratamiento de usuarios
     * @param usuarios usuarios a tratar
     * @return usuarios tratados 
     */
    private static String usuariosPaginaManejo(List<Usuario> usuarios) {

        ST template = new ST ("""
        <div class='w3-card-4 w3-margin-top w3-margin-bottom w3-white w3-animate-top'>

            <header class='w3-container w3-pink'>
                <h1>Administraci&oacute;n de usuarios</h1>
            </header>

            <div class='w3-container w3-margin-top'>

                <form class='w3-container' style='width: 60%; margin: 0 auto' method='post' action='registro'>
                    <button class='w3-btn w3-round-large w3-pink w3-margin-top w3-margin-bottom' style='margin-left: 30%; width: 40%'>Registrar usuario</button>
                </form>

                <ul class='w3-ul w3-hoverable w3-margin-bottom w3-centered'>
                    $usuarios$
                </ul>
            </div>

        </div>
        """, '$','$');

        template.add("usuarios", manejoUsuarios (usuarios));

        return template.render().toString();
    }

    /**
     * Manejo de usuarios
     * @param usuarios usuarios
     * @return String con los usuarios a tratar
     */
    private static String  manejoUsuarios(List<Usuario> usuarios) {

        StringBuffer buffer = new StringBuffer();

        for (Usuario usuario : usuarios) {
            ST template = new ST ("""
            <li>
                    
                <form class='d-flex w3-margin-left w3-margin-right w3-padding-32' action='actualizarusuario' method='post'>
                    <input type='hidden' name='nombreUsuario' value='$nombreUsuario$' />
                    <a href='http://127.0.0.1:8080/practica/borrarusuario?nombreUsuario=$nombreUsuario$'>Borrar</a>
                    
                    <p class='w3-margin-left w3-margin-right'>$nombreUsuario$</p>
                    <input class='form-control me-2  w3-margin-left' type='text' placeholder='Contrase&ntilde;a' style='width: 30%' value='' name='password'>
                    <input class='form-control me-2  w3-margin-left' type='text' placeholder='Repetir contrase&ntilde;a' style='width: 30%' value='' name='password2'>
                    <button class='btn btn-primary'>Actualizar</button>
                </form>

            </li>
            """, '$','$');

            template.add("nombreUsuario", usuario.getUsuario());

            //Al codificarla via MD5, para mostrarla hay que desencriptarla y no se puede.
            //template.add("password", usuario.getPassword());

            buffer.append(template.render().toString());


        }


        return buffer.toString();
    }


    ////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////

    //Pagina del instalador

    ////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////

    /**
     * Pagina del instalador
     */
    public static String paginaInstalador() {
        return 
        """
        <!DOCTYPE html>
        <html>
            <head>
                <meta charset='utf-8'>
                <meta http-equiv='X-UA-Compatible' content='IE=edge'>
                <title>Instalador</title>
                <meta name='viewport' content='width=device-width, initial-scale=1'>
                <style>
                    html {
                        background-color: #5c5c5c;
                    }
                    #size{
                        text-align: center;
                        margin-left: 25%;
                        margin-right: 25%;
                        margin-top: 5%;
                        background-color: #FFFFFF;
                    }
                </style>
                <link rel='stylesheet' href='https://www.w3schools.com/w3css/4/w3.css'>
                <link rel='icon' href='imagenes/logo.png'>
            </head>
            <body>
                <div id='size' class='container w3-card-4 w3-animate-opacity' >
                    <h1>Instalador</h1>
                    <img src='imagenes/fotoInstaller.png'></img>
                    <div class='w3-container w3-center'>
                        <form action='instalador' method='get'>
                            <input type='hidden' name='pulsado' value='pulsado'>
                            <button class='w3-btn w3-red '>Instalar aplicacion</button>
                        </form>
                        <br>
                    </div>
                </div>
            </body>
        </html>
        """;
    }


}


