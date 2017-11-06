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

public abstract class AbstractDefaultRepository<E extends Serializable> extends AbstractDefaultBaseRepository<E> {

    @PersistenceContext
    private EntityManager manager;

    /**
     * get all the data.
     * @param page
     * @param where
     * @param order
     * @param isDesc
     * @return
     */
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

    /**
     * search all the data.
     * @param page
     * @param condition
     * @param order
     * @param isDesc
     * @return
     */
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

    /**
     *
     * List<PredicatePojo> where = new ArrayList<>();
     *  where.add(new PredicatePojo("id","81", Criteria.lt));
     *  where.add(new PredicatePojo("age","10", Criteria.gt));
     *
     * List<PredicatePojo> where = new ArrayList<>();
     * if (userName != null){
     *     userName = "%" + userName + "%";
     *     List<PredicatePojo> like = new ArrayList<>();
     *     like.add(new PredicatePojo("username",userName, Criteria.like));
     *     like.add(new PredicatePojo("mobile",userName, Criteria.like));
     *     like.add(new PredicatePojo("email",userName, Criteria.like));
     *
     *     PredicatePojo pojo = new PredicatePojo();
     *     pojo.setLikeObj(like);
     *     pojo.setCriteria(Criteria.like);
     *     where.add(pojo);
     * }
     *
     *
     *
     * parser predicate.
     * @param where
     * @param builder
     * @param root
     * @return
     */
    protected Predicate[] getListPredicate(List<PredicatePojo> where,CriteriaBuilder builder,Root<E> root){

        Predicate[] predicates = new Predicate[where.size()];
        Predicate[] like;

        if (where != null && where.size() > 0){
            Predicate p = null;

            for (int i = 0; i < where.size();i++){

                Criteria ca = where.get(i).getCriteria();
                switch (ca){
                    case equal:
                        p = builder.equal(root.get(where.get(i).getKey()),where.get(i).getValue());
                        break;
                    case notEqual:
                        p = builder.notEqual(root.get(where.get(i).getKey()),where.get(i).getValue());
                        break;
                    case isNull:
                        p = builder.isNull(root.get(where.get(i).getKey()));
                        break;
                    case isNotNull:
                        p = builder.isNotNull(root.get(where.get(i).getKey()));
                        break;
                    case gt:
                        p = builder.gt(root.get(where.get(i).getKey()),Double.valueOf(where.get(i).getValue()));
                        break;
                    case lt:
                        p = builder.lt(root.get(where.get(i).getKey()),Double.valueOf(where.get(i).getValue()));
                        break;
                    case ge:
                        p = builder.ge(root.get(where.get(i).getKey()),Double.valueOf(where.get(i).getValue()));
                        break;
                    case le:
                        p = builder.le(root.get(where.get(i).getKey()),Double.valueOf(where.get(i).getValue()));
                        break;
                    case isFalse:
                        p = builder.isFalse(root.get(where.get(i).getKey()));
                        break;
                    case isTrue:
                        p = builder.isTrue(root.get(where.get(i).getKey()));
                        break;
                    case not:
                        p = builder.not(root.get(where.get(i).getKey()));
                        break;
                    case like:
                        like = new Predicate[where.get(i).getLikeObj().size()];
                        for (int j = 0; j < where.get(i).getLikeObj().size();j++){
                            like[j] = builder.like(root.get(where.get(i).getLikeObj().get(j).getKey())
                                    ,where.get(i).getLikeObj().get(j).getValue());
                        }
                        p = builder.or(like);
                        break;
                    case notLike:
                        like = new Predicate[where.get(i).getLikeObj().size()];
                        for (int j = 0; j < where.get(i).getLikeObj().size();j++){
                            like[j] = builder.notLike(root.get(where.get(i).getLikeObj().get(j).getKey())
                                    ,where.get(i).getLikeObj().get(j).getValue());
                        }
                        p = builder.and(like);
                        break;
                }

                predicates[i] = p;
            }
        }
        return predicates;
    }

}
