// J'AI BCP BCP MODIFIER 
// LA FENETRE EST ASSEZ GRANDE ELLE A LA TAILLE DE MON PC

import javax.swing.*;

public class Doctor1GUI extends JFrame {
    private JSplitPane splitPane;
    private AppointmentTable appointmentTable;
    private JPanel emptyPanel;

    public Doctor1GUI(CalendarINFO calendarInfo) {
        createDoctorGUI("Gestion des Rendez-vous", calendarInfo);
    }

    private void createDoctorGUI(String title, CalendarINFO calendarInfo) {
        setTitle(title);
        setSize(1550, 750);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Assuming CalendarGUI has a method to get the calendar component
        CalendarGUI calendarPanel = new CalendarGUI(calendarInfo);
        calendarPanel.setDoctorGUI(this);

        emptyPanel = new JPanel();
        appointmentTable = new AppointmentTable();

        splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, calendarPanel, emptyPanel);
        splitPane.setDividerLocation(450);

        add(splitPane);
        setVisible(true);
    }

    public void showAppointmentTable(String selectedDate) {
        appointmentTable.updateAppointments(selectedDate);
        splitPane.setRightComponent(appointmentTable.getTablePanel());
    }
}
