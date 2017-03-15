package com.tyiti.test;


import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;

public class dsfTest {
	
	public String path = "D:\\dsf\\frontcli.xml";//该路径为xml文件的绝对路径，我这里是我本地的测试路径，企业使用时需要根据自己环境来修改
	private static final String TEST_URL = "183.203.219.151"; //测试地址
	private static final int TEST_PORT =12346; //测试端口号（短连接）
	
	public static void main(String[] args) {
		Test test = new Test();
		Socket socket = null;
		try {
			//创建一个流套接字并将其连接到指定主机上的指定端口号
			socket = new Socket(TEST_URL, TEST_PORT);
			//向服务器端发送数据
			DataOutputStream outputStream = new DataOutputStream(socket.getOutputStream());
			outputStream.write(test.tranProtReq());
			//读取服务器端数据
			DataInputStream inputStream = new DataInputStream(socket.getInputStream());  
			ByteArrayOutputStream bos = new ByteArrayOutputStream(1000);  
			StringBuffer sb = new StringBuffer(); 
			
			byte[] buffer = null;
	        byte[] b = new byte[1024];  
	        int len = -1;  
	        while ((len = inputStream.read(b)) != -1) {  
	            bos.write(b, 0, len);  
	            sb.append(new String(b,0,len)); 
	        }  
	        bos.close();  
	        buffer = bos.toByteArray();
	        System.out.println(new String(buffer, "GBK"));
			test.tranProtResp(buffer);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
}
