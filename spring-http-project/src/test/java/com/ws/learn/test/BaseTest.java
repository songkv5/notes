package com.ws.learn.test;

import com.ws.learn.http.server.Application;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author willis <songkai01>
 * @desc
 * @since 2018年08月02日 17:59
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Application.class)
public abstract class BaseTest {
    protected Logger logger = LoggerFactory.getLogger(getClass());
}

