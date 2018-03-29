package android.diff;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import android.AndroidManifest;

public abstract class Diff<T> {
private ManifestDiff manifestDiff;
	
	private List<T> added = new ArrayList<>();
	private List<T> removed = new ArrayList<>();
	private List<T> modified = new ArrayList<>();
	
	private List<T> committed = new ArrayList<>();
	private List<T> beforeCommit = new ArrayList<>();
	
	public Diff(ManifestDiff manifestDiff){
		this.manifestDiff = manifestDiff;
		fillBeforeAndCommitted();
		fillAddedAndRemoved();
	}
	
	private void fillBeforeAndCommitted(){
		fillList(manifestDiff.getBeforeCommittAndroidManifests(), beforeCommit);
		fillList(manifestDiff.getCommittedAndroidManifests(), committed);
	}
	
	private void fillList(Collection<AndroidManifest> manifests, List<T> components){
		for (AndroidManifest manifest : manifests){
			for(T component : getElementsList(manifest)){
				if(!components.contains(component))
					components.add(component);
			}
		}
	}
	
	protected abstract List<T> getElementsList(AndroidManifest manifest);
	
	protected abstract boolean isModification(T component);
	
	private void fillAddedAndRemoved(){
		if (committed.size() > 0 || beforeCommit.size() > 0) {
			for (T component : committed) {
				if (!beforeCommit.contains(component)) {
					added.add(component);
				} else if(isModification(component)) {
					modified.add(component);
				}
			}
			for (T component : beforeCommit) {
				if (!committed.contains(component)) {
					removed.add(component);
				}
			}
		}
	}

	public List<T> getAdded() {
		return added;
	}

	public List<T> getRemoved() {
		return removed;
	}

	public List<T> getCommitted() {
		return committed;
	}

	public List<T> getBeforeCommit() {
		return beforeCommit;
	}

	public List<T> getModified() {
		return modified;
	}

}
