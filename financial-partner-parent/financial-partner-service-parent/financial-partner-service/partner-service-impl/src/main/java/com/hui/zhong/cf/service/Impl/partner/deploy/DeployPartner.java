package com.hui.zhong.cf.service.Impl.partner.deploy;



import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;



/**
 * @author allen.shen
 *
 */
public class DeployPartner {
	
	private static final Logger logger = LoggerFactory.getLogger(DeployPartner.class);

	private static final String CORE_WORK_XML = "classpath:spring-base.xml";
	
	
	public static void main(String[] args) {
		ClassPathXmlApplicationContext context = null;
		try {
			context = new ClassPathXmlApplicationContext(new String[] {CORE_WORK_XML});
			context.registerShutdownHook();
			context.start();
			
			logger.info("partner-service 容器启动成功! ");
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
			logger.error("partner-service 容器启动错误", e);
		}
		
	}

}
