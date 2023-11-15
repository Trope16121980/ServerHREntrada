package peticiones;

import modelo.*;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Random;

import errores.Errores;

/**
 * @author Gustavo Senoráns Varela
 * @version 1.4, 15/11/2023
 * @since jdk 17
 */
public class InsertEmpleados {

    /**
     * Conecta con la BBDD HREntrad para realizar la inserción de los datos del
     * empleado y envia los datos al cliente o un mensaje de error
     * Objeto a recibir del cliente:  codiUser,1,0,dni,datoDni,nom,datoNom,apellido,datoApellido,nomempresa
     * ,datoNomempresa,departament,datoDepartament,codicard,datoCodicard,mail,datoMail,telephon,datoTelephon,0
     * 
     * @param crud en este caso es 1 para el insert
     * @param nombreTabla el número de la tabla en este caso 0, ya que se
     * refiere al empleado
     * @param dni el nombre de la columna de la tabla
     * @param datoDni el dni del empleado a insertar
     * @param nom el nombre de la columna de la tabla
     * @param datoNom el nombre del empleado a insertar
     * @param apellido el nombre de la columna de la tabla
     * @param datoApellido el apellido del empleado a insertar
     * @param nomempresa el nombre de la columna de la tabla
     * @param datoNomempresa a el nombre de la empresa con la que se relaciona
     * el empleado a insertar
     * @param departament el nombre de la columna de la tabla
     * @param datoDepartament el departamento del empleado a insertar
     * @param codicard el nombre de la columna de la tabla
     * @param datoCodicard el codigo de tarjeta del empleado a insertar
     * @param mail el nombre de la columna de la tabla
     * @param datoMail el mail del empleado a insertar
     * @param telephon el nombre de la columna de la tabla
     * @param datoTelephon el número de teléfono del empleado a insertar
     * @param palabraAbuscar al array que contiene los datos
     * @param outObjeto el objeto que contiene el array
     * @param client el socket del cliente al que se le envían los datos
     * @return devuelve los datos del empleado que se ha insertado si es corecto
     * @throws IOException controla los errores
     */
    public static ArrayList<Empleados> insertEmpleados(String crud, String nombreTabla, String dni, String datoDni,
            String nom, String datoNom, String apellido, String datoApellido, String nomempresa, String datoNomempresa,
            String departament, String datoDepartament, String codicard, int datoCodicard, String mail,
            String datoMail, String telephon, String datoTelephon, String palabraAbuscar, ObjectOutputStream outObjeto,
            Socket client) throws IOException {

        ArrayList<Empleados> insertEmpleados = new ArrayList<>();
        try {

            String dniEmpleado = "SELECT * FROM empleados where dni = ?";
            PreparedStatement prDni = conn.Conexion.getconexion().prepareStatement(dniEmpleado);
            prDni.setString(1, datoDni);
            ResultSet rsDni = prDni.executeQuery();
            if (rsDni.next()) {
                Errores error = new Errores();
                String errorInsertEmpleadoDni = error.errorInsertEmpleadoDni();
                System.out.println(errorInsertEmpleadoDni);
                outObjeto = new ObjectOutputStream(client.getOutputStream());
                outObjeto.writeObject(errorInsertEmpleadoDni);
                outObjeto.flush();
            } else {
                try {
                    String nomempresaEmpleado = "SELECT * FROM empresa where nom = ?";
                    PreparedStatement psNomempresa = conn.Conexion.getconexion().prepareStatement(nomempresaEmpleado);
                    psNomempresa.setString(1, datoNomempresa);
                    ResultSet rsNomempresa = psNomempresa.executeQuery();
                    if (!rsNomempresa.next()) {
                        Errores error = new Errores();
                        String errorInsertEmpleadoNomempresa = error.errorInsertEmpleadoNomempresa();
                        System.out.println(errorInsertEmpleadoNomempresa);
                        outObjeto = new ObjectOutputStream(client.getOutputStream());
                        outObjeto.writeObject(errorInsertEmpleadoNomempresa);
                        outObjeto.flush();
                    } else {
                        try {

                            if (datoCodicard == 0) {
                                Random random = new Random();
                                datoCodicard = random.nextInt(100000);
                            }

                            String codicardEmpleado = "SELECT * FROM empleados where codicard = ?";
                            PreparedStatement psCodicard = conn.Conexion.getconexion()
                                    .prepareStatement(codicardEmpleado);
                            psCodicard.setInt(1, datoCodicard);
                            ResultSet rsCodicard = psCodicard.executeQuery();
                            if (rsCodicard.next()) {
                                Errores error = new Errores();
                                String errorInsertEmpleadoCodicard = error.errorInsertEmpleadoCodicard();
                                System.out.println(errorInsertEmpleadoCodicard);
                                outObjeto = new ObjectOutputStream(client.getOutputStream());
                                outObjeto.writeObject(errorInsertEmpleadoCodicard);
                                outObjeto.flush();
                            } else {

                                String insert = "INSERT INTO empleados (dni, nom, apellido, nomempresa, departament, codicard, mail, telephon) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
                                PreparedStatement preparedStatement = conn.Conexion.getconexion()
                                        .prepareStatement(insert);
                                preparedStatement.setString(1, datoDni);
                                preparedStatement.setString(2, datoNom);
                                preparedStatement.setString(3, datoApellido);
                                preparedStatement.setString(4, datoNomempresa);
                                preparedStatement.setString(5, datoDepartament);
                                preparedStatement.setInt(6, datoCodicard);
                                preparedStatement.setString(7, datoMail);
                                preparedStatement.setInt(8, Integer.parseInt(datoTelephon));

                                preparedStatement.executeUpdate();
                                preparedStatement.close();

                                Empleados nuevoEmpleado = new Empleados(datoDni, datoNom, datoApellido, datoNomempresa,
                                        datoDepartament, datoCodicard, datoMail,
                                        Integer.parseInt(datoTelephon));
                                insertEmpleados.add(nuevoEmpleado);
                                return insertEmpleados;

                            }
                        } catch (SQLException e) {
                            Errores error = new Errores();
                            String errorInsertEmpleadoCodicard = error.errorInsertEmpleadoCodicard();
                            System.out.println(errorInsertEmpleadoCodicard);
                            outObjeto = new ObjectOutputStream(client.getOutputStream());
                            outObjeto.writeObject(errorInsertEmpleadoCodicard);
                            outObjeto.flush();
                        }
                    }
                } catch (SQLException e) {
                    Errores error = new Errores();
                    String errorInsertEmpleadoNomempresa = error.errorInsertEmpleadoNomempresa();
                    System.out.println(errorInsertEmpleadoNomempresa);
                    outObjeto = new ObjectOutputStream(client.getOutputStream());
                    outObjeto.writeObject(errorInsertEmpleadoNomempresa);
                    outObjeto.flush();
                }
            }
        } catch (SQLException e) {
            Errores error = new Errores();
            String errorInsertEmpleadoDni = error.errorInsertEmpleadoDni();
            System.out.println(errorInsertEmpleadoDni);
            outObjeto = new ObjectOutputStream(client.getOutputStream());
            outObjeto.writeObject(errorInsertEmpleadoDni);
            outObjeto.flush();
        }
        return insertEmpleados;
    }
}
