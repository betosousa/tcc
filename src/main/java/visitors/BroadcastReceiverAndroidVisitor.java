package visitors;

import android.commit.AndroidCommit;
import android.diff.ComponentDiff;

public class BroadcastReceiverAndroidVisitor extends ComponentAndroidVisitor {

	public BroadcastReceiverAndroidVisitor(String repoName) {
		super(repoName);
	}

	@Override
	protected ComponentDiff getComponentDiff(AndroidCommit commit) {
		return commit.getBroadcastReceiverDiff();
	}

	@Override
	protected String getComponentName() {
		return "BroadcastReceiver";
	}
}
