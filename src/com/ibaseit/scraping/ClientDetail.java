package com.ibaseit.scraping;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.ibaseit.scraping.template.VimasHttpTemplate;
import com.ibaseit.scraping.utils.ExcelUtils;


public class ClientDetail {
	public static List<Map<String, Object>> clientInfo = new ArrayList<Map<String, Object>>();
	public static Map<String, Object> metaInfo = new HashMap<String, Object>();

	public static void main(String[] args) throws Exception {

		extractGetWay();

		executeGetWay();
	}

	static void executeGetWay() throws Exception {

		for (Map<String, Object> currentClientInfo : clientInfo) {

			if ("VIMAS".equalsIgnoreCase(currentClientInfo.get("Getway").toString())) {
				VimasHttpTemplate vimasHttpTemplate = (VimasHttpTemplate) metaInfo.get(currentClientInfo.get("Getway"));
				System.out.println("Template Name"+ vimasHttpTemplate.getTemplateName());
				vimasHttpTemplate.execute(currentClientInfo);
			} else {
				HttpTemplate template = (HttpTemplate) metaInfo.get(currentClientInfo
						.get("Getway"));
				System.out.println("Template Name" + template.getTemplateName());
				template.execute(currentClientInfo);

			}

		}

	}

	static void extractGetWay() throws FileNotFoundException, IOException {
		XSSFWorkbook wb = new XSSFWorkbook(new FileInputStream(new File(
				"src\\conf\\ChargeBackMetadata.xlsx")));
		XSSFSheet sheet = wb.getSheet("ClientDetails");

		for (int rowIndex = 1; rowIndex <= sheet.getLastRowNum(); rowIndex++) {
			Map<String, Object> currentClientInfo = new HashMap<String, Object>();
			clientInfo.add(currentClientInfo);
			for (int colIndex = 0; colIndex <= sheet.getRow(rowIndex)
					.getLastCellNum() - 1; colIndex++)
				currentClientInfo.put(ExcelUtils.asString(sheet, 0, colIndex),
						ExcelUtils.asString(sheet, rowIndex, colIndex));

			if ("VIMAS".equalsIgnoreCase(currentClientInfo.get("Getway").toString())) {
				VimasHttpTemplate vimasHttpTemplate = new VimasHttpTemplate(wb,
						currentClientInfo.get("Getway").toString());
				metaInfo.put(currentClientInfo.get("Getway").toString(),
						vimasHttpTemplate);
			} else {
				HttpTemplate httpTemplate = new HttpTemplate(wb, currentClientInfo.get(
						"Getway").toString());
				metaInfo.put(currentClientInfo.get("Getway").toString(), httpTemplate);
			}

		}
		System.out.println(clientInfo);
		System.out.println(metaInfo);
	}
}
