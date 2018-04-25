package com.rockontrol.env.test;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import org.apache.commons.lang.math.NumberUtils;

import com.rockontrol.env.utils.Client;


public class msgTest {
	private static boolean flag = true;
	public static void main(String[] args) throws UnsupportedEncodingException{
		//输入软件序列号和密码

		String sn="SDK-WSS-010-09560";
		String pwd="1-ffe2-[";
		Client client=new Client(sn,pwd);
		
		//查询余额
//		String result_balance = client.getBalance();
//		System.out.println("您的余额为 : "+result_balance);
//		if(NumberUtils.toInt(result_balance)<=50000 && flag){
//			flag = false;
//		}
//		if(NumberUtils.toInt(result_balance)>50000){
//			flag = true;
//		}else{
//			System.out.println("请尽快充值");
//		}
		String result_mt = client.mdsmssend("18636181748", URLEncoder.encode("测试1【罗克佳华】","UTF-8"), "", "", "","");
		System.out.println(result_mt);
	}

}
