package peticiones;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import errores.Errores;
import fecha.Fechas;
import modelo.Jornada;

/**
 * @author Gustavo Senoráns Varela
 * @version 1.4, 15/11/2023
 * @since jdk 17
 */
public class InsertJornada {

    static Fechas fecha = new Fechas();

    /**
     * Conecta con la BBDD HREntrada para realizar la inserción de los datos de
     * la jornada y envia los datos al cliente o un mensaje de error
     * Objeto a recibir del cliente:  codiUser,1,3,dni,datoDni,0
     *
     * @param crud en este caso es 1 para el insert
     * @param nombreTabla el número de la tabla en este caso 3, ya que se
     * refiere a la jornada
     * @param dni el nombre de la columna de la tabla
     * @param datoDni el dni del empleado que quiere iniciar jornada
     * @param palabraAbuscar al array que contiene los datos
     * @param outObjeto el objeto que contiene el array
     * @param client el socket del cliente al que se le envían los datos
     * @return devuelve los datos de la jornada que se ha insertado si es
     * corecto
     * @throws IOException controla los errores
     */
    public static ArrayList<Jornada> insertJornada(String crud, String nombreTabla, String dni, String datoDni,
            String palabraAbuscar, ObjectOutputStream outObjeto, Socket client) throws IOException {

        ArrayList<Jornada> insertJornada = new ArrayList<>();
        try {
            String consulta = "SELECT * FROM empleados where dni = ?";
            PreparedStatement preparedStatement = controladores.Conexion.getconexion().prepareStatement(consulta);
            preparedStatement.setString(1, datoDni);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (!resultSet.next()) {
                Errores error = new Errores();
                String errorDni = error.errorDni();
                System.out.println(errorDni);
                outObjeto = new ObjectOutputStream(client.getOutputStream());
                outObjeto.writeObject(errorDni);
                outObjeto.flush();

            } else {

                try {
                    String horasalidaNull = "SELECT * FROM jornada where dni = ? and horasalida = ?";
                    PreparedStatement psHorasalida = controladores.Conexion.getconexion().prepareStatement(horasalidaNull);
                    psHorasalida.setString(1, datoDni);
                    psHorasalida.setString(2, "nulo");
                    ResultSet rsHorasalida = psHorasalida.executeQuery();
                    if (rsHorasalida.next()) {
                        Errores error = new Errores();
                        String errorInsertJornadaDentro = error.errorInsertJornadaDentro();
                        System.out.println(errorInsertJornadaDentro);
                        outObjeto = new ObjectOutputStream(client.getOutputStream());
                        outObjeto.writeObject(errorInsertJornadaDentro);
                        outObjeto.flush();
                    } else {
                        datoDni = resultSet.getString("dni");
                        String datoNom = resultSet.getString("nom");
                        String datoApellido = resultSet.getString("apellido");
                        String datoCodicard = resultSet.getString("codicard");
                        String datoHoraentrada = fecha.hora();
                        String datoHorasalida = "nulo";
                        String datoTotal = "nulo";
                        String datoFecha = fecha.fecha_Jornada();

                        String insert = "INSERT INTO jornada (dni,nom,apellido,codicard,horaentrada,horasalida,total,fecha) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
                        preparedStatement = controladores.Conexion.getconexion().prepareStatement(insert);
                        preparedStatement.setString(1, datoDni);
                        preparedStatement.setString(2, datoNom);
                        preparedStatement.setString(3, datoApellido);
                        preparedStatement.setInt(4, Integer.parseInt(datoCodicard));
                        preparedStatement.setString(5, datoHoraentrada);
                        preparedStatement.setString(6, datoHorasalida);
                        preparedStatement.setString(7, datoTotal);
                        preparedStatement.setString(8, datoFecha);

                        preparedStatement.executeUpdate();
                        preparedStatement.close();

                        Jornada nuevaJornada = new Jornada(datoDni, datoNom, datoApellido,
                                datoCodicard, datoHoraentrada, datoHorasalida, datoTotal, datoFecha);
                        insertJornada.add(nuevaJornada);

                    }
                } catch (SQLException e) {
                    Errores error = new Errores();
                    String errorInsertJornadaDentro = error.errorInsertJornadaDentro();
                    System.out.println(errorInsertJornadaDentro);
                    outObjeto = new ObjectOutputStream(client.getOutputStream());
                    outObjeto.writeObject(errorInsertJornadaDentro);
                    outObjeto.flush();
                }
            }
        } catch (SQLException e) {
            Errores error = new Errores();
            String errorDni = error.errorDni();
            System.out.println(errorDni);
            outObjeto = new ObjectOutputStream(client.getOutputStream());
            outObjeto.writeObject(errorDni);
            outObjeto.flush();
        }
        return insertJornada;

    }

    /**
     * Conecta con la BBDD HREntrada para realizar la inserción de los datos de
     * la jornada y envia los datos al cliente o un mensaje de error
     * Objeto a recibir del cliente:  codiUser,1,3,codicard,datoCodicard,0
     *
     * @param crud en este caso es 1 para el insert
     * @param nombreTabla el número de la tabla en este caso 3, ya que se
     * refiere a la jornada
     * @param codicard el nombre de la columna de la tabla
     * @param datoCodicard el dni del empleado que quiere iniciar jornada
     * @param palabraAbuscar al array que contiene los datos
     * @param outObjeto el objeto que contiene el array
     * @param client el socket del cliente al que se le envían los datos
     * @return devuelve los datos de la jornada que se ha insertado si es
     * corecto
     * @throws IOException controla los errores
     */
    public static ArrayList<Jornada> insertJornadaCodicard(String crud, String nombreTabla, String codicard, String datoCodicard,
            String palabraAbuscar, ObjectOutputStream outObjeto, Socket client) throws IOException {

        ArrayList<Jornada> insertJornadaCodicard = new ArrayList<>();
        try {
            String consulta = "SELECT * FROM empleados where codicard = ?";
            PreparedStatement preparedStatement = controladores.Conexion.getconexion().prepareStatement(consulta);
            preparedStatement.setString(1, datoCodicard);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (!resultSet.next()) {
                Errores error = new Errores();
                String erroCodicard = error.erroCodicard();
                System.out.println(erroCodicard);
                outObjeto = new ObjectOutputStream(client.getOutputStream());
                outObjeto.writeObject(erroCodicard);
                outObjeto.flush();

            } else {

                try {
                    String horasalidaNull = "SELECT * FROM jornada where codicard = ? and horasalida = ?";
                    PreparedStatement psHorasalida = controladores.Conexion.getconexion().prepareStatement(horasalidaNull);
                    psHorasalida.setString(1, datoCodicard);
                    psHorasalida.setString(2, "nulo");
                    ResultSet rsHorasalida = psHorasalida.executeQuery();
                    if (rsHorasalida.next()) {
                        Errores error = new Errores();
                        String errorInsertJornadaDentro = error.errorInsertJornadaDentro();
                        System.out.println(errorInsertJornadaDentro);
                        outObjeto = new ObjectOutputStream(client.getOutputStream());
                        outObjeto.writeObject(errorInsertJornadaDentro);
                        outObjeto.flush();
                    } else {
                        String datoDni = resultSet.getString("dni");
                        String datoNom = resultSet.getString("nom");
                        String datoApellido = resultSet.getString("apellido");
                        datoCodicard = resultSet.getString("codicard");
                        String datoHoraentrada = fecha.hora();
                        String datoHorasalida = "nulo";
                        String datoTotal = "nulo";
                        String datoFecha = fecha.fecha_Jornada();

                        String insert = "INSERT INTO jornada (dni,nom,apellido,codicard,horaentrada,horasalida,total,fecha) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
                        preparedStatement = controladores.Conexion.getconexion().prepareStatement(insert);
                        preparedStatement.setString(1, datoDni);
                        preparedStatement.setString(2, datoNom);
                        preparedStatement.setString(3, datoApellido);
                        preparedStatement.setString(4, datoCodicard);
                        preparedStatement.setString(5, datoHoraentrada);
                        preparedStatement.setString(6, datoHorasalida);
                        preparedStatement.setString(7, datoTotal);
                        preparedStatement.setString(8, datoFecha);

                        preparedStatement.executeUpdate();
                        preparedStatement.close();

                        Jornada nuevaJornadaCodicard = new Jornada(datoDni, datoNom, datoApellido,
                                datoCodicard, datoHoraentrada, datoHorasalida, datoTotal, datoFecha);
                        insertJornadaCodicard.add(nuevaJornadaCodicard);

                    }
                } catch (SQLException e) {
                    Errores error = new Errores();
                    String errorInsertJornadaDentro = error.errorInsertJornadaDentro();
                    System.out.println(errorInsertJornadaDentro);
                    outObjeto = new ObjectOutputStream(client.getOutputStream());
                    outObjeto.writeObject(errorInsertJornadaDentro);
                    outObjeto.flush();
                }
            }
        } catch (SQLException e) {
            Errores error = new Errores();
            String erroCodicard = error.erroCodicard();
            System.out.println(erroCodicard);
            outObjeto = new ObjectOutputStream(client.getOutputStream());
            outObjeto.writeObject(erroCodicard);
            outObjeto.flush();
        }
        return insertJornadaCodicard;

    }
}
