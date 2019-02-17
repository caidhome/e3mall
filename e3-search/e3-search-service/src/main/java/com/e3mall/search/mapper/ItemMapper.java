package com.e3mall.search.mapper;

import com.e3mall.common.pojo.SearchItem;

import java.util.List;

public interface ItemMapper {

    List<SearchItem> getItemList();
    SearchItem getItemById(Long itemId);
 }
