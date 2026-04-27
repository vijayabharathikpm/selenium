package dataDrivenTesting;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.ss.util.WorkbookUtil;

public class ReadingDataFromExcel {

	public static void main(String[] args) throws EncryptedDocumentException, IOException {
		//1.create object of fileInputStream
		//2.open workbook in read one mode
		//3.get the control of sheet
		//4.get the control of row
		//5.get the control of cell
		//6.get the value of cell
		//7.close the workbook
		FileInputStream fis=new FileInputStream("./src\\test\\resources\\E42.xlsx");
		Workbook wb = WorkbookFactory.create(fis);
		Sheet sh=wb.getSheet("Sheet1");
		/*Row r=sh.getRow(1);
		Cell c=r.getCell(0);
		String value = c.getStringCellValue();
		System.out.println(value);*/
	
		String value = sh.getRow(1).getCell(0).getStringCellValue();
		System.out.println(value);
		String value1= sh.getRow(1).getCell(1).getStringCellValue();
		System.out.println(value1);
		
		wb.close();
	}

}
