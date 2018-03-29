package android.data;

import org.xml.sax.Attributes;

import android.permission.PermissionType;

public class Permission extends Component {

	// TODO: find a way to get these values:
	private String permissionGroup = NOT_SET;
	private PermissionType permissionType = PermissionType.NORMAL;
	
	public Permission(){
		super();
		this.type = ComponentType.PERMISSION;
	}
	
	public Permission(Attributes attributes){
		this();
		String s;
		this.label = null == (s = attributes.getValue("android:label")) ? this.label : s;
		this.name = null == (s = attributes.getValue("android:name")) ? this.name : s;
		this.process = null == (s = attributes.getValue("android:process")) ? this.process : s;
		this.enabled = !("false").equals(attributes.getValue("android:enabled"));
		this.exported = ("true").equals(attributes.getValue("android:exported"));
	}
	
	@Override
	public String toStringExclusiveAttributes() {
		StringBuilder str = new StringBuilder();
		str.append("PermissionGroup: " + permissionGroup);
		str.append("ProtectionLevel: " + getProtectionLevel());
		return str.toString();
	}

	@Override
	protected boolean hasExclusiveAttributesModifications(Component other) {
		Permission otherPermission = (Permission) other;
		return !this.permissionGroup.equals(otherPermission.getPermissionGroup())
				|| !this.getProtectionLevel().equals(otherPermission.getProtectionLevel()) ;
	}
	

	public String getPermissionGroup() {
		return permissionGroup;
	}

	public void setPermissionGroup(String permissionGroup) {
		this.permissionGroup = permissionGroup;
	}

	public PermissionType getPermissionType() {
		return permissionType;
	}

	public void setPermissionType(PermissionType permissionType) {
		this.permissionType = permissionType;
	}
	
	public String getProtectionLevel(){
		return this.permissionType.getProtectionLevel();
	}
}
