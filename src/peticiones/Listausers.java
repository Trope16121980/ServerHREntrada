
package peticiones;

import modelo.Users;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author senor
 */
public class Listausers {
      public static ArrayList<Users> listaTotalUsers(){//devuelve el dni
         ArrayList<Users> listaToUsers = new ArrayList<>();
        try {
         
            String consulta = "SELECT * FROM users" ;
            PreparedStatement preparedStatement = conn.Conexion.getconexion().prepareStatement(consulta);
            ResultSet resultSet = preparedStatement.executeQuery();
    
            while (resultSet.next()) {
                String login = resultSet.getString("login");
                String pass = resultSet.getString("pass");
                int numtipe = resultSet.getInt("numtipe");
                String dni = resultSet.getString("dni");
                listaToUsers.add(new Users(login,pass,numtipe,dni));
                
            }          
            preparedStatement.close();
            return listaToUsers;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listaToUsers;                 
    }
      
       public static ArrayList<Users> listaTotalUsersDni(String palabraAbuscar) {//devuelve el dni
        ArrayList<Users> listaToUsersDni = new ArrayList<>();
        try {

            String consulta = "SELECT * FROM users where dni = ?";
            PreparedStatement preparedStatement = conn.Conexion.getconexion().prepareStatement(consulta);
            preparedStatement.setString(1, palabraAbuscar);
            ResultSet resultSet = preparedStatement.executeQuery();

           while (resultSet.next()) {
                String login = resultSet.getString("login");
                String pass = resultSet.getString("pass");
                int numtipe = resultSet.getInt("numtipe");
                String dni = resultSet.getString("dni");
                listaToUsersDni.add(new Users(login,pass,numtipe,dni));
           }
            preparedStatement.close();
            return listaToUsersDni;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listaToUsersDni;
    }
       
       public static ArrayList<Users> listaTotalUsersLogin(String palabraAbuscar) {//devuelve el dni
        ArrayList<Users> listaTotalUsersLogin = new ArrayList<>();
        try {

            String consulta = "SELECT * FROM users where login = ?";
            PreparedStatement preparedStatement = conn.Conexion.getconexion().prepareStatement(consulta);
            preparedStatement.setString(1, palabraAbuscar);
            ResultSet resultSet = preparedStatement.executeQuery();

           while (resultSet.next()) {
                String login = resultSet.getString("login");
                String pass = resultSet.getString("pass");
                int numtipe = resultSet.getInt("numtipe");
                String dni = resultSet.getString("dni");
                listaTotalUsersLogin.add(new Users(login,pass,numtipe,dni));
           }
            preparedStatement.close();
            return listaTotalUsersLogin;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listaTotalUsersLogin;
    }

       public static ArrayList<Users> listaTotalUsersTipe(int palabraAbuscar) {//devuelve el dni
        ArrayList<Users> listaTotalUsersTipe = new ArrayList<>();
        try {

            String consulta = "SELECT * FROM users where numtipe = ?";
            PreparedStatement preparedStatement = conn.Conexion.getconexion().prepareStatement(consulta);
            preparedStatement.setInt(1, palabraAbuscar);
            ResultSet resultSet = preparedStatement.executeQuery();

           while (resultSet.next()) {
                String login = resultSet.getString("login");
                String pass = resultSet.getString("pass");
                int numtipe = resultSet.getInt("numtipe");
                String dni = resultSet.getString("dni");
                listaTotalUsersTipe.add(new Users(login,pass,numtipe,dni));
           }
            preparedStatement.close();
            return listaTotalUsersTipe;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listaTotalUsersTipe;
    }
}
