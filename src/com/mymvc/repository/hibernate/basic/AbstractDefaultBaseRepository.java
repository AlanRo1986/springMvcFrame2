package com.mymvc.repository.hibernate.basic;

import com.mymvc.repository.hibernate.resource.IDefaultRepository;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * Created by alan.luo on 2017/10/22.
 */
public abstract class AbstractDefaultBaseRepository<E extends Serializable> implements IDefaultRepository<E> {

    protected  final Class<E> entityClass;

    public AbstractDefaultBaseRepository(){

        Type genericSuperclass = this.getClass().getGenericSuperclass();
        while (!(genericSuperclass instanceof ParameterizedType)){
            if (!(genericSuperclass instanceof Class)){
                throw new IllegalStateException("Unable to determine type arguments because generic superclass" +
                        " neither parameterized type nor class.");
            }
            if (genericSuperclass == AbstractDefaultBaseRepository.class){
                throw new IllegalStateException("Unable to determine type arguments because no parameterized " +
                        "generic superclass find.");
            }

            genericSuperclass = (((Class) genericSuperclass).getGenericSuperclass());
        }
        ParameterizedType type = (ParameterizedType) genericSuperclass;
        Type[] args = type.getActualTypeArguments();
        this.entityClass = (Class<E>) args[0];
    }















}
