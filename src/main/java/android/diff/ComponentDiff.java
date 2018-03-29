package android.diff;

import java.util.List;

import android.AndroidManifest;
import android.data.Component;

public abstract class ComponentDiff extends Diff<Component> {
	
	public ComponentDiff(ManifestDiff manifestDiff){
		super(manifestDiff);
	}
	
	protected abstract <T extends Component> List<T> getComponents(AndroidManifest manifest);
	
	@Override
	protected List<Component> getElementsList(AndroidManifest manifest) {
		return getComponents(manifest);
	}
	
	/**
	 * As equals only compares name and type, 
	 * we compare the others attributes to check for modifications
	 * 
	 * @param component Committed Component to be checked with its previous version.
	 *  
	 * @return true if the component has the same name and type of its previous version 
	 * 			and any modified attribute
	 */
	@Override
	protected boolean isModification(Component component){
		int index = getBeforeCommit().indexOf(component);
		Component original = getBeforeCommit().get(index); 
		return component.isModificationOf(original);
	}
	
}
