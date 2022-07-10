package com.blog.springbootblogsystem.dto;

import java.util.List;

public class PostResponse {
    
    private List<PostDTO> content;

    private Integer pageNum;
    private Integer pageSize;
    private Long totalItems;
    private Integer totalPages;
    private boolean last;

    public PostResponse() {
    }

    /**
     * @return List<PostDTO> return the content
     */
    public List<PostDTO> getContent() {
        return content;
    }

    /**
     * @param content the content to set
     */
    public void setContent(List<PostDTO> content) {
        this.content = content;
    }

    /**
     * @return Integer return the pageNum
     */
    public Integer getPageNum() {
        return pageNum;
    }

    /**
     * @param pageNum the pageNum to set
     */
    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    /**
     * @return Integer return the pageSize
     */
    public Integer getPageSize() {
        return pageSize;
    }

    /**
     * @param pageSize the pageSize to set
     */
    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    /**
     * @return Long return the totalItems
     */
    public Long getTotalItems() {
        return totalItems;
    }

    /**
     * @param totalItems the totalItems to set
     */
    public void setTotalItems(Long totalItems) {
        this.totalItems = totalItems;
    }

    /**
     * @return Integer return the totalPages
     */
    public Integer getTotalPages() {
        return totalPages;
    }

    /**
     * @param totalPages the totalPages to set
     */
    public void setTotalPages(Integer totalPages) {
        this.totalPages = totalPages;
    }

    /**
     * @return boolean return the last
     */
    public boolean isLast() {
        return last;
    }

    /**
     * @param last the last to set
     */
    public void setLast(boolean last) {
        this.last = last;
    }

}