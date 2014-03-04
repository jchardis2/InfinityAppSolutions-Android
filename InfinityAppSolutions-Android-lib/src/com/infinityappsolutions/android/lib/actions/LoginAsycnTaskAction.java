package com.infinityappsolutions.android.lib.actions;

import java.io.IOException;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;

import android.util.Log;

import com.infinityappsolutions.android.lib.interfaces.ILoginTask;
import com.infinityappsolutions.android.lib.web.IASWebConnection;

public class LoginAsycnTaskAction extends
		WebConnectionAsyncTask<Void, Void, HttpResponse> {
	public static String LOGIN_URL = "http://home.infinityappsolutions.com:8080/terms/mobile/login";
	public static String HOME_URL = "http://home.infinityappsolutions.com:8080/terms/user/mobile/home";
	public static String GET_TERMS_URL = "http://home.infinityappsolutions.com:8080/terms/user/mobile/servlet/getTerms";

	public ILoginTask iLogin;

	public LoginAsycnTaskAction(List<NameValuePair> postParams,
			ILoginTask iLogin) {
		super(LOGIN_URL, postParams);
		this.iLogin = iLogin;
	}

	@Override
	protected HttpResponse doInBackground(Void... params) {
		IASWebConnection connection = IASWebConnection.getDefaultInstance();
		HttpResponse homeResponse = null;
		try {
			HttpResponse response = super.doInBackground(params);
			if (response != null) {
				homeResponse = connection.sendPostForResponse(HOME_URL,
						super.postParams);
				return homeResponse;
			}
		} catch (Exception e) {
			Log.e(this.getClass().getName(), e.getMessage());
		}
		return homeResponse;
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
				if (currentURL != null && !currentURL.contains(HOME_URL)) {
					String webPageString = iasWebConnection
							.getResponsePageContents(response);
					iLogin.loginSuccess(webPageString);
					return;
				}
			} catch (IllegalStateException e) {
				Log.e(this.getClass().getName(), e.getMessage());
			} catch (IOException e) {
				Log.e(this.getClass().getName(), e.getMessage());
			} catch (Exception e) {
				Log.e(this.getClass().getName(), e.getMessage());
			}
		}
		iLogin.loginFailed();
	}

	@Override
	protected void onCancelled() {
		iLogin.onCancelled();
	}
}
