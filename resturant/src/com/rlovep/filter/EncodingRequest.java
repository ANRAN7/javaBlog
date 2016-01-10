package com.rlovep.filter;

import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

public class EncodingRequest extends HttpServletRequestWrapper{
    private HttpServletRequest request;
	public EncodingRequest(HttpServletRequest request) {
		super(request);
		this.request=request;
	}
	@Override
   public String getParameter(String name)
   {
	   String value=request.getParameter(name);
	   if(value!=null)
	   {
		   try {
			value=new String(value.getBytes("iso-8859-1"),"utf-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
	   }
	   return value;
   }
}
