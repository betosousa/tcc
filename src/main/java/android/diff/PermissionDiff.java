package android.diff;

import java.util.List;

import android.AndroidManifest;
import android.data.ComponentType;
import android.data.Permission;

public class PermissionDiff extends ComponentDiff {

	public PermissionDiff(ManifestDiff manifestDiff) {
		super(manifestDiff);
	}

	@Override
	@SuppressWarnings("unchecked")
	protected List<Permission> getComponents(AndroidManifest manifest) {
		return manifest.getComponents(ComponentType.PERMISSION);
	}

}
