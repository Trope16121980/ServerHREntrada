package search;

import errores.Errores;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.List;
import modelo.Empleados;
import peticiones.Listaempleados;
import print.PrintEmpleados;

/**
 * @author Gustavo Senoráns Varela
 * @version 1.4, 15/11/2023
 * @since jdk 17
 */
public class SearchCrudNonApellidoEmpleados {

    /**
     * Este método verifica los datos y hace el envio de los datos al cliente en
     * forma de objeto y si no le envia un error
     *
     * @param crud en este caso es el 0 de select
     * @param nombreTabla en este caso es el 0, ya que se refiere a empleados
     * @param nom nombre de la columna
     * @param datoNom nombre del empleado
     * @param apellido nombre de la columna
     * @param datoApellido apellido del empleado
     * @param palabraAbuscar el array con los datos
     * @param outObjeto el objeto a enviar al cliente
     * @param client el socket del cliente
     * @throws IOException controla los errores
     */
    public static void handleSearchRequest(String crud, String nombreTabla,
            String nom, String datoNom, String apellido, String datoApellido,
            String palabraAbuscar, ObjectOutputStream outObjeto, Socket client) throws IOException {

        PrintEmpleados selectorNomApellido = new PrintEmpleados();
        List<Empleados> listaEmpleadosNomApellido = Listaempleados.listaEmpleadosNomApellido(datoNom, datoApellido);

        if (!listaEmpleadosNomApellido.isEmpty()) {

            String datosEmpleadosNomApellido = selectorNomApellido.obtenerDatosEmpleadosNomApellido(listaEmpleadosNomApellido, nom, apellido);
            System.out.println(datosEmpleadosNomApellido);
            outObjeto = new ObjectOutputStream(client.getOutputStream());
            outObjeto.writeObject(listaEmpleadosNomApellido);
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
