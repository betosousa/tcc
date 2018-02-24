package tcc;


import interfaces.IPermissionAnalyzer;
import android.permission.PermissionMap;
import android.permission.wrapper.PermissionAnalyzerWrapper;

public class LibsTest {

	private static final String APK_NAME = "ar.rulosoft.mimanganu_74"+".apk";
	private static final String APK_PATH = "D:\\11p\\TCC\\appsDB\\";
	
	public static void main(String[] args) {
		permissionChecker();
		androguard();
	}
	
	private static void permissionChecker(){
		IPermissionAnalyzer pa = new PermissionAnalyzerWrapper();
		String str = pa.generateJSON(APK_PATH + APK_NAME);
		System.out.println(str);
		System.out.println(pa.generateJSONObject(APK_PATH + APK_NAME));
		System.out.println(new PermissionMap(str));
	}
	
	private static void androguard(){
				
	}
	
}
