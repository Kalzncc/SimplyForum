package com.kalzn.bean;

import java.util.Date;
import java.util.Vector;

/**
 * javabean 封装了留言的基本信息
 * @author Kalzn 18软件02 马明皓
 *
 */
public class Message {
	private int dbid;
	private Date comtime;
	private String pulisher;
	private String title;
	private String context;
	private int replynum;
	private Vector<Reply> replies;
	public Message() {
		replies = new Vector<Reply>();
	}
	public int getDbid() {
		return dbid;
	}
	public void setDbid(int dbid) {
		this.dbid = dbid;
	}
	public Date getComtime() {
		return comtime;
	}
	public void setComtime(Date comtime) {
		this.comtime = comtime;
	}
	public String getPulisher() {
		return pulisher;
	}
	public void setPulisher(String pulisher) {
		this.pulisher = pulisher;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContext() {
		return context;
	}
	public void setContext(String context) {
		this.context = context;
	}
	public int getReplynum() {
		return replynum;
	}
	public void setReplynum(int replynum) {
		this.replynum = replynum;
	}
	public Vector<Reply> getReplies() {
		return replies;
	}
	public void setReplies(Vector<Reply> replies) {
		this.replies = replies;
	}
	
}
