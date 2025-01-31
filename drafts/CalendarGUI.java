//********* OLD VERSION *********//
//********* OLD VERSION *********//
//********* OLD VERSION *********//





// Import to create a graphical interface with swing (Window, Button, events)
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.LocalDate;

// Declaration of CalendarGUI class that contains the graphique interfce for my Medical Calendar
public class CalendarGUI {
    private CalendarINFO calendarInfo; // Instance of CalendarINFO which contains current calendar informations
    private JLabel monthYearLabel; // Label to display the current month and year
    private JPanel calendarPanel; // Panel where the months and days will be displayed

    // Constructor which initializes the interface by receiving an instance of CalendarINFO
    public CalendarGUI(CalendarINFO calendarInfo) {
        this.calendarInfo = calendarInfo;
        createGUI();
    }

    private void createGUI() {
        // Main Window
        JFrame frame = new JFrame("Medical Calendar"); // Creation of the JFrame window with a title "Medical Calendar"
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Closing the app when exiting
        frame.setSize(400, 400); // Window size (400x400 pixels)
        frame.setLayout(new BorderLayout()); // Using a Layout to Organize Components

        // Top panel (navigation)
        JPanel topPanel = new JPanel(new BorderLayout());
        monthYearLabel = new JLabel("", JLabel.CENTER); // Creating a label
        frame.add(topPanel, BorderLayout.NORTH); // Adding this label to the top of the window
        topPanel.add(monthYearLabel, BorderLayout.CENTER);
        
        JButton prevButton = new JButton("<"); // Creating a “previous” button
        prevButton.addActionListener(e -> changeMonth(-1)); // (-1) to go to the previous month (current month-1)
        topPanel.add(prevButton, BorderLayout.WEST); // Button is placed on the left

        JButton nextButton = new JButton(">"); // Creating a “next” button
        nextButton.addActionListener(e -> changeMonth(1)); // (1) to move to the next month (current month+1)
        topPanel.add(nextButton, BorderLayout.EAST); // Button is placed on the right

        // Central  panel (days)
        calendarPanel = new JPanel(new GridLayout(0, 7)); // Creation of a panel with a 7 column grid for the days of the week
        frame.add(calendarPanel, BorderLayout.CENTER); // Adding this panel to the center of the window

        updateCalendar(); // Update calendar display
        frame.setVisible(true); // Make the window visible on the screen
    }

    // Method that removes all components previously displayed in the calendar panel
    private void updateCalendar() {
        calendarPanel.removeAll();
       
        // Update the title
        String[] months = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
        monthYearLabel.setText(months[calendarInfo.getMonth() - 1] + " " + calendarInfo.getYear());

        // Add days of the week
        String[] daysOfWeek = {"Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat"};
        for (String day : daysOfWeek) {
            JLabel label = new JLabel(day, JLabel.CENTER);
            label.setFont(new Font("Arial", Font.BOLD, 12)); // Writing style and size
            calendarPanel.add(label);
        }

        // Empty spaces before the first day of the month
        int firstDayOfWeek = calendarInfo.getFirstDayOfWeek(); // Get the first day of the week
        int offset = (firstDayOfWeek == 7) ? 0 : firstDayOfWeek; // Adjust so that Sunday is the first
        for (int i = 0; i < offset; i++) {
            calendarPanel.add(new JLabel(""));
        }

        // Add the days of the month
        int daysInMonth = calendarInfo.getDayInMonth(); // Get the number of days in the month
        for (int day = 1; day <= daysInMonth; day++) {
            JLabel label = new JLabel(String.valueOf(day), JLabel.CENTER);
            label.setFont(new Font("Arial", Font.PLAIN, 12));

            // Creat new locale variable for events  
            final int currentDay = day; 

            //verify if days are blocked
            if (calendarInfo.isDayInPast(day)){
                label.setForeground(Color.GRAY); // Days in past are colored in gray
            }else if (calendarInfo.isDayBlocked(day)) {
                label.setForeground(Color.RED);// Blocked days are colored in red
            }
            label.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e){
                    if (calendarInfo.isDayInPast(currentDay)) {
                        // Error message for past days
                        JOptionPane.showMessageDialog(null, "Unable to add an appointment to a day that has already passed!", "ERROR", JOptionPane.ERROR_MESSAGE);
                    } else if (calendarInfo.isDayBlocked(currentDay)) {
                        // Message for blocked days
                        showBlockedMessage();
                    } else {
                        // Add an event for a valid day
                        showAddEventMessage(currentDay);
                    } 
                }
            });
            calendarPanel.add(label);
        }

        // Add the boxes for the following month if necessary
        int totalCells = offset + daysInMonth;
        for (int i = totalCells; i < 42; i++) { // 42 = 6 lines of 7 days
            calendarPanel.add(new JLabel(""));
        }

        calendarPanel.revalidate();
        calendarPanel.repaint();
    }

    private void showBlockedMessage(){
        // Print an error message on the window when the user click on a blocked day 
        JOptionPane.showMessageDialog(null, " The Medical Center 'bloom' is closed", "It's Friday / Holidays", JOptionPane.WARNING_MESSAGE);
    }

    private void showAddEventMessage(int date){
        JOptionPane.showMessageDialog(null, "Add Appointment for :" + date , "Add Appointment", JOptionPane.INFORMATION_MESSAGE);
    }

    private void changeMonth(int delta) {
        int newMonth = calendarInfo.getMonth() + delta;
        int newYear = calendarInfo.getYear();
        
 
        if (newMonth < 1) {
            // Valid condition we move to the previous year 
            newMonth = 12;
            newYear--;
        } else if (newMonth > 12) {
            // Valid condition we move to the next year 
            newMonth = 1;
            newYear++;
        }

        int currentYear = LocalDate.now().getYear();
        int currentMonth = LocalDate.now().getMonthValue();

        // Validation to prevent access to past months
        LocalDate currentDate = LocalDate.now();
        if (newYear < currentDate.getYear() || (newYear == currentDate.getYear() && newMonth < currentDate.getMonthValue())) {
            JOptionPane.showMessageDialog(null, "Unable to navigate to a past month!", "ERROR!", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Apply the new values ​​if they are valid
        calendarInfo.setYear(newYear); // Update year first
        calendarInfo.setMonth(newMonth); // Then update the month
        updateCalendar();
    }
}




