package ddf.base;

import java.util.Hashtable;

import org.testng.ITestContext;
import org.testng.annotations.DataProvider;

public class DataProviderClass {

	@DataProvider
	public Object[][] getData(ITestContext context) {
		
		XLS_POI datatable = new XLS_POI(System.getProperty("user.dir") + "\\src\\test\\resources\\TestData.xlsx");
		String sheetName = context.getCurrentXmlTest().getParameter("sheetName");
		String testcasename = context.getCurrentXmlTest().getParameter("TestCaseName");
		int testColumnCount = 0;
		int rowHeaderNumber = 0;
		int testDataStartNumber = 0;

		int testDataRowNumbers = 0;
		int totalRowCount = datatable.getRowCount(sheetName);

		
		for (int i = 0; i < totalRowCount; i++) {
			if (datatable.getCellData(sheetName, i, 1).equalsIgnoreCase(testcasename)) {
				rowHeaderNumber = i + 1;
				testColumnCount = datatable.getColumnCount(sheetName, i + 1);
				testDataStartNumber = rowHeaderNumber + 1;

				for (int j = testDataStartNumber; j <= totalRowCount; j++) {
					if (!(datatable.getCellData(sheetName, j, 1).equals("-2"))) {
						testDataRowNumbers++;
					} else {
						break;
					}
				}

				System.out.println("i = " + i);
				System.out.println("rowHeaderNumber = " + rowHeaderNumber);
				System.out.println("testColumnCount = " + testColumnCount);
				System.out.println("testDataStartNumber = " + testDataStartNumber);
				System.out.println("testDataRowNumbers = " + testDataRowNumbers);
			}
		}

		Object[][] obj = new Object[testDataRowNumbers][1];
		Hashtable<String, String> hashtable = null;
		String Keyname = null;
		String KeyValue = null;
		int zz = 0;

		for (int i = 0; i < testDataRowNumbers; i++) {
			hashtable = new Hashtable<String, String>();

			for (int j = 1; j <= testColumnCount; j++) {
//				System.out.print(datatable.getCellData(sheetName, testDataStartNumber, j) + " ");
				Keyname = datatable.getCellData(sheetName, rowHeaderNumber, j);
				KeyValue = datatable.getCellData(sheetName, testDataStartNumber, j);
				hashtable.put(Keyname, KeyValue);

//				obj[i][j - 1] = datatable.getCellData(sheetName, testDataStartNumber, j);

			}
			obj[zz][0] = hashtable;
			zz++;
			testDataStartNumber++;
		}

/*		for (int i = 0; i < testDataRowNumbers; i++) {
			System.out.print(obj[i][0] + " ");
			System.out.println();
		}*/
		return obj;

	}

}
