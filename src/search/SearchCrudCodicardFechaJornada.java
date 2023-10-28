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
public class SearchCrudCodicardFechaJornada {

	public static void handleSearchRequest(String crud, String nombreTabla, String codicard, int datoCodicard,
			String fecha, String datoFecha, String palabraAbuscar, ObjectOutputStream outObjeto, Socket client)
			throws IOException {

		PrintJornada selectorJornadaCodicardFecha = new PrintJornada();
		List<Jornada> listaJornadaCodicardFecha = Listajornada.listaJornadaCodiCardFecha(datoCodicard, datoFecha);

		if (!listaJornadaCodicardFecha.isEmpty()) {

			String datosJornadaCodicardFecha = selectorJornadaCodicardFecha.obtenerDatosJornadaCodicardFecha(listaJornadaCodicardFecha,palabraAbuscar, fecha);
			System.out.println(datosJornadaCodicardFecha);
			outObjeto = new ObjectOutputStream(client.getOutputStream());
			outObjeto.writeObject(listaJornadaCodicardFecha);
			outObjeto.flush();
		} else {
			Errores error = new Errores();
			String erroCodicardFechaJornada = error.erroCodicardFechaJornada();
			System.out.println(erroCodicardFechaJornada);
			outObjeto = new ObjectOutputStream(client.getOutputStream());
			outObjeto.writeObject(erroCodicardFechaJornada);
			outObjeto.flush();
		}
	}
}
