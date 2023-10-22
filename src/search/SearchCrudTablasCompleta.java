
package search;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import modelo.Empleados;
import modelo.Empresa;
import modelo.Jornada;
import modelo.Users;
import peticiones.Listaempleados;
import peticiones.Listaempresas;
import peticiones.Listajornada;
import peticiones.Listausers;
/**
 *
 * @author Gustavo_Senorans
 */
public class SearchCrudTablasCompleta {

    public static void handleSearchRequest(String crud, String nombreTabla, String columna,
            String palabraAbuscar, ObjectOutputStream outObjeto, Socket client) throws IOException {

        if (crud.equals("0")) {
            if (!nombreTabla.equals(null) && columna.equals("0")) {
                switch (nombreTabla) {
                    case "0":

                        List<Empleados> listaEmpleados = new ArrayList<Empleados>();
                        listaEmpleados = Listaempleados.listaTotalEmpleados();

                        outObjeto = new ObjectOutputStream(client.getOutputStream());
                        outObjeto.writeObject(listaEmpleados);
                        outObjeto.flush();
                        break;
                    case "1":

                        List<Users> listaToUsers = new ArrayList<Users>();
                        listaToUsers = Listausers.listaTotalUsers();

                        outObjeto = new ObjectOutputStream(client.getOutputStream());
                        outObjeto.writeObject(listaToUsers);
                        outObjeto.flush();
                        break;
                    case "2":

                        List<Empresa> listaEmpresas = new ArrayList<Empresa>();
                        listaEmpresas = Listaempresas.listaTotalEmpresas();

                        outObjeto = new ObjectOutputStream(client.getOutputStream());
                        outObjeto.writeObject(listaEmpresas);
                        outObjeto.flush();
                        break;
                    case "3":

                        List<Jornada> listaJornada = new ArrayList<Jornada>();
                        listaJornada = Listajornada.listaTotalJornada();

                        outObjeto = new ObjectOutputStream(client.getOutputStream());
                        outObjeto.writeObject(listaJornada);
                        outObjeto.flush();
                        break;
                }
            }
        }
    }
}
