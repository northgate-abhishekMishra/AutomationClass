package ddf.base;

import org.testng.SkipException;

public class Check_TestSkip {
	public static void skip(String TestCaseName) {
		XLS_POI datatable = new XLS_POI(System.getProperty("user.dir") + "\\src\\test\\resources\\TestData.xlsx");
		String testCaseName = null;
		String ExecutionMode = null;
		
		System.out.println("RoCOunt = " + datatable.getRowCount("TestCaseList"));
		
		for (int i = 2; i <= datatable.getRowCount("TestCaseList"); i++) {
			testCaseName = datatable.getCellData("TestCaseList", i, "TesCaseName");
			ExecutionMode = datatable.getCellData("TestCaseList", i, "ExecutionMode");
			
			if (testCaseName.equals(TestCaseName)) {
				if (ExecutionMode.equals("No")) {
					throw new SkipException("Test skipped as mentione in excel");
				}
			}
		}
	}
}
