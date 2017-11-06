package com.mymvc.model;

import com.mymvc.model.basic.Model;

import javax.validation.constraints.Size;

/**
 * Created by alan.luo on 2017/9/24.
 */
public class ConditionModel extends Model {

    private int id;

    private Double page;

    private Double pageSize;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Double getPage() {
        return page;
    }

    public void setPage(Double page) {
        this.page = page;
    }

    public Double getPageSize() {
        return pageSize;
    }

    public void setPageSize(Double pageSize) {
        this.pageSize = pageSize;
    }
}
