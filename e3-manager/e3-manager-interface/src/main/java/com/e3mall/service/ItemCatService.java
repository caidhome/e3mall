package com.e3mall.service;

import com.e3mall.common.pojo.EasyUITreeNode;

import java.util.List;

public interface ItemCatService {

    public List<EasyUITreeNode> getItemCatList(long parentId);
}
