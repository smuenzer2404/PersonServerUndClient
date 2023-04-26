import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;

public class ClientCommunication implements Runnable {

    private Socket client;

    public ClientCommunication(Socket client) {
        this.client = client;
    }

    private void handleCommands() throws PersonLoadExeption {
        ArrayList<Person> people = new PersonLoader("data/persons.csv").load();
        try (BufferedReader br = new BufferedReader(new InputStreamReader(client.getInputStream()));
             ObjectOutputStream oos = new ObjectOutputStream(client.getOutputStream())

        ) {

            String command;
            while ((command = br.readLine()) != null) {
                if (command.equalsIgnoreCase("exit")) {
                    System.out.println("client wants to exit");
                    break;
                }

                if (command.equalsIgnoreCase("getall")) {

                    for (Person person : people) {
                        oos.writeObject(person);
                    }
                    oos.writeObject(null);


                } else {
                    //mögliche Fälle: id 1, id 25, id 48 ... wir splitten beim Leerzeichen

                    String[] cmds = command.split(" "); //ausreichend für die Klausur!!!!


                    if (cmds.length != 2) {
                        System.out.println("command error");
                        oos.writeObject(null);
                    } else {
                        int desiredId = Integer.parseInt(cmds[1]);
                        for (Person person : people) {
                            if (person.getID() == desiredId) {
                                oos.writeObject(person);
                            }
                        }
                    }
                }
                oos.flush(); //bevor die while Schleife endet flushen wir
            }
        } catch (
                IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void run() {
        try {
            handleCommands();
        } catch (PersonLoadExeption e) {
            e.printStackTrace();
        }
    }
}
