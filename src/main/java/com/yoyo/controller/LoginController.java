package com.yoyo.controller;

import com.yoyo.service.LoginService;
import org.apache.ibatis.javassist.bytecode.stackmap.MapMaker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class LoginController {
    @Autowired
    LoginService loginService;

    @RequestMapping("/doLogin")
    @CrossOrigin
    public Map doLogin(String userName,String password) throws Exception {
        String restMsg=loginService.doLogin(userName,password);
        Map resultMap=new HashMap();
        resultMap.put("status","Success");
        resultMap.put("msg",restMsg);
        return resultMap;
    }
}
