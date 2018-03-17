package android;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import android.data.Application;
import android.data.Component;
import android.data.ComponentType;
import android.data.IntentFilter;

public class AndroidManifest {
	public static final String FILE_NAME = "AndroidManifest.xml";
	
	public String appPackage;
	public List<Component> components;
	public List<String> permissions;
	
	public int minSdkVersion;
	public int targetSdkVersion;
	
	public Application application;
	public Map<String, List<IntentFilter>> intentFilters;
	
	public AndroidManifest(){
		this.permissions = new LinkedList<String>();
		this.components = new LinkedList<Component>();
		this.intentFilters = new HashMap<String, List<IntentFilter>>();
		this.application = new Application();
	}
	
	@SuppressWarnings("unchecked")
	public <T extends Component> List<T> getComponents(ComponentType type){
		return (List<T>) components.stream()
				.filter(c -> c.type.equals(type)).collect(Collectors.toList());
	}
	
}
