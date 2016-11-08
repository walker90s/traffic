package com.tt.traffic.test;

import org.apache.log4j.Logger;
import org.junit.runner.RunWith;  
import org.springframework.test.context.ContextConfiguration;  
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;  
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;  

//指定bean注入的配置文件  
@ContextConfiguration(locations = { "classpath:app-*.xml" })  
//使用标准的JUnit @RunWith注释来告诉JUnit使用Spring TestRunner  
@RunWith(SpringJUnit4ClassRunner.class) 
public class BaseTest extends AbstractJUnit4SpringContextTests {

	Logger logger = Logger.getLogger(this.getClass());
}
