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
public class SearchCrudNomApellidoFechaJornada {

    /**
     * Este método verifica los datos y hace el envio de los datos al cliente en
     * forma de objeto y si no le envia un error
     *
     * @param crud en este caso es el 0 de select
     * @param nombreTabla en este caso es el 0, ya que se refiere a empleados
     * @param nom nombre de la tabla
     * @param datoNom nombre del empleado
     * @param apellido nombre de la tabla
     * @param datoApellido apellido del empleado
     * @param fecha nombre de la column
     * @param datoFecha fecha de la jornada laboral
     * @param palabraAbuscar el array con los datos
     * @param outObjeto el objeto a enviar al cliente
     * @param client el socket del cliente
     * @throws IOException controla los errores
     */
    public static void handleSearchRequest(String crud, String nombreTabla, String nom, String datoNom, String apellido, String datoApellido, String fecha,
            String datoFecha, String palabraAbuscar, ObjectOutputStream outObjeto, Socket client) throws IOException {

        PrintJornada selectorJornadaNomApellidoFecha = new PrintJornada();
        List<Jornada> listaJornadaNomApellidoFecha = Listajornada.listaJornadaNomApellidoFecha(datoNom, datoApellido, datoFecha);

        if (!listaJornadaNomApellidoFecha.isEmpty()) {
            String datosJornadaNomApellidoFecha = selectorJornadaNomApellidoFecha.obtenerDatosJornadaNomApellidoFecha(listaJornadaNomApellidoFecha, nom, apellido, fecha);
            System.out.println(datosJornadaNomApellidoFecha);
            outObjeto = new ObjectOutputStream(client.getOutputStream());
            outObjeto.writeObject(listaJornadaNomApellidoFecha);
            outObjeto.flush();
        } else {

            Errores error = new Errores();
            String erroNomApellidoFechaJornada = error.erroNomApellidoFechaJornada();
            System.out.println(erroNomApellidoFechaJornada);
            outObjeto = new ObjectOutputStream(client.getOutputStream());
            outObjeto.writeObject(erroNomApellidoFechaJornada);
            outObjeto.flush();
        }
    }
}
