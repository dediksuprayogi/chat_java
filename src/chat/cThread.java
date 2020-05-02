package chat;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

class cThread extends Thread{
    private Socket client ;
    private BufferedReader in ;
    private PrintWriter out ;
    public cThread(Socket socket){
        client = socket ;
        try{
            in = new BufferedReader(new InputStreamReader(client.getInputStream()));
            out = new PrintWriter(client.getOutputStream(),true);
        }catch(IOException e){
            e.printStackTrace();
        }
    }
    public void send(String message){
        out.println(message);
    } 
    public boolean equals(cThread ct){
        //jika socket ct dengan socket pada class ini sama return true
        // jika tidak return false ;
        boolean hsl = false;
        if(client.equals(ct))
             hsl= true;
        return hsl;
    } 
    public void run(){
        try{
            String received ;
            do{
                //baca dari client
                received = in.readLine();
                //tampilkan pada Server
   //             out.println(received);
                //message yang diterima oleh server di broadcast ke semua client
                for(int i=0;i<Server.clients.size();i++) {
                    cThread client = (cThread) Server.clients.get(i);
                    client.send(received);
                }
            }while(!received.equals("QUIT"));
        }catch(IOException e){
            e.printStackTrace();
        } finally{
            try{
                if (client != null){
                    System.out.println("Closing down connection");
                    client.close();
                }
            }catch(IOException e){
                e.printStackTrace();
            }
        }
    }
}
