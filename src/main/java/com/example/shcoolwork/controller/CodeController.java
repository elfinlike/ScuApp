package com.example.shcoolwork.controller;

import com.aliyuncs.exceptions.ClientException;
import com.example.shcoolwork.service.CodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;


@RestController
@RequestMapping("/Code")
public class CodeController {
    @Autowired
    private CodeService codeService;

    @GetMapping("/getCode")
    public String getCode(@RequestParam String phone) throws ClientException {
        String code = generateCode();
     // 将验证码和手机号存储到数据库或缓存中
        codeService.keep(phone,code);
        return "验证码已发送";
    }
    private String generateCode() {
        return String.valueOf(new Random().nextInt(899999) + 100000);
    }

}
