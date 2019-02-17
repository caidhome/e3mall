package com.e3mall.search.service.impl;

import com.e3mall.common.pojo.SearchItem;
import com.e3mall.common.utils.E3Result;
import com.e3mall.search.mapper.ItemMapper;
import com.e3mall.search.service.SearchItemService;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.common.SolrInputDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SearchItemServiceImpl implements SearchItemService {

    @Autowired
    private ItemMapper itemMapper;
    @Autowired
    private SolrServer solrServer;

    public E3Result importAllItems() {

        try{
            List<SearchItem> itemList = itemMapper.getItemList();
            for (SearchItem searchItem : itemList) {
                SolrInputDocument document = new SolrInputDocument();
                document.setField("id",searchItem.getId());
                document.setField("item_title",searchItem.getTitle());
                document.setField("item_sell_point",searchItem.getSell_point());
                document.setField("item_price",searchItem.getPrice());
                document.setField("item_image",searchItem.getImage());
                document.setField("item_category_name",searchItem.getCategrory_name());
                solrServer.add(document);
            }

            solrServer.commit();
            return E3Result.ok();
        }catch (Exception e){
            e.printStackTrace();
            return E3Result.build(500,"数据导入时发生异常");
        }

    }
}
