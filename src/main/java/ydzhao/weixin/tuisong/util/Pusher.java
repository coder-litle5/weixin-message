package ydzhao.weixin.tuisong.util;

import com.alibaba.fastjson.JSONObject;
import me.chanjar.weixin.mp.api.WxMpInMemoryConfigStorage;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.api.impl.WxMpServiceImpl;
import me.chanjar.weixin.mp.bean.template.WxMpTemplateData;
import me.chanjar.weixin.mp.bean.template.WxMpTemplateMessage;

/**
 *@ClassName Pusher
 *@Description TODO
 *@Author wsl
 *@Date 2022/8/2 16:03
 */
public class Pusher {
    /**
     * 测试号的appId和secret
     */
    private static String appId=ParamUtil.appId;
    private static String secret = ParamUtil.secret;
    //模版id
    private static String templateId =ParamUtil.templateId;

    public static void push(String openId){
        //1，配置
        WxMpInMemoryConfigStorage wxStorage = new WxMpInMemoryConfigStorage();
        wxStorage.setAppId(appId);
        wxStorage.setSecret(secret);
        WxMpService wxMpService = new WxMpServiceImpl();
        wxMpService.setWxMpConfigStorage(wxStorage);
        //2,推送消息
        WxMpTemplateMessage templateMessage = WxMpTemplateMessage.builder()
                .toUser(openId)
                .templateId(templateId)
                //.url("https://30paotui.com/")//点击模版消息要访问的网址
                .build();
        //3,如果是正式版发送模版消息，这里需要配置你的信息
        //        templateMessage.addData(new WxMpTemplateData("name", "value", "#FF00FF"));
        //                templateMessage.addData(new WxMpTemplateData(name2, value2, color2));
        //填写变量信息，比如天气之类的
        JSONObject todayWeather = Tianqi.getNanjiTianqi();
        templateMessage.addData(new WxMpTemplateData("riqi",todayWeather.getString("date") + "  "+ todayWeather.getString("week"),"#00BFFF"));
        templateMessage.addData(new WxMpTemplateData("tianqi",todayWeather.getString("text_day"),"#ff9999"));
        templateMessage.addData(new WxMpTemplateData("night",todayWeather.getString("text_night"),"#ff66cc"));
        templateMessage.addData(new WxMpTemplateData("low",todayWeather.getString("low") + "","#173177"));
        templateMessage.addData(new WxMpTemplateData("high",todayWeather.getString("high")+ "","#FF6347" ));
        templateMessage.addData(new WxMpTemplateData("caihongpi",CaiHongPi.getCaiHongPi(),"#FF69B4"));
        templateMessage.addData(new WxMpTemplateData("lianai",JiNianRi.getLianAi()+"","#FF1493"));
        templateMessage.addData(new WxMpTemplateData("shengri",JiNianRi.getShengRi()+"","#FFA500"));
        templateMessage.addData(new WxMpTemplateData("jinju",CaiHongPi.getJinJu()+"","#C71585"));
        //templateMessage.addData(new WxMpTemplateData("jiehun",JiNianRi.getJieHun()+""));
        templateMessage.addData(new WxMpTemplateData("xiangshi",JiNianRi.getXiangshi()+"","#FF6347"));
        String beizhu = "";
//        if(JiNianRi.getJieHun() % 365 == 0){
//            beizhu = "今天是结婚纪念日！";
//        }
        if(JiNianRi.getLianAi() % 365 == 0){
            beizhu = "今天是恋爱纪念日！";
        }
        if(JiNianRi.getShengRi() % 365 == 0){
            beizhu = "今天是你的生日哦！生日快乐，我最爱的你！";
        }
        if(JiNianRi.getXiangshi() % 365 == 0){
            beizhu = "今天是我们相识纪念日哦！";
        }
        templateMessage.addData(new WxMpTemplateData("beizhu",beizhu,"#FF0000"));


        try {
            System.out.println(templateMessage.toJson());
            System.out.println(wxMpService.getTemplateMsgService().sendTemplateMsg(templateMessage));
        } catch (Exception e) {
            System.out.println("推送失败：" + e.getMessage());
            e.printStackTrace();
        }
    }
}
