package com.zc.utils;

import com.alibaba.fastjson.JSON;
import com.dingtalk.api.request.OapiMessageCorpconversationAsyncsendV2Request;
import okhttp3.*;


import java.io.IOException;
import java.net.URLEncoder;
import java.util.concurrent.TimeUnit;

public class App
{
    public static final MediaType jsonType = MediaType.parse("application/json; charset=utf-8");
    public static void main( String[] args ) throws IOException {
//        PdfUtil.pdf2multiImage("E:\\Flink.pdf","E:\\Flink.jpg");
//        changeString();
//        for(int i=0;i<1000;i++){
//            getRequest();
//        }
//        System.out.println(Double.toHexString(13.00));
//        System.out.println(Double.toHexString(13.0));
//        getRequest();
        postRequest();
    }
    public static void postRequest() throws IOException{
        String appletId = "gh_ee7bef18be96";
        String appletPath = "homeTripPacakages/home/pages/sunriseSunset/sunriseSunset?userId=76239134";
        String reqUrl = "https://icome-contact.ennew.com/send/sendMessage";
        String messageUrl = "https://air.ennew.com/icome-app?appletId="+appletId+"&path="+appletPath;
        String messageUrlEncode = URLEncoder.encode(messageUrl,"UTF-8");
        String picUrl = "https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fimg.jj20.com%2Fup%2Fallimg%2Ftp09%2F210F2130512J47-0-lp.jpg&refer=http%3A%2F%2Fimg.jj20.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=auto?sec=1660384378&t=3d4b342cf97d61a59b19ac5c2d9385b6";
        SendMessageDto dto = new SendMessageDto();
        OapiMessageCorpconversationAsyncsendV2Request.Msg msg = new OapiMessageCorpconversationAsyncsendV2Request.Msg();
        msg.setMsgtype("link");
        msg.setLink(new OapiMessageCorpconversationAsyncsendV2Request.Link());
        msg.getLink().setTitle("测试跳转小程序");
        msg.getLink().setText("测试跳转小程序带参数");
        msg.getLink().setMessageUrl(messageUrlEncode);
        msg.getLink().setPicUrl(picUrl);
        String postMsg = JSON.toJSONString(msg);
        //组装参数
        dto.setMsg(postMsg);
        dto.setUserid_list("10068719");
//        dto.setUserid_list("10101852");
//        dto.setUserid_list("10111203");
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(8000, TimeUnit.MILLISECONDS)
                .readTimeout(8000, TimeUnit.MILLISECONDS)
                .build();
        String paramStr = JSON.toJSONString(dto);
        System.out.println(paramStr);
        RequestBody requestBody = RequestBody.create(jsonType, paramStr);
        Request.Builder builder = new Request.Builder().url(reqUrl).post(requestBody);
        Response response = okHttpClient.newCall(builder.build()).execute();
        String body = response.body().string();
        System.out.println(body);
    }

    /**
     * 发送GET请求
     */
    public static void getRequest() throws IOException {
        OkHttpClient client = new OkHttpClient();

//        Request request = new Request.Builder()
//                .url("http://10.39.49.31:9090/serviceManagerV2/allData")
//                .build();
        Headers.Builder builder_header = new Headers.Builder();
//        builder_header.add("userId","zhouchaoe");
        Headers headers = builder_header.build();
        Request.Builder reqBuild = new Request.Builder();
        HttpUrl.Builder urlBuilder =HttpUrl.parse("http://ennova-dev.opaas.enncloud.cn/cms-manage/InfoRecommendConfig/queryById")
                .newBuilder();
//        urlBuilder.addQueryParameter("pageNum", "1");
//        urlBuilder.addQueryParameter("pageSize", "5");

        reqBuild.headers(headers);
        reqBuild.url(urlBuilder.build());
        Request request = reqBuild.build();
        Response response = client.newCall(request).execute();
//        if (response.isSuccessful()) {
//            System.out.println(response.body().string());
//        }
        System.out.println(response.body().string());
//        Gson gson = new Gson();
//        MyResponse myResponse = gson.fromJson(response.body().string(), MyResponse.class);
//        System.out.println(myResponse.getCode());

    }

    public static void changeString(){
        String before = "1234\n\n\n\n\n567\n\n89\n101112\n\n\n";
        String last = deleteDuoble(before,"\n");
        System.out.println(last);
    }

    public static String deleteDuoble(String before,String doubleDelete){
        String[] arr = before.split(doubleDelete);
        String last = "";
        for(int i =0;i<arr.length;i++){
            if(!arr[i].equals("")){
                last = last+ arr[i]+doubleDelete;
            }
        }
        return last;
    }
}
