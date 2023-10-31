
package inicio;

import fecha.Fechas;
import frames.WindowServer;
import thread.Threadllogin;

import java.io.File;
import java.io.FileOutputStream;
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

    public static void main(String[] args) {
        ServerSocket server;
        Socket socket;
        Fechas fecha = new Fechas();
        int i = 0;
        try {
            WindowServer WindowServer = new WindowServer();
            WindowServer.setVisible(true);

            File outputFile = new File("file/" + fecha.nombre_fichero() + ".txt");
            FileOutputStream fileOutputStream = new FileOutputStream(outputFile);
            
            OutputStream outputStream = new OutputStream() {
                @Override
                public void write(int b) throws IOException {
                    WindowServer.appendText(String.valueOf((char) b));
                    fileOutputStream.write(b);
                }
            };
            PrintStream printStream = new PrintStream(outputStream);
            System.setOut(printStream);
            
            server = new ServerSocket(8888);
            HashMap<String,String> logins = new HashMap<String,String>();
            System.out.println(fecha.fecha_hora());
            System.out.println("Esperando cliente...");
            boolean inicio = true;
            while (inicio) {
                socket = server.accept();
                i++;
                new Threadllogin(socket,logins).start();
            }
        } catch (IOException ex) {
            Logger.getLogger(WindowServer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
