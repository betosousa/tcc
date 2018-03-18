package android.diff;

import java.util.List;

import android.AndroidManifest;
import android.data.ComponentType;
import android.data.ContentProvider;

public class ContentProviderDiff extends ComponentDiff {

	public ContentProviderDiff(ManifestDiff manifestDiff) {
		super(manifestDiff);
	}

	@Override
	@SuppressWarnings("unchecked")
	protected List<ContentProvider> getComponents(AndroidManifest manifest) {
		return manifest.getComponents(ComponentType.CONTENT_PROVIDER);
	}

}
