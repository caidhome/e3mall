package com.e3mall.sso.service.impl;

import com.e3mall.common.jedis.JedisClient;
import com.e3mall.common.utils.E3Result;
import com.e3mall.common.utils.JsonUtils;
import com.e3mall.pojo.TbUser;
import com.e3mall.sso.service.TokenService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class TokenServiceImpl implements TokenService {

    @Autowired
    private JedisClient jedisClient;
    @Value("${SESSION_EXPIRE}")
    private Integer SESSION_EXPIRE;

    public E3Result getUserByToken(String token) {
        String json = jedisClient.get("SESSION:"+token);
        if(StringUtils.isBlank(json)){
            return E3Result.build(201,"用户登录已过期！");
        }
        jedisClient.expire("SESSION:"+token,SESSION_EXPIRE);
        TbUser user = JsonUtils.jsonToPojo(json,TbUser.class);
        return E3Result.ok(user);
    }
}
