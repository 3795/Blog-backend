package com.seven.Blog.dto;

import java.util.List;

/**
 * Created By Seven.wk
 * Description: 向前端提供文章卡片列表
 * Created At 2018/10/04
 */
public class ArticleCardsDTO {

    private List<ArticleCardDTO> articleCardDTOList;

    private Integer currentPage;        //当前页码

    private Integer maxPage;        //共有多少页

    private String pageUrl;     //每个页码对应的链接前缀

    public ArticleCardsDTO() {
    }

    public ArticleCardsDTO(List<ArticleCardDTO> articleCardDTOList, Integer currentPage, Integer maxPage, String pageUrl) {
        this.articleCardDTOList = articleCardDTOList;
        this.currentPage = currentPage;
        this.maxPage = maxPage;
        this.pageUrl = pageUrl;
    }

    public List<ArticleCardDTO> getArticleCardDTOList() {
        return articleCardDTOList;
    }

    public void setArticleCardDTOList(List<ArticleCardDTO> articleCardDTOList) {
        this.articleCardDTOList = articleCardDTOList;
    }

    public Integer getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(Integer currentPage) {
        this.currentPage = currentPage;
    }

    public Integer getMaxPage() {
        return maxPage;
    }

    public void setMaxPage(Integer maxPage) {
        this.maxPage = maxPage;
    }

    public String getPageUrl() {
        return pageUrl;
    }

    public void setPageUrl(String pageUrl) {
        this.pageUrl = pageUrl;
    }
}
