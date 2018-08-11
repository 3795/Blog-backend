<html>
    <#include "../common/Head.ftl"/>
<body class="layui-layout-body">
<div class="layui-layout layui-layout-admin">
            <#include "../common/NavigationBar.ftl" />
    <div class="layui-body">
        <!-- 内容主体区域 -->
        <div style="padding: 15px;">
            <div class="layui-row">
                <div class="layui-col-md12 edit-user-info">
                    <p class="title">修改信息</p>
                    <form class="layui-form" action="#">
                        <div class="layui-form-item">
                            <label class="layui-form-label">用户名</label>
                            <div class="layui-input-block">
                                <input type="text" name="username" id="username" value="${user.username!}" required placeholder="请输入用户名" autocomplete="off" class="layui-input">
                            </div>
                        </div>

                        <div class="layui-form-item">
                            <button type="button" class="layui-btn" id="uploadImg">
                                <i class="layui-icon">&#xe67c;</i>上传图片
                            </button>
                        </div>

                        <input type="hidden" name="imgPath" id="imgPath" class="layui-input" value="${user.avatar!}">

                        <div class="layui-form-item">
                            <label class="layui-form-label">图片预览</label>
                            <div class="layui-input-block">
                                <img src="${user.avatar}" id="imgPreview" alt="图片预览" width="10%" height="10%"/>
                            </div>
                        </div>

                        <div class="layui-form-item">
                            <div class="layui-input-block">
                                <button class="layui-btn" onclick="editUserInfo()" type="button">提交</button>
                            </div>
                        </div>

                    </form>
                </div>
            </div>
        </div>

    </div>
</div>
        <#include "../common/Foot.ftl"/>
</body>
</html>