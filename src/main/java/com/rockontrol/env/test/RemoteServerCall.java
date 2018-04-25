package com.rockontrol.env.test;


import org.appserver.android.api.rpc.PCService;
import org.appserver.android.api.rpc.Request;
import org.appserver.android.api.rpc.Response;

/**
 * 远程服务调用
 * @author lvshengchao 2014-12-26 下午3:29:35
 *
 */
public class RemoteServerCall {
	
	String serverIP;
	
	String serverPort;
	
	/**
	 * 调用远程服务器上的服务
	 * @param request
	 * @return Response可以看成是String类的Map
	 * @throws Exception
	 *
	 * @author lvshengchao 2014-12-26 下午3:35:55
	 */
	public  Response schedule(Request request) throws Exception {
		if(serverIP == null || serverPort == null){
			throw new Exception("没有设置远程服务IP或PORT");
		}
		Response response = PCService.invoke( serverIP,serverPort,request,"lvshengchao");
		if(response.getAttribute("idm-error") != null){
			throw new Exception(response.getAttribute("idm-error"));
		}
		return response;
		
	}
}
