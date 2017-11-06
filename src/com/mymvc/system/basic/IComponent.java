package com.mymvc.system.basic;

import com.mymvc.model.ConditionModel;
import com.mymvc.system.exception.IllegalComponentException;

import java.util.List;

/**
 * All the service and repository must be implements this interface.
 *
 * Created by alan.luo on 2017/9/24.
 */
public interface IComponent<T> {

    T getOne(ConditionModel model) throws IllegalComponentException;

    T getRow(ConditionModel model) throws IllegalComponentException;

    List<T> getAll(ConditionModel model) throws IllegalComponentException;

    Integer count(ConditionModel model) throws IllegalComponentException;

    Integer insert(T model) throws IllegalComponentException;

    Integer update(T model) throws IllegalComponentException;

    Integer delete(ConditionModel model) throws IllegalComponentException;

}
