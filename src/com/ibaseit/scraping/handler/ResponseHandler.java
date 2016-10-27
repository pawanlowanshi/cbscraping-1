package com.ibaseit.scraping.handler;

import java.io.IOException;
import java.util.Map;

import org.apache.http.HttpResponse;

public interface ResponseHandler {

    public String handleResponse(HttpResponse response,
	    Map<String, String> currentClientInfo) throws IOException;

}
