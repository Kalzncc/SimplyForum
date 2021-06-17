package com.kalzn.bean;
/**
 * javabean封装了BadWord，即屏蔽词的基本信息
 * @author Kalzn 18软件02 马明皓
 *
 */
public class BadWord {
	int dbid;
	String word;
	public BadWord() {
		dbid = 0; word = null;
	}
	public BadWord(int dbid, String word) {
		this.dbid = dbid;
		this.word = word;
	}
	public int getDbid() {
		return dbid;
	}
	public void setDbid(int dbid) {
		this.dbid = dbid;
	}
	public String getWord() {
		return word;
	}
	public void setWord(String word) {
		this.word = word;
	}
	
}
