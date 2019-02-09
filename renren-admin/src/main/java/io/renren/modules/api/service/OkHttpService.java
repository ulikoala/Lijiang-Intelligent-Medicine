package io.renren.modules.api.service;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.*;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Map;

@Service
public class OkHttpService {
    public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
    public static final MediaType XML = MediaType.parse("application/xml; charset=utf-8");
    OkHttpClient client = new OkHttpClient();

    //get 无参或URL带参
    public String get(String url) {
        Request request = new Request.Builder()
                .url(url)
                .build();

        try {
            Response response = client.newCall(request).execute();
            return response.body().string();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "error";
    }

    //URL  map是get后面所需要带的参数
    public String get(String url, Map<String, String> map) {
        StringBuffer sb = new StringBuffer(url);
        boolean firstFlag = true;
        for (Map.Entry entry : map.entrySet()) {
            if (firstFlag) {
                sb.append("?" + entry.getKey() + "=" + entry.getValue());
                firstFlag = false;
            } else {
                sb.append("&" + entry.getKey() + "=" + entry.getValue());
            }
        }
        Request request = new Request.Builder()
                .url(sb.toString())
                .build();

        try {
            Response response = client.newCall(request).execute();
            return response.body().string();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "error";
    }

    //post json传参
    public String post(String url, String json) {
        RequestBody body = RequestBody.create(JSON, json);
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();
        try {
            Response response = client.newCall(request).execute();
            return response.body().string();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "error";
    }

    //post map传参
    public String post(String url, Map<String, Object> map) {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_EMPTY);//属性为 空（“”） 或者为 NULL 都不序列化
        try {
            String json = objectMapper.writeValueAsString(map);
            RequestBody body = RequestBody.create(JSON, json);
            Request request = new Request.Builder()
                    .url(url)
                    .post(body)
                    .build();
            Response response = client.newCall(request).execute();
            return response.body().string();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "error";
    }

    //post XML传参
    public String postXML(String url, String xml) {
        RequestBody body = RequestBody.create(XML, xml);
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();
        try {
            Response response = client.newCall(request).execute();
            return response.body().string();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "error";
    }
}
