package visitors;

import java.text.SimpleDateFormat;

import org.repodriller.persistence.PersistenceMechanism;
import org.repodriller.scm.SCMRepository;

import utils.Strings;
import android.commit.AndroidCommit;
import android.diff.Diff;

public abstract class DiffAndroidVisitor extends AndroidVisitor {

	public DiffAndroidVisitor(String repoName) {
		super(repoName);
	}
	protected abstract Diff<?> getDiff(AndroidCommit commit);
	protected abstract String getComponentName();
	
	@Override
	public void androidProcess(SCMRepository repo, AndroidCommit commit,
			PersistenceMechanism writer) {
//		System.out.print(" "+getComponentName());
		if(commit.hasAndroidModifications()){			
			writer.write(getDiff(commit).getAdded().size(),
					getDiff(commit).getRemoved().size(),
					getDiff(commit).getModified().size(),
					getDiff(commit).getComponentTotal(),
					new SimpleDateFormat(Strings.DATE_TIME_FORMAT).format(commit.getDate().getTime()),
					commit.getHash(),
					commit.getAuthor().getName()
					);
		}
//		System.out.print(" "+getComponentName()+" end");

	}

}
