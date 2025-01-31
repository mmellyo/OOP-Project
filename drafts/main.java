import java.time.LocalDate; // Class used to manipulate dates without times
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

// Main class declaration
public class Main { 
    // public: Indicates that the class is accessible to everyone
    // class main: the entry point of the program.
    private static final Doctor1Calendar defaultDoctor1 = new Doctor1Calendar(LocalDate.now().getYear(), LocalDate.now().getMonthValue());
    private static final Doctor2Calendar defaultDoctor2 = new Doctor2Calendar(LocalDate.now().getYear(), LocalDate.now().getMonthValue());
    public static void main(String[] args) {
             // Lancement de la fenêtre "Choose Doctor"
             openChooseDoctor();
            }
        
            // **** Method to open "Choose Doctor" window **** //  (Melly's Part )
            private static void openChooseDoctor() {
                JFrame doctorFrame = new JFrame("Choose Doctor");
                doctorFrame.setSize(400, 600);
                doctorFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                JPanel doctorPanel = new JPanel(new GridLayout(4, 1));
        
                JLabel chooseLabel = new JLabel("Which doctor would you like to add the visit to?");
                JButton doctor1Button = new JButton("Doctor 1: Obstetrician");
                JButton doctor2Button = new JButton("Doctor 2: Gynecologist");
        
                doctorPanel.add(chooseLabel);
                doctorPanel.add(doctor1Button);
                doctorPanel.add(doctor2Button);
        
                doctorFrame.add(doctorPanel);
                doctorFrame.setVisible(true);
                LocalDate today = LocalDate.now(); // Date actuelle
        
                // Action listener for Doctor 1
                doctor1Button.addActionListener(e -> {
                    JOptionPane.showMessageDialog(doctorFrame, "You have chosen Doctor 1: Obstetrician");
                    Doctor1Calendar doctor1Calendar = new Doctor1Calendar(today.getYear(), today.getMonthValue());
                    new Doctor1GUI(doctor1Calendar); // Création de l'interface spécifique pour le Docteur 1
                    openDoctor1Calendar(doctor1Calendar);
                     // Fermer la fenêtre "Choose Doctor"
                });
        
                // Action listener for Doctor 2
                doctor2Button.addActionListener(e -> {
                    JOptionPane.showMessageDialog(doctorFrame, "You have chosen Doctor 2: Gynecologist");
                    Doctor2Calendar doctor2Calendar = new Doctor2Calendar(today.getYear(), today.getMonthValue());
                    new Doctor2GUI(doctor2Calendar); // Création de l'interface spécifique pour le Docteur 2
                    openDoctor2Calendar(doctor2Calendar);
                    doctorFrame.dispose(); // Fermer la fenêtre "Choose Doctor"
                });
            }
            // **** End of Melly's part **** //
        LocalDate currentDate = LocalDate.now(); // Get the current date and store it in a variable named currentDate
        // Method to open doctor 1 calendar 
        private static void openDoctor1Calendar(Doctor1Calendar doctor1Calendar) {
            //  Display Docteur's 1 GUI Calendar
            new Doctor1GUI(doctor1Calendar);
        } 
        
        // Method to open doctor 2 calendar 
        private static void openDoctor2Calendar(Doctor2Calendar doctor2Calendar) {
            // Display Docteur's 2 GUI Calendar
            new Doctor2GUI(doctor2Calendar);
        }
}
