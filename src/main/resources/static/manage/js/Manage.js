// 管理端的js
$(function () {

    $("#edit-area").hide();
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
            url: "/api/manage/category/add",
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
            url: "/api/manage/category/update",
            type: "POST",
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

function addArticle() {
    var title = $("#title").val();
    var imgPath = $("#imgPath").val();
    var content = ueditor.getContent();
    var summary = ueditor.getPlainTxt();
    var categoryId = $("#categoryId").val();
    var status = $("#status").val();
    $.ajax({
        url : "/api/manage/article/add",
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
    //window.location.href = "/manage/article";

}