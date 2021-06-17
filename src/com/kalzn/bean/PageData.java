package com.kalzn.bean;

import java.util.Iterator;
import java.util.Vector;

/**
 * javabean 封装了页面数据的信息
 * implements Iterable<T> 迭代器设计模式，使得PageData可迭代其中的信息
 * @author Kalzn 18软件02 马明皓
 *
 */
public class PageData<T> implements Iterable<PageSet<T> >{ 
	/**
	 * PageMessageSet为util中的类，封装了一页的Message信息 
	 */
	private Vector<PageSet<T>> set;
	
	/**
	 * 当前页
	 */
	private int curindex = 0;
	
	
	public PageData() {
		set = new Vector<PageSet<T>>();
		curindex = 0;
	}
	public PageData(Vector<PageSet<T>> set) {
		this.set = set;
		curindex = 0;
	}
	
	public void setData(Vector<PageSet<T>> set) {
		this.set = set;
		curindex = 0;
	}
	/**
	 * 向Set中添加page页
	 * @param page
	 */
	public void add(PageSet<T> page) { 
		set.add(page);
	}
	/**
	 * 获取当前最大页码
	 * @return
	 */
	public int getPageNum() {
		return set.size();
	}
	
	/**
	 * 获取当前页页码
	 * @return
	 */
	public int getCurNum() {
		return curindex;
	}
	public PageSet<T> getCurPage() {
		return set.get(curindex);
	}
	
	/**
	 * 翻页：下一页
	 */
	public void nextPage() {
		curindex++;
		curindex = Math.min(curindex, set.size()-1);
	}
	
	/**
	 * 翻页：上一页
	 */
	public void prePage() {
		curindex--;
		curindex = Math.max(0, curindex);
	}
	
	/**
	 * 获得下一页的页码
	 * @return
	 */
	public int getNextPageNum() {
		return Math.min(curindex+1, set.size()-1);
	}
	
	/**
	 * 获得上一页页码
	 * @return
	 */
	public int getPrePageNum() {
		return Math.max(0, curindex-1);
	}
	
	/**
	 * 获得当选页的信息
	 * @param index
	 * @return
	 */
	public PageSet<T> getPage(int index) {
		curindex = index;
		return set.get(index);
	}
	
	
	/**
	 * 迭代器
	 */
	@Override
	public Iterator<PageSet<T>> iterator() {
		// TODO Auto-generated method stub
		class iter implements Iterator<PageSet<T>>
        {
			int cur = 0;
            @Override
            public boolean hasNext() {
            	return cur < set.size();
            }
            @Override
            public PageSet<T> next() {
               return set.get(cur++);
            }
            @Override
            public void remove() {} 
        }
        return new iter();
	}
	
}
