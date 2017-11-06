package com.mymvc.system.provider;

import com.mymvc.constant.Constant;
import com.mymvc.system.basic.BasicProvider;
import com.mymvc.system.annotation.Provider;
import com.mymvc.system.utils.Md5Util;
import com.mymvc.system.utils.NumberUtil;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.OutputStream;
import java.util.Random;

/**
 * Created by alan.luo on 2017/8/4.
 */
@Provider
public class VerifyProvider extends BasicProvider {

    /**
     * for session name.
     */
    private final static String VERIFY_CODE_NAME = "VERIFY_CODE_NAME";

    /**
     * 验证码的数量
     */
    private final static int VERIFY_CODE_NUMBER = 5;

    public VerifyProvider() {
        super();
    }

    /**
     *
     * @param response
     * @param session
     * @param width
     * @param height
     * @throws Exception
     */
    public void make(HttpServletResponse response,HttpSession session, int width, int height) throws Exception{

        //创建空白图片 100*30
        BufferedImage image = new BufferedImage(width,height,BufferedImage.TYPE_INT_RGB);

        //获取图片画笔
        Graphics g = image.getGraphics();


        //设置画笔颜色
        g.setColor(new Color(255,200, NumberUtil.getRandom(20,255)));


        //绘制矩形的背景
        g.fillRect(0, 0, width, height);

        //调用自定义的方法，获取长度为5的字母数字组合的字符串
        String number = getNumber(VERIFY_CODE_NUMBER);

        //将图片字符存入session,用于验证码检查使用
        session.setAttribute(VERIFY_CODE_NAME, Md5Util.md5(number.toLowerCase() + Constant.hashKey));

        //绘制干扰线
        //g.setColor(new Color(0,a,r.nextInt(255)));
        Graphics2D g2 = (Graphics2D) g;
        g2.setStroke(new BasicStroke(8.0f));
        for (int i=0;i<2;i++){
            g.setColor(new Color(0,152,NumberUtil.getRandom(1,255),NumberUtil.getRandom(1,255)));
            int x = 0;
            int y = NumberUtil.getRandom(0,height);
            int x2 = NumberUtil.getRandom(0,width);
            int y2 = NumberUtil.getRandom(0,height);

            while (x < width){
                g.drawLine(x, y,x2, y2);
                x=x2;y=y2;
                x2 = NumberUtil.getRandom(x,width+100);
                y2 = NumberUtil.getRandom(0,height);
            }
        }

        //绘制干扰点
        g2.setStroke(new BasicStroke(3.0f));
        for (int i=0;i<80;i++){
            g.setColor(new Color(NumberUtil.getRandom(1,255),NumberUtil.getRandom(1,255),NumberUtil.getRandom(1,255),NumberUtil.getRandom(1,255)));

            int x = NumberUtil.getRandom(0,width);
            int y = NumberUtil.getRandom(0,height);
            g.drawLine(x,y,x+4,y+4);
        }

        //5.设置颜色字体后，绘制字符串
        g.setColor(new Color(0, NumberUtil.getRandom(1,150),NumberUtil.getRandom(1,150)));
        g.setFont(new Font(null,Font.BOLD,width/4));
        g.drawString(number, g.getFont().getSize()/3, height - 5);

        response.setContentType("image/jpeg");
        OutputStream ops = response.getOutputStream();
        ImageIO.write(image, "jpeg", ops);
        ops.close();
    }

    /**
     * check the verify code.
     * @param code
     * @param session
     * @return
     */
    public boolean check(String code,HttpSession session){

        String raw = String.valueOf(session.getAttribute(VERIFY_CODE_NAME));
        if (raw != null && raw.equals(Md5Util.md5(code.toLowerCase() + Constant.hashKey)) == true){
            session.removeAttribute(VERIFY_CODE_NAME);
            return true;
        }
        return false;
    }

    /**
     * get verify number
     * @param size
     * @return
     */
    private String getNumber(int size){
        String str = "qwertyupkjhgfdsazxcvbnmABCDEFGHJKLMNPQRSTUVWXYZ1234567890";
        String number = "";
        Random r = new Random();
        for(int i = 0;i < size; i++){
            number += str.charAt(r.nextInt(str.length()));
        }
        return number;
    }
}
