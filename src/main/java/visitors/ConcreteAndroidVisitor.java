package visitors;

import org.repodriller.persistence.PersistenceMechanism;
import org.repodriller.scm.SCMRepository;

import android.ActivityDiff;
import android.commit.AndroidCommit;

public class ConcreteAndroidVisitor extends AndroidVisitor {

	@Override
	public void androidProcess(SCMRepository repo, AndroidCommit commit,
			PersistenceMechanism writer) {
		System.out.print(" Concrete");
		ActivityDiff activityDiff = commit.getActivityDiff(); 
		writer.write(commit.getHash(), 
				"Added: " + activityDiff.getAdded().size(),
				"Removed: " + activityDiff.getRemoved().size(),
				//commit.getAndroidManifest().toString(), 
				//commit.getPermissionMap().toString(), 
				"/n/n");
		
		System.out.print(" Concrete end");
	}

}
