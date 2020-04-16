package com.jsp.mybatis;

import java.io.Reader;
import java.sql.Connection;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.ibatis.session.TransactionIsolationLevel;


public class OracleMyBatisSqlSessionFactory implements SqlSessionFactory {
	
	private static SqlSessionFactory sqlSessionFactory;
	
	static {
		
		String config="com/jsp/mybatis/sqlConfig.xml";
		
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

	@Override
	public Configuration getConfiguration() {
		return sqlSessionFactory.getConfiguration();
	}

	@Override
	public SqlSession openSession() {
		return sqlSessionFactory.openSession();
	}

	@Override
	public SqlSession openSession(boolean autoComit) {
		return sqlSessionFactory.openSession(autoComit);
	}

	@Override
	public SqlSession openSession(Connection conn) {
		return sqlSessionFactory.openSession(conn);
	}

	@Override
	public SqlSession openSession(TransactionIsolationLevel tranLevel) {
		return sqlSessionFactory.openSession(tranLevel);
	}

	@Override
	public SqlSession openSession(ExecutorType exeType) {
		return sqlSessionFactory.openSession(exeType);
	}

	@Override
	public SqlSession openSession(ExecutorType exeType, boolean autoCommit) {
		return sqlSessionFactory.openSession(exeType, autoCommit);
	}

	@Override
	public SqlSession openSession(ExecutorType exeType, TransactionIsolationLevel tranLevel) {
		return sqlSessionFactory.openSession(exeType, tranLevel);
	}

	@Override
	public SqlSession openSession(ExecutorType exeType, Connection conn) {
		return sqlSessionFactory.openSession(exeType, conn);
	}
}
