package com.huizhongcf.util;

import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
/**
 * 
 *
 * Description:创建并填充Excel数据工具类
 *
 * @author lijie 
 * @version 1.0
 * <pre>
 * Modification History: 
 * Date         Author      Version     Description 
 * ------------------------------------------------------------------ 
 * 2015年11月20日      lijie       1.0         1.0 Version 
 * </pre>
 */
public class PoiExcelUtil {
	
	//创建一个workbook
	private static HSSFWorkbook hssfwk;
	//创建sheet
	private static HSSFSheet sheet;

	/**
	 * 
	 * Description: 			创建并生成Excel
	 * @param sheetName			自定义sheet名称
	 * @param colNames			自定义excel没列的名称
	 * @param dataList			填充到excel中的数据
	 * @param fileName			自定义excel名称
	 * @param out				将数据写入到输出流
	 * @Author lijie
	 * @Create Date: 2015年11月20日 下午4:37:53
	 */
	public static void createExportFile(String sheetName, List<String> header, List<?> dataList, String fileName, OutputStream out){
		hssfwk = new HSSFWorkbook();
		init(sheetName, header);
		try {
			processData(hssfwk, sheet, dataList);
			//将Excel存储到指定的目录
			try{
				hssfwk.write(out);
			}catch(IOException e){
				e.printStackTrace();
			}
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
	}
	/**
	 * 
	 * Description: 创建并初始化Excel
	 *
	 * @param sheetName
	 * @param colNames
	 * @Author lijie
	 * @Create Date: 2015年11月20日 下午4:38:43
	 */
	private static void init(String sheetName, List<String> header){
		//创建Excel Sheet
		sheet = hssfwk.createSheet(sheetName);
		//创建表头
		HSSFRow row = sheet.createRow(0);
		row.setHeightInPoints(20);
		HSSFCellStyle cellStyle = hssfwk.createCellStyle();
		cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);	//设置单元格居中
		for(int i=0;i<header.size();i++){
			sheet.setColumnWidth(i, 30*256);
			//创建单元格
			HSSFCell cell = row.createCell(i);
			cell.setCellValue(header.get(i));
			cell.setCellStyle(cellStyle);
		}
	}
	/**
	 * 
	 * Description: 将数据添加到Excel中
	 *
	 * @param hssfwk
	 * @param sheet
	 * @param dataList
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 * @Author lijie
	 * @Create Date: 2015年11月20日 下午4:38:54
	 */
	private static void processData(HSSFWorkbook hssfwk , 
			HSSFSheet sheet,List<?> dataList) throws IllegalArgumentException, IllegalAccessException{
		for(int i=0;i<dataList.size();i++){
			Object obj = dataList.get(i);
			//创建表头下面的单元行
			HSSFRow row = sheet.createRow(i+1);
			//反射得到传入进来数据的对象类型
			Class<?> clazz = dataList.get(i).getClass();
			//获取此对象的所有方法
			Field[] fields = clazz.getDeclaredFields();
			for(int j=0;j<fields.length;j++){
				fields[j].setAccessible(true);
				//创建此行下面的单元格
				HSSFCell cell = row.createCell(j);
				try{
					if(StringUtil.isNotBlank(fields[j].get(obj).toString())){
						cell.setCellValue(fields[j].get(obj).toString());
					}else{
						cell.setCellValue("");
					}
				}catch(Exception e){
					cell.setCellValue("");
				}
			}
		}
		
	}
}
