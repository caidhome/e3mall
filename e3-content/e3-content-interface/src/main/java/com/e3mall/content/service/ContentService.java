package com.e3mall.content.service;

import com.e3mall.common.utils.E3Result;
import com.e3mall.pojo.TbContent;

import java.util.List;

public interface ContentService {
    public E3Result addContent(TbContent content);
    public List<TbContent> getContentByCid(long cid);
}
