package tcc;

import org.repodriller.RepoDriller;
import org.repodriller.RepositoryMining;
import org.repodriller.Study;
import org.repodriller.filter.range.Commits;
import org.repodriller.persistence.csv.CSVFile;
import org.repodriller.scm.GitRepository;

import visitors.ConcreteAndroidVisitor;

public class MyStudy implements Study {
	
	//https://github.com/betosousa/fooAndroidManifest
	public static final String REPOSITORY_PATH = "D:\\11p\\TCC\\fooAndroidManifest";
	public static final String OUTPUT_PATH = "D:\\11p\\TCC\\workspace\\tcc\\plots\\";
	
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
				// .process(new XmlParserVisitor(), new CSVFile(XmlParserVisitor.FILE_NAME))
				.process(new ConcreteAndroidVisitor(), new CSVFile(OUTPUT_PATH + "androidDriller.csv")).mine();
		System.out.println("\nEnd-Execute");
	}
	
	
}
