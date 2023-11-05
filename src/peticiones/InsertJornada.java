package peticiones;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import errores.Errores;
import fecha.Fechas;
import modelo.Jornada;

public class InsertJornada {
	static Fechas fecha = new Fechas();

	public static ArrayList<Jornada> insertJornada(String crud, String nombreTabla, String dni, String datoDni,
			String palabraAbuscar, ObjectOutputStream outObjeto, Socket client) throws IOException {

		ArrayList<Jornada> insertJornada = new ArrayList<>();
		try {
			String consulta = "SELECT * FROM empleados where dni = ?";
			PreparedStatement preparedStatement = conn.Conexion.getconexion().prepareStatement(consulta);
			preparedStatement.setString(1, datoDni);
			ResultSet resultSet = preparedStatement.executeQuery();
			if (!resultSet.next()) {
				Errores error = new Errores();
				String errorDni = error.errorDni();
				System.out.println(errorDni);
				outObjeto = new ObjectOutputStream(client.getOutputStream());
				outObjeto.writeObject(errorDni);
				outObjeto.flush();

			} else {

				try {
					String horasalidaNull = "SELECT * FROM jornada where dni = ? and horasalida = ?";
					PreparedStatement psHorasalida = conn.Conexion.getconexion().prepareStatement(horasalidaNull);
					psHorasalida.setString(1, datoDni);
					psHorasalida.setString(2, "nulo");
					ResultSet rsHorasalida = psHorasalida.executeQuery();
					if (rsHorasalida.next()) {
						Errores error = new Errores();
						String errorInsertJornadaDentro = error.errorInsertJornadaDentro();
						System.out.println(errorInsertJornadaDentro);
						outObjeto = new ObjectOutputStream(client.getOutputStream());
						outObjeto.writeObject(errorInsertJornadaDentro);
						outObjeto.flush();
					} else {
						datoDni = resultSet.getString("dni");
						String datoNom = resultSet.getString("nom");
						String datoApellido = resultSet.getString("apellido");
						String datoCodicard = resultSet.getString("codicard");
						String datoHoraentrada = fecha.hora();
						String datoHorasalida = "nulo";
						String datoTotal = "nulo";
						String datoFecha = fecha.fecha_Jornada();

						String insert = "INSERT INTO jornada (dni,nom,apellido,codicard,horaentrada,horasalida,total,fecha) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
						preparedStatement = conn.Conexion.getconexion().prepareStatement(insert);
						preparedStatement.setString(1, datoDni);
						preparedStatement.setString(2, datoNom);
						preparedStatement.setString(3, datoApellido);
						preparedStatement.setInt(4, Integer.parseInt(datoCodicard));
						preparedStatement.setString(5, datoHoraentrada);
						preparedStatement.setString(6, datoHorasalida);
						preparedStatement.setString(7, datoTotal);
						preparedStatement.setString(8, datoFecha);

						preparedStatement.executeUpdate();
						preparedStatement.close();

						Jornada nuevaJornada = new Jornada(datoDni, datoNom, datoApellido,
								Integer.parseInt(datoCodicard), datoHoraentrada, datoHorasalida, datoTotal, datoFecha);
						insertJornada.add(nuevaJornada);

					}
				} catch (SQLException e) {
					Errores error = new Errores();
					String errorInsertJornadaDentro = error.errorInsertJornadaDentro();
					System.out.println(errorInsertJornadaDentro);
					outObjeto = new ObjectOutputStream(client.getOutputStream());
					outObjeto.writeObject(errorInsertJornadaDentro);
					outObjeto.flush();
				}
			}
		} catch (SQLException e) {
			Errores error = new Errores();
			String errorDni = error.errorDni();
			System.out.println(errorDni);
			outObjeto = new ObjectOutputStream(client.getOutputStream());
			outObjeto.writeObject(errorDni);
			outObjeto.flush();
		}
		return insertJornada;

	}

	public static ArrayList<Jornada> insertJornadaCodicard(String crud, String nombreTabla, String codicard, int datoCodicard,
			String palabraAbuscar, ObjectOutputStream outObjeto, Socket client) throws IOException {

		ArrayList<Jornada> insertJornadaCodicard = new ArrayList<>();
		try {
			String consulta = "SELECT * FROM empleados where codicard = ?";
			PreparedStatement preparedStatement = conn.Conexion.getconexion().prepareStatement(consulta);
			preparedStatement.setInt(1, datoCodicard);
			ResultSet resultSet = preparedStatement.executeQuery();
			if (!resultSet.next()) {
				Errores error = new Errores();
				String erroCodicard = error.erroCodicard();
				System.out.println(erroCodicard);
				outObjeto = new ObjectOutputStream(client.getOutputStream());
				outObjeto.writeObject(erroCodicard);
				outObjeto.flush();

			} else {

				try {
					String horasalidaNull = "SELECT * FROM jornada where codicard = ? and horasalida = ?";
					PreparedStatement psHorasalida = conn.Conexion.getconexion().prepareStatement(horasalidaNull);
					psHorasalida.setInt(1, datoCodicard);
					psHorasalida.setString(2, "nulo");
					ResultSet rsHorasalida = psHorasalida.executeQuery();
					if (rsHorasalida.next()) {
						Errores error = new Errores();
						String errorInsertJornadaDentro = error.errorInsertJornadaDentro();
						System.out.println(errorInsertJornadaDentro);
						outObjeto = new ObjectOutputStream(client.getOutputStream());
						outObjeto.writeObject(errorInsertJornadaDentro);
						outObjeto.flush();
					} else {
						String datoDni = resultSet.getString("dni");
						String datoNom = resultSet.getString("nom");
						String datoApellido = resultSet.getString("apellido");
						datoCodicard = resultSet.getInt("codicard");
						String datoHoraentrada = fecha.hora();
						String datoHorasalida = "nulo";
						String datoTotal = "nulo";
						String datoFecha = fecha.fecha_Jornada();

						String insert = "INSERT INTO jornada (dni,nom,apellido,codicard,horaentrada,horasalida,total,fecha) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
						preparedStatement = conn.Conexion.getconexion().prepareStatement(insert);
						preparedStatement.setString(1, datoDni);
						preparedStatement.setString(2, datoNom);
						preparedStatement.setString(3, datoApellido);
						preparedStatement.setInt(4, datoCodicard);
						preparedStatement.setString(5, datoHoraentrada);
						preparedStatement.setString(6, datoHorasalida);
						preparedStatement.setString(7, datoTotal);
						preparedStatement.setString(8, datoFecha);

						preparedStatement.executeUpdate();
						preparedStatement.close();

						Jornada nuevaJornadaCodicard = new Jornada(datoDni, datoNom, datoApellido,
								datoCodicard, datoHoraentrada, datoHorasalida, datoTotal, datoFecha);
						insertJornadaCodicard.add(nuevaJornadaCodicard);

					}
				} catch (SQLException e) {
					Errores error = new Errores();
					String errorInsertJornadaDentro = error.errorInsertJornadaDentro();
					System.out.println(errorInsertJornadaDentro);
					outObjeto = new ObjectOutputStream(client.getOutputStream());
					outObjeto.writeObject(errorInsertJornadaDentro);
					outObjeto.flush();
				}
			}
		} catch (SQLException e) {
			Errores error = new Errores();
			String erroCodicard = error.erroCodicard();
			System.out.println(erroCodicard);
			outObjeto = new ObjectOutputStream(client.getOutputStream());
			outObjeto.writeObject(erroCodicard);
			outObjeto.flush();
		}
		return insertJornadaCodicard;

	}
}
