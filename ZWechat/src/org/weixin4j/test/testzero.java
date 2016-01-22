package org.weixin4j.test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.weixin4j.util.DBUtil;

public class testzero {
	public static void main(String[] args) {
		Connection connection = null;
		PreparedStatement pstmt = null;

		String sql = "UPDATE uservo SET option1 = ? , option2 = ? ,option3 = ? ,pointTime = ?,createTime=NOW() WHERE openid = ?";

		connection = DBUtil.getConnection();
		pstmt = DBUtil.getPstmt(connection, sql);

		try {
			pstmt.setInt(1, 0);
			pstmt.setInt(2, 0);
			pstmt.setInt(3, 0);
			pstmt.setInt(4, 0);
			pstmt.setString(5, "asdf");
			pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtil.closeAll(connection, pstmt, null);
		}

	}
}
