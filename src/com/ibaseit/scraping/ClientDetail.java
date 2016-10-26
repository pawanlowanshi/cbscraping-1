package com.ibaseit.scraping;

import java.io.File;
import java.io.FileInputStream;
import java.util.HashMap;
import java.util.Map;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ClientDetail {
	public static Map<String, String> catchInfo = new HashMap<String, String>();

	public static void main(String[] args) throws Exception {

		XSSFWorkbook wb = new XSSFWorkbook(new FileInputStream(new File(
				"D:\\IBaseDev\\cbscraping\\src\\conf\\Book1.xlsx")));
		
		XSSFSheet sheet = wb.getSheet("Sheet3");
		for (int rowIndex = 1; rowIndex <= sheet.getLastRowNum(); rowIndex++) {
			Map<String, String> currentClientDeatil = new HashMap<String, String>();
			for (int colIndex = 0; colIndex <= sheet.getRow(rowIndex)
					.getLastCellNum() - 1; colIndex++)
				currentClientDeatil.put(
						ExcelUtils.getCellValByIndex(sheet, 0, colIndex),
						ExcelUtils.getCellValByIndex(sheet, rowIndex, colIndex));

			HttpTemplate httpTemplate = new HttpTemplate(wb,
					currentClientDeatil.get("Getway"));
			for (HttpStep httpStep : httpTemplate.allSteps) {
				httpStep.execute(currentClientDeatil, catchInfo);
			}

		}
	}
}
