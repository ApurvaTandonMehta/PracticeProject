package rough;

import com.w2a.TestDataProjectUtilities.Constants;
import com.w2a.TestDataProjectUtilities.ExcelReader;

public class ExcelReading {
	
	
	public static void main(String[] args) {
		
		ExcelReader excel = new ExcelReader(System.getProperty("user.dir")+ "\\src\\test\\resources\\excel\\TestDataExcel.xlsx");
		
		int rows = excel.getRowCount(Constants.Data_sheet);
		System.out.println("Total rows are : " + rows);
		
		String testName = "validateCreateCustomer";
		// tells where the test case starts from
		int testCaseRowNum = 1;
		
		for (testCaseRowNum= 1; testCaseRowNum <= rows; testCaseRowNum++) {
			
		  String testCaseName =  excel.getCellData(Constants.Data_sheet, 0, testCaseRowNum);
		  
		  if(testCaseName.equalsIgnoreCase(testName)) 
			  break;
		  
		}
		
		System.out.println("Test case starts from row num : " + testCaseRowNum );
		
		// checking total rows in test case 
		
		int dataStartRowNum = testCaseRowNum +2;
		
		int datarows = 0;
				while(!excel.getCellData(Constants.Data_sheet, 0, dataStartRowNum+datarows).equals("")) {
					
					datarows++;
				}
				
				System.out.println("Total rows of data are : " + datarows);
				
				
				// checking total columns in test case
		
				
		int colStartColNum = testCaseRowNum+1;		
		int datacols = 0;
		
		while(!excel.getCellData(Constants.Data_sheet, datacols, colStartColNum).equals("")) {
			
			datacols++;
		}
		
		System.out.println("Total columns are : " + datacols);
		
		// Printing data
		
		for(int rNum = dataStartRowNum; rNum<(dataStartRowNum+datarows); rNum++) {
			
			
			for(int cNum =0; cNum<datacols;cNum++) {
				
				
				System.out.println(excel.getCellData(Constants.Data_sheet, cNum, rNum));
			}
		}
				
		
	}

}
