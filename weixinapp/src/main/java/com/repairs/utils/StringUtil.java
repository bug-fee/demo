package com.repairs.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class StringUtil {
	public static String currentTime(){
		SimpleDateFormat fmt=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date=new Date();
		return fmt.format(date);
	}

}
