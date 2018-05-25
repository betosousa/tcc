package tcc;

import org.repodriller.RepoDriller;

import utils.Logger;

public class MyStudy {
	
	static String[] repoNames = {
		// Nome 			// Commits - Tamanho (MB)
//		"AsciiCam",			// 56 - 0.623		
//		"2048-android", 	// 70 - 7.89 				
//		
//		"Timber",			// 597 - 16.97					
//		"Telegram-FOSS", 	// 704     - 125.25				
//		"Android-Terminal-Emulator", // 1088 - 6.24 
		
//		"Authorizer", 		// 1304 - 153.63
		"fooAndroidManifest",
	};
	
	static String[] repoURLs = {
		// Nome 			// Commits - Tamanho (MB)
//		"AsciiCam",			// 56 - 0.623		
//		"2048-android", 	// 70 - 7.89 				
//		
//		"Timber",			// 597 - 16.97					
//		"Telegram-FOSS", 	// 704     - 125.25				
//		"Android-Terminal-Emulator", // 1088 - 6.24 
		
//		"https://github.com/tejado/Authorizer.git", 		// 1304 - 153.63
		"https://github.com/betosousa/fooAndroidManifest.git",
	};
	
	public static void main(String[] args) {
		System.out.println("Start-Main");
		Logger.initLog();
		for (int i = 0; i < repoURLs.length; i++){
			new RepoDriller().start(new RepoStudy(repoNames[i], repoURLs[i]));
		}
		Logger.closeLog();
		System.out.println("End-Main");
	}
}
