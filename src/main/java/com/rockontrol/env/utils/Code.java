package com.rockontrol.env.utils;

import java.io.UnsupportedEncodingException;

/**
 * 编码转换。
 * @author 杨文锋
 * @version 1.0, 2011-02-10
 * @since RKSP1.0
 */
public class Code{

    /**
     * unicode编码转成字符串
     * @param str unicode串。
     * @return 转换后的字符串
     */
    public static String UnicodeToCh(String str){
        if(str == null)
            return null;
        char[] in = str.toCharArray();
        int off = 0;
        int len = str.length();
        char[] convtBuf = new char[0];
        if(convtBuf.length < len){
            int newLen = len * 2;
            if(newLen < 0){
                newLen = Integer.MAX_VALUE;
            }
            convtBuf = new char[newLen];
        }
        char aChar;
        char[] out = convtBuf;
        int outLen = 0;
        int end = off + len;

        while(off < end){
            aChar = in[off++];
            if(aChar == '\\'){
                aChar = in[off++];
                if(aChar == 'u'){
                    int value = 0;
                    for(int i = 0; i < 4; i++){
                        aChar = in[off++];
                        switch(aChar){
                        case '0':
                        case '1':
                        case '2':
                        case '3':
                        case '4':
                        case '5':
                        case '6':
                        case '7':
                        case '8':
                        case '9':
                            value = (value << 4) + aChar - '0';
                            break;
                        case 'a':
                        case 'b':
                        case 'c':
                        case 'd':
                        case 'e':
                        case 'f':
                            value = (value << 4) + 10 + aChar - 'a';
                            break;
                        case 'A':
                        case 'B':
                        case 'C':
                        case 'D':
                        case 'E':
                        case 'F':
                            value = (value << 4) + 10 + aChar - 'A';
                            break;
                        default:
                            throw new IllegalArgumentException("Malformed \\uxxxx encoding.");
                        }
                    }
                    out[outLen++] = (char) value;
                }else{
                    if(aChar == 't')
                        aChar = '\t';
                    else if(aChar == 'r')
                        aChar = '\r';
                    else if(aChar == 'n')
                        aChar = '\n';
                    else if(aChar == 'f')
                        aChar = '\f';
                    out[outLen++] = aChar;
                }
            }else{
                out[outLen++] = (char) aChar;
            }
        }
        String value = null;
        try{
            value = new String(new String(out, 0, outLen).getBytes("GBK"), "GBK");
        }catch(UnsupportedEncodingException e){
            e.printStackTrace();
        }
        return value;
    }

    /**
     * 字符串转unicode编码
     * @param str 字符串
     * @return 转换后的unicode编码
     */
    public static String chToUnicode(String str){
        if(str == null)
            return null;
        try{
            str = new String(str.trim().getBytes("GBK"), "GBK");
        }catch(UnsupportedEncodingException e){
            e.printStackTrace();
        }
        str = (str == null ? "" : str);
        String tmp;
        StringBuffer sb = new StringBuffer();
        char c;
        int i, j;
        sb.setLength(0);
        for(i = 0; i < str.length(); i++){
            c = str.charAt(i);
            sb.append("\\u");
            j = (c >>> 8);
            tmp = Integer.toHexString(j);
            if(tmp.length() == 1)
                sb.append("0");
            sb.append(tmp);
            j = (c & 0xFF);
            tmp = Integer.toHexString(j);
            if(tmp.length() == 1)
                sb.append("0");
            sb.append(tmp);

        }
        return new String(sb);
    }
    
    /**
     * 字符串转到UTF-8编码
     * @param str
     * @return
     */
    public static String toUtf8String(String str) {
		if (str == null || str.equals("")) {
			return null;
		}
		StringBuffer sb = new StringBuffer();
		try {
			char c;
			for (int i = 0; i < str.length(); i++) {
				c = str.charAt(i);
				if (c >= 0 && c <= 255) {
					sb.append(c);
				} else {
					byte[] b;

					b = Character.toString(c).getBytes("utf-8");

					for (int j = 0; j < b.length; j++) {
						int k = b[j];
						if (k < 0)
							k += 256;
						sb.append("%" + Integer.toHexString(k).toUpperCase());
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sb.toString();
	} 
}
