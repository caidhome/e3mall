package com.e3mall.search.service.impl;

import com.e3mall.common.pojo.SearchResult;
import com.e3mall.search.dao.SearchDao;
import com.e3mall.search.service.SearchService;
import org.apache.solr.client.solrj.SolrQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SearchServiceImpl implements SearchService {

    @Autowired
    private SearchDao searchDao;

    public SearchResult search(String keywords, int page, int row) throws Exception {
        SolrQuery solrQuery = new SolrQuery();
        solrQuery.setQuery(keywords);
        if (page <= 0) page = 1;
        solrQuery.setStart((page - 1) * row);
        solrQuery.setRows(row);
        solrQuery.set("df", "item_title");
        solrQuery.setHighlight(true);
        solrQuery.addHighlightField("item_title");
        solrQuery.setHighlightSimplePre("<em style=\"color:red\">");
        solrQuery.setHighlightSimplePost("</em>");
        SearchResult searchResult = searchDao.search(solrQuery);
        long recordCount = searchResult.getRecordCount();
        int totalPage = (int) (recordCount / row);
        if (recordCount % row > 0)
            totalPage++;
        searchResult.setTotalPages(totalPage);
        return searchResult;
    }
}
