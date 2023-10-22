package Search;


import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import modelo.Jornada;
import peticiones.Listajornada;
import print.PrintJornada;

/**
 *
 * @author Gustavo_Senorans
 */
public class SearchCrudNomApellidoJornada {

    public static void handleSearchRequest(String crud, String nombreTabla,
            String nom, String datoNom, String apellido, String datoApellido,
            String palabraAbuscar, ObjectOutputStream outObjeto, Socket client) throws IOException {

        if (crud.equals("0")) {
            if (nombreTabla.equals("3") && nom.equals("nom") && apellido.equals("apellido")) {
                PrintJornada selectorJornadaNomApellido = new PrintJornada();

                List<Jornada> listaJornadaNomApellido = new ArrayList<Jornada>();
                listaJornadaNomApellido = Listajornada.listaJornadaNomApellido(datoNom, datoApellido);

                String datosJornadaNomApellido = selectorJornadaNomApellido.obtenerDatosJornadaNomApellido(listaJornadaNomApellido, nom, apellido);
                System.out.println(datosJornadaNomApellido);

                outObjeto = new ObjectOutputStream(client.getOutputStream());
                outObjeto.writeObject(listaJornadaNomApellido);
                outObjeto.flush();
            }
        }
    }

}
