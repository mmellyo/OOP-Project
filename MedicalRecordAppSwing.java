import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;




//test test to github




public class MedicalRecordAppSwing {

    private static MedicalRecord medicalRecord;

    public static void main(String[] args) {
        // Create a new medical record
        medicalRecord = new MedicalRecord("P123");

        // Set up the frame for the Swing application
        JFrame frame = new JFrame("Medical Record Management");
        frame.setSize(400, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Create a panel to hold the components
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        // Create and add components
        JTextField patientIDField = new JTextField(20);
        patientIDField.setText("P123");
        patientIDField.setEditable(false);

        JTextField allergyField = new JTextField(20);
        allergyField.setToolTipText("Enter allergies separated by commas");

        JTextField surgicalHistoryField = new JTextField(20);
        surgicalHistoryField.setToolTipText("Enter past surgeries separated by commas");

        JTextArea observationArea = new JTextArea(5, 20);
        observationArea.setLineWrap(true);
        observationArea.setWrapStyleWord(true);
        observationArea.setToolTipText("Enter any medical observations");

        JTextField medicationField = new JTextField(20);
        JTextField dosageField = new JTextField(20);
        JTextField durationField = new JTextField(20);

        JButton addButton = new JButton("Add Treatment");
        JButton showSummaryButton = new JButton("Show Medical Summary");

        // Add components to the panel
        panel.add(new JLabel("Patient ID"));
        panel.add(patientIDField);
        panel.add(new JLabel("Allergy Information"));
        panel.add(allergyField);
        panel.add(new JLabel("Surgical History"));
        panel.add(surgicalHistoryField);
        panel.add(new JLabel("Medical Observations"));
        panel.add(new JScrollPane(observationArea));
        panel.add(new JLabel("Treatment Information"));
        panel.add(new JLabel("Medication"));
        panel.add(medicationField);
        panel.add(new JLabel("Dosage"));
        panel.add(dosageField);
        panel.add(new JLabel("Duration (in days)"));
        panel.add(durationField);
        panel.add(addButton);
        panel.add(showSummaryButton);

        // Set up the frame to contain the panel
        frame.add(panel);
        frame.setVisible(true);

        // Add ActionListener for the Add Treatment button
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    // Update allergies and surgical history
                    if (!allergyField.getText().isEmpty()) {
                        medicalRecord.addAllergy(allergyField.getText());
                    }
                    if (!surgicalHistoryField.getText().isEmpty()) {
                        medicalRecord.addSurgicalHistory(surgicalHistoryField.getText());
                    }

                    // Update medical observations
                    if (!observationArea.getText().isEmpty()) {
                        medicalRecord.addObservation(observationArea.getText());
                    }

                    // Add treatment details
                    String medication = medicationField.getText();
                    String dosage = dosageField.getText();
                    int duration = Integer.parseInt(durationField.getText());

                    Treatment treatment = new Treatment(medication, dosage, duration);
                    medicalRecord.addTreatment(treatment);

                    // Clear input fields
                    medicationField.setText("");
                    dosageField.setText("");
                    durationField.setText("");

                    JOptionPane.showMessageDialog(frame, "Treatment added successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);

                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(frame, "Please enter a valid number for duration.", "Input Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // Add ActionListener for the Show Medical Summary button
        showSummaryButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Display the medical summary in a pop-up dialog
                JOptionPane.showMessageDialog(frame, medicalRecord.getMedicalSummary(), "Medical Summary", JOptionPane.INFORMATION_MESSAGE);
            }
        });
    }
}
