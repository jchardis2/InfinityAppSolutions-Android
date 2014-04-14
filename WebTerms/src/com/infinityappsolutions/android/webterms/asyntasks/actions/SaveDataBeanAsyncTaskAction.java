package com.infinityappsolutions.android.webterms.asyntasks.actions;

import java.io.IOException;

import android.content.Context;

import com.infinityappsolutions.android.lib.asyntasks.actions.AbstractAsyncTaskAction;
import com.infinityappsolutions.android.lib.interfaces.ISaveDataBean;
import com.infinityappsolutions.android.webterms.actions.DataBeanAction;
import com.infinityappsolutions.lib.beans.DataBean;
import com.infinityappsolutions.lib.webterms.bean.Term;

public class SaveDataBeanAsyncTaskAction extends
		AbstractAsyncTaskAction<Void, Void, Boolean> {
	private ISaveDataBean<Term> iDataBean;
	private Context context;
	private DataBean<Term> db;

	public SaveDataBeanAsyncTaskAction(DataBean<Term> db,
			ISaveDataBean<Term> iDataBean, Context context) {
		this.db = db;
		this.iDataBean = iDataBean;
		this.context = context;
	}

	@Override
	protected Boolean doInBackground(Void... params) {
		DataBeanAction action = new DataBeanAction();
		try {
			action.saveDataBean(context, db);
			return true;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;

	}

	@Override
	protected void onPostExecute(Boolean isSaved) {
		super.onPostExecute(isSaved);
		iDataBean.saveDataBeanListener(isSaved, db);
	}

}
