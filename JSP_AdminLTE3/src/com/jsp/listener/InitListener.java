package com.jsp.listener;

import java.lang.reflect.Method;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import org.apache.ibatis.session.SqlSessionFactory;

import com.jsp.dao.BoardDAO;
import com.jsp.dao.MemberDAO;
import com.jsp.request.SearchCriteria;
import com.jsp.service.BoardServiceImpl;
import com.jsp.service.MemberServiceImpl;

@WebListener
public class InitListener implements ServletContextListener {

    public void contextDestroyed(ServletContextEvent ctxEvent)  { 
    }

	
    public void contextInitialized(ServletContextEvent ctxEvent)  { 
         String sqlSessionFactoryType = 
        		 ctxEvent.getServletContext().getInitParameter("sqlSessionFactory");
         String memberDAOType = ctxEvent.getServletContext().getInitParameter("memberDAO");
         String boardDAOType = ctxEvent.getServletContext().getInitParameter("boardDAO");
         
         try {
        	 
	         SqlSessionFactory sqlSessionFactory = 
	        		 (SqlSessionFactory)Class.forName(sqlSessionFactoryType).newInstance();
	         
	         Class<?> cls = Class.forName(memberDAOType);        
	         
//	         Getmethod (메소드이름, 파라미터종류와 타입들(가변배열)) -> 메소드이름의 파라미터종류를 가진것이 있는지 확인
	         Method setSqlSessionFactory = cls.getMethod("setSessionFactory", SqlSessionFactory.class);
	         
	         Object obj = cls.newInstance();
	         
//	         methodname.invoke( obj1, obj2) obj1로가서 methodname과 같은것을 찾아내서 obj2를 사용하라는 뜻 (알아서해라라는 의미로 invoke사용)
	         setSqlSessionFactory.invoke(obj, sqlSessionFactory);
	         
	         MemberDAO memberDAO = (MemberDAO)obj;
	         
	         MemberServiceImpl.getInstance().setMemberDAO(memberDAO);
	         
	         
	         
	         //////////////////////////////////////////////////////////////////////
	         
	         Class<?> Boardcls = Class.forName(boardDAOType);        
	         
	         Method setSqlSessionFactoryBoard = Boardcls.getMethod("setSessionFactory", SqlSessionFactory.class);
	         
	         Object objBoard = Boardcls.newInstance();
	         
	         setSqlSessionFactoryBoard.invoke(objBoard, sqlSessionFactory);
	         
	         BoardDAO boardDAO = (BoardDAO)objBoard;
	         
	         BoardServiceImpl.getInstance().setBoardDAO(boardDAO);
	         
         }catch(Exception e) {
        	 e.printStackTrace();
         }
                
         
    }
	
}
