package peticiones;

import modelo.Empleados;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author gsenorans
 */
public class InsertEmpleados {

    public static ArrayList<Empleados> insertEmpleados(String datoDni, String datoNom,
            String datoApellido, String datoNomempresa, String datoDepartament,
            int datoCodicar, String datoMail, int datoTelephon) {//devuelve el dni

        ArrayList<Empleados> insertEmpleados = new ArrayList<>();

        try {

            String insert = "INSERT INTO empleados (dni, nom, apellido, nomempresa, departament, codicard, mail, telephon) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement = conn.Conexion.getconexion().prepareStatement(insert);
            preparedStatement.setString(1, datoDni);
            preparedStatement.setString(2, datoNom);
            preparedStatement.setString(3, datoApellido);
            preparedStatement.setString(4, datoNomempresa);
            preparedStatement.setString(5, datoDepartament);
            preparedStatement.setInt(6, datoCodicar);
            preparedStatement.setString(7, datoMail);
            preparedStatement.setInt(8, datoTelephon);

            preparedStatement.executeUpdate();
            preparedStatement.close();

            return insertEmpleados;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return insertEmpleados;
    }

    public static ArrayList<Empleados> insertEmpleadosMailTelf(String datoDni, String datoNom,
            String datoApellido, String datoNomempresa, String datoDepartament,
            int datoCodicar) {//devuelve el dni

        ArrayList<Empleados> insertEmpleadosMailTelf = new ArrayList<>();

        try {

            String insert = "INSERT INTO empleados (dni, nom, apellido, nomempresa, departament, codicard) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement = conn.Conexion.getconexion().prepareStatement(insert);
            preparedStatement.setString(1, datoDni);
            preparedStatement.setString(2, datoNom);
            preparedStatement.setString(3, datoApellido);
            preparedStatement.setString(4, datoNomempresa);
            preparedStatement.setString(5, datoDepartament);
            preparedStatement.setInt(6, datoCodicar);

            preparedStatement.executeUpdate();
            preparedStatement.close();

            return insertEmpleadosMailTelf;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return insertEmpleadosMailTelf;
    }

    public static ArrayList<Empleados> insertEmpleadosMail(String datoDni, String datoNom,
            String datoApellido, String datoNomempresa, String datoDepartament,
            int datoCodicard, String datoMail) {//devuelve el dni

        ArrayList<Empleados> insertEmpleadosMail = new ArrayList<>();

        try {

            String insert = "INSERT INTO empleados (dni, nom, apellido, nomempresa, departament, codicard, mail) VALUES (?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement = conn.Conexion.getconexion().prepareStatement(insert);
            preparedStatement.setString(1, datoDni);
            preparedStatement.setString(2, datoNom);
            preparedStatement.setString(3, datoApellido);
            preparedStatement.setString(4, datoNomempresa);
            preparedStatement.setString(5, datoDepartament);
            preparedStatement.setInt(6, datoCodicard);
            preparedStatement.setString(7, datoMail);

            preparedStatement.executeUpdate();
            preparedStatement.close();

            return insertEmpleadosMail;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return insertEmpleadosMail;
    }

    public static ArrayList<Empleados> insertEmpleadosTelf(String datoDni, String datoNom,
            String datoApellido, String datoNomempresa, String datoDepartament,
            int datoCodicard, int datoTelephon) {//devuelve el dni

        ArrayList<Empleados> insertEmpleadosTelf = new ArrayList<>();

        try {

            String insert = "INSERT INTO empleados (dni, nom, apellido, nomempresa, departament, codicard, telephon) VALUES (?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement = conn.Conexion.getconexion().prepareStatement(insert);
            preparedStatement.setString(1, datoDni);
            preparedStatement.setString(2, datoNom);
            preparedStatement.setString(3, datoApellido);
            preparedStatement.setString(4, datoNomempresa);
            preparedStatement.setString(5, datoDepartament);
            preparedStatement.setInt(6, datoCodicard);
            preparedStatement.setInt(7, datoTelephon);

            preparedStatement.executeUpdate();
            preparedStatement.close();

            return insertEmpleadosTelf;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return insertEmpleadosTelf;
    }
}
