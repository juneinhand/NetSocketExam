package com.hand;


import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class App {
	public static void main(String[] args) {
		URL url;
		try {
			url = new URL("http://files.saas.hand-china.com/java/target.pdf");
			URLConnection urlConnection = url.openConnection();
			InputStream inputStream = urlConnection.getInputStream();
			BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream);

			byte[] b = new byte[1024];
			FileOutputStream fileOutputStream = new FileOutputStream("target.pdf", true);
			int count = bufferedInputStream.read(b, 0, 1023);
			while (count > 0) {
				fileOutputStream.write(b, 0, count);
				count = bufferedInputStream.read(b, 0, 1023);
			}

			fileOutputStream.close();
			bufferedInputStream.close();
			inputStream.close();

		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
