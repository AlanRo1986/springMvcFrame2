package com.mymvc.repository.hibernate.resource;

import com.mymvc.system.pojo.PagePojo;
import com.mymvc.system.pojo.PredicatePojo;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * Created by alan.luo on 2017/10/22.
 */
public interface IDefaultRepository<E> {

    List<E> getAll(@NotNull int page, List<PredicatePojo> where, String order, boolean isDesc);

    PagePojo<E> search(int page, List<PredicatePojo> where, String order, boolean isDesc);

    E getRowById(@NotNull int id);

    void add(@NotNull E o);

    void update(@NotNull E o);

    void remove(@NotNull E o);

    int removeById(@NotNull int id);

}
