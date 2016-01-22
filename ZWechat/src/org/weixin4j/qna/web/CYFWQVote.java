package org.weixin4j.qna.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.weixin4j.OAuth2;
import org.weixin4j.User;
import org.weixin4j.Weixin;
import org.weixin4j.WeixinException;
import org.weixin4j.http.OAuth2Token;
import org.weixin4j.http.OAuthToken;
import org.weixin4j.qna.dao.UserDao;
import org.weixin4j.util.AcTokenUtil;

import com.google.gson.Gson;

public class CYFWQVote extends HttpServlet {
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		// TODO Auto-generated method stub

		// 我之前设置每一次都要获取一次openid导致冲突，所以最好判断一下user是否存在，再决定是否获取openid，吃一堑长一智
		User testUser = (User) request.getSession().getAttribute("user");

		String rurl = request.getParameter("rurl");


		if (testUser == null) {
			String code = request.getParameter("code");
			Weixin weixin = new Weixin();
			
			OAuthToken oAuthToken = null;
			OAuth2Token oAuth2Token = null;
			String json = null;// 接受发回的json
			User user = null;

			try {
				oAuthToken = AcTokenUtil.getOauthToken(weixin);
			} catch (WeixinException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}

			// 下面开始获取openid
			OAuth2 oAuth2 = new OAuth2();

			try {
				oAuth2Token = oAuth2.login(AcTokenUtil.getAppid(), AcTokenUtil.getSecret(), code);
			} catch (WeixinException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			String url = "https://api.weixin.qq.com/cgi-bin/user/info?access_token=" + oAuthToken.getAccess_token()
					+ "&openid=" + oAuth2Token.getOpenid() + "&lang=zh_CN";

			user = new User();// 接收发回的json转user对象

			// httpclient
			CloseableHttpClient httpClient = null;
			HttpGet httpGet = null;
			CloseableHttpResponse response2 = null;
			HttpEntity entity = null;

			httpClient = HttpClients.createDefault();
			httpGet = new HttpGet(url);
			try {
				response2 = httpClient.execute(httpGet);
				entity = response2.getEntity();

				json = EntityUtils.toString(entity);

				response2.close();
				httpClient.close();
			} catch (ClientProtocolException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			// httpclient

			Class type = User.class;
			Gson gson = new Gson();
			user = gson.fromJson(json, type);

			if (Integer.parseInt(user.getSubscribe()) == 0) {
				response.sendRedirect(
						"http://mp.weixin.qq.com/s?__biz=MzI2NjAxMTc1OA==&mid=455771576&idx=1&sn=d0e9addbbdd0882e7bb080f094d4418d#rd");
			} else {
				if (new UserDao().checkUser(user)) {
					request.getSession().setAttribute("user", user);
					request.getRequestDispatcher("/" + rurl).forward(request, response); // 存
				} else {
					request.getSession().setAttribute("user", user);
					new UserDao().setUserVo2SQL(user);
					request.getRequestDispatcher("/" + rurl).forward(request, response);
				}

			}
		} else {
			request.getRequestDispatcher("/" + rurl).forward(request, response);
		}
	}

}
