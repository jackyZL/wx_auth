package com.wx.auth.util;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

/**
 * Created by jacky on 2017/4/10.
 */
public class AuthUtil {

    public static final String APPID = "wxa2b392cd2bae6d9d";
    public static final String APPSECRECT = "9db62f974503c1b1ae3a44e941301fbe";

    public static JSONObject doGetJson(String url) throws IOException {

        JSONObject jsonObject = null;

        DefaultHttpClient client = new DefaultHttpClient();

        HttpGet httpGet = new HttpGet(url);

        CloseableHttpResponse httpResponse = client.execute(httpGet);

        HttpEntity entity = httpResponse.getEntity();
        if(entity!=null){
            String result = EntityUtils.toString(entity,"UTF-8");
            jsonObject = JSON.parseObject(result);
        }
        // 释放掉连接
        httpGet.releaseConnection();
        return jsonObject;
    }

}
