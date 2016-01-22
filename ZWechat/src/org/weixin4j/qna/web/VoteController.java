package org.weixin4j.qna.web;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.weixin4j.qna.dao.CandidateDao;
import org.weixin4j.qna.dao.UserVoDao;
import org.weixin4j.vo.UserVo;

public class VoteController extends HttpServlet {
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");

		String name = request.getParameter("name");
		String openid = request.getParameter("openid");
		String cid = request.getParameter("cid");
		

		int status = 0;// 0表示成功，1表示重复投票，2表示投完票了，3表示未关注
		
		String order = null;

		// String name =
		// URLDecoder.decode(request.getParameter("name"),"utf-8");

		Integer vote = new CandidateDao().checkVote(name);

		vote++;

		UserVo userVo = new UserVoDao().checkUservo(openid);
		if ((new Date().getTime()) - (userVo.getCreateTime().getTime()) >= 86400000) {
			new UserVoDao().setZeroVo(openid);
		}
		
		status = new UserVoDao().setOne(openid, cid);

		if (status == 0) {
			new CandidateDao().addVote(name, vote);
			response.setCharacterEncoding("UTF-8");
			response.getWriter().print("感谢您的支持");
		} else if (status == 1) {
			response.setCharacterEncoding("UTF-8");
			response.getWriter().print("温馨提示：一位候选人一天只能投一票哦");
		} else if (status == 2) {
			response.setCharacterEncoding("UTF-8");
			response.getWriter().print("温馨提示：每位用户每天只能投三票哦");
		} else if (status == 3) {
			response.setCharacterEncoding("UTF-8");
			response.getWriter().print("温馨提示：请您先关注我们的微信哦");
		}
		
	}

}
