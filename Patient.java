// Patient class to store individual patient details
public class Patient {

    // Instance variables (attributes) of the Patient class to store personal information
    private int id;                           // Unique identifier for the patient
    private String email;                     // Patient's email address
    private String phoneNumber;               // Patient's phone number
    private String name;                      // Patient's first name
    private String surname;                   // Patient's surname (last name)
    private double weight;                    // Patient's weight in kilograms
    private double height;                    // Patient's height in centimeters
    private String medicalHistory;            // Patient's medical history (e.g., previous illnesses)
    private String surgicalHistory;           // Patient's surgical history (e.g., past surgeries)
    private String medicalConditions;         // Patient's current medical conditions (e.g., diabetes)
    private String previousObservations;      // Previous medical observations about the patient (e.g., test results)

    // Constructor to initialize a new patient object with all the necessary details
    public Patient(int id, String email, String phoneNumber, String name, String surname, double weight, double height,
                   String medicalHistory, String surgicalHistory, String medicalConditions, String previousObservations) {
        this.id = id;                          // Set the patient's ID
        this.email = email;                    // Set the patient's email
        this.phoneNumber = phoneNumber;        // Set the patient's phone number
        this.name = name;                      // Set the patient's first name
        this.surname = surname;                // Set the patient's last name (surname)
        this.weight = weight;                  // Set the patient's weight
        this.height = height;                  // Set the patient's height
        this.medicalHistory = medicalHistory;  // Set the patient's medical history
        this.surgicalHistory = surgicalHistory; // Set the patient's surgical history
        this.medicalConditions = medicalConditions; // Set the patient's medical conditions
        this.previousObservations = previousObservations; // Set the patient's previous observations
    }

    // Getter method for the email of the patient
    public String getEmail() {
        return email; // Return the patient's email
    }

    // Getter method for the name of the patient
    public String getName() {
        return name; // Return the patient's first name
    }

    // Getter method for the previous observations of the patient
    public String getPreviousObservations() {
        return previousObservations; // Return the patient's previous medical observations
    }

    // Override the toString method to provide a string representation of the Patient object
    // This will be used to display all the patient's details in a readable format
    @Override
    public String toString() {
        // Return a string with all the patient's information formatted for display
        return "ID: " + id + "\nEmail: " + email + "\nPhone Number: " + phoneNumber + "\nName: " + name + " " + surname +
               "\nWeight: " + weight + " kg\nHeight: " + height + " cm\nMedical History: " + medicalHistory +
               "\nSurgical History: " + surgicalHistory + "\nMedical Conditions: " + medicalConditions;
    }

    // Setter method to set or update the previous observations of the patient
    public void setPreviousObservation(String observation) {
        this.previousObservations = observation; // Set the previous observations with the new observation passed as an argument
    }
}
