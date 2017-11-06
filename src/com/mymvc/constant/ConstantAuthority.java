package com.mymvc.constant;

import com.mymvc.system.pojo.AuthorityPojo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by alan.luo on 2017/11/1.
 */
public class ConstantAuthority {

    public static final Map<String,AuthorityPojo> authority = new HashMap<String,AuthorityPojo>(){
        {
            this.put("/api/user",new AuthorityPojo("/api/user","GET"));
            this.put("/api/login",new AuthorityPojo("/api/login","GET"));
            this.put("/api/loginOut/post",new AuthorityPojo("/api/loginOut","POST"));
            this.put("/api/upload/base64/post",new AuthorityPojo("/api/upload/base64","POST"));
            this.put("/api/upload/post",new AuthorityPojo("/api/upload","POST"));
            this.put("/api/article/post",new AuthorityPojo("/api/article","POST"));
            this.put("/api/article/put",new AuthorityPojo("/api/article","PUT"));
            this.put("/api/article/delete",new AuthorityPojo("/api/article","DELETE"));
            this.put("/api/comment/post",new AuthorityPojo("/api/comment","POST"));
            this.put("/api/comment/put",new AuthorityPojo("/api/comment","PUT"));
            this.put("/api/comment/delete",new AuthorityPojo("/api/comment","DELETE"));
        }
    };

}
