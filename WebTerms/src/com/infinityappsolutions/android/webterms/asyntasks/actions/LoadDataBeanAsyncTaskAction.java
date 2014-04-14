package com.infinityappsolutions.android.webterms.asyntasks.actions;

import java.io.IOException;

import android.content.Context;

import com.infinityappsolutions.android.lib.asyntasks.actions.AbstractAsyncTaskAction;
import com.infinityappsolutions.android.lib.interfaces.ILoadDataBean;
import com.infinityappsolutions.android.webterms.actions.DataBeanAction;
import com.infinityappsolutions.lib.beans.DataBean;
import com.infinityappsolutions.lib.webterms.bean.Term;

public class LoadDataBeanAsyncTaskAction extends
		AbstractAsyncTaskAction<Void, Void, DataBean<Term>> {
	private ILoadDataBean<Term> iDataBean;
	private Context context;

	public LoadDataBeanAsyncTaskAction(ILoadDataBean<Term> iDataBean,
			Context context) {
		this.iDataBean = iDataBean;
		this.context = context;
	}

	@Override
	protected DataBean<Term> doInBackground(Void... params) {
		DataBeanAction action = new DataBeanAction();
		try {
			return action.loadDataBean(context);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	protected void onPostExecute(DataBean<Term> db) {
		super.onPostExecute(db);
		if (db != null) {
			iDataBean.loadDataBeanListener(db);
		} else {
			iDataBean.loadFailed();
		}
	}

}
