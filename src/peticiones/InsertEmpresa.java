package peticiones;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import modelo.Empresa;

/**
 *
 * @author gsenorans
 */
public class InsertEmpresa {

    public static ArrayList<Empresa> insertEmpresa(String datoNom,
            String datoAddress, int datoTelephon) {//devuelve el dni

        ArrayList<Empresa> insertEmpresa = new ArrayList<>();

        try {

            String insert = "INSERT INTO empresa (nom, address, telephon) VALUES (?, ?, ?)";
            PreparedStatement preparedStatement = conn.Conexion.getconexion().prepareStatement(insert);
            preparedStatement.setString(1, datoNom);
            preparedStatement.setString(2, datoAddress);
            preparedStatement.setInt(3, datoTelephon);

            preparedStatement.executeUpdate();
            preparedStatement.close();

            return insertEmpresa;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return insertEmpresa;
    }
}
