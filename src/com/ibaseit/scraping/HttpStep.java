package com.ibaseit.scraping;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;
import org.apache.poi.xssf.usermodel.XSSFSheet;


public class HttpStep {

    public static HttpClient client = HttpClientBuilder.create().build();

    private String name;
    private String url;
    private String method;
    private Map<String, String> reqParam;
    private Map<String, String> reqHeader;
    private String resProcessHandler;

    static int rowNo;

    public HttpStep(XSSFSheet sheet, int rowNum) {
	rowNo = rowNum;
	this.name = ExcelUtils.asString(sheet, ++rowNo, 1);
	this.url = ExcelUtils.asString(sheet, ++rowNo, 1);
	this.method = ExcelUtils.asString(sheet, ++rowNo, 1);
	this.reqHeader = extractReqHeader(sheet, ++rowNo);
	this.reqParam = extractReqParam(sheet, ++rowNo);
	this.resProcessHandler = ExcelUtils.asString(sheet, ++rowNo, 1);
    }

    private Map<String, String> extractReqParam(XSSFSheet sheet, int rowNum) {
	Map<String, String> requestParam = new HashMap<String, String>();
	for (int rowCnt = rowNum + 1; true; rowCnt++) {
	    if ("ResponseProcessHandler".equalsIgnoreCase(ExcelUtils.asString(sheet, rowCnt, 0)))
		break;
	    else
		requestParam.put(ExcelUtils.asString(sheet, rowCnt, 0),
				  ExcelUtils.asString(sheet, rowCnt, 1));
	    ++rowNo;
	}

	return requestParam;
    }

    private Map<String, String> extractReqHeader(XSSFSheet sheet, int rowNum) {
	Map<String, String> requestHeader = new HashMap<String, String>();
	for (int rowCnt = rowNum + 1; true; rowCnt++) {
	    if ("Reqest Params".equalsIgnoreCase(ExcelUtils.asString(sheet, rowCnt, 0)))
		break;
	    else
		requestHeader.put(ExcelUtils.asString(sheet, rowCnt, 0),
			           ExcelUtils.asString(sheet, rowCnt, 1));
	    ++rowNo;
	}
	return requestHeader;
    }

    static String page = "";

    public void execute(Map<String, String> currentClientInfo, HttpContext httpContext) throws Exception {
	/*HttpContext httpContext = new BasicHttpContext();
	httpContext.setAttribute(HttpClientContext.COOKIE_STORE, new BasicCookieStore());*/
	List<NameValuePair> formParams = null;

	if ("GET".equalsIgnoreCase(this.getMethod())) {
	    
	    HttpGet request = new HttpGet(this.getUrl());
	    HttpResponse response = client.execute(request, httpContext);
	    page =new ResProcHandler().handleResponse(response,this.getResProcessHandler(),currentClientInfo);
	} else if ("POST".equalsIgnoreCase(this.getMethod())) {
	    
	    formParams = new FormData().getFormParams(page, this, currentClientInfo);
	    HttpPost post = new HttpPost(this.getUrl());
	    post.setEntity(new UrlEncodedFormEntity(formParams));
	    HttpResponse response = client.execute(post, httpContext);
	    page=new ResProcHandler().handleResponse(response,this.getResProcessHandler(),currentClientInfo);
	    
	    System.out.println(response.getStatusLine().getStatusCode() + "======" + page);
	} else
	    throw new Exception("unwanted request...");
    }

    public String getName() {
	return name;
    }

    public String getUrl() {
	return url;
    }

    public String getMethod() {
	return method;
    }

    public Map<String, String> getReqParam() {
	return reqParam;
    }

    public Map<String, String> getReqHeader() {
	return reqHeader;
    }

    public String getResProcessHandler() {
	return resProcessHandler;
    }
}
