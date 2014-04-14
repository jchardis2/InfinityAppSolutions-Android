package com.infinityappsolutions.android.webterms.factory;

import java.lang.reflect.Type;

import com.google.gson.reflect.TypeToken;
import com.infinityappsolutions.lib.beans.DataBean;
import com.infinityappsolutions.lib.gson.IASGson;
import com.infinityappsolutions.lib.webterms.bean.Term;

public class DataBeanFactory {

	public DataBean<Term> getDataBean(String content) {

		IASGson<DataBean<Term>> iasGson = new IASGson<DataBean<Term>>();
		Type userBeanTypeToken = new TypeToken<DataBean<Term>>() {
		}.getType();
		DataBean<Term> db = iasGson.fromGson(content, userBeanTypeToken);
		return db;
	}
}
