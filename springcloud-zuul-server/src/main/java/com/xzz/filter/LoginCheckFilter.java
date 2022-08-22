package com.xzz.filter;

import com.alibaba.fastjson.JSON;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Component
public class LoginCheckFilter extends ZuulFilter {

    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        return 0;
    }

    //返回结果决定是否要执行run方法，false就不使用run方法去校验，就放行，true就是去校验
    @Override
    public boolean shouldFilter() {
        HttpServletRequest request = RequestContext.getCurrentContext().getRequest();
        String url = request.getRequestURI();
        //2.判断是否包含在： static/**  ，/login , /register
        if(url.endsWith("/login ") || url.endsWith("/register ") || url.contains("/static/") ){
            return false;
        }
        //要做登录检查的返回true
        return true;
    }

    //核心校验方法 ： 登录检查 ， 如果请求头中有token，就是登录成功
    @Override
    public Object run() throws ZuulException {
        //1.获取请求对象
        HttpServletRequest request = RequestContext.getCurrentContext().getRequest();

        //响应对象
        HttpServletResponse response = RequestContext.getCurrentContext().getResponse();

        //2.获取请求头中的 token
        String token = request.getHeader("token");

        //3.如果没有token，登录检查失败 ，
        if(StringUtils.isEmpty(token)){
            try {
            //3.1.返回登录检查失败的错误信息 :{ success:false, message:"登录检查失败，请重新登录"}
            Map<String,Object> resultMap = new HashMap<>();
            resultMap.put("success" , false);
            resultMap.put("message" , "登录检查失败，请重新登录");
            //中文编码
            response.setContentType("application/json;charset=utf-8");
            //把map转成json字符串，写到浏览器
            String resultJsonString = JSON.toJSONString(resultMap);
            response.getWriter().write(resultJsonString);

            //3.2.阻止filter继续往后执行
            RequestContext.getCurrentContext().setSendZuulResponse(false);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
