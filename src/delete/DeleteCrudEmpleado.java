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

    /**
     * Este metodo envia los datos al metodo DeleteEmpleado.deleteEmpleado para
     * ser aliminado y después enviar el objeto al cliente e imprir en el text
     * area del servidor
     *
     * @param crud en este caso es el 3 de delete
     * @param nombreTabla en este caso es el 0, ya que se refiere a los
     * empleados
     * @param dni el nombre de la columna original de la tabla
     * @param datoDni del dni el empleado
     * @param palabraAbuscar al array que contiene los datos
     * @param outObjeto el objeto que contiene el array
     * @param client el socket del cliente al que se le envían los datos
     * @throws IOException controla los errores
     */
    public static void handleDeleteEmpleado(String crud, String nombreTabla, String dni, String datoDni, String palabraAbuscar,
            ObjectOutputStream outObjeto, Socket client) throws IOException {

        if (crud.equals("3")) {
            if (nombreTabla.equals("0")) {
                List<Empleados> deleteEmpleado = new ArrayList<Empleados>();
                deleteEmpleado = DeleteEmpleado.deleteEmpleado(crud, nombreTabla, dni, datoDni, palabraAbuscar, datoDni, outObjeto, client);

                if (!deleteEmpleado.isEmpty()) {
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
