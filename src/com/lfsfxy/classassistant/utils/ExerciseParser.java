package com.lfsfxy.classassistant.utils;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.lfsfxy.classassistant.bean.ExerciseBean;


public class ExerciseParser {
	public static List<ExerciseBean> getExercisesFormJson(InputStream is) throws IOException{
		byte[] buffer = new byte[is.available()];
		is.read(buffer);
		String json = new String(buffer,"utf-8");
		Gson gson = new Gson();
		Type listType = new TypeToken<List<ExerciseBean>>(){}.getType();
		List<ExerciseBean> exerciseInfos = gson.fromJson(json,listType);
		return exerciseInfos;
		
	}
}
