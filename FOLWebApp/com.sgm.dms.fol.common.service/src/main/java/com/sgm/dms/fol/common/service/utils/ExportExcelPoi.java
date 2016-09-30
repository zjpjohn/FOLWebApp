/*
 * Copyright 2015 SAIC General Motors Corporation Ltd. All Rights Reserved.
 *
 * This software is published under the terms of the SGM Software
 * License version 1.0, a copy of which has been included with this
 * distribution in the LICENSE.txt file.
 *
 * @Project Name : common.service
 *
 * @File name : ExportExcel.java
 *
 * @Author : ZhangBao
 *
 * @Date : 2015年11月12日
 *
----------------------------------------------------------------------------------
 *     Date       Who       Version     Comments
 * 1. 2015年11月12日    ZhangBao    1.0
 *
 *
 *
 *
----------------------------------------------------------------------------------
 */

/*
 *
 * @author ZhangBao
 * TODO description
 * @date 2015年11月12日
 */

package com.sgm.dms.fol.common.service.utils;

import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFClientAnchor;
import org.apache.poi.hssf.usermodel.HSSFComment;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFPatriarch;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.hssf.util.Region;

public class ExportExcelPoi<T> {

	public void exportExcels(String[] headers, List<T> data, OutputStream out) throws IOException {
		exportExcels("测试POI导出EXCEL文档", headers, data, out);
	}

	@SuppressWarnings({ "rawtypes", "deprecation" })
	public void exportExcels(String title, String[] headers, List<T> data,
			OutputStream out) throws IOException {
		// 声明一个工作薄
		HSSFWorkbook workbook = new HSSFWorkbook();
		// 生成一个表格
		HSSFSheet sheet = workbook.createSheet(title);
		// 设置表格默认列宽度为15个字节
		sheet.setDefaultColumnWidth(15);
		// 生成一个样式
		HSSFCellStyle style = workbook.createCellStyle();
		// 设置这些样式
		style.setFillForegroundColor(HSSFColor.GREY_25_PERCENT.index);
		style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		style.setBorderRight(HSSFCellStyle.BORDER_THIN);
		style.setBorderTop(HSSFCellStyle.BORDER_THIN);
		style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		// 生成一个字体
		HSSFFont font = workbook.createFont();
		font.setColor(HSSFColor.BLACK.index);
		font.setFontHeightInPoints((short) 12);
		font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		// 把字体应用到当前的样式
		style.setFont(font);
		// 生成并设置另一个样式
		HSSFCellStyle style2 = workbook.createCellStyle();
		style2.setFillForegroundColor(HSSFColor.WHITE.index);
		style2.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		style2.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		style2.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		style2.setBorderRight(HSSFCellStyle.BORDER_THIN);
		style2.setBorderTop(HSSFCellStyle.BORDER_THIN);
		style2.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		style2.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		// 生成另一个字体
		HSSFFont font2 = workbook.createFont();
		font2.setBoldweight(HSSFFont.BOLDWEIGHT_NORMAL);
		// 把字体应用到当前的样式
		style2.setFont(font2);

		// 声明一个画图的顶级管理器
		HSSFPatriarch patriarch = sheet.createDrawingPatriarch();
		// 定义注释的大小和位置,详见文档
		HSSFComment comment = patriarch.createComment(new HSSFClientAnchor(0,
				0, 0, 0, (short) 4, 2, (short) 6, 5));
		// 设置注释内容
		comment.setString(new HSSFRichTextString("可以在POI中添加注释！"));
		// 设置注释作者，当鼠标移动到单元格上是可以在状态栏中看到该内容.
		comment.setAuthor("leno");
		// 产生表格标题行
		HSSFRow row = sheet.createRow(0);
		HSSFRow row2 = sheet.createRow(1);
		for (short i = 0, n = 0; i < headers.length; i++) { // i是headers的索引，n是Excel的索引
			HSSFCell cell1 = row.createCell(n);
			cell1.setCellStyle(style);
			HSSFRichTextString text = null;
			if (headers[i].contains(":")) { // 双标题
				String[] temp = headers[i].split(":");
				text = new HSSFRichTextString(temp[0]);
				String[] tempSec = temp[1].split(",");
				sheet.addMergedRegion(new Region(0, n, 0, (short) (n
						+ tempSec.length - 1)));
				short tempI = n;
				for (int j = 0; j < tempSec.length - 1; j++) {
					HSSFCell cellT = row.createCell(++tempI);
					cellT.setCellStyle(style);
				}
				for (int j = 0; j < tempSec.length; j++) {
					HSSFCell cell2 = row2.createCell(n++);
					cell2.setCellStyle(style);
					cell2.setCellValue(new HSSFRichTextString(tempSec[j]));
				}
			} else { // 单标题
				HSSFCell cell2 = row2.createCell(n);
				cell2.setCellStyle(style);
				text = new HSSFRichTextString(headers[i]);
				sheet.addMergedRegion(new Region(0, n, 1, n));
				n++;
			}
			cell1.setCellValue(text);
		}

		// 遍历集合数据，产生数据行
		for (int i = 0, index = 2; i < data.size(); i++, index++) {
			row = sheet.createRow(index);
			T t = data.get(i);
			Class obj = data.get(i).getClass();
			Field[] fields = obj.getDeclaredFields();
			for (int j = 0; j < fields.length; j++) {
				HSSFCell cell = row.createCell(j);
				Field field = fields[j];
				String fieldName = field.getName();

				String getMethodName = "get"
						+ fieldName.substring(0, 1).toUpperCase()
						+ fieldName.substring(1);
				try {
					@SuppressWarnings("unchecked")
					Method getMethod = obj.getMethod(getMethodName,
							new Class[] {});
					Object value = getMethod.invoke(t, new Object[] {});

					String textValue = null;
					// if (value instanceof Integer) {
					// int intValue = (Integer) value;
					// cell.setCellValue(intValue);
					// } else if (value instanceof Float) {
					// float fValue = (Float) value;
					// textValue = new HSSFRichTextString(
					// String.valueOf(fValue));
					// cell.setCellValue(textValue);
					// } else if (value instanceof Double) {
					// double dValue = (Double) value;
					// textValue = new HSSFRichTextString(
					// String.valueOf(dValue));
					// cell.setCellValue(textValue);
					// } else if (value instanceof Long) {
					// long longValue = (Long) value;
					// cell.setCellValue(longValue);
					// }

					if (value instanceof Date) {
						// Date date = (Date) value;
						// SimpleDateFormat sdf = new SimpleDateFormat(pattern);
						// textValue = sdf.format(date);
					} else if (value instanceof byte[]) {
						// 有图片时，设置行高为60px;
						row.setHeightInPoints(60);
						// 设置图片所在列宽度为80px,注意这里单位的一个换算
						sheet.setColumnWidth(i, (short) (35.7 * 80));
						// sheet.autoSizeColumn(i);
						byte[] bsValue = (byte[]) value;
						HSSFClientAnchor anchor = new HSSFClientAnchor(0, 0,
								1023, 255, (short) 6, index, (short) 6, index);
						anchor.setAnchorType(2);
						patriarch.createPicture(anchor, workbook.addPicture(
								bsValue, HSSFWorkbook.PICTURE_TYPE_JPEG));
					} else {
						// 其它数据类型都当作字符串简单处理
						if (value == null) {
							textValue = "";
						} else {
							textValue = value.toString();
						}
					}
					// 如果不是图片数据，就利用正则表达式判断textValue是否全部由数字组成
					if (textValue != null) {
						Pattern p = Pattern.compile("^//d+(//.//d+)?$");
						Matcher matcher = p.matcher(textValue);
						if (matcher.matches()) {
							// 是数字当作double处理
							cell.setCellValue(Double.parseDouble(textValue));
						} else {

							cell.setCellStyle(style2);
							HSSFRichTextString richString = new HSSFRichTextString(
									textValue);
							cell.setCellValue(richString);
						}
					}
				} catch (Exception e) {
					e.printStackTrace();
				}finally{
					out.flush();
					out.close();
				}

			}
		}
		/*
		 * for (int i = 0; i < data.size(); i++ ) { data.get(i) HSSFCell cell =
		 * row.createCell(i); cell.setCellStyle(style2); HSSFRichTextString
		 * richString = new HSSFRichTextString(); cell.setCellValue(richString);
		 * }
		 */
		try {
			workbook.write(out);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
