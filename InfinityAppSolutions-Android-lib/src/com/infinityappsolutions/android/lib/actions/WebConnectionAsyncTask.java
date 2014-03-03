package com.infinityappsolutions.android.lib.actions;

import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;

import com.infinityappsolutions.android.lib.web.IASWebConnection;

public class WebConnectionAsyncTask<Params, Progress, Result> extends
		AbstractAsyncTaskAction<Void, Progress, HttpResponse> {
	private String url;
	private List<NameValuePair> postParams;

	public WebConnectionAsyncTask(String url, List<NameValuePair> postParams) {
		this.url = url;
		this.postParams = postParams;
	}

	@Override
	protected HttpResponse doInBackground(Void... params) {
		IASWebConnection connection = IASWebConnection.getDefaultInstance();
		try {
			HttpResponse response = connection.sendPostForResponse(url,
					postParams);
			return response;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	protected void onPostExecute(HttpResponse result) {
		super.onPostExecute(result);
	}
}
