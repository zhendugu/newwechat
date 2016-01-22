package org.weixin4j.dao;

import java.util.ArrayList;
import java.util.List;

import org.weixin4j.Menu;
import org.weixin4j.menu.MediaIdButton;
import org.weixin4j.menu.SingleButton;
import org.weixin4j.menu.ViewButton;
import org.weixin4j.menu.ViewLimitedButton;

public class ControlMenu {
	public Menu addmenu() {

		Menu menu = new Menu();

		List<SingleButton> button = new ArrayList<SingleButton>();

		// 根菜单
//		SingleButton motherButton1 = new ViewButton("路演征集",
//				"http://mp.weixin.qq.com/s?__biz=MzI2NjAxMTc1OA==&mid=453558067&idx=1&sn=00fdd7a18df00aae808701b538d3332e&scene=18#wechat_redirect");
//		SingleButton motherButton2 = new ViewButton("年会报名", "http://www.huodongxing.com/event/6316479681500");
//		SingleButton motherButton3 = new SingleButton("关于我们");
		
		SingleButton motherButton1 = new ViewButton("APP下载", "http://mp.weixin.qq.com/s?__biz=MzI2NjAxMTc1OA==&mid=222719384&idx=1&sn=65f6e7e97b44094e8a073a7da7519f3d#rd");
		SingleButton motherButton2 = new SingleButton("年会专区");
		SingleButton motherButton3 = new ViewButton("关于我们", "http://mp.weixin.qq.com/s?__biz=MzI2NjAxMTc1OA==&mid=453931310&idx=1&sn=3e0a1b8e6f4747b9d6a8f18192bcdabc#rd");

		
		// 制作子菜单容器
		List<SingleButton> sub_button = new ArrayList<SingleButton>();

		// 向子菜单容器添加子菜单
//		sub_button.add(new ViewButton("创业社区", "http://buluo.qq.com/mobile/barindex.html?_bid=128&_wv=1027&bid=233494"));
//		sub_button.add(new ViewButton("平台介绍",
//				"http://mp.weixin.qq.com/s?__biz=MzI2NjAxMTc1OA==&mid=453931310&idx=1&sn=3e0a1b8e6f4747b9d6a8f18192bcdabc&scene=18#wechat_redirect"));
//		sub_button.add(new ViewButton("投票", "http://buluo.qq.com/mobile/barindex.html?_bid=128&_wv=1027&bid=233494"));
		sub_button.add(new ViewButton("年会报名", "http://t.cn/R4oXGIs"));
		sub_button.add(new ViewButton("项目投票", "https://open.weixin.qq.com/connect/oauth2/authorize?appid=wxb8d027c2d3cae32b&redirect_uri=http%3A%2F%2Fshanyuanjie.cn%2Fwechat%2FCheckScri&response_type=code&scope=snsapi_base&state=STATE#wechat_redirect"));
		sub_button.add(new ViewButton("年会抽奖", "https://hd.faisco.cn/8164268/3/load.html?style=23"));


		// 给第三个母菜单添加子菜单
		motherButton2.setSubButton(sub_button);

		// 添加菜单
		button.add(motherButton1);
		button.add(motherButton2);
		button.add(motherButton3);

		menu.setButton(button);

		return menu;
	}
}
