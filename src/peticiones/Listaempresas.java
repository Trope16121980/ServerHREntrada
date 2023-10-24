
package peticiones;

import modelo.Empresa;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Gustavo_Senorans
 */
public class Listaempresas {

    public static ArrayList<Empresa> listaTotalEmpresas() {//devuelve el dni
        ArrayList<Empresa> listaTotalEmpresas = new ArrayList<>();
        try {

            String consulta = "SELECT * FROM empresa";
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

    public static ArrayList<Empresa> listaEmpresasNom(String palabraAbuscar) {//devuelve el dni
        ArrayList<Empresa> listaEmpresasNom = new ArrayList<>();
        try {

            String consulta = "SELECT * FROM empresa where nom = ?";
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

    public static ArrayList<Empresa> listaEmpresasAddress(String palabraAbuscar) {//devuelve el dni
        ArrayList<Empresa> listaEmpresasAddress = new ArrayList<>();
        try {

            String consulta = "SELECT * FROM empresa where address = ?";
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
    
      public static ArrayList<Empresa> listaEmpresasTelepho(int palabraAbuscar) {//devuelve el dni
        ArrayList<Empresa> listaEmpresasTelepho = new ArrayList<>();
        try {

            String consulta = "SELECT * FROM empresa where telephon = ?";
            PreparedStatement preparedStatement = conn.Conexion.getconexion().prepareStatement(consulta);
            preparedStatement.setInt(1, palabraAbuscar);
            ResultSet resultSet = preparedStatement.executeQuery();

           while (resultSet.next()) {
                String nom = resultSet.getString("nom");
                String address = resultSet.getString("address");
                int telephon = resultSet.getInt("telephon");
                listaEmpresasTelepho.add(new Empresa(nom, address, telephon));

            }
            preparedStatement.close();
            return listaEmpresasTelepho;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listaEmpresasTelepho;
    }
    
    
}