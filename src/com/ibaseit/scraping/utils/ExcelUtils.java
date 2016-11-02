package com.ibaseit.scraping.utils;

import java.util.Date;

import org.apache.poi.hssf.util.AreaReference;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtils {

	public static String asString(XSSFSheet sheet, int rowIndex, int colIndex) {

		return sheet.getRow(rowIndex).getCell(colIndex).toString();

	}

	public static int asInt(XSSFSheet sheet, int rowIndex, int colIndex) {
		return (int) sheet.getRow(rowIndex).getCell(colIndex).getNumericCellValue();// cell.setCellType(Cell.CELL_TYPE_STRING)
	}

	public static double asDouble(XSSFSheet sheet, int rowIndex, int colIndex) {
		return sheet.getRow(rowIndex).getCell(colIndex).getNumericCellValue();// cell.setCellType(Cell.CELL_TYPE_STRING)
	}

	public static String getFormulaCellValByIndex(XSSFSheet sheet, int rowIndex,
			int colIndex) {
		return sheet.getRow(rowIndex).getCell(colIndex).getNumericCellValue() + "";
	}

	public static Cell asCell(XSSFSheet sheet, int rowIndex, int colIndex) {
		return sheet.getRow(rowIndex).getCell(colIndex);
	}

	public static Boolean asBoolean(XSSFSheet sheet, int rowIndex, int colIndex) {
		if ("pass".equalsIgnoreCase(sheet.getRow(rowIndex).getCell(colIndex)
				.toString())
				|| "true".equalsIgnoreCase(sheet.getRow(rowIndex).getCell(colIndex)
						.toString())
				|| "1".equalsIgnoreCase(sheet.getRow(rowIndex).getCell(colIndex)
						.toString()))

			return true;
		else
			return false;
	}

	public static Boolean getBooleanValue(XSSFRow row) {
		if (row.getCell(1) != null
				&& "pass".equalsIgnoreCase(row.getCell(1).toString())
				|| "true".equalsIgnoreCase(row.getCell(1).toString()))
			return true;
		else
			return false;
	}

	public static Date asDate(XSSFSheet sheet, int rowIndex, int colIndex) {
		return sheet.getRow(rowIndex).getCell(colIndex).getDateCellValue();
	}

	public static void setDate(XSSFSheet sheet, int rowIndex, int colIndex,
			Date value) {
		sheet.getRow(rowIndex).getCell(colIndex).setCellValue(value);
	}

	public static void setValue(XSSFSheet sheet, int rowIndex, int colIndex,
			String value) {
		XSSFRow row = sheet.getRow(rowIndex);
		Cell cell;
		if (row != null) {
			cell = row.getCell(colIndex);
		} else {
			cell = sheet.createRow(rowIndex).getCell(colIndex);
		}
		if (cell != null) {
			cell.setCellValue(value);
		} else {
			sheet.getRow(rowIndex).createCell(colIndex).setCellValue(value);
		}
	}

	public static String asStringByName(XSSFWorkbook wb, XSSFSheet sheet,
			int rowIndex, String name) {
		return sheet
				.getRow(rowIndex)
				.getCell(
						new AreaReference(wb.getNameAt(wb.getNameIndex(name))
								.getRefersToFormula()).getFirstCell().getCol()).toString();
	}

	public static Cell asCellByName(XSSFWorkbook wb, XSSFSheet sheet,
			int rowIndex, String name) {
		return sheet.getRow(rowIndex).getCell(
				new AreaReference(wb.getNameAt(wb.getNameIndex(name))
						.getRefersToFormula()).getFirstCell().getCol());
	}
}
