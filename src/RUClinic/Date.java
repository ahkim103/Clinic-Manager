package RUClinic;

import java.util.Calendar;
import java.util.StringTokenizer;

/**
 The Date class is used to hold a date with the variables: year, month, and day.
 This class provides an easier way to not only return a date but also to keep
 track and validate the date of certain appointments in the clinic.
 @author Katrina Tong
 @author Andrew Kim
 */
public class Date implements Comparable<Date> {
    private int year;
    private int month;
    private int day;

    /**
     * Creates a Date object with variables taken from a string parameter.
     * @param date takes in a String parameter formatted as mm/dd/yyyy.
     */
    public Date(String date) {
        String monthString;
        String dayString;
        String yearString;
        final String INVALIDPARAM = "-1";

        StringTokenizer dateToken = new StringTokenizer(date,"/");

        if (dateToken.hasMoreTokens()) {
            monthString = dateToken.nextToken();
        } else {
            monthString = INVALIDPARAM;
        }
        if (dateToken.hasMoreTokens()) {
            dayString = dateToken.nextToken();
        } else {
            dayString = INVALIDPARAM;
        }
        if (dateToken.hasMoreTokens()) {
            yearString = dateToken.nextToken();
        } else {
            yearString = INVALIDPARAM;
        }

        if(dateToken.hasMoreTokens()) {
            month = Integer.parseInt(INVALIDPARAM);
            day = Integer.parseInt(INVALIDPARAM);
            year = Integer.parseInt(INVALIDPARAM);
        } else {
            month = Integer.parseInt(monthString);
            day = Integer.parseInt(dayString);
            year = Integer.parseInt(yearString);
        }
    }

    /**
     * Creates a Date object with today's date via the Calendar class.
     */
    public Date() {
        Calendar todayDate = Calendar.getInstance();
        year = todayDate.get(Calendar.YEAR);
        month = todayDate.get(Calendar.MONTH) + 1;
        day = todayDate.get(Calendar.DATE);
    }

    // Return Methods Below
    /**
     * Helper Method to get the year of the date of a certain appointment
     * @return year of the Appointment as an int
     */
    public int getYear() {
        return year;
    }

    /**
     * Helper Method to get the month of the date of a certain appointment
     * @return month of the Appointment as an int
     */
    public int getMonth() {
        return month;
    }

    /**
     * Helper Method to get the day of the date of a certain appointment
     * @return day of the Appointment as an int
     */
    public int getDay() {
        return day;
    }


    /**
     * One of the methods to check if a date is valid
     * If the date is today or a date before today, it is invalid
     * @return true if the date is not today or a day before today and false if not
     */
    public boolean isTodayOrBefore() {
        boolean valid = false;
        Date today = new Date();
        int todayYear = today.getYear();
        int todayMonth = today.getMonth();
        int todayDay = today.getDay();

        if ((year < todayYear) || ((year == todayYear) && (month < todayMonth))
                || ((year == todayYear) && (month == todayMonth) && (day <= todayDay))) {
            return valid;
        }
        else { valid = true; return valid; }
    }

    /**
     * One of the methods to check if a date is valid
     * If the date is more than 6 months ahead of today, then it is invalid
     * @return true if the date is not more than 6 months ahead of today and false if not
     */
    public boolean isInSixMonths() {
        boolean valid = false;
        Date today = new Date();
        int todayYear = today.getYear();
        int todayMonth = today.getMonth();
        int todayDay = today.getDay();

        Calendar todayCalendar = Calendar.getInstance();
        Calendar dateCalendar = Calendar.getInstance();
        todayCalendar.set(todayYear, todayMonth - 1, todayDay);
        dateCalendar.set(year, month - 1, day);

        todayCalendar.add(Calendar.MONTH, 6);
        if (dateCalendar.after(todayCalendar)) { return valid; }
        else { valid = true; return valid; }
    }

    /**
     * One of the methods to check if a date is valid
     * If the date is a weekend, then it is invalid
     * @return true if the date is not a weekend and false if not
     */
    public boolean isWeekend() {
        boolean valid = false;
        Calendar dateCalendar = Calendar.getInstance();
        dateCalendar.set(year, month - 1, day);
        int dayOfWeek = dateCalendar.get(Calendar.DAY_OF_WEEK);
        if (dayOfWeek == Calendar.SATURDAY || dayOfWeek == Calendar.SUNDAY) {
            return valid;
        }
        else { valid = true; return valid; }
    }

    /**
     * One of the methods to check if a date is valid
     * The date is invalid if it does not exist
     * @return boolean value that shows true if it exists and false if not
     */
    public boolean isDateValid() {
        boolean isValidDate = false;
        int lastDay = daysInMonth(year, month);
        if (day < 1 || day > lastDay || month < 0 || month > 12) { return isValidDate; }
        else { isValidDate = true; return isValidDate; }
    }

    /**
     * Method to see if a certain date is valid for an appointment
     * The date is invalid if it does not pass all invalid checkers
     * @return boolean value that shows true if it's a valid date and false if it's not
     */
    public boolean isValid() {
        boolean isValidDate = false;
        if (!isDateValid() || !isTodayOrBefore() || !isInSixMonths() || !isWeekend()) { return isValidDate; }
        else { isValidDate = true; return isValidDate; }
    }

    public static final int QUADRENNIAL = 4;
    public static final int CENTENNIAL = 100;
    public static final int QUATERCENTENNIAL = 400;

    /**
     * A method to figure out how many days are in a given month, including leap years
     * @param year needs to be checked if it is a leap year
     * @param month needs to see which month is being referred to
     * @return the amount of days in a given month as an int
     */
    public int daysInMonth(int year, int month) {
        boolean isLeapYear;
        int lastDay;

        if (year % QUADRENNIAL == 0) {
            if (year % CENTENNIAL == 0) {
                if (year % QUATERCENTENNIAL == 0) {
                    isLeapYear = true;
                } else {
                    isLeapYear = false;
                }
            } else {
                isLeapYear = true;
            }
        } else {
            isLeapYear = false;
        }

        if(month == 1 || month == 3 || month == 5 || month == 7
                || month == 8 || month == 10|| month == 12) {
            lastDay = 31;
        }
        else if(month == 4 || month == 6 || month == 9 || month == 11) {
            lastDay = 30;
        }
        else if(month == 2) {
            if(isLeapYear) lastDay = 29;
            else lastDay = 28;
        }
        else lastDay = -1;

        return lastDay;
    }

    /**
     * Method to return the date in a String mm/dd/yyyy format
     * @return String in the mm/dd/yyyy format
     */
    @Override
    public String toString() {
        return (month + "/" + day + "/" + year);
    }

    /**
     * Method to compare a certain  date with another one to see if one is later than the other
     * @param otherDate date to compare to the first Date
     * @return -1 if the original/first Date is earlier than otherDate,
     * 1 if the opposite, or 0 if both dates are the same
     */
    @Override
    public int compareTo(Date otherDate) {
        if (this.year < otherDate.year) { return -1; }
        else if (this.year > otherDate.year) { return 1; }
        if (this.month < otherDate.month) { return -1; }
        else if (this.month > otherDate.month) { return 1; }
        if (this.day < otherDate.day) { return -1; }
        else if (this.day > otherDate.day) { return 1; }
        return 0;
    }

    /**
     * Method to compare a certain date with another one to see if they are equal
     * @param date object to compare to the first Date
     * @return true if the specified object is a Date and represents the
     * same year, month, and day. If not, then false.
     */
    @Override
    public boolean equals(Object date) {
        if (!(date instanceof Date)) { return false; }
        Date objectDate = (Date) date;
        return ((this.year == objectDate.year) && (this.month == objectDate.month)
                && (this.day == objectDate.day));
    }

}
