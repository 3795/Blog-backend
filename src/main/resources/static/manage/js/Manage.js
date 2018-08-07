// 管理端的js
$(function () {
    // 登录的操作
    $("#submit").click(function() {
        var account = $("#account").val();
        var password = $("#password").val();
        var captchaCode = $("#captchaCode").val();
        $.ajax({
            url : "/api/user/doLogin",
            type: "POST",
            data: {"account": account, "password": password, "captchaCode": captchaCode},
            dataType: "JSON",
            async: false,
            success: function(data) {
                if(data.code === 11) {
                    alert("登录成功");
                    window.location.href = "/manage/index";
                }
                else if(data.code === 13)
                    alert("验证码错误");
                else
                    alert("登录失败");
            },
            error: function() {
                alert("数据没有传过去");
            }
        });
    });
});

/**
 * 添加文章分类
 */
function addCategory() {
    var name = $("#name").val();
    var status = $("#status").val();
    var parentId = $("#parentId").val();
    if(name === "" || parentId === "")
        alert("不能为空");
    else {
        $.ajax({
            url: "/api/category/add",
            type: "POST",
            data: {"name":name, "parentId":parentId, "status":status},
            dataType: "JSON",
            async: false,
            success: function(data) {
                if(data.code === 10) {
                    alert(data.msg);
                    window.location.reload();
                }
                else
                    alert(data.msg);
            },
            error: function (data) {
                alert("Ajax传输失败");
            }
        });
    }
}