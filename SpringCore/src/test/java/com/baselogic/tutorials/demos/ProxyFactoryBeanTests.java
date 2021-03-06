package com.baselogic.tutorials.demos;

//Hamcrest
//import static org.hamcrest.CoreMatchers.*;
//import static org.hamcrest.core.IsNull.*;
import static org.hamcrest.MatcherAssert.assertThat;

//JUnit
//import static org.junit.Assert.*;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;

//Mockito
import static org.mockito.Matchers.any;
import static org.junit.Assert.assertEquals;

//PowerMock

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.baselogic.tutorials.service.ExampleService;

/**
 * ProxyFactoryBeanTests
 *
 * <p>Spring Certification objective: 2.1</p>
 *
 * at_see <a href="http://springcert.sourceforge.net/core-3/index.html#beans">Objective 1.2 Lifecycle</a>
 *
 * @author Mick Knutson
 * at_see <a href="http://www.baselogic.com">Blog: http://baselogic.com</a>
 * at_see <a href="http://linkedin.com/in/mickknutson">LinkedIN: http://linkedin.com/in/mickknutson</a>
 * at_see <a href="http://twitter.com/mickknutson">Twitter: http://twitter.com/mickknutson</a>
 * at_see <a href="http://github.com/mickknutson">Git hub: http://github.com/mickknutson</a>
 *
 * at_see <a href="http://www.packtpub.com/java-ee6-securing-tuning-extending-enterprise-applications-cookbook/book">JavaEE 6 Cookbook Packt</a>
 * at_see <a href="http://www.amazon.com/Cookbook-securing-extending-enterprise-applications/dp/1849683166">JavaEE 6 Cookbook Amazon</a>
 *
 * @since 2012
 *
 */
@ContextConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
public class ProxyFactoryBeanTests {

	private static final Logger logger = LoggerFactory.getLogger(ProxyFactoryBeanTests.class);

	@Autowired
	ApplicationContext applicationContext;

    @BeforeClass
    public static void beforeClass(){
        logger.info("");
    }

    @Test
	public void testCustomerWithProxyFactoryService(){
		logger.info(">>>------------------------------------------------->>>");

		ExampleService service = applicationContext.getBean("exampleServiceInitializingBeanImplProxy", ExampleService.class);

		logger.info("service: {}", service.toString());

		//SimpleBean simpleBean = ((ExampleServiceInitializingBeanImpl)service).getSimpleBean();
		//logger.info("simpleBean: {}", simpleBean.getMessage());

		//assertThat(service instanceof UnImplementedService, is(true));
		//assertThat(service instanceof ExampleServiceInitializingBeanImpl, is(true));
		//assertThat(service instanceof InitializingBean, is(true));
		//assertThat(service instanceof ExampleService, is(true));
		//assertThat(service instanceof DisposableBean, is(true));

	}

}
