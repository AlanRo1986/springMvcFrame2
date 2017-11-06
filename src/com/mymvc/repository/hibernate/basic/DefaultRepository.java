package com.mymvc.repository.hibernate.basic;

import com.mymvc.constant.ConstantInit;
import com.mymvc.system.pojo.PagePojo;
import com.mymvc.system.pojo.PredicatePojo;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.io.Serializable;
import java.util.List;

/**
 * Created by alan.luo on 2017/10/22.
 */

public abstract class DefaultRepository<E extends Serializable> extends DefaultBaseRepository<E> {

    @PersistenceContext
    private EntityManager manager;

    @Override
    public List<E> getAll(int page, List<PredicatePojo> where, String order, boolean isDesc) {
        CriteriaBuilder builder = this.manager.getCriteriaBuilder();
        CriteriaQuery<E> query = builder.createQuery(this.entityClass);

        Root<E> root = query.from(this.entityClass);
        CriteriaQuery<E> select = query.select(root);
        if (order != null && !"".equals(order)){
            if (isDesc){
                select.orderBy(builder.desc(root.get(order)));
            }else {
                select.orderBy(builder.asc(root.get(order)));
            }
        }

        select.where(getListPredicate(where,builder,root));

        TypedQuery<E> typedQuery = this.manager.createQuery(select);
        typedQuery.setFirstResult((page - 1) * ConstantInit.PAGE_SIZE);
        typedQuery.setMaxResults(ConstantInit.PAGE_SIZE);
        return typedQuery.getResultList();
    }

    @Override
    public PagePojo<E> search(int page, List<PredicatePojo> condition, String order, boolean isDesc){
        CriteriaBuilder builder = this.manager.getCriteriaBuilder();
        CriteriaQuery<Long> query = builder.createQuery(Long.class);

        Root<E> root = query.from(this.entityClass);
        CriteriaQuery<Long> select = query.select(builder.count(root));

        select.where(getListPredicate(condition,builder,root));

        long total = this.manager.createQuery(select).getSingleResult();

        PagePojo<E> pages = new PagePojo<E>((long) page,total);
        pages.setList(this.getAll(page,condition,order,isDesc));
        return  pages;
    }

    @Override
    public E getRowById(int id) {
        return this.manager.find(this.entityClass,id);
    }

    @Override
    public void add(E o) {
        this.manager.persist(o);
    }

    @Override
    public void update(E o) {
        this.manager.merge(o);
    }

    @Override
    public void remove(E o) {
        this.manager.remove(o);
    }

    @Override
    public int removeById(int id) {
        CriteriaBuilder builder = this.manager.getCriteriaBuilder();
        CriteriaDelete<E> q = builder.createCriteriaDelete(this.entityClass);

        return this.manager
                .createQuery(q.where(builder.equal(q.from(this.entityClass).get("id"),id)))
                .executeUpdate();
    }

    protected Predicate[] getListPredicate(List<PredicatePojo> where,CriteriaBuilder builder,Root<E> root){
        Predicate[] predicates = new Predicate[where.size()];
        if (where != null && where.size() > 0){
            Predicate p = null;
            for (int i = 0; i < where.size();i++){
                Criteria ca = where.get(i).getCriteria();
                if (ca.equals(Criteria.equal)){
                    p = builder.equal(root.get(where.get(i).getKey()),where.get(i).getValue());
                }else if (ca.equals(Criteria.notEqual)){
                    p = builder.notEqual(root.get(where.get(i).getKey()),where.get(i).getValue());
                }else if (ca.equals(Criteria.isNull)){
                    p = builder.isNull(root.get(where.get(i).getKey()));
                }else if (ca.equals(Criteria.isNotNull)){
                    p = builder.isNotNull(root.get(where.get(i).getKey()));
                }else if (ca.equals(Criteria.gt)){
                    p = builder.gt(root.get(where.get(i).getKey()),Double.valueOf(where.get(i).getValue()));
                }else if (ca.equals(Criteria.lt)){
                    p = builder.lt(root.get(where.get(i).getKey()),Double.valueOf(where.get(i).getValue()));
                }else if (ca.equals(Criteria.ge)){
                    p = builder.ge(root.get(where.get(i).getKey()),Double.valueOf(where.get(i).getValue()));
                }else if (ca.equals(Criteria.le)){
                    p = builder.le(root.get(where.get(i).getKey()),Double.valueOf(where.get(i).getValue()));
                }else if (ca.equals(Criteria.isFalse)){
                    p = builder.isFalse(root.get(where.get(i).getKey()));
                }else if (ca.equals(Criteria.isTrue)){
                    p = builder.isTrue(root.get(where.get(i).getKey()));
                }else if (ca.equals(Criteria.not)){
                    p = builder.not(root.get(where.get(i).getKey()));
                }
                predicates[i] = p;
            }
        }
        return predicates;
    }

}
