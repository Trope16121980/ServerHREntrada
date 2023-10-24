
package peticiones;  

import modelo.Users;
import conn.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author Gustavo_Senorans
 */
public class Login {

     public Users comprobarCredencialesBD(String login, String pass){//devuelve el dni
        Users user = null;
        String dni="-1";
        String numtipe = "-1";
        String codigo = "-1";
        try {
         
            String consulta = "SELECT * FROM users where login = ? and  pass =?" ;
            PreparedStatement preparedStatement = conn.Conexion.getconexion().prepareStatement(consulta);
            //paso 4 asignar valores a los parámetros de la consulta
            preparedStatement.setString(1, login);
            preparedStatement.setString(2, pass);
            //paso 5 ejecutar la consulta
            ResultSet resultSet = preparedStatement.executeQuery();
    
            if (resultSet.next()) {
                dni = resultSet.getString("dni"); 
                numtipe = resultSet.getString("numtipe");
                user = new Users(login,pass,Integer.parseInt(numtipe),dni,Integer.parseInt(codigo));
            }          
            preparedStatement.close();
            return user;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;                 
    }
}
