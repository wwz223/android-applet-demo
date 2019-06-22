package com.lfsfxy.classassistant.activities;

import com.lfsfxy.classassistant.R;
import com.lfsfxy.classassistant.utils.MD5Utils;

import android.R.color;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class RegisterActivity extends Activity {
	private SharedPreferences sp;
	private TextView tv_title,tv_back;
	private EditText et_name,et_psw,et_repsw;
	private Button btn_save;
	private RelativeLayout rl_titleber;
	private String psw;
	private String repsw;
	private String username;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_register);
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		sp=getSharedPreferences("loginInfo", MODE_PRIVATE);
		init();
	}
	private void init() {
		// TODO Auto-generated method stub
		tv_title=(TextView) findViewById(R.id.tv_title_main);
		tv_title.setText("注册");
		tv_back=(TextView) findViewById(R.id.tv_title_back);
		rl_titleber=(RelativeLayout) findViewById(R.id.title_bar);
		rl_titleber.setBackgroundColor(Color.TRANSPARENT);
		btn_save=(Button) findViewById(R.id.btn_register_save);
		et_name=(EditText) findViewById(R.id.et_register_username);
		et_psw=(EditText) findViewById(R.id.et_register_psw);
		et_repsw=(EditText) findViewById(R.id.et_register_repsw);
		tv_back.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				RegisterActivity.this.finish();
			}
		});
		btn_save.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				getEditString();
				if(TextUtils.isEmpty(username))
				{
					Toast.makeText(RegisterActivity.this,"请输入用户名",0).show();
					return;
				}
				if(TextUtils.isEmpty(psw))
				{
					Toast.makeText(RegisterActivity.this,"请输入密码",0).show();
					return;
				}
				if(TextUtils.isEmpty(repsw))
				{
					Toast.makeText(RegisterActivity.this,"请再次输入密码",0).show();
					return;
				}
				if(!psw.equals(repsw))
				{
					Toast.makeText(RegisterActivity.this,"密码不一致",0).show();
					return;
				}
				if(isExistUsername(username))
				{
					Toast.makeText(RegisterActivity.this,"此账号已存在",0).show();
					return;
				}
				saveRegisterInfo(username,psw);
				Toast.makeText(RegisterActivity.this,"注册成功", 0).show();
				Intent data=new Intent();
				data.putExtra("username",username);
				setResult(RESULT_OK, data);
				RegisterActivity.this.finish();
				
			}
		});
		
	}
	protected void saveRegisterInfo(String username2,String psw2){
		String md5Psw=MD5Utils.md5(psw);
		Editor edit=sp.edit();
		edit.putString(username, md5Psw);
		edit.commit();
	}
	protected boolean isExistUsername(String username){
		boolean has_username=false;
		String spPsw=sp.getString(username, "");
		if(!TextUtils.isEmpty(spPsw)){
			has_username=true;
		}
		return has_username;
	}
	protected void getEditString()
	{
		username=et_name.getText().toString();
		psw=et_psw.getText().toString();
		repsw=et_repsw.getText().toString();
		
	}
	
}
