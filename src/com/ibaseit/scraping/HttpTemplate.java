package com.ibaseit.scraping;

import java.util.ArrayList;
import java.util.List;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class HttpTemplate {
    List<HttpStep> allSteps = new ArrayList<HttpStep>();

    public HttpTemplate(XSSFWorkbook wb, String getWay) {
	XSSFSheet sheet = wb.getSheet(getWay);
	System.out.println(sheet.getSheetName());
	int rowIndex = 0;
	int nullcnt = 0;
	for (rowIndex = 2; rowIndex <= sheet.getLastRowNum(); rowIndex++) {
	    String cellValue = ExcelUtils.asString(sheet, rowIndex, 0);

	    if ("".equals(cellValue))
		nullcnt++;
	    if (nullcnt == 5)
		break;
	    if (cellValue.startsWith("Step"))
		allSteps.add(new HttpStep(sheet, rowIndex));
	}
    }

}
