package tcc;

import org.repodriller.RepoDriller;
import org.repodriller.RepositoryMining;
import org.repodriller.Study;
import org.repodriller.filter.range.Commits;
import org.repodriller.persistence.csv.CSVFile;
import org.repodriller.scm.GitRepository;

import visitors.ActivityAndroidVisitor;
import visitors.BroadcastReceiverAndroidVisitor;
import visitors.ContentProviderAndroidVisitor;
import visitors.PermissionAndroidVisitor;
import visitors.ServiceAndroidVisitor;
import visitors.UsesPermissionAndroidVisitor;

public class MyStudy implements Study {
	
	//https://github.com/betosousa/fooAndroidManifest
	public static final String REPO_NAME = "fooAndroidManifest";
	public static final String REPOSITORY_PATH = "D:\\11p\\TCC\\repos\\" + REPO_NAME;
	public static final String OUTPUT_PATH = "D:\\11p\\TCC\\workspace\\tcc\\plots\\" + REPO_NAME + "\\";
	
	public static void main(String[] args) {
		System.out.println("Start-Main");
		new RepoDriller().start(new MyStudy());
		System.out.println("End-Main");
	}

	public void execute() {
		System.out.println("Start-Execute");
		new RepositoryMining()
				.in(GitRepository.singleProject(REPOSITORY_PATH))
				.through(Commits.all())
				.process(new ActivityAndroidVisitor(), new CSVFile(OUTPUT_PATH + "activityDriller.csv"))
				.process(new ServiceAndroidVisitor(), new CSVFile(OUTPUT_PATH + "serviceDriller.csv"))
				.process(new BroadcastReceiverAndroidVisitor(), new CSVFile(OUTPUT_PATH + "broadcastReceiverDriller.csv"))
				.process(new ContentProviderAndroidVisitor(), new CSVFile(OUTPUT_PATH + "contentProviderDriller.csv"))
				.process(new PermissionAndroidVisitor(), new CSVFile(OUTPUT_PATH + "permissionDriller.csv"))
				.process(new UsesPermissionAndroidVisitor(), new CSVFile(OUTPUT_PATH + "usesPermissionDriller.csv"))
				.mine();
		System.out.println("\nEnd-Execute");
	}
	
	
}
