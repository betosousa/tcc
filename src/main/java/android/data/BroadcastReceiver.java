package android.data;

public class BroadcastReceiver extends Component {

	public BroadcastReceiver() {
		super();
		super.type = ComponentType.BROADCAST_RECEIVER;
	}

	@Override
	public String toStringExclusiveAttributes() {
		return "";
	}

}

