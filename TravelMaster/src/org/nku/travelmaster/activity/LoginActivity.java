package org.nku.travelmaster.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;

public class LoginActivity extends Activity {

	private EditText txtAccount, txtPassword;
	private Button btnRegister, btnLogin;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		this.setContentView(R.layout.activity_login);

		this.txtAccount = (EditText) this.findViewById(R.id.txtAccount);
		this.txtPassword = (EditText) this.findViewById(R.id.txtPassword);

		this.btnRegister = (Button) this.findViewById(R.id.btnRegister);
		this.btnLogin = (Button) this.findViewById(R.id.btnLogin);

		this.btnRegister.setOnClickListener(new ViewOcl());
		this.btnLogin.setOnClickListener(new ViewOcl());
	}

	private class ViewOcl implements View.OnClickListener {

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			switch (v.getId()) {
			case R.id.btnRegister:
				Intent intent = new Intent();
				intent.setClass(LoginActivity.this,RegisterActivity.class );
				startActivity(intent);
				break;
			case R.id.btnLogin:
				String account = txtAccount.getText().toString().trim();
				String password = txtPassword.getText().toString().trim();
				break;
			default:
				break;
			}
		}

	}
}
