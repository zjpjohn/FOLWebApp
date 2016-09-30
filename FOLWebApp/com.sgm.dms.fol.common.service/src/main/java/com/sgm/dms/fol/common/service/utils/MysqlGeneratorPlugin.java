
/**
 * Copyright 2016 SAIC General Motors Corporation Ltd. All Rights Reserved.
 *
 * This software is published under the terms of the SGM Software
 * License version 1.0, a copy of which has been included with this
 * distribution in the LICENSE.txt file.
 *
 * @Project Name : common.service
 *
 * @File name : MysqlGeneratorPlugin.java
 *
 * @Author : wangfl
 *
 * @Date : 2016年8月16日
 *
 ----------------------------------------------------------------------------------
 *     Date       Who       Version     Comments
 * 1. 2016年8月16日    wangfl    1.0
 *
 *
 *
 *
 ----------------------------------------------------------------------------------
 */
	
package com.sgm.dms.fol.common.service.utils;

import java.util.List;

import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.PluginAdapter;
import org.mybatis.generator.api.dom.java.TopLevelClass;
import org.mybatis.generator.api.dom.xml.XmlElement;
import org.mybatis.generator.api.CommentGenerator;
import org.mybatis.generator.api.dom.java.Field;
import org.mybatis.generator.api.dom.java.JavaVisibility;
import org.mybatis.generator.api.dom.java.Method;
import org.mybatis.generator.api.dom.java.Parameter;
import org.mybatis.generator.api.dom.java.PrimitiveTypeWrapper;
import org.mybatis.generator.api.dom.xml.Attribute;
import org.mybatis.generator.api.dom.xml.TextElement;


/**
 * Mybatis Dao层自动生成插件
 * @author wangfl
 * @date 2016年8月16日
 */

public class MysqlGeneratorPlugin extends PluginAdapter {
	
	

	@Override
	public boolean modelExampleClassGenerated(TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
		// add field, getter, setter for limit clause
		addLimit(topLevelClass, introspectedTable, "beginNo");
		addLimit(topLevelClass, introspectedTable, "endNo");
		return super.modelExampleClassGenerated(topLevelClass, introspectedTable);
	}
	
	/**
	 * selectByExample
	 * @author wangfl
	 * @date 2016年8月16日
	 * @param element
	 * @param introspectedTable
	 * @return
	 * (non-Javadoc)
	 * @see org.mybatis.generator.api.PluginAdapter#sqlMapSelectByExampleWithoutBLOBsElementGenerated(org.mybatis.generator.api.dom.xml.XmlElement, org.mybatis.generator.api.IntrospectedTable)
	 */
	@Override
	public boolean sqlMapSelectByExampleWithoutBLOBsElementGenerated(XmlElement element,
			IntrospectedTable introspectedTable) {
		XmlElement pageBeginEle = new XmlElement("if");
		pageBeginEle.addAttribute(new Attribute("test", "beginNo != null and endNo != null and endNo > beginNo")); //$NON-NLS-1$ //$NON-NLS-2$
		pageBeginEle.addElement(new TextElement("select T2.* from (select T1.*,ROWNUM row_num from ("));
		element.addElement(0, pageBeginEle);
		
		XmlElement pageEndEle = new XmlElement("if");
		pageEndEle.addAttribute(new Attribute("test", "beginNo != null and endNo != null and endNo > beginNo")); //$NON-NLS-1$ //$NON-NLS-2$
		pageEndEle.addElement(new TextElement(") T1"
				                                   + " where ROWNUM &lt;= #{endNo}) T2"
				                                   + " where T2.row_num &gt;= #{beginNo}"));
		element.addElement(pageEndEle);
		return super.sqlMapUpdateByExampleWithoutBLOBsElementGenerated(element, introspectedTable);
	}




	private void addLimit(TopLevelClass topLevelClass, IntrospectedTable introspectedTable, String name) {
		CommentGenerator commentGenerator = context.getCommentGenerator();
		
		Field field = new Field();
		field.setVisibility(JavaVisibility.PROTECTED);
		field.setType(PrimitiveTypeWrapper.getIntegerInstance());
		field.setName(name);
		
		// field.setInitializationString("-1");
		commentGenerator.addFieldComment(field, introspectedTable);
		topLevelClass.addField(field);
		
		char c = name.charAt(0);
		String camel = Character.toUpperCase(c) + name.substring(1);
		Method method = new Method();
		method.setVisibility(JavaVisibility.PUBLIC);
		method.setName("set" + camel);
		method.addParameter(new Parameter(PrimitiveTypeWrapper.getIntegerInstance(), name));
		method.addBodyLine("this." + name + "=" + name + ";");
		commentGenerator.addGeneralMethodComment(method, introspectedTable);
		topLevelClass.addMethod(method);
		method = new Method();
		method.setVisibility(JavaVisibility.PUBLIC);
		method.setReturnType(PrimitiveTypeWrapper.getIntegerInstance());
		method.setName("get" + camel);
		method.addBodyLine("return " + name + ";");
		commentGenerator.addGeneralMethodComment(method, introspectedTable);
		topLevelClass.addMethod(method);
	}
	/**
	 * @author wangfl
	 * @date 2016年8月16日
	 * @param arg0
	 * @return
	 * (non-Javadoc)
	 * @see org.mybatis.generator.api.Plugin#validate(java.util.List)
	 */

	@Override
	public boolean validate(List<String> arg0) {
		return true;
	}

}
