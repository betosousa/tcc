/**
 * 
 */
package visitors;

import org.repodriller.domain.Commit;
import org.repodriller.persistence.PersistenceMechanism;
import org.repodriller.scm.SCMRepository;

public class Q1Visitor extends AbstractVisitor {
	public static final String FILE_NAME = OUTPUT_FILE_BASE_DIR
			+ "q1repoDriller.csv";

	@Override
	protected String getVisitorName() {
		return "q1 visit";
	}

	@Override
	public void visit(SCMRepository repo, Commit commit,
			PersistenceMechanism writer) {
		writer.write(commit.getHash(), commit.getAuthor().getName(), commit
				.getCommitter().getName());
	}

}
