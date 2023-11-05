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

public class UpdateNomempresa {

	public static ArrayList<Empresa> updateNomempresa(String crud, String nombreTabla, String nomNuevo,
			String datoNomnuevo, String nom, String datoNom, String palabraAbuscar, ObjectOutputStream outObjeto,
			Socket client) throws IOException {
		ArrayList<Empresa> empresa = new ArrayList<Empresa>();
		try {

			String nombreNuevo = "SELECT * FROM empresa where nom = ?";
			PreparedStatement psNom = conn.Conexion.getconexion().prepareStatement(nombreNuevo);
			psNom.setString(1, datoNom);
			ResultSet rsNom = psNom.executeQuery();
			if (!rsNom.next()) {
				Errores error = new Errores();
				String erroNomEmpresa = error.erroNomEmpresa();
				System.out.println(erroNomEmpresa);
				outObjeto = new ObjectOutputStream(client.getOutputStream());
				outObjeto.writeObject(erroNomEmpresa);
				outObjeto.flush();

			} else {
				datoNom = rsNom.getString("nom");
				String address = rsNom.getString("address");
				String telephon = rsNom.getString("telephon");

				String consulta = "update empresa set nom = ? where nom = ?";
				PreparedStatement preparedStatement = conn.Conexion.getconexion().prepareStatement(consulta);
				preparedStatement.setString(1, datoNomnuevo);
				preparedStatement.setString(2, datoNom);

				preparedStatement.executeUpdate();
				preparedStatement.close();

				Empresa nuevaJornada = new Empresa(datoNom, address, Integer.parseInt(telephon));
				empresa.add(nuevaJornada);

			}

		} catch (SQLException e) {
			Errores error = new Errores();
			String errorUpdateEmpresa = error.errorUpdateEmpresa();
			System.out.println(errorUpdateEmpresa);
			outObjeto = new ObjectOutputStream(client.getOutputStream());
			outObjeto.writeObject(errorUpdateEmpresa);
			outObjeto.flush();
		}
		return empresa;
	}

}
