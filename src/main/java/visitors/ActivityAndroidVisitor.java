package visitors;

import android.commit.AndroidCommit;
import android.diff.ComponentDiff;

public class ActivityAndroidVisitor extends ComponentAndroidVisitor {

	
	@Override
	protected String getComponentName() {
		return "Activity";
	}

	@Override
	protected ComponentDiff getComponentDiff(AndroidCommit commit) {
		return commit.getActivityDiff();
	}

	

}
