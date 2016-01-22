package org.weixin4j.qna.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.weixin4j.qna.dao.CandidateDao;
import org.weixin4j.qna.vo.Candidate;

public class Update extends HttpServlet {

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");

		String name = request.getParameter("name");
		String introduce = request.getParameter("introduce");
		String logoUrl = "http://shanyuanjie.cn/wechat/logos/" + request.getParameter("logoUrl");
		String financing = request.getParameter("financing");
		String member = request.getParameter("member");
		String mode = request.getParameter("mode");
		String productUrl = request.getParameter("productUrl");
		String prospectusUrl = request.getParameter("prospectusUrl");
		Integer vote = Integer.parseInt(request.getParameter("vote"));

		System.out.println(name);

		Candidate candidate = new Candidate(name, introduce, logoUrl, financing, member, mode, productUrl,
				prospectusUrl, vote);

		new CandidateDao().insertNew(candidate);

		response.sendRedirect("update.jsp");
	}

}
