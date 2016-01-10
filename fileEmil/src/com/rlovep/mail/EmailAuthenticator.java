package com.rlovep.mail;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;

public class EmailAuthenticator extends Authenticator{
    private String userName="null";
    private String password="null";
    public EmailAuthenticator() {
		// TODO Auto-generated constructor stub
	}
    public EmailAuthenticator(String userName,String password)
    {
    	this.password=password;
    	this.userName=userName;
    	
    }
    //获得通行证
    protected PasswordAuthentication getPasswordAuthentication() {
		return new PasswordAuthentication(userName,password);
		
	}
}
