package tcc;

import org.repodriller.RepositoryMining;
import org.repodriller.Study;
import org.repodriller.filter.range.CommitRange;
import org.repodriller.filter.range.Commits;
import org.repodriller.persistence.csv.CSVFile;
import org.repodriller.scm.GitRepository;

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

	public RepoStudy(String repoName) {
		this.repoName = repoName;
		this.repositoryPath = "D:\\11p\\TCC\\repos\\" + repoName;
		this.outputPath = "D:\\11p\\TCC\\workspace\\tcc\\plots\\" + repoName + "\\";
		
//		range = Commits.betweenDates(
//				Utils.calendarFromDateString("01/12/2016"),
//				Utils.calendarFromDateString("30/09/2017")
//				);
	}
	
	@Override
	public void execute() {
		System.out.println("Start-Execute of " + repoName);
		Logger.logMessage("Init "+repoName, null);
		new RepositoryMining()
				.in(GitRepository.singleProject(repositoryPath))
				.through(range)
				.process(new ActivityAndroidVisitor(), new CSVFile(outputPath + "activityDriller.csv"))
				.process(new ServiceAndroidVisitor(), new CSVFile(outputPath + "serviceDriller.csv"))
				.process(new BroadcastReceiverAndroidVisitor(), new CSVFile(outputPath + "broadcastReceiverDriller.csv"))
				.process(new ContentProviderAndroidVisitor(), new CSVFile(outputPath + "contentProviderDriller.csv"))
				.process(new PermissionAndroidVisitor(), new CSVFile(outputPath + "permissionDriller.csv"))
				.process(new UsesPermissionAndroidVisitor(), new CSVFile(outputPath + "usesPermissionDriller.csv"))
				.mine();
		
		CommitFilesManager.reset();
		ProgressUtil.getInstance().resetProgress();
		Logger.logMessage("End "+repoName, null);
		System.out.println("\nEnd-Execute of " + repoName);
	}

}
