package tcc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.repodriller.RepoDriller;

import utils.Logger;

public class MyStudy {
	
	static List<String> repoURLs = Arrays.asList(
		// Nome 			// Commits - Tamanho (MB)
//		"AsciiCam",			// 56 - 0.623		
//		"2048-android", 	// 70 - 7.89 				
//		
//		"Timber",			// 597 - 16.97					
//		"Telegram-FOSS", 	// 704     - 125.25				
//		"https://github.com/jackpal/Android-Terminal-Emulator.git", // 1088 - 6.24 
		
//		"https://github.com/tejado/Authorizer.git", 		// 1304 - 153.63
		"https://github.com/betosousa/fooAndroidManifest.git"
	);
	
	
	private static List<String> getReposFromFile(){
		
		return new ArrayList<String>();
	}
	
	private static void processRepos(List<String> repos){
		for (String repo : repos){
			new RepoDriller().start(new RepoStudy(repo));
		}
	}
	
	public static void main(String[] args) {
		System.out.println("Start-Main");
		Logger.initLog();
		
		List<String> repos = getReposFromFile();
		if (repos.isEmpty()) {
			repos = repoURLs;
		}
		processRepos(repos);
		
		Logger.closeLog();
		System.out.println("End-Main");
	}
}
