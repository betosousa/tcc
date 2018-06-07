package utils;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class LoggerManager {

	private static final String LOG_PATH = "logs";

	private static Map<String, Logger> loggers = new HashMap<>();

	public static Logger getLogger(String repoName) {
		String logFilePath = getLogFolderPath() + repoName;
		Logger logger = loggers.get(repoName); 
		if (logger == null) {
			logger = new Logger(logFilePath);
			loggers.put(repoName, logger);
		}
		return logger;
	}

	private static String getLogFolderPath() {
		File logFolder = new File(LOG_PATH);
		if (!logFolder.exists() || !logFolder.isDirectory()) {
			logFolder.mkdirs();
		}
		return LOG_PATH + File.separator;
	}

	public static void closeLog(String repoName) {
		Logger logger = loggers.get(repoName); 
		if (logger != null) {
			logger.closeLog();
			loggers.remove(repoName);
		}
	}
	
	public static void closeLogs() {
		for (Logger logger : loggers.values()) {
			logger.closeLog();
		}
	}
}
