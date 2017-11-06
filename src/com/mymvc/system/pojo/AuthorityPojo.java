package com.mymvc.system.pojo;

/**
 * Created by alan.luo on 2017/11/1.
 */
public class AuthorityPojo {

    private String controller;

    private String action;

    private boolean authentication;

    public AuthorityPojo(){

    }

    public AuthorityPojo(String ctl,String act){
        setController(ctl);
        setAction(act);
        setAuthentication(true);
    }

    public AuthorityPojo(String ctl,String act,boolean auth){
        setController(ctl);
        setAction(act);
        setAuthentication(auth);
    }

    public String getController() {
        return controller;
    }

    public void setController(String controller) {
        this.controller = controller;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public boolean isAuthentication() {
        return authentication;
    }

    public void setAuthentication(boolean authentication) {
        this.authentication = authentication;
    }
}
