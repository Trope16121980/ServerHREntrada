package insert;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import errores.Errores;
import modelo.Empresa;
import peticiones.InsertEmpresa;

/**
 *
 * @author Gustavo_Senorans
 */
public class InsertCrudEmpresa {

	public static void handleInsertRequest(String crud, String nombreTabla, String nom, String datoNom, String address,
			String datoAddress, String telephon, String datoTelephon, String palabraAbuscar,
			ObjectOutputStream outObjeto, Socket client) throws IOException {

		if (crud.equals("1")) {
			if (nombreTabla.equals("2")) {
				List<Empresa> insertEmpresa = new ArrayList<Empresa>();
				insertEmpresa = InsertEmpresa.insertEmpresa(crud, nombreTabla, nom, datoNom, address, datoAddress,
						telephon, datoTelephon, palabraAbuscar, outObjeto, client);

				if (!insertEmpresa.isEmpty()) {
					System.out.println("Empresa creada correctamente, sus datos son:\n");
					System.out.println("____________________________________________________________________");
					System.out.println("Nombre: " + datoNom + "\n" + "Address: " + datoAddress + "\n" + "Telefono: "
							+ Integer.parseInt(datoTelephon) + "\n");
					System.out.println("____________________________________________________________________");
					outObjeto = new ObjectOutputStream(client.getOutputStream());
					outObjeto.writeObject(insertEmpresa);
					outObjeto.flush();
				}else {
					System.out.println("____________________________________________________________________");
				}
			}
		}
	}
}
