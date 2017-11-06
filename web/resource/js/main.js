/**
 * Created by alan.luo on 2017/11/3.
 */

$(function () {
    $(".loading").hide();
    app.event("#loginForm","submit",function (e) {
        app.ajax("/api/login",$("#loginForm").serialize(),function (e) {
            alert(e.info);
            if (e.code == 1){
                app.local.set("token",e.data.token);
                location.href = "/home";
            }
        }).post();
        return false;
    });
    app.event("#registerFrorm","submit",function (e) {
        var data = {
            nickname:$("#nickname").val(),
            email:$("#email").val(),
            mobile:$("#mobile").val(),
            username:$("#username").val(),
            loginPassword:$("#loginPassword").val(),
        };
        data = JSON.stringify(data);
        app.ajax("/api/user",data,function (e) {
            console.log(e);
            alert(e.info);
            if (e.code == 1){
                location.href = "/login";
            }
        }).post(true);
        return false;
    });
    app.event(".loading","click",function () {
        $(".loading").hide();
    })
})
