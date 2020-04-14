package mybatis;

import java.io.Reader;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import sun.misc.Resource;

public class OracleMyBatisSqlSessionFactoryBuilder {
	
	// 생성자가 만들어지는일이 없도록 잠군다.
	private OracleMyBatisSqlSessionFactoryBuilder() {}
	
	private static SqlSessionFactory sqlSessionFactory;
	
	static {
		
		String config="mybatis/mybatisConfig.xml";
		
		try {
			
			Reader reader = Resources.getResourceAsReader(config);
			
			sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
			
			// reader.close();
			
			System.out.println("sqlSessionFactory성공");
			
		} catch (Exception e) {
			System.out.println("sqlSessionFactory실패");
			e.printStackTrace();
		}
	}

	public static SqlSessionFactory getSqlSessionFactory() {
		return sqlSessionFactory;
	}
}
