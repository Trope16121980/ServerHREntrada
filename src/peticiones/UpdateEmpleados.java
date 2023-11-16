package peticiones;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import errores.Errores;
import modelo.*;

/**
 * @author Gustavo Senoráns Varela
 * @version 1.4, 15/11/2023
 * @since jdk 17
 */
public class UpdateEmpleados {

    /**
     * Este método conecta con la BBDD HREntrada para realizar la modificación
     * de los datos del empleado y realizar el envío de los mismo o un error
     * 
     * codiUser,2,0,dniNuevo,datoDniNuevo,nomNuevo,datoNomNuevo,apellidoNuevo,datoApellidoNuevo
     * nomempresaNuevo,datoNomempresaNuevo,departamentNuevo,datoDepartamentNuevo,codicarNuevo,
     * datoCodicardNuevo,mailNuevo,datoMailNuevo,telephonNuevo,datoTelephonNuevo,dni,datoDni,0
     *
     * @param crud en este caso es 2 para el update
     * @param nombreTabla el número de la tabla en este caso 0, ya que se
     * refiere al empleado
     * @param dniNuevo el nombre de la columna de la tabla nombretabla+Nuevo
     * @param datoDniNuevo el dni nuevo del empleado a modificar
     * @param nomNuevo el nombre de la columna de la tabla nombretabla+Nuevo
     * @param datoNomNuevo el nombre nuevo del empleado a modificar
     * @param apellidoNuevo el nombre de la columna de la tabla
     * nombretabla+Nuevo
     * @param datoApellidoNuevo el apellido nuevo del empleado a modificar
     * @param nomempresaNuevo el nombre de la columna de la tabla
     * nombretabla+Nuevo
     * @param datoNomempresaNuevo el nombre nuevo de la empresa del empleado a
     * modificar
     * @param departamentNuevo el nombre de la columna de la tabla
     * nombretabla+Nuevo
     * @param datoDepartamentNuevo el departamento nuevo del empleado a
     * modificar
     * @param codicardNuevo el nombre de la columna de la tabla
     * nombretabla+Nuevo
     * @param datoCodicardNuevo el codigo de tarjeta nuevo del empleado a
     * modificar
     * @param mailNuevo el nombre de la columna de la tabla nombretabla+Nuevo
     * @param datoMailNuevo el mail nuevo del empleado a modificar
     * @param telephonNuevo el nombre de la columna de la tabla
     * nombretabla+Nuevo
     * @param datoTelephonNuevo el número de teléfono nuevo del empleado a
     * modificar
     * @param dni el nombre de la columna original de la tabla
     * @param datoDni el dni original del empleado a modificar
     * @param palabraAbuscar al array que contiene los datos
     * @param palabra variable necesaria para las modificaciones
     * @param outObjeto el objeto que contiene el array
     * @param client el socket del cliente al que se le envían los datos
     * @return devuelve los datos del empleado que se ha modificado si es
     * corecto
     * @throws IOException controla los errores
     */
    public static ArrayList<Empleados> updateEmpleados(String crud, String nombreTabla,
            String dniNuevo, String datoDniNuevo,
            String nomNuevo, String datoNomNuevo,
            String apellidoNuevo, String datoApellidoNuevo,
            String nomempresaNuevo, String datoNomempresaNuevo,
            String departamentNuevo, String datoDepartamentNuevo,
            String codicardNuevo, int datoCodicardNuevo,
            String mailNuevo, String datoMailNuevo,
            String telephonNuevo, int datoTelephonNuevo,
            String dni, String datoDni,
            String palabraAbuscar, String palabra, ObjectOutputStream outObjeto, Socket client) throws IOException {

        ArrayList<Empleados> empleados = new ArrayList<Empleados>();

        try {
            String nombreNuevo = "SELECT * FROM empleados where dni = ?";
            PreparedStatement psNom = controladores.Conexion.getconexion().prepareStatement(nombreNuevo);
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
                    PreparedStatement psDniNueno = controladores.Conexion.getconexion().prepareStatement(consultaDni);
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
                            String consultaNomempresa = "SELECT * FROM empresa where nom = ?";
                            PreparedStatement psNomempresaNuevo = controladores.Conexion.getconexion().prepareStatement(consultaNomempresa);
                            psNomempresaNuevo.setString(1, datoNomempresaNuevo);
                            ResultSet rsNomempresaNuevo = psNomempresaNuevo.executeQuery();
                            if (!rsNomempresaNuevo.next()) {
                                Errores error = new Errores();
                                String erroNomEmpresa = error.erroNomEmpresa();
                                System.out.println(erroNomEmpresa);
                                outObjeto = new ObjectOutputStream(client.getOutputStream());
                                outObjeto.writeObject(erroNomEmpresa);
                                outObjeto.flush();
                            } else {
                                psNomempresaNuevo.close();

                                try {
                                    String consultaCodicard = "SELECT * FROM empleados where codicard = ?";
                                    PreparedStatement psCodicardNuevo = controladores.Conexion.getconexion().prepareStatement(consultaCodicard);
                                    psCodicardNuevo.setInt(1, datoCodicardNuevo);
                                    ResultSet rsCodicardNuevo = psCodicardNuevo.executeQuery();
                                    if (rsCodicardNuevo.next() && !String.valueOf(datoCodicardNuevo).equals(rsCodicardNuevo.getString("codicard"))) {
                                        Errores error = new Errores();
                                        String errorInsertEmpleadoCodicard = error.errorInsertEmpleadoCodicard();
                                        System.out.println(errorInsertEmpleadoCodicard);
                                        outObjeto = new ObjectOutputStream(client.getOutputStream());
                                        outObjeto.writeObject(errorInsertEmpleadoCodicard);
                                        outObjeto.flush();
                                    } else {
                                        psCodicardNuevo.close();

                                        String consulta = "UPDATE empleados SET dni = ?, nom = ?, apellido = ?, nomempresa = ?, departament = ?, codicard = ?, mail = ?, telephon = ? WHERE dni = ?";
                                        PreparedStatement preparedStatement = controladores.Conexion.getconexion().prepareStatement(consulta);
                                        preparedStatement.setString(1, datoDniNuevo);
                                        preparedStatement.setString(2, datoNomNuevo);
                                        preparedStatement.setString(3, datoApellidoNuevo);
                                        preparedStatement.setString(4, datoNomempresaNuevo);
                                        preparedStatement.setString(5, datoDepartamentNuevo);
                                        preparedStatement.setInt(6, datoCodicardNuevo);
                                        preparedStatement.setString(7, datoMailNuevo);
                                        preparedStatement.setInt(8, datoTelephonNuevo);
                                        preparedStatement.setString(9, datoDni);

                                        preparedStatement.executeUpdate();
                                        preparedStatement.close();

                                        Empleados nuevoEmpleado = new Empleados(datoDniNuevo, datoNomNuevo,
                                                datoApellidoNuevo, datoNomempresaNuevo,
                                                datoDepartamentNuevo, datoCodicardNuevo,
                                                datoMailNuevo, datoTelephonNuevo);
                                        empleados.add(nuevoEmpleado);
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
                            String erroNomEmpresa = error.erroNomEmpresa();
                            System.out.println(erroNomEmpresa);
                            outObjeto = new ObjectOutputStream(client.getOutputStream());
                            outObjeto.writeObject(erroNomEmpresa);
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

        return empleados;
    }

}
