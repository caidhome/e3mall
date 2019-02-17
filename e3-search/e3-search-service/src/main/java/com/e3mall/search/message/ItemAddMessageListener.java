package com.e3mall.search.message;

import com.e3mall.common.pojo.SearchItem;
import com.e3mall.search.mapper.ItemMapper;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrInputDocument;
import org.springframework.beans.factory.annotation.Autowired;

import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

public class ItemAddMessageListener implements MessageListener {

    @Autowired
    private ItemMapper itemMapper;

    @Autowired
    private SolrServer solrServer;

    public void onMessage(Message message) {
        try {
            TextMessage textMessage = (TextMessage) message;
            String text = textMessage.getText();
            Long id = new Long(text);
            //等待消息,等待事物提交
            Thread.sleep(1000);

            SearchItem searchItem = itemMapper.getItemById(id);
//            System.out.println("+++"+id+" +++++++++ "+searchItem.getId());
            SolrInputDocument document = new SolrInputDocument();
            document.setField("id",searchItem.getId());
            document.setField("item_title",searchItem.getTitle());
            document.setField("item_sell_point",searchItem.getSell_point());
            document.setField("item_price",searchItem.getPrice());
            document.setField("item_image",searchItem.getImage());
            document.setField("item_category_name",searchItem.getCategrory_name());
            solrServer.add(document);
            solrServer.commit();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
