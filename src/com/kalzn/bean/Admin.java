package com.kalzn.bean;
/**
 * javabean：封装了Admin的信息 
 * @author Kalzn 18软件02 马明皓
 *
 */
public class Admin {
	private int dbid;
	private String adminname;
	private String password;
	public int getDbid() {
		return dbid;
	}
	public void setDbid(int dbid) {
		this.dbid = dbid;
	}
	public String getAdminname() {
		return adminname;
	}
	public void setAdminname(String adminname) {
		this.adminname = adminname;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public static void main(String[] args) {
		
	}

}
