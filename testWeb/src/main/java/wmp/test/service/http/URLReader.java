package wmp.test.service.http;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.net.URLConnection;

import org.springframework.stereotype.Component;

@Component
public class URLReader {
	public Reader getReaderFromURL(String urlString) throws IOException{
		URL url = new URL(urlString);
		URLConnection conn = url.openConnection();
		String contentType = conn.getContentType();
		if(contentType == null) {
			return new InputStreamReader(conn.getInputStream());
		}
		contentType = contentType.toUpperCase();
		contentType = contentType.replaceAll("\\s", "");
		int charsetIdx = contentType.indexOf("CHARSET=");
		if(charsetIdx == -1) {
			return new InputStreamReader(conn.getInputStream());
		}
		String charset = contentType.substring(charsetIdx+8, contentType.length());
		int semiIdx = charset.indexOf(";");
		if(semiIdx != -1) {
			charset = charset.substring(semiIdx);
		}
		System.out.println(charset);
		return new InputStreamReader(conn.getInputStream(), charset);
	}
}
