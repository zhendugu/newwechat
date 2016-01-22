package org.weixin4j.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import org.apache.commons.lang.ObjectUtils.Null;
import org.weixin4j.Configuration;
import org.weixin4j.Weixin;
import org.weixin4j.WeixinException;
import org.weixin4j.http.OAuth;
import org.weixin4j.http.OAuthToken;
import org.weixin4j.vo.Tokens;

public class AcTokenUtil {
	private static String appid = null;
	private static String secret = null;
	private static OAuthToken oAuthToken = null;
	private static Tokens tokens = null;

	static {
		appid = Configuration.getProperty("weixin4j.oauth.appid", "weixin4j");
		secret = Configuration.getProperty("weixin4j.oauth.secret", "weixin4j");

		// client_credential表示获取acctoken
	}

	/**
	 * appid出口
	 * 
	 * @return
	 */
	public static String getAppid() {
		return appid;
	}

	/**
	 * secret出口
	 * 
	 * @return
	 */
	public static String getSecret() {
		return secret;
	}

	/**
	 * oauthtoken出口
	 * 
	 * @param weixin
	 * @return
	 * @throws WeixinException
	 */
	public static OAuthToken getOauthToken(Weixin weixin) throws WeixinException {

		setOauToken(weixin);

		return oAuthToken;
	}

	/**
	 * 一次性检验、获取并保存唯一的actoken
	 * 
	 * @param weixin
	 * @throws WeixinException
	 */
	private static void setOauToken(Weixin weixin) throws WeixinException {
		OAuth oAuth = new OAuth(appid, secret);
		Tokens tokens = getTokensFromSQL(oAuth, weixin);
		if (tokens.getAccess_token() != null) {
			// 算出actoken剩余时间
			long time = ((new Date().getTime()) - (tokens.getCreateTime().getTime())) / 1000;
			int expiresIn = 0;
			if (time < 7200) {
				expiresIn = (int) (7200 - time);
			} else {
				expiresIn = 0;
				updateTokens(oAuthToken, oAuth);
			}
			weixin.init(tokens.getAccess_token(), tokens.getAppId(), tokens.getSecret(), expiresIn);
			oAuthToken = weixin.login(appid, secret);
		} else {
			oAuthToken = weixin.login(appid, secret);
			saveTokens(oAuthToken, oAuth);
		}

	}

	/**
	 * 更新已有的token
	 * @param oAuthToken2
	 * @param oAuth
	 */
	private static void updateTokens(OAuthToken oAuthToken2, OAuth oAuth) {
		// TODO Auto-generated method stub
		
		String sql = "UPDATE wechat SET access_token = ?, createTime = NOW() WHERE appId = ?";

		Connection connection = DBUtil.getConnection();
		PreparedStatement pstmt = DBUtil.getPstmt(connection, sql);

		connection = DBUtil.getConnection();
		pstmt = DBUtil.getPstmt(connection, sql);

		try {
			pstmt.setString(1, oAuthToken.getAccess_token());
			pstmt.setString(2, oAuth.getAppId());

			pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtil.closeAll(connection, pstmt, null);
		}

		
	}

	/**
	 * 保存access_token
	 * 
	 * @param oAuthToken
	 * @param oAuth
	 */
	public static void saveTokens(OAuthToken oAuthToken, OAuth oAuth) {

		String sql = "INSERT INTO wechat(appId, secret, access_token, createTime ) VALUES(?,?,?,NOW())";

		Connection connection = DBUtil.getConnection();
		PreparedStatement pstmt = DBUtil.getPstmt(connection, sql);

		connection = DBUtil.getConnection();
		pstmt = DBUtil.getPstmt(connection, sql);

		try {
			pstmt.setString(1, oAuth.getAppId());
			pstmt.setString(2, oAuth.getSecret());
			pstmt.setString(3, oAuthToken.getAccess_token());

			pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtil.closeAll(connection, pstmt, null);
		}

	}

	/**
	 * 从数据库取出actoken初始化微信对象
	 * 
	 * @param oAuth
	 * @param weixin
	 * @return
	 * @throws WeixinException
	 */
	private static Tokens getTokensFromSQL(OAuth oAuth, Weixin weixin) {

		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet resultSet = null;

		Tokens tokens = null;

		String access_token = null;
		String appId = null;
		String secret = null;
		Date createTime = null;

		String sql = "SELECT * FROM wechat WHERE appId=? AND secret=?";

		connection = DBUtil.getConnection();
		pstmt = DBUtil.getPstmt(connection, sql);

		try {

			pstmt.setString(1, oAuth.getAppId());
			pstmt.setString(2, oAuth.getSecret());

			resultSet = pstmt.executeQuery();

			while (resultSet.next()) {
				access_token = resultSet.getString("access_token");
				createTime = resultSet.getTimestamp("createTime");

			}

			tokens = new Tokens(appId, secret, access_token, createTime);

		} catch (SQLException e) {
			tokens = null;
			return tokens;
		} finally {
			DBUtil.closeAll(connection, pstmt, null);
		}
		return tokens;
	}

}
