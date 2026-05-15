

public class DateTime implements IDateTime {

    private int year;
    private int month;
    private int day;
    private int hour;
    private int minute;

    public DateTime(int year, int month, int day, int hour, int minute) {

        if (month < 1 || month > 12)
            return;

        if (day < 1 || day > 31)
            return;

        if (hour < 0 || hour > 23)
            return;

        if (minute < 0 || minute > 59)
            return;

        this.year = year;
        this.month = month;
        this.day = day;
        this.hour = hour;
        this.minute = minute;
    }

    @Override
    public int getYear() {
        return year;
    }

    @Override
    public int getMonth() {
        return month;
    }

    @Override
    public int getDay() {
        return day;
    }

    @Override
    public int getHour() {
        return hour;
    }

    @Override
    public int getMinute() {
        return minute;
    }

    @Override
    public String format() {
        return month + "/" + day + "/" + year + " " + hour + ":" + minute;
    }

    @Override
    public int compareTo(IDateTime other) {

        if (this.year != other.getYear())
            return this.year - other.getYear();

        if (this.month != other.getMonth())
            return this.month - other.getMonth();

        if (this.day != other.getDay())
            return this.day - other.getDay();

        if (this.hour != other.getHour())
            return this.hour - other.getHour();

        return this.minute - other.getMinute();
    }
}
