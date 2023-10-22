package search;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import modelo.Jornada;
import peticiones.Listajornada;
import print.PrintJornada;

/**
 *
 * @author Gustavo_Senorans
 */
public class SearchCrudJornada {

    public static void handleSearchRequest(String crud, String nombreTabla, String columna,
            String palabraAbuscar, ObjectOutputStream outObjeto, Socket client) throws IOException {

        if (crud.equals("0")) {
            if (nombreTabla.equals("3") && columna.equals("dni")) {
                PrintJornada jornada = new PrintJornada();
                List<Jornada> listaToJornadaDni = new ArrayList<Jornada>();
                listaToJornadaDni = Listajornada.listaTotalJornadaDni(palabraAbuscar);

                for (int i = 0; i < listaToJornadaDni.size(); i++) {
                    if (columna.equals("dni") && palabraAbuscar.equals(listaToJornadaDni.get(i).getDni())) {
                        String datosEmpresa = jornada.obtenerDatosJornada(listaToJornadaDni, columna);
                        System.out.println(datosEmpresa);
                    }

                    outObjeto = new ObjectOutputStream(client.getOutputStream());
                    outObjeto.writeObject(listaToJornadaDni);
                    outObjeto.flush();
                }
            } else if (nombreTabla.equals("3") && columna.equals("codicard")) {
                PrintJornada jornada = new PrintJornada();
                List<Jornada> listaJornadaCodiCard = new ArrayList<Jornada>();
                listaJornadaCodiCard = Listajornada.listaJornadaCodiCard(Integer.parseInt(palabraAbuscar));

                for (int i = 0; i < listaJornadaCodiCard.size(); i++) {

                    String codicard = String.valueOf(listaJornadaCodiCard.get(i).getCodicard());

                    if (columna.equals("codicard") && palabraAbuscar.equals(codicard)) {
                        String datosEmpresa = jornada.obtenerDatosJornada(listaJornadaCodiCard, columna);
                        System.out.println(datosEmpresa);
                    }

                    outObjeto = new ObjectOutputStream(client.getOutputStream());
                    outObjeto.writeObject(listaJornadaCodiCard);
                    outObjeto.flush();
                }
            } else if (nombreTabla.equals("3") && columna.equals("fecha")) {
                PrintJornada jornada = new PrintJornada();
                List<Jornada> listaTotalJornadaFecha = new ArrayList<Jornada>();
                listaTotalJornadaFecha = Listajornada.listaTotalJornadaFecha(palabraAbuscar);

                for (int i = 0; i < listaTotalJornadaFecha.size(); i++) {
                    if (columna.equals("fecha") && palabraAbuscar.equals(listaTotalJornadaFecha.get(i).getFecha())) {
                        String datosEmpresa = jornada.obtenerDatosJornada(listaTotalJornadaFecha, columna);
                        System.out.println(datosEmpresa);
                    }

                    outObjeto = new ObjectOutputStream(client.getOutputStream());
                    outObjeto.writeObject(listaTotalJornadaFecha);
                    outObjeto.flush();
                }
            }
        }
    }

}
