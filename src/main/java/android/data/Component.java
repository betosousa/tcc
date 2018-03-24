package android.data;

import java.util.LinkedList;
import java.util.List;

public abstract class Component
{
    public static final String NOT_SET = "#NOT SET#";
    public ComponentType type;
    public String label = NOT_SET;
    public String name = NOT_SET;
    public boolean exported = false;
    public List<IntentFilter> intentFilters;
    
    //TODO: capture other kinds of common information among components
    
	//public String icon = NOT_SET;
    public boolean enabled = true;
    public String permission = NOT_SET;
    public String process = NOT_SET;
    
    public Component() {
      intentFilters = new LinkedList<IntentFilter>();
    }
    
    public abstract String toStringExclusiveAttributes();
    
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
            
        builder.append(String.format("Type: %s\n", type));
        builder.append(String.format("Label: %s\n", label));
        builder.append(String.format("Name: %s\n", name));
        builder.append(String.format("Exported: %s\n", exported));
        builder.append(String.format("Enabled: %s\n", enabled));
        builder.append(String.format("Permission: %s\n", permission));
        builder.append(String.format("Process: %s\n", process));
        //builder.append(String.format("Icon: %s\n", icon));

        
        builder.append(toStringExclusiveAttributes());
        
        if (intentFilters.size()>0) {
	        builder.append("Intent Filters: \n");       
	        for (IntentFilter iFilter : intentFilters) {
	            builder.append(String.format("%s\n", iFilter));
	        }
        }
        
        return builder.toString();
    }
	
	private boolean hasGeneralAttributesModifications(Component other) {
		return this.enabled != other.enabled || this.exported != other.exported
				|| !this.intentFilters.equals(other.intentFilters)
				|| !this.label.equals(other.label)
				|| !this.permission.equals(other.permission)
				|| !this.process.equals(other.process);
	}
	
	protected abstract boolean hasExclusiveAttributesModifications(Component other);
	
	public boolean isModificationOf(Component other) {
		boolean isModification = false;
		if (this.equals(other)) {
			isModification = hasGeneralAttributesModifications(other)
					|| hasExclusiveAttributesModifications(other);
		}
		return isModification;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((type == null) ? 0 : type.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Component other = (Component) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (type != other.type)
			return false;
		return true;
	}	
}
