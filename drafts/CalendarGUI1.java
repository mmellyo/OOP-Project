// J'AI OUBLIE CE QUE J'AI MODIFIE

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.LocalDate;

public class CalendarGUI extends JPanel {
    private CalendarINFO calendarInfo;
    private JLabel monthYearLabel;
    private JPanel calendarPanel;
    private Doctor1GUI doctorGUI;

    public void setDoctorGUI(Doctor1GUI doctorGUI) {
        this.doctorGUI = doctorGUI;
    }

    public CalendarGUI(CalendarINFO calendarInfo) {
        this.calendarInfo = calendarInfo;
        createGUI();
    }

    

    private void createGUI() {
        setLayout(new BorderLayout());

        JPanel topPanel = new JPanel(new BorderLayout());
        monthYearLabel = new JLabel("", JLabel.CENTER);
        topPanel.add(monthYearLabel, BorderLayout.CENTER);

        JButton prevButton = new JButton("<");
        prevButton.addActionListener(e -> changeMonth(-1));
        topPanel.add(prevButton, BorderLayout.WEST);

        JButton nextButton = new JButton(">");
        nextButton.addActionListener(e -> changeMonth(1));
        topPanel.add(nextButton, BorderLayout.EAST);

        add(topPanel, BorderLayout.NORTH);

        calendarPanel = new JPanel(new GridLayout(0, 7));
        add(calendarPanel, BorderLayout.CENTER);

        updateCalendar();
    }

    private void updateCalendar() {
        calendarPanel.removeAll();

        String[] months = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
        monthYearLabel.setText(months[calendarInfo.getMonth() - 1] + " " + calendarInfo.getYear());

        String[] daysOfWeek = {"Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat"};
        for (String day : daysOfWeek) {
            JLabel label = new JLabel(day, JLabel.CENTER);
            label.setFont(new Font("Arial", Font.BOLD, 12));
            calendarPanel.add(label);
        }

        int firstDayOfWeek = calendarInfo.getFirstDayOfWeek();
        int offset = (firstDayOfWeek == 7) ? 0 : firstDayOfWeek;
        for (int i = 0; i < offset; i++) {
            calendarPanel.add(new JLabel(""));
        }

        int daysInMonth = calendarInfo.getDayInMonth();
        for (int day = 1; day <= daysInMonth; day++) {
            JLabel label = new JLabel(String.valueOf(day), JLabel.CENTER);
            label.setFont(new Font("Arial", Font.PLAIN, 12));

            if (!calendarInfo.isDayBlocked(day)) {
                label.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        String selectedDate =  monthYearLabel.getText();
                        doctorGUI.showAppointmentTable(selectedDate);
                    }
                });
            }

            if (calendarInfo.isDayInPast(day)) {
                label.setForeground(Color.GRAY);
            } else if (calendarInfo.isDayBlocked(day)) {
                label.setForeground(Color.RED);
            }

            final int currentDay = day;
            label.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    if (calendarInfo.isDayInPast(currentDay)) {
                        JOptionPane.showMessageDialog(null, "Impossible d'ajouter un rendez-vous à une date passée.", "Erreur", JOptionPane.ERROR_MESSAGE);
                    } else if (calendarInfo.isDayBlocked(currentDay)) {
                        JOptionPane.showMessageDialog(null, "Ce jour est bloqué.", "Jour bloqué", JOptionPane.WARNING_MESSAGE);
                    } else {
                        String selectedDate = currentDay + " " + monthYearLabel.getText();
                        JOptionPane.showMessageDialog(null, "Ajouter un rendez-vous pour le " + selectedDate, "Nouveau rendez-vous", JOptionPane.INFORMATION_MESSAGE);
                        
                        
                    }
                }
            });

            calendarPanel.add(label);
        }

        int totalCells = offset + daysInMonth;
        for (int i = totalCells; i < 42; i++) {
            calendarPanel.add(new JLabel(""));
        }

        calendarPanel.revalidate();
        calendarPanel.repaint();
    }

    private void changeMonth(int delta) {
        int newMonth = calendarInfo.getMonth() + delta;
        int newYear = calendarInfo.getYear();

        if (newMonth < 1) {
            newMonth = 12;
            newYear--;
        } else if (newMonth > 12) {
            newMonth = 1;
            newYear++;
        }

        LocalDate currentDate = LocalDate.now();
        if (newYear < currentDate.getYear() || (newYear == currentDate.getYear() && newMonth < currentDate.getMonthValue())) {
            JOptionPane.showMessageDialog(null, "Impossible de naviguer vers un mois passé !", "Erreur", JOptionPane.ERROR_MESSAGE);
            return;
        }

        calendarInfo.setYear(newYear);
        calendarInfo.setMonth(newMonth);
        updateCalendar();
    }

    private void onDaySelected(String selectedDate) {
        if (isValidDay(selectedDate)) { // Vérifie si le jour est valide
            doctorGUI.showAppointmentTable(selectedDate);
        }
    }

    private boolean isValidDay(String selectedDate) {
        // Ajoute ici la logique pour vérifier si le jour est valide (non bloqué)
        return true; // À remplacer par la vraie vérification
    }

}
