package org.weixin4j.test;

import org.weixin4j.Menu;
import org.weixin4j.dao.ControlMenu;

import com.google.gson.Gson;
import com.sun.swing.internal.plaf.metal.resources.metal;

public class usertest {
	public static void main(String[] args) {
		Menu menu = new Menu();
		
		menu = new ControlMenu().addmenu();

		Gson gson = new Gson();

		String jsonusercheck = gson.toJson(menu);
		
		System.out.println(jsonusercheck);
	}
}
