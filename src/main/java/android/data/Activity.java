package android.data;

public class Activity extends Component {
	
	//TODO define which attributes are relevant.
	
	public boolean allowEmbedded = false;
	public boolean allowTaskReparenting = false;
	public boolean alwaysRetainTaskState = false;
	public boolean autoRemoveFromRecents = false;
	public String banner = NOT_SET;
	public boolean clearTaskOnLaunch = false;	
	public String configChanges = NOT_SET;
	public String documentLaunchMode = "none";
	public boolean excludeFromRecents = false;
	public boolean finishOnTaskLaunch = false;
	public boolean hardwareAccelerated = false;
	public String launchMode = "standard";
	public int maxRecents = 16;
	public boolean multiprocess = false;
	public boolean noHistory = false;
	public String parentActivityName = NOT_SET;
	public boolean relinquishTaskIdentity = false;
	public String screenOrientation = "unspecified";
	public boolean stateNotNeeded = false;
	public String taskAffinity = NOT_SET;
	public String theme = NOT_SET;
	public String uiOptions = "none";
	public String windowSoftInputMode = NOT_SET;

	public Activity() {
		super();
		super.type = ComponentType.ACTIVITY;

	}

	@Override
	public String toStringExclusiveAttributes() {
		StringBuilder builder = new StringBuilder();
        builder.append(String.format("AllowEmbedded: %s\n", allowEmbedded));
        builder.append(String.format("AllowTaskReparenting: %s\n", allowTaskReparenting));
        builder.append(String.format("AlwaysRetainTaskState: %s\n", alwaysRetainTaskState));
        builder.append(String.format("AutoRemoveFromRecents: %s\n", autoRemoveFromRecents));
        builder.append(String.format("Banner : %s\n", banner ));
        builder.append(String.format("ClearTaskOnLaunch: %s\n", clearTaskOnLaunch));
        builder.append(String.format("ConfigChanges: %s\n", configChanges));
        builder.append(String.format("DocumentLaunchMode: %s\n", documentLaunchMode));
        builder.append(String.format("ExcludeFromRecents: %s\n", excludeFromRecents));
        builder.append(String.format("FinishOnTaskLaunch: %s\n", finishOnTaskLaunch));
        builder.append(String.format("HardwareAccelerated: %s\n", hardwareAccelerated));
        builder.append(String.format("LaunchMode: %s\n", launchMode));
        builder.append(String.format("MaxRecents: %s\n", maxRecents));
        builder.append(String.format("Multiprocess: %s\n", multiprocess));
        builder.append(String.format("NoHistory: %s\n", noHistory));
        builder.append(String.format("ParentActivityName: %s\n", parentActivityName));
        builder.append(String.format("RelinquishTaskIdentity: %s\n", relinquishTaskIdentity));
        builder.append(String.format("ScreenOrientation: %s\n", screenOrientation));
        builder.append(String.format("StateNotNeeded: %s\n", stateNotNeeded));
        builder.append(String.format("TaskAffinity: %s\n", taskAffinity));
        builder.append(String.format("Theme: %s\n", theme));
        builder.append(String.format("UiOptions: %s\n", uiOptions));
        builder.append(String.format("WindowSoftInputMode: %s\n", windowSoftInputMode));		
        builder.append("\n");
		return builder.toString();
	}

	@Override
	protected boolean hasExclusiveAttributesModifications(Component other) {
		Activity otherActivity = (Activity) other;
		return this.allowEmbedded != otherActivity.allowEmbedded
				|| this.allowTaskReparenting != otherActivity.allowTaskReparenting
				|| this.alwaysRetainTaskState != otherActivity.alwaysRetainTaskState
				|| this.autoRemoveFromRecents != otherActivity.autoRemoveFromRecents
				|| this.clearTaskOnLaunch != otherActivity.clearTaskOnLaunch
				|| this.excludeFromRecents != otherActivity.excludeFromRecents
				|| this.finishOnTaskLaunch != otherActivity.finishOnTaskLaunch
				|| this.hardwareAccelerated != otherActivity.hardwareAccelerated
				|| this.multiprocess != otherActivity.multiprocess
				|| this.noHistory != otherActivity.noHistory
				|| this.relinquishTaskIdentity != otherActivity.relinquishTaskIdentity
				|| this.stateNotNeeded != otherActivity.stateNotNeeded
				|| !this.banner.equals(otherActivity.banner)
				|| !this.configChanges.equals(otherActivity.configChanges)
				|| !this.documentLaunchMode
						.equals(otherActivity.documentLaunchMode)
				|| !this.launchMode.equals(otherActivity.launchMode)
				|| this.maxRecents != otherActivity.maxRecents
				|| !this.parentActivityName
						.equals(otherActivity.parentActivityName)
				|| !this.screenOrientation
						.equals(otherActivity.screenOrientation)
				|| !this.taskAffinity.equals(otherActivity.taskAffinity)
				|| !this.theme.equals(otherActivity.theme)
				|| !this.uiOptions.equals(otherActivity.uiOptions)
				|| !this.windowSoftInputMode
						.equals(otherActivity.windowSoftInputMode);
	}

}

