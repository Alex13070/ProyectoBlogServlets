package practica;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class DB {

    private static boolean activo = false;
    /**
     * URL de la base de datos.
     */
    private final static String URL = "jdbc:sqlite:blog.db";

    /**
     * Clase a instanciar para acceder a la base de datos.
     */
    private final static String CLASE = "org.sqlite.JDBC";

    /**
     * Funcion para crear tablas en la base de datos.
     * 
     * @param sql Codigo sql de la tabla.
     */
    public static void crearTabla(String sql) {
        Connection conn = null;
        try {
            if (!activo) {
                Class.forName(CLASE).getDeclaredConstructor().newInstance();
            }
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
     * Funcion para crear las tablas en la clase instalador
     */
    public static void crearTablas() {

        try {
            Class.forName(CLASE).getDeclaredConstructor().newInstance();
        } catch (Exception e) {
            System.out.println("Error en la instanciacion de la clase de la creacion de la base de datos");
        }

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

        Usuario usuario = new Usuario("admin", "admin");

        try {
            crearUsuario(usuario);
        } catch (Exception e) {
            System.out.println(e);
        }

        Date date = new Date();
        date.setTime(1644079620365L);

        Entrada entrada = new Entrada(0,
                "titulo",
                "relleno relleno relleno relleno relleno relleno relleno relleno relleno relleno relleno",
                date);

        try {
            crearEntrada(entrada);
        } catch (Exception e) {
            System.out.println(e);
        }

    }

    /**
     * Introduccion de usuarios en la base de datos.
     * 
     * @param usuario Usuario a introducir
     * @return {@code true} Exito de la operacion
     *         {@code false} Fracaso de la operacion
     */
    public static boolean crearUsuario(Usuario usuario) {

        boolean exito = false;
        Connection conn = null;
        try {
            // Class.forName(CLASE).getDeclaredConstructor().newInstance();
            conn = DriverManager.getConnection(URL);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        } finally {
            try {
                if (conn != null) {

                    String sqlInsert = "INSERT INTO usuarios (usuario, password) VALUES (?, ?)";
                    PreparedStatement pstmt = conn.prepareStatement(sqlInsert);
                    pstmt.setString(1, usuario.getUsuario());
                    pstmt.setString(2, usuario.getPassword());
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
    public static boolean crearEntrada(Entrada entrada) {
        boolean exito = false;
        Connection conn = null;
        try {
            // Class.forName(CLASE).getDeclaredConstructor().newInstance();
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

    /**
     * Comprueba el usuario y password del usuario
     * 
     * @param usuario Usuario que queremos comprobar
     * @return {@code true} si los datos son correctos
     *         {@code false} si los datos no son correctos
     */
    public static boolean comprobarUsuario(Usuario usuario) {
        boolean exito = false;
        Connection conn = null;
        try {
            // Class.forName(CLASE).getDeclaredConstructor().newInstance();
            conn = DriverManager.getConnection(URL);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        } finally {
            try {
                if (conn != null) {

                    String sqlInsert = "SELECT usuario, password FROM usuarios WHERE usuario LIKE ? AND password LIKE ?";
                    PreparedStatement pstmt = conn.prepareStatement(sqlInsert);
                    pstmt.setString(1, usuario.getUsuario());
                    pstmt.setString(2, usuario.getPassword());
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
    public static boolean existeUsuario(String usuario) {
        boolean exito = false;
        Connection conn = null;
        try {
            // Class.forName(CLASE).getDeclaredConstructor().newInstance();
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
     * Funcion para conseguir todas las entradas de la base de datos.
     * 
     * @return Lista de entradas.
     */
    public static List<Entrada> getEntradas() {

        List<Entrada> entradas = new ArrayList<Entrada>();
        Connection conn = null;
        try {
            // Class.forName(CLASE).getDeclaredConstructor().newInstance();
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
                        Date date = new Date();
                        date.setTime(cursor.getInt(4));
                        e.setFecha(date);

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
    
    /**
     * Funcion para cambiar la password del usuario
     * 
     * @param usuario Usuario que queremos actualizar
     * @return {@code true} Si se ha podido actualizar el usuario correctamente
     *         {@code false} Si no se ha podido actualizar el usuario
     */
    public static boolean cambiarPassword(Usuario usuario) {
        boolean exito = false;
        Connection conn = null;
        try {
            if (!activo) {
                Class.forName(CLASE).getDeclaredConstructor().newInstance();
            }

            conn = DriverManager.getConnection(URL);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        } finally {
            try {
                if (conn != null) {
                    String sqlInsert = "UPDATE usuario SET password = ? WHERE usuario = ?";
                    PreparedStatement pstmt = conn.prepareStatement(sqlInsert);
                    pstmt.setString(1, usuario.getPassword());
                    pstmt.setString(2, usuario.getUsuario());
                    
                    pstmt.executeUpdate();
                    conn.commit();

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

}
