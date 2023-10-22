
package peticiones;

import modelo.Jornada;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Gustavo_Senorans
 */
public class Listajornada {

    public static ArrayList<Jornada> listaTotalJornada() {//devuelve el dni
        ArrayList<Jornada> listaTotalJornada = new ArrayList<>();
        try {

            String consulta = "SELECT * FROM jornada";
            PreparedStatement preparedStatement = conn.Conexion.getconexion().prepareStatement(consulta);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                String dni = resultSet.getString("dni");
                String nom = resultSet.getString("nom");
                String apellido = resultSet.getString("apellido");
                int codicard = resultSet.getInt("codicard");
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

    public static ArrayList<Jornada> listaTotalJornadaDni(String palabraAbuscar) {//devuelve el dni
        ArrayList<Jornada> listaTotalJornadaDni = new ArrayList<>();
        try {

            String consulta = "SELECT * FROM jornada where dni = ?";
            PreparedStatement preparedStatement = conn.Conexion.getconexion().prepareStatement(consulta);
            preparedStatement.setString(1, palabraAbuscar);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                String dni = resultSet.getString("dni");
                String nom = resultSet.getString("nom");
                String apellido = resultSet.getString("apellido");
                int codicard = resultSet.getInt("codicard");
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

    public static ArrayList<Jornada> listaJornadaCodiCard(int palabraAbuscar) {//devuelve el dni
        ArrayList<Jornada> listaJornadaCodiCard = new ArrayList<>();
        try {

            String consulta = "SELECT * FROM jornada where codicard = ?";
            PreparedStatement preparedStatement = conn.Conexion.getconexion().prepareStatement(consulta);
            preparedStatement.setInt(1, palabraAbuscar);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                String dni = resultSet.getString("dni");
                String nom = resultSet.getString("nom");
                String apellido = resultSet.getString("apellido");
                int codicard = resultSet.getInt("codicard");
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

    public static ArrayList<Jornada> listaTotalJornadaFecha(String palabraAbuscar) {//devuelve el dni
        ArrayList<Jornada> listaTotalJornadaFecha = new ArrayList<>();
        try {

            String consulta = "SELECT * FROM jornada where fecha = ?";
            PreparedStatement preparedStatement = conn.Conexion.getconexion().prepareStatement(consulta);
            preparedStatement.setString(1, palabraAbuscar);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                String dni = resultSet.getString("dni");
                String nom = resultSet.getString("nom");
                String apellido = resultSet.getString("apellido");
                int codicard = resultSet.getInt("codicard");
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

    public static ArrayList<Jornada> listaJornadaNomApellido(String datoNom, String datoApellido) {//devuelve el dni
        ArrayList<Jornada> listaJornadaNomApellido = new ArrayList<>();
        try {

            String consulta = "SELECT * FROM jornada where nom = ? and apellido = ?";
            PreparedStatement preparedStatement = conn.Conexion.getconexion().prepareStatement(consulta);
            preparedStatement.setString(1, datoNom);
            preparedStatement.setString(2, datoApellido);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                String dni = resultSet.getString("dni");
                String nom = resultSet.getString("nom");
                String apellido = resultSet.getString("apellido");
                int codicard = resultSet.getInt("codicard");
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
}
