package com.kalzn.service;

import com.kalzn.bean.Message;
import com.kalzn.bean.PageData;
import com.kalzn.bean.PageSet;
import com.kalzn.dbcon.Dbcon;
/**
 * 用户服务接口 
 * @author Kalzn 18软件02 马明皓
 */
public interface UserService {
	/**
	 * 发布评论接口
	 * @param title
	 * @param pulisher
	 * @param context
	 * @param con 数据库接口
	 * @throws Exception
	 */
	void postMessage(String title, String pulisher, String context) throws Exception;
	
	
	/**
	 * 回复信息接口
	 * @param Messageid
	 * @param pulisher
	 * @param context
	 * @param con 数据库接口
	 * @throws Exception
	 */
	void replyMessage(int Messageid, String pulisher, String context) throws Exception;
	
	
	/**
	 * 获得分页后的信息
	 * @param con 数据库接口
	 * @return
	 * @throws Exception
	 */
	PageData<Message> getPageData() throws Exception;
	
	/**
	 * 获得搜索分页后的信息
	 * @param con 数据库接口
	 * @return
	 * @throws Exception
	 */
	PageData<Message> getPageData(int messageid, String pulisher, String title) throws Exception;
}
