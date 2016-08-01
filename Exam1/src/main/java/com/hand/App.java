package com.hand;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
/*
 *  (8 分)	使用程序从网上下载pdf,网址为 http://files.saas.hand-china.com/java/target.pdf,
 *  保存在本地, 编程时使用带缓冲的读写,将需要保证保存后的pdf文件能正常 
 */
public class App {
	public static void main(String[] args) {
		new ReadByGet().start();
	}
	static class ReadByGet extends Thread{
		public void run(){
			try {
				
				URL url = new URL("http://files.saas.hand-china.com/java/target.pdf");
				URLConnection connection = url.openConnection();//建立url连接
				InputStream is = connection.getInputStream();
				BufferedInputStream bi = new BufferedInputStream(is,5000);
				FileOutputStream fos = new FileOutputStream(new File("target.pdf"));
				BufferedOutputStream bos = new BufferedOutputStream(fos,5000);
				byte b[] = new byte[1000];
				while(bi.read(b)!=-1){
					bos.write(b);
				}
				bos.flush();
				bos.close();
				fos.close();
				bi.close();
				is.close();
			
			} catch (MalformedURLException e) {
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
