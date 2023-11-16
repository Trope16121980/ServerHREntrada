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
public class UpdateEmpresa {

    /**
     * Este método conecta con la BBDD HREntrada para realizar la modificación
     * de los datos de la empresa y realizar el envío de los mismo o un error
     *
     * Objeto a recibir del cliente:
     * codiUser,2,2,nomNuevo,datoNomnuevo,addressNuevo,datoAddressNuevo,
     * telephonNuevo,datoTelephonNuevo,nom,datoNom,0
     *
     * @param crud en este caso es 2 para el update
     * @param nombreTabla el número de la tabla en este caso 2, ya que se
     * refiere al empleado
     * @param nomNuevo el nombre de la columna de la tabla nombretabla+Nuevo
     * @param datoNomnuevo el nombre nuevo de la empresa a modificar
     * @param addressNuevo el nombre de la columna de la tabla nombretabla+Nuevo
     * @param datoAddressNuevo la dirección nueva de la empresa a modificar
     * @param telephonNuevo el nombre de la columna de la tabla
     * nombretabla+Nuevo
     * @param datoTelephonNuevo el teléfono nuevo de la empresa a modificar
     * @param nom el nombre de la columna original de la tabla
     * @param datoNom el nombre original de la empresa a modificar
     * @param palabraAbuscar al array que contiene los datos
     * @param palabra variable necesaria para las modificaciones
     * @param outObjeto el objeto que contiene el array
     * @param client el socket del cliente al que se le envían los datos
     * @return devuelve los datos del empleado que se ha modificado si es
     * corecto
     * @throws IOException controla los errores
     */
    public static ArrayList<Empresa> updateEmpresa(String crud, String nombreTabla, String nomNuevo,
            String datoNomnuevo, String addressNuevo, String datoAddressNuevo, String telephonNuevo,
            int datoTelephonNuevo, String nom, String datoNom, String palabraAbuscar,
            String palabra, ObjectOutputStream outObjeto, Socket client) throws IOException {

        ArrayList<Empresa> empresa = new ArrayList<Empresa>();
        try {
            String nombreNuevo = "SELECT * FROM empresa where nom = ?";
            PreparedStatement psNom = controladores.Conexion.getconexion().prepareStatement(nombreNuevo);
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
                PreparedStatement preparedStatement = controladores.Conexion.getconexion().prepareStatement(consulta);
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
