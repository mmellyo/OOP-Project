// NORMALEMEMNT J'AI RIEN CHANGE DANS CE CODE. 
// IL EST IDENTIQUE A CELUI QUI CE TROUVE DANS LE FCHIER ' CalendarINFO.java'

import java.time.YearMonth; // Importing YearMonth class from Java to represent year/month and methods to manipulate this data
import java.time.LocalDate; // It allows the user to have a complete date (year, month, day)
import java.util.HashSet; // Used to store unique data
import java.util.Set; // Used to store unique data

// Declaration of CalendarINFO class that contains all the informations about my Medical Calendar (Calendrier Personaliser)
public class CalendarINFO {
    private int year; // Attribute that represents the current year of the calendar
    private int month; // Attribute that represents the current month of the calendar
    protected Set<String> blockedDays; // Set to store blocked days (as strings)

    // Class constructor that initializes the object with a year and month
    public CalendarINFO(int year, int month){
        this.year = year; // Initialize an object with a year
        this.month = month; // Initialize an object with a month
        this.blockedDays = new HashSet<>(); // Create an empty HashSet (best for search operations) for blocked days
        initializeBlockedDays(); // Method that allows to add predefined public holidays
    }

    // Guetter allows access to the value of year 
    public int getYear() {
        return year; // Returns the current calendar year
    }

    // Guetter allows access to the value of month
    public int getMonth(){
        return month; // Returns the current calendar month
    }
     
    // Setter for the year
    public void setYear(int year){
        LocalDate currentDate = LocalDate.now();
        if (year >= currentDate.getYear()) { // If condition: the year is not in the past compared to the current year
            this.year = year; // Valid condition and it is updated
        } else { // Otherwise, an error message is displayed
            System.out.println("You cannot navigate to a year that has already passed!");
        }
    }

    // Setter for the month
    public void setMonth(int month){
        LocalDate currentDate = LocalDate.now();
        YearMonth currentYearMonth = YearMonth.from(currentDate); // Represents the current year and month (date)
        YearMonth targetYearMonth = YearMonth.of(year, month); // Represents the current year with the month given as a parameter

        if (!targetYearMonth.isBefore(currentYearMonth)) { // If condition: checks that targetYearMonth is not before the current month
            this.month = month; // Condition is valid so the month is updated
        } else { // Otherwise, an error message is displayed
            System.out.println("You cannot navigate to a month that has already passed!");
        }
    }

    // Method to get the number of days in the current month
    public int getDayInMonth() {
        return YearMonth.of(year, month).lengthOfMonth(); 
        // YearMonth.of: creates an object for a specific year and month
        // lengthOfMonth: Returns the number of days according to the month ( february 28 or 29, the rest of months 30 or 31 days)
    }

    // Method returns the first day of the week 
    public int getFirstDayOfWeek(){
        return YearMonth.of(year, month).atDay(1).getDayOfWeek().getValue(); // Return its value
    }

    // Method for Specific blocked dates (les jours feries in Algeria)
    protected void initializeBlockedDays() { // Initialization of blocked days
        blockedDays.add("01/01"); // January 1st New Year's Day
        blockedDays.add("01/12"); // January 12 Yennayer's Day
        blockedDays.add("05/01"); // May 1 Labor Day
        blockedDays.add("07/05"); // July 5 Algerian Independence Day Anniversary
        blockedDays.add("11/01"); // November 1st Anniversary of The Start of the Algerian War   
    }

     // Method Check if every Friday is a blocked day
    public boolean isDayBlocked(int day) { 
        int dayOfWeek = YearMonth.of(year, month).atDay(day).getDayOfWeek().getValue();
        if (dayOfWeek == 5) { // 5 refers to Friday, the 5th day of the week
            return true;
        }

        // Check if the date is blocked
        String dateFormatted = String.format("%02d/%02d", month , day); // The date in “MM/DD”
        return blockedDays.contains(dateFormatted);
    }

    // Checking if a date has passed
    private boolean isMonthInFuture(int year, int month) {
        LocalDate currentDate = LocalDate.now(); // Creates a LocalDate object for the targeted date
        YearMonth currentYearMonth = YearMonth.from(currentDate);
        YearMonth targetYearMonth = YearMonth.of(year, month);
        return !targetYearMonth.isBefore(currentYearMonth);
        // Comparing the two to check if the target date has passed.
    }

    public boolean isDayInPast(int day) {
        LocalDate today = LocalDate.now();
        LocalDate targetDate = LocalDate.of(year, month, day);
    
        return targetDate.isBefore(today); // Returns true if the day has passed
    }
}
