package com.e3mall.service;

import com.e3mall.common.pojo.EasyUIDataGridResult;
import com.e3mall.common.utils.E3Result;
import com.e3mall.pojo.TbItem;
import com.e3mall.pojo.TbItemDesc;

public interface ItemService {

    TbItem getItemById(long itemId);

    public EasyUIDataGridResult getItemList(int page,int rows);

    public E3Result addItem(TbItem item,String desc);

    TbItemDesc getItemDescById(long itemId);
}
