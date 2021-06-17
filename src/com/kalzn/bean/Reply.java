package com.kalzn.bean;
import java.util.Date;

/**
 * javabean 回复留言信息 
 * @author Kalzn 18软件02 马明皓
 *
 */
public class Reply {
	private int dbid;
	private String pulisher;
	private int messageid;
	private Date comtime;
	private String context;
	public int getDbid() {
		return dbid;
	}
	public void setDbid(int dbid) {
		this.dbid = dbid;
	}
	public String getPulisher() {
		return pulisher;
	}
	public void setPulisher(String pulisher) {
		this.pulisher = pulisher;
	}
	public int getMessageid() {
		return messageid;
	}
	public void setMessageid(int messageid) {
		this.messageid = messageid;
	}
	public Date getComtime() {
		return comtime;
	}
	public void setComtime(Date comtime) {
		this.comtime = comtime;
	}
	public String getContext() {
		return context;
	}
	public void setContext(String context) {
		this.context = context;
	}
	public static void main(String[] args) { }
}
