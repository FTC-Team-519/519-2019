package org.firstinspires.ftc.teamcode.teleop;

import android.content.Intent;

import org.firstinspires.ftc.teamcode.BaseOpMode;

import java.io.File;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.Map;
import java.util.StringJoiner;

public class PersistenceTest extends BaseOpMode {

    public void loop() {
       /*Intent intent = new Intent(Intent.ACTION_CREATE_DOCUMENT);
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_TITLE, "testingCopy.txt");

        hardwareMap.appContext.startActivity(intent);*/

       try {
           URL url = new URL("");
           URLConnection con = url.openConnection();
           HttpURLConnection http = (HttpURLConnection) con;
           http.setRequestMethod("POST");
           http.setDoOutput(true);

           Map<String, String> params = new HashMap<>();
           params.put("api_dev_key", "THE KEY NEEDS TO GO HERE");
           params.put("api_paste_code", "this is where we would put da info"); //todo encodeeeeeeeeeeeeeeeee
         //  params.put("api_paste_name", "TESTDUMP");// todo encode
           params.put("api_option", "paste");

           StringJoiner sj = new StringJoiner("&");
           //https://stackoverflow.com/questions/3324717/sending-http-post-request-in-java
                  }
        catch (Exception e) {
            this.log("failed", e.getStackTrace().toString());
       }
    }

    /*
    public void writeFileOnInternalStorage(String sFileName, String sBody){
        File file = new File(hardwareMap.appContext.getFilesDir(),"mydir");
        if(!file.exists()){
            file.mkdir();
        }

        try{
            File gpxfile = new File(file, sFileName);
            FileWriter writer = new FileWriter(gpxfile);
            writer.append(sBody);
            writer.flush();
            writer.close();

        }catch (Exception e){
            e.printStackTrace();

        }
    }*/

    public void start() {super.start();}
    public void init() {}
    public void init_loop() {}
}
