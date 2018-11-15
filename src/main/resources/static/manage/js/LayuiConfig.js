// 自定义Layui产生的配置文件


// 后台管理模板那里的动效
layui.use('element', function(){
    var element = layui.element;

});

//表单的渲染
layui.use('form', function(){
    var form = layui.form;
    form.render(); //更新全部
});

//文件上传
layui.use('upload', function(){
    var upload = layui.upload;
    //执行实例
    var uploadInst = upload.render({
        elem: '#uploadImg' //绑定元素
        ,url: '/api/backend/upload' //上传接口
        ,done: function(res){
            if(res.code === 14) {
                $("#imgPath").val(res.data);
                $("#imgPreview").attr('src', res.data);
            }
            else
                alert(res.msg);
        }
        ,error: function(){
            alert("请求异常");
        }
    });
});