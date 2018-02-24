package tcc;

import org.repodriller.RepoDriller;
import org.repodriller.RepositoryMining;
import org.repodriller.Study;
import org.repodriller.filter.range.Commits;
import org.repodriller.persistence.csv.CSVFile;
import org.repodriller.scm.GitRepository;

import visitors.Q4Visitor;

public class TutorialStudy implements Study {
	
	private static String P3 = "D:/9p/P3/tarefa01-betosousa";
	//private static String APS = "C:/Users/Beto/Documents/aps";
	public void execute() {
		System.out.println("execute");
		//Q3Visitor q3Visitor = new Q3Visitor();

		new RepositoryMining()
				.in(GitRepository.singleProject(P3))
				.through(Commits.all())
				//.process(new DevelopersVisitor(), new CSVFile(DevelopersVisitor.FILE_NAME))
				//.process(new Q1Visitor(), new CSVFile(Q1Visitor.FILE_NAME))
				//.process(new Q2Visitor(), new CSVFile(Q2Visitor.FILE_NAME))
				//.process(q3Visitor)
				.process(new Q4Visitor(), new CSVFile(Q4Visitor.FILE_NAME))
				//.process(new Q5Visitor(), new CSVFile(Q5Visitor.FILE_NAME))
				.mine();

		System.out.println("FIM -- Execute");

		//q3Visitor.printResults();

	}

	public static void main(String[] args) {
		System.out.println("main");
		new RepoDriller().start(new TutorialStudy());
		System.out.println("FIM -- Main");
	}
}
