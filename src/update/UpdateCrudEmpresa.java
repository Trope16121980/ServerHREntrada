package update;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import modelo.*;
import peticiones.*;

public class UpdateCrudEmpresa {
	
	public static void handleSearchRequest(String crud, String nombreTabla, String nomNuevo,
                String datoNomnuevo, String addressNuevo, String datoAddressNuevo, String telephonNuevo,
                int datoTelephonNuevo,String nom, String datoNom, String palabraAbuscar, 
                String palabra, ObjectOutputStream outObjeto, Socket client) throws IOException {

		if (crud.equals("2")) {
			if (nombreTabla.equals("2")) {
				List<Empresa> updateEmpresa = new ArrayList<Empresa>();
				updateEmpresa = UpdateEmpresa.updateEmpresa(crud, nombreTabla, nomNuevo, datoNomnuevo, addressNuevo, datoAddressNuevo, 
                                        telephonNuevo, datoTelephonNuevo, nom, datoNom, palabraAbuscar, palabra, outObjeto, client);
				if (!updateEmpresa.isEmpty()) {
				 for (Empresa empresa : updateEmpresa) {
					System.out.println("Nombre de empresa modificado correctamente:");
					System.out.println("____________________________________________________________________");
					System.out.println("Nombre: " + datoNomnuevo);
					System.out.println("Direccion: " + datoAddressNuevo);
					System.out.println("Telefono: " + datoTelephonNuevo);
					System.out.println("____________________________________________________________________");
					 }
					outObjeto = new ObjectOutputStream(client.getOutputStream());
					outObjeto.writeObject(updateEmpresa);
					outObjeto.flush();
				} else {
					System.out.println("____________________________________________________________________");
				}
			}
		}
	}
}
