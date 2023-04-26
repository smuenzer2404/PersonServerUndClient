import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class PersonLoader {

    public static void main(String[] args) {
        ArrayList<Person> people = new ArrayList<>();

    }

    private String pathToFile;

    public PersonLoader(String pathToFile) {
        this.pathToFile = pathToFile;
    }

    public ArrayList<Person> load () throws PersonLoadExeption {

        ArrayList<Person> people = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(pathToFile))) {

            String line;
            while ((line = br.readLine()) != null){
                System.out.println(line); //hier bekommen wir die gesamte Linie 1;Susi;Sorglos
                String [] personData = line.split(";"); //ergibt dann ein String Array data = {1;Susi;Sorglos}

                int id = Integer.parseInt(personData[0]);
                String fn = personData[1];
                String ln = personData[2];
                Person p = new Person (id,fn,ln);
                people.add(p);
            }


        } catch (FileNotFoundException e) {
            throw new PersonLoadExeption("file does not exist", e);
        } catch (IOException e) {
            throw new PersonLoadExeption("can not read file", e);
        }


        return people;
    }
}
