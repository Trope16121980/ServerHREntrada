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

/**
 * @author Gustavo Senoráns Varela
 * @version 1.4, 15/11/2023
 * @since jdk 17
 */
public class InsertEmpresa {

    /**
     * Conecta con la BBDD HREntrad para realizar la inserción de los datos de
     * la empresa y envia los datos al cliente o un mensaje de error
     * Objeto a recibir del cliente: codiUser,1,2,nom,datoNom,address,datoAddress,telephon,datoTelephon,0
     * 
     * @param crud en este caso es 1 para el insert
     * @param nombreTabla el número de la tabla en este caso 2, ya que se
     * refiere a la empresa
     * @param nom el nombre de la columna de la tabla
     * @param datoNom
     * @param address el nombre de la columna de la tabla
     * @param datoAddress
     * @param telephon el nombre de la columna de la tabla
     * @param datoTelephon
     * @param palabraAbuscar
     * @param outObjeto el objeto que contiene el array
     * @return devuelve los datos de la empresa que se ha insertado si es corecta
     * @param client el socket del cliente al que se le envían los datos
     * @throws IOException controla los errores
     */
    public static ArrayList<Empresa> insertEmpresa(String crud, String nombreTabla, String nom, String datoNom,
            String address, String datoAddress, String telephon, String datoTelephon, String palabraAbuscar,
            ObjectOutputStream outObjeto, Socket client) throws IOException {

        ArrayList<Empresa> insertEmpresa = new ArrayList<>();

        try {
            String consulta = "SELECT * FROM empresa where nom = ?";
            PreparedStatement preparedStatement = controladores.Conexion.getconexion().prepareStatement(consulta);
            preparedStatement.setString(1, datoNom);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                Errores error = new Errores();
                String errorInsertEmpresa = error.errorInsertEmpresa();
                System.out.println(errorInsertEmpresa);
                outObjeto = new ObjectOutputStream(client.getOutputStream());
                outObjeto.writeObject(errorInsertEmpresa);
                outObjeto.flush();
            } else {
                String insert = "INSERT INTO empresa (nom, address, telephon) VALUES (?, ?, ?)";
                preparedStatement = controladores.Conexion.getconexion().prepareStatement(insert);
                preparedStatement.setString(1, datoNom);
                preparedStatement.setString(2, datoAddress);
                preparedStatement.setInt(3, Integer.parseInt(datoTelephon));

                preparedStatement.executeUpdate();
                preparedStatement.close();

                Empresa nuevaEmpresa = new Empresa(datoNom, datoAddress, Integer.parseInt(datoTelephon));
                insertEmpresa.add(nuevaEmpresa);

                return insertEmpresa;
            }
        } catch (SQLException e) {
            Errores error = new Errores();
            String errorInsertEmpresa = error.errorInsertEmpresa();
            System.out.println(errorInsertEmpresa);
            outObjeto = new ObjectOutputStream(client.getOutputStream());
            outObjeto.writeObject(errorInsertEmpresa);
            outObjeto.flush();
        }
        return insertEmpresa;
    }
}
