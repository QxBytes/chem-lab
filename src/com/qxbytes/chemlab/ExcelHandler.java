package com.qxbytes.chemlab;



import java.io.File;
import java.io.IOException;
import java.util.List;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import jxl.write.Label;
import jxl.write.Number;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException; 
/**
 * 
 * @author QxBytes
 *
 */
public class ExcelHandler {
	public static void testHandler() throws BiffException, IOException, WriteException
	   {
	      WritableWorkbook wworkbook;
	      wworkbook = Workbook.createWorkbook(new File("output.xls"));
	      WritableSheet wsheet = wworkbook.createSheet("First Sheet", 0);
	      Label label = new Label(0, 2, "A label record");
	      wsheet.addCell(label);
	      Number number = new Number(3, 4, 3.1459);
	      wsheet.addCell(number);
	      wworkbook.write();
	      wworkbook.close();

	      Workbook workbook = Workbook.getWorkbook(new File("output.xls"));
	      Sheet sheet = workbook.getSheet(0);
	      Cell cell1 = sheet.getCell(0, 2);
	      System.out.println(cell1.getContents());
	      Cell cell2 = sheet.getCell(3, 4);
	      System.out.println(cell2.getContents());
	      workbook.close();
	   }
	/**
	 * Assume Elements is already sorted and compressed
	 * @param elements
	 * @throws BiffException
	 * @throws IOException
	 * @throws WriteException
	 */
	public static void exportToExcel(List<Element> elements) throws BiffException, IOException, WriteException {
		WritableWorkbook wworkbook;
	      wworkbook = Workbook.createWorkbook(new File("ChemEX" + System.currentTimeMillis() + ".xls"));
	      WritableSheet wsheet = wworkbook.createSheet("export", 0);
	    wsheet.addCell(new Label(0,0,"Atom Num"));
	    wsheet.addCell(new Label(1,0,"Symbol"));
	    wsheet.addCell(new Label(2,0,"Name"));
	    wsheet.addCell(new Label(3,0,"Charge"));
	    wsheet.addCell(new Label(4,0,"Atom Weight"));
	    wsheet.addCell(new Label(5,0,"Mass Num"));
	    wsheet.addCell(new Label(6,0,"P/N/E"));
	    wsheet.addCell(new Label(7,0,"State"));
	    wsheet.addCell(new Label(8,0,"Metal?"));
	    wsheet.addCell(new Label(9,0,"Artificial?"));
	    wsheet.addCell(new Label(10,0,"Radioactive?"));
	    wsheet.addCell(new Label(11,0,"Row"));
	    wsheet.addCell(new Label(12,0,"Column"));
	    wsheet.addCell(new Label(13,0,"Elec. in Shell#"));
	    wsheet.addCell(new Label(14,0,"# of atoms"));
	    
	    for (int row = 1 ; row < elements.size()+1 ; row++) {
	    	List<String> temp = Utils.esString(elements.get(row-1));
	    	temp.add(elements.get(row-1).getAbscount()+"");
	    	for (int column = 0 ; column < 15 ; column++) {
	    		wsheet.addCell(new Label(column,row,temp.get(column)));
	    	}
	    }
	    
	    wworkbook.write();
	    wworkbook.close();
	}
}
