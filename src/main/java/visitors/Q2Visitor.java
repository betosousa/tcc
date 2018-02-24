package visitors;

import org.repodriller.domain.Commit;
import org.repodriller.persistence.PersistenceMechanism;
import org.repodriller.scm.SCMRepository;

public class Q2Visitor extends AbstractVisitor {
	public static final String FILE_NAME = OUTPUT_FILE_BASE_DIR
			+ "q2repoDriller.csv";

	@Override
	protected String getVisitorName() {
		return "q2 visit";
	}

	@Override
	public void visit(SCMRepository repo, Commit commit,
			PersistenceMechanism writer) {
		writer.write(commit.getHash(), commit.getAuthor().getName(),
				commit.getMsg());
	}

}
