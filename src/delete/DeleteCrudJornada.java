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

    public static void handleSearchRequest(String crud, String nombreTabla, String dni, String datoDni, String fecha,
            String datoFecha, String palabraAbuscar, ObjectOutputStream outObjeto, Socket client) throws IOException {

       if (crud.equals("3")) {
            if (nombreTabla.equals("3")) {
                List<Jornada> deleteJornada = new ArrayList<Jornada>();
                deleteJornada = DeleteJornada.deleteJornada(crud, nombreTabla, dni, datoDni,fecha,datoFecha, palabraAbuscar,datoDni, outObjeto, client);

                if (deleteJornada.isEmpty()) {
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
