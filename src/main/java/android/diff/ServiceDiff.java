package android.diff;

import java.util.List;

import android.AndroidManifest;
import android.data.ComponentType;
import android.data.Service;

public class ServiceDiff extends ComponentDiff {
	public ServiceDiff(ManifestDiff manifestDiff) {
		super(manifestDiff);
	}

	@Override
	@SuppressWarnings("unchecked")
	protected List<Service> getComponents(AndroidManifest manifest) {
		return manifest.getComponents(ComponentType.SERVICE);
	}
}
