package cn.mikulink.rabbitbot.service.almanac;

import java.util.Objects;

/**
 * @program: sky-almanac
 * @description: 测试类
 * @author: Cosmax C
 * @create: 2021-06-18 08:14
 **/
public class HelloPicDemoApplication {
    public static void main(String[] args) {

        String username = "guiwow";

        // 获取今日实体
        TodayInfo demoName = TodayInfoUtils.getTodayInfoByUserName(username);

        // 保存路径
//        String path = Objects.requireNonNull(HelloPicDemoApplication.class.getClassLoader().getResource("")).getPath();
        String path = "./almanac.jpg";

        PicUtils.getHuangLi(username, demoName, path);
    }
}
