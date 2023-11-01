package peticiones;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Random;

import errores.Errores;
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

				try {
					String dniUser = "SELECT * FROM users where dni = ?";
					PreparedStatement psDni = conn.Conexion.getconexion().prepareStatement(dniUser);
					psDni.setString(1, datoDni);
					ResultSet rsDni = psDni.executeQuery();
					if (rsDni.next()) {
						Errores error = new Errores();
						String errorInsertUsersDni = error.errorInsertUsersDni();
						System.out.println(errorInsertUsersDni);
						outObjeto = new ObjectOutputStream(client.getOutputStream());
						outObjeto.writeObject(errorInsertUsersDni);
						outObjeto.flush();
					} else {

						try {
							String loginUser = "SELECT * FROM users where login = ?";
							PreparedStatement psLogin = conn.Conexion.getconexion().prepareStatement(loginUser);
							psLogin.setString(1, datoLogin);
							ResultSet rsLogin = psLogin.executeQuery();
							if (rsLogin.next()) {
								Errores error = new Errores();
								String errorInsertUsersLogin = error.errorInsertUsersLogin();
								System.out.println(errorInsertUsersLogin);
								outObjeto = new ObjectOutputStream(client.getOutputStream());
								outObjeto.writeObject(errorInsertUsersLogin);
								outObjeto.flush();
							} else {

								int numTipeValue = Integer.parseInt(datoNumTipe);
								if (numTipeValue == 0 || numTipeValue == 1) {

									String insert = "INSERT INTO users (login, pass, numtipe, dni) VALUES (?, ?, ?, ?)";
									preparedStatement = conn.Conexion.getconexion().prepareStatement(insert);
									preparedStatement.setString(1, datoLogin);
									preparedStatement.setString(2, datoPass);
									preparedStatement.setInt(3, Integer.parseInt(datoNumTipe));
									preparedStatement.setString(4, datoDni);

									preparedStatement.executeUpdate();
									preparedStatement.close();

									Users nuevoUser = new Users(datoLogin, datoPass, Integer.parseInt(datoNumTipe),
											datoDni);
									insertUser.add(nuevoUser);

									return insertUser;

								} else {
									Errores error = new Errores();
									String errorInsertEmpleadoNumtipe = error.errorInsertEmpleadoNumtipe();
									System.out.println(errorInsertEmpleadoNumtipe);
									outObjeto = new ObjectOutputStream(client.getOutputStream());
									outObjeto.writeObject(errorInsertEmpleadoNumtipe);
									outObjeto.flush();

								}
							}
						} catch (SQLException e) {
							Errores error = new Errores();
							String errorInsertUsersLogin = error.errorInsertUsersLogin();
							System.out.println(errorInsertUsersLogin);
							outObjeto = new ObjectOutputStream(client.getOutputStream());
							outObjeto.writeObject(errorInsertUsersLogin);
							outObjeto.flush();
						}
					}
				} catch (SQLException e) {
					Errores error = new Errores();
					String errorInsertUsersDni = error.errorInsertUsersDni();
					System.out.println(errorInsertUsersDni);
					outObjeto = new ObjectOutputStream(client.getOutputStream());
					outObjeto.writeObject(errorInsertUsersDni);
					outObjeto.flush();
				}
			}
		} catch (

		SQLException e) {
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
