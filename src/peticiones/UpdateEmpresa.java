package peticiones;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import errores.Errores;
import modelo.Empresa;

public class UpdateEmpresa {

    public static ArrayList<Empresa> updateEmpresa(String crud, String nombreTabla, String nomNuevo,
            String datoNomnuevo, String addressNuevo, String datoAddressNuevo, String telephonNuevo,
            int datoTelephonNuevo, String nom, String datoNom, String palabraAbuscar,
            String palabra, ObjectOutputStream outObjeto, Socket client) throws IOException {

        ArrayList<Empresa> empresa = new ArrayList<Empresa>();
        try {
            String nombreNuevo = "SELECT * FROM empresa where nom = ?";
            PreparedStatement psNom = conn.Conexion.getconexion().prepareStatement(nombreNuevo);
            psNom.setString(1, datoNom);
            ResultSet rsNom = psNom.executeQuery();
            if (!rsNom.next()) {
                Errores error = new Errores();
                String erroNomEmpresa = error.erroNomEmpresa();
                System.out.println(erroNomEmpresa);
                outObjeto = new ObjectOutputStream(client.getOutputStream());
                outObjeto.writeObject(erroNomEmpresa);
                outObjeto.flush();
            } else {
                datoNom = rsNom.getString("nom");

                String consulta = "UPDATE empresa SET nom = ?, address = ?, telephon = ? WHERE nom = ?";
                PreparedStatement preparedStatement = conn.Conexion.getconexion().prepareStatement(consulta);
                preparedStatement.setString(1, datoNomnuevo);
                preparedStatement.setString(2, datoAddressNuevo);
                preparedStatement.setInt(3, datoTelephonNuevo);
                preparedStatement.setString(4, datoNom);

                preparedStatement.executeUpdate();
                preparedStatement.close();

                Empresa nuevaJornada = new Empresa(datoNomnuevo, datoAddressNuevo, datoTelephonNuevo);
                empresa.add(nuevaJornada);
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
