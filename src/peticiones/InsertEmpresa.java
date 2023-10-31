package peticiones;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import errores.Errores;
import modelo.Empresa;

/**
 *
 * @author Gustavo_Senorans
 */
public class InsertEmpresa {

	public static ArrayList<Empresa> insertEmpresa(String crud, String nombreTabla, String nom, String datoNom,
			String address, String datoAddress, String telephon, String datoTelephon, String palabraAbuscar,
			ObjectOutputStream outObjeto, Socket client) throws IOException {

		ArrayList<Empresa> insertEmpresa = new ArrayList<>();

		try {
			String consulta = "SELECT * FROM empresa where nom = ?";
			PreparedStatement preparedStatement = conn.Conexion.getconexion().prepareStatement(consulta);
			preparedStatement.setString(1, datoNom);
			ResultSet resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				Errores error = new Errores();
				String errorInsertEmpresa = error.errorInsertEmpresa();
				System.out.println(errorInsertEmpresa);
				outObjeto = new ObjectOutputStream(client.getOutputStream());
				outObjeto.writeObject(errorInsertEmpresa);
				outObjeto.flush();
			} else {
				String insert = "INSERT INTO empresa (nom, address, telephon) VALUES (?, ?, ?)";
				preparedStatement = conn.Conexion.getconexion().prepareStatement(insert);
				preparedStatement.setString(1, datoNom);
				preparedStatement.setString(2, datoAddress);
				preparedStatement.setInt(3, Integer.parseInt(datoTelephon));

				preparedStatement.executeUpdate();
				preparedStatement.close();
				
				Empresa nuevaEmpresa = new Empresa(datoNom, datoAddress, Integer.parseInt(datoTelephon));
				insertEmpresa.add(nuevaEmpresa); 
				
				return insertEmpresa;
			}
		} catch (SQLException e) {
			Errores error = new Errores();
			String errorInsertEmpresa = error.errorInsertEmpresa();
			System.out.println(errorInsertEmpresa);
			outObjeto = new ObjectOutputStream(client.getOutputStream());
			outObjeto.writeObject(errorInsertEmpresa);
			outObjeto.flush();
		}
		return insertEmpresa;
	}
}
