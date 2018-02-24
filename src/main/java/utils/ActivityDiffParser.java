package utils;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import android.data.Activity;

public class ActivityDiffParser {
	
	public static List<Activity> parseActivityList(String activitiesStr){
		List<Activity> activities = new ArrayList<>();
        try {
        	XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
        	XmlPullParser parser = factory.newPullParser();
			if (activitiesStr != null && !activitiesStr.isEmpty()) {
				parser.setInput(new StringReader(activitiesStr));
				parser.nextTag();
				activities = readActivityList(parser);
			}
		} catch (XmlPullParserException | IOException e) {
			System.out.println("Could not parse activity List : " + e.getMessage());
		}
		return activities;
	}
	
	private static List<Activity> readActivityList(XmlPullParser parser) throws XmlPullParserException, IOException{
		List<Activity> activities = new ArrayList<>();
		while(parser.next() != XmlPullParser.END_DOCUMENT) {
			if(parser.getEventType() == XmlPullParser.START_TAG && parser.getName().equals(AndroidManifestParser.ACTIVITY_TAG)){
				activities.add(parseActivity(parser));
			}
		}
		return activities;
	}
	
	private static Activity parseActivity(XmlPullParser parser) {
		String s;
		Activity activity = new Activity();
		activity.label = parser.getAttributeValue(null, "android:label");
		activity.name = parser.getAttributeValue(null, "android:name");

		activity.permission = parser.getAttributeValue(null,
				"android:permission");
		activity.process = parser.getAttributeValue(null, "android:process");
		activity.enabled = !("false").equals(parser.getAttributeValue(null,
				"android:enabled"));
		activity.exported = ("true").equals(parser.getAttributeValue(null,
				"android:exported"));

		activity.allowEmbedded = ("true").equals(parser.getAttributeValue(null,
				"android:allowEmbedded"));

		activity.allowTaskReparenting = ("true").equals(parser
				.getAttributeValue(null, "android:allowTaskReparenting"));

		activity.alwaysRetainTaskState = ("true").equals(parser
				.getAttributeValue(null, "android:alwaysRetainTaskState"));
		activity.autoRemoveFromRecents = ("true").equals(parser
				.getAttributeValue(null, "android:autoRemoveFromRecents"));
		activity.banner = parser.getAttributeValue(null, "android:banner");
		activity.clearTaskOnLaunch = ("true").equals(parser.getAttributeValue(
				null, "android:clearTaskOnLaunch"));
		activity.configChanges = parser.getAttributeValue(null,
				"android:configChanges");
		activity.documentLaunchMode = parser.getAttributeValue(null,
				"android:documentLaunchMode");
		activity.excludeFromRecents = ("true").equals(parser.getAttributeValue(
				null, "android:excludeFromRecents"));
		activity.finishOnTaskLaunch = ("true").equals(parser.getAttributeValue(
				null, "android:finishOnTaskLaunch"));
		activity.hardwareAccelerated = ("true").equals(parser
				.getAttributeValue(null, "android:hardwareAccelerated"));
		activity.launchMode = parser.getAttributeValue(null,
				"android:launchMode");
		s = parser.getAttributeValue(null, "android:maxRecents");
		activity.maxRecents = (s == null) ? activity.maxRecents : Integer
				.parseInt(s);
		activity.multiprocess = ("true").equals(parser.getAttributeValue(null,
				"android:multiprocess"));
		activity.noHistory = ("true").equals(parser.getAttributeValue(null,
				"android:noHistory"));
		activity.parentActivityName = parser.getAttributeValue(null,
				"android:parentActivityName");
		activity.relinquishTaskIdentity = ("true").equals(parser
				.getAttributeValue(null, "android:relinquishTaskIdentify"));
		activity.screenOrientation = parser.getAttributeValue(null,
				"android:screenOrientation");
		activity.stateNotNeeded = ("true").equals(parser.getAttributeValue(
				null, "android:stateNotNeeded"));
		activity.taskAffinity = parser.getAttributeValue(null,
				"android:taskAffinity");
		activity.theme = parser.getAttributeValue(null, "android:theme");
		activity.uiOptions = parser
				.getAttributeValue(null, "android:uiOptions");
		activity.windowSoftInputMode = parser.getAttributeValue(null,
				"android:windowSoftInputMode");
		return activity;
	}
}
