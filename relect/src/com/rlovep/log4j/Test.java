package com.rlovep.log4j;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class Test {
    static Log log=LogFactory.getLog(Test.class);
    public static void main(String[] args) {
    	try {
			log.info("保存： 开始进入保存方法");

			int i = 1/0;
			
			log.info("保存： 执行保存结束，成功");
		} catch (Exception e) {
			
			log.error("执行App类Save()方法出现异常！");  // 异常
			
			e.printStackTrace();
		}
    	
    	// 输出不同级别的提示
    			log.debug("调试信息");
    			log.info("信息提示");
    			log.warn("警告");
    			log.error("异常");
		
	}
}
