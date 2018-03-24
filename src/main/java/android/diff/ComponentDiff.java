package android.diff;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import android.AndroidManifest;
import android.data.Component;

public abstract class ComponentDiff {
	
	private ManifestDiff manifestDiff;
	
	private List<Component> added = new ArrayList<>();
	private List<Component> removed = new ArrayList<>();
	private List<Component> modified = new ArrayList<>();
	
	private List<Component> committed = new ArrayList<>();
	private List<Component> beforeCommit = new ArrayList<>();
	
	public ComponentDiff(ManifestDiff manifestDiff){
		this.manifestDiff = manifestDiff;
		fillBeforeAndCommitted();
		fillAddedAndRemoved();
	}
	
	private void fillBeforeAndCommitted(){
		fillList(manifestDiff.getBeforeCommittAndroidManifests(), beforeCommit);
		fillList(manifestDiff.getCommittedAndroidManifests(), committed);
	}
	
	private void fillList(Collection<AndroidManifest> manifests, List<Component> components){
		for (AndroidManifest manifest : manifests){
			for(Component component : getComponents(manifest)){
				if(!components.contains(component))
					components.add(component);
			}
		}
	}
	
	protected abstract <T extends Component> List<T> getComponents(AndroidManifest manifest);
	
	
	/**
	 * As equals only compares name and type, 
	 * we compare the others attributes to check for modifications
	 * 
	 * @param component Committed Component to be checked with its previous version.
	 *  
	 * @return true if the component has the same name and type of its previous version 
	 * 			and any modified attribute
	 */
	private boolean isModification(Component component){
		int index = beforeCommit.indexOf(component);
		Component original = beforeCommit.get(index); 
		return component.isModificationOf(original);
	}
	
	private void fillAddedAndRemoved(){
		if (committed.size() > 0 || beforeCommit.size() > 0) {
			for (Component component : committed) {
				if (!beforeCommit.contains(component)) {
					added.add(component);
				} else if(isModification(component)) {
					modified.add(component);
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

	public List<Component> getRemoved() {
		return removed;
	}

	public List<Component> getCommitted() {
		return committed;
	}

	public List<Component> getBeforeCommit() {
		return beforeCommit;
	}

	public List<Component> getModified() {
		return modified;
	}

}
