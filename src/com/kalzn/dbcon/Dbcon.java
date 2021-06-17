package com.kalzn.dbcon;

import java.util.Vector;

import com.kalzn.bean.BadWord;
import com.kalzn.bean.Message;
import com.kalzn.bean.Reply;

import sun.reflect.annotation.ExceptionProxy;

/**
 * database链接操作的接口。
 * @author Kalzn 18软件02 马明皓
 *
 */
public interface Dbcon {
	/**
	 * 常量，表述检索所有id、pulisher 或者 context 
	 */
	final static int ALL_ID = -1;
	final static String ALL_PULISHER = null;
	final static String ALL_TITLE = null;
	
	/**
	 * 向database添加一条message
	 * @param ms
	 * @return
	 * @throws Exception
	 */
	void addMessage(Message ms) throws Exception;
	
	
	/**
	 * 向database添加一条reply
	 * @param py
	 * @return
	 * @throws Exception
	 */
	void addReply(Reply py)  throws Exception;
	
	
	/**
	 * 根据message id 删除
	 * @param Messageid
	 * @return
	 * @throws Exception
	 */
	void deleteMessage(int messageid)  throws Exception;
	
	/**
	 * 根据reply id 删除
	 * @param Replyid
	 * @return
	 * @throws Exception
	 */
	void deleteReply(int replyid)  throws Exception;
	
	/**
	 * 根据条件搜索database
	 * @param messageid  需要检索的id号，如果等于ALL_ID, 则检索所有id号
	 * @param pulisher 检索pulisher 如果为ALL_PULISHER，则检索所有pulisher
	 * @param title 检索title 如果为ALL_TITLE，则检索所有title
	 * @return
	 * @throws Exception
	 */
	Vector<Message> getMessage(int messageid, String pulisher, String title)  throws Exception;
	
	/**
	 * 管理员登陆
	 * @param username
	 * @param password
	 * @return
	 * @throws Exception
	 */
	boolean logIn(String username, String password)  throws Exception;
	
	/**
	 * 管理员注册
	 * @param usename
	 * @param password
	 * @return
	 * @throws Exception
	 */
	void signUp(String username, String password)  throws Exception;
	
	/**
	 * 添加屏蔽词
	 * @param badword
	 * @throws Exception
	 */
	void addBadWord(String badword) throws Exception; 
	
	/**
	 * 删除屏蔽词
	 * @param wordid
	 * @throws Exception
	 */
	void deleteBedWord(int wordid) throws Exception;
	
	/**
	 * 获取屏蔽词列表
	 * @return
	 * @throws Exception
	 */
	Vector<BadWord> getBadWord() throws Exception;
	
	/**
	 * 检查用户名
	 * @param username
	 * @return
	 * @throws Exception
	 */
	boolean checkUsername(String username) throws Exception;
}
