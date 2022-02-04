package com.chat.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;

@Aspect
@Component
public class ConnexionLogger {

    private String connectedPerson;

    private static final SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
    private static String LOG_PATH = "C:/Users/GUIDS/Documents/chat/src/main/resources/logs/log"
                    + format.format(new Date()) + ".txt";

    @Pointcut("within(com.chat.services.ConnexionServiceImpl)")
    public void connexionPointcut() {
    }

    @After("connexionPointcut()")
    public void logAfter(JoinPoint joinPoint) throws Throwable {
        try {
            File logFile = new File(LOG_PATH);
            logFile.createNewFile();
            FileWriter fileWriter = new FileWriter(logFile, true);
            BufferedWriter out = new BufferedWriter(fileWriter);
            Object[] args = joinPoint.getArgs();
            if (args.length > 0) {
                connectedPerson = String.valueOf(joinPoint.getArgs()[0]);;
                out.write("L'utilisateur " + connectedPerson + " vient de se connecter\n");
                out.close();
            } else {
                out.write("L'utilisateur " + connectedPerson + " vient de se deconnecter\n");
                out.close();
            }
        } catch (SecurityException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}