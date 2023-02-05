package cn.mikulink.rabbitbot.test;

import com.pdfcrowd.*;
import java.io.*;

public class ApiTest {
    public static void main(String[] args) throws IOException, Pdfcrowd.Error {
        try {
            // create the API client instance
            Pdfcrowd.HtmlToImageClient client =
                    new Pdfcrowd.HtmlToImageClient("demo", "ce544b6ea52a5621fb9d55f8b542d14d");

            // configure the conversion
            client.setOutputFormat("jpg");
            client.setCustomJavascript("libPdfcrowd.removeZIndexHigherThan({zlimit: 90});");

            // run the conversion and write the result to a file
            client.convertUrlToFile("https://www.bilibili.com/read/cv18173651", "result.jpg");
        }
        catch(Pdfcrowd.Error why) {
            // report the error
            System.err.println("Pdfcrowd Error: " + why);

            // rethrow or handle the exception
            throw why;
        }
        catch(IOException why) {
            // report the error
            System.err.println("IO Error: " + why);

            // rethrow or handle the exception
            throw why;
        }
    }
}