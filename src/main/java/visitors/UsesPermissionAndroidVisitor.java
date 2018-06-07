package visitors;

import android.commit.AndroidCommit;
import android.diff.Diff;

public class UsesPermissionAndroidVisitor extends DiffAndroidVisitor {

	public UsesPermissionAndroidVisitor(String repoName) {
		super(repoName);
	}

	@Override
	protected Diff<?> getDiff(AndroidCommit commit) {
		return commit.getUsesPermissionDiff();
	}

	@Override
	protected String getComponentName() {
		return "UsesPermission";
	}

	

}
