package cn.mikulink.rabbitbot.command.everywhere;

import cn.mikulink.rabbitbot.constant.ConstantDailyTask;
import cn.mikulink.rabbitbot.constant.ConstantSwitch;
import cn.mikulink.rabbitbot.entity.CommandProperties;
import cn.mikulink.rabbitbot.service.DailyTaskService;
import cn.mikulink.rabbitbot.service.RabbitBotService;
import cn.mikulink.rabbitbot.sys.annotate.Command;
import cn.mikulink.rabbitbot.utils.RandomUtil;
import net.mamoe.mirai.contact.Contact;
import net.mamoe.mirai.contact.User;
import net.mamoe.mirai.message.data.Message;
import net.mamoe.mirai.message.data.MessageChain;
import net.mamoe.mirai.message.data.PlainText;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;

@Command
public class ReloadDailyTaskCommand extends BaseEveryWhereCommand{

    @Autowired
    private RabbitBotService rabbitBotService;

    @Override
    public CommandProperties properties() {
        return new CommandProperties("ReloadDailyTask", "rdt", "重置任务");
    }

    @Override
    public Message execute(User sender, ArrayList<String> args, MessageChain messageChain, Contact subject) {
        Long qId = sender.getId();
        //查看配置是否已被最高权限强制统一接管
        if (!rabbitBotService.isMaster(qId)) {
            return new PlainText(RandomUtil.rollStrFromList(ConstantSwitch.SWITCH_FARCE));
        }

        ConstantDailyTask.file = null;

        DailyTaskService.getTaskFile();

        return new PlainText("Success.");
    }


}
