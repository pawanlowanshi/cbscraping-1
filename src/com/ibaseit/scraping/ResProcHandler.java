package com.ibaseit.scraping;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.Header;
import org.apache.http.HttpResponse;

public class ResProcHandler {
	public Map<String, String> headerInfo = new HashMap<String, String>();

	public String handleResponse(HttpResponse response) throws IOException {
		getResHeader(response);
		BufferedReader rd = new BufferedReader(new InputStreamReader(response
				.getEntity().getContent()));
		StringBuffer result = new StringBuffer();
		String line = "";
		while ((line = rd.readLine()) != null) {
			result.append(line);
		}
		return result.toString();
	}

	private void getResHeader(HttpResponse response) {
		// get all headers
		for (Header header : response.getAllHeaders()) {
			System.out.println("Key : " + header.getName() + " ,Value : "
					+ header.getValue());
			headerInfo.put(header.getName(), header.getValue());
		}
	}
}
