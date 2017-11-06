package com.mymvc.system.pojo;

import com.mymvc.model.basic.Model;
import com.mymvc.repository.hibernate.basic.Criteria;

import java.util.List;

/**
 * Created by alan.luo on 2017/10/26.
 */
public class PredicatePojo extends Model {

    private String key;

    private String value;

    private Criteria criteria;

    private List<PredicatePojo> likeObj;

    public PredicatePojo(){}

    public PredicatePojo(String key,String val){
        this.key = key;
        this.value = val;
        this.criteria = Criteria.equal;
    }

    public PredicatePojo(String key,String val,Criteria criteria){
        this.key = key;
        this.value = val;
        this.criteria = criteria;
    }

    public PredicatePojo(List<PredicatePojo> o,Criteria criteria){
        this.likeObj = o;
        this.criteria = criteria;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Criteria getCriteria() {
        return criteria;
    }

    public void setCriteria(Criteria criteria) {
        this.criteria = criteria;
    }

    public List<PredicatePojo> getLikeObj() {
        return likeObj;
    }

    public void setLikeObj(List<PredicatePojo> likeObj) {
        this.likeObj = likeObj;
    }
}
