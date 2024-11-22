public class Treatment {
    private String medication;
    private String dosage;
    private int duration; // in days

    public Treatment(String medication, String dosage, int duration) {
        this.medication = medication;
        this.dosage = dosage;
        this.duration = duration;
    }

    public String getMedication() {
        return medication;
    }

    public void setMedication(String medication) {
        this.medication = medication;
    }

    public String getDosage() {
        return dosage;
    }

    public void setDosage(String dosage) {
        this.dosage = dosage;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    @Override
    public String toString() {
        return "Medication: " + medication + ", Dosage: " + dosage + ", Duration: " + duration + " days";
    }
}
