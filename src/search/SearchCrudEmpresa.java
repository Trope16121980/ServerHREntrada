package search;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.List;

import errores.Errores;
import modelo.Empresa;
import peticiones.Listaempresas;
import print.PrintEmpresa;

/**
 * @author Gustavo Senoráns Varela
 * @version 1.4, 15/11/2023
 * @since jdk 17
 */
public class SearchCrudEmpresa {

    /**
     * Este método verifica los datos y hace el envio de los datos al cliente en
     * forma de objeto y si no le envia un error
     *
     * @param crud en este caso es el 0 de select
     * @param nombreTabla en este caso es el 2, ya que se refiere a users
     * @param columna nombre de la columna nom, address
     * @param palabraAbuscar el array con los datos
     * @param outObjeto el objeto a enviar al cliente
     * @param client el socket del cliente
     * @throws IOException controla los errores
     */
    public static void handleSearchRequest(String crud, String nombreTabla, String columna, String palabraAbuscar,
            ObjectOutputStream outObjeto, Socket client) throws IOException {

        if (crud.equals("0")) {

            if (nombreTabla.equals("2") && columna.equals("nom")) {

                PrintEmpresa empresa = new PrintEmpresa();
                List<Empresa> listaEmpresasNom = Listaempresas.listaEmpresasNom(palabraAbuscar);

                if (!listaEmpresasNom.isEmpty()) {
                    String datosEmpresa = empresa.obtenerDatosEmpresa(listaEmpresasNom, columna);
                    System.out.println(datosEmpresa);
                    outObjeto = new ObjectOutputStream(client.getOutputStream());
                    outObjeto.writeObject(listaEmpresasNom);
                    outObjeto.flush();
                } else {
                    Errores error = new Errores();
                    String erroNomEmpresa = error.erroNomEmpresa();
                    System.out.println(erroNomEmpresa);
                    outObjeto = new ObjectOutputStream(client.getOutputStream());
                    outObjeto.writeObject(erroNomEmpresa);
                    outObjeto.flush();
                }
            } else if (nombreTabla.equals("2") && columna.equals("address")) {
                PrintEmpresa empresa = new PrintEmpresa();
                List<Empresa> listaEmpresasAddress = Listaempresas.listaEmpresasAddress(palabraAbuscar);

                if (!listaEmpresasAddress.isEmpty()) {
                    String datosEmpresa = empresa.obtenerDatosEmpresa(listaEmpresasAddress, columna);
                    System.out.println(datosEmpresa);
                    outObjeto = new ObjectOutputStream(client.getOutputStream());
                    outObjeto.writeObject(listaEmpresasAddress);
                    outObjeto.flush();
                } else {
                    Errores error = new Errores();
                    String erroAddressEmpresa = error.erroAddressEmpresa();
                    System.out.println(erroAddressEmpresa);
                    outObjeto = new ObjectOutputStream(client.getOutputStream());
                    outObjeto.writeObject(erroAddressEmpresa);
                    outObjeto.flush();
                }
            }
        }
    }
}
