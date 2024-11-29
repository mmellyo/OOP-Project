//Patient class to store individual patient details
public class Patient {

    //Instance variables (attributes) of the Patient class to store personal information
    private int id;                                                
    private String phoneNumber;               
    private String name;                      
    private String lastName;                
    private double weight;                    
    private double height;                    
    private String medicalHistory;            
    private String surgicalHistory;           
    private String medicalConditions;         
    private String previousObservations; 
    private static int idCounter = 1;    

    // Constructor to initialize a new patient object with all the necessary details
    public Patient(int id, String phoneNumber, String name, String lastName, double weight, double height,
                   String medicalHistory, String surgicalHistory, String medicalConditions, String previousObservations) {

        this.id = idCounter++;;                                            
        this.phoneNumber = phoneNumber;        
        this.name = name;                      
        this.lastName = lastName;               
        this.weight = weight;                  
        this.height = height;                 
        this.medicalHistory = medicalHistory;
        this.surgicalHistory = surgicalHistory; 
        this.medicalConditions = medicalConditions;
        this.previousObservations = previousObservations;
    }

    // Getter method for the number of the patient
    public String getPhoneNumber() {
        return phoneNumber; 
    }

    // Getter method for the name of the patient
    public String getName() {
        return name;
    }

    // Getter method for the previous observations of the patient
    public String getPreviousObservations() {
        return previousObservations;
    }

    // Override the toString method to provide a string representation of the Patient object
    // This will be used to display all the patient's details in a readable format
    @Override
    public String toString() {
        // Return a string with all the patient's information formatted for display
        return "ID: " + id + "\nPhone Number: " + phoneNumber + "\nName: " + name + " " + lastName +
               "\nWeight: " + weight + " kg\nHeight: " + height + " cm\nMedical History: " + medicalHistory +
               "\nSurgical History: " + surgicalHistory + "\nMedical Conditions: " + medicalConditions;
    }

    // Setter method to set or update the previous observations of the patient
    public void setPreviousObservation(String observation) {
        this.previousObservations = observation; // Set the previous observations with the new observation passed as an argument
    }
}
