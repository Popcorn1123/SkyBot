package cn.mikulink.rabbitbot.command.everywhere;

import cn.mikulink.rabbitbot.constant.ConstantDailyTask;
import cn.mikulink.rabbitbot.entity.CommandProperties;
import cn.mikulink.rabbitbot.service.DailyTaskService;
import cn.mikulink.rabbitbot.sys.annotate.Command;
import net.mamoe.mirai.contact.Contact;
import net.mamoe.mirai.contact.User;
import net.mamoe.mirai.message.data.*;
import net.mamoe.mirai.utils.ExternalResource;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.util.ArrayList;

/**
 * @author Laity
 * @date 2022/8/19 12:50
 * 每日任务
 */
@Command
public class DailyTaskCommand extends BaseEveryWhereCommand{
    @Override
    public CommandProperties properties() {
        return new CommandProperties("DailyTask", "dt", "每日任务", "今日任务", "任务");
    }

    @Override
    public Message execute(User sender, ArrayList<String> args, MessageChain messageChain, Contact subject) {
        long first = System.currentTimeMillis();
        File file = DailyTaskService.getTaskFile();
        Image image = Contact.uploadImage(subject, file);
        MessageChainBuilder mcb = new MessageChainBuilder();
        mcb.append("今天的任务如下图所示:");
        mcb.append(image);
        String ms = String.valueOf(System.currentTimeMillis() - first);
        mcb.append("用时").append(ms).append("ms");
        return mcb.build();
    }
}
