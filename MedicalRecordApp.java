import java.awt.*;
import java.util.*;
import javax.swing.*;


public class MedicalRecordApp {
    private static Map<Integer, String> doctorName = new HashMap<>(); // Global static variable
    
    //Lists to store the patient & medical records
    private static ArrayList<Patient> patientRecords = new ArrayList<>();  //an ArrayList that hold objects of type Patient.
    private static ArrayList<Doctor> doctorsRecords = new ArrayList<>();    //an ArrayList that hold objects of type dcrs.
    private static ArrayList<MedicalRecord> medicalRecords = new ArrayList<>();
    private static Doctor defaultDoctor2;
    private static Doctor defaultDoctor1;
    //Main method - Entry point for the application
    public static void main(String[] args) {

        //Create a default patient for testing purposes
        Patient defaultPatient = new Patient(
            0000,
            "patient",
            "default",
            "05000000",
            "2005-03-11",
            "2024-11-01",
            "Doctor1"
            );

        patientRecords.add(defaultPatient);

        //Create default dcrs for testing purposes
        defaultDoctor1 = new Doctor(1111, "Amer Yahia", "lastName1", "Gynecologist" , "0511111111");
        defaultDoctor2 = new Doctor(2222, "Hamdi", "lastName2", "Obstetrician" , "0522222222");
        
        //Create default nurse for testing purposes
        Nurse nurse = new Nurse(3333, "lastName", "name", "0533333333");


        JFrame mainFrame = new JFrame("BloomCare Center");
        mainFrame.setSize(400, 300);          
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        JPanel loginPanel = new JPanel(new GridLayout(4, 1));
        JLabel welcomeLabel = new JLabel("Welcome!");
        JLabel IDLabel = new JLabel("Enter your ID please:");  // Label for id input
        JTextField IDField = new JTextField();  // Text field to enter ID
        JButton submiButton = new JButton("Submit");
        
        loginPanel.add(welcomeLabel);
        loginPanel.add(IDLabel);
        loginPanel.add(IDField);
        loginPanel.add(submiButton);

        mainFrame.add(loginPanel);
        mainFrame.setVisible(true);


        //Action listener for the submit button - choose role ( dcr / nurse )
        submiButton.addActionListener(e -> {
            int tempID = Integer.parseInt(IDField.getText());
           
            if(tempID == nurse.getId()) { // If I log as a nurse
                mainFrame.dispose();
                openNameInput();
            } else if (tempID == defaultDoctor1.getId()){
                mainFrame.dispose();
                openDoctorWelcome(defaultDoctor1.getName()); // Open the welcome window for the doctor 1
                
            } else if (tempID == defaultDoctor2.getId()) {
                mainFrame.dispose();
                openDoctorWelcome(defaultDoctor2.getName()); // Open the welcome window for the doctor 2
                
            } else { // If ID isn't Valide
                JOptionPane.showMessageDialog(mainFrame, "Invalid ID, please enter a valid ID"); 
            }
        } );  
    }
    /**** Method to display the "Welcome Doctor" window for the two doctors ****/
    private static void openDoctorWelcome(String doctorName) {
        JFrame doctorWelcomeFrame = new JFrame("Welcome Doctor");
        doctorWelcomeFrame.setSize(400, 300);  // Window size
        doctorWelcomeFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        doctorWelcomeFrame.setLocationRelativeTo(null);  // Center of the window
        
        // Create a label with the welcome message
        JLabel welcomeLabel = new JLabel("Welcome Dr." + doctorName, SwingConstants.CENTER);
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 20));  // text style 
        doctorWelcomeFrame.add(welcomeLabel); // add label to the window at the top of the window
        doctorWelcomeFrame.setVisible(true); // Make the window visible
        
    }


    /**** Method to enter patients info by nurse ****/
    private static void openNameInput() {
        JFrame nameFrame = new JFrame("Existence Verification");
        nameFrame.setSize(500, 300);
        JPanel namePanel = new JPanel(new GridLayout(4, 1));  // Increased row count for an extra message

        JLabel firstNameLabel = new JLabel("Enter patient's First Name:");
        JTextField firstNameField = new JTextField();
        JLabel lastNameLabel = new JLabel("Enter patient's Last Name:");
        JTextField lastNameField = new JTextField();
        JButton submitButton = new JButton("Submit");
        JLabel resultLabel = new JLabel("");  // Label to display the result (exists or not)

        namePanel.add(firstNameLabel);
        namePanel.add(firstNameField);
        namePanel.add(lastNameLabel);
        namePanel.add(lastNameField);
        namePanel.add(submitButton);
        namePanel.add(resultLabel);

        nameFrame.add(namePanel);
        nameFrame.setVisible(true);

        // Action listener for the submit button
        submitButton.addActionListener(e -> {
            String firstName = firstNameField.getText().trim();  // trim() removes whitespace from the string 
            String lastName = lastNameField.getText().trim();

            if (firstName.isEmpty() || lastName.isEmpty()) {
                resultLabel.setText("Please enter both First and Last names.");
                resultLabel.setForeground(Color.RED);
                return;
            }

            boolean patientFound = false;
            
            for (Patient tempPatient : patientRecords) {
                if (tempPatient.getName().equalsIgnoreCase(firstName) && tempPatient.getLastName().equalsIgnoreCase(lastName)) {
                    //if existant patient -> display his infos
                    handleFoundPatient(tempPatient);
                    resultLabel.setText("Patient found!");
                    resultLabel.setForeground(Color.GREEN);
                    patientFound = true;
                    break;
                }
            }

            if (!patientFound) {
                //if patient not found -> display his infos
                resultLabel.setText("Patient not found. Please enter details.");
                resultLabel.setForeground(Color.RED);
                collectPatientDetails(firstName, lastName);
            }
        });
    }


            
    //**** Method to output registered patient details******//
    private static void handleFoundPatient(Patient patient) {
        // display patient information
        JFrame patientInfoFrame = new JFrame("Patient Details");
        patientInfoFrame.setSize(400, 300);
        patientInfoFrame.setLayout(new BorderLayout());
        
        // Create a text area to display patient details
        JTextArea patientInfoArea = new JTextArea(patient.toString());
        patientInfoArea.setEditable(false);
        patientInfoFrame.add(new JScrollPane(patientInfoArea), BorderLayout.CENTER);  //JScrollPane: Wraps the JTextArea with scrollbars.
        
        //button panel
        JPanel buttonPanel = new JPanel();
        JButton addAppointmentButton = new JButton("Add New Appointment");
        JButton closeButton = new JButton("Close");
        buttonPanel.add(addAppointmentButton);
        buttonPanel.add(closeButton);

        patientInfoFrame.add(buttonPanel, BorderLayout.SOUTH);
        
        //Display  frame
        patientInfoFrame.setLocationRelativeTo(null);
        patientInfoFrame.setVisible(true);
        
        // Action listener for the "Add New Appointment" button
        addAppointmentButton.addActionListener(e -> {
            openChooseDoctor();
            patientInfoFrame.dispose();
        });
    
        // Action listener for the "Close" button
        closeButton.addActionListener(e -> patientInfoFrame.dispose());
    }
    
        

    //**** Method to collect additional patient details******//
    private static void collectPatientDetails(String firstName, String lastName) {
        JFrame detailsFrame = new JFrame("Enter Patient Details");
        detailsFrame.setSize(400, 400); 
        detailsFrame.setLocationRelativeTo(null);
        JPanel detailspanel = new JPanel(new GridLayout(6, 2));
    
        // Pre-fill name and last name fields
        JTextField nameField = new JTextField(firstName);
        JTextField lastnameField = new JTextField(lastName);
    
        JTextField birthdayField = new JTextField();
        JTextField phoneNumberField = new JTextField();
    
        detailspanel.add(new JLabel("First Name:"));
        detailspanel.add(nameField);
        detailspanel.add(new JLabel("Last Name:"));
        detailspanel.add(lastnameField);
        detailspanel.add(new JLabel("Date of Birth:"));
        detailspanel.add(birthdayField);
        detailspanel.add(new JLabel("Phone Number:"));
        detailspanel.add(phoneNumberField);
    
        JButton submitButton = new JButton("Submit");
        detailspanel.add(submitButton);
    
        detailsFrame.add(detailspanel);
        detailsFrame.setVisible(true);
    
        // Action listener for the submit button - create a new patient record
        submitButton.addActionListener(e -> {
            try {
                Random rand = new Random();
                int id = rand.nextInt(1000);  // Random patient ID
    
                // Collect details from the text fields
                String phoneNumber = phoneNumberField.getText();
                String name = nameField.getText();
                String lastname = lastnameField.getText();
                String dateOfBirth = birthdayField.getText();
    
                // Create new Patient object +  add it to the list
                Patient newPatient = new Patient(id, name, lastname, phoneNumber, dateOfBirth, "not yet", "none");
                patientRecords.add(newPatient);
    
                // Confirmation message - open doctor selection
                JOptionPane.showMessageDialog(detailsFrame, 
                "Patient registered successfully!\nID: " + id, 
                "Registration Success", JOptionPane.INFORMATION_MESSAGE);
            
              openChooseDoctor(); 
              detailsFrame.dispose();
            } catch (NumberFormatException ex) {
                // If the input for phone number is invalid, show an error message
                JOptionPane.showMessageDialog(detailsFrame, "Please enter a valid phone number.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
    }
    

    //**** Method to open "Choose Doctor" window ******//
    private static void openChooseDoctor() {
        JFrame doctorFrame = new JFrame("Choose Doctor");
        doctorFrame.setSize(400, 600);
        doctorFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel doctorPanel = new JPanel(new GridLayout(4, 1));

        JLabel chooseLabel = new JLabel("Which doctor would you like to add the visit to?");
        JButton doctor1Button = new JButton(defaultDoctor1.getName());
        JButton doctor2Button = new JButton(defaultDoctor2.getName());
       
        doctorPanel.add(chooseLabel);
        doctorPanel.add(doctor1Button);
        doctorPanel.add(doctor2Button);
        
        doctorFrame.add(doctorPanel);
        doctorFrame.setVisible(true);
        


        // action listeners to each button
        doctor1Button.addActionListener(e -> {
            JOptionPane.showMessageDialog(doctorFrame, "You have chosen Dr. " + defaultDoctor1.getName());
            //openDoctor1Program();
            //doctorFrame.dispose(); 
        });
    
        doctor2Button.addActionListener(e -> {
            JOptionPane.showMessageDialog(doctorFrame, "You have chosen Dr. " + defaultDoctor2.getName());
            //openDoctor2Program();
            //doctorFrame.dispose();
        });
    }
    



    // Method to find a patient by their email address
  /*  private static Patient findPatientByNumber(String email) {
        for (Patient patient : patientRecords) {
            if (patient.getPhoneNumber().equals(email)) {
                return patient;  // Return the patient if found
            }
        }
        return null;  // Return null if no patient is found with the given email
    }*/

    // Method to display the patient's information
    //private static void displayPatientObservation(Patient patient) {
       // String previousObservations = patient.getPreviousObservations();
        //if (previousObservations.isEmpty()) {
            //previousObservations = "No previous observations available.";
        //}
        // Show the patient's information in a dialog
       // JOptionPane.showMessageDialog(null, patient.toString() + "\nPrevious Observations: " + previousObservations);
    //}

    // Method to open the window where the patient can select their medicine type
    /*private static void openMedicineTypeSelection() {
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
    }*/
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







            // Method to open the patient login screen where patients can log in or sign up
    /*private static void openPatientLoginScreen() {
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
    } */
