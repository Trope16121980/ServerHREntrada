package peticiones;

import modelo.Empresa;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * @author Gustavo Senor�ns Varela
 * @version 1.4, 15/11/2023
 * @since jdk 17
 */
public class Listaempresas {

    /**
     * Este m�todo hace la busqueda total de la tabla Empresa y env�a los datos
     * ordenados por su nombre 
     * Objeto a recibir del cliente: codiUser,0,2,0,0,0
     *
     * @return devuelve la listaTotalEmpresas, todos los datos de todos los
     * empleados
     */
    public static ArrayList<Empresa> listaTotalEmpresas() {
        ArrayList<Empresa> listaTotalEmpresas = new ArrayList<>();
        try {

            String consulta = "SELECT * FROM empresa ORDER BY nom";
            PreparedStatement preparedStatement = conn.Conexion.getconexion().prepareStatement(consulta);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                String nom = resultSet.getString("nom");
                String address = resultSet.getString("address");
                int telephon = resultSet.getInt("telephon");
                listaTotalEmpresas.add(new Empresa(nom, address, telephon));

            }
            preparedStatement.close();
            return listaTotalEmpresas;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listaTotalEmpresas;
    }

    /**
     * Este m�todo hace la busqueda de los Empresa por su nombre y env�a los
     * datos ordenados por su nombre
     *
     * @param palabraAbuscar el array que contiene los datos a buscar
     * codiUser,0,2,nom,datoNom,0
     * @return devuelve la listaEmpresasNom, la lista que genera el select a la
     * BBDD HREntrada
     */
    public static ArrayList<Empresa> listaEmpresasNom(String palabraAbuscar) {
        ArrayList<Empresa> listaEmpresasNom = new ArrayList<>();
        try {

            String consulta = "SELECT * FROM empresa where nom = ? ORDER BY nom";
            PreparedStatement preparedStatement = conn.Conexion.getconexion().prepareStatement(consulta);
            preparedStatement.setString(1, palabraAbuscar);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                String nom = resultSet.getString("nom");
                String address = resultSet.getString("address");
                int telephon = resultSet.getInt("telephon");
                listaEmpresasNom.add(new Empresa(nom, address, telephon));

            }
            preparedStatement.close();
            return listaEmpresasNom;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listaEmpresasNom;
    }

    /**
     * Este m�todo hace la busqueda de los Empresa por su direcci�n ordenados y
     * env�a los datos ordenados por su nombre
     *
     * @param palabraAbuscar el array que contiene los datos a buscar
     * codiUser,0,2,address,datoAddress,0
     * @return devuelve la listaEmpresasAddress, la lista que genera el select a
     * la BBDD HREntrada
     */
    public static ArrayList<Empresa> listaEmpresasAddress(String palabraAbuscar) {
        ArrayList<Empresa> listaEmpresasAddress = new ArrayList<>();
        try {

            String consulta = "SELECT * FROM empresa where address = ? ORDER BY nom";
            PreparedStatement preparedStatement = conn.Conexion.getconexion().prepareStatement(consulta);
            preparedStatement.setString(1, palabraAbuscar);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                String nom = resultSet.getString("nom");
                String address = resultSet.getString("address");
                int telephon = resultSet.getInt("telephon");
                listaEmpresasAddress.add(new Empresa(nom, address, telephon));

            }
            preparedStatement.close();
            return listaEmpresasAddress;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listaEmpresasAddress;
    }
}
