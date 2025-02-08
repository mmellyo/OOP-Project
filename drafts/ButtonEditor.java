//  NOUVELLE CLASSE POUR LES BOUTONS 

import javax.swing.*;
import javax.swing.table.TableCellEditor;
import java.awt.*;


public class ButtonEditor extends AbstractCellEditor implements TableCellEditor {
    private JPanel panel;
    private JButton addButton, saveButton, smsButton, editButton, deleteButton;

    public ButtonEditor() {
        panel = new JPanel(new FlowLayout(FlowLayout.CENTER, 2, 0));
        addButton = new JButton("Add");
        saveButton = new JButton("Save");
        editButton = new JButton("Edit");
        deleteButton = new JButton("Delete");
        smsButton = new JButton("SMS");

        panel.add(addButton);
        panel.add(saveButton);
        panel.add(editButton);
        panel.add(deleteButton);
        panel.add(smsButton);

        // Exemple d'action sur le bouton "Add"
        addButton.addActionListener(e -> JOptionPane.showMessageDialog(null, "Add button clicked"));
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
