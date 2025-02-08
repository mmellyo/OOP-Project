// POUR LES BOUTONS 

import javax.swing.*;
import javax.swing.table.TableCellRenderer;
import java.awt.*;

public class ButtonRenderer extends JPanel implements TableCellRenderer {
    public ButtonRenderer() {
        setLayout(new FlowLayout(FlowLayout.CENTER, 2, 20));
        add(new JButton("Add"));
        add(new JButton("Save"));
        add(new JButton("Edit"));
        add(new JButton("Delete"));
        add(new JButton("SMS"));
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        return this;
    }
}
