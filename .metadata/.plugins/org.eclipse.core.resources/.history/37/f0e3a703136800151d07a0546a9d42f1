package com.rlovep.dom4j;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

import javax.sql.rowset.spi.XmlWriter;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

public class CopyXml {
	
	public static void main(String[] args) {
		try {
			Document doc = new SAXReader().read("./src/student.xml");
			FileOutputStream out=new FileOutputStream("./src/copyStu.xml");
		
			 //指定格式，选为经凑型，没有空格和换行符，一整行表示所有内容，代码上线用
			OutputFormat format=OutputFormat.createCompactFormat();
			//指定格式，选为漂亮型。有空格和换行符，调试用
			OutputFormat format2 = OutputFormat.createPrettyPrint();
			/*
			 * 指定生成的xml文档的编码
			 *    同时影响了xml文档保存时的编码  和  xml文档声明的encoding的编码（xml解析时的编码）
			 *    结论： 使用该方法生成的xml文档避免中文乱码问题。
			 */
			format.setEncoding("utf-8");
			format2.setEncoding("utf-8");
            //创建写出对象
			XMLWriter writer=new XMLWriter(out, format);
			//写出对象
			writer.write(doc);
			//关闭流
			writer.close();
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
