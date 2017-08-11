package hibernate;



import java.util.Date;



import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class StudentsTest {
	
	private SessionFactory sessionFactory;
	private Session session;
	private Transaction transaction;
	
	@Before
	public void init(){
		//配置对象
		Configuration config=new Configuration().configure();
		//服务注册对象
		org.hibernate.service.ServiceRegistry serviceRegistry=new ServiceRegistryBuilder().applySettings(config.getProperties()).buildServiceRegistry();
		//创建回话工厂
		sessionFactory = config.buildSessionFactory(serviceRegistry);
		//创建会话对象
		session =sessionFactory.openSession();
		//开启事务
		transaction=session.beginTransaction();
		
		
	}
	@After
    public void destory(){
		transaction.commit();//提交事务
		session.close();  //关闭会话
		sessionFactory.close(); //关闭会话工厂
	}

	
	@Test
	public void testSaveStudents(){
		Students s=new Students(1,"孙悟空",new Date());
		
		session.save(s);
	
	}
	
}
