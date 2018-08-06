<html>
    <#include "../common/Head.ftl" />
    <body>
        <div class="layui-container login">
            <div class="layui-row">
                <form class="layui-form" action="#">
                    <p class="title">登录</p><br/>
                    <div class="layui-form-item">
                        <label class="layui-form-label">账号</label>
                        <div class="layui-input-block">
                            <input type="text" name="title" required placeholder="请输入账号" autocomplete="off" class="layui-input">
                        </div>
                    </div>
                    <div class="layui-form-item">
                        <label class="layui-form-label">密码</label>
                        <div class="layui-input-block">
                            <input type="password" name="password" required placeholder="请输入密码" autocomplete="off" class="layui-input">
                        </div>
                    </div>
                    <div class="layui-form-item">
                        <div class="layui-input-block">
                            <button class="layui-btn">登录</button>
                        </div>
                    </div>
                </form>
            </div>
        </div>
        <#include "../common/Foot.ftl"/>
    </body>
</html>