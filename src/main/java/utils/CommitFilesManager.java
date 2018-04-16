package utils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.repodriller.scm.RepositoryFile;
import org.repodriller.scm.SCM;

import android.AndroidManifest;

public class CommitFilesManager {
	
	private static CommitFilesManager filesManager;	
	
	private SCM repo;
	private String currentCommit;
	private List<RepositoryFile> commitFiles;
	private String apkFilePath;
	private Map<String, String> manifests = new HashMap<>();
	
	private CommitFilesManager(SCM repoSCM) {
		this.repo = repoSCM;
		this.currentCommit = "";
	}
	
	public static CommitFilesManager getInstance(SCM repoSCM){
		if(filesManager == null){
			filesManager = new CommitFilesManager(repoSCM);
		}
		return filesManager;
	}
	
	private void getFiles(String commitHash){
		if(commitFiles == null || !currentCommit.equals(commitHash)){
			repo.reset();
			repo.checkout(commitHash);
			commitFiles = repo.files();
			currentCommit = commitHash;
			manifests.clear();
			searchFiles();
		}
	}
	
	private void searchFiles(){
		for (RepositoryFile repoFile : commitFiles) {				
			if (repoFile.fileNameEndsWith(Strings.APK)) {
				apkFilePath = repoFile.getFile().getAbsolutePath();
			} else if (repoFile.fileNameEndsWith(AndroidManifest.FILE_NAME)) {
				manifests.put(repoFile.getFile().getPath(), repoFile.getSourceCode());
			}
		}
	}
	
	public String getApkFilePath(String commitHash){
		getFiles(commitHash);
		return apkFilePath;
	}
	
	public Map<String, String> getManifests(String commitHash){
		getFiles(commitHash);
		return manifests;
	}
	
	void resetRepo(){
		repo.reset();
	}
	
	public static  void reset(){
		if(filesManager != null){
			filesManager.resetRepo();
			filesManager = null;
		}
	}
}
