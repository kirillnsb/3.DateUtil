package edutasks.kirillnsb;

import java.util.Objects;

public class CurrentDate {
    int year;
    int month;
    int day;

    public CurrentDate(int year, int month, int day) {
        this.year = year;
        this.month = month;
        this.day = day;
    }

    public CurrentDate() {
        long ct = System.currentTimeMillis();
        year = 1970;
        month = 1;
        day = 1;
        ct /= 1000 * 60 * 60 * 24;
        for (; ct > 0; year++) {
            if (day == 1 && month == 1 && ct > DateUtil.getYearLength(year)) {
                ct -= DateUtil.getYearLength(year);
            } else {
                for (; day <= DateUtil.getMonthLength(year, month); month++) {
                    if (ct < DateUtil.getMonthLength(year, month)) {
                        day += ct;
                        ct = 0;
                        break;
                    } else {
                        ct -= DateUtil.getMonthLength(year, month);
                    }
                }
                break;
            }
        }
    }

    @Override
    public String toString() {
        return "CurrentDate{" +
                "year=" + year +
                ", month=" + month +
                ", day=" + day +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CurrentDate that = (CurrentDate) o;
        return year == that.year &&
                month == that.month &&
                day == that.day;
    }

    @Override
    public int hashCode() {
        return Objects.hash(year, month, day);
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
