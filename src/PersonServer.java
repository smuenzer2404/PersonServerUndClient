import java.io.IOException;
import java.lang.ref.Cleaner;
import java.net.ServerSocket;
import java.net.Socket;

public class PersonServer {

    public static void main(String[] args) {
        try(ServerSocket ss = new ServerSocket(1111)){
            while (true){
                System.out.println("warte auf client");//server laufe bitte ewig
                Socket client = ss.accept(); //warte bis client sich anmeldet
                System.out.println("client ist da");
                //danach Client dem Handler übergeben
                ClientCommunication clientCommunication = new ClientCommunication(client);
                new Thread(clientCommunication).start(); //starte communication handler
                //ruft run () in ClientCommunication auf

            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
