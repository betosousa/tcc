package visitors;

import android.commit.AndroidCommit;
import android.diff.ComponentDiff;

public class ContentProviderAndroidVisitor extends ComponentAndroidVisitor {

	@Override
	protected ComponentDiff getComponentDiff(AndroidCommit commit) {
		return commit.getContentProviderDiff();
	}

	@Override
	protected String getComponentName() {
		return "ContentProvider";
	}	
}
