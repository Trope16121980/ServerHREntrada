package peticiones;

/**
 *
 * @author gsenorans
 */
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import errores.Errores;
import modelo.*;

public class UpdateEmpleados {

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
                            String consultaNomempresa = "SELECT * FROM empresa where nom = ?";
                            PreparedStatement psNomempresaNuevo = conn.Conexion.getconexion().prepareStatement(consultaNomempresa);
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
                                    PreparedStatement psCodicardNuevo = conn.Conexion.getconexion().prepareStatement(consultaCodicard);
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
                                        PreparedStatement preparedStatement = conn.Conexion.getconexion().prepareStatement(consulta);
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
