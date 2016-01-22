package org.weixin4j.web;

import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.weixin4j.Menu;
import org.weixin4j.Weixin;
import org.weixin4j.WeixinException;
import org.weixin4j.dao.ControlMenu;
import org.weixin4j.http.OAuthToken;
import org.weixin4j.util.AcTokenUtil;
import org.weixin4j.vo.Tuwen;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class SetMenu extends HttpServlet {

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {

		Weixin weixin = new Weixin();

		OAuthToken oAuthToken = null;

		try {
			oAuthToken = AcTokenUtil.getOauthToken(weixin);
		} catch (WeixinException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		Menu menu = null;

		menu = new ControlMenu().addmenu();

		Gson gson = new GsonBuilder().disableHtmlEscaping().create();

		String jsonmenu = gson.toJson(menu);

		String url = "https://api.weixin.qq.com/cgi-bin/menu/create?access_token=" + oAuthToken.getAccess_token();

		CloseableHttpClient httpClient = HttpClients.createDefault();

		HttpPost httpPost = new HttpPost(url);

		String json = null;

		// 例子
		// StringEntity entity = new
		// StringEntity(jsonParam.toString(),"utf-8");//解决中文乱码问题
		// entity.setContentEncoding("UTF-8");
		// entity.setContentType("application/json");
		// method.setEntity(entity);
		// HttpResponse result = httpClient.execute(method);

		StringEntity entity = new StringEntity(jsonmenu, "utf-8");
		entity.setContentEncoding("UTF-8");
		entity.setContentType("application/json");
		httpPost.setEntity(entity);
		CloseableHttpResponse response2 = httpClient.execute(httpPost);

		json = EntityUtils.toString(response2.getEntity());

		request.setAttribute("test", json);

		request.getRequestDispatcher("test.jsp").forward(request, response);
	}
}
