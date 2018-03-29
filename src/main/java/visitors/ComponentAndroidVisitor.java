package visitors;

import android.commit.AndroidCommit;
import android.diff.ComponentDiff;
import android.diff.Diff;

public abstract class ComponentAndroidVisitor extends DiffAndroidVisitor {

	protected abstract ComponentDiff getComponentDiff(AndroidCommit commit);
	
	@Override
	protected Diff<?> getDiff(AndroidCommit commit) {
		return getComponentDiff(commit);
	}

}
