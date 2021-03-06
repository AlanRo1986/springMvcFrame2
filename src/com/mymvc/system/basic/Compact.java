package com.mymvc.system.basic;


import java.util.logging.Logger;

/**
 * Created by alan.luo on 2017/9/18.
 */

public abstract class Compact {

    private Logger logger;

    public Compact(Class classz){
        logger = Logger.getLogger(classz.getName());
    }

    public void logger(Object o){
        logger.info(o.toString());
    }

    public void println(Object o){
        if (o == null){
            System.out.println("Compact>>null");
        }else{
            System.out.println("Compact>>"+o.toString());
        }
    }

}
