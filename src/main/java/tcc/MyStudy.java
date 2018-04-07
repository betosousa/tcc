package tcc;

import org.repodriller.RepoDriller;

public class MyStudy {
	
	static String[] repoNames = {
		//Pequenos, menos de 100 commits
		"2048-android",				
		"AsciiCam",					
		
//		// Medios, 100 - 1000 commits
//		"Telegram-FOSS",			
//		"Timber",					
//		
//		// Grandes, mais de 1000 commits
//		"Authorizer",				
//		"Android-Terminal-Emulator",
	};
	
	public static void main(String[] args) {
		System.out.println("Start-Main");
		for (String repo : repoNames){
			new RepoDriller().start(new RepoStudy(repo));
		}
		System.out.println("End-Main");
	}
}
