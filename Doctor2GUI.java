import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
// Specific graphic interface for doctor 1
public class Doctor2GUI extends CalendarGUI {
    public Doctor2GUI(Doctor2Calendar calendar) {
        super(calendar);
        createGUI(); // Call to the CalendarGUI constructor to initialize the calendar
    }
  @Override
  public void createGUI() {
    JFrame frame = new JFrame("Calendar Doctor 2: Gynecologist");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setSize(400, 400);
    frame.setLayout(new BorderLayout());
    // Appel de la méthode `createGUI()` de la classe parent
    super.createGUI();
    frame.setVisible(true); // Afficher la fenêtre
}
}
