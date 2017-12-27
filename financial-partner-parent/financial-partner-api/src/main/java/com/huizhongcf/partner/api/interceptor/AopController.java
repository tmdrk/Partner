package com.huizhongcf.partner.api.interceptor;


import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletWebRequest;

import com.huizhongcf.util.CommonUtil;

import javassist.ClassClassPath;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;
import javassist.Modifier;
import javassist.bytecode.CodeAttribute;
import javassist.bytecode.LocalVariableAttribute;
import javassist.bytecode.MethodInfo;

/**
 * 
 *
 * Description: 同意打印入参和出参日志格式
 *
 * @author baohongjian
 * @version 1.0
 * <pre>
 * Modification History: 
 * Date         Author      Version     Description 
 * ------------------------------------------------------------------ 
 * 2017年10月10日    bao       1.0        1.0 Version 
 * </pre>
 */
@Component
@Aspect
public class AopController {
	private static final Logger logger = LoggerFactory.getLogger(AopController.class);
	
    @Before("execution(* com.huizhongcf.partner.api.controller.*.*(..))")  
    public void before(JoinPoint joinPoint) throws Throwable {
    	logger.info("------------------start-Controller---------------------");
    	String classType = joinPoint.getTarget().getClass().getName();    
		Class<?> clazz = Class.forName(classType);    
		String clazzName = clazz.getName();    
		String methodName = joinPoint.getSignature().getName(); //获取方法名称   
		Object[] args = joinPoint.getArgs();//参数值
		//获取参数名称和参数值
		Map<String,Object > nameAndArgs = getFieldsName(this.getClass(), clazzName, methodName,args);
		logger.info("@执行方法:"+joinPoint.getSignature().getDeclaringTypeName()+"."+joinPoint.getSignature().getName());
		logger.info("@请求参数:"+nameAndArgs);
		logger.info("@请求包体:"+CommonUtil.getBody());
    }
    
    @AfterThrowing(pointcut = "execution(* com.huizhongcf.partner.api.controller.*.*(..))", throwing = "ex")  
    public void afterThrowing(Exception ex) {  
    	 logger.error("出现异常信息,如下:", ex);
    	 logger.info("------------------end-Controller---------------------");
    	 MDC.clear();
    }
    
    @AfterReturning(pointcut="execution(* com.huizhongcf.partner.api.controller.*.*(..))", 
            returning="returnValue")  
    public void afterReturning(JoinPoint point, Object returnValue){  
        logger.info("@响应参数:"+returnValue);
        logger.info("------------------end-Controller---------------------");
        MDC.clear();
    }  


    private void writeContent(String content) {
        HttpServletResponse response = ((ServletWebRequest) RequestContextHolder.getRequestAttributes()).getResponse();
        response.setCharacterEncoding("UTF-8");
        response.setHeader("Content-Type", "text/plain;charset=UTF-8");
        PrintWriter writer = null;
        try {
            writer = response.getWriter();
        } catch (IOException e) {
            e.printStackTrace();
        }
        writer.print(content);
        writer.flush();
        writer.close();
    }

	private Map<String,Object> getFieldsName(Class<?> cls, String clazzName, String methodName, Object[] args) throws Exception {   
		Map<String,Object > map=new HashMap<String,Object>();  
		ClassPool pool = ClassPool.getDefault();    
		ClassClassPath classPath = new ClassClassPath(cls);    
		pool.insertClassPath(classPath);    
		CtClass cc = pool.get(clazzName);    
		CtMethod cm = cc.getDeclaredMethod(methodName);    
		MethodInfo methodInfo = cm.getMethodInfo();  
		CodeAttribute codeAttribute = methodInfo.getCodeAttribute();    
		LocalVariableAttribute attr = (LocalVariableAttribute) codeAttribute.getAttribute(LocalVariableAttribute.tag);    
		int pos = Modifier.isStatic(cm.getModifiers()) ? 0 : 1;    
		for (int i = 0; i < cm.getParameterTypes().length; i++){    
			map.put( attr.variableName(i + pos),args[i]);
		}    
		return map;    
	}
	
	

/* 没有异常的情况
2017-10-13 21:09:30.972 [5fe9ffc3-7309-4ca3-9769-16984d20756f] INFO  hui.zhong.cai.fu.batch.util.Aop 80 : around前置
2017-10-13 21:09:30.974 [5fe9ffc3-7309-4ca3-9769-16984d20756f] INFO  hui.zhong.cai.fu.batch.util.Aop 48 : before
2017-10-13 21:09:30.993 [5fe9ffc3-7309-4ca3-9769-16984d20756f] INFO  hui.zhong.cai.fu.batch.controller.BatchController 62 : 执行put方法
2017-10-13 21:09:30.994 [5fe9ffc3-7309-4ca3-9769-16984d20756f] INFO  hui.zhong.cai.fu.batch.util.Aop 83 : around后置
2017-10-13 21:09:30.995 [5fe9ffc3-7309-4ca3-9769-16984d20756f] INFO  hui.zhong.cai.fu.batch.util.Aop 54 : after
2017-10-13 21:09:30.995 [5fe9ffc3-7309-4ca3-9769-16984d20756f] INFO  hui.zhong.cai.fu.batch.util.Aop 65 : @AfterReturning:{success=success}

	
	

 * 异常情况
2017-10-13 21:16:49.457 [032620d0-e6ca-4a17-b363-fd87db5ce50e] INFO  hui.zhong.cai.fu.batch.util.Aop 80 : around前置
2017-10-13 21:16:49.458 [032620d0-e6ca-4a17-b363-fd87db5ce50e] INFO  hui.zhong.cai.fu.batch.util.Aop 48 : before
2017-10-13 21:16:49.478 [032620d0-e6ca-4a17-b363-fd87db5ce50e] INFO  hui.zhong.cai.fu.batch.controller.BatchController 61 : 执行put方法
2017-10-13 21:16:49.480 [032620d0-e6ca-4a17-b363-fd87db5ce50e] INFO  hui.zhong.cai.fu.batch.util.Aop 54 : after
2017-10-13 21:16:49.480 [032620d0-e6ca-4a17-b363-fd87db5ce50e] INFO  hui.zhong.cai.fu.batch.util.Aop 59 : afterThrowing

	*/
}