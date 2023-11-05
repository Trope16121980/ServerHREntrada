package update;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import modelo.*;
import peticiones.*;

public class UpdateCrudNomEmpresa {
	
	public static void handleSearchRequest(String crud, String nombreTabla, String nomNuevo, String datoNomnuevo, String nom,
			String datoNom, String palabraAbuscar, ObjectOutputStream outObjeto, Socket client) throws IOException {

		if (crud.equals("2")) {
			if (nombreTabla.equals("2")) {
				List<Empresa> updateNomempresa = new ArrayList<Empresa>();
				updateNomempresa = UpdateNomempresa.updateNomempresa(crud, nombreTabla, nomNuevo, datoNomnuevo, nom, datoNom, palabraAbuscar, outObjeto, client);
				if (!updateNomempresa.isEmpty()) {
					 for (Empresa empresa : updateNomempresa) {
					System.out.println("Nombre de empresa modificado correctamente:");
					System.out.println("____________________________________________________________________");
					System.out.println("Nombre: " + datoNomnuevo);
					System.out.println("Direccion: " + empresa.getAddress());
					System.out.println("Telefono: " + empresa.getTelephon());
					System.out.println("____________________________________________________________________");
					 }
					outObjeto = new ObjectOutputStream(client.getOutputStream());
					outObjeto.writeObject(updateNomempresa);
					outObjeto.flush();
				} else {
					System.out.println("____________________________________________________________________");
				}
			}
		}
	}
}
