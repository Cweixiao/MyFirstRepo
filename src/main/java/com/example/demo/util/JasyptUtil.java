package com.example.demo.util;

import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.jasypt.encryption.pbe.config.EnvironmentPBEConfig;


/**
 * @Author chengxh
 * 功能：将配置文件中的敏感数据进行加密，启动项目时传入私钥，自动解密
 * 使用说明：将配置文件中敏感数据进行替换，例如：原密码 123456，加密后为hfjkjaYEQWIdasjhf,则将配置文件中的 123456替换为ENC(hfjkjaYEQWIdasjhf)
 */
public class JasyptUtil {

    //加解密算法
    private static String Algorithm = "PBEWithMD5AndDES";   // 加密的算法，这个算法是默认的

    /**
     * 加密
     * @param password 原密码
     * @param privateKey 自己定义的私钥
     * @return 加密后的密码
     */
    public static String Encrypt(String password,String privateKey){
        StandardPBEStringEncryptor standardPBEStringEncryptor = new StandardPBEStringEncryptor();
        EnvironmentPBEConfig config = new EnvironmentPBEConfig();

        config.setAlgorithm(Algorithm);
        config.setPassword(privateKey);     // 加密的密钥，随便自己填写，很重要千万不要告诉别人
        standardPBEStringEncryptor.setConfig(config);

        String encryptedText = standardPBEStringEncryptor.encrypt(password);
        return encryptedText;
    }

    /**
     * 解密
     * @param encryptedText 加密后的密码
     * @param privateKey 私钥
     * @return 原密码
     */
    public static String Decrypt(String encryptedText,String privateKey){
        StandardPBEStringEncryptor standardPBEStringEncryptor = new StandardPBEStringEncryptor();
        EnvironmentPBEConfig config = new EnvironmentPBEConfig();

        config.setAlgorithm(Algorithm);
        config.setPassword(privateKey);

        standardPBEStringEncryptor.setConfig(config);
        String orinPassword = standardPBEStringEncryptor.decrypt(encryptedText);
        return orinPassword;
    }

}
