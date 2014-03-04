package com.infinityappsolutions.android.lib.web;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.CookieHandler;
import java.net.CookieManager;
import java.net.HttpURLConnection;
import java.util.List;

import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;

public class IASWebConnection {
	public static String LOGIN_URL = "http://home.infinityappsolutions.com:8080/terms/mobile/login";
	public static String HOME_URL = "http://home.infinityappsolutions.com:8080/terms/user/mobile/home";
	public static String GET_TERMS_URL = "http://home.infinityappsolutions.com:8080/terms/user/mobile/servlet/getTerms";
	public HttpURLConnection con;

	private String cookies;
	private HttpClient client;
	private static IASWebConnection iasWebConnection;
	private boolean isDefault = false;

	/**
	 * A web connection that allows you to post content to websites and retrieve
	 * information about that website. If you are not using the default
	 * instance, you will need to call {@link #connect()}
	 */
	public IASWebConnection() {
	}

	private IASWebConnection(boolean isDefault) {
		this.isDefault = true;
	}

	/**
	 * Gets you the default instance of {@link IASWebConnection} You do not need
	 * to call {@link #connect()}
	 */
	public static IASWebConnection getDefaultInstance() {
		if (iasWebConnection == null) {
			iasWebConnection = new IASWebConnection(true);
			iasWebConnection.connect();
		}
		return iasWebConnection;
	}

	public void connect() {
		// client = HttpClientBuilder.create().build();

		// make sure cookies is turn on
		CookieHandler.setDefault(new CookieManager());

		client = new DefaultHttpClient();
	}

	public HttpResponse sendPostForResponse(String url,
			List<NameValuePair> postParams) throws ClientProtocolException,
			IOException {

		HttpPost post = new HttpPost(url);
		// add header
		post.setHeader("Host", "infinityappsoltuions.com");
		post.setHeader("Accept",
				"text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
		post.setHeader("Accept-Language", "en-US,en;q=0.5");
		post.setHeader("Cookie", getCookies());
		post.setHeader("Connection", "keep-alive");
		// post.setHeader("Referer",
		// "https://accounts.google.com/ServiceLoginAuth");
		post.setHeader("Content-Type", "application/x-www-form-urlencoded");

		post.setEntity(new UrlEncodedFormEntity(postParams));

		HttpResponse response = client.execute(post);
		return response;
	}

	public String sendPost(String url, List<NameValuePair> postParams)
			throws Exception {

		HttpResponse response = sendPostForResponse(url, postParams);

		BufferedReader rd = new BufferedReader(new InputStreamReader(response
				.getEntity().getContent()));

		StringBuffer result = new StringBuffer();
		String line = "";
		while ((line = rd.readLine()) != null) {
			result.append(line + "\n");
		}
		return line;
	}

	public String getResponsePageContents(HttpResponse response)
			throws IllegalStateException, IOException {
		BufferedReader rd = new BufferedReader(new InputStreamReader(response
				.getEntity().getContent()));

		StringBuffer result = new StringBuffer();
		String line = "";
		while ((line = rd.readLine()) != null) {
			result.append(line + "\n");
		}
		return result.toString();
	}

	public String getResponseLocation(HttpResponse response) {
		Header lastHeader = response.getLastHeader("Location");
		if (lastHeader != null) {
			return response.getLastHeader("Location").getValue();
		}
		return null;
	}

	public int getResponseStatusCode(HttpResponse response) {
		return response.getStatusLine().getStatusCode();
	}

	public String getPageContent(String url) throws Exception {

		HttpGet request = new HttpGet(url);

		request.setHeader("Accept",
				"text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
		request.setHeader("Accept-Language", "en-US,en;q=0.5");

		HttpResponse response = client.execute(request);
		int responseCode = response.getStatusLine().getStatusCode();

		System.out.println("\nSending 'GET' request to URL : " + url);
		System.out.println("Response Code : " + responseCode);

		BufferedReader rd = new BufferedReader(new InputStreamReader(response
				.getEntity().getContent()));

		StringBuffer result = new StringBuffer();
		String line = "";
		while ((line = rd.readLine()) != null) {
			result.append(line + "\n");
		}

		// set cookies
		setCookies(response.getFirstHeader("Set-Cookie") == null ? ""
				: response.getFirstHeader("Set-Cookie").toString());

		return result.toString();
	}

	public String getCookies() {
		return cookies;
	}

	public void setCookies(String cookies) {
		this.cookies = cookies;
	}

}
