package android.commit;

import interfaces.IPermissionAnalyzer;

import org.repodriller.domain.Commit;

import utils.AndroidManifestParser;
import android.ActivityDiff;
import android.AndroidManifest;
import android.permission.PermissionMap;
import android.permission.wrapper.PermissionAnalyzerWrapper;

public class AndroidCommit extends Commit {

	private PermissionMap permissionMap;
	private String apkFilePath;
	private String androidManifestCode;
	private IPermissionAnalyzer permissionAnalyzer;
	private AndroidManifest androidManifest;
	private String manifestPath;
	private ActivityDiff activityDiff;

	public AndroidCommit(Commit commit, String apkFilePath,
			String androidManifest, String manifestPath) {
		super(commit.getHash(), commit.getAuthor(), commit.getCommitter(),
				commit.getDate(), commit.getAuthorTimeZone(), commit.getCommitterDate(),
				commit.getCommitterTimeZone(), commit.getMsg(), commit.getParent(), 
				commit.isMerge(), commit.getBranches(), commit.isInMainBranch());
		this.apkFilePath = apkFilePath;
		this.permissionAnalyzer = new PermissionAnalyzerWrapper();
		this.androidManifestCode = androidManifest;
		this.manifestPath = manifestPath;
		this.addModifications(commit.getModifications());
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
		if (androidManifest == null) {
			androidManifest = AndroidManifestParser.parse(manifestPath);
		}
		return androidManifest;
	}
	
	public ActivityDiff getActivityDiff(){
		if(activityDiff == null){
			activityDiff = new ActivityDiff(getModifications());
		}
		return activityDiff;
	}

}
