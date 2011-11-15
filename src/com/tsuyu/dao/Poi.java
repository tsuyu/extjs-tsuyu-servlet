package com.tsuyu.dao;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Map;


import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.hibernate.Criteria;
import org.hibernate.Session;

import com.tsuyu.persistance.HibernateUtil;

public class Poi {
	
	public static void main( String [] args ) throws FileNotFoundException, IOException {
		 
			
		// create new workbook and new sheet in excel
		    CellStyle style;
		    CellStyle styled;
		    Row row;
		    Cell cell;
		    
		    Workbook wb = new XSSFWorkbook();
		    Sheet sheet = wb.createSheet("new sheet");
			 
		// create font n style
		    Font font = wb.createFont();
			font.setFontHeightInPoints((short)10);
			font.setFontName("Arial");
			font.setBoldweight(XSSFFont.BOLDWEIGHT_BOLD);
		 
			style = wb.createCellStyle();
			style.setFont(font);
			style.setFillForegroundColor(HSSFColor.PINK.index);
		    style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
			 
			style.setBorderBottom(CellStyle.BORDER_THIN);
		    style.setBottomBorderColor(IndexedColors.BLACK.getIndex());
		    style.setBorderLeft(CellStyle.BORDER_THIN);
		    style.setLeftBorderColor(IndexedColors.BLACK.getIndex());
		    style.setBorderRight(CellStyle.BORDER_THIN);
		    style.setRightBorderColor(IndexedColors.BLACK.getIndex());
		    style.setBorderRight(CellStyle.BORDER_THIN);
		    style.setTopBorderColor(IndexedColors.BLACK.getIndex());
		    
		    styled = wb.createCellStyle();
			 
			styled.setBorderBottom(CellStyle.BORDER_THIN);
		    styled.setBottomBorderColor(IndexedColors.BLACK.getIndex());
		    styled.setBorderLeft(CellStyle.BORDER_THIN);
		    styled.setLeftBorderColor(IndexedColors.BLACK.getIndex());
		    styled.setBorderRight(CellStyle.BORDER_THIN);
		    styled.setRightBorderColor(IndexedColors.BLACK.getIndex());
		    styled.setBorderRight(CellStyle.BORDER_THIN);
		    styled.setTopBorderColor(IndexedColors.BLACK.getIndex());
			    
			row = sheet.createRow((short)0);
			
		    cell = row.createCell((short)0);
		    cell.setCellValue("Sequence");
		    cell.setCellStyle(style);
		    
		    cell = row.createCell((short)1);
		    cell.setCellValue("Name"); 
		    cell.setCellStyle(style);
		    
		    cell = row.createCell((short)2);
		    cell.setCellValue("Description"); 
		    cell.setCellStyle(style);
		    
		    cell = row.createCell((short)3);
		    cell.setCellValue("Icon"); 
		    cell.setCellStyle(style);
		    
		    cell = row.createCell((short)4);
		    cell.setCellValue("Access Level Name"); 
		    cell.setCellStyle(style);
		    
		Session session = HibernateUtil.getSessionFactory().openSession();
		try {
			
			String hql = "select b.accordianId as accordianId, b.accordianSequence as accordianSequence, b.accordianName as accordianName," +
			" b.accordianDescription as accordianDescription, b.accordianIcon as accordianIcon,a.accessLevelName as accessLevelName from AccessLevel a join a.accordians b ";
			List data = session.createQuery(hql)
					.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP).list();
			int i=1;
			for (Object object : data) {
				Map rows = (Map) object;
				row = sheet.createRow((short)i++);
			    cell = row.createCell((short)0);
				cell.setCellValue(rows.get("accordianSequence").toString());
				cell.setCellStyle(styled);
				
		        cell = row.createCell((short)1);
		        cell.setCellValue(rows.get("accordianName").toString());
		        cell.setCellStyle(styled);
				
				cell = row.createCell((short)2);
				cell.setCellValue(rows.get("accordianDescription").toString());
				cell.setCellStyle(styled);
				
				cell = row.createCell((short)3);
				cell.setCellValue(rows.get("accordianIcon").toString());
				cell.setCellStyle(styled);
				
				cell = row.createCell((short)4);
				cell.setCellValue(rows.get("accessLevelName").toString());
				cell.setCellStyle(styled);
			}

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		session.close();
		
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(new File("/home/tsuyu/setup/eclipsejee/workspace/extjs-tsuyu-servlet/src/com/tsuyu/dao/lor.xlsx"));
            wb.write(fos);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fos != null) {
                try {
                    fos.flush();
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
		 
	}
}
