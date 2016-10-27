package com.ibaseit.scraping.handler;

import java.util.Map;

import org.apache.http.HttpResponse;

public class JsonHandler implements ResponseHandler {

    @Override
    public String handleResponse(HttpResponse response,
	    Map<String, String> currentClientInfo) {

	System.out.println("hello Response for JsonHandler");
	return "";
    }

}
