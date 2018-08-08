<html>
    <#include "../common/Head.ftl"/>
<body class="layui-layout-body">
<div class="layui-layout layui-layout-admin">
        <#include "../common/NavigationBar.ftl" />
    <div class="layui-body">
        <!-- 内容主体区域 -->
        <div style="padding: 15px;">
            <div class="layui-row">
                <div class="layui-col-md12">
                    <form action="/manage/upload" method="post" enctype="multipart/form-data">
                        <input type="file" name="file">
                        <input type="submit" value="上传"/>
                    </form>
                </div>
                <img src="/images/upload.jpg">
            </div>
            <#--<div class="layui-row">
                <div class="layui-col-md12 article-edit-area">
                    <form class="layui-form" action="">
                        <div class="layui-form-item">
                            <label class="layui-form-label">标题</label>
                            <div class="layui-input-block">
                                <input type="text" name="title" required  placeholder="请输入标题" autocomplete="off" class="layui-input">
                            </div>
                        </div>

                        <div class="layui-form-item">
                            <button type="button" class="layui-btn" id="test1">
                                <i class="layui-icon">&#xe67c;</i>上传图片
                            </button>
                        </div>

                        <div class="layui-form-item layui-form-text">
                            <label class="layui-form-label">文本域</label>
                            <div class="layui-input-block">
                                <textarea name="desc" placeholder="请输入内容" class="layui-textarea"></textarea>
                            </div>
                        </div>
                        <div class="layui-form-item option">
                            <label class="layui-form-label">选择分类</label>
                            <div class="layui-input-block">
                                <select name="city" >
                                    <option value="">请选择文章分类</option>
                                    <option value="0">北京</option>
                                </select>
                            </div>
                        </div>

                        <div class="layui-form-item option">
                            <label class="layui-form-label">选择状态</label>
                            <div class="layui-input-block">
                                <select name="city" >
                                    <option value="">请选择文章状态</option>
                                    <option value="0">暂不发布</option>
                                    <option value="1">立即发布</option>
                                </select>
                            </div>
                        </div>

                        <div class="layui-form-item">
                            <div class="layui-input-block">
                                <button class="layui-btn">提交</button>
                            </div>
                        </div>

                    </form>
                </div>
            </div>-->
        </div>
    </div>
</div>
        <#include "../common/Foot.ftl"/>
<#--<script>
    layui.use('upload', function(){
        var upload = layui.upload;

        //执行实例
        var uploadInst = upload.render({
            elem: '#test1' //绑定元素
            ,url: '/upload/' //上传接口
            ,done: function(res){
                //上传完毕回调
            }
            ,error: function(){
                //请求异常回调
                alert("有问题");
            }
        });
    });
</script>-->
</body>
</html>