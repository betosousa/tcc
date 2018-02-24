package android.permission;


public class Permission {
	private String name;
	private String permissionGroup;
	private PermissionType type;

	public Permission() {
		super();
	}
	
	public Permission(String name){
		this.name = name;
	}
	
	public Permission(String name, String permissionGroup, PermissionType type) {
		this.name = name;
		this.permissionGroup = permissionGroup;
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPermissionGroup() {
		return permissionGroup;
	}

	public void setPermissionGroup(String permissionGroup) {
		this.permissionGroup = permissionGroup;
	}

	public PermissionType getType() {
		return type;
	}

	public void setType(PermissionType type) {
		this.type = type;
	}

}
