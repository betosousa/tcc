package visitors;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.repodriller.domain.Commit;
import org.repodriller.persistence.PersistenceMechanism;
import org.repodriller.scm.CommitVisitor;
import org.repodriller.scm.RepositoryFile;
import org.repodriller.scm.SCMRepository;

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
			//System.out.print("\nAndroidVisitor");
			repo.getScm().checkout(commit.getHash());
			List<RepositoryFile> files = repo.getScm().files();
			//System.out.print(" checkout");
			for (RepositoryFile repoFile : files) {
				if (repoFile.fileNameEndsWith("apk")) {
					apkFilePath = repoFile.getFile().getAbsolutePath();
				//	System.out.print(" apk");
				} else if (repoFile.fileNameEndsWith(AndroidManifest.FILE_NAME)) {
					manifests.put(repoFile.getFile().getPath(), repoFile.getSourceCode());
					//System.out.print(" manifest");
					repo.getPath();
				}
			}
		} catch(Exception e){ 
			throw new RuntimeException("Error", e);
		} finally {
			repo.getScm().reset();
		}
		//System.out.print(" AndroidVisitor end");
		androidProcess(repo, new AndroidCommit(commit, apkFilePath, manifests, repo.getPath()), writer);
	}

}
