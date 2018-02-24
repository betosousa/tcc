package visitors;

import org.repodriller.domain.Commit;
import org.repodriller.persistence.PersistenceMechanism;
import org.repodriller.scm.CommitVisitor;
import org.repodriller.scm.SCMRepository;

public abstract class AbstractVisitor implements CommitVisitor {
	
	protected static final String OUTPUT_FILE_BASE_DIR = "C:/Users/Beto/Documents/aps/";
		
	protected abstract String getVisitorName();
	
	public abstract void visit(SCMRepository repo, Commit commit,
			PersistenceMechanism writer);
	
	public void process(SCMRepository repo, Commit commit,
			PersistenceMechanism writer) {
		System.out.println(getVisitorName());
		visit(repo, commit, writer);
	}

}
