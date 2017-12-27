package com.hui.zhong.cf.mobile.deploy;



import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;



/**
 * @author allen.shen
 *
 */
public class DeployMobile {
	
	private static final Logger logger = LoggerFactory.getLogger(DeployMobile.class);

	private static final String CORE_WORK_XML = "classpath:spring-base.xml";
	
	
	public static void main(String[] args) {
		ClassPathXmlApplicationContext context = null;
		try {
			context = new ClassPathXmlApplicationContext(new String[] {CORE_WORK_XML});
			context.registerShutdownHook();
			context.start();
			
			logger.info("mobile-service-impl 容器启动成功! ");
			Object lock = new Object();
			synchronized (lock) {
				try {
					while (true)
						lock.wait();
				} catch (InterruptedException e) {
					logger.error(e.getMessage(), e);
				}
			}
		} catch (Exception e) {
			logger.error("mobile-service-impl 容器启动错误", e);
		}
		
	}

}
