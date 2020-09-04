package org.isf.utils.time;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.MessageFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.isf.generaldata.GeneralData;
import org.isf.generaldata.MessageBundle;
import org.isf.utils.db.DbQueryLogger;
import org.isf.utils.exception.OHException;
import org.joda.time.DateTime;
import org.joda.time.Period;
import org.joda.time.PeriodType;

/**
 * 
 * @author Mwithi
 * 
 * Some useful functions for time calculation.
 *
 */
public class TimeTools {

	public static final String YYYY_MM_DD = "yyyy-MM-dd";

	public static void main(String[] args) {
		GeneralData.getGeneralData();
		MessageBundle.initialize();
		GregorianCalendar dateFrom = new GregorianCalendar(2014, 10, 1);
		GregorianCalendar dateTo = new GregorianCalendar();
		System.out.println("Formatted Age: " + getFormattedAge(dateFrom.getTime()));
		System.out.println("Days between: " + getDaysBetweenDates(dateFrom, dateTo, true));
		System.out.println("Weeks between: " + getWeeksBetweenDates(dateFrom, dateTo, true));
		System.out.println("Months between: " + getMonthsBetweenDates(dateFrom, dateTo, true));
	}
	
	
	
	/**
	 * @author Mwithi
	 * 
	 * returns <code>true</code> if the DATE part is the same (no matter the time)
	 * @param aDate
	 * @param today
	 * @return
	 */
	public static boolean isSameDay(Date aDate, Date today) {
		GregorianCalendar date1 = new GregorianCalendar();
		GregorianCalendar date2 = new GregorianCalendar();
		date1.setTime(aDate);
		date2.setTime(today);
		return isSameDay(date1, date2);
	}
	public static boolean isSameDay(GregorianCalendar aDate, GregorianCalendar today) {
		return (aDate.get(Calendar.YEAR) == today.get(Calendar.YEAR)) &&
			   (aDate.get(Calendar.MONTH) == today.get(Calendar.MONTH)) &&
			   (aDate.get(Calendar.DAY_OF_MONTH) == today.get(Calendar.DAY_OF_MONTH));
	}

	/**
	 * @author Mwithi
	 * 
	 * returns the difference in days between two dates
	 * @param from
	 * @param to
	 * @param ignoreTime - if <code>True</code> only dates will be compared
	 * @return the number of days, negative if from is after to
	 */
	public static int getDaysBetweenDates(GregorianCalendar from, GregorianCalendar to, boolean ignoreTime) {
		
		if (ignoreTime) {
			from.set(GregorianCalendar.HOUR_OF_DAY, 0);
			from.set(GregorianCalendar.MINUTE, 0);
			from.set(GregorianCalendar.SECOND, 0);
			to.set(GregorianCalendar.HOUR_OF_DAY, 0);
			to.set(GregorianCalendar.MINUTE, 0);
			to.set(GregorianCalendar.SECOND, 0);
		}
		
		DateTime dateFrom = new DateTime(from);
		DateTime dateTo = new DateTime(to);
		Period period = new Period(dateFrom, dateTo, PeriodType.days());
		return period.getDays();
	}
	
	/**
	 * @author Mwithi
	 * 
	 * returns the difference in days between two dates
	 * @param from
	 * @param to
	 * @param ignoreTime - if <code>True</code> only dates will be compared
	 * @return the number of days, negative if from is after to
	 */
	public static int getDaysBetweenDates(Date from, Date to, boolean ignoreTime) {
		
		if (ignoreTime) {
			GregorianCalendar dateFrom = new GregorianCalendar(); 
			GregorianCalendar dateTo = new GregorianCalendar();
			dateFrom.setTime(from);
			dateFrom.set(GregorianCalendar.HOUR_OF_DAY, 0);
			dateFrom.set(GregorianCalendar.MINUTE, 0);
			dateFrom.set(GregorianCalendar.SECOND, 0);
			
			dateTo.setTime(to);
			dateTo.set(GregorianCalendar.HOUR_OF_DAY, 0);
			dateTo.set(GregorianCalendar.MINUTE, 0);
			dateTo.set(GregorianCalendar.SECOND, 0);
			
			from = dateFrom.getTime();
			to = dateFrom.getTime();
		}
		
		DateTime dateFrom = new DateTime(from);
		DateTime dateTo = new DateTime(to);
		Period period = new Period(dateFrom, dateTo, PeriodType.days());
		return period.getDays();
	}
	
	/**
	 * @author Mwithi
	 * 
	 * returns the difference in weeks between two dates
	 * @param from
	 * @param to
	 * @param ignoreTime - if <code>True</code> only dates will be compared
	 * @return the number of days, negative if from is after to
	 */
	public static int getWeeksBetweenDates(GregorianCalendar from, GregorianCalendar to, boolean ignoreTime) {

		if (ignoreTime) {
			from.set(GregorianCalendar.HOUR_OF_DAY, 0);
			from.set(GregorianCalendar.MINUTE, 0);
			from.set(GregorianCalendar.SECOND, 0);
			to.set(GregorianCalendar.HOUR_OF_DAY, 0);
			to.set(GregorianCalendar.MINUTE, 0);
			to.set(GregorianCalendar.SECOND, 0);
		}
		
		DateTime dateFrom = new DateTime(from);
		DateTime dateTo = new DateTime(to);
		Period period = new Period(dateFrom, dateTo, PeriodType.weeks());
		return period.getWeeks();
	}
	
	/**
	 * @author Mwithi
	 * 
	 * returns the difference in months between two dates
	 * @param from
	 * @param to
	 * @param ignoreTime - if <code>True</code> only dates will be compared
	 * @return the number of days, negative if from is after to
	 */
	public static int getMonthsBetweenDates(GregorianCalendar from, GregorianCalendar to, boolean ignoreTime) {
		
		if (ignoreTime) {
			from.set(GregorianCalendar.HOUR_OF_DAY, 0);
			from.set(GregorianCalendar.MINUTE, 0);
			from.set(GregorianCalendar.SECOND, 0);
			to.set(GregorianCalendar.HOUR_OF_DAY, 0);
			to.set(GregorianCalendar.MINUTE, 0);
			to.set(GregorianCalendar.SECOND, 0);
		}
		
		DateTime dateFrom = new DateTime(from);
		DateTime dateTo = new DateTime(to);
		Period period = new Period(dateFrom, dateTo, PeriodType.months());
		return period.getMonths();
	}
	
	/**
	 * Return the age in the format {years}y {months}m {days}d or with other locale pattern
	 * 
	 * @author Mwithi 
	 * @param birthDate - the birthdate
	 * @return string with the formatted age
	 */
	public static String getFormattedAge(Date birthDate) {
		GregorianCalendar birthday = new GregorianCalendar();
		String pattern = MessageBundle.getMessage("angal.common.agepattern");
		String age = "";
		if (birthDate != null) {
			birthday.setTime(birthDate);
			DateTime now = new DateTime();
			DateTime birth = new DateTime(birthday.getTime());
			Period period = new Period(birth, now, PeriodType.yearMonthDay());
			age = MessageFormat.format(pattern, period.getYears(), period.getMonths(), period.getDays());
		}
		return age;
	}
	
	/**
	 * Return a string representation of the dateTime with the given pattern
	 * @param dateTime - a GregorianCalendar object
	 * @param pattern - the pattern. If <code>null</code> "yyyy-MM-dd HH:mm:ss" will be used
	 * @return the String representation of the GregorianCalendar
	 */
	public static String formatDateTime(GregorianCalendar dateTime, String pattern) {
		if (pattern == null) pattern = "yyyy-MM-dd HH:mm:ss";
		SimpleDateFormat format = new SimpleDateFormat(pattern);  //$NON-NLS-1$
		return format.format(dateTime.getTime());
	}
	
	/**
	 * Return a string representation of the dateTime with the given pattern
	 * @param date - a Date object
	 * @param pattern - the pattern. If <code>null</code> "yyyy-MM-dd HH:mm:ss" will be used
	 * @return the String representation of the GregorianCalendar
	 */
	public static String formatDateTime(Date date, String pattern) {
		if (pattern == null) pattern = "yyyy-MM-dd HH:mm:ss";
		GregorianCalendar dateTime = new GregorianCalendar();
		dateTime.setTime(date);
		SimpleDateFormat format = new SimpleDateFormat(pattern);  //$NON-NLS-1$
		return format.format(dateTime.getTime());
	}

	/**
	 * Return a string representation of the dateTime in the form "yyyy-MM-dd HH:mm:ss"
	 * @param time - a GregorianCalendar object
	 * @return the String representation of the GregorianCalendar
	 */
	public static String formatDateTimeReport(GregorianCalendar time) {
		return formatDateTime(time, null);
	}
	
	/**
	 * Return a string representation of the dateTime in the form "yyyy-MM-dd HH:mm:ss"
	 * @param date - a Date object
	 * @return the String represetation of the Date
	 */
	public static String formatDateTimeReport(Date date) {
		GregorianCalendar time = new GregorianCalendar();
		time.setTime(date);
		return formatDateTime(time, null);
	}
	
	/**
	 * Return the first instance of the current date
	 * @return
	 */
	public static GregorianCalendar getDateToday0() {
		GregorianCalendar date = new GregorianCalendar();
		date.set(GregorianCalendar.HOUR_OF_DAY, 0);
		date.set(GregorianCalendar.MINUTE, 0);
		date.set(GregorianCalendar.SECOND, 0);
		return date;
	}
	
	/**
	 * Return the last instance of the current date
	 * @return
	 */
	public static GregorianCalendar getDateToday24() {
		GregorianCalendar date = new GregorianCalendar();
		date.set(GregorianCalendar.HOUR_OF_DAY, 23);
		date.set(GregorianCalendar.MINUTE, 59);
		date.set(GregorianCalendar.SECOND, 59);
		return date;
	}
	
	/**
	 * Return a {@link GregorianCalendar} representation of the string using the given pattern
	 * @param string - a String object to be passed
	 * @param pattern - the pattern. If <code>null</code> "yyyy-MM-dd HH:mm:ss" will be used
	 * @param noTime - if <code>True</code> the time will be 00:00:00, actual time otherwise.
	 * @return the String representation of the GregorianCalendar
	 * @throws ParseException 
	 */
	public static GregorianCalendar parseDate(String string, String pattern, boolean noTime) throws ParseException {
		if (pattern == null) pattern = "yyyy-MM-dd HH:mm:ss";
		SimpleDateFormat format = new SimpleDateFormat(pattern);  //$NON-NLS-1$
		Date date = format.parse(string);
		GregorianCalendar calendar = new GregorianCalendar();
		if (noTime) {
			calendar.setTime(date);
		} else {
			calendar.setTimeInMillis(date.getTime());
		}
		//System.out.println(formatDateTime(calendar, null));

		return calendar;
	}

	public static GregorianCalendar getBeginningOfDay(GregorianCalendar date) {
		return new DateTime(date).withTimeAtStartOfDay().toGregorianCalendar();
	}

	public static GregorianCalendar getBeginningOfNextDay(GregorianCalendar date) {
		return new DateTime(date).plusDays(1).withTimeAtStartOfDay().toGregorianCalendar();
	}
	
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////


	/**
	 * @author Mwithi
	 *
	 * returns the difference in days between two dates
	 * @param from
	 * @param to
	 * @return the number of days
	 */
	public static int getDaysBetweenDates(GregorianCalendar from, GregorianCalendar to) {

		DateTime dateFrom = new DateTime(from);
		DateTime dateTo = new DateTime(to);
		Period period = new Period(dateFrom, dateTo, PeriodType.days());
		return period.getDays();
	}

	/**
	 * @author Mwithi
	 *
	 * returns the difference in days between two dates
	 * @param from
	 * @param to
	 * @return the number of days
	 */
	public static int getDaysBetweenDates(Date from, Date to) {
		DateTime dateFrom = new DateTime(from);
		DateTime dateTo = new DateTime(to);
		Period period = new Period(dateFrom, dateTo, PeriodType.days());
		return period.getDays();
	}

	/**
	 * @author Mwithi
	 *
	 * returns the difference in weeks between two dates
	 * @param from
	 * @param to
	 * @return the number of weeks
	 */
	public static int getWeeksBetweenDates(GregorianCalendar from, GregorianCalendar to) {
		DateTime dateFrom = new DateTime(from);
		DateTime dateTo = new DateTime(to);
		Period period = new Period(dateFrom, dateTo, PeriodType.weeks());
		return period.getWeeks();
	}

	/**
	 * @author Mwithi
	 *
	 * returns the difference in months between two dates
	 * @param from
	 * @param to
	 * @return the number of months
	 */
	public static int getMonthsBetweenDates(GregorianCalendar from, GregorianCalendar to) {
		DateTime dateFrom = new DateTime(from);
		DateTime dateTo = new DateTime(to);
		Period period = new Period(dateFrom, dateTo, PeriodType.months());
		return period.getMonths();
	}

	public static GregorianCalendar getDate(String strDate, String format) throws ParseException{
		try {
			SimpleDateFormat sdf = new SimpleDateFormat(format);
			Date date=sdf.parse(strDate);
			if(date!=null){
				GregorianCalendar calDate=new GregorianCalendar();
				calDate.setTime(date);
				return calDate;
			}
		} catch (ParseException e) {
			if(!format.equals("dd/MM/yyyy")){
				SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
				Date date=sdf.parse(strDate);
				if(date!=null){
					GregorianCalendar calDate=new GregorianCalendar();
					calDate.setTime(date);
					return calDate;
				}
			}
		}
		return null;
	}
	/**
	 * Return the actual date and time of the server
	 *
	 * @author hadesthanos
	 * @return DateTime
	 * @throws OHException
	 * @throws ParseException
	 */
	public static GregorianCalendar getServerDateTime()  {
		GregorianCalendar serverDate=new GregorianCalendar();
		String query = " SELECT NOW( ) as time ";

		DbQueryLogger dbQuery = new DbQueryLogger();
		try {
			ResultSet resultSet = dbQuery.getData(query, true);
			while (resultSet.next()) {
				String date = resultSet.getString("time");
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				java.util.Date utilDate = new java.util.Date();
				utilDate = sdf.parse(date);
				serverDate.setTime(utilDate);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (OHException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return serverDate;
	}
	
	/**
	 * Convert GregorianCalendar -> String using format "dd/MM/yy"
	 * @param time - a Calendar datetime
	 * @return a String representing the Calendar in the format "dd/MM/yy" 
	 * @deprecated use formatDateTime(GregorianCalendar dateTime, String pattern) instead
	 */
    public static String getConvertedString(GregorianCalendar time) {
		if (time == null)
			return MessageBundle.getMessage("angal.malnutrition.nodate");
		String string = String
				.valueOf(time.get(GregorianCalendar.DAY_OF_MONTH));
		string += "/" + String.valueOf(time.get(GregorianCalendar.MONTH) + 1);
		String year = String.valueOf(time.get(GregorianCalendar.YEAR));
		year = year.substring(2, year.length());
		string += "/" + year;
		return string;
	}

    /**
     * Convert String -> Date using pattern "ddMMyy" and the server time
     * ( SELECT NOW() as time )
     * @param string - a date in the form ddMMyy
     * @return a Calendar datetime
     * @throws ParseException
     * @deprecated use getDate(String strDate, String format) instead 
     */
    public static GregorianCalendar convertToDate(String string) throws ParseException {
		GregorianCalendar date = TimeTools.getServerDateTime();
		SimpleDateFormat sdf = new SimpleDateFormat("ddMMyy");
		date.setTime(sdf.parse(string));
		return date;
	}
}
