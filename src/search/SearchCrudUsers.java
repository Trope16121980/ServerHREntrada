
package search;

import modelo.Users;
import print.PrintUsers;
import java.util.ArrayList;
import java.util.List;

import errores.Errores;

import java.io.ObjectOutputStream;
import java.io.IOException;
import java.net.Socket;
import peticiones.Listausers;

/**
 *
 * @author Gustavo_Senorans
 */
public class SearchCrudUsers {

	public static void handleSearchRequest(String crud, String nombreTabla, String columna, String palabraAbuscar,
			ObjectOutputStream outObjeto, Socket client) throws IOException {

		if (crud.equals("0")) {
			if (nombreTabla.equals("1") && columna.equals("dni")) {
				PrintUsers usuarios = new PrintUsers();
				List<Users> listaToUsersDni = Listausers.listaTotalUsersDni(palabraAbuscar);
				if (!listaToUsersDni.isEmpty()) {

					String datosUsers = usuarios.obtenerDatosUsers(listaToUsersDni, columna);
					System.out.println(datosUsers);
					outObjeto = new ObjectOutputStream(client.getOutputStream());
					outObjeto.writeObject(listaToUsersDni);
					outObjeto.flush();
				} else {
					Errores error = new Errores();
					String erroDniUser = error.erroDniUser();
					System.out.println(erroDniUser);
					outObjeto = new ObjectOutputStream(client.getOutputStream());
					outObjeto.writeObject(erroDniUser);
					outObjeto.flush();
				}

			} else if (nombreTabla.equals("1") && columna.equals("login")) {
				PrintUsers usuarios = new PrintUsers();
				List<Users> listaTotalUsersLogin = Listausers.listaTotalUsersLogin(palabraAbuscar);
				if (!listaTotalUsersLogin.isEmpty()) {
					String datosUsers = usuarios.obtenerDatosUsers(listaTotalUsersLogin, columna);
					System.out.println(datosUsers);
					outObjeto = new ObjectOutputStream(client.getOutputStream());
					outObjeto.writeObject(listaTotalUsersLogin);
					outObjeto.flush();
				} else {
					Errores error = new Errores();
					String erroLoginUser = error.erroLoginUser();
					System.out.println(erroLoginUser);
					outObjeto = new ObjectOutputStream(client.getOutputStream());
					outObjeto.writeObject(erroLoginUser);
					outObjeto.flush();
				}
			} else if (nombreTabla.equals("1") && columna.equals("numtipe")) {
				PrintUsers usuarios = new PrintUsers();
				List<Users> listaTotalUsersTipe = Listausers.listaTotalUsersTipe(Integer.parseInt(palabraAbuscar));
				if (!listaTotalUsersTipe.isEmpty()) {
					String datosUsers = usuarios.obtenerDatosUsers(listaTotalUsersTipe, columna);
					System.out.println(datosUsers);
					outObjeto = new ObjectOutputStream(client.getOutputStream());
					outObjeto.writeObject(listaTotalUsersTipe);
					outObjeto.flush();

				} else {
					Errores error = new Errores();
					String erroNumTipeUser = error.erroNumTipeUser();
					System.out.println(erroNumTipeUser);
					outObjeto = new ObjectOutputStream(client.getOutputStream());
					outObjeto.writeObject(erroNumTipeUser);
					outObjeto.flush();
				}
			}
		}
	}
}
