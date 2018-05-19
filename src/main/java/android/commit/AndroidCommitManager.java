package android.commit;

import org.repodriller.domain.Commit;
import org.repodriller.scm.SCMRepository;

public class AndroidCommitManager {
	
	private static AndroidCommit currentCommit = null;
	private static String currentHash = "";
	
	public static AndroidCommit getAndroidCommit(Commit commit, SCMRepository repo){
		if(!currentHash.equals(commit.getHash())){
			currentCommit = new AndroidCommit(commit, repo.getScm(), repo.getPath());
			currentHash = commit.getHash();
		}
		return currentCommit;
	}
}
