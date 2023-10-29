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
 *
 * @author Gustavo_Senorans
 */
public class SearchCrudNomApellidoJornada {

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
