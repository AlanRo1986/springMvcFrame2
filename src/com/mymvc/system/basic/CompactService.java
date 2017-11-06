package com.mymvc.system.basic;


import org.springframework.stereotype.Service;

import java.util.logging.Logger;

/**
 * Created by alan.luo on 2017/9/18.
 */
@Service
public abstract class CompactService {

    private Logger logger;

    public CompactService(Class classz){
        logger = Logger.getLogger(classz.getName());
    }

    public void logger(Object o){
        logger.info(o.toString());
    }

    public void println(Object o){
        if (o == null){
            System.out.println("CompactService>>null");
        }else{
            System.out.println("CompactService>>"+o.toString());
        }
    }

}
