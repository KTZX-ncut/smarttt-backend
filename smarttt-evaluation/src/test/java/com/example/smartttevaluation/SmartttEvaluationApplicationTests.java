package com.example.smartttevaluation;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@SpringBootTest
class SmartttEvaluationApplicationTests {

    //记录器
    Logger logger = LoggerFactory.getLogger(getClass());

    //private static final Logger logger = LoggerFactory.getLogger(SmartttEvaluationApplicationTests.class);

    void contextLoads() {

        //日志的级别
        //由低到高  trace < debug < info < warn < error
        //可以调整输出的日志级别：日志只会在这个级别及级别以后的高级别生效
        //比如只想要warn和error，可以把级别调高，只输出级别较高的日志，如warn及以后的级别
        logger.trace("这是trace日志...");//跟踪轨迹
        logger.debug("这是debug日志...");//调试
        //SpringBoot默认给我们是用的是info级别的，没有指定级别的就用SpringBoot默认规定的级别：root级别
        logger.info("这是info日志...");//自己定义的
        logger.warn("这是warn日志...");//警告
        logger.error("这是error日志...");//错误

    }

}
