package com.springboot.jwt.controller;

import com.springboot.jwt.common.JWTUtil;
import com.springboot.jwt.common.ResponseBean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {
    @GetMapping("/login")
    public ResponseBean login(String username, String password) {
        ResponseBean responseBean = new ResponseBean();
        responseBean.setCode(1);
        responseBean.setMsg("login");
        responseBean.setData(JWTUtil.sign(username));
        return responseBean;
    }
    @GetMapping(value="/getList")
    public String  getList(){
        return "通过token验证，获取数据";

    }
}
