package com.mymvc.system.basic;


import java.util.logging.Logger;

/**
 * Created by alan.luo on 2017/9/18.
 */

public abstract class CompactProvider {

    private Logger logger;

    public CompactProvider(Class classz){
        logger = Logger.getLogger(classz.getName());
    }

    public void logger(Object o){
        if (o == null){
            o = "null";
        }
        logger.info(o.toString());
    }

    public void println(Object o){
        if (o == null){
            System.out.println("CompactProvider>>null");
        }else{
            System.out.println("CompactProvider>>"+o.toString());
        }
    }

}
