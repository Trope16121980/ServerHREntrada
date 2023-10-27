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
 *
 * @author Gustavo_Senorans
 */
public class SearchCrudNonApellidoEmpleados {

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
            String erroNomApellidoEmpleados = error.erroNomApellidoEmpleados();
            System.out.println(erroNomApellidoEmpleados);
            outObjeto = new ObjectOutputStream(client.getOutputStream());
            outObjeto.writeObject(erroNomApellidoEmpleados);
            outObjeto.flush();
        }

    }
}
