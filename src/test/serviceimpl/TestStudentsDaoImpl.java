package test.serviceimpl;

import java.util.Date;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import entity.Students;
import service.StudentsDao;
import serviceimpl.StudentsDaoImpl;

public class TestStudentsDaoImpl {
	@Test
	public void testQueryStudents()
	{
		StudentsDao sdao=new StudentsDaoImpl();
		List<Students> list=sdao.queryAllStudents();
		for(int i=0;i<list.size();i++)
		{
			System.out.println(list.get(i));
		}
	}
	
//	@Test
//	public void testGetNewId()
//	{
//		StudentsDaoImpl sdaoi=new StudentsDaoImpl();
//		System.out.println(sdaoi.getNewSid());
//	}
	
	@Test
	public void testAddStudents()
	{
		Students s=new Students();
		s.setSname("张三丰");
	    s.setGender("男");
	    s.setBirthday(new Date());
	    s.setAddress("武当山");
	    StudentsDao sdao=new StudentsDaoImpl();
	    
	    Assert.assertEquals(true, sdao.addStudents(s));
	}

}
