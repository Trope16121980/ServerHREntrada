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
public class DeleteUsers {

    public static ArrayList<Users> deleteUsers(String crud, String nombreTabla, String dni, String datoDni,
            String palabraAbuscar, String palabra, ObjectOutputStream outObjeto, Socket client) throws IOException {

        ArrayList<Users> users = new ArrayList<>();

        try {
            String dniNuevo = "SELECT * FROM users where dni = ?";
            PreparedStatement psDni = controladores.Conexion.getconexion().prepareStatement(dniNuevo);
            psDni.setString(1, datoDni);
            ResultSet rsNom = psDni.executeQuery();
            if (rsNom.next()) {
                String deleteConsulta = "DELETE FROM users WHERE dni = ?";
                try {
                    PreparedStatement deleteUsers = controladores.Conexion.getconexion().prepareStatement(deleteConsulta);
                    deleteUsers.setString(1, datoDni);
                    deleteUsers.executeUpdate();
                    Errores error = new Errores();
                    String deleteUsersYes = error.deleteUsersYes();
                    outObjeto = new ObjectOutputStream(client.getOutputStream());
                    outObjeto.writeObject(deleteUsersYes);
                    outObjeto.flush();
                } catch (SQLException e) {
                    Errores error = new Errores();
                    String errorDni = error.errorDni();
                    System.out.println(errorDni);
                    outObjeto = new ObjectOutputStream(client.getOutputStream());
                    outObjeto.writeObject(errorDni);
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
        } catch (SQLException e) {
            Errores error = new Errores();
            String errorDni = error.errorDni();
            System.out.println(errorDni);
            outObjeto = new ObjectOutputStream(client.getOutputStream());
            outObjeto.writeObject(errorDni);
            outObjeto.flush();
        }
        return users;
    }
}
