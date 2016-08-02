package com.hand;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.google.gson.JsonObject;

/**
 * Hello world!
 * 
 */
public class demo {
	public static void main(String[] args) {
		try {

			URL url = new URL("http://hq.sinajs.cn/list=sz300170");
			URLConnection connection = url.openConnection();// 建立url连接
			InputStream is = connection.getInputStream();
			InputStreamReader isr = new InputStreamReader(is, "GBK");// 如果乱码，可以在此设置编码集
			BufferedReader br = new BufferedReader(isr);

			String line;
			StringBuilder builder = new StringBuilder();
			while ((line = br.readLine()) != null) {
				builder.append(line);
			}
			System.out.println(builder);
			br.close();
			isr.close();
			is.close();

		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// 创建XML文件
		File file = new File("hand.XML");
		// 创建XML文件
		try {
			DocumentBuilderFactory factory = DocumentBuilderFactory
					.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document document = builder.newDocument();

			Element name = document.createElement("stock");
			Element name11 = document.createElement("name");
			name11.setTextContent("汉得信息");
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
			name.appendChild(name11);
			name.appendChild(name1);
			name.appendChild(name2);
			name.appendChild(name3);
			name.appendChild(name4);
			name.appendChild(name5);
			name.appendChild(name6);

			document.appendChild(name);

			// ------------------
			TransformerFactory transfomerFactory = TransformerFactory
					.newInstance();
			Transformer transfomer = transfomerFactory.newTransformer();

			transfomer.transform(new DOMSource(document), new StreamResult(
					new File("hand.xml")));

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

		// 获取json对象
		JsonObject object = new JsonObject();
		// 创建字符串键值
		object.addProperty("name", "汉得信息");
		object.addProperty("open", 13.910);
		object.addProperty("close", 14.550);
		object.addProperty("current", 13.500);
		object.addProperty("high", 14.040);
		object.addProperty("low", 13.270);
		object.addProperty("date", "2016-08-01,15:05:03,00");
		// 创建json文件
		File f = new File("hand.json");

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
