package com.ibaseit.scraping;

import java.io.IOException;
import java.util.Map;

import org.apache.http.HttpResponse;

public interface ResponseHandler {

	public String handleResponse(HttpResponse response,
			Map<String, Object> currentClientInfo) throws IOException;

}
