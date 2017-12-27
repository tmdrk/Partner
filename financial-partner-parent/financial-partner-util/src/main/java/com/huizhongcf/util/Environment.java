package com.huizhongcf.util;


import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.web.context.ContextLoader;

/**
 * 
 *
 * Description: 
 *
 * @author yaodawei
 * @version 1.0
 * <pre>
 * Modification History: 
 * Date         Author      Version     Description 
 * ------------------------------------------------------------------ 
 * 2014-1-7    yaodawei       1.0        1.0 Version 
 * </pre>
 */
public class Environment {

	private static ApplicationContext applicationContext;
	
	private static final String[] CONFIG_LOCATIONS = new String[]{"classpath:spring/spring-base.xml","classpath:spring/spring-redis.xml","classpath:spring/spring-hessian.xml"};
	
	static {
		applicationContext = ContextLoader.getCurrentWebApplicationContext();
		if(applicationContext == null){
			applicationContext = new ClassPathXmlApplicationContext(CONFIG_LOCATIONS);
		}
	}

	/**
	 * 需要通过 Environment 获得的资源,都写在environment_xmlPath所对应的xml中
	 */
	private Environment(){
		
	}

	private static Environment env = new Environment();
	
	/**
	 * 
	 * Description: 获取Environment实例对象
	 *
	 * @param 
	 * @return Environment
	 * @throws 
	 * @Author yaodawei
	 * Create Date: 2014-1-7 下午04:17:10
	 */
	public static Environment getInstance(){
		if(env==null){
			env=new Environment();
		}
		return env;
	}
	
	/**
	 * 
	 * Description: 根据对象类型查询bean
	 *
	 * @param c 
	 * @return E
	 * @throws 
	 * @Author yaodawei
	 * Create Date: 2014-1-7 下午04:20:21
	 */
	@SuppressWarnings("unchecked")
	public <E> E getService(Class<E> c) throws Exception{
		//从指定的spring配置文件中读取是否有c类型的（包括其子类型）的bean
		String[] names = applicationContext.getBeanNamesForType(c);

		//配置文件中每个对应的接口只配置一个，所以可以拿第一个，如果有多个，也只拿第一个
		if (names.length >= 1) {
		  return (E) applicationContext.getBean(names[0]);
		}else{
			throw new Exception("没有这样的业务类存在");
		}
	}
	
	/**
	 * 
	 * Description: 根据beanId查询对象
	 *
	 * @param 
	 * @return Object
	 * @throws 
	 * @Author yaodawei
	 * Create Date: 2014-1-7 下午04:17:39
	 */
	public static Object getBean(String beanId){
		return applicationContext.getBean(beanId);
	}
	
	/**
	 * 获得userCenter远程接口服务类
	 * @return
	 */
	/*public EmployeeWebService getEmployeeWebService(){
		EmployeeWebService emService=(EmployeeWebService)applicationContext.getBean("employeeService");
		return emService;
	}*/
	
	/**
	 * 获得lender远程接口服务类
	 * @return
	 */
	/*public BelongLenderSale getLenderGiftService(){
		BelongLenderSale service=(BelongLenderSale)applicationContext.getBean("lenderGiftService");
		return service;
	}*/
}
