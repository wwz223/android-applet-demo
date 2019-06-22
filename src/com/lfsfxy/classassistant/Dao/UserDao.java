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
	 * 保存个人信息
	 * 添加一条数据
	 */
	public boolean saveUserInfo(UserBean user){
		boolean flag = false;
		db = dbHelper.getWritableDatabase();//获得一个可写入的数据库
		ContentValues values = new ContentValues();
		values.put("userName",user.userName);
		values.put("nickName",user.nickName);
		values.put("sex",user.sex);
		values.put("signature",user.signature);
		long rowid = db.insert("userinfo", null, values);
		if(rowid!=-1){
			flag = true;
		}
		//关闭数据库
		db.close();
		return flag;
	}
	/**
	 * 修改用户个人信息
	 * @param key  所要修改字段
	 * @param value  所要修改字段值
	 * @param userName 用户名
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








