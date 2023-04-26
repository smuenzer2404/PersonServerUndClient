import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;

public class PersonClient {

    public static void main(String[] args) {
        try (Socket server = new Socket("localhost", 1111);
             BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(server.getOutputStream()));
             ObjectInputStream ois = new ObjectInputStream(server.getInputStream())
        ) {

            bw.write("get 25");
            bw.newLine();
            bw.flush();

            bw.write("getall");
            bw.newLine();
            bw.flush();


            Person person;
            while ((person = (Person) ois.readObject()) != null) {
                System.out.println(person);
            }


        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }


    }
}
