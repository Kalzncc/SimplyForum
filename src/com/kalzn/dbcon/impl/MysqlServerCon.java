package com.kalzn.dbcon.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;

import com.kalzn.bean.BadWord;
import com.kalzn.bean.Message;
import com.kalzn.bean.Reply;
import com.kalzn.dbcon.Dbcon;

/**
 * 数据库接口实现 
 * @author Kalzn 18软件02 马明皓
 *
 */
public class MysqlServerCon implements Dbcon {
	@Override
	public void addMessage(Message ms) throws Exception {
		Class.forName("com.mysql.cj.jdbc.Driver");		
		String url = "jdbc:mysql://154.8.152.140:3306/java_web_db_task8?useUnicode=true&characterEncoding=utf-8&autoReconnecte=true&failOverReadOnly=false&useSSL=false&serverTimezone=UTC";
		String un = "root";
		String pd = "000826...MMHPP";
		Connection con = null;
		try {
			con = DriverManager.getConnection(url, un, pd);
			PreparedStatement stmt = con.prepareStatement("insert into message values(null, ?, ?, ?, ?, 0)");
			stmt.setString(1, (new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(ms.getComtime())) );
			System.out.println( (new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(ms.getComtime())));
			stmt.setString(2, ms.getPulisher());
			stmt.setString(3, ms.getTitle());
			stmt.setString(4, ms.getContext());
			stmt.execute();
		} finally {
			con.close();
		}
	}
	@Override
	public void addReply(Reply py) throws Exception {
		Class.forName("com.mysql.cj.jdbc.Driver");		
		String url = "jdbc:mysql://154.8.152.140:3306/java_web_db_task8?useUnicode=true&characterEncoding=utf-8&autoReconnecte=true&failOverReadOnly=false&useSSL=false&serverTimezone=UTC";
		String un = "root";
		String pd = "000826...MMHPP";
		Connection con = null;
		try {
			con = DriverManager.getConnection(url, un, pd);
			PreparedStatement stmt = con.prepareStatement("insert into reply values(null, ?, ?, ?, ?)");
			stmt.setInt(1, py.getMessageid());
			stmt.setString(2, py.getPulisher());
			stmt.setString(3, (new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(py.getComtime())) );
			stmt.setString(4, py.getContext());
			stmt.execute();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			throw e;
		}finally {
			con.close();
		}
	}
	@Override
	public void deleteMessage(int messageid) throws Exception {
		Class.forName("com.mysql.cj.jdbc.Driver");		
		String url = "jdbc:mysql://154.8.152.140:3306/java_web_db_task8?useUnicode=true&characterEncoding=utf-8&autoReconnecte=true&failOverReadOnly=false&useSSL=false&serverTimezone=UTC";
		String un = "root";
		String pd = "000826...MMHPP";
		Connection con = null;
		try {
			con = DriverManager.getConnection(url, un, pd);
			con.setAutoCommit(false);
			PreparedStatement stmt = con.prepareStatement("delete from reply where messageid=?");
			stmt.setInt(1, messageid);
			stmt.execute();
			
			stmt = con.prepareStatement("delete from message where id=?");
			stmt.setInt(1, messageid);
			stmt.execute();
		} catch (Exception e) {
			con.rollback();
			throw e;
		}finally {
			con.commit();
			con.close();
		}
	}
	@Override
	public void deleteReply(int replyid) throws Exception {
		Class.forName("com.mysql.cj.jdbc.Driver");		
		String url = "jdbc:mysql://154.8.152.140:3306/java_web_db_task8?useUnicode=true&characterEncoding=utf-8&autoReconnecte=true&failOverReadOnly=false&useSSL=false&serverTimezone=UTC";
		String un = "root";
		String pd = "000826...MMHPP";
		Connection con = null;
		try {
			con = DriverManager.getConnection(url, un, pd);
			PreparedStatement stmt = con.prepareStatement("delete from reply where id=?");
			stmt.setInt(1, replyid);
			stmt.execute();
		} finally {
			con.close();
		}
		
	}
	@Override
	public boolean logIn(String username, String password) throws Exception {
		Class.forName("com.mysql.cj.jdbc.Driver");		
		String url = "jdbc:mysql://154.8.152.140:3306/java_web_db_task8?useUnicode=true&characterEncoding=utf-8&autoReconnecte=true&failOverReadOnly=false&useSSL=false&serverTimezone=UTC";
		String un = "root";
		String pd = "000826...MMHPP";
		Connection con = null;
		try {
			con = DriverManager.getConnection(url, un, pd);
			PreparedStatement stmt = con.prepareStatement("select password from admin where adminname=?");
			stmt.setString(1, username);
			ResultSet set = stmt.executeQuery();
			
			String rightpassword = null;
			while(set.next()) {
				rightpassword = set.getString(1);
			}
			if (rightpassword == null || !rightpassword.equals(password)) {
				return false;
			}
			return true;
			
		} finally {
			con.close();
		}
	}
	@Override
	public void signUp(String username, String password) throws Exception {
		Class.forName("com.mysql.cj.jdbc.Driver");		
		String url = "jdbc:mysql://154.8.152.140:3306/java_web_db_task8?useUnicode=true&characterEncoding=utf-8&autoReconnecte=true&failOverReadOnly=false&useSSL=false&serverTimezone=UTC";
		String un = "root";
		String pd = "000826...MMHPP";
		Connection con = null;
		try {
			con = DriverManager.getConnection(url, un, pd);
			PreparedStatement stmt = con.prepareStatement("insert into admin values(?,?)");
			stmt.setString(1, username);
			stmt.setString(2, password);
			stmt.execute();
			
		} finally {
			con.close();
		}
		return;
	}
	@Override
	public Vector<Message> getMessage(int messageid, String pulisher, String title) throws Exception {
		Vector<Message> res = new Vector<Message>();
		Class.forName("com.mysql.cj.jdbc.Driver");		
		String url = "jdbc:mysql://154.8.152.140:3306/java_web_db_task8?useUnicode=true&characterEncoding=utf-8&autoReconnecte=true&failOverReadOnly=false&useSSL=false&serverTimezone=UTC";
		String un = "root";
		String pd = "000826...MMHPP";
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Connection con = null;
		try {
			con = DriverManager.getConnection(url, un, pd);
			ResultSet set = null;
			if (messageid != Dbcon.ALL_ID) { 
				PreparedStatement stmt = con.prepareStatement("select * from message where id = ? and pulisher like ? and title like ? order by time desc, replynum asc");
				stmt.setInt(1, messageid);
				if (pulisher != Dbcon.ALL_PULISHER) stmt.setString(2, "%"+pulisher+"%");
				else stmt.setString(2, "%");
				if (pulisher != Dbcon.ALL_TITLE)  stmt.setString(3, "%"+title+"%");
				stmt.setString(3, "%");
				set = stmt.executeQuery();
			} else {
				PreparedStatement stmt = con.prepareStatement("select * from message where pulisher like ? and title like ? order by time desc, replynum asc");
				if (pulisher != Dbcon.ALL_PULISHER) stmt.setString(1, "%"+pulisher+"%");
				else stmt.setString(1, "%");
				if (title != Dbcon.ALL_TITLE)  stmt.setString(2, "%"+title+"%");
				else stmt.setString(2, "%");
				set = stmt.executeQuery();
			}
			while(set.next()) {
				Message ms = new Message();
				ms.setDbid(set.getInt(1));
				ms.setComtime(format.parse(set.getString(2)));
				ms.setPulisher(set.getString(3));
				ms.setTitle(set.getString(4));
				ms.setContext(set.getString(5));
				ms.setReplynum(set.getInt(6));
				PreparedStatement stmt = con.prepareStatement("select * from reply where messageid=?");
				stmt.setInt(1, ms.getDbid());
				ResultSet reset = stmt.executeQuery();
				Vector<Reply> Replyset = new Vector<Reply>();
				while(reset.next()) {
					Reply re = new Reply();
					re.setDbid(reset.getInt(1));
					re.setMessageid(reset.getInt(2));
					re.setPulisher(reset.getString(3));
					re.setComtime(format.parse(reset.getString(4)));
					re.setContext(reset.getString(5));
					Replyset.add(re);
				}
				ms.setReplies(Replyset);
				res.add(ms);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			con.close();
		}
		return res;
	}
	@Override
	public void addBadWord(String badword) throws Exception {
		Class.forName("com.mysql.cj.jdbc.Driver");		
		String url = "jdbc:mysql://154.8.152.140:3306/java_web_db_task8?useUnicode=true&characterEncoding=utf-8&autoReconnecte=true&failOverReadOnly=false&useSSL=false&serverTimezone=UTC";
		String un = "root";
		String pd = "000826...MMHPP";
		Connection con = null;
		try {
			con = DriverManager.getConnection(url, un, pd);
			PreparedStatement stmt = con.prepareStatement("insert into badword values(null,?)");
			stmt.setString(1, badword);
			stmt.execute();
			
		} finally {
			con.close();
		}
		return;
		
	}
	@Override
	public void deleteBedWord(int wordid) throws Exception {
		Class.forName("com.mysql.cj.jdbc.Driver");		
		String url = "jdbc:mysql://154.8.152.140:3306/java_web_db_task8?useUnicode=true&characterEncoding=utf-8&autoReconnecte=true&failOverReadOnly=false&useSSL=false&serverTimezone=UTC";
		String un = "root";
		String pd = "000826...MMHPP";
		Connection con = null;
		try {
			con = DriverManager.getConnection(url, un, pd);
			PreparedStatement stmt = con.prepareStatement("delete from badword where id=?");
			stmt.setInt(1, wordid);
			stmt.execute();
		} finally {
			con.close();
		}
		
	}
	@Override
	public Vector<BadWord> getBadWord() throws Exception {
		Class.forName("com.mysql.cj.jdbc.Driver");		
		String url = "jdbc:mysql://154.8.152.140:3306/java_web_db_task8?useUnicode=true&characterEncoding=utf-8&autoReconnecte=true&failOverReadOnly=false&useSSL=false&serverTimezone=UTC";
		String un = "root";
		String pd = "000826...MMHPP";
		Vector<BadWord> res = new Vector<BadWord>();
		Connection con = null;
		try {
			con = DriverManager.getConnection(url, un, pd);
			PreparedStatement stmt = con.prepareStatement("select * from badword");
			ResultSet set = stmt.executeQuery();
			while(set.next()) {
				res.add(new BadWord(set.getInt(1), set.getString(2)) );
			}
			
		} finally {
			con.close();
		}
		return res;
	}
	@Override
	public boolean checkUsername(String username) throws Exception {
		Class.forName("com.mysql.cj.jdbc.Driver");		
		String url = "jdbc:mysql://154.8.152.140:3306/java_web_db_task8?useUnicode=true&characterEncoding=utf-8&autoReconnecte=true&failOverReadOnly=false&useSSL=false&serverTimezone=UTC";
		String un = "root";
		String pd = "000826...MMHPP";
		Connection con = null;
		try {
			con = DriverManager.getConnection(url, un, pd);
			PreparedStatement stmt = con.prepareStatement("select password from admin where adminname=?");
			stmt.setString(1, username);
			ResultSet set = stmt.executeQuery();
			String rightpassword = null;
			while(set.next()) {
				rightpassword = set.getString(1);
			}
			if (rightpassword == null) {
				return true;
			}
			return false;
			
		} finally {
			con.close();
		}
		
	}
}
