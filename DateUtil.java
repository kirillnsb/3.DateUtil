package edutasks.kirillnsb;

public class DateUtil {

    static boolean isLeapYear(int year) {
        return year % 4 == 0 && year % 100 != 0 || year % 400 == 0;
    }

    static int getMonthLength(int year, int month) {
        int monthLength = 0;
        switch (month) {
            case 1:
            case 3:
            case 5:
            case 7:
            case 8:
            case 10:
            case 12:
                monthLength = 31;
                break;
            case 2:
                if (isLeapYear(year))
                    monthLength = 29;
                else
                    monthLength = 28;
                break;
            case 4:
            case 6:
            case 9:
            case 11:
                monthLength = 30;
                break;
        }
        return monthLength;
    }

    static int getYearLength(int year){
        if (isLeapYear(year))
            return 366;
        return 365;
    }

    static boolean isValidDate(int year, int month, int day) {
        int lastDay = getMonthLength(year, month);
        if (lastDay < day || day <= 0)
            return false;
        return year > 1582 || month > 10 || day >= 15;//Date out of Gregorian calendar(1582/10/15)
    }

    static int getDayOfWeek(int year, int month, int day) {
        if (month < 3) {
            year--;
            month += 10;
        } else
            month -= 2;
        int result = (day + 31 * month / 12 + year + year / 4 - year / 100 + year / 400) % 7;
        if (result == 0) { //correction result to task, where monday is 0, when in the previous case sunday is 0.
            result = 6;
        } else {
            result--;
        }
        return result;
    }

    private static String getDayName(int id) {
        String str = " ";
        switch (id) {
            case 0:
                str = "Monday";
                break;
            case 1:
                str = "Tuesday";
                break;
            case 2:
                str = "Wednesday";
                break;
            case 3:
                str = "Thursday";
                break;
            case 4:
                str = "Friday";
                break;
            case 5:
                str = "Saturday";
                break;
            case 6:
                str = "Sunday";
                break;
        }
        return str;
    }

    private static String getMonthName(int month) {
        String str = " ";
        switch (month) {
            case 1:
                str = "January";
                break;
            case 2:
                str = "February";
                break;
            case 3:
                str = "March";
                break;
            case 4:
                str = "April";
                break;
            case 5:
                str = "May";
                break;
            case 6:
                str = "June";
                break;
            case 7:
                str = "July";
                break;
            case 8:
                str = "August";
                break;
            case 9:
                str = "September";
                break;
            case 10:
                str = "October";
                break;
            case 11:
                str = "November";
                break;
            case 12:
                str = "December";
                break;
        }
        return str;
    }

    public static String toString(int year, int month, int day) {
        return getDayName(getDayOfWeek(year, month, day)) + ", " + getMonthName(month) + " " + day + ", " + year;
    }

    private static int countRemainingDaysInMonth(int year, int month, int day) {
        int count = getMonthLength(year, month) - day;
        count++;//including current day
        return count;
    }

    private static int countRemainingDaysInYear(int year, int month, int day) {
        int count = 0;
        for (; month <= 12; month++) {
            count += (countRemainingDaysInMonth(year, month, day));
            day = 1;
        }
        return count;
    }

    static int countDays(int year, int month, int day) {
        int count = 0;
        CurrentDate cd = new CurrentDate(); //current day entry
        int daysPassed; //past days counter
        if (isLeapYear(cd.year)) {
            daysPassed = 366 - countRemainingDaysInYear(cd.year, cd.month, cd.day);
        } else {
            daysPassed = 365 - countRemainingDaysInYear(cd.year, cd.month, cd.day);
        }
        for (; month <= 12; month++) {
            for (; year < cd.year; year++) {
                count += countRemainingDaysInYear(year, month, day);
                day = 1;
                month = 1;
            }
        }
        count += daysPassed;
        return count;

    }
}
