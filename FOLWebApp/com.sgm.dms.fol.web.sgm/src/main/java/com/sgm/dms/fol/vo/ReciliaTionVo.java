/*
* Copyright 2015 SAIC General Motors Corporation Ltd. All Rights Reserved.
*
* This software is published under the terms of the SGM Software
* License version 1.0, a copy of which has been included with this
* distribution in the LICENSE.txt file.
*
* @Project Name : web.sgm
*
* @File name : ReciliaTionVo.java
*
* @Author : ZhangBao
*
* @Date : 2015年11月4日
*
----------------------------------------------------------------------------------
*     Date       Who       Version     Comments
* 1. 2015年11月4日    ZhangBao    1.0
*
*
*
*
----------------------------------------------------------------------------------
*/
	
package com.sgm.dms.fol.vo;

import org.hibernate.validator.constraints.NotBlank;

import com.sgm.dms.fol.common.service.annotation.FileType;

/*
 *
 * @author ZhangBao
 * TODO description
 * @date 2015年11月4日
 */

public class ReciliaTionVo {
	private String filedId;//文件id
	@FileType(fileType = "txt,zip", message="文件类型不对，只能上传格式为txt和zip的文件")
	private String fileName;//文件名称
	private String filePath;//文件路径
	private String effectDate;//生效日期
	@NotBlank(message="账单日期不能为空")
	private String billDate;//账单日期

	public String getFiledId() {
		return filedId;
	}

	public void setFiledId(String filedId) {
		this.filedId = filedId;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public String getEffectDate() {
		return effectDate;
	}

	public void setEffectDate(String effectDate) {
		this.effectDate = effectDate;
	}

	public String getBillDate() {
		return billDate;
	}

	public void setBillDate(String billDate) {
		this.billDate = billDate;
	}

	@Override
	public String toString() {
		return "ReciliaTionVo [fileName=" + fileName + ", filePath=" + filePath
				+ ", effectDate=" + effectDate + ", billDate=" + billDate + "]";
	}
		
	

}
