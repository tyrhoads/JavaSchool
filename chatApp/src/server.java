import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class server implements Runnable {
   private ArrayList<connectionHandler> conections;
   
    @Override
    public void run(){
        try{
        ServerSocket server = new ServerSocket(9999);
        Socket client = server.accept();
        connectionHandler handler = new connectionHandler(client);
                conections.add(handler);
                } catch(IOException e){
        
                }
        
            }
        
            public void broadcast(String mesage){

                for(connectionHandler ch : conections){
                    if (ch != null) {
                        ch.sendMessage(mesage);
                    }
                }
        
            }
             class connectionHandler  implements Runnable {
              
                private  Socket client;
                private BufferedReader in;
                private PrintWriter out;
                private String nickName;
                public connectionHandler(Socket client2) {
                    //TODO Auto-generated constructor stub
                }
        
        
                @Override
        public void run(){
            try{
                out =  new PrintWriter(client.getOutputStream(), true);
                in = new BufferedReader(new InputStreamReader(client.getInputStream()));
                out.println("please enter a nick name");
                nickName = in.readLine();
                System.out.println(nickName + " contected!");
                broadcast(nickName + " joined the chat");
                String mesage;
                while ((mesage = in.readLine()) != null) {
                    if(mesage.startsWith("/nick ")){
                        //TOdo hadle nickname
                        String[] mesageSplit = mesage.split(" ",2);
                        if (mesageSplit.length == 2) {
                            broadcast(nickName + " renamed themselves to " + mesageSplit[1]);
                            nickName = mesageSplit[1];
                        }
                         }
                    else if(mesage.startsWith("/quit")) {

                    }
                    else{
                        broadcast(nickName + ": " + mesage);
                    }
                    
                }
            }catch(IOException e){

            }
    
        }


        public void sendMessage(String mesage)
        {
            out.println(mesage);
        }
        
    }

}
