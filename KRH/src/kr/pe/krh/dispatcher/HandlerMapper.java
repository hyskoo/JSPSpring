package kr.pe.krh.dispatcher;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Set;

import kr.pe.krh.action.Action;
import kr.pe.krh.action.ApplicationContext;

public class HandlerMapper {
	
	private Map<String,Action> commandMap =	new HashMap<String,Action>();
	
	{
		String path="kr/pe/krh/propertis/url";
		
		ResourceBundle rbHome=ResourceBundle.getBundle(path);
		
		Set<String> actionSetHome=rbHome.keySet();
		
		System.out.println("[ HandlerMapper ] : actionSetHome : " + actionSetHome);
		
		Iterator<String> it=actionSetHome.iterator();
		
		while(it.hasNext()){
			String urlkey = it.next();
			
			String actionClassName=rbHome.getString(urlkey); // properties에서 value값을 가져온다. 그런데 그것이 String이므로 getString
			
			System.out.println("[ HandlerMapper ] : actionClassName : " + actionClassName);
			
			try {
			Class<?> actionClass = Class.forName(actionClassName);
			Action commandAction = (Action)actionClass.newInstance();
			
			// 의존성 확인 및 조립
			Method[] methods = actionClass.getMethods();
			
			for (Method method : methods) {
				if (method.getName().contains("set")) {
					String paramType = method.getParameterTypes()[0].getName();
					paramType = paramType.substring(paramType.lastIndexOf(".")+1);
					
					paramType = (paramType.charAt(0) + "").toLowerCase() + paramType.substring(1);
					try {
						method.invoke(commandAction, ApplicationContext.getApplicationContext().get(paramType));
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
			
			commandMap.put(urlkey, commandAction);
			
			System.out.println("[ HandlerMapper ] : " +  urlkey + " : " + commandAction + " 가 준비되었습니다.\r\n");
			
			}catch (ClassNotFoundException e){
				System.out.println("[ HandlerMapper ] : " + actionClassName + "이 존재하지 않습니다.");
			} catch (InstantiationException e) {
				System.out.println("[ HandlerMapper ] : " + actionClassName + "인스턴스를 생성할 수 없습니다.");
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}	
			
		}
	}
	
	public Action getAction(String command){
		Action action = commandMap.get(command);
		return action;
	}
}











