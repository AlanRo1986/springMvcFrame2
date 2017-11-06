package com.mymvc.system.pojo;

import com.mymvc.constant.Constant;
import com.mymvc.constant.ConstantInit;
import com.mymvc.model.basic.Model;

import java.util.List;

/**
 * Created by alan.luo on 2017/10/26.
 */
public class PagePojo<T> extends Model {

    private Long page;
    private Long pageSize;
    private Long pageTotal;
    private Long itemNum;
    private List<T> list;

    public PagePojo(Long page){
        this.pageSize = Long.valueOf(ConstantInit.PAGE_SIZE);
        this.page = page;
    }

    public PagePojo(Long page, Long itemNum){
        this.pageSize = Long.valueOf(ConstantInit.PAGE_SIZE);
        this.page = page;
        this.setItemNum(itemNum);
    }

    public PagePojo(Long page, Long itemNum, Long pageSize){
        this.pageSize = pageSize;
        this.page = page;
        this.setItemNum(itemNum);
    }

    public Long getPage() {
        return page;
    }

    public void setPage(Long page) {
        this.page = page;
    }

    public Long getPageSize() {
        return pageSize;
    }

    public void setPageSize(Long pageSize) {
        this.pageSize = pageSize;
    }

    public Long getPageTotal() {
        return pageTotal;
    }

    public void setPageTotal(Long pageTotal) {
        this.pageTotal = pageTotal;
    }

    public Long getItemNum() {
        return itemNum;
    }

    public void setItemNum(Long itemNum) {
        this.itemNum = itemNum;
        setPageTotal((long) Math.ceil((double)itemNum / (double)getPageSize()));
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }
}
