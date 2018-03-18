package android.diff;

import java.util.List;

import android.AndroidManifest;
import android.data.BroadcastReceiver;
import android.data.ComponentType;

public class BroadcastReceiverDiff extends ComponentDiff {

	public BroadcastReceiverDiff(ManifestDiff manifestDiff) {
		super(manifestDiff);
	}

	@Override
	@SuppressWarnings("unchecked")
	protected List<BroadcastReceiver> getComponents(AndroidManifest manifest) {
		return manifest.getComponents(ComponentType.BROADCAST_RECEIVER);
	}

}
