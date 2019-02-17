package com.e3mall.sso.service.impl;

import com.e3mall.common.jedis.JedisClient;
import com.e3mall.common.utils.E3Result;
import com.e3mall.common.utils.JsonUtils;
import com.e3mall.mapper.TbUserMapper;
import com.e3mall.pojo.TbUser;
import com.e3mall.pojo.TbUserExample;
import com.e3mall.pojo.TbUserExample.Criteria;
import com.e3mall.sso.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.util.List;
import java.util.UUID;

@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private TbUserMapper userMapper;
    @Autowired
    private JedisClient jedisClient;
    @Value("${SESSION_EXPIRE}")
    private Integer SESSION_EXPIRE;

    public E3Result userLogin(String username, String password) {

        TbUserExample example = new TbUserExample();
        Criteria criteria = example.createCriteria();
        criteria.andUsernameEqualTo(username);
        List<TbUser> list = userMapper.selectByExample(example);
        if(list == null || list.size() == 0){
            return E3Result.build(400,"用户名或密码错误！");
        }
        TbUser user = list.get(0);
        if(!DigestUtils.md5DigestAsHex(password.getBytes()).equals(user.getPassword())){
            return E3Result.build(400,"用户名或密码错误！");
        }
        String token = UUID.randomUUID().toString();
        user.setPassword(null);
        //把信息写入Redis中：key->token,value->用户信息
        jedisClient.set("SESSION:"+token, JsonUtils.objectToJson(user));
        jedisClient.expire("SESSION:"+token,SESSION_EXPIRE);

        return E3Result.ok(token);

    }
}

