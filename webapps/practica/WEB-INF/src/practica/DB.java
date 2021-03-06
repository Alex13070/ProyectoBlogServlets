package practica;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Date;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *  @author @Alex13070
 *  
 *  Clase para el manejo de la base de datos
 */
public class DB {

    /**
     * Base de datos
     */
    private final String URL = "jdbc:sqlite:blog.db";

    /**
     * Clase a instanciar para acceder a la base de datos.
     */
    private final String CLASE = "org.sqlite.JDBC";

    
    /**
     * Constructor por defecto de la clase
     */
    public DB() {
        try {
            Class.forName(CLASE).getDeclaredConstructor().newInstance();
        } catch (Exception e) {
            System.err.println("Error al crear los drivers de la base de datos.");
        }
    }

    ////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////

    //Creacion de campos para la base de datos

    ////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////


    /**
     * Funcion para crear tablas en la base de datos.
     * 
     * @param sql Codigo sql de la tabla.
     */
    public void crearTabla(String sql) {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(URL);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        } finally {
            try {
                if (conn != null) {

                    Statement stmt = conn.createStatement();
                    stmt.execute(sql);
                }
                // Se cierra la conexión con la base de datos
                conn.close();
            } catch (SQLException ex) {
                System.err.println(ex.getMessage());
            }
        }
    }
    
    /**
     * Introduccion de usuarios en la base de datos.
     * 
     * @param usuario Usuario a introducir
     * @return {@code true} Exito de la operacion
     *         {@code false} Fracaso de la operacion
     */
    public boolean crearUsuario(Usuario usuario) {

        boolean exito = false;
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(URL);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        } finally {
            try {
                if (conn != null) {

                    String sqlInsert = "INSERT INTO usuarios (usuario, password) VALUES (?, ?)";
                    PreparedStatement pstmt = conn.prepareStatement(sqlInsert);
                    pstmt.setString(1, usuario.getUsuario());
                    pstmt.setString(2, MD5.encriptar(usuario.getPassword()));
                    pstmt.executeUpdate();
                    
                    exito = true;

                }
                // Se cierra la conexión con la base de datos
                conn.close();
            } catch (SQLException ex) {
                System.err.println(ex.getMessage());
            }
        }

        return exito;
    }

    /**
     * Introducir una entrada en la base de datos
     * 
     * @param entrada Entrada a introducir
     * @return {@code true} Exito de la operacion
     *         {@code false} Fracaso de la operacion
     */
    public boolean crearEntrada(Entrada entrada) {
        boolean exito = false;
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(URL);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        } finally {
            try {
                if (conn != null) {
                    String sqlInsert = "INSERT INTO entradas (titulo, texto, fecha) VALUES (?,?,?)";
                    PreparedStatement pstmt = conn.prepareStatement(sqlInsert);
                    pstmt.setString(1, entrada.getTitulo());
                    pstmt.setString(2, entrada.getTexto());
                    pstmt.setLong(3, entrada.getFecha().getTime());
                    pstmt.executeUpdate();
                    
                    exito = true;
                }
                // Se cierra la conexión con la base de datos
                conn.close();
            } catch (SQLException ex) {
                System.err.println(ex.getMessage());
            }
        }

        return exito;
    }

    ////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////

    //Creacion de la base de datos.

    ////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////


    /**
     * Funcion para crear las tablas en la clase instalador
     */
    public void crearTablas() {

        crearTabla(
                """
                        CREATE TABLE IF NOT EXISTS usuarios (
                            usuario TEXT PRIMARY KEY,
                            password TEXT
                        );
                        """);

        crearTabla(
                """
                        CREATE TABLE IF NOT EXISTS entradas (
                            id INTEGER PRIMARY KEY AUTOINCREMENT,
                            titulo TEXT,
                            texto TEXT,
                            fecha INTEGER
                        );
                        """);


        Entrada entrada = new Entrada(0,
                "titulo",
                "relleno relleno relleno relleno relleno relleno relleno relleno relleno relleno relleno",
                new Date(1644079620365L));

        try {
            crearEntrada(entrada);
        } catch (Exception e) {
            System.out.println(e);
        }
        
        Usuario usuario = new Usuario("admin", "admin");

        try {
            crearUsuario(usuario);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    ////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////

    //Consultas usuario

    ////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////


    
    /**
     * Comprueba el usuario y password del usuario
     * 
     * @param usuario Usuario que queremos comprobar
     * @return {@code true} si los datos son correctos
     *         {@code false} si los datos no son correctos
     */
    public boolean comprobarUsuario(Usuario usuario) {
        boolean exito = false;
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(URL);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        } finally {
            try {
                if (conn != null) {

                    String sqlInsert = "SELECT usuario, password FROM usuarios WHERE usuario LIKE ? AND password LIKE ?";
                    PreparedStatement pstmt = conn.prepareStatement(sqlInsert);
                    pstmt.setString(1, usuario.getUsuario());
                    pstmt.setString(2, MD5.encriptar(usuario.getPassword()));
                    ResultSet cursor = pstmt.executeQuery();

                    if (cursor.next()) {
                        exito = true;
                    }

                    // Se cierra la conexión con la base de datos
                    conn.close();
                }

            } catch (SQLException ex) {
                System.err.println(ex.getMessage());
            }
        }

        return exito;
    }

    /**
     * Funcion para ver si existe algun usuario en base a la clave primaria del
     * mismo
     * 
     * @param usuario Clave primaria del Usuario, Nombre de usuario
     * @return {@code true} si el usuario existe
     *         {@code false} si el usuario no existe
     */
    public boolean existeUsuario(String usuario) {
        boolean exito = false;
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(URL);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        } finally {
            try {
                if (conn != null) {

                    String sqlInsert = "SELECT usuario, password FROM usuarios WHERE usuario LIKE ?";
                    PreparedStatement pstmt = conn.prepareStatement(sqlInsert);
                    pstmt.setString(1, usuario);
                    ResultSet cursor = pstmt.executeQuery();

                    if (cursor.next()) {
                        exito = true;
                    }
                }

                // Se cierra la conexión con la base de datos
                conn.close();
            } catch (SQLException ex) {
                System.err.println(ex.getMessage());
            }
        }

        return exito;
    }

    /**
     * Funcion para cambiar la password del usuario
     * 
     * @param usuario Usuario que queremos actualizar
     * @return {@code true} Si se ha podido actualizar el usuario correctamente
     *         {@code false} Si no se ha podido actualizar el usuario
     */
    public boolean cambiarPassword(Usuario usuario) {
        boolean exito = false;
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(URL);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        } finally {
            try {
                if (conn != null) {
                    String sqlInsert = "UPDATE usuarios SET password = ? WHERE usuario = ?";
                    PreparedStatement pstmt = conn.prepareStatement(sqlInsert);
                    pstmt.setString(1, MD5.encriptar(usuario.getPassword()));
                    pstmt.setString(2, usuario.getUsuario());
                    
                    pstmt.executeUpdate();
                    
                    exito = true;
                }
                // Se cierra la conexión con la base de datos
                conn.close();
            } catch (SQLException ex) {
                System.err.println(ex.getMessage());
            }
        }

        return exito;
    }

    /**
     * Devuelve todos los usuarios de la base de datos
     * @return Lista de usuarios de la base de datos
     */
    public List<Usuario> getUsuarios() {

        List<Usuario> usuarios = new ArrayList<Usuario>();
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(URL);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        } finally {
            try {
                if (conn != null) {

                    String sqlInsert = "SELECT usuario, password FROM usuarios";
                    PreparedStatement pstmt = conn.prepareStatement(sqlInsert);
                    ResultSet cursor = pstmt.executeQuery();

                    while (cursor.next()) {
                        usuarios.add(
                            Usuario.builder()
                            .usuario(cursor.getString(1))
                            .password(cursor.getString(2))
                            .build()
                        );
                    }

                }
                // Se cierra la conexión con la base de datos
                conn.close();
            } catch (SQLException ex) {
                System.err.println(ex.getMessage());
            }
        }


        return usuarios;
    }

    public boolean borrarUsuario(String nombreUsuario) {
        boolean exito = false;
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(URL);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        } finally {
            try {
                if (conn != null) {

                    String sqlInsert = "DELETE FROM usuarios WHERE usuario = ?";
                    PreparedStatement pstmt = conn.prepareStatement(sqlInsert);
                    pstmt.setString (1, nombreUsuario);
                    pstmt.executeUpdate();
                    exito = true;
                }

                // Se cierra la conexión con la base de datos
                conn.close();
            } catch (SQLException ex) {


                System.err.println(ex.getMessage());
            }
        }

        return exito;
    }

    ////////////////////////////////////////////////////////////////

    //Consultas entradas

    ////////////////////////////////////////////////////////////////

    /**
     * Funcion para conseguir todas las entradas de la base de datos.
     * 
     * @return Lista de entradas.
     */
    public List<Entrada> getEntradas() {

        List<Entrada> entradas = new ArrayList<Entrada>();
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(URL);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        } finally {
            try {
                if (conn != null) {

                    String sqlInsert = "SELECT id, titulo, texto, fecha FROM entradas";
                    PreparedStatement pstmt = conn.prepareStatement(sqlInsert);
                    ResultSet cursor = pstmt.executeQuery();

                    while (cursor.next()) {
                        Entrada e = new Entrada();

                        e.setId(cursor.getInt(1));
                        e.setTitulo(cursor.getString(2));
                        e.setTexto(cursor.getString(3));
                        e.setFecha(new Date(cursor.getLong(4)));

                        entradas.add(e);
                    }
                }
                // Se cierra la conexión con la base de datos
                conn.close();
            } catch (SQLException ex) {
                System.err.println(ex.getMessage());
            }
        }


        return entradas;

    }
    
    public void actualizarEntrada(Entrada entrada) {

        Connection conn = null;
        try {
            conn = DriverManager.getConnection(URL);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        } finally {
            try {
                if (conn != null) {

                    String sqlInsert = "UPDATE entradas SET titulo = ?, texto = ?, fecha = ? WHERE id = ?";
                    PreparedStatement pstmt = conn.prepareStatement(sqlInsert);
                    pstmt.setString(1, entrada.getTitulo());
                    pstmt.setString(2, entrada.getTexto());
                    pstmt.setLong(3, entrada.getFecha().getTime());
                    pstmt.setInt(4, entrada.getId());

                    pstmt.executeUpdate();

                    
                }
                // Se cierra la conexión con la base de datos
                conn.close();
            } catch (SQLException ex) {
                System.err.println(ex.getMessage());
            }
        }
    }
    /**
     * Busqueda de una entrada en la base de datos
     * @param id Clave primaria de la entrada precisada para la busqueda
     * @return En caso de que no se encuentre la entrada, se devolvera un Optional.empty(), en caso contrario 
     *         se te devolvera un usuario encapsulado.
     */
    public Optional<Entrada> buscarEntrada(Integer id) {

        Optional<Entrada> entrada = Optional.empty();
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(URL);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        } finally {
            try {
                if (conn != null) {

                    String sqlInsert = "SELECT id, titulo, texto, fecha FROM entradas WHERE id = ?";
                    PreparedStatement pstmt = conn.prepareStatement(sqlInsert);
                    pstmt.setInt(1, id);
                    ResultSet cursor = pstmt.executeQuery();

                    if (cursor.next()) {
                        Entrada e = new Entrada();

                        e.setId(cursor.getInt(1));
                        e.setTitulo(cursor.getString(2));
                        e.setTexto(cursor.getString(3));
                        e.setFecha( new Date(cursor.getLong(4)));

                        entrada = Optional.of(e);
                    }
                }

                // Se cierra la conexión con la base de datos
                conn.close();
            } catch (SQLException ex) {
                System.err.println(ex.getMessage());
            }
        }

        return entrada;

    }

    /**
     * Borrado de usuarios de la base de datos.
     * @param id Clave primaria de entrada en la base de datos.
     * @return {@code true} Exito del borrado de la entrada.
     *         {@code false} Fracaso del borrado de la entrada.
     */
    public boolean borrarEntrada(Integer id) {
        boolean exito = false;
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(URL);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        } finally {
            try {
                if (conn != null) {

                    String sqlInsert = "DELETE FROM entradas WHERE id = ?";
                    PreparedStatement pstmt = conn.prepareStatement(sqlInsert);
                    pstmt.setInt(1, id);
                    pstmt.executeUpdate();
                    exito = true;
                }

                // Se cierra la conexión con la base de datos
                conn.close();
            } catch (SQLException ex) {


                System.err.println(ex.getMessage());
            }
        }

        return exito;

    }

    public List<Entrada> getEntradas(String string) {
        List<Entrada> entradas = new ArrayList<Entrada>();
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(URL);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        } finally {
            try {
                if (conn != null) {

                    String sqlInsert = "SELECT id, titulo, texto, fecha FROM entradas WHERE titulo LIKE ?";
                    PreparedStatement pstmt = conn.prepareStatement(sqlInsert);
                    pstmt.setString(1, string + "%");    
                    ResultSet cursor = pstmt.executeQuery();

                    while (cursor.next()) {
                        Entrada e = new Entrada();

                        e.setId(cursor.getInt(1));
                        e.setTitulo(cursor.getString(2));
                        e.setTexto(cursor.getString(3));
                        e.setFecha(new Date(cursor.getLong(4)));

                        entradas.add(e);
                    }
                }
                // Se cierra la conexión con la base de datos
                conn.close();
            } catch (SQLException ex) {
                System.err.println(ex.getMessage());
            }
        }
        return entradas;
    }


}
