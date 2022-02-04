package com.chat.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Arrays;
import java.util.logging.FileHandler;
import java.util.logging.Logger;

@Aspect
@Component
public class ConnexionLogger {

    /*private final Logger logger = Logger.getLogger(ConnexionLogger.class.getName());
    private FileHandler fileHandler;

    private static final String LOG_PATH = "C:/Users/GUIDS/Documents/chat/src/main/resources/logs/log.txt";

    @Pointcut("within(com.chat.services.ConnexionServiceImpl)")
    public void connexionPointcut() {
    }


    @After("connexionPointcut()")
    public void logAround(ProceedingJoinPoint joinPoint) throws Throwable {
        try {

            fileHandler = new FileHandler(LOG_PATH);
            logger.addHandler(fileHandler);

            logger.info(joinPoint.getSignature().getDeclaringTypeName() +
                            "." + joinPoint.getSignature().getName() +
                            "() with argument[s] = " + Arrays.toString(joinPoint.getArgs()));

        } catch (SecurityException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }*/
}