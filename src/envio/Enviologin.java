/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package envio;

import modelo.Users;
import peticiones.Login;
import codigo.Codigo;
import fecha.Fechas;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Gustavo_Senorans
 */
public class Enviologin {
    Login login = new Login();
    Fechas fecha = new Fechas();

    public String compLogin(String word) throws IOException {
        try {
            String loginFrame = word;

            String[] textElements = loginFrame.split(",");
            String ip = java.net.InetAddress.getLocalHost().getHostAddress();

            ArrayList<Users> listausuarios;
            listausuarios = login.getUsuario();
            for (int i = 0; i < listausuarios.size(); i++) {
                if (textElements[0].equals(listausuarios.get(i).getLogin())
                        && textElements[1].equals(listausuarios.get(i).getPass())) {

                    String codiClient = Codigo.crearCodigoLogin(listausuarios.get(i).getNumtipe());
                    String logins = (listausuarios.get(i).getLogin());
                    String pass = (listausuarios.get(i).getPass());
                    int numTipe = (listausuarios.get(i).getNumtipe());
                    String dni = (listausuarios.get(i).getDni());

                    System.out.println("____________________________________________________________________");
                    System.out.println(fecha.fecha_hora());
                    System.out.println("Cliente con la ip: " + ip
                            + "\nLogin: " + logins
                            + "\nPass: " + pass
                            + "\nNumTipe: " + numTipe
                            + "\nDni: " + dni
                            + "\nCodigo acceso: " + codiClient
                            + "\nConectado corectamente.");
                    System.out.println("____________________________________________________________________");

                    return logins + "," + pass + "," + numTipe + "," + codiClient;
                }
            }
            textElements = loginFrame.split(",");
            System.out.println(fecha.fecha_hora());
            System.out.println("Error al iniciar sesion con el usuario " + textElements[0]);
            System.out.println("____________________________________________________________________");
        } catch (SQLException ex) {
            Logger.getLogger(Enviologin.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
