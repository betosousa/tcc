package utils;

import java.io.File;
import java.io.FileInputStream;

import org.apache.commons.io.IOUtils;

public class Utils {
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
}
