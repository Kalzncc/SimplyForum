package com.kalzn.service.impl;

import java.util.Date;

import com.kalzn.bean.Message;
import com.kalzn.bean.PageData;
import com.kalzn.bean.PageSet;
import com.kalzn.bean.Reply;
import com.kalzn.dbcon.Dbcon;
import com.kalzn.dbcon.impl.MysqlServerCon;
import com.kalzn.service.UserService;
import com.kalzn.util.PageDataDBCreater;

/**
 * 实现了用户基础的请求
 * @author Kalzn 18软件02 马明皓
 *
 */
public class UserServiceImpl implements UserService {
	@Override
	public void postMessage(String title, String pulisher, String context) throws Exception {
		Message ms = new Message();
		ms.setTitle(title);
		ms.setContext(context);
		ms.setPulisher(pulisher);
		ms.setReplies(null);
		ms.setReplynum(0);
		ms.setComtime(new Date());
		(new MysqlServerCon()).addMessage(ms);
	}
	@Override
	public void replyMessage(int Messageid, String pulisher, String context) throws Exception {
		Reply py = new Reply();
		py.setPulisher(pulisher);
		py.setMessageid(Messageid);
		py.setContext(context);
		py.setComtime(new Date());
		(new MysqlServerCon()).addReply(py);
	}
	@Override
	public PageData<Message> getPageData() throws Exception {
		return PageDataDBCreater.createPageData((new MysqlServerCon()));
	}
	@Override
	public PageData<Message> getPageData(int messageid, String pulisher, String title) throws Exception {
		return PageDataDBCreater.createPageData(messageid,pulisher,title, (new MysqlServerCon()));
	}
	
}
