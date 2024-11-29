import java.awt.*;        // Import Swing components (e.g., JFrame, JButton) for building the GUI
import java.util.*;
import javax.swing.*;

//Main application class to manage the medical records application using Swing (GUI)
public class MedicalRecordApp {

    // Lists to store the patient & medical records
    private static ArrayList<Patient> patientRecords = new ArrayList<>();  //an ArrayList that hold objects of type Patient.
    private static ArrayList<Doctor> doctorsRecords = new ArrayList<>();
    private static ArrayList<MedicalRecord> medicalRecords = new ArrayList<>();

    //Main method - Entry point for the application
    public static void main(String[] args) {

        //Create a default patient for testing purposes
        Patient defaultPatient = new Patient(
            1,
            "0550607080",
            "patient",
            "default",
            75.0, 175.0,
            "No previous surgeries",
            "No major medical conditions",
            "Healthy",
            "Previous checkup on 2024-11-01: All vitals normal.");

        patientRecords.add(defaultPatient);  //Add the default patient to the list

        //JFrame
        JFrame mainFrame = new JFrame("Health Medical Center");
        mainFrame.setSize(400, 300);          
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        /************************************************************** */
        //panel with a GridLayout to arrange components vertically
        JPanel loginPanel = new JPanel(new GridLayout(4, 1));
        JLabel welcomeLabel = new JLabel("Welcome!");
        JLabel IDLabel = new JLabel("Enter your ID please:");  // Label for id input
        JTextField IDField = new JTextField();  // Text field to enter ID
        /*JLabel PhoneNumberLabel = new JLabel("Enter your Phone number please:");  // Label for nmbr input
        JTextField PhoneNumberField = new JTextField();  // Text field to enter nmbr         
        JButton loginButton = new JButton("already have an account? log in");      
        JButton signupButton = new JButton("create a new account");*/

        //Adding components (welcome label and buttons) to the login panel
        //loginPanel.add(welcomeLabel);
        loginPanel.add(IDLabel);
        loginPanel.add(IDField);
        /*loginPanel.add(PhoneNumberLabel);
        loginPanel.add(PhoneNumberField);
        loginPanel.add(signupButton);
        loginPanel.add(loginButton);*/

        //Adding the login panel to the main frame and make it visible
        mainFrame.add(loginPanel);
        mainFrame.setVisible(true);

        //Action listener for the sign-up button - choose role
        signupButton.addActionListener(e -> {
            String number = PhoneNumberField.getText();
            if (findPatientByNumber(number) == null) {
                // If the email is not registered :
                mainFrame.dispose();  // Close the login frame
                openRole(number);          // choose if youre a dcr or a patient
            } else {
                // If the email is already registered, show an error message
                JOptionPane.showMessageDialog(mainFrame, "This email is already registered.", "log in", JOptionPane.ERROR_MESSAGE);
            }
        } );


        //Action listener for the Doctor button - displays a message that doctor functionality is coming soon
        loginButton.addActionListener(e -> JOptionPane.showMessageDialog(mainFrame, "Doctor functionality coming soon."));
    }

    // Method to open the patient login screen where patients can log in or sign up
    private static void openPatientLoginScreen() {
        //Create a new JFrame for the patient login/signup screen
        JFrame patientFrame = new JFrame("Patient Login/Signup");
        patientFrame.setSize(400, 300);
        JPanel panel = new JPanel(new GridLayout(3, 2));  // Panel with 3 rows and 2 columns for inputs

        JLabel emailLabel = new JLabel("Enter your email:");  // Label for email input
        JTextField emailField = new JTextField();  // Text field to enter email
        JButton loginButton = new JButton("Log In");  // Button to log in
        JButton signUpButton = new JButton("Sign Up");  // Button to sign up

        // Add components (label, text field, buttons) to the panel
        panel.add(emailLabel);
        panel.add(emailField);
        panel.add(loginButton);
        panel.add(signUpButton);

        // Add the panel to the patient frame and make it visible
        patientFrame.add(panel);
        patientFrame.setVisible(true);

        // Action listener for the login button
        loginButton.addActionListener(e -> {
            String email = emailField.getText();  // Get the email entered by the user
            Patient patient = findPatientByNumber(email);  // Find the patient using the email
            if (patient != null) {
                // If the patient is found, show a welcome message and proceed to display patient info
                JOptionPane.showMessageDialog(patientFrame, "Welcome back, " + patient.getName() + "!");
                patientFrame.dispose();  // Close the login frame
                displayPatientInfo(patient);  // Display patient details
                openMedicineTypeSelection();  // Open medicine selection screen
            } else {
                // If the email is not found, show an error message
                JOptionPane.showMessageDialog(patientFrame, "Account not found. Please sign up.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        // Action listener for the sign-up button
        signUpButton.addActionListener(e -> {
            String email = emailField.getText();  // Get the email entered by the user
            if (findPatientByNumber(email) == null) {
                // If the email is not registered, proceed to sign up by collecting patient details
                patientFrame.dispose();  // Close the login frame
                collectPatientDetails(email);  // Collect additional details for sign-up
            } else {
                // If the email is already registered, show an error message
                JOptionPane.showMessageDialog(patientFrame, "This email is already registered.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
    }

    private static void openRole(String email) {
        JFrame roleFrame = new JFrame("Role Selection");
        roleFrame.setSize(400, 300);
        JPanel rolePanel = new JPanel(new GridLayout(3, 1));

        JLabel roleLabel = new JLabel("Are you signing up as a doctor or a patient?");
        JButton patientRoleButton = new JButton("Patient");
        JButton doctorRoleButton = new JButton("Doctor");

        rolePanel.add(roleLabel);
        rolePanel.add(patientRoleButton);
        rolePanel.add(doctorRoleButton);

        roleFrame.add(rolePanel);
        roleFrame.setVisible(true);


        // Action listener for the patient role button
        patientRoleButton.addActionListener(e -> {
            Random rand = new Random();
            int id = rand.nextInt(1000); // Generate a random patient ID (0 - 999)
            Patient newPatient = new Patient(
                id,
                "N/A", // Placeholder phone number
                "N/A", // Placeholder name
                "N/A", // Placeholder surname
                0, 0,  // Placeholder weight and height
                "N/A", "N/A", "N/A", ""
            );
            patientRecords.add(newPatient);
            JOptionPane.showMessageDialog(roleFrame, "Patient ID created: " + id + "\n with email :" + email) ;
            roleFrame.dispose();
            openMedicineTypeSelection();  //Proceed to medicine type selection
        });


        // Action listener for the doctor role button
        doctorRoleButton.addActionListener(e -> {
            Random rand = new Random();
            int id = 1000 + rand.nextInt(1001);  // (1000 - 2000)
            Doctor newDoctor = new Doctor(id, email, "N/A", "N/A");
            doctorsRecords.add(newDoctor);
            JOptionPane.showMessageDialog(roleFrame, "Doctor ID created: " + id + "\n with email :" + email) ;
            roleFrame.dispose();
            openMedicineTypeSelection();  //Proceed to medicine type selection

        });
    }
   


    // Method to collect additional patient details during the sign-up process
    private static void collectPatientDetails(String email) {
        // Create a new JFrame for collecting patient details
        JFrame detailsFrame = new JFrame("Enter Patient Details");
        detailsFrame.setSize(500, 500);
        JPanel panel = new JPanel(new GridLayout(10, 2));  // Panel with a grid layout

        // Create text fields to collect various details from the user
        JTextField phoneNumberField = new JTextField();
        JTextField nameField = new JTextField();
        JTextField surnameField = new JTextField();
        JTextField weightField = new JTextField();
        JTextField heightField = new JTextField();
        JTextField medicalHistoryField = new JTextField();
        JTextField surgicalHistoryField = new JTextField();
        JTextField medicalConditionsField = new JTextField();

        // Add labels and corresponding text fields to the panel
        panel.add(new JLabel("Phone Number:"));
        panel.add(phoneNumberField);
        panel.add(new JLabel("Name:"));
        panel.add(nameField);
        panel.add(new JLabel("Surname:"));
        panel.add(surnameField);
        panel.add(new JLabel("Weight (kg):"));
        panel.add(weightField);
        panel.add(new JLabel("Height (cm):"));
        panel.add(heightField);
        panel.add(new JLabel("Medical History:"));
        panel.add(medicalHistoryField);
        panel.add(new JLabel("Surgical History:"));
        panel.add(surgicalHistoryField);
        panel.add(new JLabel("Medical Conditions:"));
        panel.add(medicalConditionsField);

        // Create a submit button to confirm the input
        JButton submitButton = new JButton("Submit");
        panel.add(submitButton);

        // Add the panel to the details frame and make it visible
        detailsFrame.add(panel);
        detailsFrame.setVisible(true);

        // Action listener for the submit button to create a new patient record
        submitButton.addActionListener(e -> {
            try {
                Random rand = new Random();
                int id = rand.nextInt(1000);  // Generate a random patient ID

                // Collect details from the text fields
                String phoneNumber = phoneNumberField.getText();
                String name = nameField.getText();
                String surname = surnameField.getText();
                double weight = Double.parseDouble(weightField.getText());
                double height = Double.parseDouble(heightField.getText());
                String medicalHistory = medicalHistoryField.getText();
                String surgicalHistory = surgicalHistoryField.getText();
                String medicalConditions = medicalConditionsField.getText();

                // Create a new Patient object and add it to the list of patients
                Patient newPatient = new Patient(id, phoneNumber, name, surname, weight, height, medicalHistory, surgicalHistory, medicalConditions, "");
                patientRecords.add(newPatient);

                // Show a confirmation message and open the medicine selection screen
                JOptionPane.showMessageDialog(detailsFrame, "Patient registered successfully!");
                detailsFrame.dispose();
                openMedicineTypeSelection();  // Open the next step (medicine type selection)
            } catch (NumberFormatException ex) {
                // If the input for weight or height is invalid, show an error message
                JOptionPane.showMessageDialog(detailsFrame, "Please enter valid numbers for weight and height.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
    }

    // Method to find a patient by their email address
    private static Patient findPatientByNumber(String email) {
        for (Patient patient : patientRecords) {
            if (patient.getPhoneNumber().equals(email)) {
                return patient;  // Return the patient if found
            }
        }
        return null;  // Return null if no patient is found with the given email
    }

    // Method to display the patient's information
    private static void displayPatientInfo(Patient patient) {
        String previousObservations = patient.getPreviousObservations();
        if (previousObservations.isEmpty()) {
            previousObservations = "No previous observations available.";
        }
        // Show the patient's information in a dialog
        JOptionPane.showMessageDialog(null, patient.toString() + "\nPrevious Observations: " + previousObservations);
    }

    // Method to open the window where the patient can select their medicine type
    private static void openMedicineTypeSelection() {
        JFrame typeFrame = new JFrame("Choose Medicine Type");
        typeFrame.setSize(400, 600);
        JPanel panel = new JPanel(new GridLayout(6, 1));

        JLabel promptLabel = new JLabel("Please choose the medical specialty:");

        // Create buttons for various medical specialties
        JButton ophthalmologistButton = new JButton("Ophthalmologist");
        JButton opticianButton = new JButton("Optician");
        JButton dermatologistButton = new JButton("Dermatologist");
        JButton cardiologistButton = new JButton("Cardiologist");
        JButton dentistButton = new JButton("Dentist");

        // Add the components to the panel and make the frame visible
        panel.add(promptLabel);
        panel.add(ophthalmologistButton);
        panel.add(opticianButton);
        panel.add(dermatologistButton);
        panel.add(cardiologistButton);
        panel.add(dentistButton);

        typeFrame.add(panel);
        typeFrame.setVisible(true);
    }
}



/*String ID = IDField.getText();  // Get the ID entered by the user
            if (ID.matches("[0-9]+")) {
                JOptionPane.showMessageDialog(mainFrame, "welcome, patient!");
                openMedicineTypeSelection();
            } else if (ID.matches("[a-zA-Z]+")) {
                JOptionPane.showMessageDialog(mainFrame, "welcome, doctor!");
                openMedicineTypeSelection();
            } else {
                JOptionPane.showMessageDialog(mainFrame, "The ID contains a mix of characters.");
            } */