package com.rockontrol.env.test;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.math.NumberUtils;
import org.appserver.android.api.rpc.Request;
import org.appserver.android.api.rpc.Response;

import com.google.gson.Gson;
import com.rockontrol.env.utils.DataRecode_gzip;

public class appserverTest {

	static Gson gson = new Gson();
	public static void main(String[] args) throws Exception {
//		testAqi();
//		testTimerSendMsg();
//		testData();
//		testGetNearestInfo();
//		testList();
//		deleteDetail();
//		testUpload();
//		testGetNetTime();
//		testDetail();
//		testMsg();
//		testExcelGb();
		
	}
	
	/**
	 * excel导出国标站点数据
	 * @throws Exception
	 */
	private static void testExcelGb()throws Exception {
		
		long l = System.currentTimeMillis();

		final RemoteServerCall remoteServerCall = new RemoteServerCall();
		
		remoteServerCall.serverIP = "192.168.200.178";
		remoteServerCall.serverPort = "1502";
//		remoteServerCall.serverIP = "ty.aqiapp.com";
//		remoteServerCall.serverPort = "8414";

		Request request = new Request("web://WeatherStationInfoNewService");
		request.setAttribute("rmi", "CreatSimulationData");
		Response response = remoteServerCall.schedule(request);
		String message = response.getAttribute("message");
		String data = response.getAttribute("data");
		System.out.println(message);
		System.out.println(data);
		
		System.out.println("调用耗时：" + ( System.currentTimeMillis() - l ) +" ms");
	}
	 
	/**
	 * 获取最近设备健康指数和信息
	 * @throws Exception
	 */
	private static void testTimerSendMsg()throws Exception {
		
		long l = System.currentTimeMillis();

		final RemoteServerCall remoteServerCall = new RemoteServerCall();
		
		remoteServerCall.serverIP = "192.168.200.178";
		remoteServerCall.serverPort = "1502";
//		remoteServerCall.serverIP = "www.aqiapp.com";
//		remoteServerCall.serverPort = "8414";

		Request request = new Request("health://HealthService");
		request.setAttribute("rmi", "TimerSendMsgByScore");
		Response response = remoteServerCall.schedule(request);
		String message = response.getAttribute("message");
		String data = response.getAttribute("data");
		System.out.println(message);
		System.out.println(data);
		
		System.out.println("调用耗时：" + ( System.currentTimeMillis() - l ) +" ms");
	}
	/**
	 * 获取最近设备健康指数和信息
	 * @throws Exception
	 */
	private static void testGetNearestInfo()throws Exception {
		
		long l = System.currentTimeMillis();

		final RemoteServerCall remoteServerCall = new RemoteServerCall();
		
//		remoteServerCall.serverIP = "192.168.200.178";
//		remoteServerCall.serverPort = "1502";
		remoteServerCall.serverIP = "www.aqiapp.com";
		remoteServerCall.serverPort = "8414";

		Request request = new Request("health://HealthService");
		request.setAttribute("rmi", "getNearestInfo");
		request.setAttribute("longitude", "112.560869");
		request.setAttribute("latitude", "37.793325");
		Response response = remoteServerCall.schedule(request);
		String message = response.getAttribute("message");
		String data = response.getAttribute("data");
		System.out.println(message);
		System.out.println(data);
		
		System.out.println("调用耗时：" + ( System.currentTimeMillis() - l ) +" ms");
	}
	/**
	 * 生成模拟数据
	 * @throws Exception
	 */
	private static void testData()throws Exception {
		
		long l = System.currentTimeMillis();

		final RemoteServerCall remoteServerCall = new RemoteServerCall();
		
		remoteServerCall.serverIP = "192.168.200.178";
		remoteServerCall.serverPort = "1502";
//		remoteServerCall.serverIP = "www.aqiapp.com";
//		remoteServerCall.serverPort = "8414";

		Request request = new Request("web://WeatherStationInfoNewService");
		request.setAttribute("rmi", "saveStation24FalseData");
		Response response = remoteServerCall.schedule(request);
		String message = response.getAttribute("message");
		String data = response.getAttribute("data");
		System.out.println(message);
		System.out.println(data);
		
		System.out.println("调用耗时：" + ( System.currentTimeMillis() - l ) +" ms");
	}

	/**
	 * 获取网络时间
	 * @throws Exception
	 */
	public static void testGetNetTime()throws Exception {
//        String webUrl1 = "http://www.bjtime.cn";//bjTime
		long l = System.currentTimeMillis();
        String webUrl2 = "http://www.baidu.com";//百度
        String webUrl3 = "http://www.taobao.com";//淘宝
        String webUrl4 = "http://www.ntsc.ac.cn";//中国科学院国家授时中心
        String webUrl5 = "http://www.360.cn";//360
        String webUrl6 = "http://www.beijing-time.org";//beijing-time
//        System.out.println(getWebsiteDatetime(webUrl1) + " [bjtime]");
        System.out.println(getWebsiteDatetime(webUrl2) + " [百度]");
        System.out.println(getWebsiteDatetime(webUrl3) + " [淘宝]");
        System.out.println(getWebsiteDatetime(webUrl4) + " [中国科学院国家授时中心]");
        System.out.println(getWebsiteDatetime(webUrl5) + " [360安全卫士]");
        System.out.println(getWebsiteDatetime(webUrl6) + " [beijing-time]");
        System.out.println("调用耗时：" + ( System.currentTimeMillis() - l ) +" ms");
    }
	 private static String getWebsiteDatetime(String webUrl){
	        try {
	            URL url = new URL(webUrl);// 取得资源对象
	            URLConnection uc = url.openConnection();// 生成连接对象
	            uc.connect();// 发出连接
	            long ld = uc.getDate();// 读取网站日期时间
	            Date date = new Date(ld);// 转换为标准时间对象
	            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.CHINA);// 输出北京时间
	            return sdf.format(date);
	        } catch (MalformedURLException e) {
	            e.printStackTrace();
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	        return null;
	    }
	/**
	 * 微健康，获取服务测试
	 * @throws Exception
	 */
	@SuppressWarnings("unused")
	private static void testAqi()throws Exception {
		
		long l = System.currentTimeMillis();

		final RemoteServerCall remoteServerCall = new RemoteServerCall();
		
//		remoteServerCall.serverIP = "192.168.200.178";
//		remoteServerCall.serverPort = "1502";
		remoteServerCall.serverIP = "www.aqiapp.com";
		remoteServerCall.serverPort = "8414";

		Request request = new Request("health://HealthService");
		request.setAttribute("rmi", "listStationAqi");
		request.setAttribute("longitude", "112.560869");
		request.setAttribute("latitude", "37.793325");
		Response response = remoteServerCall.schedule(request);
		String message = response.getAttribute("message");
		String data = response.getAttribute("data");
		System.out.println(message);
		System.out.println(data);
		
		System.out.println("调用耗时：" + ( System.currentTimeMillis() - l ) +" ms");
	}
	@SuppressWarnings("unused")
	private static void testUpload()throws Exception {
	
		long l = System.currentTimeMillis();

		final RemoteServerCall remoteServerCall = new RemoteServerCall();
		
		remoteServerCall.serverIP = "192.168.200.178";
		remoteServerCall.serverPort = "1502";
//		remoteServerCall.serverIP = "www.aqiapp.com";
//		remoteServerCall.serverPort = "8414";

		Request request = new Request("health://HealthService");
		request.setAttribute("rmi", "uploadSportData");
		request.setAttribute("username", "18636181748");
		request.setAttribute("type", "0");
		request.setAttribute("mileage", "42.14");
		request.setAttribute("timeUsed", "112334");
		request.setAttribute("speed", "12322");
		request.setAttribute("calorie", "44");
		StringBuffer a = new StringBuffer();
		a.append("{\"allLocations\":[");
		for (int i = 0; i < 10000; i++) {
			a.append("{\"aqiStr\":59,\"isPause\":false,\"latitude\":\"37.788763\",\"longitude\":\"112.562648\"},");
		}
		a.append("],\"allAQI\":[{\"aqiStr\":59,\"longitude\":\"112.562648\",\"latitude\":\"37.788763\"},{\"aqiStr\":63,\"longitude\":\"112.561979\",\"latitude\":\"37.791206\"}],\"allDevices\":[{\"aqiStr\":57,\"longitude\":112.562035,\"latitude\":37.791199},{\"aqiStr\":50,\"longitude\":112.562119,\"latitude\":37.791115},{\"aqiStr\":34,\"longitude\":112.560959,\"latitude\":37.791412},{\"aqiStr\":16,\"longitude\":112.560959,\"latitude\":37.791412},{\"aqiStr\":28,\"longitude\":112.567833,\"latitude\":37.795742},{\"aqiStr\":154,\"longitude\":112.555916,\"latitude\":37.784969},{\"aqiStr\":0,\"longitude\":112.556267,\"latitude\":37.784744},{\"aqiStr\":0,\"longitude\":112.556122,\"latitude\":37.78479},{\"aqiStr\":0,\"longitude\":112.556076,\"latitude\":37.784794},{\"aqiStr\":0,\"longitude\":112.555458,\"latitude\":37.785072},{\"aqiStr\":0,\"longitude\":112.55619,\"latitude\":37.784473},{\"aqiStr\":117,\"longitude\":112.555717,\"latitude\":37.784733},{\"aqiStr\":0,\"longitude\":112.555626,\"latitude\":37.784771},{\"aqiStr\":0,\"longitude\":112.555977,\"latitude\":37.784515},{\"aqiStr\":63,\"longitude\":112.591003,\"latitude\":37.807087},{\"aqiStr\":59,\"longitude\":112.577408,\"latitude\":37.753525}]}");
		request.setAttribute("jsonString", "H4sIAAAAAAAAA+VYTWtTQRT9K/LAXajzdefOZFdwIyhUXGoXscYSSJuaj7oohYIuLBTEjW66ErR0J3SjdSH4U8Smf8NJFJo3c2JurbgxuzzmzJ0595x773s7Vavbvd1baw07vc1B1by/U7WedO4N+1WzslQ1qs5gpTUatKvm41Z30G5U3bRyOHqUHlhe4qhNetTbXP/1TGuzRN7sNma28f7fbaPjJfcZ9kdom7Bwm0hL6o83Wm1MaF++e6tO+PRo5XLAFjqKBKjDJPbD9mC43O9Pg2+3upMtQjU5UqeVJFCt3Eno9H+02Rmmv6P1Bzc2vp5Uk5g/V3tlZ5Z/Pz0Yfzg6O3h9/vbkArW1tTGDmF0/3vs8Pnh39vLVeO9ofLg/Pjw93z/+XTjjZ9Efj89O318s//b82cxScrNLP32pLb1eTS4/7GwkOirF48MXWo/fvLumdVPFKa57s73dWWtnPmAH2VXKAYK9q+UGY3UMGmFDXanhEnFdXRNsMdYwwBpfwzrGWE8obizdBe5L4L7Gcj3uHJ4DCEt1KBsc1oIjGwIFAYVFcTWJ0os8S3WWPTzynLCKRWERy4ozrIZxkwiQMozkzEkEi50wD+slLoJqhjRbo0XKQFRZCcsmYQHLxv41LPa9jlwypaI1Imwss6uiqlNFuItYbwW+x9mF2FSE6r7HWMOlqtJ9vSC9RhGoc5SbF943aQ84wXKQ5Ne6Mq5Kiathp3NViXUKudeSpCdYKnWV4kqowlAvaieagQNlNBuH3KujlkjDaSRnqmN9xFgDbBSiRJI6eiANbyVUkYUFp64qO6cVoa7tKasaWM2E2ifpzL24n7AGtdlnzg+wn8iwDJ0gxOIUXQWb0lFinapjLZyONKGq4bVEktpaUCVdVp1xhdVwzPdW0ns1BQFXFuvKRXBm70iiK2QFxxIb6WR9YKPgJYUdYp3LbIQlmX5gpFNWIivlUAONURJXGTQOiqpGQMLIjQCZSqUUDHQue+28CtbDosEBGIE4e7VxCEsUy54QXPTZfZEgE1e+KLDBsRO8yk1kVfCcsJKw5KhIbiDFRhS2YDm43EPwzYaINbqtFQywU6oAlrUEGxnEzacFh1TFHpUqohAX14w0Q6lcVUGxCrwYy+zK9kmWTHZmhA1ahWKyIsP55yg00AUw4JC1qWuvNqqnvf5/+/lm9wcVoEpmsBQAAA==");
//		request.setAttribute("jsonString", NewDataRecode_gzip.ReduceEncode(a.toString()));
//		request.setAttribute("jsonString", DataRecode_gzip.ReduceEncode(a.toString()));
//		System.out.println(NewDataRecode_gzip.ReduceEncode(a.toString()));
		System.out.println(11);
		Response response = remoteServerCall.schedule(request);
		String message = response.getAttribute("message");
		String data = response.getAttribute("data");
		System.out.println(message);
		System.out.println(data);
		
		System.out.println("调用耗时：" + ( System.currentTimeMillis() - l ) +" ms");
	}
	@SuppressWarnings("unused")
	private static void testList()throws Exception {
		
		long l = System.currentTimeMillis();

		final RemoteServerCall remoteServerCall = new RemoteServerCall();
		
		remoteServerCall.serverIP = "192.168.200.178";
		remoteServerCall.serverPort = "1502";
//		remoteServerCall.serverIP = "www.aqiapp.com";
//		remoteServerCall.serverPort = "8414";

		Request request = new Request("health://HealthService");
		request.setAttribute("rmi", "listSportData");
		request.setAttribute("username", "18636181748");
		Response response = remoteServerCall.schedule(request);
		String message = response.getAttribute("message");
		String data = response.getAttribute("data");
		System.out.println(message);
		System.out.println(data);
		
		System.out.println("调用耗时：" + ( System.currentTimeMillis() - l ) +" ms");
	}
	@SuppressWarnings("unused")
	private static void testDetail()throws Exception {
		
		long l = System.currentTimeMillis();

		final RemoteServerCall remoteServerCall = new RemoteServerCall();
		
		remoteServerCall.serverIP = "192.168.200.178";
		remoteServerCall.serverPort = "1502";
//		remoteServerCall.serverIP = "www.aqiapp.com";
//		remoteServerCall.serverPort = "8414";

		Request request = new Request("health://HealthService");
		request.setAttribute("rmi", "sportDataDetail");
		request.setAttribute("id", "2");
		Response response = remoteServerCall.schedule(request);
		String message = response.getAttribute("message");
		String data = response.getAttribute("data");
		System.out.println(message);
		System.out.println(data);
		
		System.out.println("调用耗时：" + ( System.currentTimeMillis() - l ) +" ms");
	}
	@SuppressWarnings("unused")
	private static void deleteDetail()throws Exception {
		
		long l = System.currentTimeMillis();

		final RemoteServerCall remoteServerCall = new RemoteServerCall();
		
		remoteServerCall.serverIP = "192.168.200.178";
		remoteServerCall.serverPort = "1502";
//		remoteServerCall.serverIP = "www.aqiapp.com";
//		remoteServerCall.serverPort = "8414";

		Request request = new Request("health://HealthService");
		request.setAttribute("rmi", "deleteSportDataById");
		request.setAttribute("id", "3");
		Response response = remoteServerCall.schedule(request);
		String message = response.getAttribute("message");
		String data = response.getAttribute("data");
		System.out.println(message);
		System.out.println(data);
		
		System.out.println("调用耗时：" + ( System.currentTimeMillis() - l ) +" ms");
	}

	/**
	 * 漫道短信测试
	 * @throws Exception
	 */
	@SuppressWarnings("unused")
	private static void testMsg()throws Exception {
		
		long l = System.currentTimeMillis();

		final RemoteServerCall remoteServerCall = new RemoteServerCall();
		
		remoteServerCall.serverIP = "192.168.200.178";
		remoteServerCall.serverPort = "1502";
//		remoteServerCall.serverIP = "www.aqiapp.com";
//		remoteServerCall.serverPort = "8414";

		Request request = new Request("env-user-manage://UserService");
		request.setAttribute("rmi","sendMsg");
		request.setAttribute("phone", "18636181748");
		request.setAttribute("validateCode","123");
		request.setAttribute("type","0");
		Response response = remoteServerCall.schedule(request);
		String message =  response.getAttribute("message");
		String data = response.getAttribute("data");
		System.out.println(message);
		System.out.println(data);
		
		System.out.println("调用耗时：" + ( System.currentTimeMillis() - l ) +" ms");
	}
	
	/**
	 * 乱七八糟
	 * @throws Exception
	 */
	@SuppressWarnings("unused")
	private static void otherDate()throws Exception {
		for (int i = 0; i < 6; i++) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmm");
			Calendar cal = Calendar.getInstance();
			cal.setTime(sdf.parse("201606280803"));
			System.out.println(cal.getTime());
			int b = cal.get(Calendar.MINUTE);
			System.out.println(b);
		}
		long l = System.currentTimeMillis();
		Calendar cal1 = Calendar.getInstance();
		Date time=new Date(NumberUtils.toLong("1467079020000")); 
		cal1.setTime(time);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHH");
		Calendar cal = Calendar.getInstance();
		cal.setTime(sdf.parse("2016062810"));
		System.out.println(cal1.get(Calendar.MINUTE));
		cal.add(Calendar.MINUTE,cal1.get(Calendar.MINUTE));
		cal.add(Calendar.HOUR,-1);
		System.out.println(cal.getTimeInMillis());
		StringBuffer a = new StringBuffer();
		for (int i = 0; i < 600; i++) {
			a.append("{\"allLocations\":[{\"aqiStr\":59,\"isPause\":false,\"latitude\":\"37.788763\",\"longitude\":\"112.562648\"},");
		}
		System.out.println(DataRecode_gzip.ReduceEncode(a.toString()));
		System.out.println(a.toString().getBytes("utf-8").length);
		
		System.out.println("调用耗时：" + ( System.currentTimeMillis() - l ) +" ms");
	}
	
}
