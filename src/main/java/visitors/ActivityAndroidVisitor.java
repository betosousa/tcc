package visitors;

import android.commit.AndroidCommit;
import android.diff.ComponentDiff;

public class ActivityAndroidVisitor extends ComponentAndroidVisitor {

	
	public ActivityAndroidVisitor(String repoName) {
		super(repoName);
	}

	@Override
	protected String getComponentName() {
		return "Activity";
	}

	@Override
	protected ComponentDiff getComponentDiff(AndroidCommit commit) {
		return commit.getActivityDiff();
	}

	

}
