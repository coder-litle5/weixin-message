package ydzhao.weixin.tuisong.job;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import ydzhao.weixin.tuisong.util.ParamUtil;
import ydzhao.weixin.tuisong.util.Pusher;

/**
 *@ClassName JobWorker
 *@Description TODO
 *@Author wsl
 *@Date 2022/8/2 16:00
 */
@Component
public class JobWorker {
    //要推送的用户openid
    private static String openIdw = ParamUtil.wsl;
    private static String openId=ParamUtil.rl;

    //测试用，1分钟一次
//    @Scheduled(cron = "00 0/1 * * * ?")
    @Scheduled(cron = "0 30 7 * * ?")
    public void goodMorning2(){
        Pusher.push(openIdw);
    }

    //早上7点半发送
    @Scheduled(cron = "0 30 7 * * ?")
//    @Scheduled(cron = "00 0/1 * * * ?")
    public void goodMorning(){
        Pusher.push(openId);
    }


}
