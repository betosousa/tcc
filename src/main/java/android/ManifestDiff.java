package android;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.repodriller.domain.Modification;

public class ManifestDiff {
	private static final String NULL_PATH = "/dev/null";
	
	private String path;
	private Map<String, String> committedManifests = new HashMap<>();
	private Map<String, String> beforeCommitManifests = new HashMap<>();
	
	public ManifestDiff(List<Modification> modifications, Map<String, String> manifestsMap, String path){
		this.path = path + File.separator;
		if (manifestsMap != null) {
			this.committedManifests = new HashMap<String, String>(manifestsMap);
			this.beforeCommitManifests = new HashMap<String, String>(manifestsMap);
		}
		parseDiff(modifications);
	}
	
	private void parseDiff(List<Modification> modifications){
		for (Modification mod : modifications) {
			if(mod.fileNameEndsWith(AndroidManifest.FILE_NAME)){
				StringBuilder oldFile = new StringBuilder();
				StringBuilder oldLines = new StringBuilder();
				boolean diffFlag = false;
				int count = 0;
				int n = 0;
				int qtdLines = 0;
				int srcLineIndex = 0;
				String[] lines = mod.getDiff().split("\n");
				// source file after commit
				String newFile = mod.getSourceCode();
				String[] sourceLines = newFile.split("\n");
				
				// run through modified lines
				for(String line : lines){
					if(diffFlag){
						if(!line.startsWith("+")){
							if (line.startsWith("-")) {
								oldLines.append(line.substring(1) + "\n");
								count--;
							} else {
								oldLines.append(line + "\n");
							}
						}
						count++;
						if(count == qtdLines){
							diffFlag = false;
							for(int i = n; i < srcLineIndex; i++){
								oldFile.append(sourceLines[i]+"\n");
							}
							oldFile.append(oldLines.toString());
							oldLines = new StringBuilder();
							n = srcLineIndex + qtdLines;
						}
					}
					/*
					 *  modification header: @@ -x,y +a,b @@
					 *  
					 *	x: starting line in old file
					 *	y: lines in old file
					 *	a: starting line in new file
					 *	b: lines in new file
					 */
					if(line.startsWith("@@") && line.endsWith("@@")){
						diffFlag = true;
						count = 0;
						// get 'a' and 'b' values 
						String[] data = line.substring(3, line.length()-3).split(" ")[1].substring(1).split(",");
						// a - 1 = index in 'sourceLines' array 
						srcLineIndex = Integer.parseInt(data[0]) - 1;
						// b = total lines to be copied 
						qtdLines = Integer.parseInt(data[1]);
					}
				}
				// get file end
				for(int i = n; i < sourceLines.length; i++){
					oldFile.append(sourceLines[i]+"\n");
				}
				
				String newPath = path + mod.getNewPath().replace("/", File.separator);
				
				beforeCommitManifests.remove(newPath);
				
				if (!mod.getOldPath().equals(NULL_PATH)) {
					beforeCommitManifests.put(path + mod.getOldPath().replace("/", File.separator), oldFile.toString());
				}
				if (!mod.getNewPath().equals(NULL_PATH)) {
					committedManifests.put(newPath, newFile);
				} else {
					committedManifests.remove(newPath);
				}
			}
		}
	}

	public Map<String, String> getCommittedManifests() {
		return committedManifests;
	}

	public void setCommittedManifests(Map<String, String> committedManifests) {
		this.committedManifests = committedManifests;
	}

	public Map<String, String> getBeforeCommitManifests() {
		return beforeCommitManifests;
	}

	public void setBeforeCommitManifests(Map<String, String> beforeCommitManifests) {
		this.beforeCommitManifests = beforeCommitManifests;
	}
}
