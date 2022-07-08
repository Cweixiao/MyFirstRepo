package com.example.demo.file.other.Unicode2Chinese;

import java.io.*;

public class test {

    public static void main(String[] args) throws IOException {
        Unicode2Chinese unicode2Chinese = new Unicode2Chinese();


        //读取的文件
        String path = "D:\\1111.json";
        File file = new File(path);
        InputStream in;
        //输出的文件
        BufferedWriter out = new BufferedWriter(new FileWriter("D:\\1111out.json"));
        //按行读取文件
        try {
            System.out.println("按行读取文件内容");
            in = new FileInputStream(file);
            Reader reader2 = new InputStreamReader(in);
            BufferedReader bufferedReader = new BufferedReader(reader2);
            String line;
            while((line=bufferedReader.readLine())!=null){
                System.out.println(unicode2Chinese.unicodeDecode(line));
                out.write(unicode2Chinese.unicodeDecode(line)+'\n');
            }
            bufferedReader.close();
            out.close();
            System.out.println("文件创建成功！");
        } catch (Exception e) {
            e.printStackTrace();
        }




    }
}
