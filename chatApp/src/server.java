import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class server implements Runnable {
    @Override
    public void run(){
        try{
        ServerSocket server = new ServerSocket(9999);
        Socket client = server.accept();
        } catch(IOException e){

        }

    }

     class connectionHandle  implements Runnable {
      
        @Override
        public void run(){
    
        }
        
    }

}
