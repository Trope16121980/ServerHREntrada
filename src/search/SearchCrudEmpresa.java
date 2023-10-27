package search;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import errores.Errores;
import modelo.Empresa;
import peticiones.Listaempresas;
import print.PrintEmpresa;

/**
 *
 * @author Gustavo_Senorans
 */
public class SearchCrudEmpresa {

	public static void handleSearchRequest(String crud, String nombreTabla, String columna, String palabraAbuscar,
			ObjectOutputStream outObjeto, Socket client) throws IOException {

		if (crud.equals("0")) {

			if (nombreTabla.equals("2") && columna.equals("nom")) {

				PrintEmpresa empresa = new PrintEmpresa();
				List<Empresa> listaEmpresasNom = Listaempresas.listaEmpresasNom(palabraAbuscar);

				if (!listaEmpresasNom.isEmpty()) {
					String datosEmpresa = empresa.obtenerDatosEmpresa(listaEmpresasNom, columna);
					System.out.println(datosEmpresa);
					outObjeto = new ObjectOutputStream(client.getOutputStream());
					outObjeto.writeObject(listaEmpresasNom);
					outObjeto.flush();
				} else {
					Errores error = new Errores();
					String erroNomEmpresa = error.erroNomEmpresa();
					System.out.println(erroNomEmpresa);
					outObjeto = new ObjectOutputStream(client.getOutputStream());
					outObjeto.writeObject(erroNomEmpresa);
					outObjeto.flush();
				}
			} else if (nombreTabla.equals("2") && columna.equals("address")) {
				PrintEmpresa empresa = new PrintEmpresa();
				List<Empresa> listaEmpresasAddress = Listaempresas.listaEmpresasAddress(palabraAbuscar);

				if (!listaEmpresasAddress.isEmpty()) {
					String datosEmpresa = empresa.obtenerDatosEmpresa(listaEmpresasAddress, columna);
					System.out.println(datosEmpresa);
					outObjeto = new ObjectOutputStream(client.getOutputStream());
					outObjeto.writeObject(listaEmpresasAddress);
					outObjeto.flush();
				} else {
					Errores error = new Errores();
					String erroAddressEmpresa = error.erroAddressEmpresa();
					System.out.println(erroAddressEmpresa);
					outObjeto = new ObjectOutputStream(client.getOutputStream());
					outObjeto.writeObject(erroAddressEmpresa);
					outObjeto.flush();
				}
			} else if (nombreTabla.equals("2") && columna.equals("telephon")) {
				PrintEmpresa empresa = new PrintEmpresa();
				List<Empresa> listaEmpresasTelepho = Listaempresas.listaEmpresasTelepho(Integer.parseInt(palabraAbuscar));

				if (!listaEmpresasTelepho.isEmpty()) {
					String datosEmpresa = empresa.obtenerDatosEmpresa(listaEmpresasTelepho, columna);
					System.out.println(datosEmpresa);
					outObjeto = new ObjectOutputStream(client.getOutputStream());
					outObjeto.writeObject(listaEmpresasTelepho);
					outObjeto.flush();
				} else {
					Errores error = new Errores();
					String erroTelephonEmpresa = error.erroTelephonEmpresa();
					System.out.println(erroTelephonEmpresa);
					outObjeto = new ObjectOutputStream(client.getOutputStream());
					outObjeto.writeObject(erroTelephonEmpresa);
					outObjeto.flush();
				}
			}
		}
	}
}
