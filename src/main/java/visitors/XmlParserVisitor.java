package visitors;

import org.repodriller.domain.Commit;
import org.repodriller.domain.Modification;
import org.repodriller.persistence.PersistenceMechanism;
import org.repodriller.scm.SCMRepository;

public class XmlParserVisitor extends AbstractVisitor {
	
	public static final String FILE_NAME = OUTPUT_FILE_BASE_DIR + "manifests.csv";
	
	@Override
	protected String getVisitorName() {
		return "XML parser visitor";
	}

	@Override
	public void visit(SCMRepository repo, Commit commit,
			PersistenceMechanism writer) {
		
		for (Modification modification : commit.getModifications()) {
			if (modification.fileNameEndsWith("AndroidManifest.xml")){
				writer.write("manifest: ", modification.getSourceCode());
				
				break;
			}
		}

	}

}
