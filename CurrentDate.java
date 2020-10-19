package edutasks.kirillnsb;

public class CurrentDate {
    int year;
    int month=1;
    int day=1;

    public CurrentDate(int year, int month, int day) {
        this.year = year;
        this.month = month;
        this.day = day;
    }

    public CurrentDate(int year) {
        this.year = year;
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
}
