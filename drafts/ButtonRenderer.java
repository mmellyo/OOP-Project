// JAI DU CREE UNE AUTRE CLASSE CAR JAI EU MAL A METTRE DES BOUTONS A LINTERIEURE DUN TABLEAU

import javax.swing.*;
import javax.swing.table.TableCellRenderer;
import java.awt.*;

public class ButtonRenderer extends JPanel implements TableCellRenderer {
    public ButtonRenderer() {
        setLayout(new FlowLayout(FlowLayout.CENTER, 2, 0));
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
