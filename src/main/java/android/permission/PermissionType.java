package android.permission;

public enum PermissionType {
	DANGEROUS("dangerous"),
	NORMAL("normal"),
	SIGNATURE("signature"),
	SIGNATURE_OR_SYSTEM("signatureOrSystem");
	
	private String protectionLevel;
	
	PermissionType(String protectionLevel){
		this.protectionLevel = protectionLevel;
	}
	
	public String getProtectionLevel(){
		return protectionLevel;
	}
}
