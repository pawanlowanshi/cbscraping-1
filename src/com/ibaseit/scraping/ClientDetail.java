package com.ibaseit.scraping;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ClientDetail {
    public static List<Map<String, String>> clientInfo = new ArrayList<Map<String, String>>();
    public static Map<String, Object> metaInfo = new HashMap<String, Object>();

    public static void main(String[] args) throws Exception  {
	
	extractGetWay();

	executeGetWay();
    }

    private static void executeGetWay() throws Exception {
	// TODO Auto-generated method stub
	for (Map<String, String> currentClientInfo : clientInfo) {
	    HttpContext httpContext = new BasicHttpContext();
		httpContext.setAttribute(HttpClientContext.COOKIE_STORE,
			new BasicCookieStore());
	    for (HttpStep httpStep : (List<HttpStep>) metaInfo.get(currentClientInfo.get("Getway"))) {
		httpStep.execute(currentClientInfo,httpContext);
	    }
	}
	
    }

    private static void extractGetWay() throws FileNotFoundException,IOException {
	XSSFWorkbook wb = new XSSFWorkbook(new FileInputStream(new File("src\\conf\\ChargeBackMetadata.xlsx")));
	XSSFSheet sheet = wb.getSheet("ClientDetails");

	for (int rowIndex = 1; rowIndex <= sheet.getLastRowNum(); rowIndex++) {
	    Map<String, String> currentClientInfo = new HashMap<String, String>();
	    clientInfo.add(currentClientInfo);
	    for (int colIndex = 0; colIndex <= sheet.getRow(rowIndex).getLastCellNum() - 1; colIndex++)
		currentClientInfo.put(ExcelUtils.asString(sheet, 0, colIndex),
			ExcelUtils.asString(sheet, rowIndex, colIndex));

	    HttpTemplate httpTemplate = new HttpTemplate(wb,currentClientInfo.get("Getway"));

	    metaInfo.put(currentClientInfo.get("Getway"),httpTemplate.allSteps);

	}
	System.out.println(clientInfo);
	System.out.println(metaInfo);
    }
}
