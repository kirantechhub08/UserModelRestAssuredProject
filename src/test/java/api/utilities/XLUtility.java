package api.utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.fasterxml.jackson.databind.ser.std.StdKeySerializers.Default;

public class XLUtility {
	
	public static FileInputStream fis;
	public static XSSFWorkbook wb;
	public static XSSFSheet sh;
	public static XSSFRow rw;
	public static XSSFCell cl;
	
	public static String getCellValue(String filename, String sheetName, int rn, int cl1) throws IOException
	{
		fis = new FileInputStream(new File(filename));
	    wb=new XSSFWorkbook(fis);
	    sh=wb.getSheet(sheetName);
	    cl=wb.getSheet(sheetName).getRow(rn).getCell(cl1);
	    
	    String celldata="";
	    switch(cl.getCellType())
	    {
	    case STRING: 
	         celldata=cl.getStringCellValue();
	         break;
		case NUMERIC:
	    	 celldata=String.valueOf(cl.getNumericCellValue());
	    	break;
	    case BOOLEAN:
	    	celldata=String.valueOf(cl.getBooleanCellValue());
	    	break;
	    default:
	    	celldata=cl.getStringCellValue();
	    	break;
	    		
	         
	     }
	    
	    wb.close();
	    fis.close();
	    return celldata;
	    
	   
		
	}
	
	
	public static int getRowCount(String fname, String sheetName) throws IOException
	{
	    fis=new FileInputStream(fname);
	    wb= new XSSFWorkbook(fis);
	    sh=wb.getSheet(sheetName);
	    int rn=sh.getLastRowNum()+1;
	    wb.close();
	    return rn;
	   
	}
	
	
	public static int getCellCount(String fname, String sheetName) throws IOException
	{
		fis=new FileInputStream(fname);
		wb= new XSSFWorkbook(fis);
		sh=wb.getSheet(sheetName);
		int clcnt=sh.getRow(0).getLastCellNum();
		wb.close();
		return clcnt;
	}

	

}
