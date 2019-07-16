package ddf.base;

import java.io.FileInputStream;
import java.io.FileOutputStream;

import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

class XLS_POI {
	public String excel_path;
	public FileInputStream fis = null;
	public FileOutputStream fos = null;
	public XSSFWorkbook workbook = null;
	public XSSFSheet sheet = null;
	public XSSFRow row = null;
	public XSSFCell cell = null;

	// Contrustor to create XLS_POI object with the Excel file path
	// path of excel file is passed as argument
	
	public XLS_POI(String excel_path) {

		this.excel_path = excel_path;
		try {
			this.fis = new FileInputStream(excel_path);
			this.workbook = new XSSFWorkbook(fis);
			// this.sheet = workbook.getSheetAt(0);
			// this.fis.close();
			// this.workbook.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	// Checks whether the given sheet exist in the xlxs file
	// Name of the sheet is passed as argument
	// returns 1 if "sheetName" is present
	// returns 0 if "sheetName" is not present
	public int isSheetPresent(String sheetName) {
		int index = workbook.getSheetIndex(sheetName);
		if (index == -1) {
			return 0;
		} else {
			return 1;
		}
	}

	// returns number of columns in sheet
	// Name of the sheet is passed as argument
	// returns -1 if "sheetName" is not present
	// return -2 if "sheetName" is present however there are no cols
	public int getColumnCount(String sheetName, int rownumber) {
		// if sheet does not exist then return -1
		if (isSheetPresent(sheetName) == 0)
			return -1;

		sheet = workbook.getSheet(sheetName);
		row = sheet.getRow(rownumber-1);

		if (row == null) {
			return -2;
		} else {
			return row.getLastCellNum();
		}
	}

	// returns number of rows present in the sheet "sheetName"
	// returns -1 if "sheetName" not present
	public int getRowCount(String sheetName) {
		// if sheet does not exist then return -1
		if (isSheetPresent(sheetName) == 0)
			return -1;

		sheet = workbook.getSheet(sheetName);
		return (sheet.getLastRowNum() + 1);
	}

	// returns the celldata in a sheet and at particular row and column name
	// Name of the sheet, row number and column name is passed as arguments
	// returns -1 if sheet not present
	// returns -2 if the row number does not exist
	// returns -3 if column number does not exist
	// returns -4 if cell value is blank
	// returns -5 if cell is not boolean, String, Numeric, formula or blank
	public String getCellData(String sheetName, int rowNum, String colName) {

		int colNum = -1;
		if (isSheetPresent(sheetName) == 0) {
			return "-1";
		}
		XSSFSheet worksheet = workbook.getSheet(sheetName);

		row = worksheet.getRow(0);

		if (row == null) {
			return "-2";
		}

		for (int i = 0; i < row.getLastCellNum(); i++) {
			if (row.getCell(i).getStringCellValue().trim().equalsIgnoreCase(colName)) {
				colNum = i;
				break;
			}

		}

		if (colNum == -1) {
			System.out.println(colName + " - column not present in the sheet");
			return "-3";
		}

		try {
			sheet = workbook.getSheet(sheetName);
			row = sheet.getRow(rowNum - 1);
			if (row == null) {
				System.out.println("Rownumber " + rowNum + " not present");
				return "-2";
			}

			cell = row.getCell(colNum);
			if (cell == null) {
				return "-3";
			} else {
				switch (cell.getCellTypeEnum()) {
				case BOOLEAN:
					return String.valueOf(cell.getBooleanCellValue());

				case STRING:
					return cell.getStringCellValue();

				case NUMERIC:

					if (DateUtil.isCellDateFormatted(cell)) {
						return String.valueOf(cell.getDateCellValue());
					} else {
						return String.valueOf(cell.getNumericCellValue());
					}

				case FORMULA:
					return String.valueOf(cell.getCellFormula());

				case BLANK:
					System.out.println("Cell value blank");
					return ("-4");

				default:
					System.out.println("None of the above");
					return ("-5");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		 return "-5";
	}


	// returns the celldata in a sheet and at particular row and column number
	// Name of the sheet, row number and column number is passed as arguments
	// returns -1 if sheet not present
	// returns -2 if the row number does not exist
	// returns -3 if column number does not exist
	// returns -4 if cell value is blank
	// returns -5 if cell is not boolean, String, Numeric, formula or blank	
	public String getCellData(String sheetName, int rowNum, int colNum) {
		try {

			if (isSheetPresent(sheetName) == 0) {
				return "-1";
			}

			sheet = workbook.getSheet(sheetName);
			row = sheet.getRow(rowNum - 1);
			if (row == null) {
				return "-2";
			}
			cell = row.getCell(colNum - 1);
			if (cell == null) {
				return "-3";
			} else {
				switch (cell.getCellTypeEnum()) {
				case BOOLEAN:
					return String.valueOf(cell.getBooleanCellValue());

				case STRING:
					return cell.getStringCellValue();

				case NUMERIC:

					if (DateUtil.isCellDateFormatted(cell)) {
						return String.valueOf(cell.getDateCellValue());
					} else {
						return String.valueOf(cell.getNumericCellValue());
					}

				case FORMULA:
					return String.valueOf(cell.getCellFormula());

				case BLANK:
					System.out.println("Cell value blank");
					return ("-4");

				default:
					System.out.println("None of the above");
					return "-5";
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "-5";

	}

	// set the celldata in a sheet and at particular row and column name
	// Name of the sheet, row number and column name and cellvalue are passed as arguments
	// returns -1 if sheet not present
	// returns -2 if the row number does not exist
	// returns true if celldata is updated
	public String setCellData(String sheetName, int rowNum, String colName, String CellValue) {

		try {
			int colNum = -1;
			if (isSheetPresent(sheetName) == 0) {
				System.out.println(sheetName + " - not present in the workbook");
				return "-1";
			}

			XSSFSheet worksheet = workbook.getSheet(sheetName);

			row = worksheet.getRow(0);
			if (row == null) {
				return "-2";
			}

			for (int i = 0; i < row.getLastCellNum(); i++) {
				if (row.getCell(i).getStringCellValue().trim().equalsIgnoreCase(colName)) {
					colNum = i;
					break;
				}

			}

			row = worksheet.getRow(rowNum - 1);
			if (row == null) {
				row = worksheet.createRow(rowNum - 1);
			}

			cell = row.getCell(colNum);
			if (cell == null) {
				cell = row.createCell(colNum);
			}

			cell.setCellValue(CellValue);

			// fis.close();

			fos = new FileOutputStream(excel_path);
			workbook.write(fos);
			fos.flush();
			fos.close();
		} catch (Exception e) {
			// TODO: handle exception
		}
		return String.valueOf(true);

	}

	// set the celldata in a sheet and at particular row and column number
	// Name of the sheet, row number and column name and cellvalue are passed as arguments
	// returns -1 if sheet not present
	// returns -2 if the row number does not exist
	// returns true if celldata is updated
	public String setCellData(String sheetName, int rowNum, int colNum, String CellValue) {

		try {

			if (isSheetPresent(sheetName) == 0) {
				System.out.println(sheetName + " - not present in the workbook");
				return "-1";
			}

			XSSFSheet worksheet = workbook.getSheet(sheetName);

			row = worksheet.getRow(rowNum - 1);
			if (row == null) {
				row = worksheet.createRow(rowNum - 1);
			}

			cell = row.getCell(colNum - 1);
			if (cell == null) {
				cell = row.createCell(colNum - 1);
			}

			cell.setCellValue(CellValue);

			// fis.close();

			fos = new FileOutputStream(excel_path);
			workbook.write(fos);
			fos.flush();
			fos.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return String.valueOf(true);

	}

	//adds a sheet having name in variable sheetName
	// returns -1 if sheet already present
	// returns true if sheet is added successfully
	public String addSheet(String sheetName) {
		if (isSheetPresent(sheetName) == 1) {
			return "-1";
		}

		try {
			fos = new FileOutputStream(excel_path);
			sheet = workbook.createSheet(sheetName);

			
			workbook.write(fos);
			fos.flush();
			fos.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return String.valueOf(true);

	}

	//removes sheet from workbook
	// returns -1 if sheet not present
	// returns true if sheet is removed successfully
	public String removeSheet(String sheetName) {
		if (isSheetPresent(sheetName) == 0) {
			return "-1";
		}

		try {
			fos = new FileOutputStream(excel_path);
			workbook.removeSheetAt(workbook.getSheetIndex(sheetName));

			workbook.write(fos);
			fos.flush();
			fos.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return String.valueOf(true);
	}

	// adds a column at the end of the table
	// sheetName and Column Name are passed as arguments
	// returns -1 if the sheet is not present in the workbook
	// returns true if column is added successfully
	public String addColumn(String sheetName, String colName) {
		if (isSheetPresent(sheetName) == 0) {
			return "-1";
		}

		try {
			fos = new FileOutputStream(excel_path);
			XSSFSheet worksheet = workbook.getSheet(sheetName);
			row = worksheet.getRow(0);
			if (row == null) {
				row = worksheet.createRow(0);
			}

			if (row.getLastCellNum() == -1) {
				cell = row.createCell(0);
			} else {
				cell = row.createCell(row.getLastCellNum());
			}

			cell.setCellValue(colName);

			workbook.write(fos);
			fos.flush();
			fos.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return String.valueOf(true);

	}

	// do not use this function now as when we try to remove two columns (one after the another) the xlsx file gets corrupted
	public String removeColumn(String sheetName, String colName) {
		
		if (isSheetPresent(sheetName) == 0) {
			return "-1";
		}
		try {

			fos = new FileOutputStream(excel_path);

			int colNum = -1;
			XSSFSheet worksheet = workbook.getSheet(sheetName);
			row = worksheet.getRow(0);

			for (int i = 0; i < row.getLastCellNum(); i++) {
				if (row.getCell(i).getStringCellValue().trim().equalsIgnoreCase(colName)) {
					colNum = i;
					break;
				}

			}

			for (int i = 0; i < getRowCount(sheetName); i++) {
				row = worksheet.getRow(i);
				if (row != null) {
					cell = row.getCell(colNum);
					if (cell != null) {
						row.removeCell(cell);
					}
				}

			}

			workbook.write(fos);
			fos.flush();
			fos.close();
			return colName;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return colName;
	}
	
	// not implemented yet – HomeWork
	public String addRow(String sheetName, int rowNum, String rowValue[]) {
		return String.valueOf(false);
	}
	
	// not implemented yet – HomeWork	
	public String removeRow(String sheetName, int rowNum) {
		return String.valueOf(false);
	}

}

