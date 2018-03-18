package visitors;

import java.text.SimpleDateFormat;

import org.repodriller.persistence.PersistenceMechanism;
import org.repodriller.scm.SCMRepository;

import utils.Strings;
import android.commit.AndroidCommit;
import android.diff.ActivityDiff;

public class ActivityAndroidVisitor extends AndroidVisitor {

	@Override
	public void androidProcess(SCMRepository repo, AndroidCommit commit,
			PersistenceMechanism writer) {
		System.out.print(" Activity");
		ActivityDiff activityDiff = commit.getActivityDiff(); 
		
		writer.write(activityDiff.getAdded().size(),
				activityDiff.getRemoved().size(),
				new SimpleDateFormat(Strings.DATE_FORMAT).format(commit.getDate().getTime()));
		
		System.out.print(" Activity end");
	}

}
