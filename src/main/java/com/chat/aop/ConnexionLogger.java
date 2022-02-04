package com.chat.aop;

import com.chat.services.ConnexionServiceImpl;
import org.aopalliance.intercept.Joinpoint;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.context.annotation.Configuration;

@Configuration
@AutoConfigureAfter(value = { ConnexionServiceImpl.class } )
public class ConnexionLogger {

    /*private Logger log = Logger.getLogger(getClass());

    public void log(Joinpoint point) {
        log.info(point.getSignature().getName() + " called...");
    }*/
}
