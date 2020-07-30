package wmp.test.service.processor;

import java.io.IOException;
import java.io.Reader;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import wmp.test.bdt.CharactorGroup;
import wmp.test.service.http.URLReader;
import wmp.test.service.tool.CharactorCounter;
@Component
public class TestProcessor {
	@Autowired
	URLReader urlReader;
	@Autowired
	CharactorCounter charactorCounter;
	
	public String[] process(String url, int divisor, String type) throws IOException {
		
		Reader contentReader = urlReader.getReaderFromURL(url);
		CharactorGroup group = charactorCounter.countCharactor(contentReader, type);
		int[][] alphabetArr = group.getAlphabetArr();
		int[] numArr = group.getNumArr();
		
		
		StringBuilder sb = new StringBuilder();
		int numPos=0;
		int alphabetType = 0;
		int alphabetPos = 0;
		while(alphabetPos<26 || numPos<10) {
			//영어먼
			while(alphabetPos<26 && alphabetArr[alphabetType][alphabetPos]==0) {
				if(alphabetType==0) {
					alphabetType=1;
				}else {
					alphabetType=0;
					alphabetPos++;
				}
			}
			if(alphabetPos<26) {
				if(alphabetType == 0) {
					sb.append((char)('A'+alphabetPos));
				}else {
					sb.append((char)('a'+alphabetPos));
				}
				alphabetArr[alphabetType][alphabetPos]--;
			}
			//숫자
			while(numPos<10 && numArr[numPos]==0) {
				numPos++;
			}
			if(numPos<10) {
				sb.append(numPos);
				numArr[numPos]--;
			}
		}
		int length  = sb.length();
		int rest = length%divisor;
		int portion = length - rest;
		String[] answerArr = new String[2];
		answerArr[0] = sb.substring(0,  portion);
		answerArr[1] = sb.substring(portion, length);
		return answerArr;
	}
}
