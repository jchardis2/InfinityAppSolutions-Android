package com.infinityappsolutions.android.lib.actions;

import java.io.IOException;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;

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
	protected void onPostExecute(HttpResponse response) {
		super.onPostExecute(response);
		try {
			IASWebConnection iasWebConnection = IASWebConnection
					.getDefaultInstance();
			String currentURL = iasWebConnection.getResponseLocation(response);
			if (!currentURL.contains(HOME_URL)) {
				String webPageString = iasWebConnection
						.getResponseString(response);
				iLogin.loginSuccess(webPageString);
			}
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		iLogin.loginFailed();
	}

	@Override
	protected void onCancelled() {
	}
}
