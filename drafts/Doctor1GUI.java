// NOUVELLE VERSION QUI CONSISTE A AVOIR UN JSplitPane POUR LA GESTION DES RDV 
// QUAND CE CODE S'EXECUTE IL DONNE LES OUTPUTS SUIVANTS : UNE FENETRE JSplitPane DEVISER EN 2 UNE PARTIE POUR LE CALENDAR ET L'AUTRE POUR LES DETAILS DES RDV
// UNE AUTRE FENETRE POUR LE CALENDRIER DU MEDECIN 1 

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSplitPane;

public class Doctor1GUI extends CalendarGUI {
    public Doctor1GUI(CalendarINFO calendarInfo) {
        super(calendarInfo, "Doctor 1 Calendar");
        createDoctorGUI("Appointment Management"); // The title of the JSplitPane
    }

    private void createDoctorGUI(String title) {
        JFrame frame = new JFrame(title);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        
        // Left part : for the doctor calendar ( currently empty )
        JPanel calendarPanel = new JPanel();
        calendarPanel.add(new JLabel("Doctor's Calendar 1"));
        
        // Right part: for appointment details ( currently empty )
        JPanel detailsPanel = new JPanel();
        detailsPanel.add(new JLabel("Appointment details"));
        
        // JSplitPane creation
        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, calendarPanel, detailsPanel);
        splitPane.setDividerLocation(350); // Initial position of the separator between the left and right part
        
        frame.add(splitPane);
        frame.setVisible(true); // Make the JSplitPane visible
    }
}
