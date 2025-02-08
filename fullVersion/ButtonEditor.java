// POUR LES BOUTONS 

import javax.swing.*;
import javax.swing.table.TableCellEditor;
import java.awt.*;

public class ButtonEditor extends AbstractCellEditor implements TableCellEditor {
    private JPanel panel;
    private JButton addButton, saveButton, editButton, deleteButton, smsButton;

    public ButtonEditor() {
       
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

       
    }

    @Override
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
        return panel;
    }

    @Override
    public Object getCellEditorValue() {
        return panel;
    }
}
