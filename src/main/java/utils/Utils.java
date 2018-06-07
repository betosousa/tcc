package utils;

import java.io.File;
import java.io.FileInputStream;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.apache.commons.io.IOUtils;

public class Utils {
	
	
	private static DateFormat df = new SimpleDateFormat(Strings.DATE_FORMAT);
	
	public static String readFile(File file){
		try {
			FileInputStream input = new FileInputStream(file);
			String text = IOUtils.toString(input);
			input.close();
			return text;
		} catch (Exception e) {
			throw new RuntimeException("Error reading file: "
					+ file.getAbsolutePath(), e);
		}
	}

	public static int countLineNumbers(String str) {
		int lines = 0;
		if (str != null && !str.isEmpty()) {
			int pos = 0;
			do {
				lines++;
			} while ((pos = str.indexOf("\n", pos) + 1) != 0);
		}
		return lines;
	}
	
	public static Calendar calendarFromDateString(String dateString){
		Date fromDate = new Date();
		try {
			fromDate = df.parse(dateString);
		} catch (ParseException e) {
			LoggerManager.getLogger("Utils").logMessage(e.getMessage(), e);
		} 
		Calendar cal = new GregorianCalendar();
		cal.setTime(fromDate);
		return cal;
	}
	
	public static String formatCalendar(Calendar date){
		return df.format(date.getTime());
	}
}
