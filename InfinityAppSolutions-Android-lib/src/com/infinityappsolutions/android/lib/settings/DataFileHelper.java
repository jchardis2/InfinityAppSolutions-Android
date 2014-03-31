package com.infinityappsolutions.android.lib.settings;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Type;

import android.content.Context;

import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.infinityappsolutions.lib.beans.DataBean;
import com.infinityappsolutions.lib.gson.IASGson;

public class DataFileHelper<T> {
	public static final String FILE_NAME_USER = "user.conf";

	public DataFileHelper() {

	}

	public void saveDataBean(Context context, DataBean db) throws IOException {
		IASGson<DataBean<T>> iasGson = new IASGson<DataBean<T>>();
		String dataBeanJson = iasGson.toGson(db);
		FileOutputStream fos = context.openFileOutput(FILE_NAME_USER,
				Context.MODE_PRIVATE);
		fos.write(dataBeanJson.getBytes());
		fos.close();
	}

	public DataBean<T> loadDataBean(Context context) throws IOException,
			JsonSyntaxException {
		FileInputStream fileInputStream = context.openFileInput(FILE_NAME_USER);
		BufferedReader bufferedReader = new BufferedReader(
				new InputStreamReader(fileInputStream));
		JsonReader jsonReader = new JsonReader(bufferedReader);
		IASGson<DataBean<T>> iasGson = new IASGson<DataBean<T>>();
		Type userBeanTypeToken = new TypeToken<DataBean<T>>() {
		}.getType();
		return iasGson.fromGsonReader(jsonReader, userBeanTypeToken);
	}
}
