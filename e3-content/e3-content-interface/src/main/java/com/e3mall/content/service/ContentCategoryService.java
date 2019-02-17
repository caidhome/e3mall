package com.e3mall.content.service;

import com.e3mall.common.pojo.EasyUITreeNode;
import com.e3mall.common.utils.E3Result;
import com.e3mall.pojo.TbContent;

import java.util.List;

public interface ContentCategoryService {

    public List<EasyUITreeNode> getContentCatList(Long parentId);
    public E3Result addContentCategory(long parentId,String name);

}
