package peticiones;

import modelo.Users;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author Gustavo Senoráns Varela
 * @version 1.4, 15/11/2023
 * @since jdk 17
 */
public class Login {

    /**
     * Este método conecta con la BBDD HREntrada para realizar la busqueda de
     * los datos del usuario
     * Objeto a recibir del cliente:  login,pass
     *
     * @param login login del usuari@ a conectar
     * @param pass password del usuri@ a conectar
     * @return devuelve los datos del usuario a conectar
     */
    public Users comprobarCredencialesBD(String login, String pass) {
        Users user = null;
        String dni = "-1";
        String numtipe = "-1";
        String codigo = "-1";
        try {

            String consulta = "SELECT * FROM users where login = ? and  pass =?";
            PreparedStatement preparedStatement = conn.Conexion.getconexion().prepareStatement(consulta);
            preparedStatement.setString(1, login);
            preparedStatement.setString(2, pass);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                dni = resultSet.getString("dni");
                numtipe = resultSet.getString("numtipe");
                user = new Users(login, pass, Integer.parseInt(numtipe), dni, Integer.parseInt(codigo));
            }
            preparedStatement.close();
            return user;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }
}
