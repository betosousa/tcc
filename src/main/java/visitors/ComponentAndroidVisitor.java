package visitors;

import java.text.SimpleDateFormat;

import org.repodriller.persistence.PersistenceMechanism;
import org.repodriller.scm.SCMRepository;

import utils.Strings;
import android.commit.AndroidCommit;
import android.diff.ComponentDiff;

public abstract class ComponentAndroidVisitor extends AndroidVisitor {

	protected abstract ComponentDiff getComponentDiff(AndroidCommit commit);
	protected abstract String getComponentName();
	
	@Override
	public void androidProcess(SCMRepository repo, AndroidCommit commit,
			PersistenceMechanism writer) {
		System.out.print(" "+getComponentName());
		
		writer.write(getComponentDiff(commit).getAdded().size(),
				getComponentDiff(commit).getRemoved().size(),
				getComponentDiff(commit).getModified().size(),
				new SimpleDateFormat(Strings.DATE_FORMAT).format(commit.getDate().getTime()));
		
		System.out.print(" "+getComponentName()+" end");

	}

}
