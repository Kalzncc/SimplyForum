package com.kalzn.service;

import java.util.Vector;

import com.kalzn.bean.BadWord;
import com.kalzn.dbcon.Dbcon;

/**
 * 管理员接口，继承用户服务接口，在提供普通用户服务项目的基础上
 * 提供登陆、删除留言和回复的功能
 * @author Kalzn 18软件02 马明皓
 *
 */
public interface AdminService extends UserService {
	/**
	 * 管理员登陆接口
	 * @param username 用户名
	 * @param password 密码
	 * @param con 数据库接口
	 * @return
	 * @throws Exception
	 */
	boolean loginIn(String username,  String password) throws Exception;
	
	/**
	 * 管理员注册接口
	 * @param username
	 * @param password
	 * @throws Exception
	 */
	void signUp(String username, String password) throws Exception;
	
	/**
	 * 删除信息接口
	 * @param Messageid 
	 * @param con 数据库接口
	 * @throws Exception
	 */
	void deleteMessage(int Messageid) throws Exception;
	
	
	/**
	 * 删除信息接口
	 * @param Replyid
	 * @param con 数据库链接
	 * @throws Exception
	 */
	void deleteReply(int Replyidn) throws Exception;
	
	/**
	 * 添加屏蔽词
	 * @param word
	 * @throws Exception
	 */
	void addBadWord(String word) throws Exception;
	
	/**
	 * 删除屏蔽词
	 * @param wordid
	 * @throws Exception
	 */
	void deleteBadWord(int wordid) throws Exception;
	
	/**
	 * 获得屏蔽词列表
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
