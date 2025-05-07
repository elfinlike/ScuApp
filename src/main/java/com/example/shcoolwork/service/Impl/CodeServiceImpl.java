package com.example.shcoolwork.service.Impl;

import com.example.shcoolwork.Entity.Code;
import com.example.shcoolwork.mapper.CodeMapper;
import com.example.shcoolwork.service.CodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CodeServiceImpl implements CodeService {

    @Autowired
    private CodeMapper codeMapper;
    @Override
    public void keep(String phone, String code) {
        //首先根据phone进行检查，数据库是否有之前的验证码，如果有，就删除；
        Code myCode=codeMapper.getByPhone(phone);

        if (myCode!=null) {
            codeMapper.delete(phone);//对存在的验证码进行清空
        }
        //将验证码和电话号码存储到数据库中
        codeMapper.add(phone,code);
    }
}
