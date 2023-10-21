
package conn;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Gustavo_Senorans
 */
public class Conexion {

    Connection conn = null;
    private String user = "admin";
    private String password = "admin";
    private String url = "jdbc:postgresql://localhost:5432/HREntrada";
    //private String url = "jdbc:postgresql://localhost:5432/hrentrada";

    public static Connection getconexion() throws SQLException {
        String user = "admin";
        String password = "admin";
        String url = "jdbc:postgresql://localhost:5432/HREntrada";
        //String url = "jdbc:postgresql://localhost:5432/hrentrada";
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
