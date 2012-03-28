package com.baselogic.aspects;

import java.lang.reflect.Method;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.MethodBeforeAdvice;

import com.baselogic.dao.OrderDAO;
import com.baselogic.domain.Order;

/**
 * AroundAdvice
 * 
 * <p>Spring Certification objective: 2.1 AOP Recommendations</p>
 * <p>Spring Certification objective: 2.2 AOP Pointcuts</p>
 * <p>Spring Certification objective: 2.3 AOP Advice</p>
 * 
 * @see <a href="http://springcert.sourceforge.net/core-3/index.html#aop">Objective 2.1 AOP Recommendations</a>
 * @see <a href="http://springcert.sourceforge.net/core-3/index.html#aop">Objective 2.2 AOP Pointcuts</a>
 * @see <a href="http://springcert.sourceforge.net/core-3/index.html#aop">Objective 2.3 AOP Advice</a>
 *
 * @author Mick Knutson
 * @see <a href="http://www.baselogic.com">Blog: http://baselogic.com</a>
 * @see <a href="http://linkedin.com/in/mickknutson">LinkedIN: http://linkedin.com/in/mickknutson</a>
 * @see <a href="http://twitter.com/mickknutson">Twitter: http://twitter.com/mickknutson</a>
 * @see <a href="http://github.com/mickknutson">Git hub: http://github.com/mickknutson</a>
 * 
 * @see <a href="http://www.packtpub.com/java-ee6-securing-tuning-extending-enterprise-applications-cookbook/book">JavaEE 7 Cookbook Packt</a>
 * @see <a href="http://www.amazon.com/Cookbook-securing-extending-enterprise-applications/dp/1849683166">JavaEE 7 Cookbook Amazon</a>
 * 
 * @since 2012
 * 
 */
@Aspect
public class AroundAdvice {
	
	private final Logger logger = LoggerFactory.getLogger(AroundAdvice.class);

	@Pointcut("execution(* com.baselogic.service.*.placeDelayedOrder(..))")
    public void placeDelayedOrderService() {}	

	
	
    //@Around("placeDelayedOrderService()")
    public void aroundPlaceDelayedOrderService(ProceedingJoinPoint joinpoint){
	    try {
		    logger.debug(">>> ----- aroundPlaceDelayedOrderService...>>>");
		    long start = System.currentTimeMillis();
		    
		    joinpoint.proceed();
		    
		    long end = System.currentTimeMillis();

		    logger.info(">>> ----- The order took {} milliseconds to complete.", (end-start));
	    } catch(Throwable t){
	    	logger.error(t.getMessage());
	    }
    }
}