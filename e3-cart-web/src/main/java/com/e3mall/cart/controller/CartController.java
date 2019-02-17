package com.e3mall.cart.controller;

import com.e3mall.cart.service.CartService;
import com.e3mall.common.utils.CookieUtils;
import com.e3mall.common.utils.E3Result;
import com.e3mall.common.utils.JsonUtils;
import com.e3mall.pojo.TbItem;
import com.e3mall.pojo.TbUser;
import com.e3mall.service.ItemService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

@Controller
public class CartController {

    @Value("${COOKIE_CART_EXPIRE}")
    private Integer COOKIE_CART_EXPIRE;
    @Autowired
    private ItemService itemService;
    @Autowired
    private CartService cartService;

    /*
    添加商品到购物车
     */
    @RequestMapping("/cart/add/{itemId}")
    public String addCart(@PathVariable Long itemId,
                          @RequestParam(defaultValue = "1") Integer num
                        , HttpServletRequest request, HttpServletResponse response){
        TbUser user = (TbUser) request.getAttribute("user");
        if(user != null){
            cartService.addCart(user.getId(),itemId,num);
            return "cartSuccess";
        }


        List<TbItem> cartList = getCartFromCookie(request);
        boolean flag = false;
        for (TbItem tbItem : cartList){
            if(tbItem.getId() == itemId.longValue()){
                flag = true;
                break;
            }
        }
        if(!flag){
            TbItem tbItem = itemService.getItemById(itemId);
            tbItem.setNum(num);
            String image = tbItem.getImage();
            if(StringUtils.isNotBlank(image)){
                tbItem.setImage(image.split(",")[0]);
            }
            cartList.add(tbItem);
        }
        CookieUtils.setCookie(request,response,"cart",JsonUtils.objectToJson(cartList),COOKIE_CART_EXPIRE,true);
        return "cartSuccess";
    }

    public List<TbItem> getCartFromCookie(HttpServletRequest request){
        String json = CookieUtils.getCookieValue(request,"cart",true);
        if(StringUtils.isBlank(json)){
            return new ArrayList<TbItem>();
        }
        List list = JsonUtils.jsonToList(json,TbItem.class);
        return list;
    }

    @RequestMapping("/cart/cart")
    public String showCartList(HttpServletRequest request,HttpServletResponse response){
        List<TbItem> cartList = getCartFromCookie(request);
        TbUser user = (TbUser) request.getAttribute("user");
        if(user != null){
            cartService.mergeCart(user.getId(),cartList);
            CookieUtils.deleteCookie(request,response,"cart");
            cartList = cartService.getCartList(user.getId());
        }

        request.setAttribute("cartList",cartList);
        return"cart";
    }

    /*
    跟新购物车商品数量
     */
    @RequestMapping("/cart/update/num/{itemId}/{num}")
    @ResponseBody
    public E3Result updateCartNum(@PathVariable Long itemId, @PathVariable Integer num,
                                  HttpServletRequest request,HttpServletResponse response){
        TbUser user = (TbUser) request.getAttribute("user");
        if(user != null){
            cartService.updateCartNum(user.getId(),itemId,num);
            return E3Result.ok();
        }

        List<TbItem> cartList = getCartFromCookie(request);
        for(TbItem tbItem : cartList){
            if(tbItem.getId() == itemId.longValue()){
                tbItem.setNum(num);
                break;
            }
        }
        CookieUtils.setCookie(request,response,"cart",JsonUtils.objectToJson(cartList),COOKIE_CART_EXPIRE,true);
        return E3Result.ok();
            }

    @RequestMapping("/cart/delete/{itemId}")
    public String deleteCart(@PathVariable Long itemId,HttpServletRequest request,HttpServletResponse response){
        TbUser user = (TbUser) request.getAttribute("user");
        if(user != null){
            cartService.deleteCartNum(user.getId(),itemId);
            return "redirect:/cart/cart.html";
        }
        List<TbItem> cartList = getCartFromCookie(request);
        for(TbItem tbItem : cartList){
            if(tbItem.getId() == itemId.longValue()){
                cartList.remove(tbItem);
                break;
            }
        }
        CookieUtils.setCookie(request,response,"cart",JsonUtils.objectToJson(cartList),COOKIE_CART_EXPIRE,true);
        return "redirect:/cart/cart.html";
    }

}
