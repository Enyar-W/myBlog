package org.tl.blog;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BlogApplicationTests {

	@Test
	public void contextLoads() {

		You you = new You();
		boolean rightPeople = you.getRightPeople();
		System.out.println("相伴终生。");
		while(rightPeople){
			System.out.println("💗💗💗");
		}
	}

}

class You{
	public boolean getRightPeople(){
		return true;
	}
}