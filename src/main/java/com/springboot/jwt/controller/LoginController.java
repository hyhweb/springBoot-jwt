package com.springboot.jwt.controller;

import com.springboot.jwt.common.JWTUtil;
import com.springboot.jwt.common.ResponseBean;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {
    @PostMapping("/login")
    public ResponseBean login(String username,String password){
        ResponseBean responseBean = new ResponseBean();
    responseBean.setCode(1);
    responseBean.setMsg("login");
    responseBean.setData(JWTUtil.sign(username,password));
    return responseBean;
    }
}
