package tcc;

import java.io.File;

import org.repodriller.RepositoryMining;
import org.repodriller.Study;
import org.repodriller.filter.range.CommitRange;
import org.repodriller.filter.range.Commits;
import org.repodriller.persistence.csv.CSVFile;
import org.repodriller.scm.CollectConfiguration;
import org.repodriller.scm.GitRemoteRepository;

import logger.LoggerManager;
import utils.CommitFilesManager;
import utils.ProgressUtil;
import utils.Strings;
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
		LoggerManager.getLogger(Strings.MAIN).logMessage("Start-Execute of " + repoName);
		LoggerManager.getLogger(repoName).logMessage("Init " + repoName);
		new RepositoryMining()
				.in(GitRemoteRepository.singleProject(repositoryPath))
				.through(range)
				.collect(new CollectConfiguration().basicOnly().sourceCode().diffs())
				.process(new ActivityAndroidVisitor(repoName), new CSVFile(outputPath + "activityDriller.csv"))
				.process(new ServiceAndroidVisitor(repoName), new CSVFile(outputPath + "serviceDriller.csv"))
				.process(new BroadcastReceiverAndroidVisitor(repoName), new CSVFile(outputPath + "broadcastReceiverDriller.csv"))
				.process(new ContentProviderAndroidVisitor(repoName), new CSVFile(outputPath + "contentProviderDriller.csv"))
				.process(new PermissionAndroidVisitor(repoName), new CSVFile(outputPath + "permissionDriller.csv"))
				.process(new UsesPermissionAndroidVisitor(repoName), new CSVFile(outputPath + "usesPermissionDriller.csv"))
				.mine();
		
		CommitFilesManager.reset();
		ProgressUtil.getInstance().resetProgress();
		LoggerManager.getLogger(repoName).logMessage("End " + repoName);
		LoggerManager.closeLog(repoName);
		LoggerManager.getLogger(Strings.MAIN).logMessage("\nEnd-Execute of " + repoName);
	}

}
