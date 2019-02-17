package com.e3mall.protal.controller;

import com.e3mall.content.service.ContentService;
import com.e3mall.pojo.TbContent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class IndexContrller {

    @Autowired
    private ContentService contentService;

    @Value("${CONTENT_LUNBO_ID}")
    private Long CONTENT_LUNBO_ID;

    @RequestMapping("/index")
    public String toIndex(Model model){

        List<TbContent> ad1List = contentService.getContentByCid(CONTENT_LUNBO_ID);
        model.addAttribute("ad1List",ad1List);
        return "index";
    }
}
