
package peticiones;

import modelo.Empleados;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Gustavo_Senorans
 */
public class Listaempleados {

    public static ArrayList<Empleados> listaTotalEmpleados() {//devuelve el dni
        ArrayList<Empleados> listaTotalEmpleados = new ArrayList<>();
        try {

            String consulta = "SELECT * FROM empleados";
            PreparedStatement preparedStatement = conn.Conexion.getconexion().prepareStatement(consulta);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                String dni = resultSet.getString("dni");
                String nom = resultSet.getString("nom");
                String apellido = resultSet.getString("apellido");
                String nomempresa = resultSet.getString("nomempresa");
                String departament = resultSet.getString("departament");
                int codicard = resultSet.getInt("codicard");
                String mail = resultSet.getString("mail");
                int telephon = resultSet.getInt("telephon");
                listaTotalEmpleados.add(new Empleados(dni, nom, apellido, nomempresa, departament, codicard, mail, telephon));

            }
            preparedStatement.close();
            return listaTotalEmpleados;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listaTotalEmpleados;
    }

    public static ArrayList<Empleados> listaTotalEmpleadosDni(String palabraAbuscar) {//devuelve el dni
        ArrayList<Empleados> listaTotalEmpleadosDni = new ArrayList<>();
        try {

            String consulta = "SELECT * FROM empleados where dni = ?";
            PreparedStatement preparedStatement = conn.Conexion.getconexion().prepareStatement(consulta);
            preparedStatement.setString(1, palabraAbuscar);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                String dni = resultSet.getString("dni");
                String nom = resultSet.getString("nom");
                String apellido = resultSet.getString("apellido");
                String nomempresa = resultSet.getString("nomempresa");
                String departament = resultSet.getString("departament");
                int codicard = resultSet.getInt("codicard");
                String mail = resultSet.getString("mail");
                int telephon = resultSet.getInt("telephon");
                listaTotalEmpleadosDni.add(new Empleados(dni, nom, apellido, nomempresa, departament, codicard, mail, telephon));

            }
            preparedStatement.close();
            return listaTotalEmpleadosDni;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listaTotalEmpleadosDni;
    }

    
    public static ArrayList<Empleados> listaTotalEmpleadosNom(String palabraAbuscar) {
        ArrayList<Empleados> listaTotalEmpleadosNom = new ArrayList<>();
        try {

            String consulta = "SELECT * FROM empleados where nom = ?";
            PreparedStatement preparedStatement = conn.Conexion.getconexion().prepareStatement(consulta);
            preparedStatement.setString(1, palabraAbuscar);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                String dni = resultSet.getString("dni");
                String nom = resultSet.getString("nom");
                String apellido = resultSet.getString("apellido");
                String nomempresa = resultSet.getString("nomempresa");
                String departament = resultSet.getString("departament");
                int codicard = resultSet.getInt("codicard");
                String mail = resultSet.getString("mail");
                int telephon = resultSet.getInt("telephon");
                listaTotalEmpleadosNom.add(new Empleados(dni, nom, apellido, nomempresa, departament, codicard, mail, telephon));

            }
            preparedStatement.close();
            return listaTotalEmpleadosNom;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listaTotalEmpleadosNom;
    }

    
    public static ArrayList<Empleados> listaTotalEmpleadosApellido(String palabraAbuscar) {
        ArrayList<Empleados> listaTotalEmpleadosApellido = new ArrayList<>();
        try {

            String consulta = "SELECT * FROM empleados where apellido = ?";
            PreparedStatement preparedStatement = conn.Conexion.getconexion().prepareStatement(consulta);
            preparedStatement.setString(1, palabraAbuscar);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                String dni = resultSet.getString("dni");
                String nom = resultSet.getString("nom");
                String apellido = resultSet.getString("apellido");
                String nomempresa = resultSet.getString("nomempresa");
                String departament = resultSet.getString("departament");
                int codicard = resultSet.getInt("codicard");
                String mail = resultSet.getString("mail");
                int telephon = resultSet.getInt("telephon");
                listaTotalEmpleadosApellido.add(new Empleados(dni, nom, apellido, nomempresa, departament, codicard, mail, telephon));

            }
            preparedStatement.close();
            return listaTotalEmpleadosApellido;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listaTotalEmpleadosApellido;
    }

    
    
    
    public static ArrayList<Empleados> listaTotalEmpleadosNomEmpresa(String palabraAbuscar) {
        ArrayList<Empleados> listaTotalEmpleadosNomEmpresa = new ArrayList<>();
        try {

            String consulta = "SELECT * FROM empleados where nomempresa = ?";
            PreparedStatement preparedStatement = conn.Conexion.getconexion().prepareStatement(consulta);
            preparedStatement.setString(1, palabraAbuscar);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                String dni = resultSet.getString("dni");
                String nom = resultSet.getString("nom");
                String apellido = resultSet.getString("apellido");
                String nomempresa = resultSet.getString("nomempresa");
                String departament = resultSet.getString("departament");
                int codicard = resultSet.getInt("codicard");
                String mail = resultSet.getString("mail");
                int telephon = resultSet.getInt("telephon");
                listaTotalEmpleadosNomEmpresa.add(new Empleados(dni, nom, apellido, nomempresa, departament, codicard, mail, telephon));

            }
            preparedStatement.close();
            return listaTotalEmpleadosNomEmpresa;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listaTotalEmpleadosNomEmpresa;
    }

    public static ArrayList<Empleados> listaTotalEmpleadosDepart(String palabraAbuscar) {//devuelve el dni
        ArrayList<Empleados> listaTotalEmpleadosDepart = new ArrayList<>();
        try {

            String consulta = "SELECT * FROM empleados where departament = ?";
            PreparedStatement preparedStatement = conn.Conexion.getconexion().prepareStatement(consulta);
            preparedStatement.setString(1, palabraAbuscar);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                String dni = resultSet.getString("dni");
                String nom = resultSet.getString("nom");
                String apellido = resultSet.getString("apellido");
                String nomempresa = resultSet.getString("nomempresa");
                String departament = resultSet.getString("departament");
                int codicard = resultSet.getInt("codicard");
                String mail = resultSet.getString("mail");
                int telephon = resultSet.getInt("telephon");
                listaTotalEmpleadosDepart.add(new Empleados(dni, nom, apellido, nomempresa, departament, codicard, mail, telephon));

            }
            preparedStatement.close();
            return listaTotalEmpleadosDepart;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listaTotalEmpleadosDepart;
    }

    public static ArrayList<Empleados> listaTotalEmpleadosCodiCard(int palabraAbuscar) {//devuelve el dni
        ArrayList<Empleados> listaTotalEmpleadosCodiCard = new ArrayList<>();
        try {

            String consulta = "SELECT * FROM empleados where codicard = ?";
            PreparedStatement preparedStatement = conn.Conexion.getconexion().prepareStatement(consulta);
            preparedStatement.setInt(1, palabraAbuscar);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                String dni = resultSet.getString("dni");
                String nom = resultSet.getString("nom");
                String apellido = resultSet.getString("apellido");
                String nomempresa = resultSet.getString("nomempresa");
                String departament = resultSet.getString("departament");
                int codicard = resultSet.getInt("codicard");
                String mail = resultSet.getString("mail");
                int telephon = resultSet.getInt("telephon");
                listaTotalEmpleadosCodiCard.add(new Empleados(dni, nom, apellido, nomempresa, departament, codicard, mail, telephon));

            }
            preparedStatement.close();
            return listaTotalEmpleadosCodiCard;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listaTotalEmpleadosCodiCard;
    }

    public static ArrayList<Empleados> listaTotalEmpleadosMail(String palabraAbuscar) {//devuelve el dni
        ArrayList<Empleados> listaTotalEmpleadosMail = new ArrayList<>();
        try {

            String consulta = "SELECT * FROM empleados where mail = ?";
            PreparedStatement preparedStatement = conn.Conexion.getconexion().prepareStatement(consulta);
            preparedStatement.setString(1, palabraAbuscar);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                String dni = resultSet.getString("dni");
                String nom = resultSet.getString("nom");
                String apellido = resultSet.getString("apellido");
                String nomempresa = resultSet.getString("nomempresa");
                String departament = resultSet.getString("departament");
                int codicard = resultSet.getInt("codicard");
                String mail = resultSet.getString("mail");
                int telephon = resultSet.getInt("telephon");
                listaTotalEmpleadosMail.add(new Empleados(dni, nom, apellido, nomempresa, departament, codicard, mail, telephon));

            }
            preparedStatement.close();
            return listaTotalEmpleadosMail;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listaTotalEmpleadosMail;
    }

    public static ArrayList<Empleados> listaTotalEmpleadosTelf(int palabraAbuscar) {//devuelve el dni
        ArrayList<Empleados> listaTotalEmpleadosTelf = new ArrayList<>();
        try {

            String consulta = "SELECT * FROM empleados where telephon = ?";
            PreparedStatement preparedStatement = conn.Conexion.getconexion().prepareStatement(consulta);
            preparedStatement.setInt(1, palabraAbuscar);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                String dni = resultSet.getString("dni");
                String nom = resultSet.getString("nom");
                String apellido = resultSet.getString("apellido");
                String nomempresa = resultSet.getString("nomempresa");
                String departament = resultSet.getString("departament");
                int codicard = resultSet.getInt("codicard");
                String mail = resultSet.getString("mail");
                int telephon = resultSet.getInt("telephon");
                listaTotalEmpleadosTelf.add(new Empleados(dni, nom, apellido, nomempresa, departament, codicard, mail, telephon));

            }
            preparedStatement.close();
            return listaTotalEmpleadosTelf;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listaTotalEmpleadosTelf;
    }
    
     public static ArrayList<Empleados> listaEmpleadosNomApellido(String datoNom, String datoApellido) {//devuelve el dni
        ArrayList<Empleados> listaEmpleadosNomApellido = new ArrayList<>();
        try {

            String consulta = "SELECT * FROM empleados where nom = ? and apellido = ?";
            PreparedStatement preparedStatement = conn.Conexion.getconexion().prepareStatement(consulta);
            preparedStatement.setString(1, datoNom);
            preparedStatement.setString(2, datoApellido);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                String dni = resultSet.getString("dni");
                String nom = resultSet.getString("nom");
                String apellido = resultSet.getString("apellido");
                String nomempresa = resultSet.getString("nomempresa");
                String departament = resultSet.getString("departament");
                int codicard = resultSet.getInt("codicard");
                String mail = resultSet.getString("mail");
                int telephon = resultSet.getInt("telephon");
                listaEmpleadosNomApellido.add(new Empleados(dni, nom, apellido, nomempresa, departament, codicard, mail, telephon));

            }
            preparedStatement.close();
            return listaEmpleadosNomApellido;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listaEmpleadosNomApellido;
    }
}
