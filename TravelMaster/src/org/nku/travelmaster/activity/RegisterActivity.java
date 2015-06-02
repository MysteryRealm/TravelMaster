package org.nku.travelmaster.activity;

import org.nku.travelmaster.utils.ExitApplication;

import android.app.Activity;
import android.os.Bundle;

public class RegisterActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		ExitApplication.getInstance().addActivity(this);
		this.setContentView(R.layout.activity_register);
	}

}
