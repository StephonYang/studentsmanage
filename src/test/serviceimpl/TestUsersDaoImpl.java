package test.serviceimpl;

import org.junit.Assert;
import org.junit.Test;

import entity.Users;
import service.UsersDao;
import serviceimpl.UsersDaoImpl;

public class TestUsersDaoImpl {
	@Test
	public void testUsersLogin()
	{
		Users u=new Users(1,"zhangsan","123456");
		UsersDao udao=new UsersDaoImpl();
		Assert.assertEquals(true, udao.usersLogin(u));
		
	}

}
