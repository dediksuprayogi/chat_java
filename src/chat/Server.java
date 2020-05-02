package chat;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Vector;

public class Server {
    private static ServerSocket servSock;
    private static final int PORT = 20000 ;
    //untuk menyimpan socket-socket yang terhubung ke server
    static Vector clients = new Vector();
    static Socket clientSocket; 
    public static void main(String args[]) throws IOException{
        System.out.println("Opening Port.....\n");
        try{
            servSock = new ServerSocket(PORT);
        }catch(IOException e){
            System.out.println("Unable to attach to port");
            System.exit(1);
        }
        do{
            Socket client = servSock.accept();
            cThread handler = new cThread(client);
            clients.add(handler);
            handler.start();
        }while(true);
    }
}
