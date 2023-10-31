package insert;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import modelo.Users;
import peticiones.InsertUsers;

/**
 *
 * @author Gustavo_Senorans
 */
public class InsertCrudUsers {

	public static void handleInsertRequest(String crud, String nombreTabla, String login, String datoLogin, String pass,
			String datoPass, String numTipe, String datoNumTipe, String dni, String datoDni, String palabraAbuscar,
			ObjectOutputStream outObjeto, Socket client) throws IOException {

		if (crud.equals("1")) {
			if (nombreTabla.equals("1")) {
				List<Users> insertUser = new ArrayList<Users>();
				insertUser = InsertUsers.insertUser(crud, nombreTabla, login, datoLogin, pass, datoPass, numTipe,
						datoNumTipe, dni, datoDni, palabraAbuscar, outObjeto, client);
				if (!insertUser.isEmpty()) {
					System.out.println(("Empleado creado correctamente, sus datos son: \n"));
					System.out.println("Login: " + datoLogin + "\n" + "Pass: " + datoPass + "\n" + "Num Tipe: "
							+ datoNumTipe + "\n" + "Dni: " + datoDni + "\n");
					System.out.println("____________________________________________________________________");
					outObjeto = new ObjectOutputStream(client.getOutputStream());
					outObjeto.writeObject(insertUser);
					outObjeto.flush();
				} else {
					System.out.println("____________________________________________________________________");
				}
			}
		}
	}

}
