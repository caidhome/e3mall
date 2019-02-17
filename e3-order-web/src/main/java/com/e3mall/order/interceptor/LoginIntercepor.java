package com.e3mall.order.interceptor;

import com.e3mall.cart.service.CartService;
import com.e3mall.common.utils.CookieUtils;
import com.e3mall.common.utils.E3Result;
import com.e3mall.common.utils.JsonUtils;
import com.e3mall.pojo.TbItem;
import com.e3mall.pojo.TbUser;
import com.e3mall.sso.service.TokenService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginIntercepor implements HandlerInterceptor {

    @Value("${SSO_URL}")
    private String SSO_URL;
    @Autowired
    private TokenService tokenService;
    @Autowired
    private CartService cartService;

    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object o) throws Exception {
        String taken = CookieUtils.getCookieValue(request, "token");
        if(StringUtils.isBlank(taken)){
            response.sendRedirect(SSO_URL+"/page/login?redirect="+request.getRequestURL());
            return false;
        }
        E3Result e3Result = tokenService.getUserByToken(taken);
        if(e3Result.getStatus() != 200){
            response.sendRedirect(SSO_URL+"/page/login?redirect="+request.getRequestURL());
            return false;
        }
        TbUser user = (TbUser) e3Result.getData();
        request.setAttribute("user",user);
        String jsonCartList = CookieUtils.getCookieValue(request, "cart", true);
        if(StringUtils.isNotBlank(jsonCartList)){
            cartService.mergeCart(user.getId(),JsonUtils.jsonToList(jsonCartList, TbItem.class));
        }

        return true;
    }

    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
