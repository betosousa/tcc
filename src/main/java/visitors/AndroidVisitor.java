package visitors;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.repodriller.domain.Commit;
import org.repodriller.persistence.PersistenceMechanism;
import org.repodriller.scm.CommitVisitor;
import org.repodriller.scm.RepositoryFile;
import org.repodriller.scm.SCMRepository;

import utils.Strings;
import android.AndroidManifest;
import android.commit.AndroidCommit;

public abstract class AndroidVisitor implements CommitVisitor {
	
	
	public abstract void androidProcess(SCMRepository repo,
			AndroidCommit commit, PersistenceMechanism writer);

	@Override
	public void process(SCMRepository repo, Commit commit,
			PersistenceMechanism writer) {
		String apkFilePath = "";
		Map<String, String> manifests = new HashMap<>();
		try {
			repo.getScm().checkout(commit.getHash());
			List<RepositoryFile> files = repo.getScm().files();
			for (RepositoryFile repoFile : files) {
				if (repoFile.fileNameEndsWith(Strings.APK)) {
					apkFilePath = repoFile.getFile().getAbsolutePath();
				} else if (repoFile.fileNameEndsWith(AndroidManifest.FILE_NAME)) {
					manifests.put(repoFile.getFile().getPath(), repoFile.getSourceCode());
					repo.getPath();
				}
			}
		} catch(Exception e){ 
			throw new RuntimeException(Strings.ERROR, e);
		} finally {
			repo.getScm().reset();
		}
		androidProcess(repo, new AndroidCommit(commit, apkFilePath, manifests, repo.getPath()), writer);
	}

}
