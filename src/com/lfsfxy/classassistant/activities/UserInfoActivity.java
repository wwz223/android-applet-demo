package com.lfsfxy.classassistant.activities;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.lfsfxy.classassistant.R;
import com.lfsfxy.classassistant.Dao.UserDao;
import com.lfsfxy.classassistant.bean.UserBean;

public class UserInfoActivity extends Activity implements OnClickListener{
	private static final int CHANGE_NICKNAME = 1;
	private static final int CHANGE_SIGNATURE = 2;
	SharedPreferences sp;
	private String spUserName;
	private View tv_back;
	private View rl_nickName;
	private View rl_sex;
	private View rl_signature;
	private RelativeLayout rl_username;
	private TextView tv_signature;
	private TextView tv_sex;
	private TextView tv_nickName;
	private TextView tv_username;
	private TextView tv_set;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_userinfo);
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		//获取登陆信息
		sp = getSharedPreferences("loginInfo",MODE_PRIVATE);
		spUserName = sp.getString("loginUsername","");
		init();
		initData();
		setListener();
	}

	private void setListener() {
		// TODO Auto-generated method stub
		tv_back.setOnClickListener(this);
		rl_nickName.setOnClickListener(this);
		rl_sex.setOnClickListener(this);
		rl_signature.setOnClickListener(this);
	}

	private void initData() {
		// TODO Auto-generated method stub
		UserBean userBean = null;
		UserDao userDao = new UserDao(this);
		userBean = userDao.getUserInfo(spUserName);
		if(userBean==null){
			userBean = new UserBean();
			userBean.userName = spUserName;
			userBean.nickName = "小助手";
			userBean.sex = "女";
			userBean.signature = "小助手";
			userDao.saveUserInfo(userBean);
		}
		setValue(userBean);
	}
	/**
	 * 显示信息
	 */
	public void setValue(UserBean userBean){
		tv_username.setText(userBean.userName);
		tv_nickName.setText(userBean.nickName);
		tv_sex.setText(userBean.sex);
		tv_signature.setText(userBean.signature);
	}
	
	private void init() {
		// TODO Auto-generated method stub
		TextView tv_title = (TextView) findViewById(R.id.tv_title_main);
		tv_title.setText("个人资料");
		tv_back = (TextView) findViewById(R.id.tv_title_back);
		RelativeLayout rl_titlebar = (RelativeLayout) findViewById(R.id.title_bar);
		rl_titlebar.setBackgroundColor(Color.parseColor("#30b4ff"));
		rl_username = (RelativeLayout) findViewById(R.id.rl_user_name);
		rl_nickName = (RelativeLayout) findViewById(R.id.rl_user_nickName);
		rl_sex = (RelativeLayout) findViewById(R.id.rl_user_sex);
		rl_signature = (RelativeLayout) findViewById(R.id.rl_user_signature);
		tv_username = (TextView) findViewById(R.id.tv_user_name);
		tv_nickName = (TextView) findViewById(R.id.tv_user_nickName);
		tv_sex = (TextView) findViewById(R.id.tv_user_sex);
		tv_signature = (TextView) findViewById(R.id.tv_user_signature);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch(v.getId()){
		case R.id.tv_title_back:
			this.finish();
			break;
		case R.id.rl_user_nickName:
			String name = tv_nickName.getText().toString();
			Bundle bdName = new Bundle();
			bdName.putString("content",name);
			bdName.putString("title", "昵称");
			bdName.putInt("flag",1);
			enterActivityForResult(ChangeUserInfoActivity.class,CHANGE_NICKNAME,bdName);
			break;
		case R.id.rl_user_signature:
			String signature = tv_signature.getText().toString();
			Bundle bdSignature = new Bundle();
			bdSignature.putString("content",signature);
			bdSignature.putString("title","签名");
			bdSignature.putInt("flag",2);
			enterActivityForResult(ChangeUserInfoActivity.class,CHANGE_SIGNATURE,bdSignature);
			break;
		case R.id.rl_user_sex:
			String sex = tv_sex.getText().toString();
			setDialog(sex);
			break;
		default:
			break;
		}
	}


	/**
	 * 设置性别弹出框
	 */
	private void setDialog(String sex){
		int sexFlag = 0;
		if("男".equals(sex)){
			sexFlag = 0;
		}else if("女".equals(sex)){
			sexFlag = 1;
		}
		final String items[] = {"男","女"};
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setTitle("性别");
		builder.setSingleChoiceItems(items,sexFlag,new DialogInterface.OnClickListener(){
			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				dialog.dismiss();
				Toast.makeText(UserInfoActivity.this,items[which], 0).show();
				setSex(items[which]);
			}
		});
		builder.show();
	}
	/**
	 * 更新界面上的性别数据
	 */
	protected void setSex(String sex){
		tv_sex.setText(sex);
		UserDao userDao = new UserDao(this);
		userDao.updateUserInfo("sex",sex,spUserName);
	}
	/**
	 * 处理回传数据
	 */
	private String newInfo;
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		UserDao userDao = new UserDao(this);
		switch(requestCode){
		case CHANGE_NICKNAME:
			if(data!=null){
				newInfo = data.getStringExtra("nickName");
				if(TextUtils.isEmpty(newInfo)){
					return;
				}
				tv_nickName.setText(newInfo);
				userDao.updateUserInfo("nickName", newInfo, spUserName);
				
			}
			break;
		case CHANGE_SIGNATURE:
			if(data!=null){
				newInfo = data.getStringExtra("signature");
				if(TextUtils.isEmpty(newInfo)){
					return;
				}
				tv_signature.setText(newInfo);
				userDao.updateUserInfo("signature", newInfo, spUserName);
			}
			break;
		}
	}
	/**
	 * 跳转到修改界面
	 * @param to
	 * @param requestCode
	 * @param b
	 */
	private void enterActivityForResult(Class<?> to,
			int requestCode, Bundle b) {
		// TODO Auto-generated method stub
		Intent i = new Intent(this,to);
		i.putExtras(b);
		startActivityForResult(i,requestCode);	
	}

}
