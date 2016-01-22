package org.weixin4j.test;

import java.io.UnsupportedEncodingException;

public class testurlcode {
	public static void main(String[] args) {
		String mytext = null;
		try {
			mytext = java.net.URLEncoder.encode("http://shanyuanjie.cn/wechat/WebTest", "utf-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(mytext);
	}
}
