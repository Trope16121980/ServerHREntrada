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
public class SearchCrudJornada {

	public static void handleSearchRequest(String crud, String nombreTabla, String columna, String palabraAbuscar,
			ObjectOutputStream outObjeto, Socket client) throws IOException {

		if (crud.equals("0")) {
			if (nombreTabla.equals("3") && columna.equals("dni")) {
				PrintJornada jornada = new PrintJornada();
				List<Jornada> listaToJornadaDni = Listajornada.listaTotalJornadaDni(palabraAbuscar);

				if (!listaToJornadaDni.isEmpty()) {
					String datosJornadaDni = jornada.obtenerDatosJornada(listaToJornadaDni, columna);
					System.out.println(datosJornadaDni);
					outObjeto = new ObjectOutputStream(client.getOutputStream());
					outObjeto.writeObject(listaToJornadaDni);
					outObjeto.flush();
				} else {
					Errores error = new Errores();
					String erroDniJornada = error.erroDniJornada();
					System.out.println(erroDniJornada);
					outObjeto = new ObjectOutputStream(client.getOutputStream());
					outObjeto.writeObject(erroDniJornada);
					outObjeto.flush();
				}
				
			}else if (nombreTabla.equals("3") && columna.equals("nom")) {
				PrintJornada jornada = new PrintJornada();
				List<Jornada> listaTotalJornadaNom = Listajornada.listaTotalJornadaNom(palabraAbuscar);

				if (!listaTotalJornadaNom.isEmpty()) {
					String datosJornadaDni = jornada.obtenerDatosJornada(listaTotalJornadaNom, columna);
					System.out.println(datosJornadaDni);
					outObjeto = new ObjectOutputStream(client.getOutputStream());
					outObjeto.writeObject(listaTotalJornadaNom);
					outObjeto.flush();
				} else {
					Errores error = new Errores();
					String erroNomJornada = error.erroNomJornada();
					System.out.println(erroNomJornada);
					outObjeto = new ObjectOutputStream(client.getOutputStream());
					outObjeto.writeObject(erroNomJornada);
					outObjeto.flush();
				}
				
			}else if (nombreTabla.equals("3") && columna.equals("apellido")) {
				PrintJornada jornada = new PrintJornada();
				List<Jornada> listaTotalJornadaAapellido = Listajornada.listaTotalJornadaApellido(palabraAbuscar);

				if (!listaTotalJornadaAapellido.isEmpty()) {
					String datosJornadaDni = jornada.obtenerDatosJornada(listaTotalJornadaAapellido, columna);
					System.out.println(datosJornadaDni);
					outObjeto = new ObjectOutputStream(client.getOutputStream());
					outObjeto.writeObject(listaTotalJornadaAapellido);
					outObjeto.flush();
				} else {
					Errores error = new Errores();
					String erroApellidoJornada = error.erroApellidoJornada();
					System.out.println(erroApellidoJornada);
					outObjeto = new ObjectOutputStream(client.getOutputStream());
					outObjeto.writeObject(erroApellidoJornada);
					outObjeto.flush();
				}
				
			} else if (nombreTabla.equals("3") && columna.equals("codicard")) {
				PrintJornada jornada = new PrintJornada();
				List<Jornada> listaJornadaCodiCard = Listajornada
						.listaJornadaCodiCard(Integer.parseInt(palabraAbuscar));

				if (!listaJornadaCodiCard.isEmpty()) {
					String datosJornadaCodicard = jornada.obtenerDatosJornada(listaJornadaCodiCard, columna);
					System.out.println(datosJornadaCodicard);
					outObjeto = new ObjectOutputStream(client.getOutputStream());
					outObjeto.writeObject(listaJornadaCodiCard);
					outObjeto.flush();
				} else {
					Errores error = new Errores();
					String erroCodicarJornada = error.erroCodicarJornada();
					System.out.println(erroCodicarJornada);
					outObjeto = new ObjectOutputStream(client.getOutputStream());
					outObjeto.writeObject(erroCodicarJornada);
					outObjeto.flush();
				}

			} else {
				if (nombreTabla.equals("3") && columna.equals("fecha")) {
					PrintJornada jornada = new PrintJornada();
					List<Jornada> listaTotalJornadaFecha = Listajornada.listaTotalJornadaFecha(palabraAbuscar);

					if (!listaTotalJornadaFecha.isEmpty()) {
						String datosEmpresa = jornada.obtenerDatosJornada(listaTotalJornadaFecha, columna);
						System.out.println(datosEmpresa);
						outObjeto = new ObjectOutputStream(client.getOutputStream());
						outObjeto.writeObject(listaTotalJornadaFecha);
						outObjeto.flush();
					} else {
						Errores error = new Errores();
						String erroFechaJornada = error.erroFechaJornada();
						System.out.println(erroFechaJornada);
						outObjeto = new ObjectOutputStream(client.getOutputStream());
						outObjeto.writeObject(erroFechaJornada);
						outObjeto.flush();
					}

				}
			}
		}
	}
}
