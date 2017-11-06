<%--
  Created by IntelliJ IDEA.
  User: alan.luo
  Date: 2017/11/6
  Time: 9:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/jsp/views/Inc/hearder.jsp" %>
<div class="bs-example" data-example-id="basic-forms">
    <h1 class="title">
        <div style="height: 20px;background: #E4E4E4;">

        </div>
    </h1>
    <hr/>
    <div class="content">
        <div style="height: 300px;background: #E4E4E4; width: 100%;text-align: center;padding-top: 80px;color: #666;">
            没有内容...
        </div>
    </div>
    <hr/>
    <div class="comment">
        <form id="commentForm">
            <div class="form-group">
                <label for="comment_content">评论内容</label>
                <textarea id="comment_content" style="width: 100%" rows="4"></textarea>
            </div>
            <button type="submit" class="btn btn-default">发表</button>
        </form>
    </div>
    <div class="mt50"></div>
    <hr/>
    <div class="comments">
        <%--<div class="comment-info">--%>
            <%--<div class="comment-content"></div>--%>
            <%--<hr/>--%>
            <%--<div class="info">--%>
                <%--<i class="fa fa-user"> @Alan</i> 2017-01-01 11:22:00--%>
            <%--</div>--%>
        <%--</div>--%>
    </div>
</div>
<script>
    $(function () {
        var id = "${id}";

        function parserHtml(page) {
            if (page.itemNum > 0){
                var list = page.list;
                var $comments = $(".comments");
                var h = "";
                for (var i=0;i<list.length;i++){
                    h = '<div class="comment-info">\
                            <div class="comment-content">'+list[i].content+'</div>\
                            <hr/>\
                            <div class="info">\
                                <i class="fa fa-user"> @'+list[i].author.nickname+'</i> '+list[i].createTimeFormat+' \
                            </div>\
                        </div>';
                    $comments.append(h);
                }
            }
        }

        if (id > 0){
            app.ajax("/api/article",{id:id},function (e) {
                if(e.code == 1){
                    $(".title").html(e.data.article.title);
                    $(".content").html(e.data.article.content);
               }
            }).get();

            app.ajax("/api/comment",{articleId:id},function (e) {
                app.log(e);
                if(e.code == 1){
                    parserHtml(e.data.page);

                }
            }).get();

            app.event("#commentForm","submit",function (e) {
                var data = {
                    articleId:id,
                    content:$("#comment_content").val()
                }
                app.ajax("/api/comment",JSON.stringify(data),function (e) {
                    if(e.code == 1){
                        $("#comment_content").val("");
                    }else {
                        alert(e.info);
                    }
                }).post(true);
                return false;
            })
        }
    })
</script>




<%@ include file="/WEB-INF/jsp/views/Inc/footer.jsp" %>