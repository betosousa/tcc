package android.permission.wrapper;

import interfaces.IPermissionAnalyzer;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import org.json.JSONObject;

public class PermissionAnalyzerWrapper implements IPermissionAnalyzer {
	
	private static final String LIB_PATH = "D:\\11p\\TCC\\libs\\"; // "D:/11p/TCC/libs";
	private static final String _JAR = "-jar";
	private static final String JAVA = "java";
	private static final String LIB_JAR = "PermissionChecker.jar";

	@Override
	public String generateJSON(String filePath) {
		String json = "";
		if (filePath != null && !filePath.isEmpty()) {
			ProcessBuilder pb = new ProcessBuilder(JAVA, _JAR, LIB_PATH
					+ LIB_JAR, filePath);
			pb.directory(new File(LIB_PATH));
			try {
				Process p = pb.start();
				p.waitFor();
				json = readStream(p.getInputStream(), "Results");
			} catch (IOException | InterruptedException e) {
				e.printStackTrace();
			}
		}
		return json;
	}

	@Override
	public JSONObject generateJSONObject(String filePath) {
		return new JSONObject(generateJSON(filePath));
	}

	private static String readStream(InputStream in, String header) {
		//System.out.println(header + ":");
		String result = "";
		try {
			byte b[] = new byte[in.available()];
			in.read(b, 0, b.length);
			result = new String(b);
			//System.out.println(result);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return result;
	}

}
