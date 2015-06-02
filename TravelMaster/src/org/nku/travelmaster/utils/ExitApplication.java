package org.nku.travelmaster.utils;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.app.Application;

public class ExitApplication extends Application {
	private List<Activity> list = new ArrayList<Activity>();

	private static ExitApplication exitApplication;

	private ExitApplication() {

	}

	public static ExitApplication getInstance() {
		if (null == exitApplication) {
			exitApplication = new ExitApplication();
		}
		return exitApplication;
	}

	public void addActivity(Activity activity) {
		list.add(activity);
	}

	public void exit() {
		for (Activity activity : list) {
			try {
				activity.finish();
			} catch (Exception e) {
				// TODO: handle exception
			}

		}
		System.exit(0);
	}
}
