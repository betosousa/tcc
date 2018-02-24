package visitors;

import org.repodriller.domain.Commit;
import org.repodriller.domain.Modification;
import org.repodriller.persistence.PersistenceMechanism;
import org.repodriller.scm.SCMRepository;

public class Q5Visitor extends AbstractVisitor {
	public static final String FILE_NAME = OUTPUT_FILE_BASE_DIR
			+ "q5repodriller.csv";

	@Override
	protected String getVisitorName() {
		return "Q5 visitor";
	}

	@Override
	public void visit(SCMRepository repo, Commit commit,
			PersistenceMechanism writer) {
		int adds = 0;
		int removes = 0;
		for (Modification mod : commit.getModifications()) {
			String[] lines = mod.getDiff().split("\n");
			for (String line : lines) {
				if (line.trim().startsWith("+"))
					adds++;
				else if (line.trim().startsWith("-"))
					removes++;
			}
			writer.write(commit.getHash(), adds, removes);
		}
	}

}
