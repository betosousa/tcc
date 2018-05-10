package visitors;

import org.repodriller.domain.Commit;
import org.repodriller.persistence.PersistenceMechanism;
import org.repodriller.scm.CommitVisitor;
import org.repodriller.scm.SCMRepository;

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
		
		try {	
			ProgressUtil.getInstance().updateProgress(commit.getDate(),
					repo.getScm().totalCommits(), commit.getHash());
		} catch(Exception e){ 
			Logger.logMessage(Strings.VISIT_PROCCESS_ERROR);
			Logger.logMessage(e.getMessage(), e);			
			throw new RuntimeException(Strings.ERROR, e);
		}
		androidProcess(repo, new AndroidCommit(commit, repo.getScm(), repo.getPath()), writer);
	}

}
