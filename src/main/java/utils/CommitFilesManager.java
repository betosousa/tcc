package utils;

import java.util.List;

import org.repodriller.scm.RepositoryFile;
import org.repodriller.scm.SCM;

public class CommitFilesManager {
	
	private static CommitFilesManager filesManager;	
	
	private SCM repo;
	private String currentCommit;
	private List<RepositoryFile> commitFiles;
	
	
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
	
	public List<RepositoryFile> getFiles(String commitHash){
		if(commitFiles == null || !currentCommit.equals(commitHash)){
			repo.checkout(commitHash);
			commitFiles = repo.files();
			currentCommit = commitHash;
		}
		return commitFiles;
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
