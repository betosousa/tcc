package visitors;

import java.text.SimpleDateFormat;

import org.repodriller.persistence.PersistenceMechanism;
import org.repodriller.scm.SCMRepository;

import utils.Strings;
import android.commit.AndroidCommit;
import android.diff.BroadcastReceiverDiff;

public class BroadcastReceiverAndroidVisitor extends AndroidVisitor {

	@Override
	public void androidProcess(SCMRepository repo, AndroidCommit commit,
			PersistenceMechanism writer) {
		System.out.print(" BrodacastReceiver");
		BroadcastReceiverDiff broadcastReceiverDiff = commit.getBroadcastReceiverDiff(); 
		
		writer.write(broadcastReceiverDiff.getAdded().size(),
				broadcastReceiverDiff.getRemoved().size(),
				new SimpleDateFormat(Strings.DATE_FORMAT).format(commit.getDate().getTime()));
		
		System.out.print(" BrodacastReceiver end");
	}

}
