package com.baselogic.tutorials.demos;

//Static imports
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;
import static org.mockito.Matchers.any;

import com.baselogic.tutorials.domain.Customer;
import com.baselogic.tutorials.domain.Order;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


/**
 * SpelTests
 *
 * <p>Spring Certification objective: 1.2 Lifecycle</p>
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
public class SpelTests {

	private static final Logger logger = LoggerFactory.getLogger(SpelTests.class);

	@Autowired
	ApplicationContext applicationContext;

    @BeforeClass
    public static void beforeClass(){
        logger.info("");
    }

    @Test
	public void testOrderNumberGenerator() {
		Customer customer = applicationContext.getBean(Customer.class);
		assertNotNull(customer);
		logger.info(">>>------------------------------------------------->>>");
		logger.info(">>>------------------------------------------------->>>");
		logger.info("SpEL Customer: {}", customer.toString());
	}

	@Test
	public void testOrderNoteFormatter() {

		Order order = applicationContext.getBean(Order.class);

		logger.info(">>>------------------------------------------------->>>");
		logger.info(">>>------------------------------------------------->>>");
		logger.info("SpEL Order: {}", order.toString());
		logger.info(">>>------------------------------------------------->>>");
		logger.info(">>>------------------------------------------------->>>");
		logger.info(">>>------------------------------------------------->>>");
	}


}
