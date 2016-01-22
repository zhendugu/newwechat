package org.weixin4j.qna.web;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.weixin4j.qna.dao.CandidateDao;
import org.weixin4j.qna.vo.Candidate;

public class CandidateShow extends HttpServlet{
	
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");

		List<Candidate> candidates = null;
		candidates = new CandidateDao().showAll();
		
		request.setAttribute("list", candidates);
		
		request.getRequestDispatcher("main.jsp").forward(request, response);
	}
}
