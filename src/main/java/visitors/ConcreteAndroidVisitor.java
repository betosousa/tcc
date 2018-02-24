package visitors;

import org.repodriller.persistence.PersistenceMechanism;
import org.repodriller.scm.SCMRepository;

import android.commit.AndroidCommit;

public class ConcreteAndroidVisitor extends AndroidVisitor {

	@Override
	public void androidProcess(SCMRepository repo, AndroidCommit commit,
			PersistenceMechanism writer) {
		System.out.print(" Concrete");
		writer.write(commit.getHash(), 
				commit.getAndroidManifest().toString(), 
				commit.getPermissionMap().toString(), 
				"/n/n");
		commit.getActivityDiff();
		System.out.print(" Concrete end");
	}

}
