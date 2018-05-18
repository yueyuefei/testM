package com.rockontrol.env.test;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import com.rockontrol.env.utils.HttpUtils;

import cn.hutool.core.lang.Console;

public class HttpTest {
	
	public static void main(String[] args) {
//		getTest("http://www.aqiapp.com/dm/devdata/list?type=3&number=0&size=400&mn=170201AF000014&timeBegin=1523408400000&timeEnd=1523419200000");
//		String url = "http://www.aqiapp.com/dm/devdata/list";
//		List<NameValuePair> list = new ArrayList<>();
//		list.add(new BasicNameValuePair("type", "3"));
//		list.add(new BasicNameValuePair("number", "0"));
//		list.add(new BasicNameValuePair("size", "300"));
//		list.add(new BasicNameValuePair("mn", "170201AF000014"));
//		list.add(new BasicNameValuePair("timeBegin", "1523408400000"));
//		list.add(new BasicNameValuePair("timeEnd", "1523419200000"));
//		postTest(url,list.toArray(new NameValuePair[list.size()]));
//		postFiles();
		String result = HttpUtils.get("http://www.aqiapp.com/file/del?ids=g2/M01/55/99/Qg0DAFr-PDqEalwYAAAAAAAAAAA661.png");
		//TODO need to detete
		System.out.println(result);
		
	}
	
	public static String getTest(String url ) {
		String result = HttpUtils.get(url);
		Console.log(result);
		return result;
	}
	
	public static String postTest(String url, NameValuePair... nvps) {
		String result = HttpUtils.post(url, nvps);
		Console.log(result);
		return result;
	}
	
	public static String postFiles() {
		String url = "http://www.aqiapp.com/file/batch/up";
		List<File> files = new ArrayList<>();
		File file = new File("D:\\line.png");
		files.add(file);
		String result = HttpUtils.postFiles(url, "file", files, null);
		Console.log(result);
		return result;
	}

}
