package wmp.test.service.tool;

import java.io.IOException;
import java.io.Reader;

import org.springframework.stereotype.Component;

import wmp.test.bdt.CharactorGroup;
@Component
public class CharactorCounter {
	public CharactorGroup countCharactor(Reader reader) throws IOException {

		int ch;
		int[] numArr = new int[10];
		int[][] alphabetArr = new int[2][26];
		
		while((ch = reader.read()) != -1) {
			if(ch>='0' && ch<='9') {
				numArr[ch-'0']++;
			}else if(ch>='A' && ch<='Z') {
				alphabetArr[0][ch-'A']++;
			}else if(ch>='a' && ch<='z') {
				alphabetArr[1][ch-'a']++;
			}
		}
		CharactorGroup result = new CharactorGroup();
		result.setAlphabetArr(alphabetArr);
		result.setNumArr(numArr);
		return result;
	}
}
