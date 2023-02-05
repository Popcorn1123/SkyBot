package cn.mikulink.rabbitbot.test;

import cn.hutool.core.codec.Base64Decoder;
import cn.mikulink.rabbitbot.utils.DateUtil;

import java.awt.*;
import java.util.Arrays;
import java.util.Base64;

public class DateTest {
    public static void main(String[] args) {
        Font f = new Font("微软雅黑", Font.BOLD, 30);
        System.out.println(f.getName());
        GraphicsEnvironment g = GraphicsEnvironment.getLocalGraphicsEnvironment();
        String[] fonts = g.getAvailableFontFamilyNames();//获得系统字体名
        for (String font : fonts) {
            System.out.println(font);
        }
    }
}
