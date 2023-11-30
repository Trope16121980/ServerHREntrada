package delete;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import modelo.*;
import peticiones.*;

/**
 * @author Gustavo Senoráns Varela
 * @version 1.4, 15/11/2023
 * @since jdk 17
 */
public class DeleteCrudUsers {

    public static void handleDeleteUsers(String crud, String nombreTabla, String dni, String datoDni, String palabraAbuscar,
            ObjectOutputStream outObjeto, Socket client) throws IOException {

        if (crud.equals("3")) {
            if (nombreTabla.equals("1")) {
                List<Users> deleteUsers = new ArrayList<Users>();
                deleteUsers = DeleteUsers.deleteUsers(crud, nombreTabla, dni, datoDni, palabraAbuscar, datoDni, outObjeto, client);

                if (deleteUsers.isEmpty()) {
                    System.out.println("\nUsuario eliminado correctamente");
                    System.out.println("____________________________________________________________________");
                    outObjeto = new ObjectOutputStream(client.getOutputStream());
                    outObjeto.writeObject(deleteUsers);
                    outObjeto.flush();
                } else {
                    System.out.println("____________________________________________________________________");
                }
            }
        }
    }
}
