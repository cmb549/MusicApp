package albums;

import java.util.Calendar;
import java.util.StringTokenizer;

/** This class defines the Date abstract data type with year, month, and day. ...*/
public class Date implements Comparable<Date> {
    private int year;
    private int month;
    private int day;

    /**
     * This is a parameterized constructor that takes a string in a form of "mm/dd/yyyy" and creates a Date object
     */
    public Date(String date) { //take “mm/dd/yyyy” and create a albums.Date object
        StringTokenizer st = new StringTokenizer(date, "/");
        month = Integer.parseInt(st.nextToken());
        day = Integer.parseInt(st.nextToken());
        year = Integer.parseInt(st.nextToken());
    }

    /**
     * This constructor returns today's date.
     */
    public Date() { //create an object with today’s date (see Calendar class)
        Calendar cal = java.util.Calendar.getInstance();
        month = cal.get(Calendar.MONTH)+1;
        day = cal.get(Calendar.DAY_OF_MONTH);
        year = cal.get(Calendar.YEAR);
    }


    /**
     * Getting the day of the Date object. ...
     */
    public int getDay() {
        return day;
    }

    /**
     * Getting the month of the Date object. ...
     */
    public int getMonth() {
        return month;
    }

    /**
     * Getting the year of the Date object. ...
     */
    public int getYear() {
        return year;
    }


    /**
     * This method overrides the compareTo method and compares 2 dates.
     */
    @Override
    public int compareTo(Date date) {
        if (date.getYear() > this.year) {
            return 1;
        }
        if (date.getYear() < this.year) {
            return -1;
        }
        if (date.getYear() == this.year) {
            if (date.getMonth() > this.month) {
                return 1;
            }
            if (date.getMonth() < this.month) {
                return -1;
            }
            if (date.getMonth() == this.month) {
                if (date.getDay() > this.day) {
                    return 1;
                }
                if (date.getDay() < this.day) {
                    return -1;
                }
                if (date.getDay() == this.day) {
                    return 0;
                }
            }
        }
        throw new IllegalArgumentException("Non comparable objects");
    }

    /**
     * This method returns today's date. ...
     */
    public static Date today() {
        return new Date();
    }


    private boolean isValidMonth(int monthToCheck) {
        return monthToCheck > 0 && monthToCheck < 13;
    }

    private boolean isLeapYear(int yearToCheck) {
        if (yearToCheck % 4 == 0) {
            if (yearToCheck % 100 == 0) {
                if (year % 400 == 0) {
                    return true; //divisible by 400
                }
                return false; //divisible by 100
            }
            return true; // divisible by 4
        }
        return false; // not divisible by 4
    }

    private boolean isValidDay(int dayToCheck) {
        if (!isValidMonth(month)) {
            return false;
        }
        if (month == 1 || month == 3 || month == 5 || month == 7 || month == 9 || month == 11 || month == 12) {
            return dayToCheck > 0 && dayToCheck < 32;
        }//months with 31 days

        if (month == 4 || month == 6 || month == 8 || month == 10) {
            return dayToCheck > 0 && dayToCheck < 31;
        }//months with 30 days

        if (month == 2) { // if february
            if (dayToCheck > 0 && dayToCheck < 29) {
                return true;
            } else if (isLeapYear(year)) {
                return dayToCheck == 29;
            }
            return false;
        }
        throw new IllegalArgumentException("Somehow the month was wrong");
    }


    /**
     * Checking if a date is valid. ...
     */
    public boolean isValid() {
        Calendar cal = java.util.Calendar.getInstance();
        if (year > cal.get(Calendar.YEAR)) { //If a future year
//            System.out.println(cal.MONTH);
            return false;
        }
        if (year == cal.get(Calendar.YEAR)) { // if a future month
            if (month > cal.get(Calendar.MONTH)+1) {
                return false;
            } else if (month == cal.get(Calendar.MONTH)+1) {
                if (day > cal.get(Calendar.DAY_OF_MONTH)) { // if a future day
                    return false;
                }
            }
        }
        return isValidDay(day) && isValidMonth(month) && year >= 1980;
    }
    /** Returning the textual representation of a Date object in the form of "mm/dd/yyyy". */
    public String toString(){
        return Integer.toString(month)+ "/" + Integer.toString(day) + "/" + Integer.toString(year);
    }

    /** Checking if two dates are the same. ...*/
    @Override
    public boolean equals (Object obj){
        if (obj instanceof Date){
            return this.compareTo((Date) obj) == 0;
        }
        else {
            return false;
        }
    }

    /** Tested main for the Date class ...*/
    public static void main (String[]args){
        //test case #1, a date with the year before 1980 should be invalid.
        Date date = new Date("11/1/1979");
        boolean result = date.isValid();
        System.out.print("Test case #1: ");
        if (!result)
            System.out.println("Pass.");
        else
            System.out.println("Fail.");

        //test case #2, a date with an invalid month.
        date = new Date("12/21/1999");
        result = date.isValid();
        System.out.print("Test case #2: ");
        if (result)
            System.out.println("Pass.");
        else
            System.out.println("Fail.");
    }

    //invalid day general ie: 31 in october
    //invalid day non leap ie: 29 in feb in 2001
    //valid day on leap ie: 29 in feb in 2004
    //valid day non leap ie: 28 in feb in 2001
}