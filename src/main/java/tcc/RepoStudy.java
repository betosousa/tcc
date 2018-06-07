package tcc;

import java.io.File;

import org.repodriller.RepositoryMining;
import org.repodriller.Study;
import org.repodriller.filter.range.CommitRange;
import org.repodriller.filter.range.Commits;
import org.repodriller.persistence.csv.CSVFile;
import org.repodriller.scm.CollectConfiguration;
import org.repodriller.scm.GitRemoteRepository;

import utils.CommitFilesManager;
import utils.Logger;
import utils.ProgressUtil;
import visitors.ActivityAndroidVisitor;
import visitors.BroadcastReceiverAndroidVisitor;
import visitors.ContentProviderAndroidVisitor;
import visitors.PermissionAndroidVisitor;
import visitors.ServiceAndroidVisitor;
import visitors.UsesPermissionAndroidVisitor;

public class RepoStudy implements Study {

	private String repoName;
	private String repositoryPath;
	private String outputPath;
	
	private CommitRange range = Commits.all();

	public RepoStudy(String repoURL) {
		this.repoName = getRepoName(repoURL);
		this.repositoryPath = repoURL;
		this.outputPath = "androidDriller" + File.separator + "output" + File.separator + repoName + File.separator;
		
		new File(outputPath).mkdirs();
//		range = Commits.betweenDates(
//				Utils.calendarFromDateString("01/11/2017"),
//				Utils.calendarFromDateString("25/03/2018")
//				);
	}
	
	private String getRepoName(String repoUrl){
		if(repoUrl.endsWith(".git")){
			return repoUrl.substring(repoUrl.lastIndexOf('/')+1, repoUrl.indexOf(".git"));
		}
		return repoUrl.substring(repoUrl.lastIndexOf('/')+1);
	}
	
	@Override
	public void execute() {
		System.out.println("Start-Execute of " + repoName);
		Logger.logMessage("Init " + repoName);
		new RepositoryMining()
				.in(GitRemoteRepository.singleProject(repositoryPath))
				.through(range)
				.collect(new CollectConfiguration().basicOnly().sourceCode().diffs())
				.process(new ActivityAndroidVisitor(), new CSVFile(outputPath + "activityDriller.csv"))
				.process(new ServiceAndroidVisitor(), new CSVFile(outputPath + "serviceDriller.csv"))
				.process(new BroadcastReceiverAndroidVisitor(), new CSVFile(outputPath + "broadcastReceiverDriller.csv"))
				.process(new ContentProviderAndroidVisitor(), new CSVFile(outputPath + "contentProviderDriller.csv"))
				.process(new PermissionAndroidVisitor(), new CSVFile(outputPath + "permissionDriller.csv"))
				.process(new UsesPermissionAndroidVisitor(), new CSVFile(outputPath + "usesPermissionDriller.csv"))
				.mine();
		
		CommitFilesManager.reset();
		ProgressUtil.getInstance().resetProgress();
		Logger.logMessage("End " + repoName);
		System.out.println("\nEnd-Execute of " + repoName);
	}

}
