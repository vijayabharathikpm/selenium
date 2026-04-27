package dataDrivenTesting;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class WritingDataBacktoExcel {

	public static void main(String[] args) throws EncryptedDocumentException, IOException {
		FileInputStream fis=new FileInputStream("C:\\Users\\ADMIN\\Arun-work-space\\demo\\src\\test\\resources\\E4235.xlsx");
		Workbook wb = WorkbookFactory.create(fis);
		Sheet ns = wb.createSheet("Sheet5");
		//Sheet sh=wb.getSheet("campaign");
		Row nr = ns.createRow(0);
		Cell c = nr.createCell(1);
		c.setCellValue("comments");
		FileOutputStream fos=new FileOutputStream("C:\\Users\\ADMIN\\Arun-work-space\\demo\\src\\test\\resources\\E4235.xlsx");
		wb.write(fos);
		wb.close();

	}

}
