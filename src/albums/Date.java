package albums;

import java.util.Calendar;
import java.util.StringTokenizer;

/** This class defines the Date abstract data type with year, month, and day. ...*/
public class Date implements Comparable<Date> {
    private int year;
    private int month;
    private int day;

    /** This is a parameterized constructor that takes a string in a form of "mm/dd/yyyy" and creates a Date object */
    public Date(String date) { //take “mm/dd/yyyy” and create a albums.Date object
        StringTokenizer st = new StringTokenizer(date, "/");
        month = Integer.parseInt(st.nextToken());
        day = Integer.parseInt(st.nextToken());
        year = Integer.parseInt(st.nextToken());
    }

    /** This constructor returns today's date. */
    public Date() {} //create an object with today’s date (see Calendar class)

    /** This method overrides the compareTo method and compares 2 dates. */
    @Override
    public int compareTo(Date date) {return 1;}

    /** This method returns today's date. ...*/
    public Date today() { return new Date(); }

    /** Checking if a date is valid. ...*/
    public boolean isValid() {return true;}

    /** Returning the textual representation of a Date object in the form of "mm/dd/yyyy". */
    @Override
    public String toString() { return month + "/" + day + "/" + year; }

    /** Getting the day of the Date object. ...*/
    public int getDay() { return day; }

    /** Getting the month of the Date object. ...*/
    public int getMonth() { return month; }

    /** Getting the year of the Date object. ...*/
    public int getYear() { return year; }

    /** Checking if two dates are the same. ...*/
    @Override
    public boolean equals(Object obj) {
        /*if (obj instanceOf Student) {
            Student student = (Student) obj; //casting
            return student.name.equals(this.name); //calling the equals() of the String class
        }
        */
        return false;
    }

    /** Tested main for the Date class ...*/
    public static void main(String[] args) {
        //test case #1, a date with the year before 1980 should be invalid.
        Date date = new Date("11/1/1979");
        boolean expectedResult = false;
        boolean result = date.isValid();
        System.out.print("Test case #1: ");
        if (result == expectedResult)
            System.out.println("Pass.");
        else
            System.out.println("Fail.");

        //test case #2, a date with an invalid month.
        date = new Date("13/21/1999");
        result = date.isValid();
        System.out.print("Test case #2: ");
        if (result == expectedResult)
            System.out.println("Pass.");
        else
            System.out.println("Fail.");
    }
}