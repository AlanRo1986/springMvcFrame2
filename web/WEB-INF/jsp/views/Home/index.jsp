<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<%--
  Created by IntelliJ IDEA.
  User: alan.luo
  Date: 2017/9/22
  Time: 19:46
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/jsp/views/Inc/hearder.jsp" %>

<div class="bs-example" data-example-id="hoverable-table">
    <table class="table table-hover">
        <thead>
        <tr>
            <th>#</th>
            <th>title</th>
            <th>updated time</th>
            <th>Username</th>
        </tr>
        </thead>
        <tbody id="tbody">

        </tbody>
    </table>
</div>
<script>
    $(function () {


        function getArticle(page,keywords) {
            var data = new Object();
            if (typeof page == "number"){
                data.page = page;
            }
            if (typeof keywords == "String"){
                data.keywords = keywords;
            }

            app.ajax("/api/article",data,function (e) {
                app.log(e);
                if(e.code == 1){
                    appendHtml(e.data.page);
                }

            }).get()
        }
        function appendHtml(pages) {
            var list = pages.list;
            var $tbody = $("#tbody");
            var h = "";
            for(var i = 0;i < list.length;i++){
                h = '<tr>' +
                    '<td>' +
                    '   <span class="label label-success">'+list[i].viewCount+'</span> <span class="label label-info">'+list[i].commentCount+'</span>' +
                    '</td>' +
                    '<td>' +
                    '   <a href="/article/'+list[i].id+'" target="_blank">'+list[i].title+'</a>' +
                    '</td>' +
                    '<td>' +
                    '   <span>'+list[i].updateTimeFormat+'</span>' +
                    '</td>' +
                    '<td>' +
                    '   <span>'+list[i].author.nickname+'</span>' +
                    '</td>' +
                    '</tr>';
                $tbody.append(h);
            };
//            <tr>
//            <th scope="row">
//                <span class="label label-success">100</span>
//                <span class="label label-info">5</span>
//                </th>
//                <td>
//                <a href="/article/0" target="_blank">这里是文章的标题</a>
//                </td>
//                <td>2017-01-01 11:11:00</td>
//            <td>@Alan</td>
//            </tr>
        }
        getArticle();
    })
</script>
<%@ include file="/WEB-INF/jsp/views/Inc/footer.jsp" %>