package com.kalzn.bean;

import java.util.Iterator;
import java.util.Vector;
/**
 * 单页Page信息
 * @author Kalzn 18软件02 马明皓
 *
 */
public class PageSet<T> implements Iterable<T> {
	/**
	 * 每页的最大信息条数
	 */
	final static int MAX_PAGE = 10; 
	private Vector<T> set;
	
	
	public PageSet() {
		set = new Vector<T>();
	}
	public boolean isFull() {
		return set.size() >= MAX_PAGE;
	}
	public void add(T mess) throws Exception {
		if (isFull()) throw new RuntimeException("Page is full!");
		set.add(mess);
	}
	public int getSize() {
		return set.size();
	}
	public T get(int index) {
		return set.get(index);
	}
	@Override
	public Iterator<T> iterator() {
		// TODO Auto-generated method stub
		class iter implements Iterator<T>
        {
			int cur = 0;
            @Override
            public boolean hasNext() {
            	return cur < set.size();
            }
            @Override
            public T next() {
               return set.get(cur++);
            }
            @Override
            public void remove() {} 
        }
        return new iter();
	}
}
