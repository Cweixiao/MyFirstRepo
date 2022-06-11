package com.example.demo.controller;

import com.google.zxing.BinaryBitmap;
import com.google.zxing.LuminanceSource;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.Result;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.HybridBinarizer;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RestController
public class image {

    public static void main(String[] args) throws Exception {
        // 解析读取二维码
        //解析图像
        BufferedImage i = ImageIO.read(new File("d:/test_picture.png"));
        LuminanceSource source = new BufferedImageLuminanceSource(i);
        BinaryBitmap image = new BinaryBitmap(new HybridBinarizer(source));
        try{
            Result result = new MultiFormatReader().decode(image);
            String info = result.getText();
            System.out.println(info);
            if(info==null || "".equals(info)){
                System.out.println("没有二维码信息");
            }else if(info.contains("weixin")){  //qq mp
                System.out.println("有微信二维码，不允许");
            }else{
                System.out.println("有二维，可以使用");
            }
        }catch(Exception e){
            System.out.println("图像中没有二维码");
        }
    }

    /**
     * Get a picture from Web with interface:"POST http://localhost:8088/upload" , the body is a photo with a file
     * http://localhost:8088/upload
     * @param photo
     * @return {"code": 200}
     * @throws IOException
     */
    //负责保存照片到本地
    @PostMapping("/upload")
    public Map upLoad( @RequestPart("photo") MultipartFile photo) throws IOException {
        System.out.println("start");
        String fileName = null;

        if(!photo.isEmpty()){
            //获取源照片名
            String originalFilename = photo.getOriginalFilename();
            //获取照片后缀名
            String suffixName=originalFilename.substring(originalFilename.lastIndexOf('.'));
            //使用UUID
            fileName= UUID.randomUUID().toString()+suffixName;
            //保存照片到磁盘
            new File("d://"+"fileName.png");
            photo.transferTo(new File("d://"+fileName));
        }

        /**
         * origin_code
         */
//        //创建photo对象
//        Photo p=new Photo(null,name,fileName);
//        //保存到数据库
//        photosService.save(p);
//
//        //重定向到照片展示页面
//        return "redirect:/";
        System.out.println("end");
        Map map=new HashMap<>();
        map.put("code",200);
        return map;
    }



    /**
     * Combine the two methods above
     * http://localhost:8088/upload_test
     * @param photo
     * @return {"code": 200}
     * @throws IOException
     */
    //
    @PostMapping("/upload_test")
    public Map upLoad_test( @RequestPart("photo") MultipartFile photo) throws IOException {
        System.out.println("start");
        String fileName = null;

        if(!photo.isEmpty()){
            //获取源照片名
            String originalFilename = photo.getOriginalFilename();
            //获取照片后缀名
            String suffixName=originalFilename.substring(originalFilename.lastIndexOf('.'));
            //使用UUID
            fileName= UUID.randomUUID().toString()+suffixName;
            //save picture to local

            //天坑!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
            // 文件流只可以接收读取一次，传输完毕则关闭流，所以如果transferto了，就没办法执行后面的getInputStream了，，想要不断的进行打开文件流，可以将文件保存到本地，对本地文件则可以重复打开关闭文件流
//            photo.transferTo(new File("d://"+fileName));


            BufferedImage srcImage = null;
            try {
                FileInputStream in = (FileInputStream) (photo.getInputStream());
                srcImage = javax.imageio.ImageIO.read(in);
            } catch (IOException e) {
                System.out.println("读取图片文件出错！" + e.getMessage());
            }

            // 解析读取二维码
            //解析图像

            LuminanceSource source = new BufferedImageLuminanceSource(srcImage);
            BinaryBitmap image = new BinaryBitmap(new HybridBinarizer(source));
            try{
                Result result = new MultiFormatReader().decode(image);
                String info = result.getText();
                System.out.println(info);
                if(info==null || "".equals(info)){
                    System.out.println("没有二维码信息");
                }else if(info.contains("weixin")){  //qq mp
                    System.out.println("有微信二维码，不允许");
                }else{
                    System.out.println("有二维，可以使用");
                }
            }catch(Exception e){
                System.out.println("图像中没有二维码");
            }
        }

        /**
         * origin_code
         */
//        //创建photo对象
//        Photo p=new Photo(null,name,fileName);
//        //保存到数据库
//        photosService.save(p);
//
//        //重定向到照片展示页面
//        return "redirect:/";
        System.out.println("end");
        Map map=new HashMap<>();
        map.put("code",200);
        return map;
    }




}
