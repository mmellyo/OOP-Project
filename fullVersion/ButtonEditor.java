
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;
import java.awt.*;
import javax.swing.*;
import javax.swing.table.TableCellEditor;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ButtonEditor extends AbstractCellEditor implements TableCellEditor {
    private JPanel panel;
    private JButton addButton, saveButton, editButton, deleteButton, smsButton;
    private JTable table;
    private int row;
    private AppointmentTable appointmentTable;

    public ButtonEditor(AppointmentTable appointmentTable) {
        this.appointmentTable = appointmentTable;
        panel = new JPanel(new FlowLayout(FlowLayout.CENTER, 2, 0));

        addButton = new JButton("Add");
        saveButton = new JButton("Save");
        editButton = new JButton("Edit");
        deleteButton = new JButton("Delete");
        smsButton = new JButton("SMS");

        // Définir les couleurs
        saveButton.setBackground(Color.GREEN);
        saveButton.setForeground(Color.WHITE);

        deleteButton.setBackground(Color.RED);
        deleteButton.setForeground(Color.WHITE);

        // Ajouter les boutons
        panel.add(addButton);
        panel.add(saveButton);
        panel.add(editButton);
        panel.add(deleteButton);
        panel.add(smsButton);

        // Écouteur pour le bouton "Add"
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Récupérer les infos du patient par défaut depuis AppointmentTable
                String patientInfo = appointmentTable.getDefaultPatientInfo();
                if (patientInfo != null && !patientInfo.isEmpty()) {
                    // Mettre à jour la cellule "Patients Informations" (colonne 1) de la ligne courante
                    ((DefaultTableModel) table.getModel()).setValueAt(patientInfo, row, 1);
                } else {
                    JOptionPane.showMessageDialog(null, "Aucune information de patient par défaut n'est définie.");
                }
                stopCellEditing(); // Arrêter l'édition de la cellule
            }
        });

       
    }

    @Override
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
        this.table = table;
        this.row = row;
        return panel;
    }

    @Override
    public Object getCellEditorValue() {
        return panel;
    }
}