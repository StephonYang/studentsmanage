package serviceimpl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import db.MyHibernateSessionFactory;
import entity.Students;
import service.StudentsDao;

//学生业务逻辑接口的实现类
public class StudentsDaoImpl implements StudentsDao{

	//查询所有学生资料
	@Override
	public List<Students> queryAllStudents() {
		// TODO Auto-generated method stub
		List<Students> list=null;
		String hql="";
		Transaction ts=null;
		
		try
		{
			//获得session对象
			Session session=MyHibernateSessionFactory.getSessionFactory().getCurrentSession();
			ts=session.beginTransaction();
			hql="from Students";
			Query query=session.createQuery(hql);
			list=query.list();
			ts.commit();
			return list;
			
		}
		catch(Exception ex )
		{
			ex.printStackTrace();
			ts.commit();
			return list;
		}
		finally
		{
			if(ts!=null)
			{
				ts=null;
			}
		}
		
		
		
		
		
	}

	//根据学生编号获取学生资料 修改页面显示
	@Override
	public Students queryStudentsBySid(String sid) {
		// TODO Auto-generated method stub
		Students s=null;
		
		Transaction ts=null;
		
		try
		{
			Session session=MyHibernateSessionFactory.getSessionFactory().getCurrentSession();
			ts=session.beginTransaction();
			s=(Students) session.get(Students.class, sid);
			
			
			ts.commit();
			return s;
			
		}
		catch(Exception ex )
		{
			ex.printStackTrace();
			ts.commit();
			return s;
		}
		finally
		{
			if(ts!=null)
			{
				ts=null;
			}
		}
	}

	@Override
	public boolean addStudents(Students s) {
		// TODO Auto-generated method stub
		
		s.setSid(getNewSid());//设置学生的学号
		Transaction ts=null;
		try
		{
			Session session=MyHibernateSessionFactory.getSessionFactory().getCurrentSession();
		    ts=session.beginTransaction();
		    session.save(s);
		    ts.commit();
		    return true;
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
			ts.commit();
			return false;
		}
		finally
		{
			if(ts!=null)
			{
				ts=null;
			}
		}
	
		
	}

	//修改学生信息
	@Override
	public boolean updateStudents(Students s) {
		// TODO Auto-generated method stub
		Transaction ts=null;
		try
		{
			Session session=MyHibernateSessionFactory.getSessionFactory().getCurrentSession();
			ts=session.beginTransaction();
			session.update(s);
			ts.commit();
			return true;
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
			ts.commit();
			return false;
		}
		finally
		{
			
		}
		
	}

	@Override
	public boolean deleteStudents(String sid) {
		// TODO Auto-generated method stub
		
		Transaction ts=null;
		
		try
		{
			Session session=MyHibernateSessionFactory.getSessionFactory().getCurrentSession();
			ts=session.beginTransaction();
			Students s=(Students) session.get(Students.class, sid);
			session.delete(s);
			ts.commit();
			return true;
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
			ts.commit();
			return false;
		}
		finally
		{
			if(ts!=null)
			{
				ts=null;
			}
		}
		
	}
	
	//生成学生学号
	 private String getNewSid()
	 {
		 Transaction ts=null;
		 String hql="";
		 String sid=null;
		 try
		 {
			 Session session=MyHibernateSessionFactory.getSessionFactory().getCurrentSession();
			 ts=session.beginTransaction();
			 //获得当前学生的最大编号
			 hql="select max(sid) from Students";
			 Query query=session.createQuery(hql);
			 
			 sid=(String) query.uniqueResult();
			 if(sid==null||"".equals(sid))
			 {
				 //给一个默认的最大值
				 sid="s0000001";
			 }
			 else
			 {
				 String temp=sid.substring(1);//取后七位
				 int i=Integer.parseInt(temp);//转成数字
				 i++;
				 temp=String.valueOf(i);
				 int len=temp.length();
				 for(int j=0;j<7-len;j++)
				 {
					 temp="0"+temp;
				 }
				 sid="s"+temp;
				 
			 }
			 ts.commit();
			 return sid;
		 }
		 catch(Exception ex)
		 {
			 ex.printStackTrace();
			 ts.commit();
			 return null;
		 }
		 finally
		 {
			 if(ts!=null)
			 {
				 ts=null;
			 }
		 }
	 }

}
