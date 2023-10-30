package thread;

/**
 *
 * @author Gustavo_Senorans
 */
import search.*;
import insert.*;
import print.*;
import codigo.Codigo;
import fecha.Fechas;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.*;
import java.io.*;
import java.util.concurrent.ConcurrentHashMap;
import modelo.*;

public class Threadllogin extends Thread {

//    private static final int MAX_INACTIVITY_TIME_SECONDS = 30; // 5 minutos de inactividad
//    private Timer inactivityTimer = new Timer();
    private Socket client;
    private Scanner in;
    private PrintWriter out;
    private HashMap<String, String> logins;
    private String codigo;
    Fechas fecha = new Fechas();
    boolean salir = false;
    BufferedWriter escriptor;
    BufferedReader lector;

    private static final String ERROR_LOGIN = "-1";
    private static final String USER_ALREADY_CONNECTED = "-2";
    private static Set<String> usersConnected = ConcurrentHashMap.newKeySet();

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
        codigo = ERROR_LOGIN;
        String dni = ERROR_LOGIN;
        Users user = null;

        try {

            escriptor = new BufferedWriter(new OutputStreamWriter(client.getOutputStream()));
            lector = new BufferedReader(new InputStreamReader(client.getInputStream()));

            ObjectOutputStream outObjeto = null;
            msg = "";
            escriptor.write(msg);
            escriptor.newLine();
            escriptor.flush();

            try {
                String palabra = lector.readLine();

                if (palabra != null && palabra.equalsIgnoreCase("exit")) {
                    exit(user);
                } else {
                    System.out.println("Bienvenido al ServeHREntrada");
                    System.out.println(fecha.fecha_hora());
                    String[] datos = new String[2];
                    String login = "-1";
                    String pass = "-1";
                    datos = palabra.split(",");
                    if (datos[0] != null && datos[1] != null) {
                        login = datos[0];
                        pass = datos[1];
                    }
                    System.out.println("____________________________________________________________________");
                    System.out.println("Datos de login recibidos:\nLogin : " + login + "\nPass: " + pass);

                    peticiones.Login dvLogin = new peticiones.Login();
                    user = dvLogin.comprobarCredencialesBD(login, pass);

                    try {
                        if (user != null) {
                            if (usersConnected.contains(user.getDni())) {
                                msg = USER_ALREADY_CONNECTED;
                                System.out.println("Cliente desconectado, ya esta conectado este usuario.");
                                escriptor.write(msg);
                                escriptor.newLine();
                                escriptor.flush();
                                lector.close();
                                client.close();
                            } else {
                                codigo = Codigo.crearCodigoLogin(user.getNumtipe());
                                usersConnected.add(user.getDni()); 
                            }
                        }

                        if (!client.isClosed() && codigo.equalsIgnoreCase(ERROR_LOGIN)) {
                            msg = ERROR_LOGIN;
                            System.out.println(fecha.fecha_hora());
                            System.out.println("____________________________________________________________________");
                            System.out.println("Cliente desconectado, error en el login");
                            escriptor.write(msg);
                            escriptor.newLine();
                            escriptor.flush();
                            lector.close();
                            client.close();

                        } else if (!client.isClosed()) {

                            msg = codigo;
                            escriptor.write(msg);
                            escriptor.newLine();
                            escriptor.flush();
                            logins.put(user.getDni(), codigo);

                            try {
                                while (!salir) {

                                    palabra = lector.readLine();

                                    if (palabra.equals(null) || palabra.equalsIgnoreCase("exit")) {
                                        System.out.println(
                                                "____________________________________________________________________");
                                        System.out.println("Cliente con codigo " + codigo + " que pertenece al usuario "
                                                + login + "\nse ha desconectado correctamente.");
                                        usersConnected.remove(user.getDni());
                                        salir = true;
                                        escriptor.close();
                                        lector.close();
                                        client.close();

                                    } else {
                                        System.out.println(fecha.fecha_hora());
                                        System.out.println(
                                                "____________________________________________________________________");
                                        System.out.println("Contenido a enviar al cliente: " + palabra);

                                        PrintPorColumna verificar = new PrintPorColumna();
                                        verificar.selectColumna(palabra);

                                        String[] frase = new String[6];
                                        String[] nomApellido = new String[8];
                                        String[] insertEmpresas = new String[10];
                                        String[] insertUsuarios = new String[12];
                                        String[] insertEmpleadoMailTelf = new String[16];
                                        String[] insertEmpleadoMT = new String[18];
                                        String[] insertEmpleado = new String[20];

                                        frase = palabra.split(",");
                                        nomApellido = palabra.split(",");
                                        insertEmpresas = palabra.split(",");
                                        insertUsuarios = palabra.split(",");
                                        insertEmpleadoMailTelf = palabra.split(",");
                                        insertEmpleadoMT = palabra.split(",");
                                        insertEmpleado = palabra.split(",");

                                        if (frase[5].equals("0") || frase[5].equals("1")) {
                                            handleSearchRequest(frase, outObjeto, client);

                                        } else if (nomApellido[7].equals("0") || nomApellido[7].equals("1")) {
                                            if (nomApellido[3].equals("dni") && nomApellido[5].equals("fecha")) {
                                                handleDniFechaRequest(nomApellido, palabra, outObjeto, client);

                                            } else if (nomApellido[3].equals("nom") && nomApellido[5].equals("fecha")) {
                                                handleNomFechaRequest(nomApellido, palabra, outObjeto, client);

                                            } else if (nomApellido[3].equals("apellido")
                                                    && nomApellido[5].equals("fecha")) {
                                                handleApellidoFechaRequest(nomApellido, palabra, outObjeto, client);

                                            } else if (nomApellido[3].equals("codicard")
                                                    && nomApellido[5].equals("fecha")) {
                                                handleCodicardFechaRequest(nomApellido, palabra, outObjeto, client);

                                            } else {
                                                handleNomApellidoRequest(nomApellido, palabra, outObjeto, client);
                                            }
                                        } else if (insertEmpresas[9].equals("0") || insertEmpresas[9].equals("1")) {
                                            if (insertEmpresas[3].equals("nom") && insertEmpresas[5].equals("apellido") && insertEmpresas[7].equals("fecha")) {
                                                handleNomApellidoFechaRequest(insertEmpresas, palabra, outObjeto, client);
                                            } else  if (insertEmpresas[3].equals("nom") && insertEmpresas[5].equals("address") && insertEmpresas[7].equals("telephon")) {
                                                handleEmpresaInsert(insertEmpresas, palabra, outObjeto, client);
                                            }
                                        } else if (insertUsuarios[11].equals("0") || insertUsuarios[11].equals("1")) {
                                            handleUsersInsert(insertUsuarios, palabra, outObjeto, client);

                                        } else if (insertEmpleadoMailTelf[15].equals("0")
                                                || insertEmpleadoMailTelf[15].equals("1")) {
                                            handleEmpleadoMailTelfInsert(insertEmpleadoMailTelf, palabra, outObjeto,
                                                    client);

                                        } else if (insertEmpleadoMT[17].equals("0")
                                                && insertEmpleadoMT[15].equals("mail")
                                                || insertEmpleadoMT[17].equals("1")
                                                && insertEmpleadoMT[15].equals("mail")) {
                                            handleEmpleadoMailInsert(insertEmpleadoMT, palabra, outObjeto, client);

                                        } else if (insertEmpleadoMT[17].equals("0")
                                                && insertEmpleadoMT[15].equals("telephon")
                                                || insertEmpleadoMT[17].equals("1")
                                                && insertEmpleadoMT[15].equals("telephon")) {
                                            handleEmpleadoTelfInsert(insertEmpleadoMT, palabra, outObjeto, client);

                                        } else if (insertEmpleado[19].equals("0") || insertEmpleado[19].equals("1")) {
                                            handleEmpleadoInsert(insertEmpleado, palabra, outObjeto, client);
                                        }
                                    }
                                }
                                logins.remove(user.getDni());
                                escriptor.close();
                                lector.close();
                                client.close();
                            } catch (IOException ex) {
                                logins.remove(user.getDni());
                                Logger.getLogger(Threadllogin.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }
                    } catch (IOException ex) {
                        logins.remove(user.getDni());
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
                logins.remove(user.getDni());
                Logger.getLogger(Threadllogin.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                if (user != null) {
                    logins.remove(user.getDni());
                }
            }

        } catch (IOException ex) {
            usersConnected.remove(user.getDni());
            System.err.println("Error de comunicacion con el cliente: " + ex.getMessage());
        } finally {
            try {
                if (user != null) {
                    logins.remove(user.getDni());
                }
                if (lector != null) {
                    lector.close();
                }
                if (escriptor != null) {
                    escriptor.close();
                }
                if (client != null) {
                    client.close();
                }
            } catch (IOException e) {
                System.out.println("Error al cerrar recursos de comunicación: " + e.getMessage());
            }
        }
    }

    private void handleEmpleadoInsert(String[] insertEmpleado, String palabra, ObjectOutputStream outObjeto,
            Socket client) throws IOException {
        String codigoUserRecibido = insertEmpleado[0];
        String crud = insertEmpleado[1];
        String nombreTabla = insertEmpleado[2];
        String dni = insertEmpleado[3];
        String datoDni = insertEmpleado[4];
        String nom = insertEmpleado[5];
        String datoNom = insertEmpleado[6];
        String apellido = insertEmpleado[7];
        String datoApellido = insertEmpleado[8];
        String nomempresa = insertEmpleado[9];
        String datoNomempresa = insertEmpleado[10];
        String departament = insertEmpleado[11];
        String datoDepartament = insertEmpleado[12];
        String codicard = insertEmpleado[13];
        String datoCodicard = insertEmpleado[14];
        String mail = insertEmpleado[15];
        String datoMail = insertEmpleado[16];
        String telephon = insertEmpleado[17];
        String datoTelephon = insertEmpleado[18];
        String orden = insertEmpleado[19];

        if (!codigo.equals(codigoUserRecibido)) {
            verificarCodigoCliente(codigoUserRecibido);

        } else if (orden.equals("0") || orden.equals("1")) {
            System.out.println(fecha.fecha_hora());
            if (crud.equals("1")) {
                if (nombreTabla.equals("0")) {
                    InsertCrudEmpleados.handleInsertRequest(crud, nombreTabla, dni, datoDni, nom, datoNom, apellido,
                            datoApellido, nomempresa, datoNomempresa, departament, datoDepartament, codicard,
                            datoCodicard, mail, datoMail, telephon, datoTelephon, palabra, outObjeto, client);
                }
            }
        }
    }

    private void handleEmpleadoTelfInsert(String[] insertEmpleadoMT, String palabra, ObjectOutputStream outObjeto,
            Socket client) throws IOException {
        String codigoUserRecibido = insertEmpleadoMT[0];
        String crud = insertEmpleadoMT[1];
        String nombreTabla = insertEmpleadoMT[2];
        String dni = insertEmpleadoMT[3];
        String datoDni = insertEmpleadoMT[4];
        String nom = insertEmpleadoMT[5];
        String datoNom = insertEmpleadoMT[6];
        String apellido = insertEmpleadoMT[7];
        String datoApellido = insertEmpleadoMT[8];
        String nomempresa = insertEmpleadoMT[9];
        String datoNomempresa = insertEmpleadoMT[10];
        String departament = insertEmpleadoMT[11];
        String datoDepartament = insertEmpleadoMT[12];
        String codicard = insertEmpleadoMT[13];
        String datoCodicard = insertEmpleadoMT[14];
        String telephon = insertEmpleadoMT[15];
        String datoTelephon = insertEmpleadoMT[16];
        String orden = insertEmpleadoMT[17];

        if (!codigo.equals(codigoUserRecibido)) {
            verificarCodigoCliente(codigoUserRecibido);

        } else if (orden.equals("0") || orden.equals("1")) {
            System.out.println(fecha.fecha_hora());
            if (crud.equals("1")) {
                if (nombreTabla.equals("0")) {
                    InsertCrudEmpleadosTelf.handleInsertRequest(crud, nombreTabla, dni, datoDni, nom, datoNom, apellido,
                            datoApellido, nomempresa, datoNomempresa, departament, datoDepartament, codicard,
                            datoCodicard, telephon, datoTelephon, palabra, outObjeto, client);
                }
            }
        }
    }

    private void handleEmpleadoMailInsert(String[] insertEmpleadoMT, String palabra, ObjectOutputStream outObjeto,
            Socket client) throws IOException {
        String codigoUserRecibido = insertEmpleadoMT[0];
        String crud = insertEmpleadoMT[1];
        String nombreTabla = insertEmpleadoMT[2];
        String dni = insertEmpleadoMT[3];
        String datoDni = insertEmpleadoMT[4];
        String nom = insertEmpleadoMT[5];
        String datoNom = insertEmpleadoMT[6];
        String apellido = insertEmpleadoMT[7];
        String datoApellido = insertEmpleadoMT[8];
        String nomempresa = insertEmpleadoMT[9];
        String datoNomempresa = insertEmpleadoMT[10];
        String departament = insertEmpleadoMT[11];
        String datoDepartament = insertEmpleadoMT[12];
        String codicard = insertEmpleadoMT[13];
        String datoCodicard = insertEmpleadoMT[14];
        String mail = insertEmpleadoMT[15];
        String datoMail = insertEmpleadoMT[16];
        String orden = insertEmpleadoMT[17];

        if (!codigo.equals(codigoUserRecibido)) {
            verificarCodigoCliente(codigoUserRecibido);
        } else if (orden.equals("0") || orden.equals("1")) {
            System.out.println(fecha.fecha_hora());
            if (crud.equals("1")) {
                if (nombreTabla.equals("0")) {
                    InsertCrudEmpleadosMail.handleInsertRequest(crud, nombreTabla, dni, datoDni, nom, datoNom, apellido,
                            datoApellido, nomempresa, datoNomempresa, departament, datoDepartament, codicard,
                            datoCodicard, mail, datoMail, palabra, outObjeto, client);
                }
            }
        }
    }

    private void handleEmpleadoMailTelfInsert(String[] insertEmpleadoMailTelf, String palabra,
            ObjectOutputStream outObjeto, Socket client) throws IOException {
        String codigoUserRecibido = insertEmpleadoMailTelf[0];
        String crud = insertEmpleadoMailTelf[1];
        String nombreTabla = insertEmpleadoMailTelf[2];
        String dni = insertEmpleadoMailTelf[3];
        String datoDni = insertEmpleadoMailTelf[4];
        String nom = insertEmpleadoMailTelf[5];
        String datoNom = insertEmpleadoMailTelf[6];
        String apellido = insertEmpleadoMailTelf[7];
        String datoApellido = insertEmpleadoMailTelf[8];
        String nomempresa = insertEmpleadoMailTelf[9];
        String datoNomempresa = insertEmpleadoMailTelf[10];
        String departament = insertEmpleadoMailTelf[11];
        String datoDepartament = insertEmpleadoMailTelf[12];
        String codicard = insertEmpleadoMailTelf[13];
        String datoCodicard = insertEmpleadoMailTelf[14];
        String orden = insertEmpleadoMailTelf[15];

        if (!codigo.equals(codigoUserRecibido)) {
            verificarCodigoCliente(codigoUserRecibido);

        } else if (orden.equals("0") || orden.equals("1")) {
            System.out.println(fecha.fecha_hora());
            if (crud.equals("1")) {
                if (nombreTabla.equals("0")) {
                    InsertCrudEmpleadosMailTelf.handleInsertRequest(crud, nombreTabla, dni, datoDni, nom, datoNom,
                            apellido, datoApellido, nomempresa, datoNomempresa, departament, datoDepartament, codicard,
                            datoCodicard, palabra, outObjeto, client);
                }
            }
        }
    }

    private void handleUsersInsert(String[] insertUsuarios, String palabra, ObjectOutputStream outObjeto, Socket client)
            throws IOException {
        String codigoUserRecibido = insertUsuarios[0];
        String crud = insertUsuarios[1];
        String nombreTabla = insertUsuarios[2];
        String login = insertUsuarios[3];
        String datoLogin = insertUsuarios[4];
        String pass = insertUsuarios[5];
        String datoPass = insertUsuarios[6];
        String numTipe = insertUsuarios[7];
        String datoNumTipe = insertUsuarios[8];
        String dni = insertUsuarios[9];
        String datoDni = insertUsuarios[10];
        String orden = insertUsuarios[11];

        if (!codigo.equals(codigoUserRecibido)) {
            verificarCodigoCliente(codigoUserRecibido);

        } else if (orden.equals("0") || orden.equals("1")) {
            System.out.println("\n");
            if (crud.equals("1")) {
                if (nombreTabla.equals("1")) {
                    InsertCrudUsers.handleInsertRequest(crud, nombreTabla, login, datoLogin, pass, datoPass, numTipe,
                            datoNumTipe, dni, datoDni, palabra, outObjeto, client);
                }
            }
        }
    }

    private void handleEmpresaInsert(String[] insertEmpresas, String palabra, ObjectOutputStream outObjeto,
            Socket client) throws IOException {
        String codigoUserRecibido = insertEmpresas[0];
        String crud = insertEmpresas[1];
        String nombreTabla = insertEmpresas[2];
        String nom = insertEmpresas[3];
        String datoNom = insertEmpresas[4];
        String address = insertEmpresas[5];
        String datoAddress = insertEmpresas[6];
        String telephon = insertEmpresas[7];
        String datoTelephon = insertEmpresas[8];
        String orden = insertEmpresas[9];

        if (!codigo.equals(codigoUserRecibido)) {
            verificarCodigoCliente(codigoUserRecibido);

        } else if (orden.equals("0") || orden.equals("1")) {
            if (crud.equals("1")) {
                if (nombreTabla.equals("2")) {
                    InsertCrudEmpresa.handleInsertRequest(crud, nombreTabla, nom, datoNom, address, datoAddress,
                            telephon, datoTelephon, palabra, outObjeto, client);
                }
            }
        }
    }

    private void handleNomApellidoRequest(String[] NomApellido, String palabra, ObjectOutputStream outObjeto,
            Socket client) throws IOException {
        String codigoUserRecibido = NomApellido[0];
        String crud = NomApellido[1];
        String nombreTabla = NomApellido[2];
        String nom = NomApellido[3];
        String datoNom = NomApellido[4];
        String apellido = NomApellido[5];
        String datoApellido = NomApellido[6];
        String orden = NomApellido[7];
        if (!codigo.equals(codigoUserRecibido)) {
            verificarCodigoCliente(codigoUserRecibido);
        } else if (orden.equals("0") || orden.equals("1")) {
            System.out.println(fecha.fecha_hora());

            if (nombreTabla.equals("0") && nom.equals("nom") && apellido.equals("apellido")) {
                SearchCrudNonApellidoEmpleados.handleSearchRequest(crud, nombreTabla, nom, datoNom, apellido,
                        datoApellido, palabra, outObjeto, client);
            } else if (nombreTabla.equals("3") && nom.equals("nom") && apellido.equals("apellido")) {
                SearchCrudNomApellidoJornada.handleSearchRequest(crud, nombreTabla, nom, datoNom, apellido,
                        datoApellido, palabra, outObjeto, client);
            }
        }
    }

    private void handleNomFechaRequest(String[] NomApellido, String palabra, ObjectOutputStream outObjeto,
            Socket client) throws IOException {
        String codigoUserRecibido = NomApellido[0];
        String crud = NomApellido[1];
        String nombreTabla = NomApellido[2];
        String nom = NomApellido[3];
        String datoNom = NomApellido[4];
        String fechas = NomApellido[5];
        String datoFecha = NomApellido[6];
        String orden = NomApellido[7];
        if (!codigo.equals(codigoUserRecibido)) {
            verificarCodigoCliente(codigoUserRecibido);
        } else if (orden.equals("0") || orden.equals("1")) {
            System.out.println(fecha.fecha_hora());

            if (nombreTabla.equals("3") && nom.equals("nom") && fechas.equals("fecha")) {
                SearchCrudNomFechaJornada.handleSearchRequest(crud, nombreTabla, nom, datoNom, fechas, datoFecha,
                        palabra, outObjeto, client);
            }
        }
    }

    private void handleApellidoFechaRequest(String[] NomApellido, String palabra, ObjectOutputStream outObjeto,
            Socket client) throws IOException {
        String codigoUserRecibido = NomApellido[0];
        String crud = NomApellido[1];
        String nombreTabla = NomApellido[2];
        String apellido = NomApellido[3];
        String datoApellido = NomApellido[4];
        String fechas = NomApellido[5];
        String datoFecha = NomApellido[6];
        String orden = NomApellido[7];
        if (!codigo.equals(codigoUserRecibido)) {
            verificarCodigoCliente(codigoUserRecibido);
        } else if (orden.equals("0") || orden.equals("1")) {
            System.out.println(fecha.fecha_hora());

            if (nombreTabla.equals("3") && apellido.equals("apellido") && fechas.equals("fecha")) {
                SearchCrudApellidoFechaJornada.handleSearchRequest(crud, nombreTabla, apellido, datoApellido, fechas,
                        datoFecha, palabra, outObjeto, client);
            }
        }
    }

    private void handleCodicardFechaRequest(String[] NomApellido, String palabra, ObjectOutputStream outObjeto,
            Socket client) throws IOException {
        String codigoUserRecibido = NomApellido[0];
        String crud = NomApellido[1];
        String nombreTabla = NomApellido[2];
        String codicard = NomApellido[3];
        String datoCodicard = NomApellido[4];
        String fechas = NomApellido[5];
        String datoFecha = NomApellido[6];
        String orden = NomApellido[7];
        if (!codigo.equals(codigoUserRecibido)) {
            verificarCodigoCliente(codigoUserRecibido);
        } else if (orden.equals("0") || orden.equals("1")) {
            System.out.println(fecha.fecha_hora());

            if (nombreTabla.equals("3") && codicard.equals("codicard") && fechas.equals("fecha")) {
                SearchCrudCodicardFechaJornada.handleSearchRequest(crud, nombreTabla, codicard, Integer.parseInt(datoCodicard), fechas,
                        datoFecha, palabra, outObjeto, client);
            }
        }
    }

    private void handleDniFechaRequest(String[] NomApellido, String palabra, ObjectOutputStream outObjeto,
            Socket client) throws IOException {
        String codigoUserRecibido = NomApellido[0];
        String crud = NomApellido[1];
        String nombreTabla = NomApellido[2];
        String dni = NomApellido[3];
        String datoDni = NomApellido[4];
        String fechas = NomApellido[5];
        String datoFecha = NomApellido[6];
        String orden = NomApellido[7];
        if (!codigo.equals(codigoUserRecibido)) {
            verificarCodigoCliente(codigoUserRecibido);
        } else if (orden.equals("0") || orden.equals("1")) {
            System.out.println(fecha.fecha_hora());

            if (nombreTabla.equals("3") && dni.equals("dni") && fechas.equals("fecha")) {
                SearchCrudDniFechaJornada.handleSearchRequest(crud, nombreTabla, dni, datoDni, fechas, datoFecha,
                        palabra, outObjeto, client);
            }
        }
    }

    private void handleNomApellidoFechaRequest(String[] insertEmpresas, String palabra, ObjectOutputStream outObjeto,
            Socket client) throws IOException {
        String codigoUserRecibido = insertEmpresas[0];
        String crud = insertEmpresas[1];
        String nombreTabla = insertEmpresas[2];
        String nom = insertEmpresas[3];
        String datoNom = insertEmpresas[4];
        String apellido = insertEmpresas[5];
        String datoApellido = insertEmpresas[6];
        String fechas = insertEmpresas[7];
        String datoFecha = insertEmpresas[8];
        String orden = insertEmpresas[9];
        if (!codigo.equals(codigoUserRecibido)) {
            verificarCodigoCliente(codigoUserRecibido);
        } else if (orden.equals("0") || orden.equals("1")) {
            System.out.println(fecha.fecha_hora());

            if (nombreTabla.equals("3") && nom.equals("nom") && apellido.equals("apellido") && fechas.equals("fecha")) {
                SearchCrudNomApellidoFechaJornada.handleSearchRequest(crud, nombreTabla, nom, datoNom, apellido,
                        datoApellido, fechas, datoFecha, palabra, outObjeto, client);
            }
        }
    }

    private void handleSearchRequest(String[] data, ObjectOutputStream outObjeto, Socket client) throws IOException {
        String codigoUserRecibido = data[0];
        String crud = data[1];
        String nombreTabla = data[2];
        String columna = data[3];
        String palabraAbuscar = data[4];
        String orden = data[5];

        if (!codigo.equals(codigoUserRecibido)) {
            try {
                verificarCodigoCliente(codigoUserRecibido);
            } catch (IOException ex) {
                Logger.getLogger(Threadllogin.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (orden.equals("0") || orden.equals("1")) {
            if (crud.equals("0")) {
                if ("0".equals(nombreTabla) && ("dni".equals(columna) || "nom".equals(columna)
                        || "apellido".equals(columna) || "nomempresa".equals(columna) || "departament".equals(columna)
                        || "codicard".equals(columna) || "mail".equals(columna) || "telephon".equals(columna))) {
                    SearchCrudEmpleados.handleSearchRequest(crud, nombreTabla, columna, palabraAbuscar, outObjeto,
                            client);

                } else if ("1".equals(nombreTabla)
                        && ("dni".equals(columna) || "login".equals(columna) || "numtipe".equals(columna))) {
                    SearchCrudUsers.handleSearchRequest(crud, nombreTabla, columna, palabraAbuscar, outObjeto, client);

                } else if ("2".equals(nombreTabla)
                        && ("nom".equals(columna) || "address".equals(columna))) {
                    SearchCrudEmpresa.handleSearchRequest(crud, nombreTabla, columna, palabraAbuscar, outObjeto,
                            client);

                } else if ("3".equals(nombreTabla) && ("dni".equals(columna) || "nom".equals(columna)
                        || "apellido".equals(columna) || "codicard".equals(columna) || "fecha".equals(columna))) {
                    SearchCrudJornada.handleSearchRequest(crud, nombreTabla, columna, palabraAbuscar, outObjeto,
                            client);

                } else if (!"null".equals(nombreTabla) && "0".equals(columna)) {
                    SearchCrudTablasCompleta.handleSearchRequest(crud, nombreTabla, columna, palabraAbuscar, outObjeto,
                            client);
                }
            }
        }
    }

    private void verificarCodigoCliente(String codigoUserRecibido) throws IOException {
        if (!codigo.equals(codigoUserRecibido)) {
            System.out.println("____________________________________________________________________");
            System.out.println(fecha.fecha_hora());
            System.out.println("Cliente desconectado, el codigo no es el correcto");
            salir = true;
            escriptor.close();
            lector.close();
            client.close();
        }
    }

    private void exit(Users user) {
        try {
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
