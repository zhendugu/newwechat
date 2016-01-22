package org.weixin4j.test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.weixin4j.util.DBUtil;

public class testDate {
	public static void main(String[] args) {
		String sql = "select * from date";
		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet resultSet = null;
		Date date = new Date();

		connection = DBUtil.getConnection();
		pstmt = DBUtil.getPstmt(connection, sql);
		try {
			resultSet = pstmt.executeQuery();
			while (resultSet.next()) {
				date = resultSet.getTimestamp("createTime");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtil.closeAll(connection, pstmt, null);
			long time = ((new Date().getTime()) - (date.getTime())) / 1000;
			int expiresIn = 0;
			if (time < 7200) {
				expiresIn = (int) (7200 - time);
			}
			System.out.println(time);
		}
	}
}
