package peticiones;

import errores.Errores;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.UnsupportedEncodingException;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import modelo.Users;

/**
 * @author Gustavo Senoráns Varela
 * @version 1.4, 15/11/2023
 * @since jdk 17
 */
public class UpdateUsers {

    /**
     * Este método conecta con la BBDD HREntrada para realizar la modificación
     * de los datos del usuario y realizar el envío de los mismo o un error
     *
     * Objeto a recibir del cliente:
     * codiUser,2,1,loginNuevo,datoLoginNuevo,passNuevo,
     * datoPassNuevo,numtipeNuevo,datoNumtipeNuevo,dni,datoDni,0
     *
     * @param crud en este caso es 2 para el update
     * @param nombreTabla el número de la tabla en este caso 1, ya que se
     * refiere al usuarios
     * @param passNuevo el nombre de la columna de la tabla nombretabla+Nuevo
     * @param datoPassNuevo el password nuevo a insertar
     * @param numtipeNuevo el nombre de la columna de la tabla nombretabla+Nuevo
     * @param datoNumtipeNuevo el tipo de usuario nuevo a insertar
     * @param login el nombre de la columna original de la tabla
     * @param datoLogin el login original del empleado
     * @param palabraAbuscar al array que contiene los datos
     * @param palabra variable necesaria para las modificaciones
     * @param outObjeto el objeto que contiene el array
     * @param client el socket del cliente al que se le envían los datos
     * @return devuelve los datos del usuario que se ha modificado si es corecto
     * @throws IOException controla los errores
     */
    public static ArrayList<Users> updateUsers(String crud, String nombreTabla,
            String passNuevo, String datoPassNuevo,
            String numtipeNuevo, int datoNumtipeNuevo,
            String login, String datoLogin,
            String palabraAbuscar, String palabra, ObjectOutputStream outObjeto, Socket client) throws IOException {

        ArrayList<Users> users = new ArrayList<Users>();

        try {
            String consultaUsers = "SELECT * FROM users where login = ?";
            PreparedStatement psUserNuevo = controladores.Conexion.getconexion().prepareStatement(consultaUsers);
            psUserNuevo.setString(1, datoLogin);
            ResultSet rsUserNuevo = psUserNuevo.executeQuery();

            if (!rsUserNuevo.next()) {
                Errores error = new Errores();
                String erroLoginUser = error.erroLoginUser();
                System.out.println(erroLoginUser);
                outObjeto = new ObjectOutputStream(client.getOutputStream());
                outObjeto.writeObject(erroLoginUser);
                outObjeto.flush();
            } else {
                psUserNuevo.close();

                if (datoNumtipeNuevo == 0 || datoNumtipeNuevo == 1) {

                    String hashedPassword = hashPassword(datoPassNuevo);
                    String consulta = "UPDATE users SET pass = ?, numtipe = ? WHERE login = ?";
                    PreparedStatement preparedStatement = controladores.Conexion.getconexion().prepareStatement(consulta);
                    preparedStatement.setString(1, hashedPassword);
                    preparedStatement.setInt(2, datoNumtipeNuevo);
                    preparedStatement.setString(3, datoLogin);

                    preparedStatement.executeUpdate();
                    preparedStatement.close();

                    Users nuevoUsers = new Users(rsUserNuevo.getString("login"), datoPassNuevo, datoNumtipeNuevo, rsUserNuevo.getString("dni"));
                    users.add(nuevoUsers);
                } else {
                    Errores error = new Errores();
                    String errorInsertEmpleadoNumtipe = error.errorInsertEmpleadoNumtipe();
                    System.out.println(errorInsertEmpleadoNumtipe);
                    outObjeto = new ObjectOutputStream(client.getOutputStream());
                    outObjeto.writeObject(errorInsertEmpleadoNumtipe);
                    outObjeto.flush();
                }
            }
        } catch (SQLException e) {
            Errores error = new Errores();
            String erroLoginUser = error.erroLoginUser();
            System.out.println(erroLoginUser);
            outObjeto = new ObjectOutputStream(client.getOutputStream());
            outObjeto.writeObject(erroLoginUser);
            outObjeto.flush();
        }
        return users;
    }

    private static String hashPassword(String password) throws UnsupportedEncodingException {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] encodedHash = md.digest(password.getBytes(StandardCharsets.UTF_8));

            StringBuilder hexString = new StringBuilder(2 * encodedHash.length);
            for (byte b : encodedHash) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) {
                    hexString.append('0');
                }
                hexString.append(hex);
            }

            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            // Handle exception (e.g., log or throw it)
            e.printStackTrace();
            return null;
        }
    }

}
