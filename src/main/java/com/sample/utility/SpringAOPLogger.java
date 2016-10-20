package com.sample.utility;

import org.apache.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.util.StopWatch;

@Aspect
public class SpringAOPLogger {

	@Around("execution(* com.sample.dao.*.*(..)) || execution(* com.sample.service.*.*(..))"
			+ "|| execution(* com.sample.controller.*.*(..))")
	public Object logMethod(ProceedingJoinPoint joinPoint) throws Throwable {
		String className = joinPoint.getTarget().getClass().getSimpleName();
		final Logger logger = Logger.getLogger(joinPoint.getTarget().getClass());
		Object retVal = null;

		try {
			StringBuffer startMessageStringBuffer = new StringBuffer();

			startMessageStringBuffer.append("Entered in ").append(className).append(".");
			startMessageStringBuffer.append(joinPoint.getSignature().getName());
			startMessageStringBuffer.append("(");

			Object[] args = joinPoint.getArgs();
			for (Object arg : args) {
				startMessageStringBuffer.append(arg).append(", ");
			}
			if (args.length > 0) {
				startMessageStringBuffer.deleteCharAt(startMessageStringBuffer.length() - 1);
			}

			startMessageStringBuffer.append(")");
			
			//Method Entry Log
			logger.debug(startMessageStringBuffer.toString());

			StopWatch stopWatch = new StopWatch();
			stopWatch.start();

			retVal = joinPoint.proceed();

			stopWatch.stop();

			StringBuffer endMessageStringBuffer = new StringBuffer();
			endMessageStringBuffer.append("Exit from ").append(className).append(".");
			endMessageStringBuffer.append(joinPoint.getSignature().getName());
			endMessageStringBuffer.append("(..); Execution time: ");
			endMessageStringBuffer.append(stopWatch.getTotalTimeMillis());
			endMessageStringBuffer.append(" ms;");
			
			//Method Exit Log
			logger.debug(endMessageStringBuffer.toString());
		} catch (Throwable ex) {
			StringBuffer errorMessageStringBuffer = new StringBuffer();
			// Create error message
			logger.error(errorMessageStringBuffer.toString(), ex);
			throw ex;
		}
		return retVal;
	}
}
