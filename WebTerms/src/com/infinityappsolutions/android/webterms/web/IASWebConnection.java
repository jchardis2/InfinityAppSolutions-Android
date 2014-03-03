package com.infinityappsolutions.android.webterms.web;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.CookieHandler;
import java.net.CookieManager;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;

public class IASWebConnection {
	public static String LOGIN_URL = "http://home.infinityappsolutions.com:8080/terms/mobile/login";
	public static String HOME_URL = "http://home.infinityappsolutions.com:8080/terms/user/mobile/home";
	public static String GET_TERMS_URL = "http://home.infinityappsolutions.com:8080/terms/user/mobile/servlet/getTerms";

	private String cookies;
	private HttpClient client;

	public IASWebConnection() {
	}

	public void connect() throws IOException {
		// client = HttpClientBuilder.create().build();
		// make sure cookies is turn on
		CookieHandler.setDefault(new CookieManager());
		client = new DefaultHttpClient();
	}

	public HttpResponse sendPostForResponse(String url,
			List<NameValuePair> postParams) throws Exception {

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
		BufferedReader rd = new BufferedReader(new InputStreamReader(response
				.getEntity().getContent()));

		StringBuffer result = new StringBuffer();
		String line = "";
		while ((line = rd.readLine()) != null) {
			result.append(line + "\n");
		}

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

	public String getResponseString(HttpResponse response)
			throws IllegalStateException, IOException {
		BufferedReader rd = new BufferedReader(new InputStreamReader(response
				.getEntity().getContent()));

		StringBuffer result = new StringBuffer();
		String line = "";
		while ((line = rd.readLine()) != null) {
			result.append(line + "\n");
		}
		return line;
	}

	public String getResponseLocation(HttpResponse response) {
		return response.getLastHeader("Location").getValue();
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
