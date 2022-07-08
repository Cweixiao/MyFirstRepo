package com.example.demo;

import com.example.demo.util.JasyptUtil;
import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.jasypt.encryption.pbe.config.EnvironmentPBEConfig;
import org.junit.Test;

import java.util.Random;

public class JasypTest {



    @Test
    public void testEncrypt() throws Exception {
        String[] list = {"123456","sg_realtime_app","md5","#shiro","2","RSA/ECB/OAEPWithSHA-1AndMGF1Padding"};
        String privateKey = "Inspur:EnergyProjects:PCD_APP_1";

        for(String str:list){
            System.out.println("原密码:"+str + "    加密后:"+ JasyptUtil.Encrypt(str,privateKey));
        }

    }

    @Test
    public void testDe() throws Exception {
        String privateKey = "Inspur:EnergyProjects:PCD_APP_1";
        String encryptedText = "wt1APd4H75GTINxHgl+a8u2mDUI5JQi5bHRqM06MNQJhYMiQGmnQ4zuA1T7RYgjo";
        System.out.println( JasyptUtil.Decrypt(encryptedText,privateKey) );
    }




    @Test
    public void getSalt(){
        int n = 30;
        char[] chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789!@#$%^&*()".toCharArray();
        StringBuilder sb = new StringBuilder();
        for (int i=0;i<n;i++){
            char aChar = chars[new Random().nextInt(chars.length)];
            sb.append(aChar);
        }
        System.out.println(sb.toString());
    }

}
