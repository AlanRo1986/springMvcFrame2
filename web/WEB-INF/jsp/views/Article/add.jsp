<%--
  Created by IntelliJ IDEA.
  User: alan.luo
  Date: 2017/11/6
  Time: 9:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/jsp/views/Inc/hearder.jsp" %>

<link href="/resource/lib/umeditor/themes/default/css/umeditor.min.css" type="text/css" rel="stylesheet">
<script src="/resource/lib/umeditor/third-party/template.min.js" type="application/x-javascript"></script>
<script src="/resource/lib/umeditor/umeditor.min.js" type="application/x-javascript"></script>
<script src="/resource/lib/umeditor/umeditor.config.js" type="application/x-javascript"></script>
<script src="/resource/lib/umeditor/lang/zh-cn/zh-cn.js" type="text/javascript"></script>


<div class="bs-example" data-example-id="basic-forms">
    <form id="articleAdd">
        <div class="form-group">
            <label for="title">标题</label>
            <input type="text" class="form-control" id="title" placeholder="输入标题">
        </div>
        <div class="form-group">
            <label for="content">内容</label>
            <div id="content" style="width:100%;height: 300px"></div>
        </div>

        <button type="submit" class="btn btn-default">发表</button>
    </form>
</div>
<script>
    $(function () {
        var id = "${id}";
        var ed = UM.getEditor("content");

        app.event("#articleAdd","submit",function (e) {
            var data = {
                title:$("#title").val(),
                content:ed.getContent()
            };

            if (id > 0){
                data.id = id;
            }
            data = JSON.stringify(data);
            var http = app.ajax("/api/article",data,function (e) {
                app.log(e)
                alert(e.info);
                if(e.code == 1){
                    location.href = "/home";
                }
            })
            if (id > 0){
                http.put(true);
            }else {
                http.post(true);
            }

            return false;
        });
    })
</script>


<%@ include file="/WEB-INF/jsp/views/Inc/footer.jsp" %>