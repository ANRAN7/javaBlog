package com.rlovep.jndi;

import java.util.Properties;

import javax.naming.InitialContext;

public class Publish {

    public static void main(String[] args) {
        //
        //This example creates a subcontext in a namespace
        //
        try{
            Properties prop = new Properties();
            prop.setProperty("java.naming.factory.initial",
              "com.sun.jndi.cosnaming.CNCtxFactory");
            prop.setProperty("java.naming.provider.url",
              "iiop://localhost:900");
            InitialContext ic = new InitialContext(prop);
            ic.createSubcontext("Test");
        }catch(Exception e){
            System.out.println(e);
            e.printStackTrace();
            
        }
    }
}