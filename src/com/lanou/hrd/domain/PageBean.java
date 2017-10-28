package com.lanou.hrd.domain;

import java.util.List;

/**
 * Created by dllo on 17/10/27.
 */
public class PageBean<T> {
    private int pageNum;//第几页
    private int pageSize;//每页显示条目数
    private int totalRecord;//总记录数
    
    private int startIndex;//开始索引
    private int totalPage;//总分页数

    private List<T> data;//分页数据

    public PageBean(int pageNum, int pageSize, int totalRecord) {
    }

    public PageBean() {
    }

    @Override
    public String toString() {
        return "PageBean{" +
                "pageNum=" + pageNum +
                ", pageSize=" + pageSize +
                ", totalRecord=" + totalRecord +
                ", startIndex=" + startIndex +
                ", totalPage=" + totalPage +
                ", data=" + data +
                '}';
    }

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getTotalRecord() {
        return totalRecord;
    }

    public void setTotalRecord(int totalRecord) {
        this.totalRecord = totalRecord;
    }
/////////
    public int getStartIndex() {

        return pageSize*(pageNum-1);
    }
    public void setStartIndex(int startIndex) {
        this.startIndex = startIndex;
    }
/////////
    public int getTotalPage() {

        return (totalRecord+ ( pageSize - 1))/ pageSize;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }
}
