package com.baselogic.tutorials.aspects;

import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * AfterThrowingAdvice
 *
 * @AfterThrowing
 * After on failure only
 * Can change exception type thrown
 * Use:
 * 		@AfterThrowing(pointcut="...", throwing="ex")
 * 		public void doAdvice(Exception ex)
 *
 * Spring supported types of pointcuts:
 *
 * <p>Spring Certification objective: 2.1 AOP Recommendations</p>
 * <p>Spring Certification objective: 2.2 AOP Pointcuts</p>
 * <p>Spring Certification objective: 2.3 AOP Advice</p>
 *
 * at_see <a href="http://springcert.sourceforge.net/core-3/index.html#aop">Objective 2.1 AOP Recommendations</a>
 * at_see <a href="http://springcert.sourceforge.net/core-3/index.html#aop">Objective 2.2 AOP Pointcuts</a>
 * at_see <a href="http://springcert.sourceforge.net/core-3/index.html#aop">Objective 2.3 AOP Advice</a>
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
@Aspect
public class AfterThrowingAdvice {

	private final Logger logger = LoggerFactory.getLogger(AfterThrowingAdvice.class);

	@Pointcut("execution(* com.baselogic.tutorials.dao.*.*(..))")
    public void dataAccessOperation() {}


	@AfterThrowing(
		    pointcut="execution(* com.baselogic.tutorials.dao.*.*(..))",
		    throwing="ex")

	//Fixme: does not work: @AfterThrowing("dataAccessOperation() && throwing(ex,..)")
	public void afterThrowingOrderDao(Exception ex) throws Throwable {

		logger.info(">>> ----- afterThrowingOrderDao...>>> {}", ex.getMessage());
	}

}

















