package android;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import utils.ActivityDiffParser;
import android.data.Activity;

public class ActivityDiff {
	
	private ManifestDiff manifestDiff;
	
	private List<Activity> added = new ArrayList<>();
	private List<Activity> removed = new ArrayList<>();
	
	private List<Activity> committed = new ArrayList<>();
	private List<Activity> beforeCommit = new ArrayList<>();
	
	public ActivityDiff(ManifestDiff manifestDiff){
		this.manifestDiff = manifestDiff;
		fillBeforeAndCommitted();
		fillAddedAndRemoved();
	}	
	
	private void fillBeforeAndCommitted(){
		fillActivityList(manifestDiff.getBeforeCommitManifests().values(), beforeCommit);
		fillActivityList(manifestDiff.getCommittedManifests().values(), committed);
	}
	
	private void fillActivityList(Collection<String> manifests, List<Activity> activities){
		for (String manifest : manifests){
			for(Activity activity : ActivityDiffParser.parseActivityList(manifest)){
				if(!activities.contains(activity))
					activities.add(activity);
			}
		}
	}
	
	private void fillAddedAndRemoved(){
		if (committed.size() > 0 || beforeCommit.size() > 0) {
			for (Activity activity : committed) {
				if (!beforeCommit.contains(activity)) {
					added.add(activity);
				}
			}
			for (Activity activity : beforeCommit) {
				if (!committed.contains(activity)) {
					removed.add(activity);
				}
			}
		}
	}

	public List<Activity> getCommited() {
		return committed;
	}

	public List<Activity> getBeforeCommit() {
		return beforeCommit;
	}

	public List<Activity> getAdded() {
		return added;
	}

	public List<Activity> getRemoved() {
		return removed;
	}
}

