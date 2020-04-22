package com.jsp.listener;

import java.lang.reflect.Method;
import java.util.Enumeration;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import com.jsp.action.ApplicationContext;

@WebListener
public class InitListener implements ServletContextListener {

	public void contextDestroyed(ServletContextEvent ctxEvent) {
		// TODO Auto-generated method stub
	}

	public void contextInitialized(ServletContextEvent ctxEvent) {

		Map<String, Object> applicationContext = ApplicationContext.getApplicationContext();

		ServletContext ctx = ctxEvent.getServletContext();

		// 1. 인스턴스 생성
		Enumeration<String> paramNames = ctx.getInitParameterNames();
		
		while (paramNames.hasMoreElements()) {
			String paramName = paramNames.nextElement(); // service
			String classType = ctx.getInitParameter(paramName);
			try {
				Class<?> cls = Class.forName(classType);

				Object targetObj = cls.newInstance();

				applicationContext.put(paramName, targetObj);

			} catch (Exception e) {
				e.printStackTrace();
			}

		}
		// 재할당을 해줘야 처음부터 다시 읽기가 가능하다.
		paramNames = ctx.getInitParameterNames();
		while (paramNames.hasMoreElements()) {
			String paramName = paramNames.nextElement();
			String classType = ctx.getInitParameter(paramName);
			
			try {
				Class<?> cls = Class.forName(classType);

				Method[] methods = cls.getMethods();

				for (Method method : methods) {
					if (method.getName().contains("set")) {
						
						System.out.println(method.getName());
						
						String setInstanceName = ((method.getName().replace("set", "")).charAt(0) + "").toLowerCase()
								+ method.getName().substring(4);  // dao
						
						// 요악하면 service에 있는 set메소드에 dao를 줘라.
						// set메소드에 		     service에 있는                		dao를 줘라.
						// set메소드에			 dao에 있는							sessionFactory를 줘라
						// EX)   setBoardDAO.invoke(BoardServiceImpl, BoardDAOImpl)
						method.invoke(applicationContext.get(paramName), applicationContext.get(setInstanceName));
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}
		System.out.println(applicationContext);
	}

}
