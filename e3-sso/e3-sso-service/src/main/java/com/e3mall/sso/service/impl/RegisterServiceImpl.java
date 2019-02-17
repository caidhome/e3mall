package com.e3mall.sso.service.impl;

import com.e3mall.common.utils.E3Result;
import com.e3mall.sso.service.RegisterService;
import com.e3mall.mapper.TbUserMapper;
import com.e3mall.pojo.TbUser;
import com.e3mall.pojo.TbUserExample;
import com.e3mall.pojo.TbUserExample.Criteria;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.util.Date;
import java.util.List;

@Service
public class RegisterServiceImpl implements RegisterService {

    @Autowired
    private TbUserMapper tbUserMapper;

    public E3Result checkData(String param, int type) {

        TbUserExample example = new TbUserExample();
        Criteria criteria = example.createCriteria();
        //type:1:username,2:tel,3:email;
        if(type == 1){
            //username
            criteria.andUsernameEqualTo(param);
        }else if(type == 2){
            //tel
            criteria.andPhoneEqualTo(param);
        }else if(type == 3){
            //email
            criteria.andEmailEqualTo(param);
        }else{
            return E3Result.build(400,"数据类型错误");
        }
        List<TbUser> list = tbUserMapper.selectByExample(example);
        if(list != null && list.size() > 0){
            return E3Result.ok(false);
        }
        return E3Result.ok(true);
    }

    public E3Result register(TbUser user) {
        if("".equals(user.getUsername()) && "".equals(user.getPassword())){
            return E3Result.build(400,"用户数据不完整，注册失败！");
        }
        E3Result e3Result = checkData(user.getUsername(),1);
        if(!Boolean.parseBoolean(e3Result.getData().toString())){
            return E3Result.build(400,"此用户名已占用，注册失败！");
        }
        E3Result result = checkData(user.getPhone(),2);
        if(!Boolean.parseBoolean(result.getData().toString())){
            return E3Result.build(400,"此用户名已占用，注册失败！");
        }
        user.setCreated(new Date());
        user.setUpdated(new Date());
        String md5Pwd = DigestUtils.md5DigestAsHex(user.getPassword().getBytes());
        user.setPassword(md5Pwd);
        tbUserMapper.insert(user);

        return E3Result.ok();
    }
}
