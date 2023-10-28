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

public class SearchCrudNomApellidoFechaJornada {

	public static void handleSearchRequest(String crud, String nombreTabla, String nom, String datoNom, String apellido, String datoApellido, String fecha,
			String datoFecha, String palabraAbuscar, ObjectOutputStream outObjeto, Socket client) throws IOException {

		PrintJornada selectorJornadaNomApellidoFecha = new PrintJornada();
		List<Jornada> listaJornadaNomApellidoFecha = Listajornada.listaJornadaNomApellidoFecha(datoNom, datoApellido, datoFecha);

		if (!listaJornadaNomApellidoFecha.isEmpty()) {
			String datosJornadaNomApellidoFecha = selectorJornadaNomApellidoFecha.obtenerDatosJornadaNomApellidoFecha(listaJornadaNomApellidoFecha,nom,apellido, fecha);
			System.out.println(datosJornadaNomApellidoFecha);
			outObjeto = new ObjectOutputStream(client.getOutputStream());
			outObjeto.writeObject(listaJornadaNomApellidoFecha);
			outObjeto.flush();
		} else {

			Errores error = new Errores();
			String erroNomApellidoFechaJornada = error.erroNomApellidoFechaJornada();
			System.out.println(erroNomApellidoFechaJornada);
			outObjeto = new ObjectOutputStream(client.getOutputStream());
			outObjeto.writeObject(erroNomApellidoFechaJornada);
			outObjeto.flush();
		}
	}
}
