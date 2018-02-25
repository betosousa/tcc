package android;

import java.util.ArrayList;
import java.util.List;

import org.repodriller.domain.Modification;

import android.data.Activity;
import utils.ActivityDiffParser;

public class ActivityDiff {
	
	private List<Activity> added = new ArrayList<>();
	private List<Activity> removed = new ArrayList<>();
	
	private List<Activity> committed = new ArrayList<>();
	private List<Activity> beforeCommit = new ArrayList<>();
	
	public ActivityDiff(List<Modification> modifications){
		parseDiff(modifications);
		fillAddedAndRemoved();
	}
	
	private void parseDiff(List<Modification> modifications){
		String newFile = "";
		StringBuilder oldFile = new StringBuilder();
		for (Modification mod : modifications) {
			if(mod.fileNameEndsWith(AndroidManifest.FILE_NAME)){
				
				String[] lines = mod.getDiff().split("\n");
				boolean diffFlag = false;
				int count = 0;
				int n = 0;
				int qtdLines = 0;
				int srcLineIndex = 0;
				StringBuilder oldLines = new StringBuilder();
				// source file after commit
				newFile = mod.getSourceCode();
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
				//TODO: Consider multiple manifests
				break;
			}
		}
		this.committed = ActivityDiffParser.parseActivityList(newFile);
		this.beforeCommit = ActivityDiffParser.parseActivityList(oldFile.toString());
	}
	
	private void fillAddedAndRemoved(){
		if (committed.size() > 0 || beforeCommit.size() > 0) {
			for (Activity activity : committed) {
				if (!beforeCommit.contains(activity)) {
					added.add(activity);
				}
			}
			for (Activity activity : beforeCommit) {
				if (!committed.contains(activity)) {
					removed.add(activity);
				}
			}
		}
	}

	public List<Activity> getCommited() {
		return committed;
	}

	public List<Activity> getBeforeCommit() {
		return beforeCommit;
	}

	public List<Activity> getAdded() {
		return added;
	}

	public List<Activity> getRemoved() {
		return removed;
	}
}

