package wmp.test.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import wmp.test.service.processor.TestProcessor;

/**
 * 과제용 컨트롤러
 * @author we
 * @since 2020.07.28
 */
@Controller
public class TestController{
	@Autowired
	TestProcessor testProcessor;
	
	@ResponseBody
	@RequestMapping(value="/test.do", method=RequestMethod.POST, produces = "application/json;charset=UTF-8")
	public String test(String url, int divisor, String type) throws IOException {
		String[] result = testProcessor.process(url, divisor, type);
		String res = "[\"";
		res += result[0]+"\",\""+result[1]+"\"]";
		return res;
	}

}
