package com.springboot.jwt.common.intercepter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.springboot.jwt.common.JWTUtil;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

@Component
public class TokenInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
      if(request.getMethod().equals("OPTIONS")){
          response.setStatus(HttpServletResponse.SC_OK);
          return true;
      }

    String token = request.getHeader("token");
    if(token !=null){
        boolean result = JWTUtil.verif(token);
        if(result){
            return true;
        }
    }
        response.setCharacterEncoding("utf-8");
    response.setContentType("application/json;charset=utf-8");
        PrintWriter out = null;
        try{
            Map map = new HashMap<String,Object>();
            map.put("success", false);
            map.put("msg", "认证失败，未通过拦截器");
            response.getWriter().write(new ObjectMapper().writeValueAsString(map));
        }catch (Exception e){
            e.printStackTrace();
            response.sendError(500);
            return false;
        }
        return false;
    }


}
