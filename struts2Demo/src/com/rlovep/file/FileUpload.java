package com.rlovep.file;

import java.io.File;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

public class FileUpload extends ActionSupport{	
	private static final long serialVersionUID = 1L;
	//对应表单:<input type="file" name="file1">
	private File file1;
	//前缀为文件file1。文件的类型
	private String file1ContentType;
	//前缀为文件file1。。文件名的属性
	private String file1FileName;
	public void setFile1(File file1) {
		this.file1 = file1;
	}
	public void setFile1FileName(String file1FileName) {
		this.file1FileName = file1FileName;
	}
	public void setFile1ContentType(String file1ContentType) {
		this.file1ContentType = file1ContentType;
	}
	@Override
	public String execute() throws Exception {
		System.out.println(file1ContentType);
		System.out.println(file1FileName);

		//把文件上传到upload目录
		String path = ServletActionContext.getServletContext().getRealPath("/upload");
		//创建目标对象
		File destFile=new File(path,file1FileName);
		//把上传的文件拷贝到目标文件中；
		
		FileUtils.copyFile(file1, destFile);
		return SUCCESS;
	}
}
