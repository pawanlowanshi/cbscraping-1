package com.ibaseit.scraping;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;

public class ClientUtility {

	public List<NameValuePair> getFormParams(String htmlResponse,
			HttpStep httpStep, Map<String, String> currentClientDeatil)
			throws UnsupportedEncodingException {

		System.out.println("Extracting form's data...");
		List<NameValuePair> paramList = new ArrayList<NameValuePair>();
		Map<String, String> reqParam = httpStep.getReqParam();
		for (Element login : Jsoup.parse(htmlResponse).getElementsByTag("form")) {
			for (Element inputElement : login.getElementsByTag("input")) {
				String key = inputElement.attr("name");
				String value = inputElement.attr("value");
				if (key.equals(getKey(httpStep, key)))
					value = httpStep.getName().startsWith("Login") ? currentClientDeatil
							.get(reqParam.get(key).replace("$", "")) : reqParam.get(key);
				paramList.add(new BasicNameValuePair(key, value));
			}
		}
		return paramList;
	}

	private String getKey(HttpStep httpStep, String key) {
		String myKey = null;
		for (String currentKey : httpStep.getReqParam().keySet()) {
			if (key.equals(currentKey)) {
				myKey = key;
				break;
			}
		}
		return myKey;
	}

	public String getResult(HttpResponse response) throws IOException {
		BufferedReader rd = new BufferedReader(new InputStreamReader(response
				.getEntity().getContent()));
		StringBuffer result = new StringBuffer();
		String line = "";
		while ((line = rd.readLine()) != null) {
			result.append(line);
		}
		return result.toString();
	}

}
