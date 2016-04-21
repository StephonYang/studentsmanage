package db;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;
import org.hibernate.tool.hbm2ddl.SchemaExport;

public class MyHibernateSessionFactory {
	private static SessionFactory sessionFactory;
	
	//���췽��˽�л�����֤����ģʽ
	private MyHibernateSessionFactory()
	{
		
	}
	
	//���о�̬��������ûỰ����
	public static SessionFactory getSessionFactory()
	{
		if(sessionFactory==null)
		{
			//�������ö���
			Configuration config=new Configuration().configure();
			
			//����ע�����
			ServiceRegistry serviceRegistry=new ServiceRegistryBuilder().applySettings(config.getProperties()).buildServiceRegistry();
			
			//��ûỰ����
			SessionFactory sessionFactory=config.buildSessionFactory(serviceRegistry);
			
			//Session session=sessionFactory.getCurrentSession();
            
			return sessionFactory;
		}
		else
		{
			return sessionFactory;
		}
	}
	

}
