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
 * @author Gustavo Senor�ns Varela
 * @version 1.4, 15/11/2023
 * @since jdk 17
 */
public class SearchCrudDniFechaJornada {

    /**
     * Este m�todo verifica los datos y hace el envio de los datos al cliente en
     * forma de objeto y si no le envia un error
     *
     * @param crud en este caso es el 0 de select
     * @param nombreTabla en este caso es el 3, ya que se refiere a jornada
     * @param dni nombre tabla
     * @param datoDni dni del empleado
     * @param fecha nombre de tabla
     * @param datoFecha fecha de la jornada
     * @param palabraAbuscar el array con los datos
     * @param outObjeto el objeto a enviar al cliente
     * @param client el socket del cliente
     * @throws IOException controla los errores
     */
    public static void handleSearchRequest(String crud, String nombreTabla, String dni, String datoDni, String fecha,
            String datoFecha, String palabraAbuscar, ObjectOutputStream outObjeto, Socket client) throws IOException {

        PrintJornada selectorJornadaDniFecha = new PrintJornada();
        List<Jornada> listaJornadaDniFecha = Listajornada.listaJornadaDniFecha(datoDni, datoFecha);

        if (!listaJornadaDniFecha.isEmpty()) {
            String datosJornadaDniFecha = selectorJornadaDniFecha.obtenerDatosJornadaDniFecha(listaJornadaDniFecha,
                    dni, fecha);
            System.out.println(datosJornadaDniFecha);
            outObjeto = new ObjectOutputStream(client.getOutputStream());
            outObjeto.writeObject(listaJornadaDniFecha);
            outObjeto.flush();
        } else {

            Errores error = new Errores();
            String erroDniFechaJornada = error.erroDniFechaJornada();
            System.out.println(erroDniFechaJornada);
            outObjeto = new ObjectOutputStream(client.getOutputStream());
            outObjeto.writeObject(erroDniFechaJornada);
            outObjeto.flush();
        }
    }
}
