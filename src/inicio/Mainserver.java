
package inicio;

import fecha.Fechas;
import frames.Jfserver;
import thread.Threadllogin;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Gustavo_Senorans
 */
public class Mainserver {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        ServerSocket server;
        Socket socket;
        Fechas fecha = new Fechas();
        try {
            Jfserver Jfserver = new Jfserver(); // Crear la instancia de jfIni aquí
            Jfserver.setVisible(true);

            // Redirigir System.out a jfIni
            OutputStream outputStream = new OutputStream() {
                @Override
                public void write(int b) throws IOException {
                    Jfserver.appendText(String.valueOf((char) b));
                }
            };
            PrintStream printStream = new PrintStream(outputStream);
            System.setOut(printStream);

            server = new ServerSocket(8888);
            HashMap<String,String> logins = new HashMap<String,String>();
            System.out.println(fecha.fecha_hora());
            System.out.println("Esperando cliente...");
            int i = 0;
            boolean inicio = true;
            while (inicio) {
                socket = server.accept();
                i++;
                new Threadllogin(socket,logins).start();
            }
        } catch (IOException ex) {
            Logger.getLogger(Jfserver.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
