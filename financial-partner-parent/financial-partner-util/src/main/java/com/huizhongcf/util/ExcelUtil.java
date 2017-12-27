package com.huizhongcf.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFCellUtil;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.util.CellRangeAddress;

/**
 * 
 *
 * Description: Excel导出帮助类
 *
 * @author JiangLong Cui
 * @version 1.0
 * <pre>
 * Modification History: 
 * Date         Author      Version     Description 
 * ------------------------------------------------------------------ 
 * 2013-12-18      JiangLong Cui       1.0         1.0 Version 
 * </pre>
 */
public class ExcelUtil {
	/**
	 * 功能：将HSSFWorkbook写入Excel文件
	 * @param 	wb		 HSSFWorkbook
	 * @param 	fileName 文件名
	 * @return  InputStream
	 * @throws Exception 
	 */
	public static void writeWorkbook(HSSFWorkbook wb) throws Exception{
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		InputStream bais = null;
		try {
			wb.write(baos);
			baos.flush();
			byte[] ba = baos.toByteArray();
			bais = new ByteArrayInputStream(ba, 0, ba.length);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(baos != null){
				baos.close();
			}
			if(bais != null){
				bais.close();
			}
		}
	}
	
	/**
	 * 功能：创建Sheet工作簿
	 * @param 	wb	HSSFWorkbook
	 * @param 	sheetName	String
	 * @return	Sheet
	 */
	public static Sheet createSheet(HSSFWorkbook wb, String sheetName){
		Sheet sheet=wb.createSheet(sheetName);
		return sheet;
	}
	
	/**
	 * 功能：创建 Row
	 * @param 	sheet	Sheet
	 * @param 	rowNum	int
	 * @param 	height	int
	 * @return	Row
	 */
	public static Row createRow(Sheet sheet,int rowNum,int height){
		Row row=sheet.createRow(rowNum);
		row.setHeightInPoints((float) height);
		return row;
	}
	
	/**
	 * 功能：创建Cell
	 * @param 	row		Row	
	 * @param 	cellNum	int
	 * @param 	style	CellStyle
	 * @return	Cell
	 */
	public static Cell createCell(Row row,int cellNum,CellStyle style){
		Cell cell=row.createCell(cellNum);
		cell.setCellStyle(style);
		return cell;
	}

	/**
	 * 
	 * Description: 创建表头
	 *
	 * @param hssfWB     	HSSFWorkbook工作薄
	 * @param sheetName		sheet名
	 * @param tableTitle	表头内容
	 * @param cs			表头样式
	 * @return sheet
	 * @Author JiangLong Cui
	 * @Create Date: 2013-12-19 下午1:29:45
	 */
	public static Sheet createTableHeader (HSSFWorkbook hssfWB, String sheetName, String[] tableTitle, CellStyle cs, int columnWidth, int rowHeight) {
		Sheet sheet = createSheet(hssfWB, sheetName);
		Row row = ExcelUtil.createRow(sheet, 0, rowHeight);
		for (int i = 0; i < tableTitle.length; i++) {
			sheet.setColumnWidth(i, columnWidth);
			Cell cell = ExcelUtil.createCell(row, i, cs);
			cell.setCellValue(tableTitle[i]);
		}
		return sheet;
	}
	
	/**
	 * 
	 * Description: 填充数据
	 *
	 * @param listMap		要填充到excel里的数据
	 * @param sheet			sheet
	 * @param row			行
	 * @param cell			列
	 * @param cs			样式
	 * @param dataTitle		表头列名对应的数据字段
	 * @Author JiangLong Cui
	 * @Create Date: 2013-12-19 下午1:29:22
	 */
	public static void fillData(List<Map<String, Object>> listMap, Sheet sheet, Row row, Cell cell, CellStyle cs, String[] dataTitle) {
		for (int j = 0; j < listMap.size(); j++) {
			Map<String, Object> bidMap = listMap.get(j);
			// 设置宽度自适应
			sheet.autoSizeColumn(j, true);
			row = ExcelUtil.createRow(sheet, j + 1, 26);
			// 填充数据
			for (int k = 0; k < bidMap.size(); k++) {
				// 生成序号列
				Cell cell1 = ExcelUtil.createCell(row, 0, cs);
				cell1.setCellValue(j + 1);
				cell = ExcelUtil.createCell(row, k + 1, cs);
				String value = bidMap.get(dataTitle[k].toString()).toString();
				if (StringUtil.isNotBlank(value)) {
					cell.setCellValue(value);
				} else {
					cell.setCellValue("");
				}
			}
			
		}
	}
	
	/**
	 * 
	 * Description: 分页查询填充数据(用于大数据量)
	 *
	 * @param begin   		起始行
	 * @param listMap 		要填充到excel里的数据
	 * @param sheet			sheet
	 * @param row			行
	 * @param cell			列
	 * @param cs			样式
	 * @param dataTitle		表头列名对应的数据字段
	 * @Author JiangLong Cui
	 * @Create Date: 2013-12-20 下午6:51:23
	 */
	public static void fillDataByPage(int begin, List<Map<String, Object>> listMap, Sheet sheet, Row row, Cell cell, CellStyle cs, String[] dataTitle) {
		for (Map<String, Object> bidMap : listMap) {
			// 设置宽度自适应
			sheet.autoSizeColumn(begin, true);
			row = ExcelUtil.createRow(sheet, ++begin, 26);
			// 填充数据
			for (int k = 0; k < bidMap.size(); k++) {
				// 生成序号列
				Cell cell1 = ExcelUtil.createCell(row, 0, cs);
				cell1.setCellValue(begin);
				cell = ExcelUtil.createCell(row, k + 1, cs);
				String value = bidMap.get(dataTitle[k].toString()).toString();
				if (StringUtil.isNotBlank(value)) {
					cell.setCellValue(value);
				} else {
					cell.setCellValue("");
				}
			}
			
		}
	}
	
	/**
	 * 功能：创建无边框的CellStyle样式
	 * @param 	wb				HSSFWorkbook	
	 * @param 	backgroundColor	背景色	
	 * @param 	foregroundColor	前置色
	 * @param	font			字体
	 * @return	CellStyle
	 */
	public static CellStyle createForegroundCellStyle(HSSFWorkbook wb, short alignment, short valignment, short foregroundColor, short pattern, Font font){
		CellStyle cs=wb.createCellStyle();
		cs.setAlignment(alignment);
		cs.setVerticalAlignment(valignment);
		cs.setFillForegroundColor(foregroundColor);
		cs.setFillPattern(pattern);
		cs.setFont(font);
		return cs;
	}
	
	/**
	 * 功能：创建有边框无背景色的CellStyle样式
	 * @param 	wb				HSSFWorkbook	
	 * @param 	backgroundColor	背景色	
	 * @param 	foregroundColor	前置色
	 * @param	font			字体
	 * @return	CellStyle
	 */
	public static CellStyle createCellStyle(HSSFWorkbook wb, short alignment, short valignment, short borderStyle, Font font){
		// 创建样式
		CellStyle cs=wb.createCellStyle();
		// 设置单元格内容对齐
		cs.setAlignment(alignment);
		cs.setVerticalAlignment(valignment);
		// 设置字体
		cs.setFont(font);
		// 设置单元格边框样式
		cs.setBorderLeft(borderStyle);
		cs.setBorderRight(borderStyle);
		cs.setBorderTop(borderStyle);
		cs.setBorderBottom(borderStyle);
		return cs;
	}
	
	/**
	 * 功能：创建带边框有背景色的CellStyle样式
	 * @param 	wb				HSSFWorkbook	
	 * @param 	backgroundColor	背景色	
	 * @param 	foregroundColor	前置色
	 * @param	font			字体
	 * @return	CellStyle
	 */
	public static CellStyle createBorderCellStyle(HSSFWorkbook wb, short alignment, short valignment, short borderStyle, short foregroundColor, short pattern, Font font){
		// 创建样式
		CellStyle cs=wb.createCellStyle();
		// 设置单元格内容对齐
		cs.setAlignment(alignment);
		cs.setVerticalAlignment(valignment);
		// 设置单元格颜色
		cs.setFillForegroundColor(foregroundColor);
		cs.setFillPattern(pattern);
		// 设置字体
		cs.setFont(font);
		// 设置单元格边框样式
		cs.setBorderLeft(borderStyle);
		cs.setBorderRight(borderStyle);
		cs.setBorderTop(borderStyle);
		cs.setBorderBottom(borderStyle);
		return cs;
	}
	
	/**
	 * 功能：创建字体
	 * @param 	wb			HSSFWorkbook
	 * @param   fontName    String
	 * @param 	boldweight	short
	 * @param 	color		short
	 * @return	Font	
	 */
	public static Font createFont(HSSFWorkbook wb, String fontName, short boldweight, short color){
		Font font=wb.createFont();
		font.setFontName(fontName);
		font.setBoldweight(boldweight);
		font.setColor(color);
		return font;
	}
	
	/**
	 * 功能：合并单元格
	 * @param 	sheet		HSSFSheet
	 * @param 	firstRow	int
	 * @param 	lastRow		int
	 * @param 	firstColumn	int
	 * @param 	lastColumn	int
	 * @return	int			合并区域号码
	 */
	public static int mergeCell(HSSFSheet sheet,int firstRow,int lastRow,int firstColumn,int lastColumn){
		return sheet.addMergedRegion(new CellRangeAddress(firstRow,lastRow,firstColumn,lastColumn));	
	}
	
	/**
	 * 设置合并单元格的边框样式
	 * @param	sheet	HSSFSheet	
	 * @param 	ca		CellRangAddress
	 * @param 	style	CellStyle
	 */
	public static void setRegionStyle(HSSFSheet sheet, CellRangeAddress ca,CellStyle style) {  
	    for (int i = ca.getFirstRow(); i <= ca.getLastRow(); i++) {  
	        HSSFRow row = HSSFCellUtil.getRow(i, sheet);  
	        for (int j = ca.getFirstColumn(); j <= ca.getLastColumn(); j++) {  
	            HSSFCell cell = HSSFCellUtil.getCell(row, j);  
	            cell.setCellStyle(style);  
	        }  
	    }  
	}  
}