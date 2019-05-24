/**
 * Created by Hana on 5/7/2019.
 */
public class Date {

    private int year;
    private int month;
    private int day;
    private int hour;

    public Date(int year, int month, int day, int hour) {
        this.year = year;
        this.month = month;
        this.day = day;
        this.hour = hour;
    }

    public int getYear() {
        return year;
    }

    public int getMonth() {
        return month;
    }

    public int getDay() {
        return day;
    }

    public int getHour() {
        return hour;
    }
}
