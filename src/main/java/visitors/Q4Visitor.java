package visitors;

import java.io.File;
import java.util.List;

import org.repodriller.domain.Commit;
import org.repodriller.persistence.PersistenceMechanism;
import org.repodriller.scm.RepositoryFile;
import org.repodriller.scm.SCMRepository;

import utils.Utils;

public class Q4Visitor extends AbstractVisitor {

	public static final String FILE_NAME = OUTPUT_FILE_BASE_DIR
			+ "q4repoDriller.csv";
	
	@Override
	protected String getVisitorName() {
		return "q4 visit";
	}

	@Override
	public void visit(SCMRepository repo, Commit commit,
			PersistenceMechanism writer) {
		try {
			
			repo.getScm().checkout(commit.getHash());
			List<RepositoryFile> files = repo.getScm().files();

			int totalLoc = 0;

			for (RepositoryFile repoFile : files) {
				if (!repoFile.fileNameEndsWith("java"))
					continue;
				File sourceFile = repoFile.getFile();
				String fileString = Utils.readFile(sourceFile);
				totalLoc += Utils.countLineNumbers(fileString);
			}
			writer.write(commit.getHash(),
			totalLoc);
			//writer.write(new SimpleDateFormat("YYYY/MM/dd HH:mm:ss")
				//	.format(commit.getDate().getTime()), commit.getHash(),
					//totalLoc);
		} catch(Exception e){ 
			throw new RuntimeException("Error", e);
		} finally {
			repo.getScm().reset();
		}
	}

}
