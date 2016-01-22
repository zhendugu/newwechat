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

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.eclipse.jdt.internal.compiler.lookup.InvocationSite.EmptyWithAstNode;
import org.weixin4j.Menu;
import org.weixin4j.Weixin;
import org.weixin4j.WeixinException;
import org.weixin4j.dao.ControlMenu;
import org.weixin4j.http.OAuthToken;
import org.weixin4j.util.AcTokenUtil;
import org.weixin4j.vo.Tuwen;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class CheckNewId extends HttpServlet {

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {

		Weixin weixin = new Weixin();

		OAuthToken oAuthToken = null;

		try {
			oAuthToken = weixin.login(AcTokenUtil.getAppid(), AcTokenUtil.getSecret());
		} catch (WeixinException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		int checkin = Integer.parseInt(request.getParameter("checkin"));

		Menu menu = null;

		menu = new ControlMenu().addmenu();

		Gson gson = new Gson();

		Tuwen tuwen = new Tuwen("news", checkin, 20);

		String jsonmenu = gson.toJson(tuwen);

		String action = "https://api.weixin.qq.com/cgi-bin/material/batchget_material?access_token="
				+ oAuthToken.getAccess_token();

		CloseableHttpClient httpClient = HttpClients.createDefault();

		HttpPost httpPost = new HttpPost(action);

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

		// 原代码
		// List<NameValuePair> nvps = new ArrayList<NameValuePair>();
		// nvps.add(new BasicNameValuePair("json",news ));
		// CloseableHttpResponse response2 = null;
		// HttpEntity entity2 = null;
		// try {
		// httpPost.setEntity(new UrlEncodedFormEntity(nvps));
		// response2 = httpClient.execute(httpPost);
		// entity2 = response2.getEntity();
		// json = EntityUtils.toString(entity2);
		//
		// EntityUtils.consume(entity2);
		// } catch (UnsupportedEncodingException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// } catch (ClientProtocolException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// } catch (IOException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// } finally {
		// response2.close();
		// httpClient.close();
		// }
		request.setAttribute("test", json);
		response.setCharacterEncoding("UTF-8");
		request.getRequestDispatcher("test.jsp").forward(request, response);
	}
}
