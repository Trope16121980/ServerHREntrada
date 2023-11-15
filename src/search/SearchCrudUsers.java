package search;

import modelo.Users;
import print.PrintUsers;
import java.util.List;

import errores.Errores;

import java.io.ObjectOutputStream;
import java.io.IOException;
import java.net.Socket;
import peticiones.Listausers;

/**
 * @author Gustavo Senoráns Varela
 * @version 1.4, 15/11/2023
 * @since jdk 17
 */
public class SearchCrudUsers {

    /**
     * Este método verifica los datos y hace el envio de los datos al cliente en
     * forma de objeto o si no le envia un mensaje de error y se envia los datos
     * a imprimir al server
     *
     * @param crud en este caso es el 0 de select
     * @param nombreTabla en este caso es el 1, ya que se refiere a usuarios
     * @param columna en este caso es dni el nombre de la columna
     * @param palabraAbuscar el array con los datos
     * @param outObjeto el objeto a enviar al cliente
     * @param client el socket del cliente
     * @throws IOException controla los errores
     */
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
                    String errorDni = error.errorDni();
                    System.out.println(errorDni);
                    outObjeto = new ObjectOutputStream(client.getOutputStream());
                    outObjeto.writeObject(errorDni);
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
