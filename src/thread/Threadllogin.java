package thread;

/**
 *
 * @author Gustavo_Senorans
 */
import Insert.InsertCrudEmpleados;
import Insert.InsertCrudEmpleadosMail;
import Insert.InsertCrudEmpleadosMailTelf;
import Insert.InsertCrudEmpleadosTelf;
import Insert.InsertCrudEmpresa;
import Insert.InsertCrudUsers;
import print.PrintPorColumna;
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
import Search.*;

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
                    exit();
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
                        if (codigo.equalsIgnoreCase("-1")) {

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

                            msg = codigo;
                            escriptor.write(msg);
                            escriptor.newLine();
                            escriptor.flush();
                            logins.put(user.getDni(), codigo);

                            try {
                                while (!salir) {

                                    palabra = lector.readLine();
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

                                        PrintPorColumna verificar = new PrintPorColumna();
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
                                            String codigoUserRecibido = frase[0];
                                            String crud = frase[1];
                                            String nombreTabla = frase[2];
                                            String columna = frase[3];
                                            String palabraAbuscar = frase[4];
                                            String orden = frase[5];

                                            if (!codigo.equals(codigoUserRecibido)) {
                                                verificarCodigoCliente(codigoUserRecibido);
                                            } else if (orden.equals("0") || orden.equals("1")) {
                                                System.out.println(fecha.fecha_hora());
                                                if (crud.equals("0")) {
                                                    if (nombreTabla.equals("0") && columna.equals("dni")) {
                                                        SearchCrudEmpleados.handleSearchRequest(crud, nombreTabla, columna, palabraAbuscar, outObjeto, client);
                                                    } else if (nombreTabla.equals("0") && columna.equals("nomempresa")) {
                                                        SearchCrudEmpleados.handleSearchRequest(crud, nombreTabla, columna, palabraAbuscar, outObjeto, client);
                                                    } else if (nombreTabla.equals("0") && columna.equals("departament")) {
                                                        SearchCrudEmpleados.handleSearchRequest(crud, nombreTabla, columna, palabraAbuscar, outObjeto, client);
                                                    } else if (nombreTabla.equals("0") && columna.equals("codicard")) {
                                                        SearchCrudEmpleados.handleSearchRequest(crud, nombreTabla, columna, palabraAbuscar, outObjeto, client);
                                                    } else if (nombreTabla.equals("0") && columna.equals("mail")) {
                                                        SearchCrudEmpleados.handleSearchRequest(crud, nombreTabla, columna, palabraAbuscar, outObjeto, client);
                                                    } else if (nombreTabla.equals("0") && columna.equals("telephon")) {
                                                        SearchCrudEmpleados.handleSearchRequest(crud, nombreTabla, columna, palabraAbuscar, outObjeto, client);
                                                    } else if (nombreTabla.equals("1") && columna.equals("dni")) {
                                                        SearchCrudUsers.handleSearchRequest(crud, nombreTabla, columna, palabraAbuscar, outObjeto, client);
                                                    } else if (nombreTabla.equals("1") && columna.equals("login")) {
                                                        SearchCrudUsers.handleSearchRequest(crud, nombreTabla, columna, palabraAbuscar, outObjeto, client);
                                                    } else if (nombreTabla.equals("1") && columna.equals("numtipe")) {
                                                        SearchCrudUsers.handleSearchRequest(crud, nombreTabla, columna, palabraAbuscar, outObjeto, client);
                                                    } else if (nombreTabla.equals("2") && columna.equals("nom")) {
                                                        SearchCrudEmpresa.handleSearchRequest(crud, nombreTabla, columna, palabraAbuscar, outObjeto, client);
                                                    } else if (nombreTabla.equals("2") && columna.equals("address")) {
                                                        SearchCrudEmpresa.handleSearchRequest(crud, nombreTabla, columna, palabraAbuscar, outObjeto, client);
                                                    } else if (nombreTabla.equals("2") && columna.equals("telephon")) {
                                                        SearchCrudEmpresa.handleSearchRequest(crud, nombreTabla, columna, palabraAbuscar, outObjeto, client);
                                                    } else if (nombreTabla.equals("3") && columna.equals("dni")) {
                                                        SearchCrudJornada.handleSearchRequest(crud, nombreTabla, columna, palabraAbuscar, outObjeto, client);
                                                    } else if (nombreTabla.equals("3") && columna.equals("codicard")) {
                                                        SearchCrudJornada.handleSearchRequest(crud, nombreTabla, columna, palabraAbuscar, outObjeto, client);
                                                    } else if (nombreTabla.equals("3") && columna.equals("fecha")) {
                                                        SearchCrudJornada.handleSearchRequest(crud, nombreTabla, columna, palabraAbuscar, outObjeto, client);
                                                    } else if (!nombreTabla.equals(null) && columna.equals("0")) {
                                                        SearchCrudTablasCompleta.handleSearchRequest(crud, nombreTabla, columna, palabraAbuscar, outObjeto, client);
                                                    }
                                                }
                                            }
                                        } else if (NomApellido[7].equals("0") || NomApellido[7].equals("1")) {

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
                                                    SearchCrudNonApellidoEmpleados.handleSearchRequest(crud, nombreTabla, nom, datoNom,
                                                            apellido, datoApellido, palabra, outObjeto, client);
                                                } else if (nombreTabla.equals("3") && nom.equals("nom") && apellido.equals("apellido")) {
                                                    SearchCrudNomApellidoJornada.handleSearchRequest(crud, nombreTabla, nom, datoNom,
                                                            apellido, datoApellido, palabra, outObjeto, client);
                                                }
                                            }
                                        } else if (insertEmpresas[9].equals("0") || insertEmpresas[9].equals("1")) {
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
                                                        InsertCrudEmpresa.handleInsertRequest(crud, nombreTabla, nom, datoNom,
                                                                address, datoAddress, telephon, datoTelephon, palabra, outObjeto, client);
                                                    }
                                                }
                                            }
                                        } else if (insertUsuarios[11].equals("0") || insertUsuarios[11].equals("1")) {
                                            String codigoUserRecibido = insertUsuarios[0];
                                            String crud = insertUsuarios[1];
                                            String nombreTabla = insertUsuarios[2];
                                            login = insertUsuarios[3];
                                            String datoLogin = insertUsuarios[4];
                                            pass = insertUsuarios[5];
                                            String datoPass = insertUsuarios[6];
                                            String numTipe = insertUsuarios[7];
                                            String datoNumTipe = insertUsuarios[8];
                                            dni = insertUsuarios[9];
                                            String datoDni = insertUsuarios[10];
                                            String orden = insertUsuarios[11];

                                            if (!codigo.equals(codigoUserRecibido)) {
                                                verificarCodigoCliente(codigoUserRecibido);

                                            } else if (orden.equals("0") || orden.equals("1")) {
                                                System.out.println(fecha.fecha_hora());
                                                if (crud.equals("1")) {
                                                    if (nombreTabla.equals("1")) {
                                                        InsertCrudUsers.handleInsertRequest(crud, nombreTabla, login, datoLogin,
                                                                pass, datoPass, numTipe, datoNumTipe, dni,
                                                                datoDni, palabra, outObjeto, client);
                                                    }
                                                }
                                            }
                                        } else if (insertEmpleadoMailTelf[15].equals("0") || insertEmpleadoMailTelf[15].equals("1")) {
                                            String codigoUserRecibido = insertEmpleadoMailTelf[0];
                                            String crud = insertEmpleadoMailTelf[1];
                                            String nombreTabla = insertEmpleadoMailTelf[2];
                                            dni = insertEmpleadoMailTelf[3];
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
                                                        InsertCrudEmpleadosMailTelf.handleInsertRequest(crud, nombreTabla, dni, datoDni,
                                                                nom, datoNom, apellido, datoApellido, nomempresa, datoNomempresa,
                                                                departament, datoDepartament, codicard, datoCodicard, palabra, outObjeto, client);
                                                    }
                                                }
                                            }

                                        } else if (insertEmpleadoMT[17].equals("0") && insertEmpleadoMT[15].equals("mail")
                                                || insertEmpleadoMT[17].equals("1") && insertEmpleadoMT[15].equals("mail")) {
                                            String codigoUserRecibido = insertEmpleadoMT[0];
                                            String crud = insertEmpleadoMT[1];
                                            String nombreTabla = insertEmpleadoMT[2];
                                            dni = insertEmpleadoMT[3];
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
                                                        InsertCrudEmpleadosMail.handleInsertRequest(crud, nombreTabla,
                                                                dni, datoDni, nom, datoNom, apellido, datoApellido,
                                                                nomempresa, datoNomempresa, departament, datoDepartament,
                                                                codicard, datoCodicard, mail, datoMail, palabra, outObjeto, client);
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
                                                verificarCodigoCliente(codigoUserRecibido);

                                            } else if (orden.equals("0") || orden.equals("1")) {
                                                System.out.println(fecha.fecha_hora());
                                                if (crud.equals("1")) {
                                                    if (nombreTabla.equals("0")) {
                                                        InsertCrudEmpleadosTelf.handleInsertRequest(crud, nombreTabla, dni,
                                                                datoDni, nom, datoNom, apellido, datoApellido,
                                                                nomempresa, datoNomempresa, departament, datoDepartament,
                                                                codicard, datoCodicard, telephon, datoTelephon, palabra,
                                                                outObjeto, client);
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
                                                verificarCodigoCliente(codigoUserRecibido);

                                            } else if (orden.equals("0") || orden.equals("1")) {
                                                System.out.println(fecha.fecha_hora());
                                                if (crud.equals("1")) {
                                                    if (nombreTabla.equals("0")) {
                                                        InsertCrudEmpleados.handleInsertRequest(crud, nombreTabla, dni,
                                                                datoDni, nom, datoNom, apellido, datoApellido,
                                                                nomempresa, datoNomempresa, departament, datoDepartament,
                                                                codicard, datoCodicard, mail, datoMail, telephon,
                                                                datoTelephon, palabra, outObjeto, client);
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

    private void verificarCodigoCliente(String codigoUserRecibido) throws IOException {
        if (!codigo.equals(codigoUserRecibido)) {
            System.out.println("____________________________________________________________________");
            System.out.println(fecha.fecha_hora());
            System.out.println("Cliente desconectado, el código no es el correcto");
            salir = true;
            escriptor.close();
            lector.close();
            client.close();
        }
    }

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
            Logger.getLogger(Threadllogin.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
    }
}
