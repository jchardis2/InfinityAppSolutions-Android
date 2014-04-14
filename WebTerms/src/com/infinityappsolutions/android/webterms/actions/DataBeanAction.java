package com.infinityappsolutions.android.webterms.actions;

import java.io.IOException;

import android.content.Context;

import com.google.gson.JsonSyntaxException;
import com.infinityappsolutions.android.lib.actions.AbstractAction;
import com.infinityappsolutions.android.lib.settings.DataFileHelper;
import com.infinityappsolutions.lib.beans.DataBean;
import com.infinityappsolutions.lib.webterms.bean.Term;

public class DataBeanAction extends AbstractAction {

	public DataBeanAction() {
	}

	public boolean saveDataBean(Context context, DataBean<Term> db)
			throws IOException {
		DataFileHelper<Term> dataFileHelper = new DataFileHelper<Term>();
		dataFileHelper.saveDataBean(context, db);
		return true;
	}

	public DataBean<Term> loadDataBean(Context context) throws IOException,
			JsonSyntaxException {
		DataFileHelper<Term> dataFileHelper = new DataFileHelper<Term>();
		return dataFileHelper.loadDataBean(context);
	}

}
