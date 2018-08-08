// 自定义Layui产生的配置文件


// 后台管理模板那里的动效
layui.use('element', function(){
    var element = layui.element;

});

layui.use('form', function(){
    var form = layui.form;
    form.render(); //更新全部
});