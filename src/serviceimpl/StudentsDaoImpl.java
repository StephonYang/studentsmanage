package serviceimpl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import db.MyHibernateSessionFactory;
import entity.Students;
import service.StudentsDao;

//ѧ��ҵ���߼��ӿڵ�ʵ����
public class StudentsDaoImpl implements StudentsDao{

	//��ѯ����ѧ������
	@Override
	public List<Students> queryAllStudents() {
		// TODO Auto-generated method stub
		List<Students> list=null;
		String hql="";
		Transaction ts=null;
		
		try
		{
			//���session����
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

	//����ѧ����Ż�ȡѧ������ �޸�ҳ����ʾ
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
		
		s.setSid(getNewSid());//����ѧ����ѧ��
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

	//�޸�ѧ����Ϣ
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
	
	//����ѧ��ѧ��
	 private String getNewSid()
	 {
		 Transaction ts=null;
		 String hql="";
		 String sid=null;
		 try
		 {
			 Session session=MyHibernateSessionFactory.getSessionFactory().getCurrentSession();
			 ts=session.beginTransaction();
			 //��õ�ǰѧ���������
			 hql="select max(sid) from Students";
			 Query query=session.createQuery(hql);
			 
			 sid=(String) query.uniqueResult();
			 if(sid==null||"".equals(sid))
			 {
				 //��һ��Ĭ�ϵ����ֵ
				 sid="s0000001";
			 }
			 else
			 {
				 String temp=sid.substring(1);//ȡ����λ
				 int i=Integer.parseInt(temp);//ת������
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
