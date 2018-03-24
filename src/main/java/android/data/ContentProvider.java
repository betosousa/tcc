package android.data;

import java.util.ArrayList;
import java.util.List;

public class ContentProvider extends Component {
	public List<String> authorities;
	
	public boolean exportedWasSetted;
	
	public boolean grantUriPermissions = false;
	public int initOrder;
	public boolean multiprocess = false;
	public String readPermission = NOT_SET;
	public boolean syncable = false;
	public String writePermission = NOT_SET;
	
	public ContentProvider(){
		super();
		super.type = ComponentType.CONTENT_PROVIDER;
		
		authorities = new ArrayList<String>();
	}
	
	
	public void setDefaultExported(int minSdkVersion, int targetSdkVersion){
		if(minSdkVersion <= 16 || targetSdkVersion <=16){
			super.exported = true;
		} else {
			super.exported = false;
		}
	}
	
	@Override
	public String toStringExclusiveAttributes() {
		StringBuilder builder = new StringBuilder();
		
		builder.append(String.format("Authorities: "));
        for(String aut : authorities){
        	builder.append(String.format("%s;", aut));
        }
        builder.append("\n");
        builder.append(String.format("GrantUriPermissions: %s\n", grantUriPermissions));
        builder.append(String.format("InitOrder: %s\n", initOrder));
        builder.append(String.format("Multiprocess: %s\n", multiprocess));
        builder.append(String.format("ReadPermission: %s\n", readPermission));
        builder.append(String.format("Syncable: %s\n", syncable));
        builder.append(String.format("WritePermission: %s\n", writePermission));

		return builder.toString();
	}


	@Override
	protected boolean hasExclusiveAttributesModifications(Component other) {
		// TODO Auto-generated method stub
		return false;
	}
}
