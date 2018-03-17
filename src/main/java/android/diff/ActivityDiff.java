package android.diff;

import java.util.List;

import android.AndroidManifest;
import android.data.Activity;
import android.data.ComponentType;

public class ActivityDiff extends ComponentDiff {
	
	public ActivityDiff(ManifestDiff manifestDiff){
		super(manifestDiff);
	}

	@Override
	@SuppressWarnings("unchecked")
	protected List<Activity> getComponents(AndroidManifest manifest) {
		return manifest.getComponents(ComponentType.ACTIVITY);
	}	
}

