package kr.pe.krh.listener;

import java.lang.reflect.Method;
import java.util.Enumeration;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import kr.pe.krh.action.ApplicationContext;

public class InitListener implements ServletContextListener {

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		System.out.println("[InitListner] : Destory");
	}

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		Map<String, Object> applicationContext = ApplicationContext.getApplicationContext();

		ServletContext servletContext = sce.getServletContext();

		Enumeration<String> paramNames = servletContext.getInitParameterNames();
		
		while (paramNames.hasMoreElements()) {
			String paramName = paramNames.nextElement(); // service
			String classType = servletContext.getInitParameter(paramName);
			try {
				Class<?> cls = Class.forName(classType);

				Object targetObj = cls.newInstance();

				applicationContext.put(paramName, targetObj);

			} catch (Exception e) {
				e.printStackTrace();
			}

		}
		paramNames = servletContext.getInitParameterNames();
		while (paramNames.hasMoreElements()) {
			String paramName = paramNames.nextElement();
			String classType = servletContext.getInitParameter(paramName);
			
			try {
				Class<?> cls = Class.forName(classType);

				Method[] methods = cls.getMethods();

				for (Method method : methods) {
					if (method.getName().contains("set")) {
						
						System.out.println(method.getName());
						
						String setInstanceName = ((method.getName().replace("set", "")).charAt(0) + "").toLowerCase()
								+ method.getName().substring(4);
						
						method.invoke(applicationContext.get(paramName), applicationContext.get(setInstanceName));
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

}
