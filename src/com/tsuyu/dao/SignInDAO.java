package com.tsuyu.dao;

import com.tsuyu.model.AccessLevel;
import com.tsuyu.model.Accordian;
import com.tsuyu.model.SignIn;
import com.tsuyu.model.User;
import com.tsuyu.persistance.ConnectionDB;
import com.tsuyu.persistance.HibernateUtil;
import com.tsuyu.util.Util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;

public class SignInDAO {
	
	private Session session;
	private Util util;
	
	public SignInDAO(){
		this.util = new Util();
	}
	
	public ArrayList<SignIn> showSigninGrid(){
		session = HibernateUtil.getSessionFactory().openSession();

		ArrayList<SignIn> signAll = new ArrayList<SignIn>();
		
		String hql = "select b.signinId as signinId, b.userName as userName, b.password as password," +
				"c.name as name, c.email as email, a.accessLevelName as accessLevelName from AccessLevel a join a.signIns b join b.user c";     
    	 List data = session.createQuery(hql).setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP)
  		.list();
     	 for (Object object : data){
  		  Map row = (Map)object;
  		  AccessLevel accMap = new AccessLevel();
  		  accMap.setAccessLevelName(row.get("accessLevelName").toString());
  		  SignIn signMap = new SignIn();
  		  signMap.setSigninId(Integer.parseInt(row.get("signinId").toString()));
  		  signMap.setUserName(row.get("userName").toString());
  		  User userMap = new User();
  		  userMap.setName(row.get("name").toString());
  		  userMap.setEmail(row.get("email").toString());
  		  signMap.setUser(userMap);
  		  signMap.setAccessLevel(accMap);
  		  signAll.add(signMap);
  		 }
     	 
		session.close();
		return signAll;
	}
	public int addUser(SignIn user) throws ClassNotFoundException,
			SQLException {
		String hashPass = String.valueOf(user.getPassword().hashCode());

		Connection conn = ConnectionDB.connectDB();

		PreparedStatement prep = conn
				.prepareStatement("INSERT INTO sign_in(user_id,password,access_level) VALUES (?,?,?)");

		//prep.setString(1, user.getUserId());
		prep.setString(2, hashPass);

		int updateStatus = prep.executeUpdate();

		if (conn != null) {
			conn.close();
		}
		return updateStatus;
	}

	public String tempPassword(String password) {
		String baru = password.trim().substring(10, 14);
		return baru;
	}

	public boolean login(HttpServletRequest request)
			throws ClassNotFoundException, SQLException {
		
		String username = request.getParameter("username");
		String pass = String.valueOf(request.getParameter("password").hashCode());
        
		session = HibernateUtil.getSessionFactory().openSession();
		
		Query query = session
		.createQuery("select count(*) from SignIn si where si.userName = :userName" +
				" and si.password = :password").setParameter("userName",username.trim())
		.setParameter("password",pass);
        Long result = (Long) query.uniqueResult();
		
		
		session.close();
		
		 if (result == 1) {
			 return true;
         } else {
             return false;
         }
	
	}

	public Boolean checkFirstLogin(String userid, String password)
			throws ClassNotFoundException, SQLException {
		String pass1 = "12345";
		String pass2 = String.valueOf(pass1.hashCode());

		Boolean yes = Boolean.valueOf(false);
		Connection conn = ConnectionDB.connectDB();

		PreparedStatement pre = conn
				.prepareStatement("SELECT password FROM sign_in where user_id = ? and password = ? ");

		pre.setString(1, userid);
		pre.setString(2, pass2);

		ResultSet rs = pre.executeQuery();

		if (rs.next()) {
			yes = Boolean.valueOf(true);
		}

		if (conn != null) {
			conn.close();
		}

		return yes;
	}

	public int deleteAccount(String userid) throws ClassNotFoundException,
			SQLException {
		Connection conn = ConnectionDB.connectDB();

		PreparedStatement prep = conn
				.prepareStatement("DELETE FROM sign_in WHERE user_id = ?");

		prep.setString(1, userid);

		int updateStatus = prep.executeUpdate();

		if (conn != null) {
			conn.close();
		}
		return updateStatus;
	}

	public int checkPassword(String user_id, String password)
			throws ClassNotFoundException, SQLException {
		int updateStatus = 0;
		String pass = String.valueOf(password.hashCode());

		Connection conn = ConnectionDB.connectDB();

		PreparedStatement prep = conn
				.prepareStatement("SELECT COUNT(user_id) FROM sign_in WHERE user_id = ? AND password = ?");
		prep.setString(1, user_id);
		prep.setString(2, pass);

		ResultSet resultset = prep.executeQuery();

		while (resultset.next()) {
			updateStatus = resultset.getInt(1);
		}

		if (conn != null) {
			conn.close();
		}
		return updateStatus;
	}

	public int changePassword(String user_id, String password)
			throws ClassNotFoundException, SQLException {
		String pass = String.valueOf(password.hashCode());

		Connection conn = ConnectionDB.connectDB();

		PreparedStatement prep = conn
				.prepareStatement("UPDATE sign_in SET password = ? WHERE user_id = ?");
		prep.setString(1, pass);
		prep.setString(2, user_id);

		int updateStatus = prep.executeUpdate();

		if (conn != null) {
			conn.close();
		}
		return updateStatus;
	}

	public int checkStaffAvailable(String id) throws ClassNotFoundException,
			SQLException {
		int updateStatus = 0;

		Connection conn = ConnectionDB.connectDB();

		PreparedStatement prep = conn
				.prepareStatement("SELECT COUNT(user_id) FROM sign_in WHERE user_id = ?");
		prep.setString(1, id);

		ResultSet resultset = prep.executeQuery();

		while (resultset.next()) {
			updateStatus = resultset.getInt(1);
		}

		if (conn != null) {
			conn.close();
		}
		return updateStatus;
	}
}