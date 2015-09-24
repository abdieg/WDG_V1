package org.application.common;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Utilities {
	
	public String getTodaysDate() {
		DateFormat format = new SimpleDateFormat(Constant.dateFormat);
		return format.format(new Date());
	}
	
	public String getYesterdaysDate() {
		DateFormat format = new SimpleDateFormat(Constant.dateFormat);
		return format.format(new Date(System.currentTimeMillis() - 1000L * 60 * 60 * 24)); // 24 hours ago
	}
	
	public String getTomorrowsDate() {
		DateFormat format = new SimpleDateFormat(Constant.dateFormat);
		return format.format(new Date(System.currentTimeMillis() + 1000L * 60 * 60 * 24)); // 24 hours after today
	}
	
	public double roundDoubleToPlaces(double value, int places) {
		return new BigDecimal(value).setScale(places, RoundingMode.HALF_UP).doubleValue();
	}

}
