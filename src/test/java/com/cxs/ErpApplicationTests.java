package com.cxs;

import com.cxs.domain.People;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ErpApplicationTests {

    List<People> list = Arrays.asList(
            new People("耀哥", 22, "武汉"),
            new People("李哥", 20, "武汉"),
            new People("章哥", 50, "武汉"),
            new People("耀晨哥", 10, "武汉"),
            new People("会哥", 33, "武汉"),
            new People("荷藕哥", 66, "武汉")
    );


    @Test
    public void contextLoads() {
        list.stream().filter((e) -> e.getAge() >= 30)
                .limit(2)
                .forEach(System.out::println);
        System.out.println("=====================");
        list.stream().map(People::getName).forEach(System.out::println);

    }

}
