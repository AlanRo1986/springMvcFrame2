package com.mymvc.system.utils;

import java.util.Random;

/**
 * Created by alan.luo on 2017/8/10.
 */
public class  NumberUtil {

    /**
     * 取随机数
     * @param min 最小范围
     * @param max 最大范围
     * @return
     */
    public static int getRandom(int min,int max){

        Random random = new Random();

        int s = random.nextInt(max)%(max-min+1) + min;

        return s;
    }
}
