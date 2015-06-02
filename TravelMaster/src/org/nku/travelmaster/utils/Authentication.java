package org.nku.travelmaster.utils;

import android.app.Activity;
import android.content.Intent;

public class Authentication {
	public static int getLoginUserId(Activity activity){
		Intent intent=activity.getIntent();
		String userIdStr =intent.getStringExtra("userId");
		if(userIdStr==null){
			//Intent intentLogin = new Intent();
			//intentLogin.setClass(activity, LoginActivity.class);
			//activity.startActivity(intentLogin);
			return -1;
		}
		
		return Integer.valueOf(userIdStr);
	}
}
