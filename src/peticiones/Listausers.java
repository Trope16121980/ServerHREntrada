package peticiones;

import modelo.Users;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * @author Gustavo Senoráns Varela
 * @version 1.4, 15/11/2023
 * @since jdk 17
 */
public class Listausers {

    /**
     * Este método hace la busqueda total de la tabla Users y envía los datos
     * ordenados por su nombre Objeto a recibir del cliente: codiUser,0,1,0,0,0
     *
     * @return devuelve la listaToUsers, todos los datos de todos los empleados
     */
    public static ArrayList<Users> listaTotalUsers() {
        ArrayList<Users> listaToUsers = new ArrayList<>();
        try {

            String consulta = "SELECT * FROM users ORDER BY login";
            PreparedStatement preparedStatement = controladores.Conexion.getconexion().prepareStatement(consulta);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                String login = resultSet.getString("login");
                String pass = resultSet.getString("pass");
                int numtipe = resultSet.getInt("numtipe");
                String dni = resultSet.getString("dni");
                listaToUsers.add(new Users(login, pass, numtipe, dni));

            }
            preparedStatement.close();
            return listaToUsers;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listaToUsers;
    }

    /**
     * Este método hace la busqueda de la tabla Users por su dni de empleado y
     * envía los datos ordenados por su nombre
     *
     * @param palabraAbuscar el array que contiene los datos a buscar Objeto a
     * recibir del cliente: codiUser,0,1,dni,datoDni,0
     * @return devuelve la listaToUsersDni, la lista que genera el select a la
     * BBDD HREntrada
     */
    public static ArrayList<Users> listaTotalUsersDni(String palabraAbuscar) {
        ArrayList<Users> listaToUsersDni = new ArrayList<>();
        try {

            String consulta = "SELECT * FROM users where dni = ? ORDER BY login";
            PreparedStatement preparedStatement = controladores.Conexion.getconexion().prepareStatement(consulta);
            preparedStatement.setString(1, palabraAbuscar);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                String login = resultSet.getString("login");
                String pass = resultSet.getString("pass");
                int numtipe = resultSet.getInt("numtipe");
                String dni = resultSet.getString("dni");
                listaToUsersDni.add(new Users(login, pass, numtipe, dni));
            }
            preparedStatement.close();
            return listaToUsersDni;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listaToUsersDni;
    }

    /**
     * Este método hace la busqueda de la tabla Users por su Login y envía los
     * datos ordenados por su nombre
     *
     * @param palabraAbuscar el array que contiene los datos a buscar Objeto a
     * recibir del cliente: codiUser,0,1,login,datoLogin,0
     * @return devuelve la listaTotalUsersLogin, la lista que genera el select a
     * la BBDD HREntrada
     */
    public static ArrayList<Users> listaTotalUsersLogin(String palabraAbuscar) {
        ArrayList<Users> listaTotalUsersLogin = new ArrayList<>();
        try {

            String consulta = "SELECT * FROM users where login = ? ORDER BY login";
            PreparedStatement preparedStatement = controladores.Conexion.getconexion().prepareStatement(consulta);
            preparedStatement.setString(1, palabraAbuscar);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                String login = resultSet.getString("login");
                String pass = resultSet.getString("pass");
                int numtipe = resultSet.getInt("numtipe");
                String dni = resultSet.getString("dni");
                listaTotalUsersLogin.add(new Users(login, pass, numtipe, dni));
            }
            preparedStatement.close();
            return listaTotalUsersLogin;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listaTotalUsersLogin;
    }

    /**
     * Este método hace la busqueda de la tabla Users por su tipo de usuario
     * admin = 0 y user = 1 envía los datos ordenados por su nombre
     *
     * @param palabraAbuscar el array que contiene los datos a buscar Objeto a
     * recibir del cliente: codiUser,0,1,NumTipe,datoNumTipe,0
     * @return devuelve la listaTotalUsersTipe, la lista que genera el select a
     * la BBDD HREntrada
     */
    public static ArrayList<Users> listaTotalUsersTipe(int palabraAbuscar) {
        ArrayList<Users> listaTotalUsersTipe = new ArrayList<>();
        try {

            String consulta = "SELECT * FROM users where numtipe = ?  ORDER BY login";
            PreparedStatement preparedStatement = controladores.Conexion.getconexion().prepareStatement(consulta);
            preparedStatement.setInt(1, palabraAbuscar);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                String login = resultSet.getString("login");
                String pass = resultSet.getString("pass");
                int numtipe = resultSet.getInt("numtipe");
                String dni = resultSet.getString("dni");
                listaTotalUsersTipe.add(new Users(login, pass, numtipe, dni));
            }
            preparedStatement.close();
            return listaTotalUsersTipe;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listaTotalUsersTipe;
    }
}
