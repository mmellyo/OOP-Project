import java.awt.*;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;


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
            "0550607080",
            "2005-03-11",
            "default observation",
            "default prescription",
            "default med"
            );

        patientRecords.add(defaultPatient);

        // Save the default patient to a file
         savePatientToFile(defaultPatient);

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
           
            if(tempID == nurse.getId()) {
                mainFrame.dispose();
                openNameInput();

            } else if (tempID == defaultDoctor1.getId()){
                mainFrame.dispose();
                openDoctorWelcome(defaultDoctor1.getName()); // Open the welcome window for the doctor 
                
            } else if (tempID == defaultDoctor2.getId()) {
                mainFrame.dispose();
                openDoctorWelcome(defaultDoctor2.getName());
                
            } else {
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
    
        // Créer un bouton de déconnexion (Log Out)
        JButton logoutButton = new JButton("Log Out");
        logoutButton.addActionListener(e -> {
            doctorWelcomeFrame.dispose();
        });
    
        // Créer le bouton "See Patient"
        JButton seePatientButton = new JButton("See Patient");
        seePatientButton.addActionListener(e -> openPatientWindow());
    
        // Créer un panel pour les boutons
        JPanel buttonPanel = new JPanel();
        logoutButton.setPreferredSize(new Dimension(150, 50)); 
        seePatientButton.setPreferredSize(new Dimension(150, 50));
    
        buttonPanel.add(logoutButton); // Ajouter le bouton à ce panel
        buttonPanel.add(seePatientButton);  
    
        // Ajouter l'étiquette au centre de la fenêtre
        doctorWelcomeFrame.add(welcomeLabel, BorderLayout.CENTER);
    
        // Ajouter le panel contenant les boutons au bas de la fenêtre
        doctorWelcomeFrame.add(buttonPanel, BorderLayout.SOUTH);
    
        // Afficher la fenêtre de bienvenue du médecin
        doctorWelcomeFrame.setVisible(true);  
    }
     
    /**********      Methode open see patient window         *********/
    
    private static void openPatientWindow() {
        JFrame patientFrame = new JFrame("Today's Patients Informations");
        patientFrame.setSize(800, 400);  // Taille de la fenêtre
        patientFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);  // Fermer uniquement cette fenêtre
        patientFrame.setLocationRelativeTo(null);  // Centrer la fenêtre
    
        // Définir les noms des colonnes
        String[] columnNames = {"ID", "Name", "First Name", "Phone Number", "Date of Birth", "Observation", "Diagnostic", "Prescription"};
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);
        JTable table = new JTable(model);
    
        // Read patient files and populate the table
        File folder = new File("."); // Current directory
        File[] listOfFiles = folder.listFiles((dir, name) -> name.startsWith("patient_") && name.endsWith(".txt"));
    
        if (listOfFiles != null) {
            for (File file : listOfFiles) {
                String fileName = file.getName();
                int patientId = Integer.parseInt(fileName.substring(8, fileName.length() - 4)); // Extract patient ID from filename
                Patient patient = readPatientFromFile(patientId);
                if (patient != null) {
                    model.addRow(new Object[]{
                        patient.getId(),
                        patient.getName(),
                        patient.getLastName(),
                        patient.getPhoneNumber(),
                        patient.getDateOfBirth(),
                        patient.getObservation(),
                        patient.getDiagnostic(),
                        patient.getPrescription()
                    });
                }
            }
        }
    
        // Ajouter des renderers et editors pour les colonnes contenant des boutons
        for (int i = 4; i < columnNames.length; i++) {
            table.getColumnModel().getColumn(i).setCellRenderer(new ButtonRenderer());
            table.getColumnModel().getColumn(i).setCellEditor(new ButtonEditor(new JTextField()));
        }
    
        // Ajouter le tableau dans un JScrollPane
        JScrollPane scrollPane = new JScrollPane(table);
        patientFrame.add(scrollPane, BorderLayout.CENTER);
    
        // Afficher la fenêtre
        patientFrame.setVisible(true);
    }
    // Classe pour rendre un bouton dans une cellule
    static class ButtonRenderer extends JButton implements TableCellRenderer {
    public ButtonRenderer() {
        setOpaque(true);
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        setText("Edit");

        //setText((value == null) ? "Add" : value.toString());
        return this;
    }
  }

 // Classe pour éditer un bouton dans une cellule
 static class ButtonEditor extends DefaultCellEditor {
    protected JButton button;
    private String label;

    public ButtonEditor(JTextField textField) {
        super(textField);
        button = new JButton();
        button.setOpaque(true);

        // Action listener pour détecter le clic sur "Add" dans la colonne Prescription
        button.addActionListener(e -> {
            JTable table = (JTable) SwingUtilities.getAncestorOfClass(JTable.class, button);
            if (table != null) {
                int row = table.getSelectedRow(); // Obtenir la ligne sélectionnée
                int column = table.getSelectedColumn(); // Obtenir la colonne sélectionnée
        
                // Affichage pdqour déboguer
                System.out.println("Clic détecté sur la ligne " + row + ", colonne " + column);
        
                // Vérification des colonnes et des actions associées
                if (column == 7) { // Si c'est la colonne "Prescription" (colonne 7 ici)
                    openPrescriptionWindow((int) table.getValueAt(row, 0)); // Ouvre la fenêtre d'ordonnance
                
                } else if (column == 5) {
                    openObservationOrDiagnosticWindow("Observation", (int) table.getValueAt(row, 0));// Ouvre la fenetre Observation
                
                } else if (column == 6) {
                    openObservationOrDiagnosticWindow("Diagnostic", (int) table.getValueAt(row, 0));  // Ouvre la fenêtre Diagnostic
                
                } else if (column == 9) {
                    openCertificateWindow();  // Ouvre la fenêtre certtificat
                }
            }
        });
    } 

    @Override
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
        label = "Edit";

        //label = (value == null) ? "Add" : value.toString();
        button.setText(label);
        return button;
    }

    @Override
    public Object getCellEditorValue() {
        return label;
    }
}

    
  /******** Method to display the prescription window ********/


// Method to open the prescription window with patient details
private static void openPrescriptionWindow(int patientId) {
    // Créer la fenêtre d'ordonnance
    JFrame prescriptionFrame = new JFrame("Prescription");
    prescriptionFrame.setSize(400, 500); // Taille de la fenêtre
    prescriptionFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Fermer la fenêtre sans fermer l'application
    prescriptionFrame.setLayout(new BorderLayout());

    // ** Panel supérieur avec titre "Ordonnance" **
    JLabel titleLabel = new JLabel("Prescription", SwingConstants.CENTER);
    titleLabel.setFont(new Font("Arial", Font.BOLD, 20)); // Police en gras
    prescriptionFrame.add(titleLabel, BorderLayout.NORTH);

    // ** Panel central avec le formulaire d'ordonnance **
    JPanel formPanel = new JPanel();
    formPanel.setLayout(new GridLayout(5, 2, 10, 10)); // Grille 5 lignes, 2 colonnes
    formPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

    JLabel doctorLabel = new JLabel("Doctor:");
    JTextField doctorField = new JTextField("Dr.... "); // Nom par défaut du médecin
    doctorField.setEditable(false); // Le champ est non modifiable

    JLabel dateLabel = new JLabel("Date:");
    JTextField dateField = new JTextField();
    dateField.setEditable(false); // Le champ est non modifiable

    // Set today's date in the date field
    dateField.setText(new java.text.SimpleDateFormat("yyyy-MM-dd").format(new java.util.Date()));

    JLabel patientLabel = new JLabel("Patient:");
    JTextField patientField = new JTextField();
    patientField.setEditable(false); // Le champ est non modifiable

    JLabel bdLabel = new JLabel("date of birth:");
    JTextField bdField = new JTextField();
    bdField.setEditable(false); // Le champ est non modifiable

    JLabel medicationLabel = new JLabel("Medications:");
    JTextArea medicationArea = new JTextArea(5, 20); // Zone de texte pour les médicaments
    medicationArea.setLineWrap(true);
    medicationArea.setWrapStyleWord(true);

    // Load patient details
    Patient patient = readPatientFromFile(patientId);
    if (patient != null) {
        patientField.setText(patient.getName() + " " + patient.getLastName());
        bdField.setText(patient.getDateOfBirth()); 
        medicationArea.setText(patient.getPrescription()); // Load existing prescription if available
    }

    // Ajouter les champs au formulaire
    formPanel.add(doctorLabel);
    formPanel.add(doctorField);
    formPanel.add(dateLabel);
    formPanel.add(dateField);
    formPanel.add(patientLabel);
    formPanel.add(patientField);
    formPanel.add(bdLabel);
    formPanel.add(bdField);
    formPanel.add(medicationLabel);
    formPanel.add(new JScrollPane(medicationArea));

    prescriptionFrame.add(formPanel, BorderLayout.CENTER);

    // ** Panel inférieur avec boutons pour enregistrer ou fermer **
    JPanel buttonPanel = new JPanel();
    JButton saveButton = new JButton("Enregistrer");
    JButton closeButton = new JButton("Fermer");

    saveButton.addActionListener(e -> {
        if (patient != null) {
            patient.setPrescription(medicationArea.getText());
            savePatientToFile(patient);
            JOptionPane.showMessageDialog(prescriptionFrame, "prescription saved!");
        }
    });
    closeButton.addActionListener(e -> prescriptionFrame.dispose());

    buttonPanel.add(saveButton);
    buttonPanel.add(closeButton);

    prescriptionFrame.add(buttonPanel, BorderLayout.SOUTH);

    // Afficher la fenêtre au centre de l'écran
    prescriptionFrame.setLocationRelativeTo(null);
    prescriptionFrame.setVisible(true);
}

/******* Méthode pour ouvrir la fenêtre Observation ou Diagnostic ******/


private static void openObservationOrDiagnosticWindow(String title, int patientId) {
    JFrame window = new JFrame(title);
    window.setSize(400, 300);
    window.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    window.setLayout(new BorderLayout());

    // Titre en gras
    JPanel titlePanel = new JPanel();
    JLabel titleLabel = new JLabel(title);
    titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
    titlePanel.add(titleLabel);
    window.add(titlePanel, BorderLayout.NORTH);

    // Zone de texte pour entrer l'observation ou diagnostic
    JPanel inputPanel = new JPanel();
    inputPanel.setLayout(new BoxLayout(inputPanel, BoxLayout.Y_AXIS));
    inputPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

    JTextArea inputArea = new JTextArea(5, 20);
    JScrollPane scrollPane = new JScrollPane(inputArea);
    inputPanel.add(scrollPane);
    window.add(inputPanel, BorderLayout.CENTER);

    // Load existing observation or diagnostic if available
    Patient patient = readPatientFromFile(patientId);
    if (patient != null) {
        if (title.equals("Observation")) {
            inputArea.setText(patient.getObservation());
        } else if (title.equals("Diagnostic")) {
            inputArea.setText(patient.getDiagnostic());
        }
    }

    // Boutons Enregistrer et Fermer
    JPanel buttonPanel = new JPanel();
    JButton saveButton = new JButton("Enregistrer");
    JButton closeButton = new JButton("Fermer");

    saveButton.addActionListener(e -> {
        if (patient != null) {
            if (title.equals("Observation")) {
                patient.setObservation(inputArea.getText());
            } else if (title.equals("Diagnostic")) {
                patient.setDiagnostic(inputArea.getText());
            }
            savePatientToFile(patient);
            JOptionPane.showMessageDialog(window, "Détails enregistrés!");
        }
    });

    closeButton.addActionListener(e -> window.dispose());

    buttonPanel.add(saveButton);
    buttonPanel.add(closeButton);
    window.add(buttonPanel, BorderLayout.SOUTH);

    window.setLocationRelativeTo(null);  // Centrer la fenêtre
    window.setVisible(true);
}


/******* Méthode pour ouvrir la fenêtre de Certificate *******/ 
    private static void openCertificateWindow() {
       
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
                //if patient not found -> enter his infos
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

                // Validate input
                if (name.isEmpty() || lastname.isEmpty() || dateOfBirth.isEmpty() || phoneNumber.isEmpty()) {
                    JOptionPane.showMessageDialog(detailsFrame, "All fields must be filled!", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                // Checks if phone is exactly 10 digits
                if (!phoneNumber.matches("\\d{10}")) { 
                    JOptionPane.showMessageDialog(detailsFrame, "Invalid phone number! Enter 10 digits.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                // Create new Patient object +  add it to the list
                Patient newPatient = new Patient(id, name, lastname, phoneNumber, dateOfBirth, "not yet", "none", "none");
                patientRecords.add(newPatient);

                // Save patient details to a file
                savePatientToFile(newPatient);

                // Confirmation message - open doctor selection
                JOptionPane.showMessageDialog(detailsFrame, 
                "Patient registered successfully!\nID: " + id, 
                "Registration Success", JOptionPane.INFORMATION_MESSAGE);
            
              openChooseDoctor(); 
              //detailsFrame.dispose();
              detailsFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

            } catch (NumberFormatException ex) {
                // If the input for phone number is invalid, show an error message
                JOptionPane.showMessageDialog(detailsFrame, "Please enter a valid phone number.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
    }

    // Method to save patient details to a file
    private static void savePatientToFile(Patient patient) {
        String filename = "patient_" + patient.getId() + ".txt";
        try (PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(filename)))) {
            out.println("ID: " + patient.getId());
            out.println("Name: " + patient.getName());
            out.println("Last Name: " + patient.getLastName());
            out.println("Phone Number: " + patient.getPhoneNumber());
            out.println("Date of Birth: " + patient.getDateOfBirth());
            out.println(" Observations: " + patient.getObservation());
            out.println(" diagnostic: " + patient.getDiagnostic());
            out.println("prescription: " + patient.getPrescription());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Method to read patient details from a file
    private static Patient readPatientFromFile(int patientId) {
        String filename = "patient_" + patientId + ".txt";
        
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            int id = Integer.parseInt(br.readLine().split(": ")[1]);
            String name = br.readLine().split(": ")[1];
            String lastName = br.readLine().split(": ")[1];
            String phoneNumber = br.readLine().split(": ")[1];
            String dateOfBirth = br.readLine().split(": ")[1];
            String Observations = br.readLine().split(": ")[1];
            String diagnostic = br.readLine().split(": ")[1];
            String prescription = br.readLine().split(": ")[1];

            return new Patient(id, name, lastName, phoneNumber, dateOfBirth, Observations, diagnostic, prescription);

        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
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