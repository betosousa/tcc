package utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.StringTokenizer;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import android.AndroidManifest;
import android.data.Activity;
import android.data.BroadcastReceiver;
import android.data.Component;
import android.data.ContentProvider;
import android.data.IntentFilter;
import android.data.Service;

public class AndroidManifestParser extends DefaultHandler {
	static final String MANIFEST_TAG = "manifest";
	static final String APPLICATION_TAG = "application";
	static final String ACTIVITY_TAG = "activity";
	static final String SERVICE_TAG = "service";
	static final String PROVIDER_TAG = "provider";
	static final String RECEIVER_TAG = "receiver";
	static final String INTENT_FILTER_TAG = "intent-filter";
	static final String ACTION_TAG = "action";
	static final String CATEGORY_TAG = "category";
	static final String DATA_TAG = "data";
	static final String USES_PERMISSIONS_TAG = "uses-permission";
	static final String USES_SDK_TAG = "uses-sdk";

	static final String DATA_SCHEME_ATTR = "android:scheme";
	static final String DATA_HOST_ATTR = "android:host";
	static final String DATA_PORT_ATTR = "android:port";
	static final String DATA_PATH_ATTR = "android:path";
	static final String DATA_PATH_PATTERN_ATTR = "android:pathPattern";
	static final String DATA_PATH_PREFIX_ATTR = "android:pathPrefix";
	static final String DATA_MIME_TYPE_ATTR = "android:mimeType";


	
	private AndroidManifest androidManifest;
	
	String currentComponent;

	Component currComponent;
	IntentFilter currentIntentFilter;

	public AndroidManifestParser() {
		this.androidManifest = new AndroidManifest();
	}

	public AndroidManifestParser(String manifestPath) throws Exception {
		this();
		SAXParser parser = SAXParserFactory.newInstance().newSAXParser();
		parser.parse(new FileInputStream(new File(manifestPath)), this);
	}
	
	public static AndroidManifest parse(String manifestPath){
		if (manifestPath != null && !manifestPath.isEmpty()) {
			try {
				AndroidManifestParser mParser = new AndroidManifestParser();
				SAXParser parser = SAXParserFactory.newInstance()
						.newSAXParser();
				parser.parse(new FileInputStream(new File(manifestPath)),
						mParser);
				return mParser.androidManifest;
			} catch (ParserConfigurationException | SAXException | IOException e) {
				e.printStackTrace();
			}
		}
		return null;
	}
	
	public void setCommonComponentsAttrs(Component c, Attributes attrs) {
		// TODO: maybe checking if getValue == null so we do not set label and
		// other attributes to null?
		String s;
		c.label = null == (s = attrs.getValue("android:label")) ? c.label : s;
		c.name = null == (s = attrs.getValue("android:name")) ? c.name : s;

		String permission = attrs.getValue("android:permission");

		switch (c.type) {
		case CONTENT_PROVIDER:
			c.permission = null == (permission) ? c.permission : permission;
			break;
		default:
			if (null == permission) {
				c.permission = androidManifest.application.permission;
			} else {
				c.permission = permission;
			}
			break;
		}

		c.process = null == (s = attrs.getValue("android:process")) ? androidManifest.application.process : s;
		c.enabled = ("false").equals(attrs.getValue("android:enabled")) ? false : true;
		// c.icon = null == (s = attributes.getValue("android:icon")) ? c.icon :
		// s;

		// It is a common attribute, but may have different rules (by component)
		c.exported = ("true").equals(attrs.getValue("android:exported")) ? true : false;
	}

	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
		switch (qName) {

		case MANIFEST_TAG:
			if (attributes != null) {
				androidManifest.appPackage = attributes.getValue("package");
			}
			break;

		case USES_PERMISSIONS_TAG:
			if (attributes != null) {
				androidManifest.permissions.add(attributes.getValue("android:name"));
			}
			break;
		case USES_SDK_TAG:
			String in;
			androidManifest.minSdkVersion = null == (in = attributes.getValue("android:minSdkVersion")) ? 1 : Integer.parseInt(in);
			androidManifest.targetSdkVersion = null == (in = attributes.getValue("android:targetSdkVersion")) ? androidManifest.minSdkVersion
					: Integer.parseInt(in);

			break;
		case APPLICATION_TAG:
			String a;
			if (attributes != null) {
				androidManifest.application.allowTaskReparenting = ("true")
						.equals(attributes.getValue("android:allowTaskReparenting")) ? true : false;
				androidManifest.application.allowBackup = ("false").equals(attributes.getValue("android:allowBackup")) ? false
						: true;
				androidManifest.application.backupAgent = (null) == (a = attributes.getValue("android:backupAgent"))
						? androidManifest.application.backupAgent : a;
				androidManifest.application.banner = (null) == (a = attributes.getValue("android:banner")) ? androidManifest.application.banner
						: a;
				androidManifest.application.debuggable = ("true").equals(attributes.getValue("android:debuggable")) ? true : false;
				androidManifest.application.description = (null) == (a = attributes.getValue("android:description"))
						? androidManifest.application.description : a;
				androidManifest.application.enabled = ("false").equals(attributes.getValue("android:enabled")) ? false : true;

				androidManifest.application.hasCode= ("false").equals(attributes.getValue("android:hasCode")) ? false : true; 
				
				androidManifest.application.hardwareAcceleratedWasSetted = null == attributes.getValue("android:hardwareAccelerated") ? false : true;
				if(!androidManifest.application.hardwareAcceleratedWasSetted){
					androidManifest.application.setDefaulthardwareAccelerated(androidManifest.minSdkVersion, androidManifest.targetSdkVersion);
				} else {
					androidManifest.application.hardwareAccelerated = ("true")
							.equals(attributes.getValue("android:hardwareAccelerated")) ? true : false;
				}

				androidManifest.application.icon = (null) == (a = attributes.getValue("android:icon")) ? androidManifest.application.icon : a;
				androidManifest.application.isGame = ("true").equals(attributes.getValue("android:isGame")) ? true : false;
				androidManifest.application.killAfterRestore = ("false").equals(attributes.getValue("android:killAfterRestore"))
						? false : true;
				androidManifest.application.largeHeap = ("true").equals(attributes.getValue("android:largeHeap")) ? true : false;
				androidManifest.application.label = (null) == (a = attributes.getValue("android:label")) ? androidManifest.application.label : a;
				androidManifest.application.logo = (null) == (a = attributes.getValue("android:logo")) ? androidManifest.application.logo : a;
				androidManifest.application.manageSpaceActivity = (null) == (a = attributes
						.getValue("android:manageSpaceActivity")) ? androidManifest.application.manageSpaceActivity : a;
				androidManifest.application.name = (null) == (a = attributes.getValue("android:name")) ? androidManifest.application.name : a;
				androidManifest.application.permission = (null) == (a = attributes.getValue("android:permission"))
						? androidManifest.application.permission : a;
				androidManifest.application.persistent = ("true").equals(attributes.getValue("android:persistent")) ? true : false;
				androidManifest.application.process = (null) == (a = attributes.getValue("android:process")) ? androidManifest.appPackage : a;
				androidManifest.application.restoreAnyVersion = ("true").equals(attributes.getValue("android:restoreAnyVersion"))
						? true : false;
				;
				androidManifest.application.requiredAccountType = (null) == (a = attributes
						.getValue("android:requiredAccountType")) ? androidManifest.application.requiredAccountType : a;
				androidManifest.application.restrictedAccountType = (null) == (a = attributes
						.getValue("android:restrictedAccountType")) ? androidManifest.application.restrictedAccountType : a;
				androidManifest.application.supportsRtl = ("true").equals(attributes.getValue("android:supportsRtl")) ? true
						: false;
				;
				;

				androidManifest.application.taskAffinity = (null) == (a = attributes.getValue("android:taskAffinity"))
						? androidManifest.appPackage : a;

				androidManifest.application.testOnly = ("true").equals(attributes.getValue("android:testOnly")) ? true : false;
				androidManifest.application.theme = (null) == (a = attributes.getValue("android:theme")) ? androidManifest.application.theme : a;
				androidManifest.application.uiOptions = (null) == (a = attributes.getValue("android:uiOptions"))
						? androidManifest.application.uiOptions : a;
				;
				androidManifest.application.usesCleartextTraffic = ("false")
						.equals(attributes.getValue("android:usesCleartextTraffic")) ? false : true;
				androidManifest.application.vmSafeMode = ("true").equals(attributes.getValue("vmSafeMode")) ? true : false;
				;
			}
			break;
		case ACTIVITY_TAG:
			if (attributes != null) {
				String s;
				Activity c = new Activity();
				setCommonComponentsAttrs(c, attributes);
				c.allowEmbedded = ("true").equals(attributes.getValue("android:allowEmbedded")) ? true : false;

				String allowTaksReparenting = attributes.getValue("android:allowTaskReparenting");
				if (null == allowTaksReparenting) {
					c.allowTaskReparenting = androidManifest.application.allowTaskReparenting;
				} else if (("true").equals(allowTaksReparenting)) {
					c.allowTaskReparenting = true;
				} else {
					c.allowTaskReparenting = false;
				}

				c.alwaysRetainTaskState = ("true").equals(attributes.getValue("android:alwaysRetainTaskState")) ? true
						: false;
				c.autoRemoveFromRecents = ("true").equals(attributes.getValue("android:autoRemoveFromRecents")) ? true
						: false;
				c.banner = null == (s = attributes.getValue("android:banner")) ? c.banner : s;
				c.clearTaskOnLaunch = ("true").equals(attributes.getValue("android:clearTaskOnLaunch")) ? true : false;
				c.configChanges = null == (s = attributes.getValue("android:configChanges")) ? c.configChanges : s;
				c.documentLaunchMode = null == (s = attributes.getValue("android:documentLaunchMode"))
						? c.documentLaunchMode : s;
				c.excludeFromRecents = ("true").equals(attributes.getValue("android:excludeFromRecents")) ? true
						: false;
				c.finishOnTaskLaunch = ("true").equals(attributes.getValue("android:finishOnTaskLaunch")) ? true
						: false;
				c.hardwareAccelerated = ("true").equals(attributes.getValue("android:hardwareAccelerated")) ? true
						: false;
				c.launchMode = null == (s = attributes.getValue("android:launchMode")) ? c.launchMode : s;
				c.maxRecents = null == (s = attributes.getValue("android:maxRecents")) ? c.maxRecents
						: Integer.parseInt(s);
				c.multiprocess = ("true").equals(attributes.getValue("android:multiprocess")) ? true : false;
				c.noHistory = ("true").equals(attributes.getValue("android:noHistory")) ? true : false;
				c.parentActivityName = null == (s = attributes.getValue("android:parentActivityName"))
						? c.parentActivityName : s;
				c.relinquishTaskIdentity = ("true").equals(attributes.getValue("android:relinquishTaskIdentify")) ? true
						: false;
				c.screenOrientation = null == (s = attributes.getValue("android:screenOrientation"))
						? c.screenOrientation : s;
				c.stateNotNeeded = ("true").equals(attributes.getValue("android:stateNotNeeded")) ? true : false;
				c.taskAffinity = null == (s = attributes.getValue("android:taskAffinity"))
						? androidManifest.application.taskAffinity : s;
				c.theme = null == (s = attributes.getValue("android:theme")) ? androidManifest.application.theme : s;
				c.uiOptions = null == (s = attributes.getValue("android:uiOptions")) ? c.uiOptions : s;
				c.windowSoftInputMode = null == (s = attributes.getValue("android:windowSoftInputMode"))
						? c.windowSoftInputMode : s;
				this.currComponent = c;
				androidManifest.components.add(c);

				// TODO: remove the following line once we finish working on the
				// rest of implementation
				this.currentComponent = attributes.getValue("android:name");
				androidManifest.intentFilters.put(this.currentComponent, new ArrayList<IntentFilter>());
			}
			break;

		case SERVICE_TAG:
			if (attributes != null) {
				Service c = new Service();
				setCommonComponentsAttrs(c, attributes);

				c.isolatedProcess = ("true").equals(attributes.getValue("android:isolatedProcess")) ? true : false;

				this.currComponent = c;
				androidManifest.components.add(c);

				// TODO: remove the following line once we finish working on the
				// rest of implementation
				this.currentComponent = attributes.getValue("android:name");
				androidManifest.intentFilters.put(this.currentComponent, new ArrayList<IntentFilter>());
			}
			break;

		// TODO: capture dynamically registered broadcast receiver
		// leopoldo: we have to look in the code for that, we won't be able to
		// capture them right here
		case RECEIVER_TAG:
			if (attributes != null) {
				BroadcastReceiver c = new BroadcastReceiver();
				setCommonComponentsAttrs(c, attributes);

				this.currComponent = c;
				androidManifest.components.add(c);

				// TODO: remove the following line once we finish working on the
				// rest of implementation
				this.currentComponent = attributes.getValue("android:name");
				androidManifest.intentFilters.put(this.currentComponent, new ArrayList<IntentFilter>());
			}
			break;

		case PROVIDER_TAG:
			if (attributes != null) {
				ContentProvider c = new ContentProvider();
				setCommonComponentsAttrs(c, attributes);

				c.exportedWasSetted = null == attributes.getValue("android:exported") ? false : true;
				if (!c.exportedWasSetted) {
					c.setDefaultExported(androidManifest.minSdkVersion, androidManifest.targetSdkVersion);
				}

				String s;
				StringTokenizer auts = new StringTokenizer(attributes.getValue("android:authorities"), ";");
				while (auts.hasMoreElements()) {
					c.authorities.add((String) auts.nextElement());
				}

				c.grantUriPermissions = ("true").equals(attributes.getValue("android:grantUriPermissions")) ? true
						: false;
				c.initOrder = null == (s = attributes.getValue("android:initOrder")) ? 0 : Integer.parseInt(s);
				c.multiprocess = ("true").equals(attributes.getValue("android:multiprocess")) ? true : false;
				c.readPermission = null == (s = attributes.getValue("android:readPermission")) ? c.readPermission : s;
				c.syncable = ("true").equals(attributes.getValue("android:syncable")) ? true : false;
				c.writePermission = null == (s = attributes.getValue("android:writePermission")) ? c.writePermission
						: s;

				this.currComponent = c;
				androidManifest.components.add(c);

				// TODO: remove the following line once we finish working on the
				// rest of implementation
				this.currentComponent = attributes.getValue("android:name");
				androidManifest.intentFilters.put(this.currentComponent, new ArrayList<IntentFilter>());
			}

			break;

		case INTENT_FILTER_TAG:
			if (attributes != null) {
				this.currentIntentFilter = new IntentFilter();
			}
			break;

		case ACTION_TAG:
			if (attributes != null) {
				this.currentIntentFilter.actions.add(attributes.getValue("android:name"));
			}
			break;

		case CATEGORY_TAG:
			if (attributes != null) {
				this.currentIntentFilter.categories.add(attributes.getValue("android:name"));
			}
			break;

		case DATA_TAG:
			if (attributes != null) {
				for (int i = 0; i < attributes.getLength(); i++) {
					switch (attributes.getQName(i)) {
					case DATA_SCHEME_ATTR:
						this.currentIntentFilter.data.scheme = attributes.getValue(i);
						break;

					case DATA_HOST_ATTR:
						this.currentIntentFilter.data.host = attributes.getValue(i);
						break;

					case DATA_PORT_ATTR:
						this.currentIntentFilter.data.port = attributes.getValue(i);
						break;

					case DATA_PATH_ATTR:
						this.currentIntentFilter.data.path = attributes.getValue(i);
						break;

					case DATA_PATH_PATTERN_ATTR:
						this.currentIntentFilter.data.pathPattern = attributes.getValue(i);
						break;

					case DATA_PATH_PREFIX_ATTR:
						this.currentIntentFilter.data.pathPrefix = attributes.getValue(i);
						break;

					case DATA_MIME_TYPE_ATTR:
						this.currentIntentFilter.data.mimeType = attributes.getValue(i);
						break;
					}
				}
			}
			break;
		}
	}

	public void endElement(String uri, String localName, String qName) throws SAXException {
		if (qName.equals(INTENT_FILTER_TAG)) {
			((ArrayList<IntentFilter>) androidManifest.intentFilters.get(this.currentComponent)).add(this.currentIntentFilter);
			this.currComponent.intentFilters.add(this.currentIntentFilter);
			if (currComponent.intentFilters.size() > 0) {
				switch (currComponent.type) {
				case ACTIVITY:
				case BROADCAST_RECEIVER:
				case SERVICE:
					currComponent.exported = true;
					break;
				case CONTENT_PROVIDER:
					// special
					break;
				}
			}
		} else if (qName.equals(USES_SDK_TAG)) {
			ContentProvider cp;
			for (Component c : androidManifest.components) {
				if (c instanceof ContentProvider) {
					cp = (ContentProvider) c;
					if (!cp.exportedWasSetted) {
						cp.setDefaultExported(androidManifest.minSdkVersion, androidManifest.targetSdkVersion);
					}
				}
			}
		}

	}

}
