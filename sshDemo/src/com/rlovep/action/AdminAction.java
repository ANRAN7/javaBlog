package com.rlovep.action;

import org.apache.catalina.SessionListener;
import org.apache.log4j.Logger;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.rlovep.entity.Admin;
import com.rlovep.service.IAdminService;
/**
 * 
* @ClassName: AdminAction
* @Description: 继承ActionSupport类，和实现ModelDriver接口
* @author peace w_peace@163.com 
* @date 20 Jan 2016 3:11:40 pm
*
 */
public class AdminAction extends ActionSupport implements ModelDriven<Admin>{

	/**
	* @Fields serialVersionUID : TODO
	*/
	
	private static final long serialVersionUID = 1L;
    private Admin admin=new Admin();
    private IAdminService adminService;
    //日志文件：
   private static Logger log=Logger.getLogger(AdminAction.class);
    //使用spring注入属性
	public void setAdminService(IAdminService adminService) {
		this.adminService = adminService;
	}
	@Override
	public Admin getModel() {
		return admin;
	}
    
    public String login()  {
    	//登陆验证
    	Admin adminInfo = adminService.login(admin);
    	//验证
    	if(adminInfo!=null){
    		//登陆成功。保存数据到session
    		ActionContext.getContext().getSession().put("adminInfo",adminInfo);
    		log.warn("登陆成功");
    		return "index";
    	}
    	//登陆失败
    	return "loginFaild";
    }
}
