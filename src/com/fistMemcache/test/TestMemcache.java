package com.fistMemcache.test;

import java.util.Date;

import com.danga.MemCached.MemCachedClient;
import com.danga.MemCached.SockIOPool;

public class TestMemcache {

	public static void main(String[] args) {

		MemCachedClient client = new MemCachedClient();
		String[] addr = { "127.0.0.1:11211" };
		Integer[] weight = { 3 };
		SockIOPool pool = SockIOPool.getInstance();
		pool.setServers(addr);
		pool.setWeights(weight);
		pool.setInitConn(5);
		pool.setMinConn(5);
		pool.setMaxConn(200);
		pool.setMaxIdle(1000 * 30 * 30);
		pool.setMaintSleep(30);
		pool.setNagle(false);
		pool.setSocketTO(30);
		pool.setSocketConnectTO(0);
		pool.initialize();
 
	   client.set("test", "test");//将数据放入缓存
		
	   Date date = new Date(20000);
	   client.set("test1", "test1",date);//将数据放入缓存并设置失效时间
	   
	   client.delete("test");//删除缓存
	   
	   String str = (String)client.get("test1");//获取缓存
	   System.out.println(str);
	}
}
