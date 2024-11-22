import java.util.ArrayList;
import java.util.List;

public class MedicalRecord {
    private String patientID;
    private List<String> allergies;
    private List<String> surgicalHistory;
    private String medicalObservations;
    private List<Treatment> treatments;

    public MedicalRecord(String patientID) {
        this.patientID = patientID;
        this.allergies = new ArrayList<>();
        this.surgicalHistory = new ArrayList<>();
        this.treatments = new ArrayList<>();
    }

    // Add an allergy to the record
    public void addAllergy(String allergy) {
        allergies.add(allergy);
    }

    // Add a surgical history to the record
    public void addSurgicalHistory(String surgery) {
        surgicalHistory.add(surgery);
    }

    // Add a medical observation
    public void addObservation(String observation) {
        this.medicalObservations = observation;
    }

    // Add a treatment to the record
    public void addTreatment(Treatment treatment) {
        treatments.add(treatment);
    }

    // Display the medical summary
    public String getMedicalSummary() {
        StringBuilder summary = new StringBuilder();
        summary.append("Patient ID: ").append(patientID).append("\n");
        summary.append("Allergies: ").append(allergies.isEmpty() ? "None" : String.join(", ", allergies)).append("\n");
        summary.append("Surgical History: ").append(surgicalHistory.isEmpty() ? "None" : String.join(", ", surgicalHistory)).append("\n");
        summary.append("Medical Observations: ").append(medicalObservations != null ? medicalObservations : "None").append("\n");
        summary.append("Treatments: \n");

        for (Treatment treatment : treatments) {
            summary.append("  - ").append(treatment).append("\n");
        }

        return summary.toString();
    }
}
