package com.ws.mobileline;

import com.ws.mobileline.utils.Logs;

import android.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
/**
 * 登陆界面
 * @author ycf
 *
 */
public class LoginActivity extends Activity {

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		
		ActionBar ab = getActionBar();
		ab.show();
		//back
//		View view = getWindow().getDecorView();	
//		for (int i = 0; i < 3; i++)
//			view = ((ViewGroup) view).getChildAt(0);
		
		//title
		View view = getWindow().getDecorView();	
		for (int i = 0; i < 4; i++)
			view = ((ViewGroup) view).getChildAt(0);
		final View v = view;
		view.post(new Runnable() {
			public void run() {
				v.setPadding(getResources().getDisplayMetrics().widthPixels/2-v.getWidth()/2-v.getLeft(), 0, 0, 0);
			}
		});

		
		Button login = (Button) findViewById(R.id.login);
		login.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				startActivity(new Intent(getApplicationContext(), MainActivity.class));
				finish();
			}
		});
	}
}
