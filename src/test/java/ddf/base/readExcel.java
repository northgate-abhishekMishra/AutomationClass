package ddf.base;

public class readExcel {

	public static void main(String[] args) {

		XLS_POI datatable = new XLS_POI(System.getProperty("user.dir") + "\\src\\test\\resources\\TestData.xlsx");
		String sheetName = "Sheet1";
		String testcasename = "hybrid";
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

		Object [][] obj = new Object[testDataRowNumbers][testColumnCount];
		
		for (int i = 0; i < testDataRowNumbers; i++) {
			for (int j = 1; j <= testColumnCount; j++) {
//				System.out.print(datatable.getCellData(sheetName, testDataStartNumber, j) + " ");
				obj[i][j-1] = datatable.getCellData(sheetName, testDataStartNumber, j);
			}
			testDataStartNumber++;
//			System.out.println();
		}
		
		for (int i = 0; i < testDataRowNumbers; i++) {
			for (int j = 0; j < testColumnCount; j++) {
				System.out.print(obj[i][j] + " ");
			}
			System.out.println();
		}
		
	}
}
