/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thread;

/**
 *
 * @author Gustavo_Senorans
 */
import codigo.Codigo;
import envio.Enviologin;
import fecha.Fechas;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.*;
import java.io.*;
import modelo.*;
import peticiones.*;

/**
 *
 *
 */
public class Threadllogin extends Thread {

//    private static final int MAX_INACTIVITY_TIME_SECONDS = 30; // 5 minutos de inactividad
//    private Timer inactivityTimer = new Timer();
    private Socket client;
    private Scanner in;
    private PrintWriter out;
    private HashMap logins;
    private String codigo;
    Fechas fecha = new Fechas();
    boolean salir = false;
    BufferedWriter escriptor;
    BufferedReader lector;

    public Threadllogin(Socket client, HashMap<String, String> logins) {
        try {
            this.client = client;
            this.in = new Scanner(client.getInputStream());
            this.out = new PrintWriter(client.getOutputStream(), true);
            this.logins = logins;
//            inactivityTimer.schedule(new InactivityTimerTask(), MAX_INACTIVITY_TIME_SECONDS * 1000);
        } catch (IOException ex) {
            Logger.getLogger(Threadllogin.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void run() {
        String msg;
        Enviologin soft = new Enviologin();
        String[] codigosLogin = new String[2];
        codigo = "-1";
        String dni = "-1";
        Users user = null;

        try {

            //IMPLEMENTA
            escriptor = new BufferedWriter(new OutputStreamWriter(client.getOutputStream()));
            lector = new BufferedReader(new InputStreamReader(client.getInputStream()));

//            //enviamos al cliente la pregunta y el mensaje de bienvenida
            ObjectOutputStream outObjeto = null;
            msg = "";
            escriptor.write(msg);//enviamos
            escriptor.newLine();
            escriptor.flush();
            //leemos la respuesta con la palabra a buscar   

            try {
                String palabra = lector.readLine(); //recibimos  

                if (palabra != null && palabra.equalsIgnoreCase("exit")) {
                    exit();
                } else {
                    System.out.println("Bienvenido al ServeHREntrada");
                    System.out.println(fecha.fecha_hora());
                    String[] datos = new String[2];//preparamos un array de string para login:password
                    String login = "-1";
                    String pass = "-1";
                    datos = palabra.split(",");
                    if (datos[0] != null && datos[1] != null) {
                        login = datos[0];
                        pass = datos[1];
                    }
                    System.out.println("____________________________________________________________________");
                    System.out.println("Datos de login recibidos:\nLogin : " + login
                            + "\nPass: " + pass);
                    
                    peticiones.Login dvLogin = new peticiones.Login();
                    user = dvLogin.comprobarCredencialesBD(login, pass);

                    try {
                        if (user != null) {
                            if (logins.containsKey(user.getDni())) {
                                msg = "-2";
                                System.out.println("Cliente desconectado, ya esta conectado este usuario.");
                                escriptor.write(msg);
                                escriptor.newLine();
                                escriptor.flush();
                                lector.close();
                                client.close();
                            } else {
                                codigo = Codigo.crearCodigoLogin(user.getNumtipe());
                            }
                        }
                        if (codigo.equalsIgnoreCase("-1")) {//si es -1 es un error

                            //si no es -1 es que nos ha enviado un codigo
                            //enviamos al cliente el codigo y luego ya vamos con que nos envie que quiere buscar (Empleados)
                            msg = "-1";
                            System.out.println(fecha.fecha_hora());
                            System.out.println("____________________________________________________________________");
                            System.out.println("Ciente desconectado, error en el login");
                            escriptor.write(msg);//enviamos
                            escriptor.newLine();
                            escriptor.flush();
                            lector.close();
                            client.close();
                        } else {
                            //si no es -1 es que nos ha enviado un codigo
                            //enviamos al cliente el codigo y luego ya vamos con que nos envie que quiere buscar (Empleados)
                            msg = codigo;
                            escriptor.write(msg);
                            escriptor.newLine();
                            escriptor.flush();

                            logins.put(user.getDni(), codigo);
                            try {
                                while (!salir) {
                                    //leemos la respuesta con la palabra a buscar
                                    palabra = lector.readLine(); //recibimos la consulta
                                    System.out.println(fecha.fecha_hora());

                                    if (palabra.equals(null) || palabra.equalsIgnoreCase("exit")) {
                                        System.out.println("____________________________________________________________________");
                                        System.out.println("Cliente con código " + codigo + " que pertenece al usuario " + login
                                                + "\nse ha desconectado correctamente.");
                                        salir = true;
                                        escriptor.close();
                                        lector.close();
                                        client.close();

                                    } else {
                                        System.out.println("____________________________________________________________________");
                                        System.out.println("Contenido a enviar al cliente: " + palabra);

                                        SelectColumna verificar = new SelectColumna();
                                        verificar.selectColumna(palabra);

                                        String[] frase = new String[6];
                                        String[] NomApellido = new String[8];
                                        String[] insertEmpresas = new String[10];
                                        String[] insertUsuarios = new String[12];
                                        String[] insertEmpleadoMailTelf = new String[16];
                                        String[] insertEmpleadoMT = new String[18];
                                        String[] insertEmpleado = new String[20];

                                        frase = palabra.split(",");
                                        NomApellido = palabra.split(",");
                                        insertEmpresas = palabra.split(",");
                                        insertUsuarios = palabra.split(",");
                                        insertEmpleadoMailTelf = palabra.split(",");
                                        insertEmpleadoMT = palabra.split(",");
                                        insertEmpleado = palabra.split(",");

                                        if (frase[5].equals("0") || frase[5].equals("1")) {
                                            String codigoUserRecibido = frase[0]; //el codigo recibido tiene que ser el mismo que le hemos asignado
                                            String crud = frase[1];
                                            String nombreTabla = frase[2]; //Será el numero de tabla. (ej: 1->empleados 2->users 3-jornada 4-usertipe 5->empresa)
                                            String columna = frase[3]; //sera la palabra que busquemos(ej: juan,1234567D), si ponemos 0 sera todos los de la tabla
                                            String palabraAbuscar = frase[4];// si es el caso será la columna (,dni,nom,etc), si no hay ponemos 0
                                            String orden = frase[5];// si es el caso el orden, si no hay ponemos 0

                                            if (!codigo.equals(codigoUserRecibido)) {
                                                System.out.println("____________________________________________________________________");
                                                System.out.println(fecha.fecha_hora());
                                                System.out.println("Ciente desconectado, el codigo no es el correcto");
                                                salir = true;
                                                escriptor.close();
                                                lector.close();
                                                client.close();

                                            } else if (orden.equals("0") || orden.equals("1")) {

                                                System.out.println("____________________________________________________________________");
                                                System.out.println(fecha.fecha_hora());
                                                System.out.println("El codigo es correcto, es :" + codigo);
                                                System.out.println("codigoUserRecibido: " + codigoUserRecibido);
                                                System.out.println("nombreTabla: " + nombreTabla);
                                                System.out.println("columna: " + columna);
                                                System.out.println("palabraAbuscar: " + palabraAbuscar);
                                                System.out.println("orden: " + orden);
                                                System.out.println("____________________________________________________________________");

                                                if (crud.equals("0")) {
                                                    if (nombreTabla.equals("0") && columna.equals("dni")) {

                                                        SelectEmpleados selector = new SelectEmpleados();
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

                                                        SelectEmpleados selector = new SelectEmpleados();
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

                                                        SelectEmpleados selector = new SelectEmpleados();
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

                                                        SelectEmpleados selector = new SelectEmpleados();
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

                                                        SelectEmpleados selector = new SelectEmpleados();
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

                                                        SelectEmpleados selector = new SelectEmpleados();
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
                                                    } else if (nombreTabla.equals("1") && columna.equals("dni")) {
                                                        SelectUsers usuarios = new SelectUsers();
                                                        List<Users> listaToUsersDni = new ArrayList<Users>();
                                                        listaToUsersDni = Listausers.listaTotalUsersDni(palabraAbuscar);

                                                        for (int i = 0; i < listaToUsersDni.size(); i++) {
                                                            if (columna.equals("dni") && palabraAbuscar.equals(listaToUsersDni.get(i).getDni())) {
                                                                String datosUsers = usuarios.obtenerDatosUsers(listaToUsersDni, columna);
                                                                System.out.println(datosUsers);
                                                            }

                                                            outObjeto = new ObjectOutputStream(client.getOutputStream());
                                                            outObjeto.writeObject(listaToUsersDni);
                                                            outObjeto.flush();
                                                        }
                                                    } else if (nombreTabla.equals("1") && columna.equals("login")) {
                                                        SelectUsers usuarios = new SelectUsers();
                                                        List<Users> listaTotalUsersLogin = new ArrayList<Users>();
                                                        listaTotalUsersLogin = Listausers.listaTotalUsersLogin(palabraAbuscar);

                                                        for (int i = 0; i < listaTotalUsersLogin.size(); i++) {
                                                            if (columna.equals("login") && palabraAbuscar.equals(listaTotalUsersLogin.get(i).getLogin())) {
                                                                String datosUsers = usuarios.obtenerDatosUsers(listaTotalUsersLogin, columna);
                                                                System.out.println(datosUsers);
                                                            }

                                                            outObjeto = new ObjectOutputStream(client.getOutputStream());
                                                            outObjeto.writeObject(listaTotalUsersLogin);
                                                            outObjeto.flush();
                                                        }
                                                    } else if (nombreTabla.equals("1") && columna.equals("numtipe")) {
                                                        SelectUsers usuarios = new SelectUsers();
                                                        List<Users> listaTotalUsersTipe = new ArrayList<Users>();
                                                        listaTotalUsersTipe = Listausers.listaTotalUsersTipe(Integer.parseInt(palabraAbuscar));
                                                        for (int i = 0; i < listaTotalUsersTipe.size(); i++) {

                                                            String numtipe = String.valueOf(listaTotalUsersTipe.get(i).getNumtipe());

                                                            if (columna.equals("numtipe") && palabraAbuscar.equals(numtipe)) {
                                                                String datosUsers = usuarios.obtenerDatosUsers(listaTotalUsersTipe, columna);
                                                                System.out.println(datosUsers);
                                                            }

                                                            outObjeto = new ObjectOutputStream(client.getOutputStream());
                                                            outObjeto.writeObject(listaTotalUsersTipe);
                                                            outObjeto.flush();
                                                        }
                                                    } else if (nombreTabla.equals("2") && columna.equals("nom")) {
                                                        SelectEmpresa empresa = new SelectEmpresa();
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
                                                        SelectEmpresa empresa = new SelectEmpresa();
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
                                                        SelectEmpresa empresa = new SelectEmpresa();
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
                                                    } else if (nombreTabla.equals("3") && columna.equals("dni")) {
                                                        SelectJornada jornada = new SelectJornada();
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
                                                        SelectJornada jornada = new SelectJornada();
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
                                                        SelectJornada jornada = new SelectJornada();
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
                                                    } else if (!nombreTabla.equals(null) && columna.equals("0")) {
                                                        switch (nombreTabla) {
                                                            case "0" -> {

                                                                List<Empleados> listaEmpleados = new ArrayList<Empleados>();
                                                                listaEmpleados = Listaempleados.listaTotalEmpleados();

                                                                outObjeto = new ObjectOutputStream(client.getOutputStream());
                                                                outObjeto.writeObject(listaEmpleados);
                                                                outObjeto.flush();
                                                            }
                                                            case "1" -> {

                                                                List<Users> listaToUsers = new ArrayList<Users>();
                                                                listaToUsers = Listausers.listaTotalUsers();

                                                                outObjeto = new ObjectOutputStream(client.getOutputStream());
                                                                outObjeto.writeObject(listaToUsers);
                                                                outObjeto.flush();
                                                            }
                                                            case "2" -> {

                                                                List<Empresa> listaEmpresas = new ArrayList<Empresa>();
                                                                listaEmpresas = Listaempresas.listaTotalEmpresas();

                                                                outObjeto = new ObjectOutputStream(client.getOutputStream());
                                                                outObjeto.writeObject(listaEmpresas);
                                                                outObjeto.flush();
                                                            }

                                                            case "3" -> {

                                                                List<Jornada> listaJornada = new ArrayList<Jornada>();
                                                                listaJornada = Listajornada.listaTotalJornada();

                                                                outObjeto = new ObjectOutputStream(client.getOutputStream());
                                                                outObjeto.writeObject(listaJornada);
                                                                outObjeto.flush();
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        } else if (NomApellido[7].equals("0") || NomApellido[7].equals("1")) {

                                            String codigoUserRecibido = NomApellido[0]; //el codigo recibido tiene que ser el mismo que le hemos asignado
                                            String crud = NomApellido[1];
                                            String nombreTabla = NomApellido[2]; //Será el numero de tabla. (ej: 1->empleados 2->users 3-jornada 4-usertipe 5->empresa)
                                            String nom = NomApellido[3]; //sera la palabra que busquemos(ej: juan,1234567D), si ponemos 0 sera todos los de la tabla
                                            String datoNom = NomApellido[4];// si es el caso será la columna (,dni,nom,etc), si no hay ponemos 0
                                            String apellido = NomApellido[5]; //sera la palabra que busquemos(ej: juan,1234567D), si ponemos 0 sera todos los de la tabla
                                            String datoApellido = NomApellido[6];
                                            String orden = NomApellido[7];// si es el caso el orden, si no hay ponemos 0

                                            if (!codigo.equals(codigoUserRecibido)) {
                                                System.out.println("____________________________________________________________________");
                                                System.out.println(fecha.fecha_hora());
                                                System.out.println("Ciente desconectado, el codigo no es el correcto");
                                                salir = true;
                                                escriptor.close();
                                                lector.close();
                                                client.close();

                                            } else if (orden.equals("0") || orden.equals("1")) {

                                                System.out.println("____________________________________________________________________");
                                                System.out.println(fecha.fecha_hora());
                                                System.out.println("El codigo es correcto, es :" + codigo);
                                                System.out.println("codigoUserRecibido: " + codigoUserRecibido);
                                                System.out.println("crud: " + crud);
                                                System.out.println("nombreTabla: " + nombreTabla);
                                                System.out.println("nom: " + nom);
                                                System.out.println("datoNom: " + datoNom);
                                                System.out.println("apellido: " + apellido);
                                                System.out.println("datoApellido: " + datoApellido);
                                                System.out.println("orden: " + orden);
                                                System.out.println("____________________________________________________________________");

                                                if (crud.equals("0")) {
                                                    if (nombreTabla.equals("0") && nom.equals("nom") && apellido.equals("apellido")) {
                                                        SelectEmpleados selectorNomApellido = new SelectEmpleados();

                                                        List<Empleados> listaEmpleadosNomApellido = new ArrayList<Empleados>();
                                                        listaEmpleadosNomApellido = Listaempleados.listaEmpleadosNomApellido(datoNom, datoApellido);
                                                        String datosEmpleadosNomApellido = selectorNomApellido.obtenerDatosEmpleadosNomApellido(listaEmpleadosNomApellido, nom, apellido);
                                                        System.out.println(datosEmpleadosNomApellido);

                                                        outObjeto = new ObjectOutputStream(client.getOutputStream());
                                                        outObjeto.writeObject(listaEmpleadosNomApellido);
                                                        outObjeto.flush();
//                                                        }
                                                    } else if (nombreTabla.equals("3") && nom.equals("nom") && apellido.equals("apellido")) {
                                                        SelectJornada selectorJornadaNomApellido = new SelectJornada();

                                                        List<Jornada> listaJornadaNomApellido = new ArrayList<Jornada>();
                                                        listaJornadaNomApellido = Listajornada.listaJornadaNomApellido(datoNom, datoApellido);

                                                        String datosJornadaNomApellido = selectorJornadaNomApellido.obtenerDatosJornadaNomApellido(listaJornadaNomApellido, nom, apellido);
                                                        System.out.println(datosJornadaNomApellido);

                                                        outObjeto = new ObjectOutputStream(client.getOutputStream());
                                                        outObjeto.writeObject(listaJornadaNomApellido);
                                                        outObjeto.flush();
//                                                        }
                                                    }
                                                }
                                            }
                                        } else if (insertEmpresas[9].equals("0") || insertEmpresas[9].equals("1")) {
                                            String codigoUserRecibido = insertEmpresas[0]; //el codigo recibido tiene que ser el mismo que le hemos asignado
                                            String crud = insertEmpresas[1];
                                            String nombreTabla = insertEmpresas[2]; //Será el numero de tabla. (ej: 1->empleados 2->users 3-jornada 4-usertipe 5->empresa)
                                            String nom = insertEmpresas[3]; //sera la palabra que busquemos(ej: juan,1234567D), si ponemos 0 sera todos los de la tabla
                                            String datoNom = insertEmpresas[4];
                                            String address = insertEmpresas[5];
                                            String datoAddress = insertEmpresas[6];
                                            String telephon = insertEmpresas[7]; //sera la palabra que busquemos(ej: juan,1234567D), si ponemos 0 sera todos los de la tabla
                                            String datoTelephon = insertEmpresas[8];
                                            String orden = insertEmpresas[9];// si es el caso el orden, si no hay ponemos 0

                                            if (!codigo.equals(codigoUserRecibido)) {
                                                System.out.println("____________________________________________________________________");
                                                System.out.println(fecha.fecha_hora());
                                                System.out.println("Ciente desconectado, el codigo no es el correcto");
                                                salir = true;
                                                escriptor.close();
                                                lector.close();
                                                client.close();

                                            } else if (orden.equals("0") || orden.equals("1")) {
                                                if (crud.equals("1")) {
                                                    if (nombreTabla.equals("2")) {
                                                        List<Empresa> insertEmpresa = new ArrayList<Empresa>();
                                                        insertEmpresa = InsertEmpresa.insertEmpresa(datoNom, datoAddress, Integer.parseInt(datoTelephon));
                                                        System.out.println("Empleado creado correctamente, sus datos son:\n");
                                                        System.out.println("____________________________________________________________________");
                                                        System.out.println("Nombre: " + datoNom + "\n"
                                                                + "Address: " + datoAddress + "\n"
                                                                + "Teléfono: " + Integer.parseInt(datoTelephon) + "\n");
                                                        System.out.println("____________________________________________________________________");
                                                        outObjeto = new ObjectOutputStream(client.getOutputStream());
                                                        outObjeto.writeObject(insertEmpresa);
                                                        outObjeto.flush();
                                                    }
                                                }
                                            }
                                        } else if (insertUsuarios[11].equals("0") || insertUsuarios[11].equals("1")) {
                                            String codigoUserRecibido = insertUsuarios[0]; //el codigo recibido tiene que ser el mismo que le hemos asignado
                                            String crud = insertUsuarios[1];
                                            String nombreTabla = insertUsuarios[2]; //Será el numero de tabla. (ej: 1->empleados 2->users 3-jornada 4-usertipe 5->empresa)
                                            login = insertUsuarios[3]; //sera la palabra que busquemos(ej: juan,1234567D), si ponemos 0 sera todos los de la tabla
                                            String datoLogin = insertUsuarios[4];
                                            pass = insertUsuarios[5];
                                            String datoPass = insertUsuarios[6];
                                            String numTipe = insertUsuarios[7]; //sera la palabra que busquemos(ej: juan,1234567D), si ponemos 0 sera todos los de la tabla
                                            String datoNumTipe = insertUsuarios[8];
                                            dni = insertUsuarios[9]; //sera la palabra que busquemos(ej: juan,1234567D), si ponemos 0 sera todos los de la tabla
                                            String datoDni = insertUsuarios[10];
                                            String orden = insertUsuarios[11];// si es el caso el orden, si no hay ponemos 0

                                            if (!codigo.equals(codigoUserRecibido)) {
                                                System.out.println("____________________________________________________________________");
                                                System.out.println(fecha.fecha_hora());
                                                System.out.println("Ciente desconectado, el codigo no es el correcto");
                                                salir = true;
                                                escriptor.close();
                                                lector.close();
                                                client.close();

                                            } else if (orden.equals("0") || orden.equals("1")) {

                                                System.out.println("____________________________________________________________________");
                                                System.out.println("codigoUserRecibido: " + codigoUserRecibido);
                                                System.out.println("crud: " + crud);
                                                System.out.println("nombreTabla: " + nombreTabla);
                                                System.out.println("login: " + login);
                                                System.out.println("datoLogin: " + datoLogin);
                                                System.out.println("pass: " + pass);
                                                System.out.println("datoPass: " + datoPass);
                                                System.out.println("numTipe: " + numTipe);
                                                System.out.println("datoNumTipe: " + datoNumTipe);
                                                System.out.println("dni: " + dni);
                                                System.out.println("datoDni: " + datoDni);
                                                System.out.println("orden: " + orden);
                                                System.out.println("____________________________________________________________________");

                                                if (crud.equals("1")) {
                                                    if (nombreTabla.equals("1")) {
                                                        List<Users> insertUser = new ArrayList<Users>();
                                                        insertUser = InsertUsers.insertUser(datoLogin, datoPass, Integer.parseInt(datoNumTipe), datoDni);
                                                        System.out.println(("Empleado creado correctamente, sus datos son: \n"));
                                                        System.out.println("Login: " + datoLogin + "\n"
                                                                + "Pass: " + datoPass + "\n"
                                                                + "Num Tipe: " + datoNumTipe + "\n"
                                                                + "Dni: " + datoDni + "\n");
                                                        System.out.println("____________________________________________________________________");
                                                        outObjeto = new ObjectOutputStream(client.getOutputStream());
                                                        outObjeto.writeObject(insertUser);
                                                        outObjeto.flush();
                                                    }
                                                }
                                            }
                                        } else if (insertEmpleadoMailTelf[15].equals("0") || insertEmpleadoMailTelf[15].equals("1")) {
                                            String codigoUserRecibido = insertEmpleadoMailTelf[0]; //el codigo recibido tiene que ser el mismo que le hemos asignado
                                            String crud = insertEmpleadoMailTelf[1];
                                            String nombreTabla = insertEmpleadoMailTelf[2]; //Será el numero de tabla. (ej: 1->empleados 2->users 3-jornada 4-usertipe 5->empresa)
                                            dni = insertEmpleadoMailTelf[3]; //sera la palabra que busquemos(ej: juan,1234567D), si ponemos 0 sera todos los de la tabla
                                            String datoDni = insertEmpleadoMailTelf[4];// si es el caso será la columna (,dni,nom,etc), si no hay ponemos 0
                                            String nom = insertEmpleadoMailTelf[5]; //sera la palabra que busquemos(ej: juan,1234567D), si ponemos 0 sera todos los de la tabla
                                            String datoNom = insertEmpleadoMailTelf[6];
                                            String apellido = insertEmpleadoMailTelf[7];// si es el caso el orden, si no hay ponemos 0
                                            String datoApellido = insertEmpleadoMailTelf[8]; //el codigo recibido tiene que ser el mismo que le hemos asignado
                                            String nomempresa = insertEmpleadoMailTelf[9];
                                            String datoNomempresa = insertEmpleadoMailTelf[10]; //Será el numero de tabla. (ej: 1->empleados 2->users 3-jornada 4-usertipe 5->empresa)
                                            String departament = insertEmpleadoMailTelf[11]; //sera la palabra que busquemos(ej: juan,1234567D), si ponemos 0 sera todos los de la tabla
                                            String datoDepartament = insertEmpleadoMailTelf[12];// si es el caso será la columna (,dni,nom,etc), si no hay ponemos 0
                                            String codicard = insertEmpleadoMailTelf[13]; //sera la palabra que busquemos(ej: juan,1234567D), si ponemos 0 sera todos los de la tabla
                                            String datoCodicard = insertEmpleadoMailTelf[14];
                                            String orden = insertEmpleadoMailTelf[15];// si es el caso el orden, si no hay ponemos 0

                                            if (!codigo.equals(codigoUserRecibido)) {
                                                System.out.println("____________________________________________________________________");
                                                System.out.println(fecha.fecha_hora());
                                                System.out.println("Ciente desconectado, el codigo no es el correcto");
                                                salir = true;
                                                escriptor.close();
                                                lector.close();
                                                client.close();

                                            } else if (orden.equals("0") || orden.equals("1")) {

                                                System.out.println("____________________________________________________________________");
                                                System.out.println("codigoUserRecibido: " + codigoUserRecibido);
                                                System.out.println("crud: " + crud);
                                                System.out.println("nombreTabla: " + nombreTabla);
                                                System.out.println("dni: " + dni);
                                                System.out.println("datoDni: " + datoDni);
                                                System.out.println("nom: " + nom);
                                                System.out.println("datoNom: " + datoNom);
                                                System.out.println("apellido: " + apellido);
                                                System.out.println("datoApellido: " + datoApellido);
                                                System.out.println("nomempresa: " + nomempresa);
                                                System.out.println("datoNomempresa: " + datoNomempresa);
                                                System.out.println("departament: " + departament);
                                                System.out.println("datoDepartament: " + datoDepartament);
                                                System.out.println("codicard: " + codicard);
                                                System.out.println("datoCodicar: " + datoCodicard);
                                                System.out.println("orden: " + orden);
                                                System.out.println("____________________________________________________________________");

                                                if (crud.equals("1")) {
                                                    if (nombreTabla.equals("0")) {
                                                        List<Empleados> insertEmpleadosMailTelf = new ArrayList<Empleados>();
                                                        insertEmpleadosMailTelf = InsertEmpleados.insertEmpleadosMailTelf(datoDni, datoNom,
                                                                datoApellido, datoNomempresa, datoDepartament,
                                                                Integer.parseInt(datoCodicard));
                                                        System.out.println("Empleado creado correctamente, sus datos son:\n");
                                                        System.out.println("____________________________________________________________________");
                                                        System.out.println("Dni: " + datoDni + "\n"
                                                                + "Nombre: " + datoNom + "\n"
                                                                + "Apellido: " + datoApellido + "\n"
                                                                + "Nombre empresa: " + datoNomempresa + "\n"
                                                                + "Departamento: " + datoDepartament + "\n"
                                                                + "Codigo tarjeta: " + Integer.parseInt(datoCodicard));
                                                        System.out.println("____________________________________________________________________");
                                                        outObjeto = new ObjectOutputStream(client.getOutputStream());
                                                        outObjeto.writeObject(insertEmpleadosMailTelf);
                                                        outObjeto.flush();
                                                    }
                                                }
                                            }

                                        } else if (insertEmpleadoMT[17].equals("0") && insertEmpleadoMT[15].equals("mail")
                                                || insertEmpleadoMT[17].equals("1") && insertEmpleadoMT[15].equals("mail")) {
                                            String codigoUserRecibido = insertEmpleadoMT[0]; //el codigo recibido tiene que ser el mismo que le hemos asignado
                                            String crud = insertEmpleadoMT[1];
                                            String nombreTabla = insertEmpleadoMT[2]; //Será el numero de tabla. (ej: 1->empleados 2->users 3-jornada 4-usertipe 5->empresa)
                                            dni = insertEmpleadoMT[3]; //sera la palabra que busquemos(ej: juan,1234567D), si ponemos 0 sera todos los de la tabla
                                            String datoDni = insertEmpleadoMT[4];// si es el caso será la columna (,dni,nom,etc), si no hay ponemos 0
                                            String nom = insertEmpleadoMT[5]; //sera la palabra que busquemos(ej: juan,1234567D), si ponemos 0 sera todos los de la tabla
                                            String datoNom = insertEmpleadoMT[6];
                                            String apellido = insertEmpleadoMT[7];// si es el caso el orden, si no hay ponemos 0
                                            String datoApellido = insertEmpleadoMT[8]; //el codigo recibido tiene que ser el mismo que le hemos asignado
                                            String nomempresa = insertEmpleadoMT[9];
                                            String datoNomempresa = insertEmpleadoMT[10]; //Será el numero de tabla. (ej: 1->empleados 2->users 3-jornada 4-usertipe 5->empresa)
                                            String departament = insertEmpleadoMT[11]; //sera la palabra que busquemos(ej: juan,1234567D), si ponemos 0 sera todos los de la tabla
                                            String datoDepartament = insertEmpleadoMT[12];// si es el caso será la columna (,dni,nom,etc), si no hay ponemos 0
                                            String codicard = insertEmpleadoMT[13]; //sera la palabra que busquemos(ej: juan,1234567D), si ponemos 0 sera todos los de la tabla
                                            String datoCodicard = insertEmpleadoMT[14];
                                            String mail = insertEmpleadoMT[15];// si es el caso el orden, si no hay ponemos 0
                                            String datoMail = insertEmpleadoMT[16];
                                            String orden = insertEmpleadoMT[17];// si es el caso el orden, si no hay ponemos 0

                                            if (!codigo.equals(codigoUserRecibido)) {
                                                System.out.println("____________________________________________________________________");
                                                System.out.println(fecha.fecha_hora());
                                                System.out.println("Ciente desconectado, el codigo no es el correcto");
                                                salir = true;
                                                escriptor.close();
                                                lector.close();
                                                client.close();

                                            } else if (orden.equals("0") || orden.equals("1")) {

                                                System.out.println("____________________________________________________________________");
                                                System.out.println("codigoUserRecibido: " + codigoUserRecibido);
                                                System.out.println("crud: " + crud);
                                                System.out.println("nombreTabla: " + nombreTabla);
                                                System.out.println("dni: " + dni);
                                                System.out.println("datoDni: " + datoDni);
                                                System.out.println("nom: " + nom);
                                                System.out.println("datoNom: " + datoNom);
                                                System.out.println("apellido: " + apellido);
                                                System.out.println("datoApellido: " + datoApellido);
                                                System.out.println("nomempresa: " + nomempresa);
                                                System.out.println("datoNomempresa: " + datoNomempresa);
                                                System.out.println("departament: " + departament);
                                                System.out.println("datoDepartament: " + datoDepartament);
                                                System.out.println("codicard: " + codicard);
                                                System.out.println("datoCodicar: " + datoCodicard);
                                                System.out.println("mail: " + mail);
                                                System.out.println("datoMail: " + datoMail);
                                                System.out.println("orden: " + orden);
                                                System.out.println("____________________________________________________________________");

                                                if (crud.equals("1")) {
                                                    if (nombreTabla.equals("0")) {
                                                        List<Empleados> insertEmpleadosMail = new ArrayList<Empleados>();
                                                        insertEmpleadosMail = InsertEmpleados.insertEmpleadosMail(datoDni, datoNom,
                                                                datoApellido, datoNomempresa, datoDepartament,
                                                                Integer.parseInt(datoCodicard), datoMail);
                                                        System.out.println("Empleado creado correctamente, sus datos son:\n");
                                                        System.out.println("____________________________________________________________________");
                                                        System.out.println("Dni: " + datoDni + "\n"
                                                                + "Nombre: " + datoNom + "\n"
                                                                + "Apellido: " + datoApellido + "\n"
                                                                + "Nombre empresa: " + datoNomempresa + "\n"
                                                                + "Departamento: " + datoDepartament + "\n"
                                                                + "Codigo tarjeta: " + Integer.parseInt(datoCodicard) + "\n"
                                                                + "Mail: " + datoMail + "\n");
                                                        System.out.println("____________________________________________________________________");
                                                        outObjeto = new ObjectOutputStream(client.getOutputStream());
                                                        outObjeto.writeObject(insertEmpleadosMail);
                                                        outObjeto.flush();
                                                    }
                                                }
                                            }
                                        } else if (insertEmpleadoMT[17].equals("0") && insertEmpleadoMT[15].equals("telephon")
                                                || insertEmpleadoMT[17].equals("1") && insertEmpleadoMT[15].equals("telephon")) {
                                            String codigoUserRecibido = insertEmpleadoMT[0]; //el codigo recibido tiene que ser el mismo que le hemos asignado
                                            String crud = insertEmpleadoMT[1];
                                            String nombreTabla = insertEmpleadoMT[2]; //Será el numero de tabla. (ej: 1->empleados 2->users 3-jornada 4-usertipe 5->empresa)
                                            dni = insertEmpleadoMT[3]; //sera la palabra que busquemos(ej: juan,1234567D), si ponemos 0 sera todos los de la tabla
                                            String datoDni = insertEmpleadoMT[4];// si es el caso será la columna (,dni,nom,etc), si no hay ponemos 0
                                            String nom = insertEmpleadoMT[5]; //sera la palabra que busquemos(ej: juan,1234567D), si ponemos 0 sera todos los de la tabla
                                            String datoNom = insertEmpleadoMT[6];
                                            String apellido = insertEmpleadoMT[7];// si es el caso el orden, si no hay ponemos 0
                                            String datoApellido = insertEmpleadoMT[8]; //el codigo recibido tiene que ser el mismo que le hemos asignado
                                            String nomempresa = insertEmpleadoMT[9];
                                            String datoNomempresa = insertEmpleadoMT[10]; //Será el numero de tabla. (ej: 1->empleados 2->users 3-jornada 4-usertipe 5->empresa)
                                            String departament = insertEmpleadoMT[11]; //sera la palabra que busquemos(ej: juan,1234567D), si ponemos 0 sera todos los de la tabla
                                            String datoDepartament = insertEmpleadoMT[12];// si es el caso será la columna (,dni,nom,etc), si no hay ponemos 0
                                            String codicard = insertEmpleadoMT[13]; //sera la palabra que busquemos(ej: juan,1234567D), si ponemos 0 sera todos los de la tabla
                                            String datoCodicard = insertEmpleadoMT[14];
                                            String telephon = insertEmpleadoMT[15];// si es el caso el orden, si no hay ponemos 0
                                            String datoTelephon = insertEmpleadoMT[16];
                                            String orden = insertEmpleadoMT[17];// si es el caso el orden, si no hay ponemos 0

                                            if (!codigo.equals(codigoUserRecibido)) {
                                                System.out.println("____________________________________________________________________");
                                                System.out.println(fecha.fecha_hora());
                                                System.out.println("Ciente desconectado, el codigo no es el correcto");
                                                salir = true;
                                                escriptor.close();
                                                lector.close();
                                                client.close();

                                            } else if (orden.equals("0") || orden.equals("1")) {

                                                System.out.println("____________________________________________________________________");
                                                System.out.println("codigoUserRecibido: " + codigoUserRecibido);
                                                System.out.println("crud: " + crud);
                                                System.out.println("nombreTabla: " + nombreTabla);
                                                System.out.println("dni: " + dni);
                                                System.out.println("datoDni: " + datoDni);
                                                System.out.println("nom: " + nom);
                                                System.out.println("datoNom: " + datoNom);
                                                System.out.println("apellido: " + apellido);
                                                System.out.println("datoApellido: " + datoApellido);
                                                System.out.println("nomempresa: " + nomempresa);
                                                System.out.println("datoNomempresa: " + datoNomempresa);
                                                System.out.println("departament: " + departament);
                                                System.out.println("datoDepartament: " + datoDepartament);
                                                System.out.println("codicard: " + codicard);
                                                System.out.println("datoCodicar: " + datoCodicard);
                                                System.out.println("telephon: " + telephon);
                                                System.out.println("datoTelephon: " + datoTelephon);
                                                System.out.println("orden: " + orden);
                                                System.out.println("____________________________________________________________________");

                                                if (crud.equals("1")) {
                                                    if (nombreTabla.equals("0")) {
                                                        List<Empleados> insertEmpleadosTelf = new ArrayList<Empleados>();
                                                        insertEmpleadosTelf = InsertEmpleados.insertEmpleadosTelf(datoDni, datoNom,
                                                                datoApellido, datoNomempresa, datoDepartament,
                                                                Integer.parseInt(datoCodicard), Integer.parseInt(datoTelephon));
                                                        System.out.println("Empleado creado correctamente, sus datos son:\n");
                                                        System.out.println("____________________________________________________________________");
                                                        System.out.println("Dni: " + datoDni + "\n"
                                                                + "Nombre: " + datoNom + "\n"
                                                                + "Apellido: " + datoApellido + "\n"
                                                                + "Nombre empresa: " + datoNomempresa + "\n"
                                                                + "Departamento: " + datoDepartament + "\n"
                                                                + "Codigo tarjeta: " + Integer.parseInt(datoCodicard) + "\n"
                                                                + "Telephon: " + Integer.parseInt(datoTelephon) + "\n");
                                                        System.out.println("____________________________________________________________________");
                                                        outObjeto = new ObjectOutputStream(client.getOutputStream());
                                                        outObjeto.writeObject(insertEmpleadosTelf);
                                                        outObjeto.flush();
                                                    }
                                                }
                                            }

                                        } else if (NomApellido[19].equals("0") || NomApellido[19].equals("1")) {
                                            String codigoUserRecibido = insertEmpleado[0]; //el codigo recibido tiene que ser el mismo que le hemos asignado
                                            String crud = insertEmpleado[1];
                                            String nombreTabla = insertEmpleado[2]; //Será el numero de tabla. (ej: 1->empleados 2->users 3-jornada 4-usertipe 5->empresa)
                                            dni = insertEmpleado[3]; //sera la palabra que busquemos(ej: juan,1234567D), si ponemos 0 sera todos los de la tabla
                                            String datoDni = insertEmpleado[4];// si es el caso será la columna (,dni,nom,etc), si no hay ponemos 0
                                            String nom = insertEmpleado[5]; //sera la palabra que busquemos(ej: juan,1234567D), si ponemos 0 sera todos los de la tabla
                                            String datoNom = insertEmpleado[6];
                                            String apellido = insertEmpleado[7];// si es el caso el orden, si no hay ponemos 0
                                            String datoApellido = insertEmpleado[8]; //el codigo recibido tiene que ser el mismo que le hemos asignado
                                            String nomempresa = insertEmpleado[9];
                                            String datoNomempresa = insertEmpleado[10]; //Será el numero de tabla. (ej: 1->empleados 2->users 3-jornada 4-usertipe 5->empresa)
                                            String departament = insertEmpleado[11]; //sera la palabra que busquemos(ej: juan,1234567D), si ponemos 0 sera todos los de la tabla
                                            String datoDepartament = insertEmpleado[12];// si es el caso será la columna (,dni,nom,etc), si no hay ponemos 0
                                            String codicard = insertEmpleado[13]; //sera la palabra que busquemos(ej: juan,1234567D), si ponemos 0 sera todos los de la tabla
                                            String datoCodicard = insertEmpleado[14];
                                            String mail = insertEmpleado[15];// si es el caso el orden, si no hay ponemos 0
                                            String datoMail = insertEmpleado[16];// si es el caso será la columna (,dni,nom,etc), si no hay ponemos 0
                                            String telephon = insertEmpleado[17]; //sera la palabra que busquemos(ej: juan,1234567D), si ponemos 0 sera todos los de la tabla
                                            String datoTelephon = insertEmpleado[18];
                                            String orden = insertEmpleado[19];// si es el caso el orden, si no hay ponemos 0

                                            if (!codigo.equals(codigoUserRecibido)) {
                                                System.out.println("____________________________________________________________________");
                                                System.out.println(fecha.fecha_hora());
                                                System.out.println("Ciente desconectado, el codigo no es el correcto");
                                                salir = true;
                                                escriptor.close();
                                                lector.close();
                                                client.close();

                                            } else if (orden.equals("0") || orden.equals("1")) {

                                                System.out.println("____________________________________________________________________");
                                                System.out.println("codigoUserRecibido: " + codigoUserRecibido);
                                                System.out.println("crud: " + crud);
                                                System.out.println("nombreTabla: " + nombreTabla);
                                                System.out.println("dni: " + dni);
                                                System.out.println("datoDni: " + datoDni);
                                                System.out.println("nom: " + nom);
                                                System.out.println("datoNom: " + datoNom);
                                                System.out.println("apellido: " + apellido);
                                                System.out.println("datoApellido: " + datoApellido);
                                                System.out.println("nomempresa: " + nomempresa);
                                                System.out.println("datoNomempresa: " + datoNomempresa);
                                                System.out.println("departament: " + departament);
                                                System.out.println("datoDepartament: " + datoDepartament);
                                                System.out.println("codicard: " + codicard);
                                                System.out.println("datoCodicar: " + datoCodicard);
                                                System.out.println("mail: " + mail);
                                                System.out.println("datoMail: " + datoMail);
                                                System.out.println("telephon: " + telephon);
                                                System.out.println("datoTelephon: " + datoTelephon);
                                                System.out.println("orden: " + orden);
                                                System.out.println("____________________________________________________________________");

                                                if (crud.equals("1")) {
                                                    if (nombreTabla.equals("0")) {
                                                        List<Empleados> insertEmpleados = new ArrayList<Empleados>();
                                                        insertEmpleados = InsertEmpleados.insertEmpleados(datoDni, datoNom,
                                                                datoApellido, datoNomempresa, datoDepartament,
                                                                Integer.parseInt(datoCodicard), datoMail, Integer.parseInt(datoTelephon));
                                                        System.out.println("Empleado creado correctamente, sus datos son:\n");
                                                        System.out.println("____________________________________________________________________");
                                                        System.out.println("Dni: " + datoDni + "\n"
                                                                + "Nombre: " + datoNom + "\n"
                                                                + "Apellido: " + datoApellido + "\n"
                                                                + "Nombre empresa: " + datoNomempresa + "\n"
                                                                + "Departamento: " + datoDepartament + "\n"
                                                                + "Codigo tarjeta: " + Integer.parseInt(datoCodicard) + "\n"
                                                                + "Mail: " + datoMail + "\n"
                                                                + "Teléfono: " + Integer.parseInt(datoTelephon) + "\n");
                                                        System.out.println("____________________________________________________________________");
                                                        outObjeto = new ObjectOutputStream(client.getOutputStream());
                                                        outObjeto.writeObject(insertEmpleados);
                                                        outObjeto.flush();
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                                escriptor.close();
                                lector.close();
                                client.close();
                            } catch (IOException ex) {
                                Logger.getLogger(Threadllogin.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }
                    } catch (IOException ex) {
                        Logger.getLogger(Threadllogin.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            } catch (NullPointerException e) {
                System.out.println("NullPointerException thrown!");
                logins.remove(user.getDni());
                System.out.println("Users conectados: " + logins);
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("NullPointerException thrown!");
                logins.remove(user.getDni());
                System.out.println("Users conectados: " + logins);
            } catch (IOException ex) {
                Logger.getLogger(Threadllogin.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                if (user != null) {
                    logins.remove(user.getDni()); // Eliminar al usuario de la lista en caso de excepción
                }
            }
        } catch (IOException ex) {
            Logger.getLogger(Threadllogin.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (user != null) {
                logins.remove(user.getDni()); // Eliminar al usuario de la lista en caso de excepción
            }
        }
    }

//    private class InactivityTimerTask extends TimerTask {
//
//        @Override
//        public void run() {
//            // Cerrar la conexión por inactividad
//            System.out.println("Cerrando la conexión debido a inactividad.");
//            try {
//                inactivityTimer.cancel(); // Detener el temporizador
//                inactivityTimer.purge();
//                inactivityTimer = null;
//                exit();
//                in.close();
//                out.close();
//                client.close();
//                
//            } catch (IOException ex) {
//                Logger.getLogger(Threadllogin.class.getName()).log(Level.SEVERE, null, ex);
//            }
//        }
//    }

    
    
    
    private void exit() {
        try {
            Users user = null;
            salir = true;
            if (user != null) {
                logins.remove(user.getDni());
            }
            lector.close();
            escriptor.close();
            client.close();
        } catch (IOException ex) {
            Logger.getLogger(Threadllogin.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
