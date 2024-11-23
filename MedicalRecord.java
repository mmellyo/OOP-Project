import java.util.*; // Importing the ArrayList and List classes from the java.util package

// MedicalRecord class for storing allergies, surgical history, observations, and treatments
public class MedicalRecord {

    // Instance variables (attributes) to store information about a patient's medical record
    private String patientID;               // Unique ID associated with the patient
    private List<String> allergies;         // A list to store allergies the patient has
    private List<String> surgicalHistory;   // A list to store the patient's surgical history
    private String medicalObservations;     // Medical observations about the patient (e.g., diagnoses)
    private List<Treatment> treatments;     // A list of treatments the patient has received (using Treatment class)

    // Constructor to initialize a new MedicalRecord object with just the patient's ID
    // The constructor initializes the lists to empty since they may be filled later
    public MedicalRecord(String patientID) {
        this.patientID = patientID;          // Set the patient's unique ID
        this.allergies = new ArrayList<>();  // Initialize the allergies list as an empty list
        this.surgicalHistory = new ArrayList<>(); // Initialize the surgical history list as an empty list
        this.treatments = new ArrayList<>(); // Initialize the treatments list as an empty list
    }

    // Methods to add information to the medical record

    // Add an allergy to the patient's medical record
    public void addAllergy(String allergy) {
        allergies.add(allergy); // Adds the given allergy to the allergies list
    }

    // Add a surgery to the patient's surgical history
    public void addSurgicalHistory(String surgery) {
        surgicalHistory.add(surgery); // Adds the given surgery to the surgical history list
    }

    // Set the medical observations for the patient (e.g., diagnoses, results)
    public void setMedicalObservations(String observations) {
        this.medicalObservations = observations; // Set the medical observations field
    }

    // Add a treatment to the patient's treatments list
    public void addTreatment(Treatment treatment) {
        treatments.add(treatment); // Adds the given treatment (which is an object of the Treatment class) to the treatments list
    }

    // Getter methods to retrieve information from the medical record

    // Get the patient's ID (e.g., for identifying the record)
    public String getPatientID() {
        return patientID; // Return the patient's unique ID
    }

    // Get the list of allergies the patient has
    public List<String> getAllergies() {
        return allergies; // Return the list of allergies
    }

    // Get the list of surgeries the patient has had
    public List<String> getSurgicalHistory() {
        return surgicalHistory; // Return the list of surgical history
    }

    // Get the medical observations of the patient (e.g., diagnoses or test results)
    public String getMedicalObservations() {
        return medicalObservations; // Return the medical observations
    }

    // Get the list of treatments the patient has undergone
    public List<Treatment> getTreatments() {
        return treatments; // Return the list of treatments
    }
}
