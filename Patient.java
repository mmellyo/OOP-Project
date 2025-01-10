public class Patient extends Person {


    private String previousObservations;
    private String previousObservationsDcr;
    private String dateOfBirth;

    // Constructor to initialize a new Patient object with all the necessary details
    public Patient(int id, 
                   String name,
                   String lastName, 
                   String phoneNumber, 
                   String dateOfBirth, 
                   String previousObservations, 
                   String previousObservationsDcr) {

        super(id, name, lastName, phoneNumber); // Call the constructor of the Person class
        this.previousObservations = previousObservations;
        this.previousObservationsDcr = previousObservationsDcr;
        this.dateOfBirth = dateOfBirth;
    }


    // Getter methods
    public String getPreviousObservations() {
        return previousObservations;
    }

    public String getPreviousObservationsdcr() {
        return previousObservationsDcr;
    }

    public String getbd() {
        return dateOfBirth;
    }

    // Override the toString method to provide a string representation of the Patient object
    @Override
    public String toString() {
        return "ID: " + getId() + "\nName: " + getName() + " " + getLastName() + "\nPhone Number: " + getPhoneNumber() + "\nDate of birth: " + getbd() + 
              "\nprevious Observations dates: " + getPreviousObservations()+  "\nprevious Observations dates: " + getPreviousObservationsdcr();
    }

    // Setter method to set or update the previous observations of the patient
    public void setPreviousObservation(String observation) {
        this.previousObservations = observation;
    }
}
