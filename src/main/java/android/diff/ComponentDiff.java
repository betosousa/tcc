package android.diff;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import android.data.Component;

public abstract class ComponentDiff {
	
	private ManifestDiff manifestDiff;
	
	private List<Component> added = new ArrayList<>();
	private List<Component> removed = new ArrayList<>();
	
	private List<Component> committed = new ArrayList<>();
	private List<Component> beforeCommit = new ArrayList<>();
	
	public ComponentDiff(ManifestDiff manifestDiff){
		this.manifestDiff = manifestDiff;
		fillBeforeAndCommitted();
		fillAddedAndRemoved();
	}
	
	private void fillBeforeAndCommitted(){
		fillList(manifestDiff.getBeforeCommitManifests().values(), beforeCommit);
		fillList(manifestDiff.getCommittedManifests().values(), committed);
	}
	
	private void fillList(Collection<String> manifests, List<Component> components){
		for (String manifest : manifests){
			for(Component component : parseComponents(manifest)){
				if(!components.contains(component))
					components.add(component);
			}
		}
	}
	
	protected abstract <T extends Component> List<T> parseComponents(String manifest);
	
	private void fillAddedAndRemoved(){
		if (committed.size() > 0 || beforeCommit.size() > 0) {
			for (Component component : committed) {
				if (!beforeCommit.contains(component)) {
					added.add(component);
				}
			}
			for (Component component : beforeCommit) {
				if (!committed.contains(component)) {
					removed.add(component);
				}
			}
		}
	}

	public List<Component> getAdded() {
		return added;
	}

	public void setAdded(List<Component> added) {
		this.added = added;
	}

	public List<Component> getRemoved() {
		return removed;
	}

	public void setRemoved(List<Component> removed) {
		this.removed = removed;
	}

	public List<Component> getCommitted() {
		return committed;
	}

	public void setCommitted(List<Component> committed) {
		this.committed = committed;
	}

	public List<Component> getBeforeCommit() {
		return beforeCommit;
	}

	public void setBeforeCommit(List<Component> beforeCommit) {
		this.beforeCommit = beforeCommit;
	}

}
