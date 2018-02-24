package interfaces;

import org.json.JSONObject;

public interface IPermissionAnalyzer {
	String generateJSON(String filePath);
	JSONObject generateJSONObject(String filePath);
}
