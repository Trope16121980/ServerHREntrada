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
public class DeleteCrudEmpleado {

    public static void handleDeleteEmpleado(String crud, String nombreTabla, String dni, String datoDni, String palabraAbuscar,
            ObjectOutputStream outObjeto, Socket client) throws IOException {

        if (crud.equals("3")) {
            if (nombreTabla.equals("0")) {
                List<Empleados> deleteEmpleado = new ArrayList<Empleados>();
                deleteEmpleado = DeleteEmpleado.deleteEmpleado(crud, nombreTabla, dni, datoDni, palabraAbuscar, datoDni, outObjeto, client);

                if (deleteEmpleado.isEmpty()) {
                    System.out.println("\nEmpleado eliminado correctamente y ademas se ha\n"
                            + "eliminado el usuario y sus jornadas correctamente si es que existe alguna.");
                    System.out.println("____________________________________________________________________");
                    outObjeto = new ObjectOutputStream(client.getOutputStream());
                    outObjeto.writeObject(deleteEmpleado);
                    outObjeto.flush();
                } else {
                    System.out.println("____________________________________________________________________");
                }
            }
        }
    }
}
