package tcc;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import org.repodriller.RepoDriller;

import logger.LoggerManager;
import logger.ProgressLogger;
import utils.Strings;

public class MyStudy {
	
	private static final String INPUT_FILE = "androidDriller" + File.separator + "input" + File.separator
			+ "repoURLs.in";
	
	static List<String> repoURLs = Arrays.asList(
		// Nome 													// Commits - Tamanho (MB)
		"https://github.com/dozingcat/AsciiCam",					// 56 	- 0.623		
		"https://github.com/uberspot/2048-android", 				// 70 	- 7.89 				
		
		"https://github.com/naman14/Timber",						// 597 	- 16.97					
		"https://github.com/Telegram-FOSS-Team/Telegram-FOSS", 		// 704 	- 125.25				
		"https://github.com/jackpal/Android-Terminal-Emulator.git",	// 1088	- 6.24 
		
		"https://github.com/tejado/Authorizer.git", 				// 1304	- 153.63
		"https://github.com/betosousa/fooAndroidManifest.git"		// 29	- 0.006
		,"https://github.com/betosousa/fooAndroidManifest.git"		// 29	- 0.006
	);
	
	
	private static List<String> getReposFromFile(){
		List<String> repos = new ArrayList<>();
		try (Stream<String> stream = Files.lines(Paths.get(INPUT_FILE))) {
	        stream.forEach(repos::add);
		} catch (IOException e) {
			LoggerManager.getLogger(Strings.MAIN).logMessage("Error reading input file", e);
		}
		return repos;
	}
	
	private static void processRepos(List<String> repos){
		int count = 1;
		for (String repo : repos){
			ProgressLogger.updateProgress(count++, repos.size());
			try {
				new RepoDriller().start(new RepoStudy(repo));
			} catch(RuntimeException e) {
				LoggerManager.getLogger(Strings.MAIN).logMessage("Error proccessing repo: " + repo, e);	
			}
		}
	}
	
	public static void main(String[] args) {
		LoggerManager.getLogger(Strings.MAIN).logMessage("Start Main");
		List<String> repos = getReposFromFile();
		if (repos.isEmpty()) {
			repos = repoURLs;
		}
		processRepos(repos);		
		LoggerManager.getLogger(Strings.MAIN).logMessage("End-Main");
		LoggerManager.closeLogs();
	}
}
