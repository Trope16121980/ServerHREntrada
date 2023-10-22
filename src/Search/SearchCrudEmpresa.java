package Search;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import modelo.Empresa;
import peticiones.Listaempresas;
import print.PrintEmpresa;

/**
 *
 * @author Gustavo_Senorans
 */
public class SearchCrudEmpresa {

    public static void handleSearchRequest(String crud, String nombreTabla, String columna,
            String palabraAbuscar, ObjectOutputStream outObjeto, Socket client) throws IOException {

        if (crud.equals("0")) {

            if (nombreTabla.equals("2") && columna.equals("nom")) {
                PrintEmpresa empresa = new PrintEmpresa();
                List<Empresa> listaEmpresasNom = new ArrayList<Empresa>();
                listaEmpresasNom = Listaempresas.listaEmpresasNom(palabraAbuscar);

                for (int i = 0; i < listaEmpresasNom.size(); i++) {
                    if (columna.equals("nom") && palabraAbuscar.equals(listaEmpresasNom.get(i).getNom())) {
                        String datosEmpresa = empresa.obtenerDatosEmpresa(listaEmpresasNom, columna);
                        System.out.println(datosEmpresa);
                    }

                    outObjeto = new ObjectOutputStream(client.getOutputStream());
                    outObjeto.writeObject(listaEmpresasNom);
                    outObjeto.flush();
                }
            } else if (nombreTabla.equals("2") && columna.equals("address")) {
                PrintEmpresa empresa = new PrintEmpresa();
                List<Empresa> listaEmpresasAddress = new ArrayList<Empresa>();
                listaEmpresasAddress = Listaempresas.listaEmpresasAddress(palabraAbuscar);

                for (int i = 0; i < listaEmpresasAddress.size(); i++) {
                    if (columna.equals("address") && palabraAbuscar.equals(listaEmpresasAddress.get(i).getAddress())) {
                        String datosEmpresa = empresa.obtenerDatosEmpresa(listaEmpresasAddress, columna);
                        System.out.println(datosEmpresa);
                    }

                    outObjeto = new ObjectOutputStream(client.getOutputStream());
                    outObjeto.writeObject(listaEmpresasAddress);
                    outObjeto.flush();
                }
            } else if (nombreTabla.equals("2") && columna.equals("telephon")) {
                PrintEmpresa empresa = new PrintEmpresa();
                List<Empresa> listaEmpresasTelepho = new ArrayList<Empresa>();
                listaEmpresasTelepho = Listaempresas.listaEmpresasTelepho(Integer.parseInt(palabraAbuscar));

                for (int i = 0; i < listaEmpresasTelepho.size(); i++) {

                    String telephon = String.valueOf(listaEmpresasTelepho.get(i).getTelephon());

                    if (columna.equals("telephon") && palabraAbuscar.equals(telephon)) {
                        String datosEmpresa = empresa.obtenerDatosEmpresa(listaEmpresasTelepho, columna);
                        System.out.println(datosEmpresa);
                    }

                    outObjeto = new ObjectOutputStream(client.getOutputStream());
                    outObjeto.writeObject(listaEmpresasTelepho);
                    outObjeto.flush();
                }
            }
        }
    }
}
