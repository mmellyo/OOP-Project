import java.util.*;

public class Doctor {
    private int id;                     
    private String name;                
    private String specialty;           
    private String email;
    private String phoneNumber;
            
    private ArrayList<Patient> patientsList; // List of patients the doctor is treating

    // Constructor to initialize a Doctor object
    public Doctor(int id, String name, String specialty, String email) {
        this.id = id;
        this.name = name;
        this.specialty = specialty;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.patientsList = new ArrayList<>();
    }

    // Method to add a patient to the doctor's patient list
    public void addPatient(Patient patient) {
        this.patientsList.add(patient);
    }

    // Method to view a patient's information by email
    public Patient viewPatientInfo(String email) {
        for (Patient patient : patientsList) {   //go through each Patient in patientsListvand perform op with each Patient.
            if (patient.getPhoneNumber().equals(email)) {
                return patient;  // Return the patient if found
            }
        }
        return null;  // Return null if patient not found
    }

    // Method to update a patient's observations
    public void updatePatientObservation(Patient patient, String observation) {
        patient.setPreviousObservation(observation);  // Update the patient's observation
    }

    // Method to display doctor's information
    public String getDoctorInfo() {
        return "Doctor ID: " + id + "\nName: " + name + "\nSpecialty: " + specialty + "\nContact Info: " + email;
    }

    // Method to display the list of patients the doctor is treating
    public void displaypatientsList() {
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
