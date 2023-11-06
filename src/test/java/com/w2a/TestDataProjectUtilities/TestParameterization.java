package com.w2a.TestDataProjectUtilities;

import java.lang.reflect.Method;
import java.util.Hashtable;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class TestParameterization {

	@Test(dataProvider = "getData")
	public void testData(Hashtable<String, String>data) {
		
		System.out.println(data.get("RunMode")+ "-----" + data.get("name") + "-----" + data.get("email") + "----" + data.get("description") + "----" + data.get("balance"));

	}

	@DataProvider
	public Object[][] getData() {

		ExcelReader excel = new ExcelReader(
				System.getProperty("user.dir") + "\\src\\test\\resources\\excel\\TestDataExcel.xlsx");

		int rows = excel.getRowCount(Constants.Data_sheet);
		System.out.println("Total rows are : " + rows);

		String testName = "validateCreateCustomer";
		// tells where the test case starts from
		int testCaseRowNum = 1;

		for (testCaseRowNum = 1; testCaseRowNum <= rows; testCaseRowNum++) {

			String testCaseName = excel.getCellData(Constants.Data_sheet, 0, testCaseRowNum);

			if (testCaseName.equalsIgnoreCase(testName))
				break;

		}

		System.out.println("Test case starts from row num : " + testCaseRowNum);

		// checking total rows in test case

		int dataStartRowNum = testCaseRowNum + 2;

		int datarows = 0;
		while (!excel.getCellData(Constants.Data_sheet, 0, dataStartRowNum + datarows).equals("")) {

			datarows++;
		}

		System.out.println("Total rows of data are : " + datarows);

		// checking total columns in test case

		int colStartColNum = testCaseRowNum + 1;
		int datacols = 0;

		while (!excel.getCellData(Constants.Data_sheet, datacols, colStartColNum).equals("")) {

			datacols++;
		}

		System.out.println("Total columns are : " + datacols);

		// Printing data

		Object[][] data = new Object[datarows][1];
		int i =0;

		for (int rNum = dataStartRowNum; rNum < (dataStartRowNum + datarows); rNum++) {
			
			Hashtable<String, String> table = new Hashtable<String, String>();

			for (int cNum = 0; cNum < datacols; cNum++) {

				// System.out.println(excel.getCellData(Constants.Data_sheet, cNum, rNum));

				/*
				 * DataFormatter formatter = new DataFormatter(); String value =
				 * formatter.formatCellValue((excel.getCellData(Constants.Data_sheet, cNum,
				 * rNum))); //data[i][k] = value;
				 */
				String testData  = excel.getCellData(Constants.Data_sheet, cNum, rNum);
				String colName = excel.getCellData(Constants.Data_sheet, cNum, colStartColNum);
				
				table.put(colName, testData);
			}
			
			data[i][0] = table;
			i++;
			
			
		}

		return data;
	}

}
