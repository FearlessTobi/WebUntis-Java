package de.iboticial.untisapi;

import java.util.HashMap;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

public class HttpRequestManager {

	private String sessionId = null;
	private String schoolName = null;
	private String hostname = null;
	private final String baseUrl = "/WebUntis/jsonrpc.do";
	private Credentials credentials = null;
	private boolean isLoggedIn = false;

	public HttpRequestManager() {

	}

	// TODO: School-Name Cookie
	HttpResponseWithEntity httpPOST(String method, HashMap<String, String> paramsMap) {

		if (isLoggedIn || method.equals("authenticate")) {

			HttpResponse result = null;
			String JSON = null;
			String URL = generateUrl();
			String requestBody = preProcessParams(method, paramsMap);

			try (CloseableHttpClient httpClient = HttpClientBuilder.create().build()) {

				HttpPost request = new HttpPost(URL);
				StringEntity requestParams = new StringEntity(requestBody);
				request.addHeader("content-type", "application/json");

				if (sessionId != null && (!method.equals("authenticate")))
					request.addHeader("Cookie", "JSESSIONID=" + sessionId + "; schoolname=" + schoolName);

				request.setEntity(requestParams);
				result = httpClient.execute(request);

				JSON = EntityUtils.toString(result.getEntity(), "UTF-8");

				checkForErrors(JSON);

				if (method.equals("authenticate")) {
					getSessionIdFromJSON(JSON);
					getSchoolNameFromResult(result);
				}

			} catch (Exception e) {
				e.printStackTrace();
			}

			return new HttpResponseWithEntity(result, JSON);

		} else {

			System.err.println("You have to connect before sending requests!");

			return null;

		}

	}

	private void getSessionIdFromJSON(String JSON) {

		JSONObject obj = new JSONObject(JSON);
		try {
			sessionId = obj.getJSONObject("result").getString("sessionId");
			isLoggedIn = true;
			System.out.println("Login successful!");
		} catch (Exception e) {
			isLoggedIn = false;
		}

	}

	private void getSchoolNameFromResult(HttpResponse result) {

		try {
			schoolName = result.getHeaders("Set-Cookie")[1].getValue().split(";")[0].split("\"")[1];
		} catch (Exception e) {
			isLoggedIn = false;
		}

	}

	public boolean checkForErrors(String JSON) {

		JSONObject obj;
		JSONObject error;
		String message = null;
		String code = null;

		try {
			obj = new JSONObject(JSON);
			error = obj.getJSONObject("error");
		} catch (Exception e) {
			return false;
		}

		try {
			message = error.getString("message");
			message = message.substring(0, 1).toUpperCase() + message.substring(1);
		} catch (Exception e) {
		}

		try {
			code = String.valueOf(error.getInt("code"));
		} catch (Exception e) {
		}

		if (message == null && code == null) {
			return false;
		} else {
			System.err.println("There was an error: " + message + ", Code: " + code);
			System.err.println();
			return true;
		}

	}

	public static String preProcessParams(String method, HashMap<String, String> paramsMap) {

		JSONObject paramsJSONObject = new JSONObject(paramsMap);
		return "{\"id\":\"ID\",\"method\":\"" + method + "\",\"jsonrpc\":\"2.0\",\"params\":" + paramsJSONObject + "}";

	}

	public String generateUrl() {
		if (hostname != null && credentials.getSchoolname() != null)
			return hostname + baseUrl + "?school=" + credentials.getSchoolname();
		else
			return null;
	}

	public String getSessionId() {
		return sessionId;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}

	public String getHostname() {
		return hostname;
	}

	public void setHostname(String hostname) {
		this.hostname = hostname;
	}

	public String getBaseUrl() {
		return baseUrl;
	}

	public Credentials getCredentials() {
		return credentials;
	}

	public void setCredentials(Credentials credentials) {
		this.credentials = credentials;
	}

	public boolean isLoggedIn() {
		return isLoggedIn;
	}

	public void setLoggedIn(boolean isLoggedIn) {
		this.isLoggedIn = isLoggedIn;
	}

	public String getSchoolName() {
		return schoolName;
	}

	public void setSchoolName(String schoolName) {
		this.schoolName = schoolName;
	}

}
