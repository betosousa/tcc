package visitors;

import android.commit.AndroidCommit;
import android.diff.ComponentDiff;

public class ServiceAndroidVisitor extends ComponentAndroidVisitor {

	public ServiceAndroidVisitor(String repoName) {
		super(repoName);
	}

	@Override
	protected ComponentDiff getComponentDiff(AndroidCommit commit) {
		return commit.getServiceDiff();
	}

	@Override
	protected String getComponentName() {
		return "Service";
	}

}
