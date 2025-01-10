// Treatment class for storing medication details
public class Treatment {
    private String medicationName;  // Name of the medication
    private String dosage;          // Dosage of the medication (e.g., 500mg, 1 tablet)
    private int duration;           // Duration of the treatment in days

    // Constructor to initialize a Treatment object with medication details
    public Treatment(String medicationName, String dosage, int duration) {
        this.medicationName = medicationName;
        this.dosage = dosage;
        this.duration = duration;
    }

    // Getter for medication name
    public String getMedicationName() {
        return medicationName;  // Return the name of the medication
    }

    // Getter for dosage
    public String getDosage() {
        return dosage;  // Return the dosage of the medication
    }

    // Getter for duration
    public int getDuration() {
        return duration;  // Return the duration of the treatment in days
    }

    // Override the toString method to display treatment details in a readable format
    @Override
    public String toString() {
        return "Medication: " + medicationName + "\nDosage: " + dosage + "\nDuration: " + duration + " days";
    }
}
