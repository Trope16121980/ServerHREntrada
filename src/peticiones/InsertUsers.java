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
import modelo.Users;

/**
 *
 * @author Gustavo_Senorans
 */
public class InsertUsers {

	public static ArrayList<Users> insertUser(String crud, String nombreTabla, String login, String datoLogin,
			String pass, String datoPass, String numTipe, String datoNumTipe, String dni, String datoDni,
			String palabraAbuscar, ObjectOutputStream outObjeto, Socket client) throws IOException {

		ArrayList<Users> insertUser = new ArrayList<>();

		try {
			String consulta = "SELECT * FROM empleados where dni = ?";
			PreparedStatement preparedStatement = conn.Conexion.getconexion().prepareStatement(consulta);
			preparedStatement.setString(1, datoDni);
			ResultSet resultSet = preparedStatement.executeQuery();
			if (!resultSet.next()) {
				Errores error = new Errores();
				String errorInsertUsers = error.errorInsertUsers();
				System.out.println(errorInsertUsers);
				outObjeto = new ObjectOutputStream(client.getOutputStream());
				outObjeto.writeObject(errorInsertUsers);
				outObjeto.flush();
			} else {

				String insert = "INSERT INTO users (login, pass, numtipe, dni) VALUES (?, ?, ?, ?)";
				preparedStatement = conn.Conexion.getconexion().prepareStatement(insert);
				preparedStatement.setString(1, datoLogin);
				preparedStatement.setString(2, datoPass);
				preparedStatement.setInt(3, Integer.parseInt(datoNumTipe));
				preparedStatement.setString(4, datoDni);

				preparedStatement.executeUpdate();
				preparedStatement.close();

				Users nuevoUser = new Users(datoLogin, datoPass, Integer.parseInt(datoNumTipe), datoDni);
				insertUser.add(nuevoUser);

				return insertUser;
			}
		} catch (SQLException e) {
			Errores error = new Errores();
			String errorInsertUsers = error.errorInsertUsers();
			System.out.println(errorInsertUsers);
			outObjeto = new ObjectOutputStream(client.getOutputStream());
			outObjeto.writeObject(errorInsertUsers);
			outObjeto.flush();
		}
		return insertUser;
	}
}
