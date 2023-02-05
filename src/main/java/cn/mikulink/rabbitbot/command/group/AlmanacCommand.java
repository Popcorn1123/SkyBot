package cn.mikulink.rabbitbot.command.group;

import cn.mikulink.rabbitbot.command.GroupCommand;
import cn.mikulink.rabbitbot.command.everywhere.BaseEveryWhereCommand;
import cn.mikulink.rabbitbot.constant.ConstantDailyTask;
import cn.mikulink.rabbitbot.entity.CommandProperties;
import cn.mikulink.rabbitbot.service.DailyTaskService;
import cn.mikulink.rabbitbot.service.almanac.PicUtils;
import cn.mikulink.rabbitbot.service.almanac.TodayInfo;
import cn.mikulink.rabbitbot.service.almanac.TodayInfoUtils;
import cn.mikulink.rabbitbot.sys.annotate.Command;
import net.mamoe.mirai.contact.Contact;
import net.mamoe.mirai.contact.Group;
import net.mamoe.mirai.contact.Member;
import net.mamoe.mirai.contact.User;
import net.mamoe.mirai.message.data.*;

import java.io.File;
import java.util.ArrayList;

@Command
public class AlmanacCommand implements GroupCommand {
    @Override
    public CommandProperties properties() {
        return new CommandProperties("Almanac", "almanac", "黄历");
    }

    @Override
    public Message execute(Member sender, ArrayList<String> args, MessageChain messageChain, Group subject) {

        String nick = sender.getNameCard().isEmpty() ? sender.getNick() : sender.getNameCard();

        long first = System.currentTimeMillis();
        // 获取今日实体
        TodayInfo todayInfo = TodayInfoUtils.getTodayInfoByUserName(nick);
        File file = PicUtils.getHuangLi(nick, todayInfo, "./almanac.jpg");
        Image image = Contact.uploadImage(subject, file);
        MessageChainBuilder mcb = new MessageChainBuilder();
        mcb.append(image);
        String ms = String.valueOf(System.currentTimeMillis() - first);
        mcb.append("用时").append(ms).append("ms");
        return mcb.build();
    }

    @Override
    public Message permissionCheck(Member sender, ArrayList<String> args, MessageChain messageChain, Group subject) {
        return null;
    }

}
