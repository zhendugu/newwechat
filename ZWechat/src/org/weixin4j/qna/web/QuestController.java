package org.weixin4j.qna.web;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class QuestController extends HttpServlet {
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String qingtianxie = request.getParameter("qingtianxie");

		String danxuan = request.getParameter("danxuan");

		String duoxuan1 = request.getParameter("duoxuan1");
		String duoxuan2 = request.getParameter("duoxuan2");
		String duoxuan3 = request.getParameter("duoxuan3");
		String duoxuan4 = request.getParameter("duoxuan4");
		if (duoxuan1 == null) {
			duoxuan1 = "0";
		}
		if (duoxuan2 == null) {
			duoxuan2 = "0";
		}
		if (duoxuan3 == null) {
			duoxuan3 = "0";
		}
		if (duoxuan4 == null) {
			duoxuan4 = "0";
		}

		String duoxuan = duoxuan1 + duoxuan2 + duoxuan3 + duoxuan4;

		String xiala = request.getParameter("xiala");

		try {
			Class.forName("com.mysql.jdbc.Driver");

			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/text", "root", "82603988hztC");

			PreparedStatement preparedStatement = connection
					.prepareStatement("INSERT INTO QUEST(qingtianxie,danxuan,duoxuan,xiala) VALUES(?,?,?,?)");

			preparedStatement.setString(1, qingtianxie);
			preparedStatement.setString(2, danxuan);
			preparedStatement.setString(3, duoxuan);
			preparedStatement.setString(4, xiala);

			preparedStatement.executeUpdate();

			connection.close();
			preparedStatement.close();

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
