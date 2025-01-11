import java.util.ArrayList;

public class Doctor extends Person {
    private String specialty;            
    private ArrayList<Patient> patientsList; // List of patients the doctor is treating

    // Constructor to initialize a Doctor object
    public Doctor(int id, String name, String lastName, String specialty, String phoneNumber) {
        super(id, name, lastName, phoneNumber);  // Call the constructor of the Person class
        this.specialty = specialty;
        this.patientsList = new ArrayList<>();
    }

    // Method to get doctor's specialty
    public String getSpecialty() {
        return specialty;
    }

    // Method to add a patient to the doctor's patient list
    public void addPatient(Patient patient) {
        this.patientsList.add(patient);
    }

    // Method to view a patient's information by number
    /*public Patient viewPatientInfo(int phoneNumber) {
        for (Patient tempPatient : patientsList) {   
            if (tempPatient.getPhoneNumber() == phoneNumber) {
                return tempPatient;  // Return the patient if found
            }
        }
        return null;  // Return null if patient not found
    }*/

    // Method to update a patient's observations
    public void updatePatientObservation(Patient patient, String observation) {
        patient.setPreviousObservation(observation);  // Update the patient's observation
    }

    // Method to display doctor's information
    public String getDoctorInfo() {
        return "Doctor ID: " + getId() + "\nName: " + getName() + "\nLast Name: " + getLastName() + "\nSpecialty: " + specialty ;
    }

    // Method to display the list of patients the doctor is treating
    public void displayPatientsList() {
        if (patientsList.isEmpty()) {
            System.out.println("No patients assigned to this doctor.");
        } else {
            System.out.println("Patients under care:");
            for (Patient patient : patientsList) {
                System.out.println(patient.getName() + " (" + patient.getPhoneNumber() + ")");
            }
        }
    }
}