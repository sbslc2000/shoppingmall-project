package org.cau.shoppingmall;

import org.junit.jupiter.api.Test;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@EntityScan(basePackages = {"org.cau.shoppingmall"})
@ComponentScan(basePackages = {"org.cau.shoppingmall"})
class ShoppingmallApplicationTests {

	@Test
	void contextLoads() {
	}

}
