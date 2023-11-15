package peticiones;

import errores.Errores;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
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
     * Objeto a recibir del cliente:  codiUser,2,1,loginNuevo,datoLoginNuevo,passNuevo,
     * datoPassNuevo,numtipeNuevo,datoNumtipeNuevo,dni,datoDni,0
     *
     * @param crud en este caso es 2 para el update
     * @param nombreTabla el número de la tabla en este caso 1, ya que se
     * refiere al usuarios
     * @param loginNuevo el nombre de la columna de la tabla nombretabla+Nuevo
     * @param datoLoginNuevo el login nuevo a insertar
     * @param passNuevo el nombre de la columna de la tabla nombretabla+Nuevo
     * @param datoPassNuevo el password nuevo a insertar
     * @param numtipeNuevo el nombre de la columna de la tabla nombretabla+Nuevo
     * @param datoNumtipeNuevo el tipo de usuario nuevo a insertar
     * @param dniNuevo el nombre de la columna de la tabla nombretabla+Nuevo
     * @param datoDniNuevo el dni nuevo a insertar
     * @param dni el nombre de la columna original de la tabla
     * @param datoDni el dni original del usuario a modificar
     * @param palabraAbuscar al array que contiene los datos
     * @param palabra variable necesaria para las modificaciones
     * @param outObjeto el objeto que contiene el array
     * @param client el socket del cliente al que se le envían los datos
     * @return devuelve los datos del usuario que se ha modificado si es corecto
     * @throws IOException controla los errores
     */
    public static ArrayList<Users> updateUsers(String crud, String nombreTabla,
            String loginNuevo, String datoLoginNuevo,
            String passNuevo, String datoPassNuevo,
            String numtipeNuevo, int datoNumtipeNuevo,
            String dniNuevo, String datoDniNuevo,
            String dni, String datoDni,
            String palabraAbuscar, String palabra, ObjectOutputStream outObjeto, Socket client) throws IOException {

        ArrayList<Users> users = new ArrayList<Users>();

        try {
            String nombreNuevo = "SELECT * FROM users where dni = ?";
            PreparedStatement psNom = conn.Conexion.getconexion().prepareStatement(nombreNuevo);
            psNom.setString(1, datoDni);
            ResultSet rsNom = psNom.executeQuery();
            if (!rsNom.next()) {
                Errores error = new Errores();
                String errorDni = error.errorDni();
                System.out.println(errorDni);
                outObjeto = new ObjectOutputStream(client.getOutputStream());
                outObjeto.writeObject(errorDni);
                outObjeto.flush();
            } else {
                psNom.close();
                try {
                    String consultaDni = "SELECT * FROM empleados where dni = ?";
                    PreparedStatement psDniNueno = conn.Conexion.getconexion().prepareStatement(consultaDni);
                    psDniNueno.setString(1, datoDniNuevo);
                    ResultSet rsDniNuevo = psDniNueno.executeQuery();
                    if (rsDniNuevo.next() && !datoDniNuevo.equals(datoDni)) {
                        Errores error = new Errores();
                        String errorUpdateEmpleadoDni = error.errorUpdateEmpleadoDni();
                        System.out.println(errorUpdateEmpleadoDni);
                        outObjeto = new ObjectOutputStream(client.getOutputStream());
                        outObjeto.writeObject(errorUpdateEmpleadoDni);
                        outObjeto.flush();
                    } else {
                        psDniNueno.close();

                        try {
                            String consultaNomempresa = "SELECT * FROM users where login = ?";
                            PreparedStatement psNomempresaNuevo = conn.Conexion.getconexion().prepareStatement(consultaNomempresa);
                            psNomempresaNuevo.setString(1, datoLoginNuevo);
                            ResultSet rsNomempresaNuevo = psNomempresaNuevo.executeQuery();
                            if (!rsNomempresaNuevo.next() && !datoLoginNuevo.equals(rsNomempresaNuevo.getString("login"))) {
                                Errores error = new Errores();
                                String erroNomEmpresa = error.erroNomEmpresa();
                                System.out.println(erroNomEmpresa);
                                outObjeto = new ObjectOutputStream(client.getOutputStream());
                                outObjeto.writeObject(erroNomEmpresa);
                                outObjeto.flush();
                            } else {
                                psNomempresaNuevo.close();

                                String consulta = "UPDATE users SET login = ?, pass = ?, numtipe = ?, dni = ? WHERE dni = ?";
                                PreparedStatement preparedStatement = conn.Conexion.getconexion().prepareStatement(consulta);
                                preparedStatement.setString(1, datoLoginNuevo);
                                preparedStatement.setString(2, datoPassNuevo);
                                preparedStatement.setInt(3, datoNumtipeNuevo);
                                preparedStatement.setString(4, datoDniNuevo);
                                preparedStatement.setString(5, datoDni);

                                preparedStatement.executeUpdate();
                                preparedStatement.close();

                                Users nuevoUsers = new Users(datoLoginNuevo,
                                        datoPassNuevo, datoNumtipeNuevo,
                                        datoDniNuevo);
                                users.add(nuevoUsers);

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
                    String errorUpdateEmpleadoDni = error.errorUpdateEmpleadoDni();
                    System.out.println(errorUpdateEmpleadoDni);
                    outObjeto = new ObjectOutputStream(client.getOutputStream());
                    outObjeto.writeObject(errorUpdateEmpleadoDni);
                    outObjeto.flush();
                }
            }
        } catch (SQLException e) {
            Errores error = new Errores();
            String errorUpdateEmpresa = error.errorUpdateEmpresa();
            System.out.println(errorUpdateEmpresa);
            outObjeto = new ObjectOutputStream(client.getOutputStream());
            outObjeto.writeObject(errorUpdateEmpresa);
            outObjeto.flush();
        }

        return users;
    }

}
