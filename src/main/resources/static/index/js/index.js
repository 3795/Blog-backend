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
});

function go() {
    var keywords = $("#search-input").val();
    search(keywords);
}

function search(keywords) {
    if(keywords === "") {
        alert("输入不能为空");
        return 0;
    }
    window.location.href = "/search?keywords=" + keywords;
}