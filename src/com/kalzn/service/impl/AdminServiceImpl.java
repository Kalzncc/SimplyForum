package com.kalzn.service.impl;

import java.util.Vector;

import com.kalzn.bean.BadWord;
import com.kalzn.bean.PageData;
import com.kalzn.bean.PageSet;
import com.kalzn.dbcon.Dbcon;
import com.kalzn.dbcon.impl.MysqlServerCon;
import com.kalzn.service.AdminService;
import com.kalzn.util.MD5;

/**
 * 实现了管理员的各服务请求 
 * @author Kalzn 18软件02 马明皓
 *
 */
public class AdminServiceImpl extends UserServiceImpl implements AdminService {
	@Override
	public boolean loginIn(String username, String password) throws Exception {
		return (new MysqlServerCon()).logIn(username, MD5.md5(password) );
	}
	@Override
	public void deleteMessage(int Messageid) throws Exception {
		(new MysqlServerCon()).deleteMessage(Messageid);
	}
	@Override
	public void deleteReply(int Replyid) throws Exception {
		(new MysqlServerCon()).deleteReply(Replyid);
	}
	@Override
	public void addBadWord(String word) throws Exception {
		(new MysqlServerCon()).addBadWord(word);
		
	}
	@Override
	public void deleteBadWord(int wordid) throws Exception {
		(new MysqlServerCon()).deleteBedWord(wordid);
		
	}
	@Override
	public Vector<BadWord> getBadWord() throws Exception {
		return (new MysqlServerCon()).getBadWord();
	}
	@Override
	public void signUp(String username, String password) throws Exception {
		(new MysqlServerCon()).signUp(username, MD5.md5(password));
	}
	@Override
	public boolean checkUsername(String username) throws Exception {
		return (new MysqlServerCon()).checkUsername(username);
	}
}
