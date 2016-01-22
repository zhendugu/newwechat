package org.weixin4j.qna.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.weixin4j.User;
import org.weixin4j.util.DBUtil;
import org.weixin4j.vo.UserVo;

public class UserVoDao {

	/**
	 * 判断是否可以投要投的一票
	 * 
	 * @param openid
	 * @param cid
	 * @return
	 */
	public int setOne(String openid, String cid) {
		// TODO Auto-generated method stub
		boolean flag1 = false;

		User user = new User();

		user.setOpenid(openid);

		flag1 = new UserDao().checkUser(user);

		if (flag1 == false) {
			return 3;
		}

		int flag2 = 0;

		flag2 = setvote(openid, cid);
		return flag2;

	}

	/**
	 * 判断选择是否重复以及是否用完3次
	 * 
	 * @param openid
	 * @param cid
	 * @return
	 */
	private int setvote(String openid, String cid) {
		// TODO Auto-generated method stub
		UserVo userVo = checkUservo(openid);

		// 如果已经点了三次就返回通知
		if (userVo.getPointTime() == 3) {
			return 2;
		}

		if (userVo.getPointTime() == 2 && Integer.parseInt(cid) == userVo.getOption1()) {
			return 1;
		} else if (userVo.getPointTime() == 2 && Integer.parseInt(cid) == userVo.getOption2()) {
			return 1;
		} else if (userVo.getPointTime() == 2 && userVo.getOption3() == 0) {
			saveOption3(cid, openid, "UPDATE uservo SET option3 = ? , pointTime = ? WHERE openid = ?", 3);
			return 0;
		}

		if (userVo.getPointTime() == 1 && Integer.parseInt(cid) == userVo.getOption1()) {
			return 1;
		} else if (userVo.getPointTime() == 1 && Integer.parseInt(cid) != userVo.getOption1()) {
			saveOption3(cid, openid, "UPDATE uservo SET option2 = ? , pointTime = ? WHERE openid = ?", 2);
			return 0;
		}

		saveOption3(cid, openid, "UPDATE uservo SET option1 = ? , pointTime = ? WHERE openid = ?", 1);
		return 0;
	}

	/**
	 * 查询uservo
	 * 
	 * @param openid
	 * @return
	 */
	public UserVo checkUservo(String openid) {
		UserVo userVo = new UserVo();
		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet resultSet = null;

		String sql = "SELECT * FROM uservo WHERE openid = ?";
		connection = DBUtil.getConnection();
		pstmt = DBUtil.getPstmt(connection, sql);
		try {
			pstmt.setString(1, openid);
			resultSet = pstmt.executeQuery();
			while (resultSet.next()) {
				userVo.setOpenid(resultSet.getString("openid"));
				userVo.setSubscribe(resultSet.getString("subscribe"));
				userVo.setOption1(resultSet.getInt("option1"));
				userVo.setOption2(resultSet.getInt("option2"));
				userVo.setOption3(resultSet.getInt("option3"));
				userVo.setPointTime(resultSet.getInt("pointTime"));
				userVo.setCreateTime(resultSet.getTimestamp("createTime"));
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtil.closeAll(connection, pstmt, resultSet);
		}
		return userVo;
	}

	/**
	 * 填写下一个选项
	 * 
	 * @param cid
	 * @param openid
	 */
	private void saveOption3(String cid, String openid, String sql, int pointTime) {
		// TODO Auto-generated method stub
		Connection connection = null;
		PreparedStatement pstmt = null;

		connection = DBUtil.getConnection();
		pstmt = DBUtil.getPstmt(connection, sql);

		try {
			pstmt.setInt(1, Integer.parseInt(cid));
			pstmt.setInt(2, pointTime);
			pstmt.setString(3, openid);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtil.closeAll(connection, pstmt, null);
		}
	}

	/**
	 * 投票归零
	 * 
	 * @param openid
	 */
	public void setZeroVo(String openid) {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
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
			pstmt.setString(5, openid);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtil.closeAll(connection, pstmt, null);
		}

	}
}
