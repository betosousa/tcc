package android.commit;

import interfaces.IPermissionAnalyzer;

import java.util.List;
import java.util.Map;

import org.repodriller.domain.Commit;
import org.repodriller.domain.Modification;
import org.repodriller.scm.SCM;

import utils.CommitFilesManager;
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
	private SCM repoSCM;
	
	
	public AndroidCommit(Commit commit, SCM repoSCM, String path) {
		super(commit.getHash(), commit.getAuthor(), commit.getCommitter(),
				commit.getDate(), commit.getAuthorTimeZone(), commit.getCommitterDate(),
				commit.getCommitterTimeZone(), commit.getMsg(), commit.getParent(), 
				commit.isMerge(), commit.getBranches(), commit.isInMainBranch());
		
		this.permissionAnalyzer = new PermissionAnalyzerWrapper();
		this.path = path;
		this.addModifications(commit.getModifications());		
		this.manifestModifications = ManifestDiff.getManifestModifications(getModifications());
		this.repoSCM = repoSCM;		
	}

	private ManifestDiff getManifestDiff(){
		if(manifestDiff == null){
			manifestDiff = new ManifestDiff(manifestModifications, getManifestsMap(), path, isMerge());
		}
		return manifestDiff;
	}
	
	public PermissionMap getPermissionMap() {
		if (permissionMap == null) {
			permissionMap = new PermissionMap(permissionAnalyzer.generateJSON(getApkFilePath()));
		}
		return permissionMap;
	}
	
	public boolean hasAndroidModifications(){
		return !manifestModifications.isEmpty();
	}
	
	public String getApkFilePath(){
		if(apkFilePath == null){
			apkFilePath = CommitFilesManager.getInstance(repoSCM)
					.getApkFilePath(getHash());
		}
		return apkFilePath;
	}
	
	public String getAndroidManifestCode() {
		return androidManifestCode;
	}
	
	
	public Map<String, String> getManifestsMap() {
		if(manifestsMap == null){
			manifestsMap = CommitFilesManager.getInstance(repoSCM)
					.getManifests(getHash());
		}
		return manifestsMap;
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
