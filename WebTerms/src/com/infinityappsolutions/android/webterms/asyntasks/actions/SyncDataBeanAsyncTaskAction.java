package com.infinityappsolutions.android.webterms.asyntasks.actions;

import java.io.IOException;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;

import android.content.Context;
import android.util.Log;

import com.infinityappsolutions.android.lib.asyntasks.actions.WebConnectionAsyncTask;
import com.infinityappsolutions.android.lib.interfaces.ILoadDataBean;
import com.infinityappsolutions.android.lib.web.IASWebConnection;
import com.infinityappsolutions.android.webterms.factory.DataBeanFactory;
import com.infinityappsolutions.android.webterms.listeners.DataBeanSyncedListener;
import com.infinityappsolutions.lib.beans.DataBean;
import com.infinityappsolutions.lib.webterms.bean.Term;

public class SyncDataBeanAsyncTaskAction extends
		WebConnectionAsyncTask<Void, Void, HttpResponse> {
	public static String GET_TERMS_URL = "http://home.infinityappsolutions.com:8080/terms/user/mobile/servlet/getTerms";
	private ILoadDataBean<Term> iDataBean;
	private Context context;
	private DataBean<Term> db;
	private DataBeanSyncedListener dataBeanSyncedListener;

	public SyncDataBeanAsyncTaskAction(DataBean<Term> db,
			List<NameValuePair> postParams) {
		super(GET_TERMS_URL, postParams);
		this.db = db;
	}

	public void setOnDataBeanSyncedListener(
			DataBeanSyncedListener dbSyncedListener) {
		this.dataBeanSyncedListener = dbSyncedListener;
	}

	@Override
	protected HttpResponse doInBackground(Void... params) {
		HttpResponse response = null;
		try {
			response = super.doInBackground(params);
		} catch (Exception e) {
			Log.e(this.getClass().getName(), e.getMessage());
		}
		return response;
	}

	@Override
	protected void onPostExecute(HttpResponse response) {
		super.onPostExecute(response);
		if (response != null) {
			try {
				IASWebConnection iasWebConnection = IASWebConnection
						.getDefaultInstance();
				String currentURL = iasWebConnection
						.getResponseLocation(response);
				if (currentURL != null && !currentURL.contains(GET_TERMS_URL)) {
					String webPageString = iasWebConnection
							.getResponsePageContents(response);

					DataBeanFactory beanFactory = new DataBeanFactory();
					DataBean<Term> db = beanFactory.getDataBean(webPageString);
					if (dataBeanSyncedListener != null) {
						dataBeanSyncedListener.onDataBeanSynced(db);
					}
					return;
				}
			} catch (IllegalStateException e) {
				Log.e(this.getClass().getName(), e.getMessage());
			} catch (IOException e) {
				Log.e(this.getClass().getName(), e.getMessage());
			} catch (Exception e) {
				Log.e(this.getClass().getName(), e.getMessage());
			}
			dataBeanSyncedListener.onDataBeanSyncFailure();
		}
	}
}
