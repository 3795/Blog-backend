// 管理端的js

/**
 * 用户登录操作
 */
function login() {
    var account = $("#account").val();
    var password = $("#password").val();
    var captchaCode = $("#captchaCode").val();
    $.ajax({
        url : "/blog/v1/login",
        type: "POST",
        data: {"account": account, "password": password, "captchaCode": captchaCode},
        dataType: "JSON",
        async: false,
        success: function(data) {
            if(data.code === 11) {
                window.location.href = "/backend/index";
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
            url: "/api/backend/category",
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
        url: '/api/backend/category/' + id,
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
            url: '/api/backend/category/' + id,
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
    $(".category-edit-form").show(1000);
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
            url: "/api/backend/category",
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
        url: '/api/backend/article/' + id,
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
    var content = $("#editorContent").val();
    var summary = $("#summary").val();
    var categoryId = $("#categoryId").val();
    var status = $("#status").val();
    $.ajax({
        url : "/api/backend/article",
        type: "POST",
        data: {"id": '-1', "title": title, "img": imgPath, "content": content,
        "summary": summary, "categoryId": categoryId, "status": status},
        dataType: "JSON",
        async: false,
        success: function (data) {
            if(data.code === 10) {
                window.location.href = "/backend/article";
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
            url: '/api/backend/article/' + id,
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
    var content = $("#editorContent").val();
    var summary = $("#summary").val();
    var categoryId = $("#categoryId").val();
    var status = $("#status").val();
    $.ajax({
        url : "/api/backend/article",
        type: "PUT",
        data: {"id": id, "title": title, "img": imgPath, "content": content,
            "summary": summary, "categoryId": categoryId, "status": status},
        dataType: "JSON",
        async: false,
        success: function (data) {
            if(data.code === 10) {
                window.location.href = "/backend/article";
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
            url: "/api/backend/navigation",
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
        url: '/api/backend/navigation/' + id,
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
    $(".category-edit-form").show(1000);
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
            url: "/api/backend/navigation",
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
            url: '/api/backend/navigation/' + id,
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
        url: "/api/backend/user",
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
            url: "/api/backend/user/logout",
            type: "GET",
            async: false,
            success: function(data) {
                if(data.code === 10)
                    window.location.href = "/backend";
                else
                    alert("登出失败");
            },
            error: function() {
                alert("信息更新失败");
            }
        });
    }
}