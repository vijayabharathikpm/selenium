package dataDrivenTesting;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ReadingdatafromMultipleRow {

	public static void main(String[] args) throws EncryptedDocumentException, IOException {
		FileInputStream fis=new FileInputStream("C:\\Users\\ADMIN\\Arun-work-space\\demo\\src\\test\\resources\\E4235.xlsx");
		Workbook wb = WorkbookFactory.create(fis);
		Sheet sh=wb.getSheet("campaign");
		int lastrow = sh.getLastRowNum();
	     
		for(int i=1;i<=lastrow;i++)
		{
			
			String name = sh.getRow(i).getCell(0).getStringCellValue();
			String size = sh.getRow(i).getCell(1).getStringCellValue();
			System.out.println(name+ " "+ size);
		}

	}

}
