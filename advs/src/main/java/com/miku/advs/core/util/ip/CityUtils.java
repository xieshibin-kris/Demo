package com.miku.advs.core.util.ip;

import net.ipip.ipdb.City;

/**
 * Created by hp on 2019/5/7.
 * <p>
 * * 下载网址：http://www.ipip.net
 */
public class CityUtils {

    //国内到市，国外到国家
    public static String getCityAddress(String ip) {

        try {
            String realPath = "E:\\project\\IdeaProjects\\advs\\src\\main\\webapp\\";
            //"E:\\project\\IdeaProjects\\advs\\src\\main\\webapp\\"
            City db = new City(realPath + "ipfile/ipipfree.ipdb");

            String arr[] = db.find(ip, "CN");
            String address = arr[0];

            if ("中国".equals(arr[0])) {
                address = arr[0] + arr[1];
            }
            return address;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }


}
