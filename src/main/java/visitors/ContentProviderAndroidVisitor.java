package visitors;

import java.text.SimpleDateFormat;

import org.repodriller.persistence.PersistenceMechanism;
import org.repodriller.scm.SCMRepository;

import utils.Strings;
import android.commit.AndroidCommit;
import android.diff.ContentProviderDiff;

public class ContentProviderAndroidVisitor extends AndroidVisitor {

	@Override
	public void androidProcess(SCMRepository repo, AndroidCommit commit,
			PersistenceMechanism writer) {
		System.out.print(" ContentProvider");
		ContentProviderDiff contentProviderDiff = commit.getContentProviderDiff(); 
		
		writer.write(contentProviderDiff.getAdded().size(),
				contentProviderDiff.getRemoved().size(),
				new SimpleDateFormat(Strings.DATE_FORMAT).format(commit.getDate().getTime()));
		
		System.out.print(" ContentProvider end");
	}

}
