package peticiones;

import modelo.Jornada;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * @author Gustavo Senoráns Varela
 * @version 1.4, 15/11/2023
 * @since jdk 17
 */
public class Listajornada {

    /**
     * Este método hace la busqueda total de la tabla Jornada y envía los datos
     * ordenados por su nombre Objeto a recibir del cliente: codiUser,0,3,0,0,0
     *
     * @return devuelve la listaTotalJornada, todos los datos de todos los
     * empleados
     */
    public static ArrayList<Jornada> listaTotalJornada() {
        ArrayList<Jornada> listaTotalJornada = new ArrayList<>();
        try {

            String consulta = "SELECT * FROM jornada ORDER BY fecha";
            PreparedStatement preparedStatement = controladores.Conexion.getconexion().prepareStatement(consulta);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                String dni = resultSet.getString("dni");
                String nom = resultSet.getString("nom");
                String apellido = resultSet.getString("apellido");
                String codicard = resultSet.getString("codicard");
                String horaentrada = resultSet.getString("horaentrada");
                String horasalida = resultSet.getString("horasalida");
                String total = resultSet.getString("total");
                String fecha = resultSet.getString("fecha");
                listaTotalJornada.add(new Jornada(dni, nom, apellido, codicard, horaentrada, horasalida, total, fecha));

            }
            preparedStatement.close();
            return listaTotalJornada;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listaTotalJornada;
    }

    /**
     * Este método hace la busqueda total de la tabla Jornada y envía los datos
     * ordenados por su nombre
     *
     * @param palabraAbuscar el array que contiene los datos a buscar Objeto a
     * recibir del cliente: codiUser,0,3,dni,datoDni,0
     * @return devuelve la listaTotalJornadaDni, la lista que genera el select a
     * la BBDD HREntrada
     */
    public static ArrayList<Jornada> listaTotalJornadaDni(String palabraAbuscar) {
        ArrayList<Jornada> listaTotalJornadaDni = new ArrayList<>();
        try {

            String consulta = "SELECT * FROM jornada where dni = ? ORDER BY fecha";
            PreparedStatement preparedStatement = controladores.Conexion.getconexion().prepareStatement(consulta);
            preparedStatement.setString(1, palabraAbuscar);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                String dni = resultSet.getString("dni");
                String nom = resultSet.getString("nom");
                String apellido = resultSet.getString("apellido");
                String codicard = resultSet.getString("codicard");
                String horaentrada = resultSet.getString("horaentrada");
                String horasalida = resultSet.getString("horasalida");
                String total = resultSet.getString("total");
                String fecha = resultSet.getString("fecha");
                listaTotalJornadaDni.add(new Jornada(dni, nom, apellido, codicard, horaentrada, horasalida, total, fecha));
            }
            preparedStatement.close();
            return listaTotalJornadaDni;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listaTotalJornadaDni;
    }

    /**
     * Este método hace la busqueda de la tabla Jornada por su nombre de
     * empleado y envía los datos ordenados por su nombre
     *
     * @param palabraAbuscar el array que contiene los datos a buscar Objeto a
     * recibir del cliente: codiUser,0,3,nom,datoNom,0
     * @return devuelve la listaTotalJornadaNom, la lista que genera el select a
     * la BBDD HREntrada
     */
    public static ArrayList<Jornada> listaTotalJornadaNom(String palabraAbuscar) {
        ArrayList<Jornada> listaTotalJornadaNom = new ArrayList<>();
        try {

            String consulta = "SELECT * FROM jornada where nom = ? ORDER BY fecha";
            PreparedStatement preparedStatement = controladores.Conexion.getconexion().prepareStatement(consulta);
            preparedStatement.setString(1, palabraAbuscar);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                String dni = resultSet.getString("dni");
                String nom = resultSet.getString("nom");
                String apellido = resultSet.getString("apellido");
                String codicard = resultSet.getString("codicard");
                String horaentrada = resultSet.getString("horaentrada");
                String horasalida = resultSet.getString("horasalida");
                String total = resultSet.getString("total");
                String fecha = resultSet.getString("fecha");
                listaTotalJornadaNom.add(new Jornada(dni, nom, apellido, codicard, horaentrada, horasalida, total, fecha));
            }
            preparedStatement.close();
            return listaTotalJornadaNom;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listaTotalJornadaNom;
    }

    /**
     * Este método hace la busqueda de la tabla Jornada por su apellido de
     * empleado y envía los datos ordenados por su nombre
     *
     * @param palabraAbuscar el array que contiene los datos a buscar Objeto a
     * recibir del cliente: codiUser,0,3,apellido,datoApellido,0
     * @return devuelve la listaTotalJornadaApellido, la lista que genera el
     * select a la BBDD HREntrada
     */
    public static ArrayList<Jornada> listaTotalJornadaApellido(String palabraAbuscar) {
        ArrayList<Jornada> listaTotalJornadaApellido = new ArrayList<>();
        try {

            String consulta = "SELECT * FROM jornada where apellido = ? ORDER BY fecha";
            PreparedStatement preparedStatement = controladores.Conexion.getconexion().prepareStatement(consulta);
            preparedStatement.setString(1, palabraAbuscar);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                String dni = resultSet.getString("dni");
                String nom = resultSet.getString("nom");
                String apellido = resultSet.getString("apellido");
                String codicard = resultSet.getString("codicard");
                String horaentrada = resultSet.getString("horaentrada");
                String horasalida = resultSet.getString("horasalida");
                String total = resultSet.getString("total");
                String fecha = resultSet.getString("fecha");
                listaTotalJornadaApellido.add(new Jornada(dni, nom, apellido, codicard, horaentrada, horasalida, total, fecha));
            }
            preparedStatement.close();
            return listaTotalJornadaApellido;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listaTotalJornadaApellido;
    }

    /**
     * Este método hace la busqueda de la tabla Jornada por su código de tarjeta
     * de empleado y envía los datos ordenados por su nombre
     *
     * @param palabraAbuscar el array que contiene los datos a buscar Objeto a
     * recibir del cliente: codiUser,0,3,codicard,datoCodicard,0
     * @return devuelve la listaJornadaCodiCard, la lista que genera el select a
     * la BBDD HREntrada
     */
    public static ArrayList<Jornada> listaJornadaCodiCard(int palabraAbuscar) {
        ArrayList<Jornada> listaJornadaCodiCard = new ArrayList<>();
        try {

            String consulta = "SELECT * FROM jornada where codicard = ? ORDER BY fecha";
            PreparedStatement preparedStatement = controladores.Conexion.getconexion().prepareStatement(consulta);
            preparedStatement.setInt(1, palabraAbuscar);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                String dni = resultSet.getString("dni");
                String nom = resultSet.getString("nom");
                String apellido = resultSet.getString("apellido");
                String codicard = resultSet.getString("codicard");
                String horaentrada = resultSet.getString("horaentrada");
                String horasalida = resultSet.getString("horasalida");
                String total = resultSet.getString("total");
                String fecha = resultSet.getString("fecha");
                listaJornadaCodiCard.add(new Jornada(dni, nom, apellido, codicard, horaentrada, horasalida, total, fecha));
            }
            preparedStatement.close();
            return listaJornadaCodiCard;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listaJornadaCodiCard;
    }

    /**
     * Este método hace la busqueda de la tabla Jornada por su fecha de empleado
     * y envía los datos ordenados por su nombre
     *
     * @param palabraAbuscar el array que contiene los datos a buscar Objeto a
     * recibir del cliente: codiUser,0,3,fecha,datoFecha,0
     * @return devuelve la listaTotalJornadaFecha, la lista que genera el select
     * a la BBDD HREntrada
     */
    public static ArrayList<Jornada> listaTotalJornadaFecha(String palabraAbuscar) {
        ArrayList<Jornada> listaTotalJornadaFecha = new ArrayList<>();
        try {

            String consulta = "SELECT * FROM jornada where fecha = ? ORDER BY fecha";
            PreparedStatement preparedStatement = controladores.Conexion.getconexion().prepareStatement(consulta);
            preparedStatement.setString(1, palabraAbuscar);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                String dni = resultSet.getString("dni");
                String nom = resultSet.getString("nom");
                String apellido = resultSet.getString("apellido");
                String codicard = resultSet.getString("codicard");
                String horaentrada = resultSet.getString("horaentrada");
                String horasalida = resultSet.getString("horasalida");
                String total = resultSet.getString("total");
                String fecha = resultSet.getString("fecha");
                listaTotalJornadaFecha.add(new Jornada(dni, nom, apellido, codicard, horaentrada, horasalida, total, fecha));
            }
            preparedStatement.close();
            return listaTotalJornadaFecha;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listaTotalJornadaFecha;
    }

    /**
     * Este método hace la busqueda de la tabla Jornada por su nombre y apellido
     * de empleado y envía los datos ordenados por su nombre Objeto a recibir
     * del cliente: codiUser,0,3,nom,datoNom,apellido,datoApellido,0
     *
     * @param datoNom nombre del empleado a buscar
     * @param datoApellido apellido del empleado a buscar
     * @return devuelve la listaJornadaNomApellido, la lista que genera el
     * select a la BBDD HREntrada
     */
    public static ArrayList<Jornada> listaJornadaNomApellido(String datoNom, String datoApellido) {
        ArrayList<Jornada> listaJornadaNomApellido = new ArrayList<>();
        try {

            String consulta = "SELECT * FROM jornada where nom = ? and apellido = ? ORDER BY fecha";
            PreparedStatement preparedStatement = controladores.Conexion.getconexion().prepareStatement(consulta);
            preparedStatement.setString(1, datoNom);
            preparedStatement.setString(2, datoApellido);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                String dni = resultSet.getString("dni");
                String nom = resultSet.getString("nom");
                String apellido = resultSet.getString("apellido");
                String codicard = resultSet.getString("codicard");
                String horaentrada = resultSet.getString("horaentrada");
                String horasalida = resultSet.getString("horasalida");
                String total = resultSet.getString("total");
                String fecha = resultSet.getString("fecha");
                listaJornadaNomApellido.add(new Jornada(dni, nom, apellido, codicard, horaentrada, horasalida, total, fecha));
            }
            preparedStatement.close();
            return listaJornadaNomApellido;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listaJornadaNomApellido;
    }

    /**
     * Este método hace la busqueda de la tabla Jornada por su dni y fecha de
     * empleado y envía los datos ordenados por su nombre Objeto a recibir del
     * cliente: codiUser,0,3,dni,datoDni,fecha,datoFecha,0
     *
     * @param datoDni dni del empleado a buscar
     * @param datoFecha fecha de la jornada laboral del empleado a buscar
     * @return devuelve la listaJornadaDniFecha, la lista que genera el select a
     * la BBDD HREntrada
     */
    public static ArrayList<Jornada> listaJornadaDniFecha(String datoDni, String datoFecha) {
        ArrayList<Jornada> listaJornadaDniFecha = new ArrayList<>();
        try {

            String consulta = "SELECT * FROM jornada where dni = ? and fecha = ? ORDER BY fecha";
            PreparedStatement preparedStatement = controladores.Conexion.getconexion().prepareStatement(consulta);
            preparedStatement.setString(1, datoDni);
            preparedStatement.setString(2, datoFecha);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                String dni = resultSet.getString("dni");
                String nom = resultSet.getString("nom");
                String apellido = resultSet.getString("apellido");
                String codicard = resultSet.getString("codicard");
                String horaentrada = resultSet.getString("horaentrada");
                String horasalida = resultSet.getString("horasalida");
                String total = resultSet.getString("total");
                String fecha = resultSet.getString("fecha");
                listaJornadaDniFecha.add(new Jornada(dni, nom, apellido, codicard, horaentrada, horasalida, total, fecha));
            }
            preparedStatement.close();
            return listaJornadaDniFecha;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listaJornadaDniFecha;
    }

    /**
     * Este método hace la busqueda de la tabla Jornada por su nombre y fecha de
     * empleado y envía los datos ordenados por su nombre Objeto a recibir del
     * cliente: codiUser,0,3,nom,datoNom,fecha,datoFecha,0
     *
     * @param datoNom nombre del empleado a buscar
     * @param datoFecha fecha de la jornada laboral del empleado a buscar
     * @return devuelve la listaJornadaNomFecha, la lista que genera el select a
     * la BBDD HREntrada
     */
    public static ArrayList<Jornada> listaJornadaNomFecha(String datoNom, String datoFecha) {
        ArrayList<Jornada> listaJornadaNomFecha = new ArrayList<>();
        try {

            String consulta = "SELECT * FROM jornada where nom = ? and fecha = ? ORDER BY fecha";
            PreparedStatement preparedStatement = controladores.Conexion.getconexion().prepareStatement(consulta);
            preparedStatement.setString(1, datoNom);
            preparedStatement.setString(2, datoFecha);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                String dni = resultSet.getString("dni");
                String nom = resultSet.getString("nom");
                String apellido = resultSet.getString("apellido");
                String codicard = resultSet.getString("codicard");
                String horaentrada = resultSet.getString("horaentrada");
                String horasalida = resultSet.getString("horasalida");
                String total = resultSet.getString("total");
                String fecha = resultSet.getString("fecha");
                listaJornadaNomFecha.add(new Jornada(dni, nom, apellido, codicard, horaentrada, horasalida, total, fecha));
            }
            preparedStatement.close();
            return listaJornadaNomFecha;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listaJornadaNomFecha;
    }

    /**
     * Este método hace la busqueda de la tabla Jornada por su apellido y fecha
     * de empleado y envía los datos ordenados por su nombre Objeto a recibir
     * del cliente: codiUser,0,3,apellido,datoApellido,fecha,datoFecha,0
     *
     * @param datoApellido apellido del empleado a buscar
     * @param datoFecha fecha de la jornada laboral del empleado a buscar
     * @return devuelve la listaJornadaApellidoFecha, la lista que genera el
     * select a la BBDD HREntrada
     */
    public static ArrayList<Jornada> listaJornadaApellidoFecha(String datoApellido, String datoFecha) {
        ArrayList<Jornada> listaJornadaApellidoFecha = new ArrayList<>();
        try {

            String consulta = "SELECT * FROM jornada where apellido = ? and fecha = ? ORDER BY fecha";
            PreparedStatement preparedStatement = controladores.Conexion.getconexion().prepareStatement(consulta);
            preparedStatement.setString(1, datoApellido);
            preparedStatement.setString(2, datoFecha);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                String dni = resultSet.getString("dni");
                String nom = resultSet.getString("nom");
                String apellido = resultSet.getString("apellido");
                String codicard = resultSet.getString("codicard");
                String horaentrada = resultSet.getString("horaentrada");
                String horasalida = resultSet.getString("horasalida");
                String total = resultSet.getString("total");
                String fecha = resultSet.getString("fecha");
                listaJornadaApellidoFecha.add(new Jornada(dni, nom, apellido, codicard, horaentrada, horasalida, total, fecha));
            }
            preparedStatement.close();
            return listaJornadaApellidoFecha;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listaJornadaApellidoFecha;
    }

    /**
     * Este método hace la busqueda de la tabla Jornada por su código de tarjeta
     * y fecha de empleado y envía los datos ordenados por su nombre Objeto a
     * recibir del cliente: codiUser,0,3,codicard,datoCodicard,fecha,datoFecha,0
     *
     * @param codicardDato código de tarjeta del empleado a buscar
     * @param datoFecha fecha de la jornada laboral del empleado a buscar
     * @return devuelve la listaJornadaCodiCardFecha, la lista que genera el
     * select a la BBDD HREntrada
     */
    public static ArrayList<Jornada> listaJornadaCodiCardFecha(int codicardDato, String datoFecha) {
        ArrayList<Jornada> listaJornadaCodiCardFecha = new ArrayList<>();
        try {

            String consulta = "SELECT * FROM jornada where codicard = ? and fecha = ? ORDER BY fecha";
            PreparedStatement preparedStatement = controladores.Conexion.getconexion().prepareStatement(consulta);
            preparedStatement.setInt(1, codicardDato);
            preparedStatement.setString(2, datoFecha);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                String dni = resultSet.getString("dni");
                String nom = resultSet.getString("nom");
                String apellido = resultSet.getString("apellido");
                String codicard = resultSet.getString("codicard");
                String horaentrada = resultSet.getString("horaentrada");
                String horasalida = resultSet.getString("horasalida");
                String total = resultSet.getString("total");
                String fecha = resultSet.getString("fecha");
                listaJornadaCodiCardFecha.add(new Jornada(dni, nom, apellido, codicard, horaentrada, horasalida, total, fecha));
            }
            preparedStatement.close();
            return listaJornadaCodiCardFecha;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listaJornadaCodiCardFecha;
    }

    /**
     * Este método hace la busqueda de la tabla Jornada por su nombre,apellido y
     * fecha de empleado y envía los datos ordenados por su nombre Objeto a
     * recibir del cliente:
     * codiUser,0,3,nom,datoNom,apellido,datoApellido,fecha,datoFecha,0
     *
     * @param datoNom nombre del empleado a buscar
     * @param datoApellido apellido del empleado a buscar
     * @param datoFecha fecha de la jornada laboral del empleado a buscar
     * @return devuelve la listaJornadaNomApellidoFecha, la lista que genera el
     * select a la BBDD HREntrada
     */
    public static ArrayList<Jornada> listaJornadaNomApellidoFecha(String datoNom, String datoApellido, String datoFecha) {
        ArrayList<Jornada> listaJornadaNomApellidoFecha = new ArrayList<>();
        try {

            String consulta = "SELECT * FROM jornada where nom = ? and apellido = ? and fecha = ? ORDER BY fecha";
            PreparedStatement preparedStatement = controladores.Conexion.getconexion().prepareStatement(consulta);
            preparedStatement.setString(1, datoNom);
            preparedStatement.setString(2, datoApellido);
            preparedStatement.setString(3, datoFecha);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                String dni = resultSet.getString("dni");
                String nom = resultSet.getString("nom");
                String apellido = resultSet.getString("apellido");
                String codicard = resultSet.getString("codicard");
                String horaentrada = resultSet.getString("horaentrada");
                String horasalida = resultSet.getString("horasalida");
                String total = resultSet.getString("total");
                String fecha = resultSet.getString("fecha");
                listaJornadaNomApellidoFecha.add(new Jornada(dni, nom, apellido, codicard, horaentrada, horasalida, total, fecha));
            }
            preparedStatement.close();
            return listaJornadaNomApellidoFecha;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listaJornadaNomApellidoFecha;
    }
}
