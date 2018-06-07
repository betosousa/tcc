package utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Date;

public class Logger {
	
	private static final String FILE_ENCODING = "UTF-8";
	
	private String logger_file = "logger.log";	
	private String repoName;
	private PrintWriter log;
	
	public Logger(String filePath) {
		this.logger_file = filePath+".log";
		this.repoName = filePath;
		initLog();
	}
	
	
	public void logMessage(String msg, Throwable e) {
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
	
	public void logMessage(String msg){
		logMessage(msg, null);
	}

	public void initLog(){
		try {
			File f = new File(logger_file);
//			f.delete();
			log = new PrintWriter(f, FILE_ENCODING);
		} catch (FileNotFoundException | UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}
	
	public void closeLog(){
		log.close();
	}


	public String getRepoName() {
		return repoName;
	}
}
