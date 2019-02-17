package com.e3mall.search.controller;

import com.e3mall.common.pojo.SearchResult;
import com.e3mall.search.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 商品搜素Controller
 */
@Controller
public class SearchController {

    @Autowired
    private SearchService searchService;

    @Value("${SEARCH_RESULT_ROWS}")
    private Integer SEARCH_RESULT_ROWS;

    @RequestMapping("/search")
    public String searchItemList(String keyword, @RequestParam(defaultValue = "1")Integer page, Model model) throws Exception {
        keyword = new String(keyword.getBytes("ISO-8859-1"),"utf-8");
        SearchResult searchResult = searchService.search(keyword,page,SEARCH_RESULT_ROWS);
        model.addAttribute("query",keyword);
        model.addAttribute("totalPages",searchResult.getTotalPages());
        model.addAttribute("page",page);
        model.addAttribute("recourdCount",searchResult.getRecordCount());
        model.addAttribute("itemList",searchResult.getItemList());
        //异常测试
//        int a = 1/0;
        return "search";
    }
}
