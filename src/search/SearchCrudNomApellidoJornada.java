package search;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.List;

import errores.Errores;
import modelo.Jornada;
import peticiones.Listajornada;
import print.PrintJornada;

/**
 * @author Gustavo Senoráns Varela
 * @version 1.4, 15/11/2023
 * @since jdk 17
 */
public class SearchCrudNomApellidoJornada {

    /**
     * Este método verifica los datos y hace el envio de los datos al cliente en
     * forma de objeto y si no le envia un error
     *
     * @param crud en este caso es el 0 de select
     * @param nombreTabla en este caso es el 3, ya que se refiere a jornada
     * @param nom nombre de la tabla
     * @param datoNom nombre del empleado
     * @param apellido nombre la tabla
     * @param datoApellido apelledo del empleado
     * @param palabraAbuscar el array con los datos
     * @param outObjeto el objeto a enviar al cliente
     * @param client el socket del cliente
     * @throws IOException controla los errores
     */
    public static void handleSearchRequest(String crud, String nombreTabla, String nom, String datoNom, String apellido,
            String datoApellido, String palabraAbuscar, ObjectOutputStream outObjeto, Socket client)
            throws IOException {

        PrintJornada selectorJornadaNomApellido = new PrintJornada();
        List<Jornada> listaJornadaNomApellido = Listajornada.listaJornadaNomApellido(datoNom, datoApellido);

        if (!listaJornadaNomApellido.isEmpty()) {
            String datosJornadaNomApellido = selectorJornadaNomApellido
                    .obtenerDatosJornadaNomApellido(listaJornadaNomApellido, nom, apellido);
            System.out.println(datosJornadaNomApellido);
            outObjeto = new ObjectOutputStream(client.getOutputStream());
            outObjeto.writeObject(listaJornadaNomApellido);
            outObjeto.flush();
        } else {

            Errores error = new Errores();
            String errorNomApellido = error.errorNomApellido();
            System.out.println(errorNomApellido);
            outObjeto = new ObjectOutputStream(client.getOutputStream());
            outObjeto.writeObject(errorNomApellido);
            outObjeto.flush();
        }
    }
}
