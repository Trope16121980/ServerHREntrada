package peticiones;

import errores.Errores;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import modelo.Empleados;
import modelo.Empresa;

/**
 * @author Gustavo Senoráns Varela
 * @version 1.4, 15/11/2023
 * @since jdk 17
 */
public class DeleteEmpresa {

    public static ArrayList<Empresa> deleteEmpresa(String crud, String nombreTabla, String nom, String datoNom,
            String palabraAbuscar, String palabra, ObjectOutputStream outObjeto, Socket client) throws IOException {

        ArrayList<Empresa> empresa = new ArrayList<>();
        ArrayList<Empleados> empleados = new ArrayList<>();

        try {
            String nombreNuevo = "SELECT * FROM empresa where nom = ?";
            try (PreparedStatement psNom = controladores.Conexion.getconexion().prepareStatement(nombreNuevo)) {
                psNom.setString(1, datoNom);
                try (ResultSet rsNom = psNom.executeQuery()) {
                    if (rsNom.next()) {
                        String consulta = "UPDATE empleados SET nomempresa = 'EmpresaBaja' WHERE nomempresa = ?";
                        try (PreparedStatement preparedStatement = controladores.Conexion.getconexion().prepareStatement(consulta)) {
                            preparedStatement.setString(1, datoNom);
                            preparedStatement.executeUpdate();
                        }

                        String deleteConsulta = "DELETE FROM empresa WHERE nom = ?";
                        try (PreparedStatement deleteEmpresa = controladores.Conexion.getconexion().prepareStatement(deleteConsulta)) {
                            deleteEmpresa.setString(1, datoNom);
                            deleteEmpresa.executeUpdate();
                            Errores error = new Errores();
                            String deleteEmpresaYes = error.deleteEmpresaYes();
                            System.out.println(deleteEmpresaYes);
                            outObjeto = new ObjectOutputStream(client.getOutputStream());
                            outObjeto.writeObject(deleteEmpresaYes);
                            outObjeto.flush();
                        }
                    } else {
                        Errores error = new Errores();
                        String erroNomEmpresa = error.erroNomEmpresa();
                        System.out.println(erroNomEmpresa);
                        outObjeto = new ObjectOutputStream(client.getOutputStream());
                        outObjeto.writeObject(erroNomEmpresa);
                        outObjeto.flush();
                    }
                }
            }
        } catch (SQLException e) {
            Errores error = new Errores();
            String erroNomEmpresa = error.erroNomEmpresa();
            System.out.println(erroNomEmpresa);
            outObjeto = new ObjectOutputStream(client.getOutputStream());
            outObjeto.writeObject(erroNomEmpresa);
            outObjeto.flush();
        }

        return empresa;
    }
}
