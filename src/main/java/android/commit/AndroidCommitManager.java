package android.commit;

import java.util.HashMap;
import java.util.Map;

import org.repodriller.domain.Commit;
import org.repodriller.scm.SCMRepository;

public class AndroidCommitManager {
	private static Map<String, AndroidCommit> map = new HashMap<>();
	
	public static AndroidCommit getAndroidCommit(Commit commit, SCMRepository repo){
		if(!map.containsKey(commit.getHash())){
			map.put(commit.getHash(), new AndroidCommit(commit, repo.getScm(), repo.getPath()));
		}
		return map.get(commit.getHash());
	}
}
