import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class PatientManager {
    private static final String FILE_NAME = "patients.txt";
    private static ArrayList<Patient> patientRecords = new ArrayList<>();

    public static void main(String[] args) {
        loadPatientsFromFile(); // Load patients at startup
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n1. Add New Patient\n2. Update Observation\n3. View All Patients\n4. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1 -> addNewPatient(scanner);
                case 2 -> updatePatientObservation(scanner);
                case 3 -> viewAllPatients();
                case 4 -> {
                    savePatientsToFile();
                    System.out.println("Exiting...");
                    return;
                }
                default -> System.out.println("Invalid choice! Try again.");
            }
        }
    }

    // 🔹 Load patients from the file at startup
    private static void loadPatientsFromFile() {
        File file = new File(FILE_NAME);
        if (!file.exists()) return;

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                Patient patient = Patient.fromString(line);
                if (patient != null) {
                    patientRecords.add(patient);
                }
            }
        } catch (IOException e) {
            System.out.println("Error loading patients from file.");
        }
    }

    // 🔹 Save all patients to file
    private static void savePatientsToFile() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_NAME))) {
            for (Patient p : patientRecords) {
                bw.write(p.toString());
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error saving patients to file.");
        }
    }

    // 🔹 Find a patient by name
    private static Patient findPatientByName(String name) {
        for (Patient p : patientRecords) {
            if (p.getName().equalsIgnoreCase(name)) {
                return p;
            }
        }
        return null;
    }

    // 🔹 Add a new patient
    private static void addNewPatient(Scanner scanner) {
        System.out.print("Enter Patient Name: ");
        String name = scanner.nextLine();

        if (findPatientByName(name) != null) {
            System.out.println("Patient already exists!");
            return;
        }

        System.out.print("Enter Last Name: ");
        String lastName = scanner.nextLine();
        System.out.print("Enter Phone Number: ");
        String phoneNumber = scanner.nextLine();
        System.out.print("Enter Date of Birth (YYYY-MM-DD): ");
        String dob = scanner.nextLine();

        int newId = patientRecords.size() + 1; // Generate a new unique ID
        Patient newPatient = new Patient(newId, name, lastName, phoneNumber, dob, "No Observations", "No Prescription");
        patientRecords.add(newPatient);

        savePatientsToFile(); // Save the new patient to file
        System.out.println("Patient added successfully!");
    }

    // 🔹 Update Observation & Prescription
    private static void updatePatientObservation(Scanner scanner) {
        System.out.print("Enter Patient Name to update: ");
        String name = scanner.nextLine();

        Patient patient = findPatientByName(name);
        if (patient == null) {
            System.out.println("Patient not found!");
            return;
        }

        System.out.print("Enter New Observation: ");
        String newObservation = scanner.nextLine();
        System.out.print("Enter New Prescription: ");
        String newPrescription = scanner.nextLine();

        patient.setObservation(newObservation);
        patient.setPrescription(newPrescription);

        savePatientsToFile(); // Save changes to file
        System.out.println("Observation & Prescription updated!");
    }

    // 🔹 View all patients
    private static void viewAllPatients() {
        if (patientRecords.isEmpty()) {
            System.out.println("No patients found.");
            return;
        }
        for (Patient p : patientRecords) {
            System.out.println(p.getId() + ". " + p.getName() + " " + p.getLastName() +
                    " - " + p.getPhoneNumber() + " - " + p.getDateOfBirth() +
                    "\n   Observation: " + p.getObservation() +
                    "\n   Prescription: " + p.getPrescription() + "\n");
        }
    }
}
