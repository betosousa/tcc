package visitors;

import org.repodriller.domain.Commit;
import org.repodriller.persistence.PersistenceMechanism;
import org.repodriller.scm.SCMRepository;

public class DevelopersVisitor extends AbstractVisitor {
	public static final String FILE_NAME = OUTPUT_FILE_BASE_DIR
			+ "repoDriller.csv";

	@Override
	protected String getVisitorName() {
		return "Dev visitor";
	}

	@Override
	public void visit(SCMRepository repo, Commit commit,
			PersistenceMechanism writer) {
		writer.write(commit.getHash(), commit.getCommitter().getName());
	}
}
