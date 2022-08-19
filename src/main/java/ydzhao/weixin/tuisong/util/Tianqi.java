package ydzhao.weixin.tuisong.util;


import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

/**
 * @ClassName Tianqi
 * @Description TODO
 * @Author wsl
 * @Date 2022/8/2 16:45
 */
public class Tianqi {

    //百度开发者申请的ak
    private static String ak =ParamUtil.ak;

    //地区编码 https://lbsyun.baidu.com/index.php?title=webapi/weather
    private static String district_id =ParamUtil.district_id;

    public static JSONObject getNanjiTianqi() {
        String result = null;
        JSONObject today = new JSONObject();
        try {
            result = HttpUtil.getUrl("https://api.map.baidu.com/weather/v1/?district_id=" + district_id + "&data_type=all&ak=" + ak);
            JSONObject jsonObject = JSONObject.parseObject(result);
            if (jsonObject.getString("message").equals("success")) {
                JSONArray arr = jsonObject.getJSONObject("result").getJSONArray("forecasts");
                today = arr.getJSONObject(0);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return today;
    }

    public static void main(String[] args) {
        System.out.println(getNanjiTianqi());
    }
}
