public class Person {
    private int id;
    private String name;
    private String lastName;
    private String phoneNumber;

    // Constructor to initialize Person object
    public Person(int id, String name, String lastName, String phoneNumber) {
        this.id = id;
        this.name = name;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
    }

    // Getter methods
    public int getId() { return id; }
    public String getName() { return name; }
    public String getLastName() { return lastName; }
    public String getPhoneNumber() { return phoneNumber; }
}
