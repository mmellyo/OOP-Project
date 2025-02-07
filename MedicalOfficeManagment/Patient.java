class Patient {
    private int id;
    private String name;
    private String lastName;
    private String phoneNumber;
    private String dateOfBirth;
    private String observation;
    private String prescription;
    private String diagnostic;

    public Patient(int id, String name, String lastName, String phoneNumber, String dateOfBirth, String observation, String diagnostic, String prescription) {
        this.id = id;
        this.name = name;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.dateOfBirth = dateOfBirth;
        this.observation = observation;
        this.diagnostic = diagnostic;
        this.prescription = prescription;
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public String getLastName() { return lastName; }
    public String getPhoneNumber() { return phoneNumber; }
    public String getDateOfBirth() { return dateOfBirth; }
    public String getObservation() { return observation; }
    public String getDiagnostic() { return diagnostic; }
    public String getPrescription() { return prescription; }

    public void setObservation(String observation) { this.observation = observation; }
    public void setPrescription(String prescription) { this.prescription = prescription; }
    public void setDiagnostic(String diagnostic) { this.diagnostic = diagnostic; }

    @Override
    public String toString() {
        return id + "," + name + "," + lastName + "," + phoneNumber + "," + dateOfBirth + "," + observation + "," + diagnostic + "," + prescription;
    }

    public static Patient fromString(String line) {
        String[] data = line.split(",");
        if (data.length == 8) {
            return new Patient(
                Integer.parseInt(data[0]), data[1], data[2], data[3], data[4], data[5], data[6], data[7]
            );
        }
        return null;
    }
}