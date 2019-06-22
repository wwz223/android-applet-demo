package com.lfsfxy.classassistant.activities;

import com.lfsfxy.classassistant.R;
import com.lfsfxy.classassistant.utils.MD5Utils;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends Activity {
	private SharedPreferences sp;
	private TextView tv_title,tv_back,tv_register,tv_psw;
	private Button btn_login;
	private EditText et_user,et_psw;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		sp=getSharedPreferences("loginInfo", MODE_PRIVATE);
		init();
	}

	private void init() {
		// TODO Auto-generated method stub
		tv_title=(TextView) findViewById(R.id.tv_title_main);
		tv_title.setText("登录");
		tv_back=(TextView) findViewById(R.id.tv_title_back);
		tv_register=(TextView) findViewById(R.id.tv_login_register);
		tv_psw=(TextView) findViewById(R.id.tv_login_findpsw);
		btn_login=(Button) findViewById(R.id.btn_login_login);
		et_user=(EditText) findViewById(R.id.et_login_username);
		et_psw=(EditText) findViewById(R.id.et_login_psw);
		tv_register.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent=new Intent(LoginActivity.this,RegisterActivity.class);
				startActivityForResult(intent, 1);
			}
		});
		tv_back.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				LoginActivity.this.finish();
			}
		});
       tv_psw.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent=new Intent(LoginActivity.this,FindPswActivity.class);
				startActivity(intent);
			}
		});
       btn_login.setOnClickListener(new OnClickListener() {
			private String username;
			private String psw;
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				username=et_user.getText().toString();
				psw=et_psw.getText().toString();
				String md5Psw=MD5Utils.md5(psw);
				String spPsw=sp.getString(username, "");
				if(TextUtils.isEmpty(username))
				{
					Toast.makeText(LoginActivity.this,"请输入用户名",0).show();
					return;
				}
				if(TextUtils.isEmpty(psw))
				{
					Toast.makeText(LoginActivity.this,"请输入密码",0).show();
					return;
				}
				if(md5Psw.equals(spPsw))
				{
					Toast.makeText(LoginActivity.this,"登录成功",0).show();
					saveLoginStatus(true,username);
					Intent intent=new Intent(LoginActivity.this,MainActivity.class);
					startActivity(intent);
					LoginActivity.this.finish();
				}else if(!TextUtils.isEmpty(spPsw) && !md5Psw.equals(spPsw))
				{
					Toast.makeText(LoginActivity.this,"请输入的用户名和密码不一致",0).show();
					return;
				}else{
					Toast.makeText(LoginActivity.this,"用户名不存在",0).show();
				}
				
			}
		});
       
       
	}
	protected void saveLoginStatus(boolean status,String username)
	{
		Editor edit=sp.edit();
		edit.putBoolean("isLogin", status);
		edit.putString("loginUsername",username);
		edit.commit();
	}
	@Override
	protected void onActivityResult(int requestCode,int resultCode,Intent data)
	{
		super.onActivityResult(requestCode, resultCode, data);
		if(data!=null)
		{
			String username=data.getStringExtra("username");
			if(!TextUtils.isEmpty(username))
			{
				et_user.setText(username);
				et_user.setSelection(username.length());
			}
		}
	}
}
