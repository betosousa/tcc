package visitors;

import org.repodriller.domain.Commit;
import org.repodriller.persistence.PersistenceMechanism;
import org.repodriller.scm.CommitVisitor;
import org.repodriller.scm.SCMRepository;

import android.commit.AndroidCommit;
import android.commit.AndroidCommitManager;
import utils.LoggerManager;
import utils.ProgressUtil;
import utils.Strings;

public abstract class AndroidVisitor implements CommitVisitor {
	
	private String repoName;
	
	public AndroidVisitor(String repoName) {
		this.repoName = repoName;
	}
	
	public abstract void androidProcess(SCMRepository repo,
			AndroidCommit commit, PersistenceMechanism writer);

	@Override
	public void process(SCMRepository repo, Commit commit,
			PersistenceMechanism writer) {
		
		try {	
			ProgressUtil.getInstance().updateProgress(commit.getDate(),
					repo.getScm().totalCommits(), commit.getHash());
		} catch(Exception e){ 
			LoggerManager.getLogger(repoName).logMessage(Strings.VISIT_PROCCESS_ERROR);
			LoggerManager.getLogger(repoName).logMessage(e.getMessage(), e);			
			throw new RuntimeException(Strings.ERROR, e);
		}
		androidProcess(repo, AndroidCommitManager.getAndroidCommit(commit, repo), writer);
	}

}
