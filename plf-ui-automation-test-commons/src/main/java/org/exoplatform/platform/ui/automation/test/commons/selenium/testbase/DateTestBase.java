package org.exoplatform.platform.ui.automation.test.commons.selenium.testbase;

import org.exoplatform.platform.ui.automation.test.config.Logger;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class DateTestBase {
  public DateTestBase() {
  }

  /**
   * Get minute in format "HH" from current date
   *
   * @return hours
   */
  public static int getHours() {
    Date date = new Date();
    Calendar cal = Calendar.getInstance();
    cal.setTime(date);
    int minute = cal.get(Calendar.HOUR);
    return (minute);
  }

  /**
   * Get date by text format
   * ex. Saturday, Febuary 16, 2015
   *
   * @param format
   */
  public static String getDateByTextFormat(String format) {
    DateFormat dateFormat = new SimpleDateFormat(format);
    Calendar cal = Calendar.getInstance();
    String date = dateFormat.format(cal.getTime());
    Logger.info(date);
    return date;
  }

  /**
   * Get first day of week
   *
   * @param format
   * @return firstDayOfWeek
   */
  public static String getFirstDayOfWeek(String format) {
    DateFormat dateFormat = new SimpleDateFormat(format);
    Date date = new Date();
    Calendar calendar = Calendar.getInstance();
    calendar.clear();
    calendar.setTime(date);
    calendar.set(Calendar.DAY_OF_WEEK, 2);
    String firstDayOfWeek = dateFormat.format(calendar.getTime());
    Logger.info("firstDayOfWeek:" + firstDayOfWeek);
    return firstDayOfWeek;
  }

  /**
   * Get last day of week
   *
   * @param format
   * @return firstDayOfWeek
   */
  public static String getLastDayOfWeek(String format) {
    DateFormat dateFormat = new SimpleDateFormat(format);
    Calendar currentDate = Calendar.getInstance();
    int firstDayOfWeek = currentDate.getFirstDayOfWeek();

    Calendar startDate = Calendar.getInstance();
    startDate.setTime(currentDate.getTime());
    int days = (startDate.get(Calendar.DAY_OF_WEEK) + 7 - firstDayOfWeek) % 7;
    startDate.add(Calendar.DATE, -days);

    Calendar endDate = Calendar.getInstance();
    endDate.setTime(startDate.getTime());
    endDate.add(Calendar.DATE, 5);
    String lastDayOfWeek = dateFormat.format(endDate.getTime());
    Logger.info("lastDayOfWeek:" + lastDayOfWeek);
    return lastDayOfWeek;
  }

  /**
   * function get current Date of system follow a format
   *
   * @param format
   * @return current Date of system
   */
  public static String getCurrentDate(String format) {
    DateFormat dateFormat = new SimpleDateFormat(format);
    Date date = new Date();
    return (dateFormat.format(date));
  }

  /**
   * Get current date with time zone
   *
   * @param format
   * @param local
   * @return current Date with correct time zone
   */
  public static String getCurrentDate(String format, String local) {
    DateFormat df = new SimpleDateFormat(format);
    Date date = new Date();
    df.setTimeZone(TimeZone.getTimeZone(local));
    System.out.println("Date and time in" + local + ": " + df.format(date));
    return df.format(date);
  }

  /**
   * Add 1 minute to current date time
   *
   * @param min
   * @param format
   * @return string minute
   */
  public static String addMinuteToCurrentDateTime(int min, String... format) {
    DateFormat dateFormat = format.length > 0 ? new SimpleDateFormat(format[0]) : new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
    Calendar cal = Calendar.getInstance();
    cal.add(Calendar.MINUTE, min);
    return (dateFormat.format(cal.getTime()));
  }

  /**
   * Get date in format "dd"
   *
   * @param gap distance from current date
   * @return date in format "dd"
   */
  public static String getDate(int gap, String format) {
    DateFormat dateFormat = new SimpleDateFormat(format);
    Calendar cal = Calendar.getInstance();
    cal.add(Calendar.DAY_OF_MONTH, gap);
    Logger.info(dateFormat.format(cal.getTime()));
    return (dateFormat.format(cal.getTime()));
  }

  /**
   * Get date from firstDayOf Week
   *
   * @param gap
   * @param format
   * @return date in format
   */
  public static String getDateFromFirstDayOfWeek(int gap, String format) {
    DateFormat dateFormat = new SimpleDateFormat(format);
    Date date = new Date();
    Calendar calendar = Calendar.getInstance();
    calendar.clear();
    calendar.setTime(date);
    calendar.set(Calendar.DAY_OF_WEEK, 2);
    calendar.add(Calendar.DAY_OF_MONTH, gap);
    String getDateFromFirstDayOfWeek = dateFormat.format(calendar.getTime());
    Logger.info("getDateFromFirstDayOfWeek:" + getDateFromFirstDayOfWeek);
    return getDateFromFirstDayOfWeek;
  }

  /**
   * Get day of week
   *
   * @param gap distance from current date
   * @return day of week (monday, tuesday,..., sunday)
   */
  public static int getDayOfWeek(int gap) {
    Calendar cal = Calendar.getInstance();
    cal.add(Calendar.DAY_OF_MONTH, gap);
    return cal.get(Calendar.DAY_OF_WEEK);
  }

  /**
   * Get day of the next month
   *
   * @param format
   * @return date
   */
  public static String getDayOfNextMonth(String format, int dayNum, int weekNum) {
    DateFormat dateFormat = new SimpleDateFormat(format);
    Calendar calendar = Calendar.getInstance();

    Calendar nextMonth = Calendar.getInstance();
    nextMonth.setTime(calendar.getTime());
    nextMonth.add(Calendar.DAY_OF_WEEK_IN_MONTH, weekNum);
    nextMonth.add(Calendar.DAY_OF_MONTH, dayNum);
    String dayOfNextMonth = dateFormat.format(nextMonth.getTime());
    System.out.println("dayOfNextMonth:" + dayOfNextMonth);
    return dayOfNextMonth;

  }

  /**
   * Get the day of next year
   *
   * @param format
   * @param year
   * @return dayOfYear
   */
  public static String getDayOfNextYear(String format, int year) {
    DateFormat dateFormat = new SimpleDateFormat(format);
    Calendar calendar = Calendar.getInstance();

    Calendar nextYear = Calendar.getInstance();
    nextYear.setTime(calendar.getTime());
    nextYear.add(Calendar.YEAR, year);
    nextYear.add(Calendar.MONTH, 12);
    String dayOfYear = dateFormat.format(nextYear.getTime());
    System.out.println("dayOfYear:" + dayOfYear);
    return dayOfYear;
  }

  /**
   * Get the day of next week
   *
   * @param format
   * @return
   */
  public static String getDayOfNextWeek(String format) {
    DateFormat dateFormat = new SimpleDateFormat(format);
    Calendar calendar = Calendar.getInstance();
    String dayOfNextMonth1 = dateFormat.format(calendar.getTime());
    System.out.println("dayOfNextMonth1:" + dayOfNextMonth1);

    Calendar nextWeek = Calendar.getInstance();
    nextWeek.setTime(calendar.getTime());
    nextWeek.add(Calendar.DATE, 7);
    String dayOfNextWeek = dateFormat.format(nextWeek.getTime());
    return dayOfNextWeek;

  }

  /**
   * Get the number of current week
   *
   * @return weekNum
   */
  public static int getWeekNumber() {
    Calendar calendar = Calendar.getInstance();
    calendar.setTime(new Date());
    return calendar.get(Calendar.DAY_OF_WEEK);
  }

  public static int getDayNumber() {
    Calendar calendar = Calendar.getInstance();
    calendar.setTime(new Date());
    return calendar.get(Calendar.DATE);
  }

  /**
   * Get current month/day/year
   *
   * @param format as MMM for month, dd for day, or yyyy for year
   * @return dateFormat.format(now.getTime())
   */
  public static String getCurrentMonthDayYear(String format) {
    DateFormat dateFormat = new SimpleDateFormat(format);
    Calendar now = Calendar.getInstance();
    return dateFormat.format(now.getTime());
  }

  /**
   * Get minute in format "mm" from current date
   *
   * @return minute
   */
  public static  int getMinute() {
    Date date = new Date();
    Calendar cal = Calendar.getInstance();
    cal.setTime(date);
    int minute = cal.get(Calendar.MINUTE);
    return (minute);
  }
}
