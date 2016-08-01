package com.hand;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.google.gson.JsonObject;

public class App {
	public static void main(String[] args) {
		new GetThread().run();
		
		
		JsonObject object = new JsonObject();
		
		object.addProperty("name", "汉得信息");
		object.addProperty("open",13.910 );
		object.addProperty("close", 14.550);
		object.addProperty("current", 13.500);
		object.addProperty("high", 14.040);
		object.addProperty("low", 13.270);
		object.addProperty("date", "2016-08-01,15:05:03,00");
		
		File f = new File("hand-china.json");
		//创建XML文件
		File file = new File("hand-china.XML");
		//创建XML文件
		try {
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document document = builder.newDocument();
			
			Element name = document.createElement("name");
			name.setTextContent("汉得信息");
			Element name1 = document.createElement("open");
			name1.setTextContent("13.910");
			Element name2 = document.createElement("close");
			name2.setTextContent("14.550");
			Element name3 = document.createElement("current");
			name3.setTextContent("13.500");
			Element name4 = document.createElement("hign");
			name4.setTextContent("14.040");
			Element name5 = document.createElement("low");
			name5.setTextContent("13.270");
			Element name6 = document.createElement("date");
			name6.setTextContent("2016-08-01,15:05:03,00");
			name.appendChild(name1);
			name.appendChild(name2);
			name.appendChild(name3);
			name.appendChild(name4);
			name.appendChild(name5);
			name.appendChild(name6);
			
			document.appendChild(name);
			
			//------------------
			TransformerFactory transfomerFactory = TransformerFactory.newInstance();
			Transformer transfomer = transfomerFactory.newTransformer();
			
			transfomer.transform(new DOMSource(document),new StreamResult(new File("hand-china.xml")) );
			
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TransformerConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TransformerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	
		//---
		
		try {
			FileOutputStream fos = new FileOutputStream(f);
			PrintWriter bos = new PrintWriter(fos);
			String s = object.toString();
			bos.write(s);
			bos.flush();
			fos.close();
			
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	 
}


class GetThread extends Thread{
	
	HttpClient client = HttpClients.createDefault();
	
	
	public void run(){
		HttpGet get = new HttpGet("http://hq.sinajs.cn/list=sz300170");
		try {
			
			HttpResponse response =  client.execute(get);
			HttpEntity entity = response.getEntity();
			String result = EntityUtils.toString(entity, "UTF-8");
			
			System.out.println(result);
			
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
