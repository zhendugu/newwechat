package org.weixin4j.test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.weixin4j.util.DBUtil;
import org.weixin4j.vo.UserVo;

public class testvo {
	public static void main(String[] args) {
		Connection connection = null;
		PreparedStatement pstmt = null;
		
		String sql = "UPDATE uservo SET option1 = ? , pointTime = ? WHERE openid = ?";

		connection = DBUtil.getConnection();
		pstmt = DBUtil.getPstmt(connection, sql);

		try {
			pstmt.setInt(1, Integer.parseInt("10"));
			pstmt.setInt(2, 1);
			pstmt.setString(3, "asdf");
			pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtil.closeAll(connection, pstmt, null);
		}
	}
	
}
