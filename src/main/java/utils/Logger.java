package utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Date;

public class Logger {
	
	private static final String LOGGER_FILE = "logger.txt";
	private static final String FILE_ENCODING = "UTF-8";
	
	private static PrintWriter log;

	public static void logMessage(String msg, Throwable e) {
		if(log == null){
			initLog();
		}
		log.println(new Date());
		log.println(Strings.COMMIT_DATE_LOG + ProgressUtil.getInstance().getActualCommitDate());
		if (msg != null)
			log.println(msg);
		if (e != null)
			log.println(e.getStackTrace());
	}

	public static void initLog(){
		try {
			File f = new File(LOGGER_FILE);
			f.delete();
			log = new PrintWriter(f, FILE_ENCODING);
		} catch (FileNotFoundException | UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}
	
	public static void closeLog(){
		log.close();
	}
}
