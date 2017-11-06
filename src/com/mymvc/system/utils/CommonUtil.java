package com.mymvc.system.utils;

import com.mymvc.repository.hibernate.pojo.User;
import com.mymvc.system.core.Application;
import com.mymvc.system.listener.RequestListener;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.text.DecimalFormat;
import java.util.Base64;

/**
 * Created by alan.luo on 2017/8/10.
 */
public class CommonUtil {

    public static String getOrderSn(){
        String str = "2000";
        str += DateUtil.getFullDateQ(null) + NumberUtil.getRandom(1111,9999);
        str += DateUtil.getTime() + "" + NumberUtil.getRandom(111,999);
        return str;
    }

    /**
     * 取支付编码
     * @return
     */
    public static String getPaySn(){
        String str = "1000";
        str += DateUtil.getFullDateQ(null)+ NumberUtil.getRandom(1111,9999);
        str += String.valueOf(DateUtil.getTime()).substring(7) + "" + NumberUtil.getRandom(111,999);
        return str;
    }

    /**
     * 解析邀请人的id，如果为空则返回0
     * @param base64Str
     * @return
     */
    public static int unParseUserParent(String base64Str){
        if (base64Str == null || StringUtils.isEmpty(base64Str)){
            return 0;
        }
        String unStr = new String(Base64.getDecoder().decode(base64Str.getBytes()));
        try {
            return Integer.parseInt(unStr);
        }catch (NumberFormatException e){
            e.printStackTrace();
        }

        return 0;
    }

    /**
     * 将邀请人的id加密成字符串
     * @param user_id
     * @return
     */
    public static String parseUserParent(int user_id){

        if (user_id <= 0){
            return "";
        }

        return new String(Base64.getEncoder().encode(String.valueOf(user_id).getBytes()));
    }


    /**
     * 格式化金钱，返回00.00格式
     * @param money
     * @return
     */
    public static String moneyFormat(Double money){
        if (money == null){
            return "0.00";
        }
        DecimalFormat df = new DecimalFormat("#0.00");
        return df.format(money);
    }

    /**
     * 微信支付使用，将上传过来的金额格式化成0.00格式，然后再乘以100返回以分为单位的的金额
     * @param money
     * @return
     */
    public static int moneyFormatToInt(Double money){
        DecimalFormat df = new DecimalFormat("##.00");
        return (int)(Double.parseDouble(df.format(money))*100);
    }

    /**
     * 格式化数字
     * @param number
     * @param pattern 比如:00.0
     * @return
     */
    public static String formatNumber(Float number,String pattern){
        if (pattern == null){
            pattern = "#0.00";
        }
        DecimalFormat df = new DecimalFormat(pattern);
        return df.format(number);
    }

    /**
     * 用户手机号码隐藏
     * @param mobile
     * @return
     */
    public static String hideMobile(String mobile) {
        return mobile.substring(0,4) + "****" + mobile.substring(9);
    }

    /**
     * 解析double
     * @param a
     * @return
     */
    public static Double parseDouble(String a) {
        if (StringUtils.isEmpty(a)){
            return 0.0;
        }

        return Double.parseDouble(a);
    }

    public static String getClientIp(HttpServletRequest request){

        if (request == null){
            return RequestListener.getInstance().getClientIp();
        }

        String ip=request.getHeader("x-forwarded-for");
        if(ip==null || ip.length()==0 || "unknown".equalsIgnoreCase(ip)){
            ip=request.getHeader("Proxy-Client-IP");
        }

        if(ip==null || ip.length()==0 || "unknown".equalsIgnoreCase(ip)){
            ip=request.getHeader("WL-Proxy-Client-IP");
        }

        if(ip==null || ip.length()==0 || "unknown".equalsIgnoreCase(ip)){
            ip=request.getHeader("X-Real-IP");
        }

        if(ip==null || ip.length()==0 || "unknown".equalsIgnoreCase(ip)){
            ip=request.getRemoteAddr();
        }

        return ip;
    }

    /**
     * 隐藏用户的重要信息
     * @param author
     * @return
     */
    public static User parserUserInfoToHide(User author){
        if (author != null){
            author.setLoginPassword(null);
            author.setMobile(null);
            author.setEmail(null);
            author.setUsername(null);
            author.setCreateTimeFormat(DateUtil.getFullDateTime(author.getCreateTime() * 1000L));
        }
        return author;
    }
}
