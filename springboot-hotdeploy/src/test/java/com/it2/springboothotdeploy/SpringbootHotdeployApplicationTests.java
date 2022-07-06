package com.it2.springboothotdeploy;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;


//@SpringBootTest(properties = {"test.abc=hello2"})
//@SpringBootTest(args = {"--test.abc=hello3"})
@SpringBootTest(properties = {"test.abc=hello2"},args = {"--test.abc=hello3"})
class SpringbootHotdeployApplicationTests {

	@Value("${test.abc}")
	private String abc;

	@Test
	void contextLoads() {
		System.out.println(abc);
	}

}
