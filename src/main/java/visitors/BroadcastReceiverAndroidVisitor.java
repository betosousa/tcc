package visitors;

import android.commit.AndroidCommit;
import android.diff.ComponentDiff;

public class BroadcastReceiverAndroidVisitor extends ComponentAndroidVisitor {

	@Override
	protected ComponentDiff getComponentDiff(AndroidCommit commit) {
		return commit.getBroadcastReceiverDiff();
	}

	@Override
	protected String getComponentName() {
		return "BroadcastReceiver";
	}
}
