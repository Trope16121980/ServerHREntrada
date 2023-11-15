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
public class SearchCrudApellidoFechaJornada {

    /**
     * Este método verifica los datos y hace el envio de los datos al cliente en
     * forma de objeto y si no le envia un error
     *
     * @param crud en este caso es el 0 de select
     * @param nombreTabla en este caso es el 3, ya que se refiere a jornada
     * @param apellido nombre de la tabla
     * @param datoApellido apellido del empleado
     * @param fecha nombre de la tabla
     * @param datoFecha fecha de la jornada laboral
     * @param palabraAbuscar el array con los datos
     * @param outObjeto el objeto a enviar al cliente
     * @param client el socket del cliente
     * @throws IOException controla los errores
     */
    public static void handleSearchRequest(String crud, String nombreTabla, String apellido, String datoApellido, String fecha,
            String datoFecha, String palabraAbuscar, ObjectOutputStream outObjeto, Socket client) throws IOException {

        PrintJornada selectorJornadaDniFecha = new PrintJornada();
        List<Jornada> listaJornadaApellidoFecha = Listajornada.listaJornadaApellidoFecha(datoApellido, datoFecha);

        if (!listaJornadaApellidoFecha.isEmpty()) {
            String datosJornadaDniFecha = selectorJornadaDniFecha.obtenerDatosJornadaNomApellido(listaJornadaApellidoFecha,
                    apellido, fecha);
            System.out.println(datosJornadaDniFecha);
            outObjeto = new ObjectOutputStream(client.getOutputStream());
            outObjeto.writeObject(listaJornadaApellidoFecha);
            outObjeto.flush();
        } else {

            Errores error = new Errores();
            String erroApellidoFechaJornada = error.erroApellidoFechaJornada();
            System.out.println(erroApellidoFechaJornada);
            outObjeto = new ObjectOutputStream(client.getOutputStream());
            outObjeto.writeObject(erroApellidoFechaJornada);
            outObjeto.flush();
        }
    }
}
