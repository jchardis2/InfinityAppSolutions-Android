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
import com.infinityappsolutions.lib.gson.IASGson;

public class DataFileHelper {
	public static final String FILE_NAME_USER = "User.conf";
	public DataBean dataBean;

	public DataFileHelper() {

	}

	public void saveDataBean(Context context, DataBean db) throws IOException {
		IASGson<DataBean> iasGson = new IASGson<DataBean>();
		String dataBeanJson = iasGson.toGson(dataBean);
		FileOutputStream fos = context.openFileOutput(FILE_NAME_USER,
				Context.MODE_PRIVATE);
		fos.write(dataBeanJson.getBytes());
		fos.close();
	}

	public DataBean getDataBean(Context context) throws IOException,
			JsonSyntaxException {
		FileInputStream fileInputStream = context.openFileInput(FILE_NAME_USER);
		BufferedReader bufferedReader = new BufferedReader(
				new InputStreamReader(fileInputStream));
		JsonReader jsonReader = new JsonReader(bufferedReader);
		IASGson<DataBean> iasGson = new IASGson<DataBean>();
		Type userBeanTypeToken = new TypeToken<DataBean>() {
		}.getType();
		return iasGson.fromGsonReader(jsonReader, userBeanTypeToken);
	}
}
