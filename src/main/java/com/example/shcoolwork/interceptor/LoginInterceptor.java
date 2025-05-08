package com.example.shcoolwork.interceptor;

import com.alibaba.fastjson.JSONObject;
import com.example.shcoolwork.Entity.Result;
import com.example.shcoolwork.utils.JwtUtils;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;

@Slf4j
@Component
public class LoginInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        String jwt = request.getHeader("token");
        if(!StringUtils.hasLength(jwt)){
            log.info("当前请求无令牌");
            Result<Object> error = Result.error("NOT_LOGIN");

            String notLogin = JSONObject.toJSONString(error);
            response.getWriter().write(notLogin);
            return false;
        }
        //检验令牌是否有效
        try{
            JwtUtils.parseJWT(jwt);
        }catch (Exception e){
            log.info("当前令牌不合法");
            Result<Object> error = Result.error("NOT_LOGIN");
            //将error转成JSON格式
            String notLogin = JSONObject.toJSONString(error);
            //将JSON化的对象写入响应数据中
            response.getWriter().write(notLogin);
            return false;
        }

        log.info("令牌校验通过，放行");
        return true;
    }
}
