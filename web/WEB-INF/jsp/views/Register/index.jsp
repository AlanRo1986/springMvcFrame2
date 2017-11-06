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
    <form id="registerFrorm">
        <div class="form-group">
            <label for="nickname">昵称</label>
            <input type="text" class="form-control" id="nickname" name="nickname" placeholder="输入昵称">
        </div>
        <div class="form-group">
            <label for="nickname">邮箱地址</label>
            <input type="email" class="form-control" id="email" name="email" placeholder="输入联系邮箱">
        </div>
        <div class="form-group">
            <label for="mobile">手机号码</label>
            <input type="number" class="form-control" id="mobile" name="mobile" placeholder="输入手机号码">
        </div>
        <div class="form-group">
            <label for="username">用户名</label>
            <input type="text" class="form-control" id="username" name="username" placeholder="输入用户名">
        </div>
        <div class="form-group">
            <label for="loginPassword">密码</label>
            <input type="text" class="form-control" id="loginPassword" name="loginPassword" placeholder="输入密码">
        </div>
        <button type="submit" class="btn btn-primary" style="width: 100%;">注册</button>
    </form>
</div>


<%@ include file="/WEB-INF/jsp/views/Inc/footer.jsp" %>