package org.weixin4j.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.weixin4j.Configuration;
import org.weixin4j.OAuth2;
import org.weixin4j.User;
import org.weixin4j.Weixin;
import org.weixin4j.WeixinException;
import org.weixin4j.http.OAuth;
import org.weixin4j.http.OAuth2Token;
import org.weixin4j.http.OAuthToken;
import org.weixin4j.util.AcTokenUtil;
import org.weixin4j.util.TokenUtil;

public class CheckUser extends HttpServlet {
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");

		String appId = AcTokenUtil.getAppid();
		String secret = AcTokenUtil.getSecret();
		String openid = request.getParameter("openid");
		User user = null;
		String acctoken = null;

		Weixin weixin = new Weixin();
		OAuthToken oAuthToken = null;

		OAuth oAuth = new OAuth(appId, secret);

		try {
			oAuthToken = AcTokenUtil.getOauthToken(weixin);
		} catch (WeixinException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		request.setAttribute("user", openid);
		// request.setAttribute("appId", code);
		request.setAttribute("secret", secret);
		request.setAttribute("test", acctoken);

		request.getRequestDispatcher("final.jsp").forward(request, response);

	}
}
