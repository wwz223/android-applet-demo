package com.lfsfxy.classassistant.Dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.lfsfxy.classassistant.bean.UserBean;
import com.lfsfxy.classassistant.db.DBHelper;

public class UserDao {
	private Context context;
	private DBHelper dbHelper;
	private SQLiteDatabase db;
	
	public UserDao(Context context) {
		super();
		this.context = context;
		dbHelper = new DBHelper(context);
	}
	/**
	 * ���������Ϣ
	 * ���һ������
	 */
	public boolean saveUserInfo(UserBean user){
		boolean flag = false;
		db = dbHelper.getWritableDatabase();//���һ����д������ݿ�
		ContentValues values = new ContentValues();
		values.put("userName",user.userName);
		values.put("nickName",user.nickName);
		values.put("sex",user.sex);
		values.put("signature",user.signature);
		long rowid = db.insert("userinfo", null, values);
		if(rowid!=-1){
			flag = true;
		}
		//�ر����ݿ�
		db.close();
		return flag;
	}
	/**
	 * �޸��û�������Ϣ
	 * @param key  ��Ҫ�޸��ֶ�
	 * @param value  ��Ҫ�޸��ֶ�ֵ
	 * @param userName �û���
	 */
	public boolean updateUserInfo(String key,String value,String userName){
		boolean flag = false;
		db = dbHelper.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put(key, value);
		int num = db.update("userinfo",values,"userName=?",new String[]{userName});
		if(num>0){
			flag = true;
		}
		db.close();
		return flag;
	}
	
	public UserBean getUserInfo(String userName){
		db = dbHelper.getReadableDatabase();
		String sql = "select * from userinfo where userName=?";
		Cursor cursor = db.rawQuery(sql, new String[]{userName});
		UserBean user = null;
		if(cursor.moveToNext()){
			user = new UserBean();
			user.userName = cursor.getString(1);
			user.nickName = cursor.getString(2);
			user.sex = cursor.getString(3);
			user.signature = cursor.getString(4);
		}
		cursor.close();
		db.close();
		return user;
	}
}








