
package search;

import modelo.Users;
import print.PrintUsers;
import java.util.ArrayList;
import java.util.List;
import java.io.ObjectOutputStream;
import java.io.IOException;
import java.net.Socket;
import peticiones.Listausers;

/**
 *
 * @author Gustavo_Senorans
 */
public class SearchCrudUsers {

    public static void handleSearchRequest(String crud, String nombreTabla, String columna,
            String palabraAbuscar, ObjectOutputStream outObjeto, Socket client) throws IOException {

        if (crud.equals("0")) {
            if (nombreTabla.equals("1") && columna.equals("dni")) {
                PrintUsers usuarios = new PrintUsers();
                List<Users> listaToUsersDni = new ArrayList<Users>();
                listaToUsersDni = Listausers.listaTotalUsersDni(palabraAbuscar);

                for (int i = 0; i < listaToUsersDni.size(); i++) {
                    if (columna.equals("dni") && palabraAbuscar.equals(listaToUsersDni.get(i).getDni())) {
                        String datosUsers = usuarios.obtenerDatosUsers(listaToUsersDni, columna);
                        System.out.println(datosUsers);
                    }

                    outObjeto = new ObjectOutputStream(client.getOutputStream());
                    outObjeto.writeObject(listaToUsersDni);
                    outObjeto.flush();
                }
            } else if (nombreTabla.equals("1") && columna.equals("login")) {
                PrintUsers usuarios = new PrintUsers();
                List<Users> listaTotalUsersLogin = new ArrayList<Users>();
                listaTotalUsersLogin = Listausers.listaTotalUsersLogin(palabraAbuscar);

                for (int i = 0; i < listaTotalUsersLogin.size(); i++) {
                    if (columna.equals("login") && palabraAbuscar.equals(listaTotalUsersLogin.get(i).getLogin())) {
                        String datosUsers = usuarios.obtenerDatosUsers(listaTotalUsersLogin, columna);
                        System.out.println(datosUsers);
                    }

                    outObjeto = new ObjectOutputStream(client.getOutputStream());
                    outObjeto.writeObject(listaTotalUsersLogin);
                    outObjeto.flush();
                }
            } else if (nombreTabla.equals("1") && columna.equals("numtipe")) {
                PrintUsers usuarios = new PrintUsers();
                List<Users> listaTotalUsersTipe = new ArrayList<Users>();
                listaTotalUsersTipe = Listausers.listaTotalUsersTipe(Integer.parseInt(palabraAbuscar));
                for (int i = 0; i < listaTotalUsersTipe.size(); i++) {

                    String numtipe = String.valueOf(listaTotalUsersTipe.get(i).getNumtipe());

                    if (columna.equals("numtipe") && palabraAbuscar.equals(numtipe)) {
                        String datosUsers = usuarios.obtenerDatosUsers(listaTotalUsersTipe, columna);
                        System.out.println(datosUsers);
                    }

                    outObjeto = new ObjectOutputStream(client.getOutputStream());
                    outObjeto.writeObject(listaTotalUsersTipe);
                    outObjeto.flush();
                }
            }
        }
    }
}
