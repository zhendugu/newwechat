package org.weixin4j.qna.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.weixin4j.qna.dao.CandidateDao;
import org.weixin4j.qna.vo.Candidate;


public class OneShow extends HttpServlet {
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");

		Candidate candidate = null;
		candidate = new CandidateDao().showOne(Integer.parseInt(request.getParameter("id")));
		
		request.setAttribute("candidate", candidate);
		
		request.getRequestDispatcher("final.jsp").forward(request, response);

	}
}
