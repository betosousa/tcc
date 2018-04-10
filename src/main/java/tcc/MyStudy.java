package tcc;

import org.repodriller.RepoDriller;

public class MyStudy {
	
	static String[] repoNames = {
		// Nome 			// Commits - Tamanho (MB)
//		"2048-android", 	// 70 - 7.89 				
//		"AsciiCam",			// 56 - 0.623		
		
//		"Timber",			// 597 - 16.97					
//		"Telegram-FOSS", 	// 710 - 125.25				
//		"Android-Terminal-Emulator", // 1088 - 6.24
		
//		"Authorizer", 		// 1304 - 153.63			
	};
	
	public static void main(String[] args) {
		System.out.println("Start-Main");
		for (String repo : repoNames){
			new RepoDriller().start(new RepoStudy(repo));
		}
		System.out.println("End-Main");
	}
}
