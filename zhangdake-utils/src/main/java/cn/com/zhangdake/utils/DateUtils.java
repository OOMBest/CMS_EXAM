package cn.com.zhangdake.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

/**
 * Author: ZhangDaKe
 * Date: 2019年9月6日
 * Describe:日期工具类
 * Version:1.0
 * 
 * 1.0:初版
 * parseDate(String date, String pattern) 格式化String类型的日期
 * getAgeByBirthday(String birthday) 根据生日获取年龄
 * getAgeByBirthday(Date birthday) 根据生日获取年龄
 * getDateByMonthLast(String date) 根据date获取当前月份的最后一天的时间
 * getDateByMonthLast(Date date) 根据date获取当前月份的最后一天的时间
 * getDateByMonthFirst(String date) 根据date获取当前月份的第一天
 * getDateByMonthFirst(Date date) 根据date获取当前月份的第一天
 * getRandomDateBetweenAnd(Date start, Date end, int count, boolean isHMS) 随机获取一个日期 在start和end之间
 * getRandomDateBetweenAnd(String start, String end, int count, boolean isHMS) 随机获取一个日期 在start和end之间
 * 
 */
public final class DateUtils {

	public static final String DEFAULT_DATE_PATTERN = "yyyy-MM-dd";

	private DateUtils() {
		throw new IllegalStateException("this class can`t be created by constructor");
	}

	/**
	 * 格式化String类型的日期
	 * 
	 * @param date 输入日期
	 * @param pattern 日期格式
	 * @return null输入格式错误
	 */
	public static Date parseDate(String date, String pattern) {
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		Date d = null;
		try {
			d = sdf.parse(date);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return d;
	}

	/**
	 * 根据生日获取年龄
	 * 
	 * @param birthday 生日 格式为yyyy-MM-dd的形式 例如：1994-02-03
	 * @return -1参数格式错误
	 *         -2来自于未来的错误
	 */
	public static int getAgeByBirthday(String birthday) {
		return getAgeByBirthday(parseDate(birthday, DEFAULT_DATE_PATTERN));
	}

	/**
	 * 根据生日获取年龄
	 * 
	 * @param birthday 生日
	 * @return -1参数格式错误
	 *         -2来自于未来的错误
	 */
	public static int getAgeByBirthday(Date birthday) {

		if (birthday == null) {
			// date error
			return -1;
		}

		Calendar cal = Calendar.getInstance();

		if (cal.before(birthday)) {
			// Future person????
			return -2;
		}

		int yearNow = cal.get(Calendar.YEAR);
		int monthNow = cal.get(Calendar.MONTH);
		int dayOfMonthNow = cal.get(Calendar.DAY_OF_MONTH);

		cal.setTime(birthday);
		int yearBirth = cal.get(Calendar.YEAR);
		int monthBirth = cal.get(Calendar.MONTH);
		int dayOfMonthBirth = cal.get(Calendar.DAY_OF_MONTH);

		int age = yearNow - yearBirth;

		if (monthNow < monthBirth) {
			return --age;
		}

		if (monthNow == monthBirth && dayOfMonthNow < dayOfMonthBirth) {
			return --age;
		}

		return age;
	}

	/**
	 * 根据date获取当前月份的最后一天的时间 例如 2009-01-19则返回 2009-01-31 23:59:59
	 * 
	 * @param date
	 * @return null无效参数 或 输入格式错误
	 */
	public static Date getDateByMonthLast(String date) {
		return getDateByMonthLast(parseDate(date, DEFAULT_DATE_PATTERN));
	}

	/**
	 * 根据date获取当前月份的最后一天的时间 例如 2009-01-19则返回 2009-01-31 23:59:59
	 * 
	 * @param date
	 * @return null无效参数
	 */
	public static Date getDateByMonthLast(Date date) {

		if (date == null) {
			return null;
		}

		Calendar cal = Calendar.getInstance();
		cal.setTime(date);

		// 获取当前月份的最后一天
		cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(Calendar.DAY_OF_MONTH));
		cal.set(Calendar.HOUR_OF_DAY, 23);
		cal.set(Calendar.MINUTE, 59);
		cal.set(Calendar.SECOND, 59);

		return cal.getTime();
	}

	/**
	 * 根据date获取当前月份的第一天的时间 例如 2009-01-19则返回 2009-01-01 00:00:00
	 * 
	 * @param date
	 * @return null无效参数
	 */
	public static Date getDateByMonthFirst(String date) {
		return getDateByMonthFirst(parseDate(date, DEFAULT_DATE_PATTERN));
	}

	/**
	 * 根据date获取当前月份的第一天的时间 例如 2009-01-19则返回 2009-01-01 00:00:00
	 * 
	 * @param date
	 * @return null无效参数
	 */
	public static Date getDateByMonthFirst(Date date) {

		if (date == null) {
			return null;
		}

		Calendar cal = Calendar.getInstance();
		cal.setTime(date);

		// 获取当前月份的第一天
		cal.set(Calendar.DAY_OF_MONTH, cal.getActualMinimum(Calendar.DAY_OF_MONTH));
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);

		return cal.getTime();
	}

	/**
	 * 在start和end之间 随机获取一个时间信息
	 * 如果start在end之后 则会互换start和end的位置
	 * 
	 * @param start 时间段开始的时间 例如 2009-01-19
	 * @param end 时间段结束的时间 例如 2010-03-22
	 * @param count 随机获取的数量
	 * @param isHMS 是否随机获取时分秒
	 *            true获取
	 *            false不获取
	 * @return null参数错误
	 */
	public static Date[] getRandomDateBetweenAnd(String start, String end, int count,
			boolean isHMS) {
		return getRandomDateBetweenAnd(parseDate(start, DEFAULT_DATE_PATTERN),
				parseDate(end, DEFAULT_DATE_PATTERN), count, isHMS);
	}

	/**
	 * 在start和end之间 随机获取一个时间信息
	 * 如果start在end之后 则会互换start和end的位置
	 * 该方法需要JDK 1.8
	 * 
	 * @param start 时间段开始的时间
	 * @param end 时间段结束的时间
	 * @param count 随机获取的数量
	 * @param isHMS 是否随机获取时分秒
	 *            true获取
	 *            false不获取(默认为0时0分0秒)
	 * @return null参数错误
	 */
	public static Date[] getRandomDateBetweenAnd(Date start, Date end, int count,
			boolean isHMS) {
		if (start == null || end == null || count < 1) {
			return null;
		}

		// 如果start在end之后 则互换start end的位置
		if (start.after(end)) {
			Date temp = start;
			start = end;
			end = temp;
		}

		Calendar calStart = Calendar.getInstance();
		calStart.setTime(start);
		Calendar calEnd = Calendar.getInstance();
		calEnd.setTime(end);

		Random random = new Random();

		Date[] dates = new Date[count];
		for (int i = 0; i < count; i++) {
			dates[i] = randomDate(calStart, calEnd, random, isHMS);
		}

		return dates;

		// 简易代码 但是效率低 约慢4倍
		/*		long startTime = start.getTime();
		long endTime = end.getTime();
		
		Random random = new Random();
		LongStream stream = random.longs(count, startTime, endTime);
		long[] result = stream.toArray();
		
		Date[] dates = new Date[result.length];
		for (int i = 0 ; i < result.length ; i++) {
			dates[i] = new Date(result[i]);
		}
		
		return dates;*/
	}

	/**
	 * 随机获取一个日期 在start和end之间
	 * 
	 * @param calStart 时间段开始的时间
	 * @param calEnd 时间段结束的时间
	 * @param random 产生随机数的对象
	 * @param isHMS 是否随机获取时分秒
	 *            true获取
	 *            false不获取
	 * @return
	 */
	private static Date randomDate(Calendar calStart, Calendar calEnd, Random random,
			boolean isHMS) {

		Calendar calendar = Calendar.getInstance();

		// -----------------------随机生成年-----------------------
		int year;
		int startYear = calStart.get(Calendar.YEAR);
		int endYear = calEnd.get(Calendar.YEAR);
		if (startYear != endYear) {
			year = startYear + random.nextInt(endYear - startYear + 1);
		} else {
			year = startYear;
		}
		calendar.set(Calendar.YEAR, year);
		// -----------------------随机生成年-----------------------

		// -----------------------随机生成月-----------------------
		int month;
		int startMonth = calStart.get(Calendar.MONTH);
		int endMonth = calEnd.get(Calendar.MONTH);
		if (year == startYear) {
			month = startMonth + random.nextInt(12 - startMonth);
		} else if (year == endYear) {
			month = random.nextInt(endMonth + 1);
		} else {
			month = random.nextInt(12);
		}
		calendar.set(Calendar.MONTH, month);
		// -----------------------随机生成月-----------------------

		// -----------------------随机生成日-----------------------
		int day;
		int startDay = calStart.get(Calendar.DAY_OF_MONTH);
		int endDay = calEnd.get(Calendar.DAY_OF_MONTH);
		if (year == startYear && month == startMonth) {
			int monthEndDay = calStart.getActualMaximum(Calendar.DAY_OF_MONTH);
			day = startDay + random.nextInt(monthEndDay - startDay + 1);
		} else if (year == endYear && month == endMonth) {
			day = random.nextInt(endDay);
		} else {
			day = random.nextInt(calendar.getActualMaximum(Calendar.DAY_OF_MONTH) + 1);
		}
		calendar.set(Calendar.DAY_OF_MONTH, day);
		// -----------------------随机生成日-----------------------

		// -----------------------随机生成时分秒-----------------------
		if (isHMS) {

			// ---------------------随机生成小时---------------------
			int hour;
			int startHour = calStart.get(Calendar.HOUR_OF_DAY);
			int endHour = calEnd.get(Calendar.HOUR_OF_DAY);
			if (year == startYear && month == startMonth && day == startDay) {
				hour = startHour + random.nextInt(24 - startHour);
			} else if (year == endYear && month == endMonth && day == endDay) {
				hour = random.nextInt(endHour);
			} else {
				hour = random.nextInt(24);
			}
			calendar.set(Calendar.HOUR_OF_DAY, hour);
			// ---------------------随机生成小时---------------------

			// ---------------------随机生成分钟---------------------
			int minute;
			int startMinute = calStart.get(Calendar.MINUTE);
			int endMinute = calEnd.get(Calendar.MINUTE);
			if (year == startYear && month == startMonth && day == startDay
					&& hour == startHour) {
				minute = startMinute + random.nextInt(60 - startMinute);
			} else if (year == endYear && month == endMonth && day == endDay
					&& hour == endHour) {
				minute = random.nextInt(endMinute);
			} else {
				minute = random.nextInt(60);
			}
			calendar.set(Calendar.MINUTE, minute);
			// ---------------------随机生成分钟---------------------

			// ---------------------随机生成秒---------------------
			int second;
			int startSecond = calStart.get(Calendar.SECOND);
			int endSecond = calEnd.get(Calendar.SECOND);
			if (year == startYear && month == startMonth && day == startDay
					&& hour == startHour && minute == startMinute) {
				second = startSecond + random.nextInt(60 - startSecond);
			} else if (year == endYear && month == endMonth && day == endDay
					&& hour == endHour && minute == endMinute) {
				second = random.nextInt(endSecond);
			} else {
				second = random.nextInt(60);
			}
			calendar.set(Calendar.SECOND, second);
			// ---------------------随机生成秒---------------------

		} else {
			calendar.set(Calendar.HOUR_OF_DAY, 0);
			calendar.set(Calendar.MINUTE, 0);
			calendar.set(Calendar.SECOND, 0);
		}
		// -----------------------随机生成时分秒-----------------------

		return calendar.getTime();
	}

}
