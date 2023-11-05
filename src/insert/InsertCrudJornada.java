package insert;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import modelo.Jornada;
import peticiones.InsertJornada;

public class InsertCrudJornada {


	public static void handleInsertRequest(String crud, String nombreTabla, String dni, String datoDni, String palabraAbuscar, ObjectOutputStream outObjeto,
			Socket client) throws IOException {

		if (crud.equals("1")) {
			if (nombreTabla.equals("3")) {
				List<Jornada> insertJornada = new ArrayList<Jornada>();
				insertJornada = InsertJornada.insertJornada(crud, nombreTabla, dni, datoDni, palabraAbuscar, outObjeto, client);
				if (!insertJornada.isEmpty()) {
					 for (Jornada jornada : insertJornada) {
					System.out.println("Jornada creada correctamente:");
					System.out.println("____________________________________________________________________");
					System.out.println("Dni: " + jornada.getDni());
					System.out.println("Nombre: " + jornada.getNom());
					System.out.println("Apellido: " + jornada.getApellido());
					System.out.println("Codigo tarjeta: " + jornada.getCodicard());
					System.out.println("Hora entrada: " + jornada.getHoraentrada());
					System.out.println("Hora salida: " + jornada.getHorasalida());
					System.out.println("Total: " + jornada.getTotal());
					System.out.println("Fecha: " + jornada.getFecha());
					System.out.println("____________________________________________________________________");
					 }
					outObjeto = new ObjectOutputStream(client.getOutputStream());
					outObjeto.writeObject(insertJornada);
					outObjeto.flush();
				} else {
					System.out.println("____________________________________________________________________");
				}
			}
		}
	}
	
	public static void handleInsertCodicardRequest(String crud, String nombreTabla, String codicard, int datoCodicard, String palabraAbuscar, ObjectOutputStream outObjeto,
			Socket client) throws IOException {

		if (crud.equals("1")) {
			if (nombreTabla.equals("3")) {
				List<Jornada> insertJornadaCodicard = new ArrayList<Jornada>();
				insertJornadaCodicard = InsertJornada.insertJornadaCodicard(crud, nombreTabla, codicard, datoCodicard, palabraAbuscar, outObjeto, client);
				if (!insertJornadaCodicard.isEmpty()) {
					 for (Jornada jornada : insertJornadaCodicard) {
					System.out.println("Jornada creada correctamente:");
					System.out.println("____________________________________________________________________");
					System.out.println("Dni: " + jornada.getDni());
					System.out.println("Nombre: " + jornada.getNom());
					System.out.println("Apellido: " + jornada.getApellido());
					System.out.println("Codigo tarjeta: " + jornada.getCodicard());
					System.out.println("Hora entrada: " + jornada.getHoraentrada());
					System.out.println("Hora salida: " + jornada.getHorasalida());
					System.out.println("Total: " + jornada.getTotal());
					System.out.println("Fecha: " + jornada.getFecha());
					System.out.println("____________________________________________________________________");
					 }
					outObjeto = new ObjectOutputStream(client.getOutputStream());
					outObjeto.writeObject(insertJornadaCodicard);
					outObjeto.flush();
				} else {
					System.out.println("____________________________________________________________________");
				}
			}
		}
	}
}