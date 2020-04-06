package com.jsp.ibatis.config;

import java.io.IOException;
import java.io.Reader;
import java.nio.charset.Charset;

import com.ibatis.common.resources.Resources;
import com.ibatis.sqlmap.client.SqlMapClient;
import com.ibatis.sqlmap.client.SqlMapClientBuilder;

/*
 * Mapoper(member.xml)파일을 sql문을 수행하기 위해서
 * SqlMapClient 객체를 생성 한다.
 * 
 * SqlMapClient 객체 생성시 환경설정파일(SqlMpaConfig.xml)을 재료로 한다.
 */
public class SqlMapClientFactory {
	
	private static SqlMapClient smc;
	
	static {
		try {
//			1-1. xml문서 읽어오기
//			설정파일의 인코딩 설정
			Charset charset = Charset.forName("UTF-8");
			Resources.setCharset(charset);
			Reader rd = Resources.getResourceAsReader("com/jsp/ibatis/config/SqlMapConfig.xml");
			
//			1-2. 위에서 읽어온 Reader객체를 이용하여 실제 작업을 진행할 객체 생성
			smc = SqlMapClientBuilder.buildSqlMapClient(rd);
			
		} catch (IOException e) {
			System.out.println("SqlMapClient 객체 생성 실패");
			e.printStackTrace();
		}
	}
	
	public static SqlMapClient getSMClient() {
		return smc;
	}
}
