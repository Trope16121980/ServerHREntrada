package controladores;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Gustavo Senoráns Varela
 * @version 1.4, 15/11/2023
 * @since jdk 17
 */
public class Conexion {

    Connection conn = null;
    private String user = "admin";
    private String password = "admin";
    private String url = "jdbc:postgresql://localhost:5432/HREntrada";

    /**
     * Genera la conexión con la BBDD HREntrada
     *
     * @throws SQLException en el proceso de conexión con BBDD HREntrada
     * @return devuelve true si la conexión es correcta o false si la conexion
     * es fallida
     */
    public static Connection getconexion() throws SQLException {
        String user = "admin";
        String password = "admin";
        String url = "jdbc:postgresql://localhost:5432/HREntrada";
        Connection conn = null;
        conn = DriverManager.getConnection(url, user, password);
        return conn;
    }

    public Conexion() {
        try {
            conn = DriverManager.getConnection(url, user, password);
        } catch (SQLException ex) {
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Metodo para la conexión
     *
     * @param user nombre de usuario de la BBDD HREntrada
     * @param password del usuario de BBDD HREntrada
     * @param url la dirección de conexión con la BBDD HREntrada
     */
    public Conexion(String user, String password, String url) {
        try {
            conn = DriverManager.getConnection(url, user, password);
        } catch (SQLException ex) {
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public Connection getConn() {
        return conn;
    }

    public void setConn(Connection conn) {
        this.conn = conn;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

}
