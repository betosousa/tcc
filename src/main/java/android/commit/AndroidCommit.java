package android.commit;

import java.util.Map;
import java.util.Map.Entry;

import interfaces.IPermissionAnalyzer;

import org.repodriller.domain.Commit;

import utils.AndroidManifestParser;
import android.AndroidManifest;
import android.diff.ActivityDiff;
import android.diff.ManifestDiff;
import android.permission.PermissionMap;
import android.permission.wrapper.PermissionAnalyzerWrapper;

public class AndroidCommit extends Commit {

	private PermissionMap permissionMap;
	private String apkFilePath;
	private String androidManifestCode;
	private IPermissionAnalyzer permissionAnalyzer;
	private AndroidManifest androidManifest;
	private ActivityDiff activityDiff;
	private Map<String, String> manifestsMap;
	private String path;
	private ManifestDiff manifestDiff;

	public AndroidCommit(Commit commit, String apkFilePath, Map<String, String> manifestsMap, String path) {
		super(commit.getHash(), commit.getAuthor(), commit.getCommitter(),
				commit.getDate(), commit.getAuthorTimeZone(), commit.getCommitterDate(),
				commit.getCommitterTimeZone(), commit.getMsg(), commit.getParent(), 
				commit.isMerge(), commit.getBranches(), commit.isInMainBranch());
		
		this.apkFilePath = apkFilePath;
		this.permissionAnalyzer = new PermissionAnalyzerWrapper();
		this.manifestsMap =  manifestsMap;
		this.path = path;
		
		if (manifestsMap != null && manifestsMap.entrySet().size() == 1) {
			for(Entry<String, String> entry : manifestsMap.entrySet()){
				this.androidManifestCode = entry.getValue();
			}
		}
		
		this.addModifications(commit.getModifications());
	}

	private ManifestDiff getManifestDiff(){
		if(manifestDiff == null){
			manifestDiff = new ManifestDiff(getModifications(), manifestsMap, path);
		}
		return manifestDiff;
	}
	
	public PermissionMap getPermissionMap() {
		if (permissionMap == null) {
			permissionMap = new PermissionMap(permissionAnalyzer.generateJSON(apkFilePath));
		}
		return permissionMap;
	}

	public String getAndroidManifestCode() {
		return androidManifestCode;
	}

	public AndroidManifest getAndroidManifest() {
		if (androidManifest == null && androidManifestCode != null) {
			androidManifest = AndroidManifestParser.parse(androidManifestCode);
		}
		return androidManifest;
	}
	
	public ActivityDiff getActivityDiff(){
		if(activityDiff == null){
			activityDiff = new ActivityDiff(getManifestDiff());
		}
		return activityDiff;
	}

	public Map<String, String> getManifestsMap() {
		return manifestsMap;
	}

	public void setManifestsMap(Map<String, String> manifestsMap) {
		this.manifestsMap = manifestsMap;
	}
	
	
}
