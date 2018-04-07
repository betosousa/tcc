package tcc;

import org.repodriller.RepositoryMining;
import org.repodriller.Study;
import org.repodriller.filter.range.Commits;
import org.repodriller.persistence.csv.CSVFile;
import org.repodriller.scm.GitRepository;

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

	public RepoStudy(String repoName) {
		this.repoName = repoName;
		this.repositoryPath = "D:\\11p\\TCC\\repos\\" + repoName;
		this.outputPath = "D:\\11p\\TCC\\workspace\\tcc\\plots\\" + repoName + "\\";
	}
	
	@Override
	public void execute() {
		System.out.println("Start-Execute of " + repoName);
		new RepositoryMining()
				.in(GitRepository.singleProject(repositoryPath))
				.through(Commits.all())
				.process(new ActivityAndroidVisitor(), new CSVFile(outputPath + "activityDriller.csv"))
				.process(new ServiceAndroidVisitor(), new CSVFile(outputPath + "serviceDriller.csv"))
				.process(new BroadcastReceiverAndroidVisitor(), new CSVFile(outputPath + "broadcastReceiverDriller.csv"))
				.process(new ContentProviderAndroidVisitor(), new CSVFile(outputPath + "contentProviderDriller.csv"))
				.process(new PermissionAndroidVisitor(), new CSVFile(outputPath + "permissionDriller.csv"))
				.process(new UsesPermissionAndroidVisitor(), new CSVFile(outputPath + "usesPermissionDriller.csv"))
				.mine();
		
		System.out.println("\nEnd-Execute of "+repoName);
		ProgressUtil.getInstance().resetProgress();
	}

}
