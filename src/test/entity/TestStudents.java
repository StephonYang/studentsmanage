package test.entity;

import java.util.Date;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;
import org.hibernate.tool.hbm2ddl.SchemaExport;
import org.junit.Test;

import entity.Students;

public class TestStudents {
	
	@Test
	public void testSchemaExport()
	{
		//创建配置对象
		Configuration config=new Configuration().configure();
		
		//创建服务注册对象
//		ServiceRegistry serviceRegistry=new ServiceRegistryBuilder().applySettings(config.getProperties()).buildServiceRegistry();
//		
		//创建sessionfactory
//		SessionFactory sessionFactory=config.buildSessionFactory(serviceRegistry);
//		
		//获得session对象
//		Session session=sessionFactory.getCurrentSession();
		
		//创建SchemaExport对象
		SchemaExport export=new SchemaExport(config);
		
		export.create(true, true);
	}
	
	//添加测试数据
	@Test
	public void testSaveStudents()
	{
        Configuration config=new Configuration().configure();
		
		ServiceRegistry serviceRegistry=new ServiceRegistryBuilder().applySettings(config.getProperties()).buildServiceRegistry();
		
		SessionFactory sessionFactory=config.buildSessionFactory(serviceRegistry);
		
		Session session=sessionFactory.getCurrentSession();
		
		Transaction ts=session.beginTransaction();
		
		Students s1=new Students("s0000001", "张三", "男", new Date(), "629");
		Students s2=new Students("s0000002", "张四", "男", new Date(), "629");
		Students s3=new Students("s0000003", "张五", "男", new Date(), "629");
		
		session.save(s1);
		session.save(s2);
		session.save(s3);
		
		ts.commit();
		sessionFactory.close();
	}

}
