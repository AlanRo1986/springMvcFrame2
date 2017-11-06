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

<div class="bs-example" data-example-id="basic-forms">
    <form id="loginForm">
        <div class="form-group">
            <label for="username">用户名:</label>
            <input type="text" class="form-control" id="username" name="username" placeholder="input user name">
        </div>
        <div class="form-group">
            <label for="password">密码:</label>
            <input type="password" class="form-control" id="password" name="password" placeholder="input user password">
        </div>
        <div class="form-group">
            <a href="/register" target="_blank">没有账号?注册</a>
        </div>
        <button type="submit" class="btn btn-primary" style="width: 100%;">登录</button>
    </form>
</div>




<%@ include file="/WEB-INF/jsp/views/Inc/footer.jsp" %>