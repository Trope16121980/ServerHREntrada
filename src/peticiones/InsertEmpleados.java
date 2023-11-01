package peticiones;

import modelo.Empleados;
import modelo.Users;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Random;

import errores.Errores;

/**
 *
 * @author Gustavo_Senorans
 */
public class InsertEmpleados {

	public static ArrayList<Empleados> insertEmpleados(String crud, String nombreTabla, String dni, String datoDni,
			String nom, String datoNom, String apellido, String datoApellido, String nomempresa, String datoNomempresa,
			String departament, String datoDepartament, String codicard, int datoCodicard, String mail,
			String datoMail, String telephon, String datoTelephon, String palabraAbuscar, ObjectOutputStream outObjeto,
			Socket client) throws IOException {

		ArrayList<Empleados> insertEmpleados = new ArrayList<>();
		try {

			String dniEmpleado = "SELECT * FROM empleados where dni = ?";
			PreparedStatement prDni = conn.Conexion.getconexion().prepareStatement(dniEmpleado);
			prDni.setString(1, datoDni);
			ResultSet rsDni = prDni.executeQuery();
			if (rsDni.next()) {
				Errores error = new Errores();
				String errorInsertEmpleadoDni = error.errorInsertEmpleadoDni();
				System.out.println(errorInsertEmpleadoDni);
				outObjeto = new ObjectOutputStream(client.getOutputStream());
				outObjeto.writeObject(errorInsertEmpleadoDni);
				outObjeto.flush();
			} else {
				try {
					String nomempresaEmpleado = "SELECT * FROM empresa where nom = ?";
					PreparedStatement psNomempresa = conn.Conexion.getconexion().prepareStatement(nomempresaEmpleado);
					psNomempresa.setString(1, datoNomempresa);
					ResultSet rsNomempresa = psNomempresa.executeQuery();
					if (!rsNomempresa.next()) {
						Errores error = new Errores();
						String errorInsertEmpleadoNomempresa = error.errorInsertEmpleadoNomempresa();
						System.out.println(errorInsertEmpleadoNomempresa);
						outObjeto = new ObjectOutputStream(client.getOutputStream());
						outObjeto.writeObject(errorInsertEmpleadoNomempresa);
						outObjeto.flush();
					} else {
						try {
							
							 if (datoCodicard == 0) {
			                        Random random = new Random();
			                        datoCodicard = random.nextInt(100000); 
			                    }
							
							String codicardEmpleado = "SELECT * FROM empleados where codicard = ?";
							PreparedStatement psCodicard = conn.Conexion.getconexion()
									.prepareStatement(codicardEmpleado);
							psCodicard.setInt(1, datoCodicard);
							ResultSet rsCodicard = psCodicard.executeQuery();
							if (rsCodicard.next()) {
								Errores error = new Errores();
								String errorInsertEmpleadoCodicard = error.errorInsertEmpleadoCodicard();
								System.out.println(errorInsertEmpleadoCodicard);
								outObjeto = new ObjectOutputStream(client.getOutputStream());
								outObjeto.writeObject(errorInsertEmpleadoCodicard);
								outObjeto.flush();
							} else {

								String insert = "INSERT INTO empleados (dni, nom, apellido, nomempresa, departament, codicard, mail, telephon) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
								PreparedStatement preparedStatement = conn.Conexion.getconexion()
										.prepareStatement(insert);
								preparedStatement.setString(1, datoDni);
								preparedStatement.setString(2, datoNom);
								preparedStatement.setString(3, datoApellido);
								preparedStatement.setString(4, datoNomempresa);
								preparedStatement.setString(5, datoDepartament);
								preparedStatement.setInt(6, datoCodicard);
								preparedStatement.setString(7, datoMail);
								preparedStatement.setInt(8, Integer.parseInt(datoTelephon));

								preparedStatement.executeUpdate();
								preparedStatement.close();

								Empleados nuevoEmpleado = new Empleados(datoDni, datoNom, datoApellido, datoNomempresa,
										datoDepartament, datoCodicard, datoMail,
										Integer.parseInt(datoTelephon));
								insertEmpleados.add(nuevoEmpleado);
								return insertEmpleados;

							}
						} catch (SQLException e) {
							Errores error = new Errores();
							String errorInsertEmpleadoCodicard = error.errorInsertEmpleadoCodicard();
							System.out.println(errorInsertEmpleadoCodicard);
							outObjeto = new ObjectOutputStream(client.getOutputStream());
							outObjeto.writeObject(errorInsertEmpleadoCodicard);
							outObjeto.flush();
						}
					}
				} catch (SQLException e) {
					Errores error = new Errores();
					String errorInsertEmpleadoNomempresa = error.errorInsertEmpleadoNomempresa();
					System.out.println(errorInsertEmpleadoNomempresa);
					outObjeto = new ObjectOutputStream(client.getOutputStream());
					outObjeto.writeObject(errorInsertEmpleadoNomempresa);
					outObjeto.flush();
				}
			}
		} catch (SQLException e) {
			Errores error = new Errores();
			String errorInsertEmpleadoDni = error.errorInsertEmpleadoDni();
			System.out.println(errorInsertEmpleadoDni);
			outObjeto = new ObjectOutputStream(client.getOutputStream());
			outObjeto.writeObject(errorInsertEmpleadoDni);
			outObjeto.flush();
		}
		return insertEmpleados;
	}
}
