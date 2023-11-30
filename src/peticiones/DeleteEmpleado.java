package peticiones;

import errores.Errores;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Random;
import modelo.*;

/**
 * @author Gustavo Senoráns Varela
 * @version 1.4, 15/11/2023
 * @since jdk 17
 */
public class DeleteEmpleado {
   
     public static ArrayList<Empleados> deleteEmpleado(String crud, String nombreTabla, String dni, String datoDni,
            String palabraAbuscar, String palabra, ObjectOutputStream outObjeto, Socket client) throws IOException {

        
        ArrayList<Empleados> empleados = new ArrayList<>();

        try {
            String dniNuevo = "SELECT * FROM empleados where dni = ?";
            try (PreparedStatement psDni = controladores.Conexion.getconexion().prepareStatement(dniNuevo)) {
                psDni.setString(1, datoDni);
                try (ResultSet rsNom = psDni.executeQuery()) {
                    if (rsNom.next()) {
                        String consulta = "DELETE FROM jornada WHERE dni = ?";
                        try (PreparedStatement preparedStatement = controladores.Conexion.getconexion().prepareStatement(consulta)) {
                            preparedStatement.setString(1, datoDni);
                            preparedStatement.executeUpdate();
                            Errores error = new Errores();
                            String deleteJornadaYes = error.deleteJornadaYes();
                            System.out.println(deleteJornadaYes);
                            outObjeto = new ObjectOutputStream(client.getOutputStream());
                            outObjeto.writeObject(deleteJornadaYes);
                            outObjeto.flush();
                            
                        }

                        String deleteConsulta = "DELETE FROM empleados WHERE dni = ?";
                        try (PreparedStatement deleteEmpresa = controladores.Conexion.getconexion().prepareStatement(deleteConsulta)) {
                            deleteEmpresa.setString(1, datoDni);
                            deleteEmpresa.executeUpdate();
                            Errores error = new Errores();
                            String deleteEmpleadoYes = error.deleteEmpleadoYes();
                            System.out.println(deleteEmpleadoYes);
                            outObjeto = new ObjectOutputStream(client.getOutputStream());
                            outObjeto.writeObject(deleteEmpleadoYes);
                            outObjeto.flush();
                        }
                    } else {
                        Errores error = new Errores();
                        String errorDni = error.errorDni();
                        System.out.println(errorDni);
                        outObjeto = new ObjectOutputStream(client.getOutputStream());
                        outObjeto.writeObject(errorDni);
                        outObjeto.flush();
                    }
                }
            }
        } catch (SQLException e) {
            Errores error = new Errores();
            String errorDni = error.errorDni();
            System.out.println(errorDni);
            outObjeto = new ObjectOutputStream(client.getOutputStream());
            outObjeto.writeObject(errorDni);
            outObjeto.flush();
        }

        return empleados;
    }
}
