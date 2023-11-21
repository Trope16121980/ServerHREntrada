package peticiones;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import errores.Errores;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import modelo.Users;

/**
 * @author Gustavo Senoráns Varela
 * @version 1.4, 15/11/2023
 * @since jdk 17
 */
public class InsertUsers {

    /**
     * Conecta con la BBDD HREntrad para realizar la inserción de los datos del
     * usuario y envia los datos al cliente o un mensaje de error Objeto a
     * recibir del cliente:
     * codiUser,1,1,login,datoLogin,pass,datoPass,numTipe,datooNumTipe,dni,datoDni,0
     *
     * @param crud en este caso es 1 para el insert
     * @param nombreTabla el número de la tabla en este caso 1, ya que se
     * refiere al usuario
     * @param login el nombre de la columna de la tabla
     * @param datoLogin el login del usuario que quiere insertar
     * @param pass el nombre de la columna de la tabla
     * @param datoPass el password del usuario que quiere insertar
     * @param numTipe el nombre de la columna de la tabla
     * @param datoNumTipe el tipo de usuario que quiere insertar
     * @param dni el nombre de la columna de la tabla
     * @param datoDni el dni del empleado que quiere insertar para que u pueda
     * iniciar sesión como admin o user generico
     * @param palabraAbuscar al array que contiene los datos
     * @param outObjeto el objeto que contiene el array
     * @param client el socket del cliente al que se le envían los datos
     * @return devuelve los datos del usuario que se ha insertado si es corecto
     * @throws IOException controla los errores
     */
    public static ArrayList<Users> insertUser(String crud, String nombreTabla, String login, String datoLogin,
            String pass, String datoPass, String numTipe, String datoNumTipe, String dni, String datoDni,
            String palabraAbuscar, ObjectOutputStream outObjeto, Socket client) throws IOException {

        ArrayList<Users> insertUser = new ArrayList<>();

        try {
            String consulta = "SELECT * FROM empleados where dni = ?";
            PreparedStatement preparedStatement = controladores.Conexion.getconexion().prepareStatement(consulta);
            preparedStatement.setString(1, datoDni);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (!resultSet.next()) {
                Errores error = new Errores();
                String errorInsertUsers = error.errorInsertUsers();
                System.out.println(errorInsertUsers);
                outObjeto = new ObjectOutputStream(client.getOutputStream());
                outObjeto.writeObject(errorInsertUsers);
                outObjeto.flush();
            } else {

                try {
                    String dniUser = "SELECT * FROM users where dni = ?";
                    PreparedStatement psDni = controladores.Conexion.getconexion().prepareStatement(dniUser);
                    psDni.setString(1, datoDni);
                    ResultSet rsDni = psDni.executeQuery();
                    if (rsDni.next()) {
                        Errores error = new Errores();
                        String errorInsertUsersDni = error.errorInsertUsersDni();
                        System.out.println(errorInsertUsersDni);
                        outObjeto = new ObjectOutputStream(client.getOutputStream());
                        outObjeto.writeObject(errorInsertUsersDni);
                        outObjeto.flush();
                    } else {

                        try {
                            String loginUser = "SELECT * FROM users where login = ?";
                            PreparedStatement psLogin = controladores.Conexion.getconexion().prepareStatement(loginUser);
                            psLogin.setString(1, datoLogin);
                            ResultSet rsLogin = psLogin.executeQuery();
                            if (rsLogin.next()) {
                                Errores error = new Errores();
                                String errorInsertUsersLogin = error.errorInsertUsersLogin();
                                System.out.println(errorInsertUsersLogin);
                                outObjeto = new ObjectOutputStream(client.getOutputStream());
                                outObjeto.writeObject(errorInsertUsersLogin);
                                outObjeto.flush();
                            } else {

                                int numTipeValue = Integer.parseInt(datoNumTipe);
                                if (numTipeValue == 0 || numTipeValue == 1) {

                                    String hashedPassword = hashPassword(datoPass);

                                    String insert = "INSERT INTO users (login, pass, numtipe, dni) VALUES (?, ?, ?, ?)";
                                    preparedStatement = controladores.Conexion.getconexion().prepareStatement(insert);
                                    preparedStatement.setString(1, datoLogin);
                                    preparedStatement.setString(2, hashedPassword);
                                    preparedStatement.setInt(3, Integer.parseInt(datoNumTipe));
                                    preparedStatement.setString(4, datoDni);

                                    preparedStatement.executeUpdate();
                                    preparedStatement.close();

                                    Users nuevoUser = new Users(datoLogin, datoPass, Integer.parseInt(datoNumTipe),
                                            datoDni);
                                    insertUser.add(nuevoUser);

                                    return insertUser;

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
                            String errorInsertUsersLogin = error.errorInsertUsersLogin();
                            System.out.println(errorInsertUsersLogin);
                            outObjeto = new ObjectOutputStream(client.getOutputStream());
                            outObjeto.writeObject(errorInsertUsersLogin);
                            outObjeto.flush();
                        }
                    }
                } catch (SQLException e) {
                    Errores error = new Errores();
                    String errorInsertUsersDni = error.errorInsertUsersDni();
                    System.out.println(errorInsertUsersDni);
                    outObjeto = new ObjectOutputStream(client.getOutputStream());
                    outObjeto.writeObject(errorInsertUsersDni);
                    outObjeto.flush();
                }
            }
        } catch (SQLException e) {
            Errores error = new Errores();
            String errorInsertUsers = error.errorInsertUsers();
            System.out.println(errorInsertUsers);
            outObjeto = new ObjectOutputStream(client.getOutputStream());
            outObjeto.writeObject(errorInsertUsers);
            outObjeto.flush();
        }
        return insertUser;
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
