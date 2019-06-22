package com.lfsfxy.classassistant.activities;

import java.util.Timer;
import java.util.TimerTask;

import com.lfsfxy.classassistant.R;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Bundle;
import android.widget.TextView;

public class SplashActivity extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_splash);
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		init();
	}

	private void init() {
		// TODO Auto-generated method stub
		TextView tv_version=(TextView) findViewById(R.id.tv_splash_version);
		PackageManager pm=getPackageManager();
		try {
			PackageInfo info=pm.getPackageInfo(getPackageName(), 0);
			tv_version.setText("°æ±¾ºÅ"+info.versionName);
		} catch (NameNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			tv_version.setText("°æ±¾ºÅ");
		}
		Timer timer=new Timer();
		TimerTask task=new TimerTask() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				Intent intent= new Intent(SplashActivity.this,LoginActivity.class);
				startActivity(intent);
				SplashActivity.this.finish();
			}
		};
		timer.schedule(task, 3000);
	}
}
