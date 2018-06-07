package visitors;

import android.commit.AndroidCommit;
import android.diff.ComponentDiff;

public class PermissionAndroidVisitor extends ComponentAndroidVisitor {

	public PermissionAndroidVisitor(String repoName) {
		super(repoName);
	}

	@Override
	protected ComponentDiff getComponentDiff(AndroidCommit commit) {
		return commit.getPermissionDiff();
	}

	@Override
	protected String getComponentName() {
		return "Permission";
	}
}
