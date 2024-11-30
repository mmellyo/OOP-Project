import java.time.YearMonth; // Importing YearMonth class from java to manipulate years and months
import java.util.HashSet;
import java.util.Set;

// Declaration of CalendarINFO class
public class CalendarINFO {
    private int year; // Current year attribute
    private int month; // Month attribute from January to December
    private Set<String> blockedDays; // Set of blocked days 

    // Constructor that initializes the object with a year and a month
    public CalendarINFO(int year, int month){
        this.year = year;
        this.month = month;
        this.blockedDays = new HashSet<>();
        initializeBlockedDays();
    }

    // Guetters allow access to the value of year and month
    public int getYear() {
        return year;
    }
    public int getMonth(){
        return month;
    }
     
    // Setters allow to modify the value of year and month
    public void setYear(int year){
        this.year = year;
    }
    public void setMonth(int month){
        this.month = month;
    }

    // Method that calculates the number of days in the current month
    public int getDayInMonth() {
        return YearMonth.of(year, month).lengthOfMonth(); 
        // YearMonth.of: creates an object for a specific year and month
        // lengthOfMonth: Returns the number of days according to the month (28,29,30,31)
    }

    // Method returns the day of the week for the first day of the current month
    public int getFirstDayOfWeek(){
        return YearMonth.of(year, month).atDay(1).getDayOfWeek().getValue();
    }

    private void initializeBlockedDays() {
        //Specific dates blocked
        blockedDays.add("01/01"); // January 1st New Year's Day
        blockedDays.add("01/12"); // January 12 Yennayer
        blockedDays.add("05/01"); // May 1 Labor Day
        blockedDays.add("07/05"); // July 5 Algerian Independence Day Anniversary
        blockedDays.add("11/01"); // November 1st Anniversary of The Start of the Algerian War   
    }

    public boolean isDayBlocked(int day) {
        // Check if every Friday is blocked 
        int dayOfWeek = YearMonth.of(year, month).atDay(day).getDayOfWeek().getValue();
        if (dayOfWeek == 5) { // Friday
            return true;
        }

        // Check if the date is blocked
        String dateFormatted = String.format("%02d/%02d", month , day);
        return blockedDays.contains(dateFormatted);
    }
}
    
