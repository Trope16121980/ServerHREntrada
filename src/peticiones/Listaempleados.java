package peticiones;

import modelo.Empleados;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * @author Gustavo Senoráns Varela
 * @version 1.4, 15/11/2023
 * @since jdk 17
 */
public class Listaempleados {

    /**
     * Este método hace la busqueda total de la tabla Empleados y envía los
     * datos ordenados por su nombre Objeto a recibir del cliente:
     * codiUser,0,0,0,0,0
     *
     * @return devuelve la listaTotalEmpleados, todos los datos de todos los
     * empleados
     */
    public static ArrayList<Empleados> listaTotalEmpleados() {
        ArrayList<Empleados> listaTotalEmpleados = new ArrayList<>();
        try {

            String consulta = "SELECT * FROM empleados ORDER BY nom";
            PreparedStatement preparedStatement = controladores.Conexion.getconexion().prepareStatement(consulta);
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

    /**
     * Este método hace la busqueda de los empleados por su dni y envía los
     * datos ordenados por su nombre codiUser,0,0,dni,datoDni,0
     *
     * @param palabraAbuscar el array que contiene los datos a buscar
     * @return devuelve la listaTotalEmpleadosDni, la lista que genera el select
     * a la BBDD HREntrada
     */
    public static ArrayList<Empleados> listaTotalEmpleadosDni(String palabraAbuscar) {
        ArrayList<Empleados> listaTotalEmpleadosDni = new ArrayList<>();
        try {

            String consulta = "SELECT * FROM empleados where dni = ? ORDER BY nom";
            PreparedStatement preparedStatement = controladores.Conexion.getconexion().prepareStatement(consulta);
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
                listaTotalEmpleadosDni
                        .add(new Empleados(dni, nom, apellido, nomempresa, departament, codicard, mail, telephon));

            }
            preparedStatement.close();
            return listaTotalEmpleadosDni;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listaTotalEmpleadosDni;
    }

    /**
     * Este método hace la busqueda de los Empleados por su nombre y envía los
     * datos ordenados por su nombre Objeto a recibir del cliente:
     * codiUser,0,0,nom,datoNom,0
     *
     * @param palabraAbuscar el array que contiene los datos a buscar
     * @return devuelve la listaTotalEmpleadosNom, la lista que genera el select
     * a la BBDD HREntrada
     */
    public static ArrayList<Empleados> listaTotalEmpleadosNom(String palabraAbuscar) {
        ArrayList<Empleados> listaTotalEmpleadosNom = new ArrayList<>();
        try {

            String consulta = "SELECT * FROM empleados where nom = ? ORDER BY nom";
            PreparedStatement preparedStatement = controladores.Conexion.getconexion().prepareStatement(consulta);
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
                listaTotalEmpleadosNom
                        .add(new Empleados(dni, nom, apellido, nomempresa, departament, codicard, mail, telephon));

            }
            preparedStatement.close();
            return listaTotalEmpleadosNom;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listaTotalEmpleadosNom;
    }

    /**
     * Este método hace la busqueda de los Empleados por su aplellido y envía
     * los datos ordenados por su nombre Objeto a recibir del cliente:
     * codiUser,0,0,apellido,datoApellido,0
     *
     * @param palabraAbuscar el array que contiene los datos a buscar
     * @return devuelve la listaTotalEmpleadosApellido, la lista que genera el
     * select a la BBDD HREntrada
     */
    public static ArrayList<Empleados> listaTotalEmpleadosApellido(String palabraAbuscar) {
        ArrayList<Empleados> listaTotalEmpleadosApellido = new ArrayList<>();
        try {

            String consulta = "SELECT * FROM empleados where apellido = ? ORDER BY nom";
            PreparedStatement preparedStatement = controladores.Conexion.getconexion().prepareStatement(consulta);
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
                listaTotalEmpleadosApellido
                        .add(new Empleados(dni, nom, apellido, nomempresa, departament, codicard, mail, telephon));

            }
            preparedStatement.close();
            return listaTotalEmpleadosApellido;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listaTotalEmpleadosApellido;
    }

    /**
     * Este método hace la busqueda de los Empleados por su nombre de la empresa
     * y envía los datos ordenados por su nombre Objeto a recibir del cliente:
     * codiUser,0,0,nomempresa,datoNomempresa,0
     *
     * @param palabraAbuscar el array que contiene los datos a buscar
     * @return devuelve la listaTotalEmpleadosNomEmpresa, la lista que genera el
     * select a la BBDD HREntrada
     */
    public static ArrayList<Empleados> listaTotalEmpleadosNomEmpresa(String palabraAbuscar) {
        ArrayList<Empleados> listaTotalEmpleadosNomEmpresa = new ArrayList<>();
        try {

            String consulta = "SELECT * FROM empleados where nomempresa = ? ORDER BY nom";
            PreparedStatement preparedStatement = controladores.Conexion.getconexion().prepareStatement(consulta);
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
                listaTotalEmpleadosNomEmpresa
                        .add(new Empleados(dni, nom, apellido, nomempresa, departament, codicard, mail, telephon));

            }
            preparedStatement.close();
            return listaTotalEmpleadosNomEmpresa;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listaTotalEmpleadosNomEmpresa;
    }

    /**
     * Este método hace la busqueda de los Empleados por su departamento y envía
     * los datos ordenados por su nombre Objeto a recibir del cliente:
     * codiUser,0,0,departament,datoDepartament,0
     *
     * @param palabraAbuscar el array que contiene los datos a buscar
     * @return devuelve la listaTotalEmpleadosDepart, la lista que genera el
     * select a la BBDD HREntrada
     */
    public static ArrayList<Empleados> listaTotalEmpleadosDepart(String palabraAbuscar) {
        ArrayList<Empleados> listaTotalEmpleadosDepart = new ArrayList<>();
        try {

            String consulta = "SELECT * FROM empleados where departament = ? ORDER BY nom";
            PreparedStatement preparedStatement = controladores.Conexion.getconexion().prepareStatement(consulta);
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
                listaTotalEmpleadosDepart
                        .add(new Empleados(dni, nom, apellido, nomempresa, departament, codicard, mail, telephon));

            }
            preparedStatement.close();
            return listaTotalEmpleadosDepart;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listaTotalEmpleadosDepart;
    }

    /**
     * Este método hace la busqueda de los Empleados por su código de tarjeta y
     * envía los datos ordenados por su nombre Objeto a recibir del cliente:
     * codiUser,0,0,codicard,datoCodicard,0
     *
     * @param palabraAbuscar el array que contiene los datos a buscar
     * @return devuelve la listaTotalEmpleadosCodiCard, la lista que genera el
     * select a la BBDD HREntrada
     */
    public static ArrayList<Empleados> listaTotalEmpleadosCodiCard(int palabraAbuscar) {
        ArrayList<Empleados> listaTotalEmpleadosCodiCard = new ArrayList<>();
        try {

            String consulta = "SELECT * FROM empleados where codicard = ? ORDER BY nom";
            PreparedStatement preparedStatement = controladores.Conexion.getconexion().prepareStatement(consulta);
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
                listaTotalEmpleadosCodiCard
                        .add(new Empleados(dni, nom, apellido, nomempresa, departament, codicard, mail, telephon));

            }
            preparedStatement.close();
            return listaTotalEmpleadosCodiCard;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listaTotalEmpleadosCodiCard;
    }

    /**
     * Este método hace la busqueda de los Empleados por su mail y envía los
     * datos ordenados por su nombre Objeto a recibir del cliente:
     * codiUser,0,0,mail,datoMail,0
     *
     * @param palabraAbuscar el array que contiene los datos a buscar
     * @return devuelve la listaTotalEmpleadosMail, la lista que genera el
     * select a la BBDD HREntrada
     */
    public static ArrayList<Empleados> listaTotalEmpleadosMail(String palabraAbuscar) {
        ArrayList<Empleados> listaTotalEmpleadosMail = new ArrayList<>();
        try {

            String consulta = "SELECT * FROM empleados where mail = ? ORDER BY nom";
            PreparedStatement preparedStatement = controladores.Conexion.getconexion().prepareStatement(consulta);
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
                listaTotalEmpleadosMail
                        .add(new Empleados(dni, nom, apellido, nomempresa, departament, codicard, mail, telephon));

            }
            preparedStatement.close();
            return listaTotalEmpleadosMail;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listaTotalEmpleadosMail;
    }

    /**
     * Este método hace la busqueda de los Empleados por su teléfono y envía los
     * datos ordenados por su nombre Objeto a recibir del cliente:
     * codiUser,0,0,telephon,datoTelephon,0
     *
     * @param palabraAbuscar el array que contiene los datos a buscar
     * @return devuelve la listaTotalEmpleadosTelf, la lista que genera el
     * select a la BBDD HREntrada
     */
    public static ArrayList<Empleados> listaTotalEmpleadosTelf(int palabraAbuscar) {
        ArrayList<Empleados> listaTotalEmpleadosTelf = new ArrayList<>();
        try {

            String consulta = "SELECT * FROM empleados where telephon = ? ORDER BY nom";
            PreparedStatement preparedStatement = controladores.Conexion.getconexion().prepareStatement(consulta);
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
                listaTotalEmpleadosTelf
                        .add(new Empleados(dni, nom, apellido, nomempresa, departament, codicard, mail, telephon));

            }
            preparedStatement.close();
            return listaTotalEmpleadosTelf;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listaTotalEmpleadosTelf;
    }

    /**
     * Este método hace la busqueda de los Empleados por su nombre y apellido y
     * envía los datos ordenados por su nombre Objeto a recibir del cliente:
     * codiUser,0,0,nom,datoNom,apellido,datoApellido,0
     *
     * @param datoNom nombre del empleado a buscar
     * @param datoApellido apellido del empleado a buscar
     * @return devuelve la listaEmpleadosNomApellido, la lista que genera el
     * select a la BBDD HREntrada
     */
    public static ArrayList<Empleados> listaEmpleadosNomApellido(String datoNom, String datoApellido) {
        ArrayList<Empleados> listaEmpleadosNomApellido = new ArrayList<>();
        try {

            String consulta = "SELECT * FROM empleados where nom = ? and apellido = ? ORDER BY nom";
            PreparedStatement preparedStatement = controladores.Conexion.getconexion().prepareStatement(consulta);
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
                listaEmpleadosNomApellido
                        .add(new Empleados(dni, nom, apellido, nomempresa, departament, codicard, mail, telephon));

            }
            preparedStatement.close();
            return listaEmpleadosNomApellido;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listaEmpleadosNomApellido;
    }

}
