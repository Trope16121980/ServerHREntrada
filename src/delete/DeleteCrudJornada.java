package delete;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.List;
import java.util.ArrayList;
import modelo.Jornada;
import peticiones.DeleteJornada;

/**
 * @author Gustavo Senoráns Varela
 * @version 1.4, 15/11/2023
 * @since jdk 17
 */
public class DeleteCrudJornada {

    /**
     * Este metodo envia los datos al metodo DeleteJornada.deleteJornada para
     * ser aliminada y después enviar el objeto al cliente e imprir en el text
     * area del servidor
     *
     * @param crud en este caso es el 3 de delete
     * @param nombreTabla en este caso es el 0, ya que se refiere a los
     * empleados
     * @param dni el nombre de la columna original de la tabla
     * @param datoDni del dni el empleado
     * @param fecha el nombre de la columna original de la tabla
     * @param datoFecha la fecha en la que el empleado inicio la jorda que
     * queremos eliminar
     * @param palabraAbuscar al array que contiene los datos
     * @param outObjeto el objeto que contiene el array
     * @param client el socket del cliente al que se le envían los datos
     * @throws IOException controla los errores
     */
    public static void handleSearchRequest(String crud, String nombreTabla, String dni, String datoDni, String fecha,
            String datoFecha, String palabraAbuscar, ObjectOutputStream outObjeto, Socket client) throws IOException {

        if (crud.equals("3")) {
            if (nombreTabla.equals("3")) {
                List<Jornada> deleteJornada = new ArrayList<Jornada>();
                deleteJornada = DeleteJornada.deleteJornada(crud, nombreTabla, dni, datoDni, fecha, datoFecha, palabraAbuscar, datoDni, outObjeto, client);

                if (!deleteJornada.isEmpty()) {
                    System.out.println("\nJornada eliminada correctamente");
                    System.out.println("____________________________________________________________________");
                    outObjeto = new ObjectOutputStream(client.getOutputStream());
                    outObjeto.writeObject(deleteJornada);
                    outObjeto.flush();
                } else {
                    System.out.println("____________________________________________________________________");
                }
            }
        }
    }
}
