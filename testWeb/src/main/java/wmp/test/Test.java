package wmp.test;

public class Test {
	static Test test = new Test();	//유일한 객체(싱글톤)
	public static Test getInstance() {	//객체 획득하는 함수
		return test;
	}
	private Test() {}					//생성자 숨김
}
