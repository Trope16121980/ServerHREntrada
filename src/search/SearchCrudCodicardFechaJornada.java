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
public class SearchCrudCodicardFechaJornada {

    /**
     * Este método verifica los datos y hace el envio de los datos al cliente en
     * forma de objeto y si no le envia un error
     *
     * @param crud en este caso es el 0 de select
     * @param nombreTabla en este caso es el 3, ya que se refiere a jornada
     * @param codicard nombre de la tabla
     * @param datoCodicard codigo de tarjeta del empleado
     * @param fecha nombre de la tabla
     * @param datoFecha fecha de la jornada laboral
     * @param palabraAbuscar el array con los datos
     * @param outObjeto el objeto a enviar al cliente
     * @param client el socket del cliente
     * @throws IOException controla los errores
     */
    public static void handleSearchRequest(String crud, String nombreTabla, String codicard, String datoCodicard,
            String fecha, String datoFecha, String palabraAbuscar, ObjectOutputStream outObjeto, Socket client)
            throws IOException {

        PrintJornada selectorJornadaCodicardFecha = new PrintJornada();
        List<Jornada> listaJornadaCodicardFecha = Listajornada.listaJornadaCodiCardFecha(datoCodicard, datoFecha);

        if (!listaJornadaCodicardFecha.isEmpty()) {

            String datosJornadaCodicardFecha = selectorJornadaCodicardFecha.obtenerDatosJornadaCodicardFecha(listaJornadaCodicardFecha, palabraAbuscar, fecha);
            System.out.println(datosJornadaCodicardFecha);
            outObjeto = new ObjectOutputStream(client.getOutputStream());
            outObjeto.writeObject(listaJornadaCodicardFecha);
            outObjeto.flush();
        } else {
            Errores error = new Errores();
            String erroCodicardFechaJornada = error.erroCodicardFechaJornada();
            System.out.println(erroCodicardFechaJornada);
            outObjeto = new ObjectOutputStream(client.getOutputStream());
            outObjeto.writeObject(erroCodicardFechaJornada);
            outObjeto.flush();
        }
    }
}
