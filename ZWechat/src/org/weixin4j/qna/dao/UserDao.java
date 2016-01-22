package org.weixin4j.qna.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.eclipse.jdt.internal.compiler.ast.TrueLiteral;
import org.weixin4j.User;
import org.weixin4j.util.DBUtil;

public class UserDao {

	/**
	 * 如果没有关注
	 * 
	 * @param user
	 */
	public void setUserVo2SQL(User user) {
		Connection connection = null;
		PreparedStatement pstmt = null;

		String sql = "INSERT INTO uservo(openid,subscribe,option1,option2,option3,pointTime,createTime) VALUES(?,?,?,?,?,?,NOW())";

		connection = DBUtil.getConnection();
		pstmt = DBUtil.getPstmt(connection, sql);

		try {
			pstmt.setString(1, user.getOpenid());
			pstmt.setString(2, user.getSubscribe());
			pstmt.setInt(3, 0);
			pstmt.setInt(4, 0);
			pstmt.setInt(5, 0);
			pstmt.setInt(6, 0);

			pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtil.closeAll(connection, pstmt, null);
		}
	}

	/**
	 * 检查是否有这个人在数据库
	 * @param user
	 * @return
	 */
	public boolean checkUser(User user) {
		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet resultSet = null;
		int id = 0;

		String sql = "SELECT * FROM uservo WHERE openid = ? ";

		connection = DBUtil.getConnection();
		pstmt = DBUtil.getPstmt(connection, sql);

		try {
			pstmt.setString(1, user.getOpenid());
			resultSet = pstmt.executeQuery();

			while (resultSet.next()) {
				id = resultSet.getInt("id");
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
			return false;
		} finally {
			DBUtil.closeAll(connection, pstmt, resultSet);
		}

		if (id != 0) {
			return true;
		} 

		return false;
	}
}
