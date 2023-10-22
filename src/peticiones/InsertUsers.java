package peticiones;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import modelo.Users;
/**
 *
 * @author Gustavo_Senorans
 */
public class InsertUsers {
    
     public static ArrayList<Users> insertUser(String datoLogin,
            String datoPass, int datoNumTipe, String datoDni) {//devuelve el dni

        ArrayList<Users> insertUser = new ArrayList<>();

        try {

            String insert = "INSERT INTO users (login, pass, numtipe, dni) VALUES (?, ?, ?, ?)";
            PreparedStatement preparedStatement = conn.Conexion.getconexion().prepareStatement(insert);
            preparedStatement.setString(1, datoLogin);
            preparedStatement.setString(2, datoPass);
            preparedStatement.setInt(3, datoNumTipe);
            preparedStatement.setString(4, datoDni);

            preparedStatement.executeUpdate();
            preparedStatement.close();

            return insertUser;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return insertUser;
    }
}
