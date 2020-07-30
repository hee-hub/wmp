package wmp.test.service.tool;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import wmp.test.bdt.CharactorGroup;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(value = "/applicationContext.xml")
public class CharactorCounterTest {
	@Autowired
	CharactorCounter counter;

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void test() throws IOException {
		String str = "104dAaaaa32Bbe";
		Reader reader = new StringReader(str);
		
		CharactorGroup group = counter.countCharactor(reader);
		int[][] alArr = group.getAlphabetArr();
		int[] numArr = group.getNumArr();
		for(int i=0;i<2;i++) {
			for(int j=0;j<26;j++) {
				System.out.print(alArr[i][j]+ " ");
			}
			System.out.println();
		}
		for(int i=0;i<10;i++) {
			System.out.print(numArr[i]);
		}
		System.out.println();
		assertEquals(1, alArr[0][0]);
	}

}
