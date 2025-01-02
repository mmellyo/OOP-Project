import java.time.YearMonth;
import java.awt.event.*;
// Doctor2Calendar is a subclass

public class Doctor2Calendar extends CalendarINFO  {  // The new class herite the methods and attributes of the CalendarINFO class
    public Doctor2Calendar(int year, int month) {
        super(year, month); // Call the constructor of the parent class (CalendarINFO) to initialize the years and months
    }
    @Override
    protected void initializeBlockedDays() {
        super.initializeBlockedDays(); // For les jours fériés and friday
        for (int day = 1; day <= getDayInMonth(); day++) {
            int dayOfWeek = YearMonth.of(getYear(), getMonth()).atDay(day).getDayOfWeek().getValue(); // We go through all the days of the month by checking the day of the week 
            if (dayOfWeek == 7 || dayOfWeek == 3 || dayOfWeek == 6) { // Sunday (7), Wednesday (3), Saturday (6)
                blockedDays.add(String.format("%02d/%02d", getMonth(), day)); // Blocked dates are added in MM/DD format
            }
        }
    }
}
