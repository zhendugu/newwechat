package org.weixin4j.test;

import org.weixin4j.Menu;
import org.weixin4j.dao.ControlMenu;
import org.weixin4j.vo.Tuwen;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class json {
	public static void main(String[] args) {
		Menu menu = null;

		menu = new ControlMenu().addmenu();

		Gson gson = new GsonBuilder().disableHtmlEscaping().create();//防止被转成urlencode


		String jsonmenu = gson.toJson(menu);
		
		System.out.println(jsonmenu);
	}
}
