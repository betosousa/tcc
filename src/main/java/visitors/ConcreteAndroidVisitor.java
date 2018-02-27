package visitors;

import java.text.SimpleDateFormat;

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
		//writer.write(commit.getHash(), 
				//"Added: " + activityDiff.getAdded().size(),
				//"Removed: " + activityDiff.getRemoved().size(),
				//commit.getAndroidManifest().toString(), 
				//commit.getPermissionMap().toString(), 
				//"/n/n");
		
		writer.write(activityDiff.getAdded().size(),
				activityDiff.getRemoved().size(),
				new SimpleDateFormat("yyyy/MM/dd-hh:mm:ss").format(commit.getDate().getTime()));
		
		System.out.print(" Concrete end");
	}

}
