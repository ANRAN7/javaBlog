package com.rlovep.type;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import org.apache.struts2.util.StrutsTypeConverter;


/**
 * 
* @ClassName: MyConverter
* @Description: 自定义的转换器对Data进行转换，与BeanUils中的自定义转换器类似
* @author peace w_peace@163.com 
* @date 26 Dec 2015 3:54:36 pm
*
 */
public class MyConverter extends StrutsTypeConverter{
    DateFormat[] df={new SimpleDateFormat("yyyy-MM-dd"),new SimpleDateFormat("yyyyMMdd"),
    		new SimpleDateFormat("yyyy年MM月dd日")
    };
    
     /*
      * (非 Javadoc) 
     * <p>Title: convertFromString</p> 
     * <p>Description:把String转换为指定的类型，此去使用的是String to date </p> 
     * @param context  当前上下文环境
     * @param values  Jsp表单提交的字符串的值
     * @param toclass  要转换的目标类型
     * @return  返回准换后的对象
     * @see org.apache.struts2.util.StrutsTypeConverter#convertFromString(java.util.Map, java.lang.String[], java.lang.Class)
      */
	@Override
	public Object convertFromString(Map context, String[] values, Class toclass) {
		//判断：内容不能为空
		if(values==null|| values.length==0)
			return null;
	    //判断类型必须为Data 
		if(Date.class!=toclass)
	    	 return null;
	     //迭代转换：转换失败继续下一个格式的转换；转换成功就直接返回
		for(int i=0;i<df.length;i++){
			try{
				return df[i].parse(values[0]);
			}catch(ParseException e){
				continue;
			}
		}
		return null;
	}

	@Override
	public String convertToString(Map arg0, Object arg1) {
		return null;
	}

}
