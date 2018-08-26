$(function () {

   $('#keywords').bind("keypress", function (event) {
       if(event.keyCode === 13) {
           search($('#keywords').val());
       }
   });

    $('#search-input').bind("keypress", function (event) {
        if(event.keyCode === 13) {
            search($('#search-input').val());
        }
    });

    $('#mKeywords').bind("keypress", function (event) {
        if(event.keyCode === 13) {
            search($('#mKeywords').val());
        }
    });

    $('#m-search-input').bind("keypress", function (event) {
        if(event.keyCode === 13) {
            search($('#m-search-input').val());
        }
    });

    //点击按钮显示菜单
    $("#showMenu").click(function (event) {
        event.stopPropagation();        //阻止冒泡事件
        $("#mobile-navigation").show(100);
    });

    //点击任意位置隐藏菜单
    $(document).click(function () {
        $("#mobile-navigation").hide(100);
    });
});

/**
 * pc端触发搜索功能
 */
function go() {
    var keywords = $("#search-input").val();
    search(keywords);
}

/**
 * 移动端触发搜索功能
 */
function mGo() {
    var keywords = $("#m-search-input").val();
    search(keywords);
}

/**
 * 搜索功能
 * @param keywords
 * @returns {number}
 */
function search(keywords) {
    if(keywords === "") {
        alert("输入不能为空");
        return 0;
    }
    window.location.href = "/search?keywords=" + keywords;
}


