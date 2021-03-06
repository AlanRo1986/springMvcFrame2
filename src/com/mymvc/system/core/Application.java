package com.mymvc.system.core;

import com.mymvc.constant.Constant;
import com.mymvc.system.listener.RequestListener;
import com.mymvc.system.provider.CacheProvider;
import org.springframework.web.context.WebApplicationContext;

import java.io.*;
import java.util.Properties;
import java.util.logging.Logger;

/**
 * Created by alan.luo on 2017/9/19.
 */
public class Application {

    private Logger logger = Logger.getLogger(this.getClass().getName());
    private static Application $_app;
    private WebApplicationContext mContext;

    public Application(WebApplicationContext ctx) {
        this.mContext = ctx;
    }

    public static Application getInstance(WebApplicationContext ctx){
        if ($_app == null){
            $_app = new Application(ctx);
        }
        return $_app;
    }

    public void run(){
        System.out.println("---------------run---------------");
    }

    public void stop(){
        System.out.println("---------------stop---------------");
    }

    public Object getBean(String name){
        return mContext.getBean(name);
    }

    public Object getBean(Class classz){
        return mContext.getBean(classz);
    }

    public void catBeans(){
        String[] names = mContext.getBeanDefinitionNames();
        StringBuffer sb = new StringBuffer();
        for (String string:names){
            System.out.println("catBean:"+string);
        }
    }

    public WebApplicationContext getContext() {
        return mContext;
    }



    /**
     * get locale lang.
     * @param name
     * @param type lang|errors|null
     * @return
     */
    public String getLang(String name,String type){
        if (type == null){
            type = "lang";
        }
        Properties locales = getLangMap(type);
        if (locales != null && locales.containsKey(name.toLowerCase())){
            try {
                return new String(locales.getProperty(name.toLowerCase()).getBytes("ISO-8859-1"),"UTF-8");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        return name.toUpperCase();
    }

    /**
     * get lang objects.
     * @param type
     * @return
     */
    public Properties getLangMap(String type){

        //....&locale=en_US
        String locale = RequestListener.getInstance().getLocale();

        String cacheKey = "locale" + locale + type;

        CacheProvider cacheProvider = (CacheProvider) getBean(CacheProvider.class);
        /**
         * get for cache first,and if has not,then will be new a object.
         */
        Properties prop = (Properties) cacheProvider.get(cacheKey);
        if (prop != null){
            System.out.println("locales for cache.");
            return prop;
        }

        File file = null;
        InputStream in = null;
        String path = this.getClass().getResource("").getPath()+"../../";
        file = new File(path + "lang/" + locale + "/" + type + ".properties");
        if (!file.exists()){
            if (cacheProvider.has(cacheKey)){
                return (Properties) cacheProvider.get(cacheKey);
            }
            file = new File(path + "lang/" + Constant.defaultLocale + "/" + type + ".properties");
        }

        try {
            if (!file.exists()){
                throw new FileNotFoundException("cannot read this file:" + path + "lang/"+ Constant.defaultLocale+"/" + type + ".properties");
            }
            prop = new Properties();
            in = new BufferedInputStream(new FileInputStream(file));
            prop.load(in);

            /**
             * cache valid time in 10 days.
             */
            cacheProvider.put(cacheKey,prop,86400 * 10);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (in != null){
                    in.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return prop;
    }
}
