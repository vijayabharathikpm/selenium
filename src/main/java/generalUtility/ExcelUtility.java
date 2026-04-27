package generalUtility;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

//simple command
public class ExcelUtility {


		public String toReadDataFromExcel(String Sheetname, int RowNum, int CellNum) throws EncryptedDocumentException, IOException
		{
			FileInputStream fis2=new FileInputStream("C:\\Users\\ADMIN\\Arun-work-space\\demo\\src\\test\\resources\\E4235.xlsx");
			Workbook wb = WorkbookFactory.create(fis2);
			String value = wb.getSheet(Sheetname).getRow(RowNum).getCell(CellNum).getStringCellValue();
			return value;
			
		}
		
	}


