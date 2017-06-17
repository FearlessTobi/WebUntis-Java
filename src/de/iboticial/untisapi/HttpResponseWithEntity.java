package de.iboticial.untisapi;

import org.apache.http.HttpResponse;
import org.json.JSONObject;

public class HttpResponseWithEntity {

	private final HttpResponse httpResponse;
	private final String JSON;
	private String result;

	public HttpResponseWithEntity(HttpResponse newHttpResponse, String newJSON) {
		httpResponse = newHttpResponse;
		JSON = newJSON;

		try {
			result = new JSONObject(JSON).get("result").toString();
		} catch (Exception e) {

			try {
				result = new JSONObject(JSON).get("error").toString();
			} catch (Exception e2) {
				result = null;
			}

		}

	}

	public HttpResponse getHttpResponse() {
		return httpResponse;
	}

	public String getJSON() {
		return JSON;
	}

	public String getResult() {
		return result == null ? JSON : result;
	}

}
