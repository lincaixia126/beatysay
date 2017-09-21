package com.example.lin.net.okhttp;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class EsmTest {
    /**
     * 通过HttpURLConnection模拟post表单提交
     * @throws Exception
     */
    public void sendEms() throws Exception {
        String wen = "MS2201828";
        String btnSearch = "EMS快递查询";
        URL url = new URL("http://www.kd185.com/ems.php");
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("POST");// 提交模式
        // conn.setConnectTimeout(10000);//连接超时 单位毫秒
        // conn.setReadTimeout(2000);//读取超时 单位毫秒
        conn.setDoOutput(true);// 是否输入参数
        StringBuffer params = new StringBuffer();
        // 表单参数与get形式一样
        params.append("wen").append("=").append(wen).append("&")
              .append("btnSearch").append("=").append(btnSearch);
        byte[] bypes = params.toString().getBytes();
        conn.getOutputStream().write(bypes);// 输入参数
        InputStream inStream=conn.getInputStream();
        //System.out.println(new String(StreamTool.readInputStream(inStream), "gbk"));

    }

    public void sendSms() throws Exception{
        String message="货已发到";
        message= URLEncoder.encode(message, "UTF-8");
        System.out.println(message);
        String path ="http://localhost:8083/DS_Trade/mobile/sim!add.do?message="+message;
        URL url =new URL(path);
        HttpURLConnection conn = (HttpURLConnection)url.openConnection();
        conn.setConnectTimeout(5*1000);
        conn.setRequestMethod("GET");
        InputStream inStream = conn.getInputStream();    
//        byte[] data = StreamTool.readInputStream(inStream);
//        String result=new String(data, "UTF-8");
//        System.out.println(result);
    }
}

