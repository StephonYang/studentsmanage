package action;

import org.apache.struts2.interceptor.validation.SkipValidation;

import com.opensymphony.xwork2.ModelDriven;

import entity.Users;
import service.UsersDao;
import serviceimpl.UsersDaoImpl;

public class UsersAction extends SuperAction implements ModelDriven<Users>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Users user=new Users();
	
	public String login()
	{
		UsersDao udao=new UsersDaoImpl();
		if(udao.usersLogin(user))
		{
			//在session中保存登陆成功的用户名
			session.setAttribute("loginUserName", user.getUsername());
			
			return "login_success";
		}
		else
		{
			return "login_failure";
		}
	}
	//用户注销方法
	//注销时 不进行表单验证
	@SkipValidation
	public String logout()
	{
		if(session.getAttribute("loginUserName")!=null)
		{
			session.removeAttribute("loginUserName");
		}
		return "logout_success";
	}
	
	
	//重写表单验证方法
	@Override
	public void validate() {
		// TODO Auto-generated method stub
		//super.validate();
		//检查用户名不能为空
		if("".equals(user.getUsername().trim()))
		{
			this.addFieldError("usernameError", "用户名不能为空");
		}
		if(user.getPassword().length()<6)
		{
			this.addFieldError("passwordError", "密码长度不小于6位");
		}
	}
	@Override
	public Users getModel() {
		// TODO Auto-generated method stub
		return this.user;
	}
	

}
