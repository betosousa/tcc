package logger;

import java.io.File;
import java.io.IOException;

import utils.Strings;

public class ProgressLogger {
	private static File progressFile;
	
	public static void updateProgress(int current, int total) {
		if(progressFile != null && progressFile.exists()) {
			progressFile.delete();
		}
		progressFile = new File(current+"_"+total);
		try {
			progressFile.createNewFile();
		} catch (IOException e) {
			LoggerManager.getLogger(Strings.MAIN).logMessage("Error updating progress", e);
		}
	}
}
