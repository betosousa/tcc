package android.diff;

import java.util.List;

import utils.ActivityDiffParser;
import android.data.Activity;

public class ActivityDiff extends ComponentDiff {
	
	public ActivityDiff(ManifestDiff manifestDiff){
		super(manifestDiff);
	}

	@Override
	@SuppressWarnings("unchecked")
	protected List<Activity> parseComponents(String manifest) {
		return ActivityDiffParser.parseActivityList(manifest);
	}	
}

