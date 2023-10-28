package search;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.List;

import errores.Errores;
import modelo.Jornada;
import peticiones.Listajornada;
import print.PrintJornada;

public class SearchCrudNomFechaJornada {

	/**
	 *
	 * @author Gustavo_Senorans
	 */

	public static void handleSearchRequest(String crud, String nombreTabla, String nom, String datoNom, String fecha,
			String datoFecha, String palabraAbuscar, ObjectOutputStream outObjeto, Socket client) throws IOException {

		PrintJornada selectorJornadaNomFecha = new PrintJornada();
		List<Jornada> listaJornadaNomFecha = Listajornada.listaJornadaNomFecha(datoNom, datoFecha);

		if (!listaJornadaNomFecha.isEmpty()) {
			String datosJornadaNomFecha = selectorJornadaNomFecha.obtenerDatosJornadaNomApellido(listaJornadaNomFecha,
					nom, fecha);
			System.out.println(datosJornadaNomFecha);
			outObjeto = new ObjectOutputStream(client.getOutputStream());
			outObjeto.writeObject(listaJornadaNomFecha);
			outObjeto.flush();
		} else {

			Errores error = new Errores();
			String erroNomFechaJornada = error.erroNomFechaJornada();
			System.out.println(erroNomFechaJornada);
			outObjeto = new ObjectOutputStream(client.getOutputStream());
			outObjeto.writeObject(erroNomFechaJornada);
			outObjeto.flush();
		}
	}
}