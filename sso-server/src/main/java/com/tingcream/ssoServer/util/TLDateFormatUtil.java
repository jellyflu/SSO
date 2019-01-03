package com.tingcream.ssoServer.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *  日期--时间转换工具类
 *   线程安全
 * @author jelly
 *
 */
public class TLDateFormatUtil {

	   private static ThreadLocal<SimpleDateFormat> threadLocal = new ThreadLocal<SimpleDateFormat>() {
	        @Override
	        protected SimpleDateFormat initialValue() {
	            return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	        }
	    };

	    public static Date parse(String dateStr) throws ParseException {
	    	 
	        return threadLocal.get().parse(dateStr);
	    }

	    public static String format(Date date) {
	        return threadLocal.get().format(date);
	    }
}

