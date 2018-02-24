package android.permission;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;

public class PermissionMap {
	public static final String DECLARED = "declared";
	public static final String REQUIRED_NOT_USED = "requiredButNotUsed";
	public static final String REQUIRED_AND_USED = "requiredAndUsed";
	public static final String USED_NOT_REQUIRED = "notRequiredButUsed";
	
	private Map<String, List<String>> map;
	private JSONObject json;

	public PermissionMap(String jsonStr){
		this.map = new HashMap<String, List<String>>();
		this.json = null;
		if (!jsonStr.isEmpty()) {
			this.json = new JSONObject(jsonStr);
		}
		parseJSON();
	}
	
	private void parseJSON(){
		parsePermissionList(DECLARED);
		parsePermissionList(REQUIRED_AND_USED);
		parsePermissionList(REQUIRED_NOT_USED);
		parsePermissionList(USED_NOT_REQUIRED);
	}
	
	private void parsePermissionList(String key) {
		List<String> permissions = new ArrayList<String>();
		if (json != null) {
			JSONArray arr = json.getJSONArray(key);
			for (int i = 0; i < arr.length(); i++) {
				String str = arr.getString(i);
				permissions.add(str);
			}
		}
		map.put(key, permissions);
	}
	
	public List<String> getPermissions(String type){
		return map.get(type);
	}
	
	@Override
	public String toString() {
		StringBuilder str = new StringBuilder();
		if (json != null) {
			str.append(json.toString());
		}
		return str.toString();
	}
}
