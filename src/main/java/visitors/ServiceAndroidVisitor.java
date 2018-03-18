package visitors;

import java.text.SimpleDateFormat;

import org.repodriller.persistence.PersistenceMechanism;
import org.repodriller.scm.SCMRepository;

import utils.Strings;
import android.commit.AndroidCommit;
import android.diff.ServiceDiff;

public class ServiceAndroidVisitor extends AndroidVisitor {

	@Override
	public void androidProcess(SCMRepository repo, AndroidCommit commit,
			PersistenceMechanism writer) {
		System.out.print(" Service");
		ServiceDiff serviceDiff = commit.getServiceDiff(); 
		
		writer.write(serviceDiff.getAdded().size(),
				serviceDiff.getRemoved().size(),
				new SimpleDateFormat(Strings.DATE_FORMAT).format(commit.getDate().getTime()));
		
		System.out.print(" Service end");
	}

}
