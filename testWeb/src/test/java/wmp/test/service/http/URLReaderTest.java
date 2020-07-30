package wmp.test.service.http;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNotSame;

import java.io.IOException;
import java.io.Reader;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(value = "/applicationContext.xml")
public class URLReaderTest {
	@Autowired
	URLReader urlReader;

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void test() throws IOException {
		try (Reader reader = urlReader.getReaderFromURL("http://google.com")) {
			assertNotNull(reader);
		}
	}
	@Test
	public void testResponse() throws IOException {
		try (Reader reader = urlReader.getReaderFromURL("http://google.com")) {
			int ch;
			StringBuilder sb = new StringBuilder();
			while((ch = reader.read()) != -1) {
				sb.append((char)ch);
			}
			assertNotSame(0, sb.toString().length());
		}
	}

}
