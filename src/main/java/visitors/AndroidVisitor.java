package visitors;

import java.util.HashMap;
import java.util.Map;

import org.repodriller.domain.Commit;
import org.repodriller.persistence.PersistenceMechanism;
import org.repodriller.scm.CommitVisitor;
import org.repodriller.scm.SCMRepository;

import utils.CommitFilesManager;
import utils.Logger;
import utils.ProgressUtil;
import utils.Strings;
import android.commit.AndroidCommit;

public abstract class AndroidVisitor implements CommitVisitor {
	
	
	public abstract void androidProcess(SCMRepository repo,
			AndroidCommit commit, PersistenceMechanism writer);

	@Override
	public void process(SCMRepository repo, Commit commit,
			PersistenceMechanism writer) {
		
		String apkFilePath = "";
		Map<String, String> manifests = new HashMap<>();
		try {	
			ProgressUtil.getInstance().updateProgress(commit.getDate(),
					repo.getScm().totalCommits(), commit.getHash());

			apkFilePath = CommitFilesManager.getInstance(repo.getScm())
					.getApkFilePath(commit.getHash());

			manifests = CommitFilesManager.getInstance(repo.getScm())
					.getManifests(commit.getHash());
		} catch(Exception e){ 
			Logger.logMessage(Strings.VISIT_PROCCESS_ERROR, null);
			Logger.logMessage(e.getMessage(), e);			
			throw new RuntimeException(Strings.ERROR, e);
		}
		androidProcess(repo, new AndroidCommit(commit, apkFilePath, manifests, repo.getPath()), writer);
	}

}
