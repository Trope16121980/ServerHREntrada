package search;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.List;

import errores.Errores;
import modelo.Jornada;
import peticiones.Listajornada;
import print.PrintJornada;

public class SearchCrudDniFechaJornada {

	/**
	 *
	 * @author Gustavo_Senorans
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