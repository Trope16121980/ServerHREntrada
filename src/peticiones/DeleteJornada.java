package peticiones;

import errores.Errores;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import modelo.*;

/**
 * @author Gustavo Senoráns Varela
 * @version 1.4, 15/11/2023
 * @since jdk 17
 */
public class DeleteJornada {

    public static ArrayList<Jornada> deleteJornada(String crud, String nombreTabla, String dni, String datoDni, String fecha, String datoFecha,
            String palabraAbuscar, String palabra, ObjectOutputStream outObjeto, Socket client) throws IOException {

        ArrayList<Jornada> jornada = new ArrayList<>();

        try {
            String dniNuevo = "SELECT * FROM jornada where dni = ? and fecha = ?";
            PreparedStatement psDni = controladores.Conexion.getconexion().prepareStatement(dniNuevo);
            psDni.setString(1, datoDni);
            psDni.setString(2, datoFecha);
            ResultSet rsNom = psDni.executeQuery();
            if (rsNom.next()) {
                String deleteConsulta = "DELETE FROM jornada where dni = ? and fecha = ?";
                PreparedStatement deleteUsers = controladores.Conexion.getconexion().prepareStatement(deleteConsulta);
                deleteUsers.setString(1, datoDni);
                deleteUsers.setString(2, datoFecha);
                deleteUsers.executeUpdate();
                Errores error = new Errores();
                String deleteJornadaYes = error.deleteJornadaYes();
                outObjeto = new ObjectOutputStream(client.getOutputStream());
                outObjeto.writeObject(deleteJornadaYes);
                outObjeto.flush();
            } else {
                Errores error = new Errores();
                String erroDniFechaJornada = error.erroDniFechaJornada();
                System.out.println(erroDniFechaJornada);
                outObjeto = new ObjectOutputStream(client.getOutputStream());
                outObjeto.writeObject(erroDniFechaJornada);
                outObjeto.flush();
            }
        } catch (SQLException e) {
            Errores error = new Errores();
            String erroDniFechaJornada = error.erroDniFechaJornada();
            System.out.println(erroDniFechaJornada);
            outObjeto = new ObjectOutputStream(client.getOutputStream());
            outObjeto.writeObject(erroDniFechaJornada);
            outObjeto.flush();
        }
        return jornada;
    }
}
