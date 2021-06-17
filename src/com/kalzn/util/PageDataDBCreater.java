package com.kalzn.util;

import java.util.Vector;

import com.kalzn.bean.Message;
import com.kalzn.bean.PageData;
import com.kalzn.bean.PageSet;
import com.kalzn.dbcon.Dbcon;

/**
 * 工厂设计模式。
 * 从database中构造PageData
 * @author Kalzn 18软件02 马明皓
 *
 */
final public class PageDataDBCreater {
	
	
	
	public static PageData<Message> createPageData(Dbcon con) throws Exception {
		Vector<Message> set = con.getMessage(Dbcon.ALL_ID,Dbcon.ALL_PULISHER, Dbcon.ALL_TITLE);
		PageData<Message> data = new PageData<Message>();
		PageSet<Message> page = new PageSet<Message>();
		for (Message ms : set) {
			if (page.isFull()) {
				data.add(page);
				page = new PageSet<Message>();
				page.add(ms);
			} else {
				try {
					page.add(ms);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		if (page.getSize()!=0) data.add(page);
		return data;
	}
	public static PageData<Message> createPageData(int messageid, String pulisher, String title, Dbcon con) throws Exception{
		Vector<Message> set = con.getMessage(messageid,pulisher, title);
		PageData<Message> data = new PageData<Message>();
		PageSet<Message> page = new PageSet<Message>();
		for (Message ms : set) {
			if (page.isFull()) {
				data.add(page);
				page = new PageSet<Message>();
				page.add(ms);
			} else {
				try {
					page.add(ms);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		if (page.getSize()!=0) data.add(page);
		return data;
	}
}
