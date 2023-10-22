package Search;

import modelo.Empleados;
import print.PrintEmpleados;
import java.util.ArrayList;
import java.util.List;
import java.io.ObjectOutputStream;
import java.io.IOException;
import java.net.Socket;
import peticiones.Listaempleados;

/**
 *
 * @author Gustavo_Senorans
 */

public class SearchCrudEmpleados {

    public static void handleSearchRequest(String crud, String nombreTabla, String columna,
            String palabraAbuscar, ObjectOutputStream outObjeto, Socket client) throws IOException {

        if (crud.equals("0")) {
            if (nombreTabla.equals("0") && columna.equals("dni")) {

                PrintEmpleados selector = new PrintEmpleados();
                List<Empleados> listaEmpleadosDni = new ArrayList<Empleados>();
                listaEmpleadosDni = Listaempleados.listaTotalEmpleadosDni(palabraAbuscar);

                for (int i = 0; i < listaEmpleadosDni.size(); i++) {
                    if (columna.equals("dni") && palabraAbuscar.equals(listaEmpleadosDni.get(i).getDni())) {

                        String datosEmpleados = selector.obtenerDatosEmpleados(listaEmpleadosDni, columna);
                        System.out.println(datosEmpleados);
                    }

                    outObjeto = new ObjectOutputStream(client.getOutputStream());
                    outObjeto.writeObject(listaEmpleadosDni);
                    outObjeto.flush();
                }
            } else if (nombreTabla.equals("0") && columna.equals("nomempresa")) {

                PrintEmpleados selector = new PrintEmpleados();
                List<Empleados> listaTotalEmpleadosNomEmpresa = new ArrayList<Empleados>();
                listaTotalEmpleadosNomEmpresa = Listaempleados.listaTotalEmpleadosNomEmpresa(palabraAbuscar);

                for (int i = 0; i < listaTotalEmpleadosNomEmpresa.size(); i++) {
                    if (columna.equals("nomempresa") && palabraAbuscar.equals(listaTotalEmpleadosNomEmpresa.get(i).getNomempresa())) {
                        String datosEmpleados = selector.obtenerDatosEmpleados(listaTotalEmpleadosNomEmpresa, columna);
                        System.out.println(datosEmpleados);
                    }

                    outObjeto = new ObjectOutputStream(client.getOutputStream());
                    outObjeto.writeObject(listaTotalEmpleadosNomEmpresa);
                    outObjeto.flush();
                }
            } else if (nombreTabla.equals("0") && columna.equals("departament")) {

                PrintEmpleados selector = new PrintEmpleados();
                List<Empleados> listaTotalEmpleadosDepart = new ArrayList<Empleados>();
                listaTotalEmpleadosDepart = Listaempleados.listaTotalEmpleadosDepart(palabraAbuscar);

                for (int i = 0; i < listaTotalEmpleadosDepart.size(); i++) {
                    if (columna.equals("departament") && palabraAbuscar.equals(listaTotalEmpleadosDepart.get(i).getDepartament())) {
                        String datosEmpleados = selector.obtenerDatosEmpleados(listaTotalEmpleadosDepart, columna);
                        System.out.println(datosEmpleados);
                    }

                    outObjeto = new ObjectOutputStream(client.getOutputStream());
                    outObjeto.writeObject(listaTotalEmpleadosDepart);
                    outObjeto.flush();
                }
            } else if (nombreTabla.equals("0") && columna.equals("codicard")) {

                PrintEmpleados selector = new PrintEmpleados();
                List<Empleados> listaTotalEmpleadosCodiCard = new ArrayList<Empleados>();
                listaTotalEmpleadosCodiCard = Listaempleados.listaTotalEmpleadosCodiCard(Integer.parseInt(palabraAbuscar));

                for (int i = 0; i < listaTotalEmpleadosCodiCard.size(); i++) {
                    String codicard = String.valueOf(listaTotalEmpleadosCodiCard.get(i).getCodicard());
                    if (columna.equals("codicard") && palabraAbuscar.equals(codicard)) {
                        String datosEmpleados = selector.obtenerDatosEmpleados(listaTotalEmpleadosCodiCard, columna);
                        System.out.println(datosEmpleados);
                    }

                    outObjeto = new ObjectOutputStream(client.getOutputStream());
                    outObjeto.writeObject(listaTotalEmpleadosCodiCard);
                    outObjeto.flush();
                }
            } else if (nombreTabla.equals("0") && columna.equals("mail")) {

                PrintEmpleados selector = new PrintEmpleados();
                List<Empleados> listaTotalEmpleadosMail = new ArrayList<Empleados>();
                listaTotalEmpleadosMail = Listaempleados.listaTotalEmpleadosMail(palabraAbuscar);

                for (int i = 0; i < listaTotalEmpleadosMail.size(); i++) {
                    if (columna.equals("mail") && palabraAbuscar.equals(listaTotalEmpleadosMail.get(i).getMail())) {
                        String datosEmpleados = selector.obtenerDatosEmpleados(listaTotalEmpleadosMail, columna);
                        System.out.println(datosEmpleados);
                    }

                    outObjeto = new ObjectOutputStream(client.getOutputStream());
                    outObjeto.writeObject(listaTotalEmpleadosMail);
                    outObjeto.flush();
                }
            } else if (nombreTabla.equals("0") && columna.equals("telephon")) {

                PrintEmpleados selector = new PrintEmpleados();
                List<Empleados> listaTotalEmpleadosTelf = new ArrayList<Empleados>();
                listaTotalEmpleadosTelf = Listaempleados.listaTotalEmpleadosTelf(Integer.parseInt(palabraAbuscar));

                for (int i = 0; i < listaTotalEmpleadosTelf.size(); i++) {
                    String telephon = String.valueOf(listaTotalEmpleadosTelf.get(i).getTelephon());
                    if (columna.equals("telephon") && palabraAbuscar.equals(telephon)) {
                        String datosEmpleados = selector.obtenerDatosEmpleados(listaTotalEmpleadosTelf, columna);
                        System.out.println(datosEmpleados);
                    }

                    outObjeto = new ObjectOutputStream(client.getOutputStream());
                    outObjeto.writeObject(listaTotalEmpleadosTelf);
                    outObjeto.flush();
                }
            }
        }
    }
}
