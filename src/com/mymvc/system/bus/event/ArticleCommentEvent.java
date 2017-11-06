package com.mymvc.system.bus.event;


/**
 * Created by alan.luo on 2017/11/6.
 */
public class ArticleCommentEvent extends ApplicationArticleEvent {
    public ArticleCommentEvent(Integer source) {
        super(source);
    }
}
