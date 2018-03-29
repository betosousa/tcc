package visitors;

import android.commit.AndroidCommit;
import android.diff.Diff;

public class UsesPermissionAndroidVisitor extends DiffAndroidVisitor {

	@Override
	protected Diff<?> getDiff(AndroidCommit commit) {
		return commit.getUsesPermissionDiff();
	}

	@Override
	protected String getComponentName() {
		return "UsesPermission";
	}

	

}
