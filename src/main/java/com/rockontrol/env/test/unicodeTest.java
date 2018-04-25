package com.rockontrol.env.test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.rockontrol.env.utils.Code;

public class unicodeTest {
	public static void main(String[] args) {
//		readFile("C://Users//Administrator//Desktop//msg_conf2.xml");
		readFile("C://Users//Administrator//Desktop//111.txt");
	}
	
	public void saveFile(String log) {
	       SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmm");
	       Date date = new Date();
	       String formateDate = format.format(date);
	       String remotefilename = formateDate + ".txt";
	       try {
	           OutputStream os = new FileOutputStream("");
	           os.write(log.getBytes());
	           os.close();
	       } catch (IOException ex) {
	        }
	    }
	//读文件：String remoteFile为文本文件的地址（包含文件名）
	public static String readFile(String remoteFile) {
	       String log = "";
	       try {
	           File file = new File(remoteFile);
	           FileInputStream is = new FileInputStream(remoteFile);
	           int size = (int) file.length();
	           byte[] bytes = getBytes(is,size);
	           String content = new String(bytes, "GBK");
	           String[] a = content.split("\r\n");
	           for (String string : a) {
	        	   if(!string.startsWith("level")){
	        		   log = Code.chToUnicode(string);
	        	   }else{
	        		   log = string;
	        	   }
	        	   System.out.println(log);
	           }
	           is.close();
	       } catch (IOException ex) {
	        }
	       return log;
	    }
	 
	 
	    private static byte[] getBytes(InputStream inputStream,int size) {
	       byte[] bytes = new byte[size];
	       try {
	           int readBytes = inputStream.read(bytes);
	           return bytes;
	       } catch (Exception e) {
	           e.printStackTrace();
	       }
	       return null;
	    }
}
