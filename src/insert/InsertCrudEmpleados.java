package insert;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import modelo.Empleados;
import peticiones.InsertEmpleados;

/**
 *
 * @author Gustavo_Senorans
 */
public class InsertCrudEmpleados {

    public static void handleInsertRequest(String crud, String nombreTabla,
            String dni, String datoDni, String nom, String datoNom, 
            String apellido, String datoApellido, String nomempresa, 
            String datoNomempresa, String departament, String datoDepartament, 
            String codicard, String datoCodicard, String mail, String datoMail,
            String telephon, String datoTelephon, String palabraAbuscar, 
            ObjectOutputStream outObjeto, Socket client) throws IOException {

        if (crud.equals("1")) {
            if (nombreTabla.equals("0")) {
                List<Empleados> insertEmpleados = new ArrayList<Empleados>();
                insertEmpleados = InsertEmpleados.insertEmpleados(datoDni, datoNom,
                        datoApellido, datoNomempresa, datoDepartament,
                        Integer.parseInt(datoCodicard), datoMail, Integer.parseInt(datoTelephon));
                System.out.println("Empleado creado correctamente, sus datos son:\n");
                System.out.println("____________________________________________________________________");
                System.out.println("Dni: " + datoDni + "\n"
                        + "Nombre: " + datoNom + "\n"
                        + "Apellido: " + datoApellido + "\n"
                        + "Nombre empresa: " + datoNomempresa + "\n"
                        + "Departamento: " + datoDepartament + "\n"
                        + "Codigo tarjeta: " + Integer.parseInt(datoCodicard) + "\n"
                        + "Mail: " + datoMail + "\n"
                        + "Telefono: " + Integer.parseInt(datoTelephon) + "\n");
                System.out.println("____________________________________________________________________");
                outObjeto = new ObjectOutputStream(client.getOutputStream());
                outObjeto.writeObject(insertEmpleados);
                outObjeto.flush();
            }
        }
    }
}
