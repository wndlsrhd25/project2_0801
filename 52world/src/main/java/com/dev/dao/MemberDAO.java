package com.dev.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.dev.vo.MemberVO;

public class MemberDAO {

	Connection conn;
	PreparedStatement pstmt;
	ResultSet rs;
	
	public void connect() {
		try {
			InitialContext ic = new InitialContext();
			//톰캣은 가상메모리?가 java:comp/env 이렇게
			DataSource ds = (DataSource) ic.lookup("java:comp/env/jdbc/myoracle");
			conn = ds.getConnection();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void disconnect() {
		try {
			if(rs != null) 
				rs.close();
			if(pstmt != null) 
				pstmt.close();
			if(conn != null) 
				conn.close();
		}catch(SQLException e) {
			e.printStackTrace();
		}
	
	}
	
	//입력 
	public void insertMember (MemberVO vo) {
		String sql="insert into member(id,password,name, birth,gender,email,phone) values(?,?,?,?,?,?,?)";
		connect();
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, vo.getId());
			pstmt.setString(2, vo.getPassword());
			pstmt.setString(3, vo.getName());
			pstmt.setDate(4, vo.getBirth());
			pstmt.setString(5, vo.getGender());
			pstmt.setString(6, vo.getEmail());
			pstmt.setString(7, vo.getPhone());
			
			int r = pstmt.executeUpdate();
			
			System.out.println(r+"건 입력");
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			disconnect();
		}
	}
	
	
	
	//아이디 중복체크
	public boolean checkId(String id) {
		
		String sql="select count(*) from member where id= ?";
		connect();
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1,id);
			
			int r = pstmt.executeUpdate();
			if(r>0) {
				//동일 아이디가 있는 경우 1이 반환
				return true;
			}
					
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
		//동일아이디가 없는 경우 0을 반환
		return false;
	}

	//리스트
	public List<MemberVO> getList(){
		String sql = "select * from member order by 1";
		List<MemberVO> list = new ArrayList<>();
		connect();
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				MemberVO vo = new MemberVO();
				vo.setId(rs.getString("id"));
				vo.setPassword(rs.getString("password"));
				vo.setName(rs.getNString("name"));
				vo.setBirth(rs.getDate("birth"));
				vo.setGender(rs.getString("gender"));
				vo.setEmail(rs.getNString("email"));
				vo.setPhone(rs.getString("phone"));
				
				list.add(vo);
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		} finally {
			disconnect();
		}
		return list;
	}
	
	//한건 조회 (id)
	public MemberVO searchMember(String id) {
		String sql = "select * from member where id=?";
		connect();
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1,id);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				MemberVO vo = new MemberVO();
				vo.setId(rs.getString("id"));
				vo.setPassword(rs.getString("password"));
				vo.setName(rs.getNString("name"));
				vo.setBirth(rs.getDate("birth"));
				vo.setGender(rs.getString("gender"));
				vo.setEmail(rs.getNString("email"));
				vo.setPhone(rs.getString("phone"));
				
				return vo;
			}
					
		} catch (SQLException e) {
			
			e.printStackTrace();
		} finally {
			disconnect();
		}
		
		return null;
	}
	
	//삭제
	public boolean DeleteMember(String id) {
		String sql="delete member where id=?";
		connect();
		
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, id);
			
			int r = pstmt.executeUpdate();
			System.out.println(r+"건 삭제");
		
			if(r>0) {
			return true;
			} 
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}finally {
			disconnect();
		}
		return false;
		
	}
}
