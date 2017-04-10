package com.wx.auth.servlet;

import com.wx.auth.util.AuthUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;

/**
 * Created by jacky on 2017/4/10.
 */
@WebServlet("/wxLogin")
public class LoginServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String myPublicDomain = "https://47ed6a9a.ngrok.io";//这里填写自己的内网映射域名，可以使用花生壳或者使用ngrok，后者比较简单
        String backUrl = myPublicDomain + "/callBack";

        String url2 = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=" + AuthUtil.APPID +
                "&redirect_uri=" + URLEncoder.encode(backUrl)+
                "&response_type=code" +
                "&scope=snsapi_base" +
                "&state=STATE#wechat_redirect";

        String url = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=" + AuthUtil.APPID +
                "&redirect_uri=" + URLEncoder.encode(backUrl) +
                "&response_type=code" +
                "&scope=snsapi_userinfo" +
                "&state=STATE#wechat_redirect";

        System.out.println(url2);

        resp.sendRedirect(url2);
    }
}
