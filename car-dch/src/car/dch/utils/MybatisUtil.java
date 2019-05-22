package car.dch.utils;

import java.io.InputStream;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class MybatisUtil {
	
	/**
	 * 打开SqlSession
	 * @return
	 */
	public static SqlSession getSession(){
		String resource = "MybatisConfig.xml";
		InputStream is = MybatisUtil.class.getClassLoader().getResourceAsStream(resource);
		SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(is);
		SqlSession session = sessionFactory.openSession();
		return session;
	}
	
	/**
	 * 关闭SqlSession
	 * @param session
	 */
	public static void close(SqlSession session){
		if(session != null){
			session.close();
		}
	}
	
	/**
	 * 测试数据库连接
	 * @param args
	 */
	public static void main(String[] args){
		SqlSession session = getSession();
		System.out.println("数据库连接成功");
		close(session);
		System.out.println("数据库关闭成功");
	}

}
