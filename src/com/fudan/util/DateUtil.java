package com.fudan.util;

import java.sql.Date;
import java.util.Calendar;

public class DateUtil {
	
	final static String dateFormat = "%d-%02d-%02d";  // 可自定义的时间格式，用于前台显示
	private int year, month, day;
	// 当前对象是否被初始化。若为false，则该对象没有保存有效的时间信息。这在诸如order的checkDate属性上很有必要，因为订单并不一定被check
	private boolean initialized;  
	
	/** 获得一个表示当前时间的DateUtil对象
	 *  2015/07/21
	 */
	public DateUtil() {
		Calendar now = Calendar.getInstance();
		this.year = now.get(Calendar.YEAR);
		this.month = now.get(Calendar.MONTH) + 1;
		this.day = now.get(Calendar.DATE);
		initialized = true;
	}
	
	/** 获得一个DateUtil对象，日期和时间由输入的字符串决定
	 *  2015/07/21
	 */
	public DateUtil(String dateStr) {
		try {
			int[] i = parseDateStr(dateStr);  // dateStr形如  "yyyy-MM-dd"
			this.year  = i[0];
			this.month = i[1];
			this.day   = i[2];
			initialized = true;
		} catch (Exception e) {
			initialized = false;  // 当dateStr非法时，如未设置checkDate时传入的参数有可能为空字符串
		}
	}
	
	/** 得到两个时间相差的天数
	 *  输入：用于和当前DateUtil比较的DateUtil对象
	 *  输出：int 相差的天数。如果当前日期在other之前，返回值小于0。如果有一个DateUtil对象未被初始化，也返回0
	 *  2015/07/21
	 */
	public int getDeltaDay(DateUtil other) {
		if (this.initialized && other.initialized) {
			long ms = this.getCalendar().getTimeInMillis() - other.getCalendar().getTimeInMillis();  // 得到毫秒
			return (int) (ms / 1000 / 60 / 60 / 24);  // 得到天数
		} else {
			return 0;
		}
	}
	
	/**
	 * 得到和当前时间对象相差n天的时间对象
	 * 输入：int：nDays
	 * 输出：新的DateUtil对象
	 * 2015/07/31
	 */
	public DateUtil add(int nDays) {
		Calendar time = this.getCalendar();
		time.add(Calendar.DATE, nDays);
		DateUtil retDate = new DateUtil();
		retDate.year = time.get(Calendar.YEAR);
		retDate.month = time.get(Calendar.MONTH) + 1;
		retDate.day = time.get(Calendar.DATE);
		retDate.initialized = true;
		return retDate;
	}
	
	/*// 判断当前时间是否在指定的时间范围内
	public boolean in(DateUtil beginDate, DateUtil endDate) {
		return (this.earlierOrEqualThan(endDate) && this.laterOrEqualThan(beginDate));
	}*/
	
	/** 根据给定的时间格式输出时间字符串 （用于前台显示）
	 *  输出：String；null：当前对象未被初始化
	 *  2015/07/21
	 */
	public String toString() {
		if (initialized) {
			return String.format(dateFormat, year, month, day);
		} else {
			return null;
		}
	}
	
	/** 得到用于SQL查询语句的时间字符串 （用于后台根据时间的搜索功能）
	 *  输出：String；null：当前对象未被初始化
	 *  2015/07/22 增加包围时间的单引号
	 */
	public String toSQLString() {
		if (initialized) {
			return String.format("'%d-%02d-%02d'", year, month, day);
		} else {
			return null;
		}
	}
	
	/** 得到存入数据库的sql.Date对象
	 *  输出：String；null：当前对象未被初始化
	 *  2015/07/21
	 */
	public Date toSQLDate() {
		if (initialized) {
			long ms = this.getCalendar().getTimeInMillis();
			return new Date(ms);
		} else {
			return null;
		}

	}
	
	/** 得到用于生成订单号的表示当前时间的时间字符串 （yyyyMMdd）
	 *  输出：String
	 *  2015/07/20
	 */
	public String toOrderString() {
		return String.format("%d%02d%02d", year, month, day);
	}
	/*// 判断两个时间的大小
	public boolean laterOrEqualThan(DateUtil other) {
		return (this.getDeltaDay(other) >= 0);
	}
	
	// 判断两个时间的大小
	public boolean earlierOrEqualThan(DateUtil other) {
		return (this.getDeltaDay(other) <= 0);
	}*/
	
	/** 根据（从数据库获得的）SQL时间格式解析出年月日 （用于构造DateUtil对象）
	 *  输入：dateStr表示时间的字符串
	 *  输出：int[] 元素依次为 年 月 日
	 *  2015/07/20
	 */
	private int[] parseDateStr(String dateStr) {
		String[] s = dateStr.split("-|\\s");
		return new int[] {Integer.parseInt(s[0]), Integer.parseInt(s[1]), Integer.parseInt(s[2])};	
	}
	
	/** 得到表示当前时间的Calendar对象 （用于时间的比较）
	 *  输出：Calendar
	 *  2015/07/20
	 */
	private Calendar getCalendar() {
		Calendar c = Calendar.getInstance();  // 得到Calendar
		c.clear();  // 清除当前的时间信息
		c.set(year, month - 1, day);  // 赋值为设定的时间
		return c;
	}

	public boolean isInitialized() {
		return initialized;
	}

}
