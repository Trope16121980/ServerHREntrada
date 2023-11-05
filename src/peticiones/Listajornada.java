
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

    public static ArrayList<Jornada> listaTotalJornada() {
        ArrayList<Jornada> listaTotalJornada = new ArrayList<>();
        try {

            String consulta = "SELECT * FROM jornada ORDER BY fecha";
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

    public static ArrayList<Jornada> listaTotalJornadaDni(String palabraAbuscar) {
        ArrayList<Jornada> listaTotalJornadaDni = new ArrayList<>();
        try {

            String consulta = "SELECT * FROM jornada where dni = ? ORDER BY fecha";
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

    public static ArrayList<Jornada> listaTotalJornadaNom(String palabraAbuscar) {
        ArrayList<Jornada> listaTotalJornadaNom = new ArrayList<>();
        try {

            String consulta = "SELECT * FROM jornada where nom = ? ORDER BY fecha";
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
                listaTotalJornadaNom.add(new Jornada(dni, nom, apellido, codicard, horaentrada, horasalida, total, fecha));
            }
            preparedStatement.close();
            return listaTotalJornadaNom;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listaTotalJornadaNom;
    }
    
    public static ArrayList<Jornada> listaTotalJornadaApellido(String palabraAbuscar) {
        ArrayList<Jornada> listaTotalJornadaApellido = new ArrayList<>();
        try {

            String consulta = "SELECT * FROM jornada where apellido = ? ORDER BY fecha";
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
                listaTotalJornadaApellido.add(new Jornada(dni, nom, apellido, codicard, horaentrada, horasalida, total, fecha));
            }
            preparedStatement.close();
            return listaTotalJornadaApellido;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listaTotalJornadaApellido;
    }
    
    public static ArrayList<Jornada> listaJornadaCodiCard(int palabraAbuscar) {
        ArrayList<Jornada> listaJornadaCodiCard = new ArrayList<>();
        try {

            String consulta = "SELECT * FROM jornada where codicard = ? ORDER BY fecha";
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

    public static ArrayList<Jornada> listaTotalJornadaFecha(String palabraAbuscar) {
        ArrayList<Jornada> listaTotalJornadaFecha = new ArrayList<>();
        try {

            String consulta = "SELECT * FROM jornada where fecha = ? ORDER BY fecha";
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

    public static ArrayList<Jornada> listaJornadaNomApellido(String datoNom, String datoApellido) {
        ArrayList<Jornada> listaJornadaNomApellido = new ArrayList<>();
        try {

            String consulta = "SELECT * FROM jornada where nom = ? and apellido = ? ORDER BY fecha";
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
    
    public static ArrayList<Jornada> listaJornadaDniFecha(String datoDni, String datoFecha) {
        ArrayList<Jornada> listaJornadaDniFecha = new ArrayList<>();
        try {

            String consulta = "SELECT * FROM jornada where dni = ? and fecha = ? ORDER BY fecha";
            PreparedStatement preparedStatement = conn.Conexion.getconexion().prepareStatement(consulta);
            preparedStatement.setString(1, datoDni);
            preparedStatement.setString(2, datoFecha);
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
                listaJornadaDniFecha.add(new Jornada(dni, nom, apellido, codicard, horaentrada, horasalida, total, fecha));
            }
            preparedStatement.close();
            return listaJornadaDniFecha;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listaJornadaDniFecha;
    }
    
    public static ArrayList<Jornada> listaJornadaNomFecha(String datoNom, String datoFecha) {
        ArrayList<Jornada> listaJornadaNomFecha = new ArrayList<>();
        try {

            String consulta = "SELECT * FROM jornada where nom = ? and fecha = ? ORDER BY fecha";
            PreparedStatement preparedStatement = conn.Conexion.getconexion().prepareStatement(consulta);
            preparedStatement.setString(1, datoNom);
            preparedStatement.setString(2, datoFecha);
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
                listaJornadaNomFecha.add(new Jornada(dni, nom, apellido, codicard, horaentrada, horasalida, total, fecha));
            }
            preparedStatement.close();
            return listaJornadaNomFecha;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listaJornadaNomFecha;
    }
    
    public static ArrayList<Jornada> listaJornadaApellidoFecha(String datoApellido, String datoFecha) {
        ArrayList<Jornada> listaJornadaApellidoFecha = new ArrayList<>();
        try {

            String consulta = "SELECT * FROM jornada where apellido = ? and fecha = ? ORDER BY fecha";
            PreparedStatement preparedStatement = conn.Conexion.getconexion().prepareStatement(consulta);
            preparedStatement.setString(1, datoApellido);
            preparedStatement.setString(2, datoFecha);
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
                listaJornadaApellidoFecha.add(new Jornada(dni, nom, apellido, codicard, horaentrada, horasalida, total, fecha));
            }
            preparedStatement.close();
            return listaJornadaApellidoFecha;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listaJornadaApellidoFecha;
    }
    
    public static ArrayList<Jornada> listaJornadaCodiCardFecha(int codicardDato, String datoFecha) {
        ArrayList<Jornada> listaJornadaCodiCardFecha = new ArrayList<>();
        try {

            String consulta = "SELECT * FROM jornada where codicard = ? and fecha = ? ORDER BY fecha";
            PreparedStatement preparedStatement = conn.Conexion.getconexion().prepareStatement(consulta);
            preparedStatement.setInt(1, codicardDato);
            preparedStatement.setString(2, datoFecha);
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
                listaJornadaCodiCardFecha.add(new Jornada(dni, nom, apellido, codicard, horaentrada, horasalida, total, fecha));
            }
            preparedStatement.close();
            return listaJornadaCodiCardFecha;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listaJornadaCodiCardFecha;
    }

    public static ArrayList<Jornada> listaJornadaNomApellidoFecha(String datoNom, String datoApellido, String datoFecha) {
        ArrayList<Jornada> listaJornadaNomApellidoFecha = new ArrayList<>();
        try {

            String consulta = "SELECT * FROM jornada where nom = ? and apellido = ? and fecha = ? ORDER BY fecha";
            PreparedStatement preparedStatement = conn.Conexion.getconexion().prepareStatement(consulta);
            preparedStatement.setString(1, datoNom);
            preparedStatement.setString(2, datoApellido);
            preparedStatement.setString(3, datoFecha );
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
