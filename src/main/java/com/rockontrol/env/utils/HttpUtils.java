package com.rockontrol.env.utils;

import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.springframework.web.multipart.MultipartFile;

import okhttp3.FormBody;
import okhttp3.FormBody.Builder;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class HttpUtils {

	static OkHttpClient client = new OkHttpClient.Builder()
			.readTimeout(20, TimeUnit.SECONDS).writeTimeout(10, TimeUnit.SECONDS)
			.connectTimeout(10, TimeUnit.SECONDS).build();
	
	//文件上传下载不限制时间
	static OkHttpClient file_client = new OkHttpClient.Builder()
			.readTimeout(0, TimeUnit.SECONDS).writeTimeout(0, TimeUnit.SECONDS)
			.connectTimeout(0, TimeUnit.SECONDS).build();
	
	static final MediaType MediaType_JSON = MediaType
			.parse("application/json; charset=utf-8");

	public static String get(String url,OkHttpClient client, NameValuePair... headers){
		okhttp3.Request.Builder builder = new Request.Builder().url(url);
		if(headers != null && headers.length > 0){
			for(NameValuePair header : headers){
				if(StringUtils.isNotBlank(header.getName())){
					builder.addHeader(header.getName(), header.getValue());
				}
			}
		}
		Request request = builder.build();
		try {
			Response response = client.newCall(request).execute();
			return returnHandle(response);
		} catch (Throwable e) {
			throw new RuntimeException(e);
		}
	}
	
	public static String get(String url){
		return get(url,client,new BasicNameValuePair("",""));
	}

	public static String get(String server, String url){
		if (!url.startsWith("/") || url.startsWith("\\")) {
			url = "/" + url;
		}
		url = server + url;
		return get(url);
	}

	private static String returnHandle(Response response) throws IOException {
		String data = response.body().string();
		return data;
	}

	public static String post(String server, String url, NameValuePair... nvps){
		Builder builder = new FormBody.Builder();
		if (nvps != null) {
			for (NameValuePair nv : nvps) {
				builder.add(nv.getName(), nv.getValue());
			}
		}

		if (!url.startsWith("/") || url.startsWith("\\")) {
			url = "/" + url;
		}
		url = server + url;
		
		
		Request request = new Request.Builder().url(url).post(builder.build())
				.build();
		try {
			Response response = client.newCall(request).execute();
			return returnHandle(response);
		} catch (Throwable e) {
			throw new RuntimeException(e);
		}
	}

	public static String post(String url, byte[] b){
        RequestBody body = RequestBody.create(MediaType.parse("application/x-www-form-urlencoded; charset=utf-8"), b);
        Request request = new Request.Builder().url(url).post(body).build();
        try {
            Response response = file_client.newCall(request).execute();
            return returnHandle(response);
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
    }
	
	public static String post(String url, NameValuePair... nvps){
		Builder builder = new FormBody.Builder();
		if (nvps != null) {
			for (NameValuePair nv : nvps) {
				builder.add(nv.getName(), nv.getValue());
			}
		}

		Request request = new Request.Builder().url(url).post(builder.build())
				.build();
		try {
			Response response = client.newCall(request).execute();
			return returnHandle(response);
		} catch (Throwable e) {
			throw new RuntimeException(e);
		}
	}

	public static String postJson(String url, String data){
		Request request = new Request.Builder().url(url)
				.post(RequestBody.create(MediaType_JSON, data)).build();

		try {
			Response response = client.newCall(request).execute();
			return returnHandle(response);
		} catch (Throwable e) {
			throw new RuntimeException(e);
		}
	}
	
	public static String postJson(OkHttpClient client, String url, String data){
		Request request = new Request.Builder().url(url)
				.post(RequestBody.create(MediaType_JSON, data)).build();

		try {
			Response response = client.newCall(request).execute();
			return returnHandle(response);
		} catch (Throwable e) {
			throw new RuntimeException(e);
		}
	}
	
	public static String postJson(String server, String url, String data){
		if (!url.startsWith("/") || url.startsWith("\\")) {
			url = "/" + url;
		}
		url = server + url;
		return postJson(url,data);
	}

	static final String contentType = "application/octet-stream"; 
	
	/**
	 * 删除DFS服务器资源
	 */
	public static String deleteFiles(String url,String dfsId ){
		okhttp3.MultipartBody.Builder builder = new MultipartBody.Builder();
		try {
			
			builder.addFormDataPart("oldFileId", URLEncoder.encode(dfsId, "UTF-8"));
		
			MultipartBody multipartBody = builder.build();
			Request request = new Request.Builder()
							    .url(url)
							    .post(multipartBody)
							    .build();
			
			Response response = file_client.newCall(request).execute();
			return returnHandle(response);
		} catch (Throwable e) {
			throw new RuntimeException(e);
		}
	}
	
	public static String postFiles(String url,File file){
		okhttp3.MultipartBody.Builder builder = new MultipartBody.Builder();
		try {
			
			RequestBody fileBody = RequestBody.create(MediaType.parse(contentType),file);
			builder.addFormDataPart("_f_",file.getName(), fileBody);
			
			MultipartBody multipartBody = builder.build();
			Request request = new Request.Builder()
							    .url(url)
							    .post(multipartBody)
							    .build();
		
			Response response = file_client.newCall(request).execute();
			return returnHandle(response);
		} catch (Throwable e) {
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * 同时上传多个文件
	 * @author lvshengchao
	 * @since  2017年1月13日 下午3:35:29
	 * @param url
	 * @param key
	 * @param files
	 * @param nvps
	 * @return
	 */
	public static String postFiles(String url,String key,List<File> files,NameValuePair... nvps){
		if(StringUtils.isBlank(key)){
			key = "_f_";
		}
		okhttp3.MultipartBody.Builder builder = new MultipartBody.Builder();
		try {
			if(files != null && files.size() > 0){
				for(File file : files){
					RequestBody fileBody = RequestBody.create(MediaType.parse(contentType),file);
					builder.addFormDataPart(key,file.getName(), fileBody);
				}
				
			}
			if(nvps != null && nvps.length > 0){
				for(NameValuePair nv : nvps){
					builder.addFormDataPart(nv.getName(), nv.getValue());
				}
			}
			
			MultipartBody multipartBody = builder.build();
			Request request = new Request.Builder()
							    .url(url)
							    .post(multipartBody)
							    .build();
		
			Response response = file_client.newCall(request).execute();
			return returnHandle(response);
		} catch (Throwable e) {
			throw new RuntimeException(e);
		}
	}
	
	public static String postFiles(String url,Map<String,File> fileMap,NameValuePair... nvps){
		okhttp3.MultipartBody.Builder builder = new MultipartBody.Builder();
		try {
			if(fileMap != null && fileMap.size() > 0){
				Set<Entry<String, File>> entrySet = fileMap.entrySet();
				for(Entry<String, File> es : entrySet){
					RequestBody fileBody = RequestBody.create(MediaType.parse(contentType),es.getValue());
					builder.addFormDataPart(es.getKey(),es.getValue().getName(), fileBody);
				}
				
			}
			if(nvps != null && nvps.length > 0){
				for(NameValuePair nv : nvps){
					builder.addFormDataPart(nv.getName(), nv.getValue());
				}
			}
			
			MultipartBody multipartBody = builder.build();
			Request request = new Request.Builder()
							    .url(url)
							    .post(multipartBody)
							    .build();
		
			Response response = file_client.newCall(request).execute();
			return returnHandle(response);
		} catch (Throwable e) {
			throw new RuntimeException(e);
		}
	}
	
	public static String postFiles(String url,MultipartFileUpload mfu){
		okhttp3.MultipartBody.Builder builder = new MultipartBody.Builder();
		try {
			
			if(mfu != null){
				RequestBody fileBody = RequestBody.create(MediaType.parse(contentType),mfu.getFile().getBytes());
				builder.addFormDataPart(mfu.getParam(),mfu.getFile().getOriginalFilename(), fileBody);
			}
			
			MultipartBody multipartBody = builder.build();
			Request request = new Request.Builder()
							    .url(url)
							    .post(multipartBody)
							    .build();
		
			Response response = file_client.newCall(request).execute();
			return returnHandle(response);
		} catch (Throwable e) {
			throw new RuntimeException(e);
		}
	}
	
	
	/**
	 * url对应的资源下载
	 *
	 * @param donwloadUrl
	 * @param nameValuePairs
	 * @return
	 *
	 */
	public static byte[] getFileByteArray(String donwloadUrl){
		Request request = new Request.Builder().url(donwloadUrl).build();
		try {
			Response response = file_client.newCall(request).execute();
			
			int code = response.code();
			if (code != 200) {
				String data = response.body().string();
				throw new RuntimeException(">>> Unexpected response status: "
						+ code + ",message: " + data);
			}
			return response.body().bytes();
		} catch (Throwable e) {
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * 文件上传的数据结构
	 * @author lvshengchao
	 * @since  2016-8-18 下午1:47:26
	 *
	 */
	public static class MultipartFileUpload{
		String param;
		MultipartFile file;
		
		public MultipartFileUpload() {
			super();
		}
		public MultipartFileUpload(String param, MultipartFile file) {
			super();
			this.param = param;
			this.file = file;
		}
		public String getParam() {
			return param;
		}
		public void setParam(String param) {
			this.param = param;
		}
		public MultipartFile getFile() {
			return file;
		}
		public void setFile(MultipartFile file) {
			this.file = file;
		}
		
		
	}

}
