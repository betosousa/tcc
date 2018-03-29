package android.diff;

import java.util.List;

import android.AndroidManifest;

public class UsesPermissionDiff extends Diff<String>{

	public UsesPermissionDiff(ManifestDiff manifestDiff) {
		super(manifestDiff);
	}

	@Override
	protected List<String> getElementsList(AndroidManifest manifest) {
		return manifest.permissions;
	}

	@Override
	protected boolean isModification(String component) {
		return false;
	}
	
}
