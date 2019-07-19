package cn.ntshare.Blog.util;

import cn.ntshare.Blog.bo.CaptchaBO;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

/**
 * Created By Seven.wk
 * Description: 验证码工具
 * Created At 2018/11/10
 */
public class CaptchaUtil {

    /**
     * 生成验证码
     * @param length     验证码长度
     * @param height    验证码宽度
     * @return
     */
    public static CaptchaBO createCaptcha(int length, int height) {
        BufferedImage captchaImage = new BufferedImage(length, height, BufferedImage.TYPE_INT_RGB);

        Graphics graphics = captchaImage.createGraphics();
        Random random = new Random();

        // 填充验证码背景色
        graphics.setColor(getRandColor(200, 250));
        graphics.fillRect(0, 0, length, height);

        // 设置字体形式
        graphics.setFont(new Font("Times New Roman", Font.ITALIC, 20));

        // 设置干扰条纹的颜色
        graphics.setColor(getRandColor(160, 200));
        for (int i=0; i<155; i++) {
            int x = random.nextInt(length);
            int y = random.nextInt(height);
            int x1 = random.nextInt(12);
            int y1 = random.nextInt(12);
            graphics.drawLine(x, y, x1, y1);
        }

        //生成验证码
        StringBuilder sRand = new StringBuilder();
        for (int i=0; i<4; i++) {
            String rand = String.valueOf(random.nextInt(10));
            sRand.append(rand);
            graphics.setColor(new Color(20 + random.nextInt(110), 20 + random.nextInt(110), 20 + random.nextInt(110)));
            graphics.drawString(rand, 13 * i + length/4, height/2 + 6);
        }
        graphics.dispose();

        return new CaptchaBO(captchaImage, sRand.toString());
    }

    /**
     * 生成颜色
     * @param fc    取色上限
     * @param bc    取色下限
     * @return  Color
     */
    private static Color getRandColor(int fc, int bc) {
        Random random = new Random();
        if (fc > 255) {
            fc = 255;
        }
        if (bc > 255) {
            bc = 255;
        }
        int r = fc + random.nextInt(bc - fc);
        int g = fc + random.nextInt(bc - fc);
        int b = fc + random.nextInt(bc - fc);
        return new Color(r, g, b);
    }
}
