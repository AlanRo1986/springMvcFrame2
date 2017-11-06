package com.mymvc.system.bus;

import com.mymvc.repository.hibernate.service.resource.IArticleService2;
import com.mymvc.system.annotation.EventBus;
import com.mymvc.system.bus.event.ArticleCommentEvent;
import com.mymvc.system.exception.IllegalServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.scheduling.annotation.Async;

/**
 * Created by alan.luo on 2017/11/6.
 */
@EventBus
public class ArticleCommentEventListener implements ApplicationListener<ArticleCommentEvent> {

    @Autowired
    private IArticleService2 service2;

    @Async
    @Override
    public void onApplicationEvent(ArticleCommentEvent articleCommentEvent) {
        Integer id = (Integer) articleCommentEvent.getSource();
        if (id != null && id > 0){
            try {
                service2.updateCommentCount(id);
            } catch (IllegalServiceException e) {
                e.printStackTrace();
            }
        }
    }
}
