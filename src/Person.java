import java.io.Serializable;

public class Person implements Serializable { //wenn wir Objekte schreiben wollen brauchen wir diese Interface - File I/O

    private int ID;
    private String firstname;
    private String lastname;

    public Person(int ID, String firstname, String lastname) {
        this.ID = ID;
        this.firstname = firstname;
        this.lastname = lastname;
    }

    public int getID() {
        return ID;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    @Override
    public String toString() {
        return "Person{" +
                "ID=" + ID +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                '}';
    }
}
