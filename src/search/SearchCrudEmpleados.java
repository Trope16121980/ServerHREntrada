package search;

import modelo.Empleados;
import print.PrintEmpleados;
import java.util.ArrayList;
import java.util.List;
import java.io.ObjectOutputStream;
import java.io.IOException;
import java.net.Socket;
import peticiones.Listaempleados;
import errores.Errores;

/**
 *
 * @author Gustavo_Senorans
 */
public class SearchCrudEmpleados {

	public static void handleSearchRequest(String crud, String nombreTabla, String columna, String palabraAbuscar,
			ObjectOutputStream outObjeto, Socket client) throws IOException {

		if (crud.equals("0")) {
			if (nombreTabla.equals("0") && columna.equals("dni")) {
				PrintEmpleados selector = new PrintEmpleados();
				List<Empleados> listaEmpleadosDni = Listaempleados.listaTotalEmpleadosDni(palabraAbuscar);
				if (!listaEmpleadosDni.isEmpty()) {

					String datosEmpleados = selector.obtenerDatosEmpleados(listaEmpleadosDni, columna);
					System.out.println(datosEmpleados);
					outObjeto = new ObjectOutputStream(client.getOutputStream());
					outObjeto.writeObject(listaEmpleadosDni);
					outObjeto.flush();

				} else {
					Errores error = new Errores();
					String errorDniEmpleados = error.errorDniEmpleados();
					System.out.println(errorDniEmpleados);
					outObjeto = new ObjectOutputStream(client.getOutputStream());
					outObjeto.writeObject(errorDniEmpleados);
					outObjeto.flush();
				}

			} else if (nombreTabla.equals("0") && columna.equals("nom")) {
				PrintEmpleados selector = new PrintEmpleados();
				List<Empleados> listaTotalEmpleadosNom = Listaempleados.listaTotalEmpleadosNom(palabraAbuscar);
				if (!listaTotalEmpleadosNom.isEmpty()) {

					String datosEmpleados = selector.obtenerDatosEmpleados(listaTotalEmpleadosNom, columna);
					System.out.println(datosEmpleados);
					outObjeto = new ObjectOutputStream(client.getOutputStream());
					outObjeto.writeObject(listaTotalEmpleadosNom);
					outObjeto.flush();

				} else {
					Errores error = new Errores();
					String erroNomEmpleados = error.erroNomEmpleados();
					System.out.println(erroNomEmpleados);
					outObjeto = new ObjectOutputStream(client.getOutputStream());
					outObjeto.writeObject(erroNomEmpleados);
					outObjeto.flush();
				}

			} else if (nombreTabla.equals("0") && columna.equals("apellido")) {
				PrintEmpleados selector = new PrintEmpleados();
				List<Empleados> listaTotalEmpleadosApellido = Listaempleados
						.listaTotalEmpleadosApellido(palabraAbuscar);
				if (!listaTotalEmpleadosApellido.isEmpty()) {

					String datosEmpleados = selector.obtenerDatosEmpleados(listaTotalEmpleadosApellido, columna);
					System.out.println(datosEmpleados);
					outObjeto = new ObjectOutputStream(client.getOutputStream());
					outObjeto.writeObject(listaTotalEmpleadosApellido);
					outObjeto.flush();

				} else {
					Errores error = new Errores();
					String erroApellidoEmpleados = error.erroApellidoEmpleados();
					System.out.println(erroApellidoEmpleados);
					outObjeto = new ObjectOutputStream(client.getOutputStream());
					outObjeto.writeObject(erroApellidoEmpleados);
					outObjeto.flush();
				}

			} else if (nombreTabla.equals("0") && columna.equals("nomempresa")) {

				PrintEmpleados selector = new PrintEmpleados();
				List<Empleados> listaTotalEmpleadosNomEmpresa = Listaempleados
						.listaTotalEmpleadosNomEmpresa(palabraAbuscar);
				if (!listaTotalEmpleadosNomEmpresa.isEmpty()) {

					String datosEmpleados = selector.obtenerDatosEmpleados(listaTotalEmpleadosNomEmpresa, columna);
					System.out.println(datosEmpleados);
					outObjeto = new ObjectOutputStream(client.getOutputStream());
					outObjeto.writeObject(listaTotalEmpleadosNomEmpresa);
					outObjeto.flush();

				} else {
					Errores error = new Errores();
					String errorNomEmpresaEmpleados = error.errorNomEmpresaEmpleados();
					System.out.println(errorNomEmpresaEmpleados);
					outObjeto = new ObjectOutputStream(client.getOutputStream());
					outObjeto.writeObject(errorNomEmpresaEmpleados);
					outObjeto.flush();
				}
			} else if (nombreTabla.equals("0") && columna.equals("departament")) {

				PrintEmpleados selector = new PrintEmpleados();
				List<Empleados> listaTotalEmpleadosDepart = Listaempleados.listaTotalEmpleadosDepart(palabraAbuscar);

				if (!listaTotalEmpleadosDepart.isEmpty()) {
					String datosEmpleados = selector.obtenerDatosEmpleados(listaTotalEmpleadosDepart, columna);
					System.out.println(datosEmpleados);
					outObjeto = new ObjectOutputStream(client.getOutputStream());
					outObjeto.writeObject(listaTotalEmpleadosDepart);
					outObjeto.flush();
				} else {
					Errores error = new Errores();
					String errorDepartamentEmpleados = error.errorDepartamentEmpleados();
					System.out.println(errorDepartamentEmpleados);
					outObjeto = new ObjectOutputStream(client.getOutputStream());
					outObjeto.writeObject(errorDepartamentEmpleados);
					outObjeto.flush();
				}

			} else if (nombreTabla.equals("0") && columna.equals("codicard")) {

				PrintEmpleados selector = new PrintEmpleados();
				List<Empleados> listaTotalEmpleadosCodiCard = Listaempleados
						.listaTotalEmpleadosCodiCard(Integer.parseInt(palabraAbuscar));

				if (!listaTotalEmpleadosCodiCard.isEmpty()) {
					String datosEmpleados = selector.obtenerDatosEmpleados(listaTotalEmpleadosCodiCard, columna);
					System.out.println(datosEmpleados);
					outObjeto = new ObjectOutputStream(client.getOutputStream());
					outObjeto.writeObject(listaTotalEmpleadosCodiCard);
					outObjeto.flush();
				} else {
					Errores error = new Errores();
					String erroCodicardEmpleados = error.erroCodicardEmpleados();
					System.out.println(erroCodicardEmpleados);
					outObjeto = new ObjectOutputStream(client.getOutputStream());
					outObjeto.writeObject(erroCodicardEmpleados);
					outObjeto.flush();
				}
			} else if (nombreTabla.equals("0") && columna.equals("mail")) {

				PrintEmpleados selector = new PrintEmpleados();
				List<Empleados> listaTotalEmpleadosMail = Listaempleados.listaTotalEmpleadosMail(palabraAbuscar);
				if (!listaTotalEmpleadosMail.isEmpty()) {
					String datosEmpleados = selector.obtenerDatosEmpleados(listaTotalEmpleadosMail, columna);
					System.out.println(datosEmpleados);
					outObjeto = new ObjectOutputStream(client.getOutputStream());
					outObjeto.writeObject(listaTotalEmpleadosMail);
					outObjeto.flush();
				} else {
					Errores error = new Errores();
					String erroMailEmpleados = error.erroMailEmpleados();
					System.out.println(erroMailEmpleados);
					outObjeto = new ObjectOutputStream(client.getOutputStream());
					outObjeto.writeObject(erroMailEmpleados);
					outObjeto.flush();
				}

			} else {

				if (nombreTabla.equals("0") && columna.equals("telephon")) {
					PrintEmpleados selector = new PrintEmpleados();
					List<Empleados> listaTotalEmpleadosTelf = Listaempleados
							.listaTotalEmpleadosTelf(Integer.parseInt(palabraAbuscar));

					if (!listaTotalEmpleadosTelf.isEmpty()) {
						String datosEmpleados = selector.obtenerDatosEmpleados(listaTotalEmpleadosTelf, columna);
						System.out.println(datosEmpleados);
						outObjeto = new ObjectOutputStream(client.getOutputStream());
						outObjeto.writeObject(listaTotalEmpleadosTelf);
						outObjeto.flush();
					} else {
						Errores error = new Errores();
						String erroTelephonEmpleados = error.erroTelephonEmpleados();
						System.out.println(erroTelephonEmpleados);
						outObjeto = new ObjectOutputStream(client.getOutputStream());
						outObjeto.writeObject(erroTelephonEmpleados);
						outObjeto.flush();
					}
				}
			}
		}
	}
}
