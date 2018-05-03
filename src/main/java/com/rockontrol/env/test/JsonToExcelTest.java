package com.rockontrol.env.test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelWriter;



public class JsonToExcelTest {

	public static void main(String[] args) {
//		jsonToExcelUtil("http://www.aqiapp.com/dm/devdata/list?type=3&number=0&size=400&mn=170201AF000014&timeBegin=1523408400000&timeEnd=1523419200000");
		String timeBegin = "2018-05-02 13:40";
		String timeEnd = "2018-05-02 14:16";
		jsonToExcelTest(timeBegin, timeEnd);
	}
	
	public static void jsonToExcelUtil(String url) {
		String json = HttpUtil.get(url);
		JSONObject obj = new JSONObject(json); 
		// 通过工具类创建writer
		ExcelWriter writer = ExcelUtil.getWriter("C:/Users/Administrator/Desktop/writeBean.xlsx");
		// 一次性写出内容，使用默认样式
		writer.write(obj.getJSONArray("content"));
		// 关闭writer，释放内存
		writer.close();
		
	}
	
	public static void jsonToExcelTest(String timeBegin, String timeEnd) {
		List<String> mns = Arrays.asList("170201AF000012","170201AF000014","170201AF000020");
		Long stime = DateUtil.parse(timeBegin).getTime();
		Long etime = DateUtil.parse(timeEnd).getTime();
		String url = "http://www.aqiapp.com/dm/devdata/list?type=3&number=0&size=400&mn={}&timeBegin={}&timeEnd={}";
		List<Map<String, Object>> array = new ArrayList<Map<String,Object>>();
		for (String mn : mns) {
			url = StrUtil.format(url,mn, stime, etime); 
			String json = HttpUtil.get(url);
			JSONObject obj = new JSONObject(json); 
			JSONArray list = obj.getJSONArray("content");
			for (Object object : list) {
				Map<String, Object> map = new LinkedHashMap<String, Object>();
				map.put("时间", new JSONObject(object).get("dataTime")+" ");
				map.put("mn", new JSONObject(object).get("mn"));
				map.put("温度", new JSONObject(object).get("tempin"));
				map.put("温度状态", new JSONObject(object).get("tempinFlag"));
				map.put("湿度", new JSONObject(object).get("humiin"));
				map.put("湿度状态", new JSONObject(object).get("humiinFlag"));
				map.put("voc", new JSONObject(object).get("voc"));
				map.put("voc状态", new JSONObject(object).get("vocFlag"));
				map.put("油烟", new JSONObject(object).get("yy"));
				map.put("油烟状态", new JSONObject(object).get("yyFlag"));
				array.add(map);
			}
		}
		// 通过工具类创建writer
		ExcelWriter writer = ExcelUtil.getWriter("C:/Users/Administrator/Desktop/数据.xlsx");
		// 一次性写出内容，使用默认样式
		writer.write(array);
		// 关闭writer，释放内存
		writer.close();
	}
	
}
