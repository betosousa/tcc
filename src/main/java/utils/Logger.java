package utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Date;

public class Logger {

	private static PrintWriter log;

	public static void logMessage(String msg, Throwable e) {
		if(log == null){
			initLog();
		}
		log.println(new Date());
		if (msg != null)
			log.println(msg);
		if (e != null)
			log.println(e.getStackTrace());
	}

	public static void initLog(){
		try {
			File f = new File("logger.txt");
			f.delete();
			log = new PrintWriter(f, "UTF-8");
		} catch (FileNotFoundException | UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}
	
	public static void closeLog(){
		log.close();
	}
}
