// POUR LES BOUTONS 

import javax.swing.*;
import javax.swing.table.TableCellRenderer;
import java.awt.*;

public class ButtonRenderer extends JPanel implements TableCellRenderer {
    private JButton addButton, saveButton, editButton, deleteButton, smsButton;

    public ButtonRenderer() {
        setLayout(new FlowLayout(FlowLayout.CENTER, 8, 20));

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
        add(addButton);
        add(saveButton);
        add(editButton);
        add(deleteButton);
        add(smsButton);
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        return this;
    }
}
