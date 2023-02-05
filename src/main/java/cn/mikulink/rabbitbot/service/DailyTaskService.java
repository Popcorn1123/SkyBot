package cn.mikulink.rabbitbot.service;

import cn.mikulink.rabbitbot.constant.ConstantDailyTask;
import cn.mikulink.rabbitbot.constant.ConstantFreeTime;
import cn.mikulink.rabbitbot.utils.FileUtil;
import com.pdfcrowd.Pdfcrowd;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

@Service
public class DailyTaskService {
    @Value("${file.path.data:}")
    private String dataPath;
    public void clear() {
        ConstantDailyTask.file = null;
    }

    public static File getTaskFile() {
        if (ConstantDailyTask.file != null) return new File("result.jpg");
        else return getLatestTask();
    }

    public static File getLatestTask() {
        String url = "https://gitee.com/popcorn29/SkyDailyTask/blob/master/README.md#";

        try {
            // create the API client instance
            Pdfcrowd.HtmlToImageClient client =
                    new Pdfcrowd.HtmlToImageClient("demo", "ce544b6ea52a5621fb9d55f8b542d14d");

            // configure the conversion
            client.setOutputFormat("jpg");
            client.setCustomJavascript("libPdfcrowd.removeZIndexHigherThan({zlimit: 90});");

            // run the conversion and write the result to a file
            client.convertUrlToFile(url, "result.jpg");
        }
        catch(Pdfcrowd.Error why) {
            // report the error
            System.err.println("Pdfcrowd Error: " + why);

            // rethrow or handle the exception
            why.printStackTrace();
        }
        catch(IOException why) {
            // report the error
            System.err.println("IO Error: " + why);

            // rethrow or handle the exception
            why.printStackTrace();
        }
        ConstantDailyTask.file = new File("result.jpg");
        return ConstantDailyTask.file;
    }
}
