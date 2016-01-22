package org.weixin4j.qna.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.weixin4j.qna.vo.Candidate;
import org.weixin4j.util.DBUtil;

public class CandidateDao {

	/**
	 * 获取某人票数
	 * 
	 * @param candidate
	 * @return
	 */
	public Integer checkVote(String name) {

		Integer vote = null;

		String sql = "SELECT vote FROM candidate WHERE name = ?";

		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet resultSet = null;

		connection = DBUtil.getConnection();
		pstmt = DBUtil.getPstmt(connection, sql);
		
		try {
			pstmt.setString(1, name);

			resultSet = pstmt.executeQuery();

			while (resultSet.next()) {
				vote = resultSet.getInt("vote");
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtil.closeAll(connection, pstmt, resultSet);
		}

		return vote;

	}

	/**
	 * 票数＋1
	 * 
	 * @param name
	 * @param vote
	 */
	public void addVote(String name, Integer vote) {
		// TODO Auto-generated method stub
		String sql = "UPDATE candidate SET vote = ? WHERE name = ?	";

		Connection connection = null;
		PreparedStatement pstmt = null;

		connection = DBUtil.getConnection();
		pstmt = DBUtil.getPstmt(connection, sql);

		try {
			pstmt.setInt(1, vote);
			pstmt.setString(2, name);

			pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtil.closeAll(connection, pstmt, null);
		}

	}

	/**
	 * 获取指定的候选人
	 * @param name
	 * @return
	 */
	public Candidate showCandidate(String name) {
		Candidate candidate = null;
		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet resultSet = null;

		String sql = "SELECT * FROM candidate WHERE name = ?";

		connection = DBUtil.getConnection();
		pstmt = DBUtil.getPstmt(connection, sql);
		try {
			pstmt.setString(1, name);
			resultSet = pstmt.executeQuery();
			while (resultSet.next()) {

				candidate = new Candidate();
				resultToCandidate(resultSet, candidate);

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtil.closeAll(connection, pstmt, resultSet);
		}

		return candidate;
	}
	
	/**
	 * 查询所有候选人
	 * @return
	 */
	public List<Candidate> showAll(){
		List<Candidate> candidates = new ArrayList<Candidate>();
		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet resultSet = null;
		Candidate candidate = null;
		
		String sql = "SELECT * FROM candidate ORDER BY vote DESC";
		
		connection = DBUtil.getConnection();
		pstmt=DBUtil.getPstmt(connection, sql);
		
		try {
			resultSet=pstmt.executeQuery();
			
			while (resultSet.next()) {
				
				candidate = new Candidate();
				resultToCandidate(resultSet, candidate);
				candidates.add(candidate);
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBUtil.closeAll(connection, pstmt, resultSet);
		}
		
		return candidates;
		
	}

	/**
	 * resultset转化为candidate对象
	 * 
	 * @param resultSet
	 * @param candidate
	 */
	public void resultToCandidate(ResultSet resultSet, Candidate candidate) {
		try {
			candidate.setId(resultSet.getInt("id"));
			candidate.setName(resultSet.getString("name"));
			candidate.setIntroduce(resultSet.getString("introduce"));
			candidate.setLogoUrl(resultSet.getString("logoUrl"));
			candidate.setFinancing(resultSet.getString("financing"));
			candidate.setMember(resultSet.getString("member"));
			candidate.setMode(resultSet.getString("mode"));
			candidate.setProductUrl(resultSet.getString("productUrl"));
			candidate.setProspectusUrl(resultSet.getString("prospectusUrl"));
			candidate.setVote(resultSet.getInt("vote"));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * 上传一位候选人
	 * @param candidate
	 */
	public void insertNew(Candidate candidate){
		Connection connection = null;
		PreparedStatement pstmt = null;
		
		String sql = "INSERT INTO candidate(name, introduce, logoUrl, financing, member, mode, productUrl, prospectusUrl, vote ) VALUES(?,?,?,?,?,?,?,?,?)";
		
		connection = DBUtil.getConnection();
		pstmt =DBUtil.getPstmt(connection, sql);
		
		try {
			pstmt.setString(1, candidate.getName());
			pstmt.setString(2, candidate.getIntroduce());
			pstmt.setString(3, candidate.getLogoUrl());
			pstmt.setString(4, candidate.getFinancing());
			pstmt.setString(5, candidate.getMember());
			pstmt.setString(6, candidate.getMode());
			pstmt.setString(7, candidate.getProductUrl());
			pstmt.setString(8, candidate.getProspectusUrl());
			pstmt.setInt(9, candidate.getVote());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBUtil.closeAll(connection, pstmt, null);
		}
		
	}

	/**
	 * 按照id查询此公司
	 * @param parseInt
	 * @return
	 */
	public Candidate showOne(int parseInt) {
		// TODO Auto-generated method stub
		
		Candidate candidate = null;
		
		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet resultSet = null;
		 
		String sql = "SELECT * FROM candidate WHERE id = ?";
		
		connection = DBUtil.getConnection();
		pstmt=DBUtil.getPstmt(connection, sql);
		try {
			pstmt.setInt(1, parseInt);
			resultSet=pstmt.executeQuery();
			while (resultSet.next()) {
				candidate = new Candidate();
				resultToCandidate(resultSet, candidate);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBUtil.closeAll(connection, pstmt, resultSet);
		}
		
		return candidate;
	}

}
