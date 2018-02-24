package visitors;

import java.util.Hashtable;
import java.util.Map;
import java.util.Map.Entry;

import org.repodriller.domain.Commit;
import org.repodriller.domain.Modification;
import org.repodriller.domain.ModificationType;
import org.repodriller.persistence.PersistenceMechanism;
import org.repodriller.persistence.csv.CSVFile;
import org.repodriller.scm.SCMRepository;

public class Q3Visitor extends AbstractVisitor {
	public static final String FILE_NAME = OUTPUT_FILE_BASE_DIR
			+ "q3repodriller.csv";

	private Map<String, Integer> files;

	public Q3Visitor() {
		this.files = new Hashtable<String, Integer>();
	}

	@Override
	protected String getVisitorName() {
		return "q3 visit";
	}

	@Override
	public void visit(SCMRepository repo, Commit commit,
			PersistenceMechanism writer) {
		for (Modification modification : commit.getModifications()) {
			if (modification.wasDeleted())
				continue;
			else if (modification.getType().equals(ModificationType.RENAME)) {
				Integer total = files.get(modification.getOldPath());
				files.remove(modification.getOldPath());
				files.put(modification.getNewPath(), total);
			}
			plusOne(modification.getNewPath());
		}
	}

	private void plusOne(String file) {
		if (!files.containsKey(file)) {
			files.put(file, 0);
		}
		files.put(file, files.get(file) + 1);
	}

	public void printResults(){
		CSVFile csv = new CSVFile(FILE_NAME);
		for (Entry<String, Integer> entry : files.entrySet()) {
			csv.write(entry.getKey(), entry.getValue());
		}
	}
}
