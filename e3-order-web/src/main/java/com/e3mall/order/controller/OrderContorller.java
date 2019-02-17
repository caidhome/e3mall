package com.e3mall.order.controller;

import com.e3mall.cart.service.CartService;
import com.e3mall.common.utils.E3Result;
import com.e3mall.order.pojo.OrderInfo;
import com.e3mall.order.service.OrderService;
import com.e3mall.pojo.TbItem;
import com.e3mall.pojo.TbUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class OrderContorller {

    @Autowired
    private CartService cartService;
    @Autowired
    private OrderService orderService;

    @RequestMapping("/order/order-cart")
    public String showOrderCart(HttpServletRequest request){
        TbUser user = (TbUser) request.getAttribute("user");
        List<TbItem> cartList = cartService.getCartList(user.getId());
        request.setAttribute("cartList",cartList);
        return "order-cart";
    }

    @RequestMapping(value="/order/create",method = RequestMethod.POST)
    public String createOrder(OrderInfo orderInfo,HttpServletRequest request){
        TbUser user = (TbUser) request.getAttribute("user");
        orderInfo.setBuyerNick(user.getUsername());
        orderInfo.setUserId(user.getId());
        E3Result e3Result = orderService.createOrder(orderInfo);
        if(e3Result.getStatus() == 200){
            cartService.clearCartItem(user.getId());
        }
        request.setAttribute("orderId",e3Result.getData());
        request.setAttribute("payment",orderInfo.getPayment());
        return "success";
    }
}
