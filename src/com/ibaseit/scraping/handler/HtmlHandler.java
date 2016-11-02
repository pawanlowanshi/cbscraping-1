package com.ibaseit.scraping.handler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;

import org.apache.http.HttpResponse;

import com.ibaseit.scraping.ResponseHandler;

public class HtmlHandler implements ResponseHandler {

	@Override
	public String handleResponse(HttpResponse response,
			Map<String, Object> currentClientInfo) throws IOException {

		System.out.println("hello Response for HtmlHandler");

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
