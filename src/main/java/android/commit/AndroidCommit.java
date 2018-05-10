package android.commit;

import interfaces.IPermissionAnalyzer;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.repodriller.domain.Commit;
import org.repodriller.domain.Modification;

import utils.AndroidManifestParser;
import android.AndroidManifest;
import android.diff.ActivityDiff;
import android.diff.BroadcastReceiverDiff;
import android.diff.ContentProviderDiff;
import android.diff.ManifestDiff;
import android.diff.PermissionDiff;
import android.diff.ServiceDiff;
import android.diff.UsesPermissionDiff;
import android.permission.PermissionMap;
import android.permission.wrapper.PermissionAnalyzerWrapper;

public class AndroidCommit extends Commit {

	private PermissionMap permissionMap;
	private String apkFilePath;
	private String androidManifestCode;
	private IPermissionAnalyzer permissionAnalyzer;
	private AndroidManifest androidManifest;
	private Map<String, String> manifestsMap;
	private String path;
	private ManifestDiff manifestDiff;
	private ActivityDiff activityDiff;
	private ServiceDiff serviceDiff;
	private BroadcastReceiverDiff broadcastReceiverDiff;
	private ContentProviderDiff contentProviderDiff;
	private PermissionDiff permissionDiff;	
	private UsesPermissionDiff usesPermissionDiff;
	
	private List<Modification> manifestModifications;
	
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
		
		this.manifestModifications = ManifestDiff.getManifestModifications(getModifications());
	}

	private ManifestDiff getManifestDiff(){
		if(manifestDiff == null){
			manifestDiff = new ManifestDiff(manifestModifications, manifestsMap, path, false);
		}
		return manifestDiff;
	}
	
	public PermissionMap getPermissionMap() {
		if (permissionMap == null) {
			permissionMap = new PermissionMap(permissionAnalyzer.generateJSON(apkFilePath));
		}
		return permissionMap;
	}
	
	public boolean hasAndroidModifications(){
		return !manifestModifications.isEmpty();
	}
	
	public String getAndroidManifestCode() {
		return androidManifestCode;
	}
	
	
	public Map<String, String> getManifestsMap() {
		return manifestsMap;
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
	
	public ServiceDiff getServiceDiff(){
		if(serviceDiff == null){
			serviceDiff = new ServiceDiff(getManifestDiff());
		}
		return serviceDiff;
	}
	
	public BroadcastReceiverDiff getBroadcastReceiverDiff(){
		if(broadcastReceiverDiff == null){
			broadcastReceiverDiff = new BroadcastReceiverDiff(getManifestDiff());
		}
		return broadcastReceiverDiff;
	}
	
	public ContentProviderDiff getContentProviderDiff(){
		if(contentProviderDiff == null){
			contentProviderDiff = new ContentProviderDiff(getManifestDiff());
		}
		return contentProviderDiff;
	}
	
	public PermissionDiff getPermissionDiff(){
		if(permissionDiff == null){
			permissionDiff = new PermissionDiff(getManifestDiff());
		}
		return permissionDiff;
	}
	
	public UsesPermissionDiff getUsesPermissionDiff(){
		if(usesPermissionDiff == null){
			usesPermissionDiff = new UsesPermissionDiff(getManifestDiff());
		}
		return usesPermissionDiff;
	}
}
