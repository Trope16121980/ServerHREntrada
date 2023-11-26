package update;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import modelo.Users;
import peticiones.UpdateUsers;

/**
 * @author Gustavo Senoráns Varela
 * @version 1.4, 15/11/2023
 * @since jdk 17
 */
public class UpdateCrudUsers {

    /**
     * Este metodo envia los datos al metodo UpdateUsers.updateUsers para ser
     * modificado y después enviar el objeto al cliente e imprir en el text area
     * del servidor
     *
     * @param crud en este caso es el 2 de update
     * @param nombreTabla en este caso es el 1, ya que se refiere a usuarios
     * @param passNuevo el nombre de la columna de la tabla nombretabla+Nuevo
     * @param datoPassNuevo el password nuevo del usuario
     * @param numtipeNuevo el nombre de la columna de la tabla nombretabla+Nuevo
     * @param datoNumtipeNuevo el tipo de usuario
     * @param login el nombre de la columna original de la tabla
     * @param datoLogin el login original del empleado
     * @param palabraAbuscar al array que contiene los datos
     * @param palabra variable necesaria para las modificaciones
     * @param outObjeto el objeto que contiene el array
     * @param client el socket del cliente al que se le envían los datos
     * @throws IOException controla los errores
     */
    public static void handleSearchRequest(String crud, String nombreTabla,
            String passNuevo, String datoPassNuevo,
            String numtipeNuevo, int datoNumtipeNuevo,
            String login, String datoLogin,
            String palabraAbuscar, String palabra, ObjectOutputStream outObjeto, Socket client) throws IOException {

        if (crud.equals("2")) {
            if (nombreTabla.equals("1")) {
                List<Users> updateUsers = new ArrayList<Users>();
                updateUsers = UpdateUsers.updateUsers(crud, nombreTabla,
                        passNuevo, datoPassNuevo,
                        numtipeNuevo, datoNumtipeNuevo,
                        login, datoLogin,
                        palabraAbuscar, palabra, outObjeto, client);

                if (!updateUsers.isEmpty()) {
                    System.out.println("\nUsuario modificado correctamente:");
                    Users users = updateUsers.get(0);
                    System.out.println("\nLogin: " + users.getLogin());
                    System.out.println("\nPass: " + datoPassNuevo);
                    System.out.println("Numtipe: " + datoNumtipeNuevo);
                    System.out.println("\nDni: " + users.getDni());
                    System.out.println("____________________________________________________________________");

                    outObjeto = new ObjectOutputStream(client.getOutputStream());
                    outObjeto.writeObject(updateUsers);
                    outObjeto.flush();
                } else {
                    System.out.println("____________________________________________________________________");
                }
            }
        }
    }

}
