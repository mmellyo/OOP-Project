import java.time.YearMonth;
import java.awt.event.*;
// Doctor1Calendar is a subclass
public class Doctor1Calendar extends CalendarINFO { // The new class herite the methods and attributes of the CalendarINFO class
    public Doctor1Calendar(int year, int month) {
        super(year, month); // Call the constructor of the parent class (CalendarINFO) to initialize the years and months
    }
    @Override
    protected void initializeBlockedDays() {
        super.initializeBlockedDays();
        for (int day = 1; day <= getDayInMonth(); day++) {
            int dayOfWeek = YearMonth.of(getYear(), getMonth()).atDay(day).getDayOfWeek().getValue(); // We go through all the days of the month by checking the day of the week 
            if (dayOfWeek == 1 || dayOfWeek == 2 || dayOfWeek == 4) { // Monday (1), Tuesday (2), Thursday (4)
                blockedDays.add(String.format("%02d/%02d", getMonth(), day)); // Blocked dates are added in MM/DD format
            }
        }
    }
}
