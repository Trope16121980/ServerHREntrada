package controladores;

import search.*;
import update.*;
import insert.*;
import print.*;
import controladores.Codigo;
import fecha.Fechas;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.*;
import java.io.*;
import java.util.concurrent.ConcurrentHashMap;
import modelo.*;
import peticiones.*;
import update.UpdateCrudEmpresa;

/**
 * @author Gustavo Senoráns Varela
 * @version 1.4, 15/11/2023
 * @since jdk 17
 */
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

    /**
     * Este método guarda los datos del socket del cliente, el hashmap para el
     * codigo del usuario conectado
     *
     * @param client el socket del cliente
     * @param logins los datos del usuario conectado
     */
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

    /**
     * Metodo run que genera la conexión con el cliente para gestionar el envio
     * y recepción de datos
     */
    @Override
    public void run() {
        String msg;
        codigo = ERROR_LOGIN;
        String dni = ERROR_LOGIN;
        Users user = null;

        try {
            /**
             * Creamos las distinsta variables para la lectura de datos del
             * cliente y el envio de los datos
             */
            escriptor = new BufferedWriter(new OutputStreamWriter(client.getOutputStream()));
            lector = new BufferedReader(new InputStreamReader(client.getInputStream()));

            /**
             * Creamos el objeto que contendra la lista de las clases
             */
            ObjectOutputStream outObjeto = null;
            msg = "";
            escriptor.write(msg);
            escriptor.newLine();
            escriptor.flush();

            try {
                String palabra = lector.readLine();

                /**
                 * Verificamos la entrada de datos del cliente si es exit
                 * cerramos conexión
                 */
                if (palabra != null && palabra.equalsIgnoreCase("exit")) {
                    exit(user);
                } else {
                    /**
                     * Generamos la conexión con el cliente y le damos la
                     * bienvenida
                     */
                    System.out.println("\nBienvenido al ServeHREntrada");
                    System.out.println(fecha.fecha_hora());
                    String[] datos = new String[2];
                    String login = "-1";
                    String pass = "-1";
                    datos = palabra.split(",");
                    if (datos[0] != null && datos[1] != null) {
                        login = datos[0];
                        pass = datos[1];
                    }
                    /**
                     * Imprimimos los datos del cliente conectado
                     */
                    System.out.println("\nDatos de login recibidos:\nLogin : " + login + "\nPass: " + pass);

                    peticiones.Login dvLogin = new peticiones.Login();
                    user = dvLogin.comprobarCredencialesBD(login, pass);

                    try {
                        /**
                         * Se verifica si el usuario esta conectado
                         */
                        if (user != null) {
                            if (usersConnected.contains(user.getDni())) {
                                msg = USER_ALREADY_CONNECTED;
                                System.out.println("\nCliente desconectado, ya esta conectado este usuario.");
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

                        /**
                         * Se verifica si el socket del cliente esta cerrado o
                         * no y si el codigo de usuario es correcto
                         */
                        if (!client.isClosed() && codigo.equalsIgnoreCase(ERROR_LOGIN)) {
                            msg = ERROR_LOGIN;
                            System.out.println(fecha.fecha_hora());
                            System.out.println("\nCliente desconectado, error en el login");
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

                                    /**
                                     * Si el codigo es correcto y hay conexión
                                     * con el servidor se le envia el codigo al
                                     * cliente
                                     */
                                    if (palabra.equals(null) || palabra.equalsIgnoreCase("exit")) {
                                        System.out.println("\nCliente con codigo " + codigo + " que pertenece al usuario "
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

                                        PrintPorColumna verificar = new PrintPorColumna();
                                        verificar.selectColumna(palabra);

                                        /**
                                         * Dependiendo de los datos recibido
                                         * desde el cliente los introducimos en
                                         * un array o en otro
                                         */
                                        String[] frase = new String[6];
                                        String[] nomApellido = new String[8];
                                        String[] insertEmpresas = new String[10];
                                        String[] insertUsuarios = new String[12];
                                        String[] insertEmpleado = new String[20];
                                        String[] updateEmpleado = new String[22];

                                        frase = palabra.split(",");
                                        nomApellido = palabra.split(",");
                                        insertEmpresas = palabra.split(",");
                                        insertUsuarios = palabra.split(",");
                                        insertEmpleado = palabra.split(",");
                                        updateEmpleado = palabra.split(",");

                                        if (frase[1].equals("1") && frase[5].equals("0") && frase[3].equals("dni")
                                                || frase[5].equals("1") && frase[3].equals("dni")) {

                                            handleDniJornadaInsert(frase, palabra, outObjeto, client);

                                        } else if (frase[1].equals("1") && frase[5].equals("0") && frase[3].equals("codicard")
                                                || frase[5].equals("1") && frase[3].equals("codicard")) {

                                            handleDniJornadaCodicardInsert(frase, palabra, outObjeto, client);

                                        } else if (frase[5].equals("0") || frase[5].equals("1")) {

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
                                            if (insertEmpresas[3].equals("nom") && insertEmpresas[5].equals("apellido")
                                                    && insertEmpresas[7].equals("fecha")) {
                                                handleNomApellidoFechaRequest(insertEmpresas, palabra, outObjeto,
                                                        client);
                                            } else if (insertEmpresas[3].equals("nom")
                                                    && insertEmpresas[5].equals("address")
                                                    && insertEmpresas[7].equals("telephon")) {
                                                handleEmpresaInsert(insertEmpresas, palabra, outObjeto, client);
                                            } else if (insertEmpresas[1].equals("2") && insertEmpresas[2].equals("1")) {
                                                handleUpdateUsersRequest(insertEmpresas, palabra, outObjeto, client);
                                            }

                                        } else if (insertUsuarios[1].equals("1") && insertUsuarios[9].equals("dni") && insertUsuarios[11].equals("0")
                                                || insertUsuarios[1].equals("1") && insertUsuarios[9].equals("dni") && insertUsuarios[11].equals("1")) {
                                            handleUsersInsert(insertUsuarios, palabra, outObjeto, client);
                                            
                                        } else if (insertUsuarios[1].equals("2") && insertUsuarios[2].equals("2") && insertUsuarios[11].equals("0")
                                                || insertUsuarios[1].equals("2") && insertUsuarios[1].equals("2") && insertUsuarios[11].equals("1")) {
                                            handleUpdateEmpresaRequest(insertUsuarios, palabra, outObjeto, client);
                                        } else if (insertEmpleado[19].equals("0")
                                                && insertEmpleado[9].equals("nomempresa")
                                                || insertEmpleado[19].equals("1")
                                                && insertEmpleado[9].equals("nomempresa")) {
                                            handleEmpleadoInsert(insertEmpleado, palabra, outObjeto, client);

                                        } else if (updateEmpleado[1].equals("2") && updateEmpleado[2].equals("0") && updateEmpleado[19].equals("dni") && updateEmpleado[21].equals("0")
                                                || updateEmpleado[1].equals("2") && updateEmpleado[2].equals("0") && updateEmpleado[19].equals("dni") && updateEmpleado[21].equals("1")) {
                                            handleUpdateEmpleadosRequest(updateEmpleado, palabra, outObjeto, client);
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
                usersConnected.remove(user.getDni());
                System.out.println("Users conectados: " + logins);
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("NullPointerException thrown!");
                logins.remove(user.getDni());
                usersConnected.remove(user.getDni());
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

    /**
     * Este método envia los datos recibido del cliente a verificar
     *
     * @param updateUsers array que contiene los datos
     * @param palabra datos recibidos del cliente
     * @param outObjeto objeto que contiene la lista para el envio
     * @param client socket del cliente
     * @throws IOException controla los errores
     */
    private void handleUpdateUsersRequest(String[] updateUsers, String palabra, ObjectOutputStream outObjeto,
            Socket client) throws IOException {
        String codigoUserRecibido = updateUsers[0];
        String crud = updateUsers[1];
        String nombreTabla = updateUsers[2];
        String passNuevo = updateUsers[3];
        String datoPassNuevo = updateUsers[4];
        String numtipeNuevo = updateUsers[5];
        String datoNumtipeNuevo = updateUsers[6];
        String login = updateUsers[7];
        String datoLogin = updateUsers[8];
        String orden = updateUsers[9];
        if (!codigo.equals(codigoUserRecibido)) {
            verificarCodigoCliente(codigoUserRecibido);

        } else if (orden.equals("0") || orden.equals("1")) {
            System.out.println(fecha.fecha_hora());
            UpdateCrudUsers.handleSearchRequest(crud, nombreTabla, passNuevo, datoPassNuevo, numtipeNuevo, Integer.parseInt(datoNumtipeNuevo),
                    login, datoLogin, palabra, palabra, outObjeto, client);
        }
    }

    /**
     * Este método envia los datos recibido del cliente a verificar
     *
     * @param insertJornada array que contiene los datos
     * @param palabra datos recibidos del cliente
     * @param outObjeto objeto que contiene la lista para el envio
     * @param client socket del cliente
     * @throws IOException controla los errores
     */
    private void handleDniJornadaInsert(String[] insertJornada, String palabra, ObjectOutputStream outObjeto,
            Socket client) throws IOException {
        String codigoUserRecibido = insertJornada[0];
        String crud = insertJornada[1];
        String nombreTabla = insertJornada[2];
        String dni = insertJornada[3];
        String datoDni = insertJornada[4];
        String orden = insertJornada[5];

        if (!codigo.equals(codigoUserRecibido)) {
            verificarCodigoCliente(codigoUserRecibido);

        } else if (orden.equals("0") || orden.equals("1")) {
            System.out.println(fecha.fecha_hora());
            if (crud.equals("1")) {
                if (nombreTabla.equals("3")) {
                    InsertCrudJornada.handleInsertRequest(crud, nombreTabla, dni, datoDni, palabra, outObjeto, client);
                }
            }
        }
    }

    /**
     * Este método envia los datos recibido del cliente a verificar
     *
     * @param insertJornadaCodicard array que contiene los datos
     * @param palabra datos recibidos del cliente
     * @param outObjeto objeto que contiene la lista para el envio
     * @param client socket del cliente
     * @throws IOException controla los errores
     */
    private void handleDniJornadaCodicardInsert(String[] insertJornadaCodicard, String palabra,
            ObjectOutputStream outObjeto, Socket client) throws IOException {
        String codigoUserRecibido = insertJornadaCodicard[0];
        String crud = insertJornadaCodicard[1];
        String nombreTabla = insertJornadaCodicard[2];
        String codicard = insertJornadaCodicard[3];
        String datoCodicard = insertJornadaCodicard[4];
        String orden = insertJornadaCodicard[5];

        if (!codigo.equals(codigoUserRecibido)) {
            verificarCodigoCliente(codigoUserRecibido);

        } else if (orden.equals("0") || orden.equals("1")) {
            System.out.println(fecha.fecha_hora());
            if (crud.equals("1")) {
                if (nombreTabla.equals("3")) {
                    InsertCrudJornada.handleInsertCodicardRequest(crud, nombreTabla, codicard,
                            datoCodicard, palabra, outObjeto, client);
                }
            }
        }
    }

    /**
     * Este método envia los datos recibido del cliente a verificar
     *
     * @param insertEmpleado array que contiene los datos
     * @param palabra datos recibidos del cliente
     * @param outObjeto objeto que contiene la lista para el envio
     * @param client socket del cliente
     * @throws IOException controla los errores
     */
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
                            datoCodicard, mail, datoMail, telephon, datoTelephon, palabra, outObjeto,
                            client);
                }
            }
        }
    }

    /**
     * Este método envia los datos recibido del cliente a verificar
     *
     * @param insertUsuarios array que contiene los datos
     * @param palabra datos recibidos del cliente
     * @param outObjeto objeto que contiene la lista para el envio
     * @param client socket del cliente
     * @throws IOException controla los errores
     */
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

    /**
     * Este método envia los datos recibido del cliente a verificar
     *
     * @param insertEmpresas array que contiene los datos
     * @param palabra datos recibidos del cliente
     * @param outObjeto objeto que contiene la lista para el envio
     * @param client socket del cliente
     * @throws IOException controla los errores
     */
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

    /**
     * Este método envia los datos recibido del cliente a verificar
     *
     * @param NomApellido array que contiene los datos
     * @param palabra datos recibidos del cliente
     * @param outObjeto objeto que contiene la lista para el envio
     * @param client socket del cliente
     * @throws IOException controla los errores
     */
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

    /**
     * Este método envia los datos recibido del cliente a verificar
     *
     * @param NomApellido array que contiene los datos
     * @param palabra datos recibidos del cliente
     * @param outObjeto objeto que contiene la lista para el envio
     * @param client socket del cliente
     * @throws IOException controla los errores
     */
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

    /**
     * Este método envia los datos recibido del cliente a verificar
     *
     * @param insertUsuarios array que contiene los datos
     * @param palabra datos recibidos del cliente
     * @param outObjeto objeto que contiene la lista para el envio
     * @param client socket del cliente
     * @throws IOException controla los errores
     */
    private void handleUpdateEmpresaRequest(String[] insertUsuarios, String palabra, ObjectOutputStream outObjeto,
            Socket client) throws IOException {
        String codigoUserRecibido = insertUsuarios[0];
        String crud = insertUsuarios[1];
        String nombreTabla = insertUsuarios[2];
        String nomNuevo = insertUsuarios[3];
        String datoNomnuevo = insertUsuarios[4];
        String addressNuevo = insertUsuarios[5];
        String datoAddressNuevo = insertUsuarios[6];
        String telephonNuevo = insertUsuarios[7];
        String datoTelephonNuevo = insertUsuarios[8];
        String nom = insertUsuarios[9];
        String datoNom = insertUsuarios[10];
        String orden = insertUsuarios[11];
        if (!codigo.equals(codigoUserRecibido)) {
            verificarCodigoCliente(codigoUserRecibido);

        } else if (orden.equals("0") || orden.equals("1")) {
            System.out.println(fecha.fecha_hora());
            UpdateCrudEmpresa.handleSearchRequest(crud, nombreTabla,
                    nomNuevo, datoNomnuevo,
                    addressNuevo, datoAddressNuevo,
                    telephonNuevo, datoTelephonNuevo,
                    nom, datoNom,
                    palabra, palabra, outObjeto, client);
        }
    }

    /**
     * Este método envia los datos recibido del cliente a verificar
     *
     * @param updateEmpleado array que contiene los datos
     * @param palabra datos recibidos del cliente
     * @param outObjeto objeto que contiene la lista para el envio
     * @param client socket del cliente
     * @throws IOException controla los errores
     */
    private void handleUpdateEmpleadosRequest(String[] updateEmpleado, String palabra, ObjectOutputStream outObjeto,
            Socket client) throws IOException {
        String codigoUserRecibido = updateEmpleado[0];
        String crud = updateEmpleado[1];
        String nombreTabla = updateEmpleado[2];
        String dniNuevo = updateEmpleado[3];
        String datoDniNuevo = updateEmpleado[4];
        String nomNuevo = updateEmpleado[5];
        String datoNomNuevo = updateEmpleado[6];
        String apellidoNuevo = updateEmpleado[7];
        String datoApellidoNuevo = updateEmpleado[8];
        String nomempresaNuevo = updateEmpleado[9];
        String datoNomempresaNuevo = updateEmpleado[10];
        String departamentNuevo = updateEmpleado[11];
        String datoDepartamentNuevo = updateEmpleado[12];
        String codicardNuevo = updateEmpleado[13];
        String datoCodicardNuevo = updateEmpleado[14];
        String mailNuevo = updateEmpleado[15];
        String datoMailNuevo = updateEmpleado[16];
        String telephonNuevo = updateEmpleado[17];
        String datoTelephonNuevo = updateEmpleado[18];
        String dni = updateEmpleado[19];
        String datoDni = updateEmpleado[20];
        String orden = updateEmpleado[21];
        if (!codigo.equals(codigoUserRecibido)) {
            verificarCodigoCliente(codigoUserRecibido);

        } else if (orden.equals("0") || orden.equals("1")) {
            System.out.println(fecha.fecha_hora());
            UpdateCrudEmpleados.handleSearchRequest(crud, nombreTabla, dniNuevo, datoDniNuevo,
                    nomNuevo, datoNomNuevo, apellidoNuevo, datoApellidoNuevo,
                    nomempresaNuevo, datoNomempresaNuevo,
                    departamentNuevo, datoDepartamentNuevo,
                    codicardNuevo, datoCodicardNuevo,
                    mailNuevo, datoMailNuevo,
                    telephonNuevo,datoTelephonNuevo,
                    dni, datoDni,
                    palabra, palabra, outObjeto, client);
        }
    }

    /**
     * Este método envia los datos recibido del cliente a verificar
     *
     * @param NomApellido array que contiene los datos
     * @param palabra datos recibidos del cliente
     * @param outObjeto objeto que contiene la lista para el envio
     * @param client socket del cliente
     * @throws IOException controla los errores
     */
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

    /**
     * Este método envia los datos recibido del cliente a verificar
     *
     * @param NomApellido array que contiene los datos
     * @param palabra datos recibidos del cliente
     * @param outObjeto objeto que contiene la lista para el envio
     * @param client socket del cliente
     * @throws IOException controla los errores
     */
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
                SearchCrudCodicardFechaJornada.handleSearchRequest(crud, nombreTabla, codicard,
                        Integer.parseInt(datoCodicard), fechas, datoFecha, palabra, outObjeto, client);
            }
        }
    }

    /**
     * Este método envia los datos recibido del cliente a verificar
     *
     * @param NomApellido array que contiene los datos
     * @param palabra datos recibidos del cliente
     * @param outObjeto objeto que contiene la lista para el envio
     * @param client socket del cliente
     * @throws IOException controla los errores
     */
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

    /**
     * Este método envia los datos recibido del cliente a verificar
     *
     * @param insertEmpresas array que contiene los datos
     * @param palabra datos recibidos del cliente
     * @param outObjeto objeto que contiene la lista para el envio
     * @param client socket del cliente
     * @throws IOException controla los errores
     */
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

    /**
     * Este método envia los datos recibido del cliente a verificar
     *
     * @param insertEmpresas array que contiene los datos
     * @param outObjeto objeto que contiene la lista para el envio
     * @param client socket del cliente
     * @throws IOException controla los errores
     */
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

                } else if ("2".equals(nombreTabla) && ("nom".equals(columna) || "address".equals(columna))) {
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
            } else if (crud.equals("1")) {
                if ("3".equals(nombreTabla) && "dni".equals(columna)) {
                    InsertCrudJornada.handleInsertRequest(crud, nombreTabla, columna, orden, palabraAbuscar, outObjeto,
                            client);
                }
            }
        }
    }

    /**
     * Verifica el codigo del cliente para realizar su desconexión
     *
     * @param codigoUserRecibido codigo de usuario recibido desde el cliente
     * @throws IOException
     */
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

    /**
     * Este método realiza la salida correcta del usuario
     *
     * @param user datos del usuario
     */
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
