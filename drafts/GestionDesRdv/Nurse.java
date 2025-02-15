public class Nurse extends Person {
    int id;
    public Nurse( int id, String lastName, String name, String phoneNumber) {
        super(id, name, lastName, phoneNumber);  // Call the constructor of the Person class
    }

    public int getId() { return id; }
}