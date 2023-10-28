package search;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.List;

import errores.Errores;
import modelo.Jornada;
import peticiones.Listajornada;
import print.PrintJornada;

public class SearchCrudApellidoFechaJornada {

	/**
	 *
	 * @author Gustavo_Senorans
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