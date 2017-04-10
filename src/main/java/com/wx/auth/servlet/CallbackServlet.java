package com.wx.auth.servlet;

import com.alibaba.fastjson.JSONObject;
import com.wx.auth.util.AuthUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 这是微信的回调地址
 *
 * Created by jacky on 2017/4/10.
 */
@WebServlet("/callBack")
public class CallbackServlet extends HttpServlet{

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //获取code
        String code = req.getParameter("code");

        //获取code后，请求以下链接获取access_token：
        //这里通过code换取的是一个特殊的网页授权access_token,与基础支持中的access_token（该access_token用于调用其他接口）不同
        String url = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=" + AuthUtil.APPID
                + "&secret=" + AuthUtil.APPSECRECT
                + "&code=" + code
                + "&grant_type=authorization_code";

        JSONObject jsonObject = AuthUtil.doGetJson(url);

        String openid = jsonObject.getString("openid");
        String token = jsonObject.getString("access_token");

        String infoUrl = "https://api.weixin.qq.com/sns/userinfo?access_token=" + token +
                "&openid=" + openid +
                "&lang=zh_CN ";

        JSONObject userInfo = AuthUtil.doGetJson(infoUrl);
        System.out.println(userInfo);

    }
}
