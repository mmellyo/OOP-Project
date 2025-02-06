// NOUVELLE VERSION AVEC DES IDEES AMELIORER ET SIMPLE POUR L'UTILISATEUR

import java.time.LocalDate; // Class used to manipulate dates without times
import javax.swing.*;
import java.awt.*;

// Main class declaration
public class Main { 
    

    private static final Doctor1Calendar defaultDoctor1 = new Doctor1Calendar(LocalDate.now().getYear(), LocalDate.now().getMonthValue());
    private static final Doctor2Calendar defaultDoctor2 = new Doctor2Calendar(LocalDate.now().getYear(), LocalDate.now().getMonthValue());
    public static void main(String[] args) {

             // Launching the “Choose Doctor” window
             openChooseDoctor();
            }
        
            // **** Method to open "Choose Doctor" window **** //  (Melly's Part )
            private static void openChooseDoctor() {
                JFrame doctorFrame = new JFrame("Choose Doctor");
                doctorFrame.setSize(400, 600);
                doctorFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                JPanel doctorPanel = new JPanel(new GridLayout(4, 1));
        
                JLabel chooseLabel = new JLabel("Which doctor would you like to add the visit to?");
                JButton doctor1Button = new JButton("Doctor 1: Obstetrician"); // I add some modification doctor speciality
                JButton doctor2Button = new JButton("Doctor 2: Gynecologist"); // I add some modification doctor speciality (if you don't like it tell me!)
        
                doctorPanel.add(chooseLabel);
                doctorPanel.add(doctor1Button);
                doctorPanel.add(doctor2Button);
        
                doctorFrame.add(doctorPanel);
                doctorFrame.setVisible(true);
        
                // Action listener for Doctor 1
                doctor1Button.addActionListener(e -> {
                    JOptionPane.showMessageDialog(doctorFrame, "You have chosen Doctor 1: Obstetrician");
                    openDoctor1Calendar(); // Open doctor 1 Calendar
                    doctorFrame.dispose(); // Close "Choose Doctor" window
                });
        
                // Action listener for Doctor 2
                doctor2Button.addActionListener(e -> {
                    JOptionPane.showMessageDialog(doctorFrame, "You have chosen Doctor 2: Gynecologist");
                    openDoctor2Calendar(); // Open doctor 2 Calendar
                    doctorFrame.dispose(); // Close "Choose Doctor" window
                });
            }
            // **** End of Melly's part **** //

        LocalDate currentDate = LocalDate.now(); // Get the current date and store it in a variable named currentDate

        // Method to open doctor 1 calendar 
        private static void openDoctor1Calendar() {
            //  Display Docteur's 1 GUI Calendar
            new Doctor1GUI(defaultDoctor1);
        } 
        
        // Method to open doctor 2 calendar 
        private static void openDoctor2Calendar() {
            // Display Docteur's 2 GUI Calendar
            new Doctor2GUI(defaultDoctor2);
        }
}
