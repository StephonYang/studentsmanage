package db;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;
import org.hibernate.tool.hbm2ddl.SchemaExport;

public class MyHibernateSessionFactory {
	private static SessionFactory sessionFactory;
	
	//构造方法私有化。保证单例模式
	private MyHibernateSessionFactory()
	{
		
	}
	
	//公有静态方法，获得会话工厂
	public static SessionFactory getSessionFactory()
	{
		if(sessionFactory==null)
		{
			//生成配置对象
			Configuration config=new Configuration().configure();
			
			//服务注册对象
			ServiceRegistry serviceRegistry=new ServiceRegistryBuilder().applySettings(config.getProperties()).buildServiceRegistry();
			
			//获得会话工程
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
