
package Search;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import modelo.Empleados;
import peticiones.Listaempleados;
import print.PrintEmpleados;

/**
 *
 * @author Gustavo_Senorans
 */
public class SearchCrudNonApellidoEmpleados {

    public static void handleSearchRequest(String crud, String nombreTabla,
            String nom, String datoNom, String apellido, String datoApellido,
            String palabraAbuscar, ObjectOutputStream outObjeto, Socket client) throws IOException {

        if (crud.equals("0")) {
            if (nombreTabla.equals("0") && nom.equals("nom") && apellido.equals("apellido")) {
                PrintEmpleados selectorNomApellido = new PrintEmpleados();

                List<Empleados> listaEmpleadosNomApellido = new ArrayList<Empleados>();
                listaEmpleadosNomApellido = Listaempleados.listaEmpleadosNomApellido(datoNom, datoApellido);
                String datosEmpleadosNomApellido = selectorNomApellido.obtenerDatosEmpleadosNomApellido(listaEmpleadosNomApellido, nom, apellido);
                System.out.println(datosEmpleadosNomApellido);

                outObjeto = new ObjectOutputStream(client.getOutputStream());
                outObjeto.writeObject(listaEmpleadosNomApellido);
                outObjeto.flush();

            }
        }
    }
}
