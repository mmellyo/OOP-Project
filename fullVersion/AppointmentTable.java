// INTERFACE POUR LES DETAILS DES RENDEZ VOUS SOUS FORME DE TABLEAU 


import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class AppointmentTable {
    private JPanel panel;
    private JTable table;
    private DefaultTableModel tableModel;

    public AppointmentTable() {
        createGUI();
    }

    private void createGUI() {
        panel = new JPanel(new BorderLayout());
        String[] columnNames = {"Time Slot", "Patients Informations", "Actions"};
        tableModel = new DefaultTableModel(columnNames, 0);
        table = new JTable(tableModel);

        // Appliquer le renderer et l'editor pour la colonne "Actions"
        table.getColumnModel().getColumn(2).setCellRenderer(new ButtonRenderer());
        table.getColumnModel().getColumn(2).setCellEditor(new ButtonEditor());

        // Agrandir les lignes et colonnes pour un meilleur affichage
        table.setRowHeight(70);
        table.getColumnModel().getColumn(0).setPreferredWidth(150);
        table.getColumnModel().getColumn(1).setPreferredWidth(200);
        table.getColumnModel().getColumn(2).setPreferredWidth(400);

        JScrollPane scrollPane = new JScrollPane(table);
        panel.add(scrollPane, BorderLayout.CENTER);
    }

    public JPanel getTablePanel() {
        return panel;
    }

    public void updateAppointments(String selectedDate) {
        tableModel.setRowCount(0); // Réinitialiser les rendez-vous pour la date sélectionnée

        for (int hour = 9; hour < 18; hour++) {
            tableModel.addRow(new Object[]{hour + ":00 - " + (hour + 1) + ":00", "", ""});
        }
    }
}

