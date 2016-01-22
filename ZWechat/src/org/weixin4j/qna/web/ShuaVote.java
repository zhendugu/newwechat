package org.weixin4j.qna.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.weixin4j.qna.dao.CandidateDao;


public class ShuaVote extends HttpServlet {
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		
		String shuapiao = request.getParameter("shuapiao");
		
		String name = request.getParameter("name");
		
//		String name = URLDecoder.decode(request.getParameter("name"),"utf-8"); 
		
		Integer vote = new CandidateDao().checkVote(name);
		
		vote = vote + Integer.parseInt(shuapiao);
		
		new CandidateDao().addVote(name ,vote);
		
		response.sendRedirect("update.jsp");
		
		
	}
	
	
}
