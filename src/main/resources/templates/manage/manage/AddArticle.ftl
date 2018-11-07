<html>
    <#include "../common/Head.ftl"/>
<body class="layui-layout-body">
<div class="layui-layout layui-layout-admin">
        <#include "../common/NavigationBar.ftl" />
    <div class="layui-body">
        <!-- 内容主体区域 -->
        <div style="padding: 15px;">
            <div class="layui-row">
                <div class="layui-col-md12 article-edit-area">
                    <form class="layui-form" action="">
                        <div class="layui-form-item">
                            <label class="layui-form-label">标题</label>
                            <div class="layui-input-block">
                                <input type="text" name="title" id="title" required  placeholder="请输入标题" autocomplete="off" class="layui-input">
                            </div>
                        </div>

                        <div class="layui-form-item">
                            <button type="button" class="layui-btn" id="uploadImg">
                                <i class="layui-icon">&#xe67c;</i>上传图片
                            </button>
                        </div>

                        <input type="hidden" name="imgPath" id="imgPath" class="layui-input">


                        <div class="layui-form-item">
                            <label class="layui-form-label">图片预览</label>
                            <div class="layui-input-block">
                                <img src="/images/imgPreview.png" id="imgPreview" alt="图片预览" width="10%" height="10%"/>
                            </div>
                        </div>

                        <div class="layui-form-item">
                            <label class="layui-form-label">文章摘要</label>
                            <div class="layui-input-block">
                                <textarea id="summary" class="layui-textarea">我是摘要的内容</textarea>
                            </div>
                        </div>

                            <div class="editormd" id="editormd">
                                <textarea class="editormd-markdown-textarea" style="display:none;" id="editorContent"></textarea>
                                <textarea class="editormd-html-textarea" id="htmlContent"></textarea>
                            </div>

                        <div class="layui-form-item option">
                            <label class="layui-form-label">选择分类</label>
                            <div class="layui-input-block">
                                <select name="categoryId" id="categoryId">
                                    <option value="">请选择文章分类</option>
                                    <#list categoryList as category>
                                        <#if category.parentId != 0>
                                            <option value="${category.id}">${category.name}</option>
                                        </#if>
                                    </#list>
                                </select>
                            </div>
                        </div>

                        <div class="layui-form-item option">
                            <label class="layui-form-label">选择状态</label>
                            <div class="layui-input-block">
                                <select name="status" id="status">
                                    <option value="">请选择文章状态</option>
                                    <option value="0">暂不发布</option>
                                    <option value="1">立即发布</option>
                                </select>
                            </div>
                        </div>

                        <div class="layui-form-item">
                            <div class="layui-input-block">
                                <button class="layui-btn" onclick="addArticle()" type="button">提交</button>
                            </div>
                        </div>

                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
        <#include "../common/Foot.ftl"/>
<script type="text/javascript" src="/editor.md/editormd.js"></script>
<script type="text/javascript">
    var testEditor;

    $(function() {
        testEditor = editormd("editormd", {
            width   : "100%",
            height  : 800,
            syncScrolling : "single",
            path    : "/editor.md/lib/",
            saveHTMLToTextarea : true,
            searchReplace : true,
            emoji : true,
            taskList : true,
            tocm: true,
            tex : true,
            flowChart : true,
            sequenceDiagram : true,
            imageUpload : true,
            imageFormats : ["jpg", "jpeg", "gif", "png", "bmp", "webp"],
            imageUploadURL : "/api/manage/editorUpload"
        });
    });
    </script>
</body>
</html>