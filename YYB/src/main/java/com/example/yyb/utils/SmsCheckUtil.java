package com.example.yyb.utils;

import com.github.qcloudsms.SmsSingleSender;
import com.github.qcloudsms.SmsSingleSenderResult;
import com.github.qcloudsms.httpclient.HTTPException;
import org.json.JSONException;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

public class SmsCheckUtil {
    public static void sendMessage(String phone,String[] params){
        int appid = 1400186614;

        String appkey = "2677003b608144555b10a1cb5525c172";

        int templateId = 282818;

        String smsSign = "伍延璋";

        try {
            SmsSingleSender ssender = new SmsSingleSender(appid, appkey);
            SmsSingleSenderResult result = ssender.sendWithParam("86", phone,templateId, params, smsSign, "", "");
            //System.out.println(result);
        } catch (HTTPException e) {
            // HTTP响应码错误
            e.printStackTrace();
        } catch (JSONException e) {
            // json解析错误
            e.printStackTrace();
        } catch (IOException e) {
            // 网络IO错误
            e.printStackTrace();
        }
    }
    public static String getCheckMS(){
        Integer rand = (int)((Math.random()*9+1)*10000);
        return rand.toString();
    }
    public static void removeAttrbute(final HttpSession session, final String attrName,Integer minute) {
        final Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                // 删除session中存的验证码
                session.removeAttribute(attrName);
                timer.cancel();
            }
        }, minute * 60 * 1000);
    }

    public static void main(String[] args) {
        String checkMS = SmsCheckUtil.getCheckMS();
        SmsCheckUtil.sendMessage("15077545371", new String[]{checkMS,"2"});
    }

}
