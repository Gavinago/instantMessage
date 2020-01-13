package com.instant.base;
import org.junit.After;
import org.junit.Before;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.runner.RunWith;
/**
* 	类说明 
*
* @author grl
* @date 2019年12月20日  新建 
*/
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.instant.main.InstantMessageApplication;


//这是JUnit的注解，通过这个注解让SpringJUnit4ClassRunner这个类提供Spring测试上下文。  
@RunWith(SpringRunner.class)  
//这是Spring Boot注解，为了进行集成测试，需要通过这个注解加载和配置Spring应用上下  
@SpringBootTest(classes=InstantMessageApplication.class)
@WebAppConfiguration 
public class BaseTest {
 
    @Before
    public void init() {
        System.out.println("开始测试-----------------");
    }
 
    @After
    public void after() {
        System.out.println("测试结束-----------------");
    }
}
