// 管理端的js
$(function () {

    $("#edit-area").hide();
    $("#navigation-edit-area").hide();
});

/**
 * 用户登录操作
 */
function login() {
    var account = $("#account").val();
    var password = $("#password").val();
    var captchaCode = $("#captchaCode").val();
    $.ajax({
        url : "/api/doLogin",
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
}

/**
 * 添加文章分类
 */
function addCategory() {
    var name = $("#name").val();
    var status = $("#status").val();
    var parentId = $("#parentId").val();
    if(name === "" || parentId === "" || status === "")
        alert("不能为空");
    else {
        $.ajax({
            url: "/api/manage/category",
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
            error: function () {
                alert("Ajax传输失败");
            }
        });
    }
}

/**
 * 更改文章分类状态
 * @param id
 */
function changeCategoryStatus(id) {
    $.ajax({
        url: '/api/manage/category/' + id,
        type: "PATCH",
        async: false,
        success: function(data) {
            if(data.code === 10 )
                window.location.reload();
            else
                alert(data.msg);
        },
        error: function () {
            alert('Ajax传输失败')
        }
    });
}

/**
 * 删除文章分类
 * @param id
 */
function deleteCategory(id) {
    if(confirm("真的要删除吗？")) {
        $.ajax({
            url: '/api/manage/category/' + id,
            type: "DELETE",
            async: false,
            success: function (data) {
                if(data.code === 10)
                    window.location.reload();
                else
                    alert(data.msg);
            },
            error: function () {
                alert("Ajax传输失败");
            }
        });
    }
}

/**
 * 显示分类信息编辑区
 * @param id
 * @param name
 * @param parentId
 * @param status
 */
function showArea(id, name, parentId, status) {
    $("#editId").val(id);
    $("#editName").val(name);
    $("#edit-area").show(1000);
}

/**
 * 修改文章分类信息
 */
function editCategory() {
    var id = $("#editId").val();
    var name = $("#editName").val();
    var status = $("#editStatus").val();
    var parentId = $("#editParentId").val();
    if(name === "" || parentId === "" || status === "")
        alert("不能为空");
    else {
        $.ajax({
            url: "/api/manage/category",
            type: "PUT",
            data: {"id": id, "name":name, "parentId":parentId, "status":status},
            dataType: "JSON",
            async: false,
            success: function(data) {
                if(data.code === 10) {
                    window.location.reload();
                }
                else
                    alert(data.msg);
            },
            error: function () {
                alert("Ajax传输失败");
            }
        });
    }
}

/**
 * 更改文章状态
 * @param id
 */
function changeArticleStatus(id) {
    $.ajax({
        url: '/api/manage/article/' + id,
        type: "PATCH",
        async: false,
        success: function(data) {
            if(data.code === 10 )
                window.location.reload();
            else
                alert(data.msg);
        },
        error: function () {
            alert('Ajax传输失败')
        }
    });
}

/**
 * 添加一篇文章
 */
function addArticle() {
    var title = $("#title").val();
    var imgPath = $("#imgPath").val();
    var content = ueditor.getContent();
    var summary = ueditor.getPlainTxt();
    var categoryId = $("#categoryId").val();
    var status = $("#status").val();
    $.ajax({
        url : "/api/manage/article",
        type: "POST",
        data: {"id": '-1', "title": title, "img": imgPath, "content": content,
        "summary": summary, "categoryId": categoryId, "status": status},
        dataType: "JSON",
        async: false,
        success: function (data) {
            if(data.code === 10) {
                window.location.href = "/manage/article";
            }
            else
                alert(data.msg);
        },
        error: function() {
            alert("AJAX传输失败");
        }
    });
}

/**
 * 根据id值删除一篇文章
 * @param id
 */
function deleteArticle(id) {
    if(confirm("真的要删除吗？")) {
        $.ajax({
            url: '/api/manage/article/' + id,
            type: "DELETE",
            async: false,
            success: function (data) {
                if(data.code === 10)
                    window.location.reload();
                else
                    alert(data.msg);
            },
            error: function () {
                alert("Ajax传输失败");
            }
        });
    }
}

/**
 * 修改文章内容
 */
function editArticle() {
    var id = $("#id").val();
    var title = $("#title").val();
    var imgPath = $("#imgPath").val();
    var content = ueditor.getContent();
    var summary = ueditor.getPlainTxt();
    var categoryId = $("#categoryId").val();
    var status = $("#status").val();
    $.ajax({
        url : "/api/manage/article",
        type: "PUT",
        data: {"id": id, "title": title, "img": imgPath, "content": content,
            "summary": summary, "categoryId": categoryId, "status": status},
        dataType: "JSON",
        async: false,
        success: function (data) {
            if(data.code === 10) {
                window.location.href = "/manage/article";
            }
            else
                alert(data.msg);
        },
        error: function() {
            alert("AJAX传输失败");
        }
    });
}

/**
 * 添加一个导航
 */
function addNavigation() {
    var name = $("#name").val();
    var status = $("#status").val();
    var link = $("#link").val();
    var priority = $("#priority").val();
    if(name === "" || link === "" || status === "" || priority === "")
        alert("不能为空");
    else {
        $.ajax({
            url: "/api/manage/navigation",
            type: "POST",
            data: {"id": '-1', "name":name, "priority":priority, "link":link, "status":status},
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
            error: function () {
                alert("Ajax传输失败");
            }
        });
    }
}

/**
 * 改变导航状态
 * @param id
 */
function changeNavigationStatus(id) {
    $.ajax({
        url: '/api/manage/navigation/' + id,
        type: "PATCH",
        async: false,
        success: function(data) {
            if(data.code === 10 )
                window.location.reload();
            else
                alert(data.msg);
        },
        error: function () {
            alert('Ajax传输失败')
        }
    });
}

/**
 * 显示修改导航信息的区域
 * @param id
 * @param name
 * @param priority
 * @param link
 */
function showNavigationArea(id, name, priority, link) {
    $("#editId").val(id);
    $("#editName").val(name);
    $("#editPriority").val(priority);
    $("#editLink").val(link);
    $("#navigation-edit-area").show(1000);
}

/**
 * 修改某个导航的信息
 */
function editNavigation() {
    var id = $("#editId").val();
    var name = $("#editName").val();
    var priority = $("#editPriority").val();
    var link = $("#editLink").val();
    var status = $("#editStatus").val();
    if(name === "" || priority === "" || status === "" || link === '')
        alert("不能为空");
    else {
        $.ajax({
            url: "/api/manage/navigation",
            type: "PUT",
            data: {"id": id, "name":name, "priority":priority, "link": link, "status":status},
            dataType: "JSON",
            async: false,
            success: function(data) {
                if(data.code === 10) {
                    window.location.reload();
                }
                else
                    alert(data.msg);
            },
            error: function () {
                alert("Ajax传输失败");
            }
        });
    }
}

/**
 * 删除一个导航信息
 * @param id
 */
function deleteNavigation(id) {
    if(confirm("真的要删除吗？")) {
        $.ajax({
            url: '/api/manage/navigation/' + id,
            type: "DELETE",
            async: false,
            success: function (data) {
                if(data.code === 10)
                    window.location.reload();
                else
                    alert(data.msg);
            },
            error: function () {
                alert("Ajax传输失败");
            }
        });
    }
}

/**
 * 更改用户信息
 */
function editUserInfo() {
    var username = $("#username").val();
    var imgPath = $("#imgPath").val();
    $.ajax({
        url: "/api/manage/user",
        type: "PUT",
        data: {"username": username, "avatar": imgPath},
        dataType: "JSON",
        async: false,
        success: function(data) {
            if(data.code === 10)
                window.location.reload();
            else
                alert(data.msg);
        },
        error: function() {
            alert("信息更新失败");
        }
    });
}

/**
 * 用户退出登录
 */
function logout() {
    if(confirm("确认退出？")) {
        $.ajax({
            url: "/api/manage/user/logout",
            type: "GET",
            async: false,
            success: function(data) {
                if(data.code === 10)
                    window.location.href = "/manage";
                else
                    alert("登出失败");
            },
            error: function() {
                alert("信息更新失败");
            }
        });
    }
}