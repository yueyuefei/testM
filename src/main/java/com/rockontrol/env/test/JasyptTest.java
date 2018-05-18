package com.rockontrol.env.test;

import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;

public class JasyptTest {

	// 密钥  
    private static final String KEY = "rkconfig132";  
      
    public static void main(String[] args) {  
        String username = encrypt("root");
        String password = encrypt("123456");
        System.out.println(username);  
        System.out.println(password);  
          
        String text1 = decrypt(username);  
        String text2 = decrypt(password);  
        System.out.println(text1);
        System.out.println(text2);
    }  
      
    /** 
     * 加密 
     * @param text 明文 
     * @return     密文 
     */  
    // 默认加密/解密算法是 PBEWithMD5AndDES  
    public static String encrypt(String text) {  
        StandardPBEStringEncryptor encryptor = new StandardPBEStringEncryptor();  
        encryptor.setPassword(KEY);  
        return encryptor.encrypt(text);  
    }  
      
    /** 
     * 解密 
     * @param ciphertext 密文 
     * @return           明文 
     */  
	// 默认加密/解密算法是 PBEWithMD5AndDES  
    public static String decrypt(String ciphertext) {  
        StandardPBEStringEncryptor encryptor = new StandardPBEStringEncryptor();  
        encryptor.setPassword(KEY);  
        return encryptor.decrypt(ciphertext);  
    }
}
